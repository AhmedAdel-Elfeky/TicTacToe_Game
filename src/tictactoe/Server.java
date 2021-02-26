package tictactoe;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.*;
import javafx.application.Platform;

public class Server {

    ServerSocket serverSocket;
    public static int unAuthorizedClintsNum = 0;
    public static DataBase dBase;

    public static void main(String[] args) {
        new Server();
    }

    public Server() {
        try {
            dBase = new DataBase();
            serverSocket = new ServerSocket(5005);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        while (true) {
            try {
                Socket s = serverSocket.accept();
                new ServerHandler(s, unAuthorizedClintsNum);
                unAuthorizedClintsNum++;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}

class ServerHandler extends Thread {

    DataInputStream dis;
    PrintStream ps;
    int unreliableId;
    volatile int reliableId;
    volatile boolean grantAcssesState = false;
    private int gameID;
    static volatile int waitingPlayerId = 0;
    public volatile String msgFromClient = "";

    //nihal
    static int symbol = 0;
    static int turn = 0;

    static Vector<ServerHandler> unauthorizedClientsVector = new Vector<ServerHandler>();

    static HashMap<Integer, ServerHandler> clients = new HashMap<Integer, ServerHandler>();
    static HashMap<Integer, Game> runningGames = new HashMap<Integer, Game>();

    public ServerHandler(Socket cs, int unauthorizedId) {
        try {
            dis = new DataInputStream(cs.getInputStream());
            ps = new PrintStream(cs.getOutputStream());
            unreliableId = unauthorizedId;
            unauthorizedClientsVector.add(this);

            ps.println("00" + "0" + unreliableId); //00 auth  0 unauthorized 
            if (symbol == 0) {
                symbol = 1;
            } else {
                symbol = 0;
            }
            start();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void run() { //this functio run when the server recieve new data
        while (true) {
            try {
                msgFromClient = dis.readLine();
                ParseDataFromClients(msgFromClient);
            } catch (IOException ex) {
                unauthorizedClientsVector.remove(this);
                break;
            }
        }
    }

    void ParseDataFromClients(String msg) {
        switch (msg.substring(0, 2)) {
            case "01": {
                int userLength = Integer.parseInt(msg.substring(4, 5));
                String userName = msg.substring(5, userLength + 5);
                int passwordLength = Integer.parseInt(msg.substring(5 + userLength, 6 + userLength));
                String password = msg.substring(6 + userLength, passwordLength + 6 + userLength);
                int authResult = Server.dBase.checkLoginUser(userName, password);
                if (authResult != -1) {
                    this.reliableId = authResult;
                    this.ps.println("00" + "1" + authResult);
                    clients.put(this.reliableId, this);
                    unauthorizedClientsVector.remove(this);
                }
                break;
            }
            case "03": //want to play
            {
                if (msg.substring(2, 3).equals("1")) //want to play another second player
                {
                    if (ServerHandler.waitingPlayerId == 0) {
                        waitingPlayerId = this.reliableId;
                    } else {
                        if (this.reliableId != ServerHandler.waitingPlayerId) {
                            Game game = new Game(waitingPlayerId, this.reliableId);
                            game.gameId = Server.dBase.addNewGame(game.getGameDate());
                            runningGames.put(game.gameId, game);
                            clients.get(waitingPlayerId).ps.println("040");
                            clients.get(waitingPlayerId).gameID = game.gameId;
                            this.gameID = game.gameId;
                            this.ps.println("041");
                            waitingPlayerId = 0;
                        }
                    }

                } else // want to play against ai
                {

                    Game game = new Game(this.reliableId,1);
                    game.gameId = Server.dBase.addNewGame(game.getGameDate());
                    runningGames.put(game.gameId, game);
                    this.gameID = game.gameId;
                    this.ps.println("040I");
                    
                }
                break;
            }
            case "AS":
            {
                makeMoveANDSwitchAi(msg);
                break;
            }
            case "0S": {
                makeMoveANDSwitch(msg);
                break;
            }

            case "S0": //sign out
            {
                unauthorizedClientsVector.add(this);
                clients.remove(this.reliableId);
                this.reliableId = -1;
                this.grantAcssesState = false;
                break;
            }
            case "0M": {
                clients.get(runningGames.get(this.gameID).playerOneId).ps.println(msg);
                clients.get(runningGames.get(this.gameID).playerwoId).ps.println(msg);
                break;
            }
        }
    }

    void makeMoveANDSwitch(String msg) {
        int row = Character.getNumericValue(msg.charAt(3)) / 3;
        int col = Character.getNumericValue(msg.charAt(3)) % 3;
        clients.get(runningGames.get(this.gameID).playerOneId).ps.println(msg);
        clients.get(runningGames.get(this.gameID).playerwoId).ps.println(msg);
        runningGames.get(this.gameID).gameBoard[row][col] = Character.toString(msg.charAt(2));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.println(runningGames.get(this.gameID).gameBoard[i][j]);
            }
        }
        
        String res = runningGames.get(this.gameID).checkWinner();
        System.out.println(res + "resault");
        if (!res.equals("")) {
            clients.get(runningGames.get(this.gameID).playerOneId).ps.println("sd" + res);
            clients.get(runningGames.get(this.gameID).playerwoId).ps.println("sd" + res);

        }
    }
    void makeMoveANDSwitchAi(String msg) {
        int row = Character.getNumericValue(msg.charAt(3)) / 3;
        int col = Character.getNumericValue(msg.charAt(3)) % 3;
        clients.get(runningGames.get(this.gameID).playerOneId).ps.println("0S"+msg.substring(2));
        runningGames.get(this.gameID).gameBoard[row][col] = Character.toString(msg.charAt(2));
        int pos = Game.findBestMove(runningGames.get(this.gameID).gameBoard);
        row = pos / 3;
        col = pos % 3;
        runningGames.get(this.gameID).gameBoard[row][col] = "O";
        clients.get(runningGames.get(this.gameID).playerOneId).ps.println("0S"+"O"+pos);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.println(runningGames.get(this.gameID).gameBoard[i][j]);
            }
        }
        String res = runningGames.get(this.gameID).checkWinner();
        System.out.println(res + "resault");
        if (!res.equals("")) {
            clients.get(runningGames.get(this.gameID).playerOneId).ps.println("sd" + res);

        }
    }
}
