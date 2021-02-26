/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

/**
 *
 * @author Islam
 */
import java.net.*;
import java.io.*;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class ClientPlayer {

    public Socket mySocket;
    public DataInputStream dis;
    public PrintStream ps;
    public Thread th1;

    public volatile String msgFromServer = "";

    volatile public boolean State = false;
    public volatile int trueId;
    public int falseId;
    public volatile boolean yourTurn;
    public volatile String symbol;
    public volatile String clientSymbol;
    public volatile boolean aiGame=false;
    public volatile Scene gameScene;
    public TicTacBase gameRoot;
    public LoginPageBase loginRoot;
    public Button[][] gridButtons;
    public TextArea chatArea;
    public GridPane gridPaneButtons;
    public StartGameMenuBase startGame;

    public ClientPlayer() {

        try {
            mySocket = new Socket("127.0.0.1", 5005);
            dis = new DataInputStream(mySocket.getInputStream());
            ps = new PrintStream(mySocket.getOutputStream());
            th1 = new ThreadOne();
            th1.start();

        } catch (IOException ex) {
            System.out.println("The Serve is Down, you can't open the game");
            ex.printStackTrace();
            System.exit(0);
        }

    }

    public void sendPlayMoveToserver(String rowcol) {
        ps.println(rowcol);
    }

    public void setGameRoot(String root) {
        switch (root) {
            case "login":
                this.gameScene.setRoot(loginRoot);
                break;
            case "startGame":
                this.gameScene.setRoot(startGame);
                break;
            case "game":
                this.gameScene.setRoot(gameRoot);
                break;
        }
    }

    public void setClientRootsAndScene(Scene s, LoginPageBase l, TicTacBase g, StartGameMenuBase st) {
        this.gameScene = s;
        this.gameRoot = g;
        this.loginRoot = l;
        this.startGame = st;
    }

    public void sendDataToServer(String operationCode) {
        ps.println(operationCode);
    }

    public String ReadDataFromServer() {
        String msg = null;
        try {
            msg = dis.readLine();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return msg;
    }

    public void setButtonData(Button[][] gridB) {
        this.gridButtons = gridB;
    }

    public void DrawRecievedData(String msg) {
        int row, col;
        String f1 = msg.substring(0, 2);

        switch (f1) {
            case "00": // login
            {
                if ("0".equals(msg.substring(2, 3))) {
                    this.falseId = Integer.parseInt(msg.substring(3, 4));
                    this.State = false;
                    System.out.println(falseId);
                    System.out.println(State);
                } else {
                    this.trueId = Integer.parseInt(msg.substring(3, 4));
                    this.State = true;
                    System.out.println(this.trueId);
                    System.out.println(this.State);
                    this.setGameRoot("startGame");
                }
                System.out.println(msg);

                break;
            }

            case "04": // go to game
            {
                this.setGameRoot("game");
                if (msg.charAt(2) == '0') {
                    clientSymbol = "X";
                    yourTurn=true;
                } else {
                    clientSymbol = "O";
                    yourTurn=false;
                }
                if (msg.charAt(3) == 'I') {
                aiGame=true;
                }
                break;
            }
            
            case "0S":
                gridPaneButtons.getChildren().get(Character.getNumericValue(msg.charAt(3))).setDisable(true);
                Button temp = (Button) gridPaneButtons.getChildren().get(Character.getNumericValue(msg.charAt(3)));
                String s = Character.toString(msg.charAt(2));
                temp.setText(s);
                if(!s.equals(clientSymbol))
                    yourTurn=true;
                break;
            case "0M":
                chatArea.appendText(msg.substring(2) + "\n");
                break;
 
            case "sd":
                String resualt = Character.toString(msg.charAt(2));
                gridPaneButtons.getChildren().forEach(item->item.setDisable(true));
                if(clientSymbol.equals(resualt))
                {
                   //i win 
                }
                else if("d".equals(resualt))
                {
                //draw
                }
                else 
                {
                    // i lose 
                }
                break;
            default: {

            }
        }
    }

    class ThreadOne extends Thread {

        public void run() {
            while (true) {
                try {
                    msgFromServer = ReadDataFromServer();
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            DrawRecievedData(msgFromServer);
                        }
                    });

                } catch (Exception ex) {
                    System.out.println("Server is offline");
                    ex.printStackTrace();
                }
            }
        }
    }
}
