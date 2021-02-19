package tictactoe;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.*;

public class Server {

    ServerSocket serverSocket;

    public static void main(String[] args) {
        new Server();
    }

    public Server() {
        try {
            serverSocket = new ServerSocket(5005);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        while (true) {
            try {
                Socket s = serverSocket.accept();
                new ChatHandler(s);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}

class ChatHandler extends Thread {

    DataInputStream dis;
    PrintStream ps;

    static Vector<ChatHandler> clientsVector = new Vector<ChatHandler>();

    public ChatHandler(Socket cs) {
        try {
            dis = new DataInputStream(cs.getInputStream());
            ps = new PrintStream(cs.getOutputStream());
            clientsVector.add(this);
            start();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            try {
                String str = dis.readLine();
                System.out.println(str);
                sendMessageToAll(str);
            } catch (IOException ex) {
                clientsVector.remove(this);
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
