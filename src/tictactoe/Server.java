package tictactoe;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            serverSocket = new ServerSocket(49000);

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

    DataInputStream inputStream;
    PrintStream outStream;
    int unreliableId;
    volatile int reliableId;
    volatile boolean grantAcssesState = false;
    private int gameID;
    static volatile int waitingPlayerId = 0;
    public volatile String msgFromClient = "";
    public PlayerInformation pInfo = new PlayerInformation();

    //nihal
    static int symbol = 0;
    static int turn = 0;

    static Vector<ServerHandler> unauthorizedClientsVector = new Vector<ServerHandler>();

    static HashMap<Integer, ServerHandler> clients = new HashMap<Integer, ServerHandler>();
    
    static HashMap<Integer, Game> runningGames = new HashMap<Integer, Game>();
    boolean startRecord = false;

    public ServerHandler(Socket cs, int unauthorizedId) {
        try {
            inputStream = new DataInputStream(cs.getInputStream());
            outStream = new PrintStream(cs.getOutputStream());
            unreliableId = unauthorizedId;
            unauthorizedClientsVector.add(this);

            outStream.println("00" + "0" + unreliableId); //00 auth  0 unauthorized 
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
                msgFromClient = inputStream.readLine();
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
                pInfo = Server.dBase.checkLoginUser(userName, password);
                if (pInfo.playerId != -1) {
                    this.reliableId = pInfo.playerId;
                    this.outStream.println("00_" + "1_" +pInfo.playerId +"_"+pInfo.playerName+"_"+pInfo.playerAvatar);
                    clients.put(this.reliableId, this);
                    unauthorizedClientsVector.remove(this);
                }
                else
                {
                    this.outStream.println("00" + "T"); //wrong user
                }
                break;
            }
            case "rg": //want to register
            {
                String temp = msg.substring(3);
                StringTokenizer p = new StringTokenizer(temp, "_"); 
                String name = p.nextToken();
                String email = p.nextToken();
                String password = p.nextToken();
                int avatarId = Integer.parseInt(p.nextToken());
                pInfo = Server.dBase.RegisterNewUser(name, password,email,avatarId);
                if (pInfo.playerId != -1) {
                    this.reliableId = pInfo.playerId;
                    this.outStream.println("rg_" + "1_" +pInfo.playerId +"_"+pInfo.playerName+"_"+pInfo.playerAvatar);
                    clients.put(this.reliableId, this);
                    unauthorizedClientsVector.remove(this);
                }
                else
                {
                    this.outStream.println("rg_" + "0");//reg faild
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
                            clients.get(waitingPlayerId).outStream.println("040"+this.pInfo.playerName+"_"+this.pInfo.playerAvatar);
                            clients.get(waitingPlayerId).gameID = game.gameId;
                            this.gameID = game.gameId;
                            this.outStream.println("041"+clients.get(waitingPlayerId).pInfo.playerName+"_"+clients.get(waitingPlayerId).pInfo.playerAvatar);
                            waitingPlayerId = 0;
                        }
                    }

                } else // want to play against ai
                {
                    Game game = new Game(this.reliableId,1);
                    game.gameId = Server.dBase.addNewGame(game.getGameDate());
                    runningGames.put(game.gameId, game);
                    this.gameID = game.gameId;
                    this.outStream.println("04I0");                    
                }
                break;
            }
            case "CW": // cancel waiting
            {
                if(this.reliableId == waitingPlayerId)
                {
                    waitingPlayerId=0;
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
            case "ZD":
                getProfileData();
                break;

            case "S0": //sign out
            {
                unauthorizedClientsVector.add(this);
                clients.remove(this.reliableId);
                this.reliableId = -1;
                this.grantAcssesState = false;
                break;
            }
            case "0M": 
            {
                if(runningGames.get(this.gameID).playerOneId == 1)
                    clients.get(runningGames.get(this.gameID).playerwoId).outStream.println(msg);
                else if(runningGames.get(this.gameID).playerwoId == 1)
                    clients.get(runningGames.get(this.gameID).playerOneId).outStream.println(msg);
                else
                {
                    clients.get(runningGames.get(this.gameID).playerOneId).outStream.println(msg);
                    clients.get(runningGames.get(this.gameID).playerwoId).outStream.println(msg);
                }
                break;
            }
             case "sm": {
                this.outStream.println("sm");

                try {
                    msgFromClient = inputStream.readLine();//read client
                    int ID = Integer.parseInt(msg.substring(2));
                    String symbols = Server.dBase.simulate(ID);
                    System.out.println(ID + symbols);
                    outStream.println("sy" + symbols);
                    System.err.println("done");

                } catch (IOException ex) {
                    Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
            case "sg": {
                clients.get(reliableId).startRecord = true;
                break;
            }
        }
    }

    void getProfileData() {
        String uname = Server.dBase.getUsername(this.reliableId);
        uname += "_";
        uname += Server.dBase.getMatches(this.reliableId, "w");
        uname += "_";
        uname += Server.dBase.getMatches(this.reliableId, "l");
        uname += "_";
        uname += Server.dBase.getMatches(this.reliableId, "d");
        uname += ".";
        uname += Server.dBase.getSavedGames(this.reliableId);

        outStream.println("ZD" + uname);

    }
    void makeMoveANDSwitch(String msg) {
//        int row = Character.getNumericValue(msg.charAt(3)) / 3;
//        int col = Character.getNumericValue(msg.charAt(3)) % 3;
//        runningGames.get(this.gameID).gameBoard[row][col] = Character.toString(msg.charAt(2));
//        String res = runningGames.get(this.gameID).checkWinner();
//        clients.get(runningGames.get(this.gameID).playerOneId).outStream.println("0S"+res+msg.substring(2));
//        clients.get(runningGames.get(this.gameID).playerwoId).outStream.println("0S"+res+msg.substring(2));
//        System.out.println("OS"+res+msg.substring(2));
           int row = Character.getNumericValue(msg.charAt(3)) / 3;
        int col = Character.getNumericValue(msg.charAt(3)) % 3;
        int player2ID = 0;
        String player1State, player2State;
        runningGames.get(this.gameID).gameBoard[row][col] = Character.toString(msg.charAt(2));
        String res = runningGames.get(this.gameID).checkWinner();
        if (!res.equals("_")) //player win or his move result in draw
        {
            if (reliableId == runningGames.get(gameID).playerOneId) {
                player2ID = runningGames.get(gameID).playerwoId;
            } else {
                player2ID = runningGames.get(gameID).playerOneId;
            }
            if (res.equals("d")) {
                player1State = "d";
                player2State = "d";
            } else {
                player1State = "w";
                player2State = "l";
            }
            Server.dBase.savedGameResult(gameID, reliableId, player1State, clients.get(reliableId).startRecord, msg.charAt(2));

            if (Character.toString(msg.charAt(2)).equals("O")) {
                Server.dBase.savedGameResult(gameID, player2ID, player2State, clients.get(player2ID).startRecord, 'X');
            } else {
                Server.dBase.savedGameResult(gameID, player2ID, player2State, clients.get(player2ID).startRecord, 'O');
            }
        }
        clients.get(runningGames.get(this.gameID).playerOneId).outStream.println("0S" + res + msg.substring(2));
        clients.get(runningGames.get(this.gameID).playerwoId).outStream.println("0S" + res + msg.substring(2));
        System.out.println("OS" + res + msg.substring(2));
        if (runningGames.get(gameID).firstPlay == true) {
            runningGames.get(gameID).record += 'X';
            runningGames.get(gameID).firstPlay = false;
        }
        runningGames.get(gameID).record += '_';
        runningGames.get(gameID).record += msg.charAt(3);
        if (!res.equals("_") && (clients.get(reliableId).startRecord || clients.get(player2ID).startRecord)) {
            Server.dBase.saveMatch(gameID, runningGames.get(gameID).record);
        }  
    }
    void makeMoveANDSwitchAi(String msg) {
//        int row = Character.getNumericValue(msg.charAt(3)) / 3;
//        int col = Character.getNumericValue(msg.charAt(3)) % 3;
//        runningGames.get(this.gameID).gameBoard[row][col] = Character.toString(msg.charAt(2));
//        String res = runningGames.get(this.gameID).checkWinner();
//        if (!res.equals("_")) //player win or his move result in draw
//        {
//            clients.get(runningGames.get(this.gameID).playerOneId).outStream.println("0S"+res+msg.substring(2));  //size 5         
//        }                   
//        else
//        {       
//            int pos = Game.findBestMove(runningGames.get(this.gameID).gameBoard);
//            row = pos / 3;
//            col = pos % 3;
//            runningGames.get(this.gameID).gameBoard[row][col] = "O";
//            res = runningGames.get(this.gameID).checkWinner();
//            clients.get(runningGames.get(this.gameID).playerOneId).outStream.println("0S"+res+msg.substring(2)+"O"+pos);
//        } 

    int row = Character.getNumericValue(msg.charAt(3)) / 3;
        int col = Character.getNumericValue(msg.charAt(3)) % 3;
        int pos;
        runningGames.get(this.gameID).gameBoard[row][col] = Character.toString(msg.charAt(2));
        String res = runningGames.get(this.gameID).checkWinner();
        if (runningGames.get(gameID).firstPlay == true) {
            runningGames.get(gameID).record += 'X';
            runningGames.get(gameID).firstPlay = false;
        }
        runningGames.get(gameID).record += '_';
        runningGames.get(gameID).record += msg.charAt(3);
        if (!res.equals("_")) //player win or his move result in draw
        {
            if (res.equals("d")) {
                Server.dBase.savedGameResult(gameID, reliableId, res, clients.get(reliableId).startRecord, 'X');
            } else {
                Server.dBase.savedGameResult(gameID, reliableId, "w", clients.get(reliableId).startRecord, 'X');
            }
            clients.get(runningGames.get(this.gameID).playerOneId).outStream.println("0S" + res + msg.substring(2));  //size 5         

        } else {
            pos = Game.findBestMove(runningGames.get(this.gameID).gameBoard);
            row = pos / 3;
            col = pos % 3;
            runningGames.get(this.gameID).gameBoard[row][col] = "O";
            res = runningGames.get(this.gameID).checkWinner();
        
            clients.get(runningGames.get(this.gameID).playerOneId).outStream.println("0S" + res + msg.substring(2) + "O" + pos);
            runningGames.get(gameID).record += '_';
            runningGames.get(gameID).record += pos;
                if (!res.equals("_")) {
                Server.dBase.savedGameResult(gameID, reliableId, "l", clients.get(reliableId).startRecord, 'X');
            }
        }
        if (!res.equals("_") && clients.get(reliableId).startRecord) {
            Server.dBase.saveMatch(gameID, runningGames.get(gameID).record);
        }

    
    }
}
