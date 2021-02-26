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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class ClientPlayer {

    public Socket mySocket;
    public DataInputStream inputStream;
    public PrintStream outStream;
    public Thread th1;

    public volatile String msgFromServer = "";

    volatile public boolean State = false;
    public volatile int trueId;
    public int falseId;
    public volatile boolean turn;
    public volatile String symbol;
    public volatile String clientSymbol;
    public volatile boolean aiGame=false;
    public volatile Scene gameScene;
    public TicTacBase gameRoot;
    public LoginRegPageBase loginRoot;
    public Button[][] gridButtons;
    public TextArea chatArea;
    public GridPane gridPaneButtons;
    public StartGameMenuBase startGame;
    public IntroPageBase intoRoot;
    
    public Label warningMessage;

    public ClientPlayer() {

        try {
            mySocket = new Socket("127.0.0.1", 5005);
            inputStream = new DataInputStream(mySocket.getInputStream());
            outStream = new PrintStream(mySocket.getOutputStream());
            th1 = new ThreadOne();
            th1.start();

        } catch (IOException ex) {
            System.out.println("The Serve is Down, you can't open the game");
            ex.printStackTrace();
            System.exit(0);
        }

    }

    public void sendPlayMoveToserver(String rowcol) {
        outStream.println(rowcol);
    }

    public void setGameRoot(String root) {
        switch (root) {
            case "login":
                this.gameScene.setRoot(loginRoot);
                break;
            case "into":
                this.gameScene.setRoot(intoRoot);
                break;
            case "startGame":
                this.gameScene.setRoot(startGame);
                break;
            case "game":
                this.gameScene.setRoot(gameRoot);
                break;
                
        }
    }

    public void setClientRootsAndScene(Scene s, LoginRegPageBase l, TicTacBase g, StartGameMenuBase st,IntroPageBase i) {
        this.gameScene = s;
        this.gameRoot = g;
        this.loginRoot = l;
        this.startGame = st;
        this.intoRoot=i;
    }

    public void sendDataToServer(String operationCode) {
        outStream.println(operationCode);
    }

    public String ReadDataFromServer() {
        String msg = null;
        try {
            msg = inputStream.readLine();

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
         System.out.println(msg);
        switch (f1) {
            case "00": // login
            {
                if ("0".equals(msg.substring(2, 3))) {
                    this.falseId = Integer.parseInt(msg.substring(3, 4));
                    this.State = false;
                    System.out.println(falseId);
                    System.out.println(State);
                }
                else if("T".equals(msg.substring(2, 3)))
                {
                    warningMessage.setText("Wrong Data");
                    warningMessage.setVisible(true);
                }
                else {
                    this.trueId = Integer.parseInt(msg.substring(3, 4));
                    this.State = true;
                    warningMessage.setVisible(false);
                    System.out.println(this.trueId);
                    System.out.println(this.State);
                    this.setGameRoot("into");
                }
                System.out.println(msg);

                break;
            }

            case "04": // go to game
            {
                System.out.println(msg);
                this.setGameRoot("game");
                if (msg.charAt(2) == '0') {
                    clientSymbol = "X";
                    turn=true;
                } else {
                    clientSymbol = "O";
                    turn=false;
                }
                if (msg.charAt(2) == 'I') {
                    aiGame=true;
                    if (msg.charAt(3) == '0') {
                    clientSymbol = "X";
                    turn=true;
                    } else {
                        clientSymbol = "O";
                        turn=false;
                    }
                        System.out.println(this.aiGame);
                    }
                break;
            }
            
            case "0S":
                System.out.println("msg from server"+msg);
                int cntr=0;
                int x_o_pos=3;
                int bu_pos=4;
                if(msg.length()==7) // playing with AI
                    cntr = 2;
                else
                    cntr=1;
                
                for(int i =0;i<cntr;i++)
                {
                    gridPaneButtons.getChildren().get(Character.getNumericValue(msg.charAt(bu_pos))).setDisable(true);
                    Button temp = (Button) gridPaneButtons.getChildren().get(Character.getNumericValue(msg.charAt(bu_pos)));
                    String s = Character.toString(msg.charAt(x_o_pos));
                    temp.setText(s);
                    x_o_pos+=2;
                    bu_pos+=2;
                    if(!s.equals(clientSymbol))
                        turn=true;
                }
                
                if(msg.charAt(2) != '_')
                {
                    String resualt = Character.toString(msg.charAt(2));
                    gridPaneButtons.getChildren().forEach(item->item.setDisable(true));
                    System.out.println(resualt);
                    if(clientSymbol.equals(resualt))
                    {
                       //i win 
                        System.out.println("you win");
                    }
                    else if("d".equals(resualt))
                    {
                         //draw
                        System.out.println("you draw");
                    }
                    else 
                    {
                        // i lose 
                        System.out.println("you lose");
                    }
                }
                break;
                
            case "0M":
                chatArea.appendText(msg.substring(2) + "\n");
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
