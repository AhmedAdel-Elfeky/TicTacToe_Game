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
import java.util.StringTokenizer;
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
    
    static public String avatarsImgPaths[] = { "file:src/tictactoe/img/av1.jpg",
                                                "file:src/tictactoe/img/av2.jpg",
                                                "file:src/tictactoe/img/av3.jpg",
                                                "file:src/tictactoe/img/av4.jpg",
                                                "file:src/tictactoe/img/av5.png"};
    

    public volatile String msgFromServer = "";

    volatile public boolean State = false;
    public volatile int trueId;
    public int falseId;
    public String playerName="";
    public int avatarId;
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
    public WaitingPageBase waitRoot;
    
    public Label warningMessage;

    public ClientPlayer() {

        try {
            //mySocket = new Socket("127.0.0.1", 5005);
            mySocket = new Socket("41.37.232.20", 49000,InetAddress.getLocalHost(),49000);
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
            case "wait":
                this.gameScene.setRoot(waitRoot);
                break;
                
        }
    }
    
     public void setGameRoot(String root,int path1Index,int path2Index) 
     {       
        gameRoot = new TicTacBase(this,avatarsImgPaths[path2Index],avatarsImgPaths[path1Index]);
        this.gameScene.setRoot(gameRoot);    
     }

    public void setClientRootsAndScene(Scene s, LoginRegPageBase l,  StartGameMenuBase st,IntroPageBase i,
            WaitingPageBase w) {
        this.gameScene = s;
       // this.gameRoot = g;
        this.loginRoot = l;
        this.startGame = st;
        this.waitRoot = w;
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
                    String temp = msg.substring(4);
                    StringTokenizer st1 = new StringTokenizer(temp, "_"); 
                    this.trueId = Integer.parseInt(st1.nextToken()); 
                    this.playerName = st1.nextToken();
                    this.avatarId =  Integer.parseInt(st1.nextToken());
                    this.State = true;
                    warningMessage.setVisible(false);                    
                    System.out.println(this.trueId);
                    System.out.println(this.playerName);
                    System.out.println(this.State);
                    this.setGameRoot("into");
                }

                break;
            }

            case "04": // go to game
            {
                System.out.println(msg);
                if (msg.charAt(2) != 'I') 
                {
                    String temp = msg.substring(3);
                    StringTokenizer st1 = new StringTokenizer(temp, "_"); 
                    String opponentName = st1.nextToken();
                    int oppenentAvatar = Integer.parseInt(st1.nextToken());
                    if (msg.charAt(2) == '0') {
                        this.setGameRoot("game",avatarId,oppenentAvatar);
                        clientSymbol = "X";
                        gameRoot.label3.setText(playerName);
                        gameRoot.label5.setText(opponentName);                    
                        turn=true;
                    } else {
                        this.setGameRoot("game",oppenentAvatar,avatarId);
                        clientSymbol = "O";
                        gameRoot.label3.setText(opponentName);
                        gameRoot.label5.setText(playerName);                   
                        turn=false;
                    }
                }
                
                else { // go to ai
                    aiGame=true;
                    if (msg.charAt(3) == '0') {
                         this.setGameRoot("game",avatarId,4);
                        clientSymbol = "X";
                        turn=true;
                        gameRoot.label3.setText(playerName);
                        gameRoot.label5.setText("AI");
                    } 
                    else {
                        this.setGameRoot("game",5,avatarId);
                        clientSymbol = "O";
                        turn=false;
                        gameRoot.label3.setText("AI");
                        gameRoot.label5.setText(playerName);
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
                gameRoot.textArea.appendText(msg.substring(2) + "\n");
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
