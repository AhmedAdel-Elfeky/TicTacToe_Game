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
   
    volatile public boolean State=false;
    public volatile int trueId;
    public int falseId;
    public volatile boolean yourTurn;
    public volatile String symbol;
    
    
    //nihal
    //volatile String symbol;
    volatile String clientSymbol;
    
    public Scene gameScene;
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
    
    public void setGameRoot(String root)
    {
        switch(root)
        {
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
    
     public void setClientRootsAndScene(Scene s,LoginPageBase l ,TicTacBase g,StartGameMenuBase st)
    {
        this.gameScene = s;
        this.gameRoot = g;
        this.loginRoot = l;
        this.startGame = st;
    }
    
    public void sendDataToServer(String operationCode)
    {
        ps.println(operationCode);
    }

//    public String ReadPlayMoveFromserver() {
//        String msg = null;
//        try {
//            msg = dis.readLine();
//
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//        return msg;
//    }
    
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
        
        switch(f1)
        {
            case "00":
            {
                if("0".equals(msg.substring(2,3)))
                {
                    this.falseId = Integer.parseInt(msg.substring(3,4));
                    this.State= false;
                    System.out.println(falseId);
                    System.out.println(State);
                }
                else
                {
                    this.trueId = Integer.parseInt(msg.substring(3,4));
                    this.State = true;
                    System.out.println(this.trueId);
                    System.out.println( this.State);
                    this.setGameRoot("startGame");
                }
                System.out.println(msg);
                
                break;
            }
//            case "01":
//            {
//                  if(Integer.parseInt(msg.substring(2)) != -1)
//                  {
//
//                  }
//                  break;
//            }
            case "04":
            {
                  System.out.println(msgFromServer);
                  this.setGameRoot("game");
                  break;
            }
            case "0S":
                gridPaneButtons.getChildren().get(Character.getNumericValue(msg.charAt(3))).setDisable(true);
                Button temp = (Button) gridPaneButtons.getChildren().get(Character.getNumericValue(msg.charAt(3)));
                String s = Character.toString(msg.charAt(2));
//                Platform.runLater(new Runnable() {
//                    @Override
//                    public void run() {
                       temp.setText(s);
//                    }
//                });
                break;
            case "0M":
             //   Platform.runLater(new Runnable() {
            //        @Override
             //       public void run() {
                        //System.out.println(input);
                        chatArea.appendText(msg.substring(2) + "\n");
          //          }
         //       });
                break;
            case "0T" : //every game
                if (msg.charAt(2) == '0') {
                    symbol = "X";
                } else {
                    symbol = "O";
                }
                break;
            case "0N" ://one time
//                System.out.println(input.charAt(1));
                if (msg.charAt(2) == '0') {
                    clientSymbol = "X";
                } else {
                    clientSymbol = "O";
                }
                break;
            default:
            {
                
            }
        }
//        row = Integer.parseInt(f1);
//        System.out.println(row);
//        f1 = msg.substring(1);
//        col = Integer.parseInt(f1);
//        System.out.println(col);
//        this.gridButtons[row][col].setText("x");
//        this.gridButtons[row][col].setDisable(true);
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
    
    
/*
 public void run() 
    {
        while (true) {

            try {
                msgFromServer = dis.readLine();
                Thread.sleep(1000);
                root.gridPane.getChildren().forEach(item -> {
                    if (root.gridPane.getRowIndex(item) == Integer.parseInt(msgFromServer.charAt(0)+"") && root.gridPane.getColumnIndex(item) ==Integer.parseInt(msgFromServer.charAt(1)+"") )
                    {
                        root.board[Integer.parseInt(msgFromServer.charAt(0)+"")][Integer.parseInt(msgFromServer.charAt(1)+"")] = "X";
                        ((Button) item).setText("X");
                    }
                });
            } catch (Exception e) {
                System.out.println("Server is offline");
                System.exit(0);
                break;
            }

        }
    }

 */

 /*
public ClientPlayer(TicTacBase r) {
        root = r;
        try {
            mySocket = new Socket("127.0.0.1", 5005);
            dis = new DataInputStream(mySocket.getInputStream());
            ps = new PrintStream(mySocket.getOutputStream());

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        root.gridPane.getChildren().forEach(item -> {
            item.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) 
                {
                    ps.println(GridPane.getRowIndex(item) + "" + GridPane.getColumnIndex(item));
                    ((Button) item).setText("X");

                }
            });
        });
        Thread th1 = new Thread(this);
        th1.start();
    }
 */
