package tictactoe;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.*;

public class Server {

    ServerSocket serverSocket;
    public static int unAuthorizedClintsNum=0;
    public static DataBase dBase ;

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
            try 
            {
                Socket s = serverSocket.accept();
                new ServerHandler(s,unAuthorizedClintsNum);
                unAuthorizedClintsNum++;
            } 
            catch (IOException ex) {
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
    volatile boolean GrantAcssesState = false;
    private int GameID ;
    static volatile int waitingPlayerId = 0 ;
    public volatile String msgFromClient = "";
    
    //nihal
    static int symbol = 0;
    static int turn = 0;
    
    static Vector<ServerHandler> clientsVector = new Vector<ServerHandler>();
    static Vector<ServerHandler> unauthorizedClientsVector = new Vector<ServerHandler>();
    
    static HashMap<Integer,ServerHandler> clients = new HashMap<Integer,ServerHandler>();
    static HashMap<Integer,Game> runningGames = new HashMap<Integer,Game>();
    

    public ServerHandler(Socket cs, int unauthorizedId) {
        try 
        {
            dis = new DataInputStream(cs.getInputStream());
            ps = new PrintStream(cs.getOutputStream());
            unreliableId = unauthorizedId;
            unauthorizedClientsVector.add(this);
            
            // unauthorizedClientsVector.forEach(action);
            ps.println("00"+"0"+unreliableId); //00 auth  0 unauthorized 
             if (symbol == 0) {
               // System.out.println("tictactoe.ServerHandler.<iiiiinit>()");
                symbol = 1;
            } else {
               // System.out.println("tictactoe.ServerHandler.<iniiiiiiiiiiiiit>()");
                symbol = 0;
            }
              this.ps.println("0N"+turn++);
             sendMessageToAll("0T" + symbol);
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
                //System.out.println(str);
               // sendMessageToAll(str);
                if (msgFromClient.substring(0,2).equals("0S")) {
                        if (symbol == 0) {
                            symbol = 1;
                        } else {
                            symbol = 0;
                        }
                        sendMessageToAll("0T" + symbol);
                    }
                    sendMessageToAll(msgFromClient);
            } catch (IOException ex) {
                clientsVector.remove(this);
                unauthorizedClientsVector.remove(this);
                break;
            }
        }
    }
    
    void ParseDataFromClients(String msg)
    {
        System.out.println(msg);
        switch(msg.substring(0,2))
        {
            case "01":
            {
                int userLength = Integer.parseInt(msg.substring(4, 5));
                String userName = msg.substring(5, userLength+5);
                int passwordLength = Integer.parseInt(msg.substring(5+userLength, 6+userLength));
                String password = msg.substring( 6+userLength, passwordLength+6+userLength);
                int  authResult = Server.dBase.checkLoginUser(userName, password);
                System.out.println(authResult);
                if(authResult != -1)
                {
                    this.reliableId = authResult;
                    this.ps.println("00"+"1"+authResult);
                    clientsVector.add(this);
                    clients.put(this.reliableId,this);
                    unauthorizedClientsVector.remove(this);
                }
                break;
            }
            case "03":
            {
                if(msg.substring(2, 3).equals("1")) //want to play another second player
                {
                    if(ServerHandler.waitingPlayerId == 0)
                    {
                        waitingPlayerId = this.reliableId;
                    }
                    else
                    {
                        if(this.reliableId != ServerHandler.waitingPlayerId)
                        {
                            Game game = new Game(waitingPlayerId,this.reliableId);
                            clients.get(waitingPlayerId).ps.println("04");
                            this.ps.println("04");
                            waitingPlayerId = 0;
                        }
                    }
                         
                }
                else // want to play against ai
                {
                    
                }
                break;
            }
            case "0S":
            {
                System.out.println(msg);
                break;
            }
        }
    }

    void sendMessageToAll(String st) {
        for (ServerHandler ch : clientsVector) {
            try {
                ch.ps.println(st);
            } catch (Exception ex) {
                ex.printStackTrace();

            }
        }
    }
}
