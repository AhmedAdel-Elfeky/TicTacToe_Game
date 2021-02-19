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
            try {
                Socket s = serverSocket.accept();
                new ChatHandler(s,unAuthorizedClintsNum);
                unAuthorizedClintsNum++;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}

class ChatHandler extends Thread {
    
    DataInputStream dis;
    PrintStream ps;
    int unreliableId;
    int reliableId;
    boolean GrantAcssesState = false;
    
    
    static Vector<ChatHandler> clientsVector = new Vector<ChatHandler>();
    static Vector<ChatHandler> unauthorizedClientsVector = new Vector<ChatHandler>();

    public ChatHandler(Socket cs, int unauthorizedId) {
        try {
            dis = new DataInputStream(cs.getInputStream());
            ps = new PrintStream(cs.getOutputStream());
            unreliableId = unauthorizedId;
            unauthorizedClientsVector.add(this);
            ps.println("00"+"0"+unreliableId);
            start();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    
    
    public void run() { //this functio run when the server recieve new data
        while (true) {
            try {
                String str = dis.readLine();
                ParseDataFromClients(str);
                //System.out.println(str);
               // sendMessageToAll(str);
            } catch (IOException ex) {
                clientsVector.remove(this);
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
                for(ChatHandler ua : unauthorizedClientsVector)
                {
                    if(ua.unreliableId == Integer.parseInt(msg.substring(2, 4)))
                    {
                        int userLength = Integer.parseInt(msg.substring(4, 5));
                        String userName = msg.substring(5, userLength+5);
                        int passwordLength = Integer.parseInt(msg.substring(5+userLength, 6+userLength));
                        String password = msg.substring( 6+userLength, passwordLength+6+userLength);
                        int  f = Server.dBase.checkLoginUser(userName, password);
                        System.out.println(f);
                        ua.ps.println("01"+f);
                    }
                }
                break;
            }
        }
    }

    void sendMessageToAll(String st) {
        for (ChatHandler ch : clientsVector) {
            try {
                ch.ps.println(st);
            } catch (Exception ex) {
                ex.printStackTrace();

            }
        }
    }
}
