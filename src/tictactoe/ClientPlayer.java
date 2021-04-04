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
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

public class ClientPlayer {

    public Socket mySocket;
    public DataInputStream inputStream;
    public PrintStream outStream;
    public Thread th1;
    
//        static public String avatarsImgPaths[] = { "file:img/av1.jpg",
//                                                "file:img/av4.jpg",
//                                                "file:img/av3.jpg",
//                                                "file:img/av2.jpg",
//                                                "file:img/av5.png"};
//    
     static public String avatarsImgPaths[] = { "file:src/tictactoe/img/av1.jpg",
                                                "file:src/tictactoe/img/av4.jpg",
                                                "file:src/tictactoe/img/av3.jpg",
                                                "file:src/tictactoe/img/av2.jpg",
                                                "file:src/tictactoe/img/av5.png"};
    

    public volatile String msgFromServer = "";

    volatile public boolean State = false;
    public volatile int trueId;
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
    public PlayerProfileBase pProfileRoot;
    public Simulate_BordBase simRoot;
    public WinnerBase winRoot;
    public LossBase loseRoot;
    public DrawBase drawRoot ;
    

    public ClientPlayer(String ip,int port) throws IOException
    {
           mySocket = new Socket(ip, port);
          //  mySocket = new Socket("154.176.119", 49000,InetAddress.getLocalHost(),49000);
         //  mySocket = new Socket("41.37.232.20", 49000);
            inputStream = new DataInputStream(mySocket.getInputStream());
            outStream = new PrintStream(mySocket.getOutputStream());
            th1 = new ThreadOne();
            th1.start();
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
            case "win":
                this.gameScene.setRoot(winRoot);
                break;
            case "lose":
                this.gameScene.setRoot(loseRoot);
                break;
            case "draw":
                this.gameScene.setRoot(drawRoot);
                break;
            case "sim":
                this.gameScene.setRoot(simRoot);
                break;
            case "profile":
                this.gameScene.setRoot(pProfileRoot);
                break;
            
                
        }
    }
    
     public void setGameRoot(String root,int path1Index,int path2Index) 
     {
         switch (root)
         {
         case "game":
            gameRoot = new TicTacBase(this,avatarsImgPaths[path2Index],avatarsImgPaths[path1Index]);
            this.gameScene.setRoot(gameRoot);  
            break;
         case "profile":
            pProfileRoot = new PlayerProfileBase(this,avatarsImgPaths[path2Index]);
            this.gameScene.setRoot(pProfileRoot);  
            break;
         }
     }

    public void setClientRootsAndScene(Scene s, LoginRegPageBase l,  StartGameMenuBase st,IntroPageBase i,
            WaitingPageBase w) {
        this.gameScene = s;
        this.loginRoot = l;
        this.startGame = st;
        this.waitRoot = w;
        this.intoRoot=i;
        
    }

    public void sendDataToServer(String operationCode) {
        outStream.println(operationCode);
    }
    
    public void setProfileData(String msg) {
        String[] split = new String[2];
        StringTokenizer st0 = new StringTokenizer(msg, ".");
        for (int i = 0; st0.hasMoreTokens(); i++) {

            split[i] = st0.nextToken();
        }
        String[] tokens = new String[4];
        StringTokenizer st1 = new StringTokenizer(split[0], "_");
        for (int i = 0; st1.hasMoreTokens(); i++) {
            tokens[i] = st1.nextToken();
        }
        StringTokenizer st2 = new StringTokenizer(split[1], "_");
        for (;st2.hasMoreTokens();) {
            
            MenuItem mi = new MenuItem();
            mi.setMnemonicParsing(false);
            mi.setText(st2.nextToken());
            mi.setOnAction((e)-> {
                pProfileRoot.splitMenuButton.setText(mi.getText());
            });
            pProfileRoot.splitMenuButton.getItems().add(mi);
        }
       
        pProfileRoot.label6.setText(" " + tokens[0]);
        pProfileRoot.label8.setText(" " + tokens[1]);
        pProfileRoot.label9.setText(" " + tokens[3]);
        pProfileRoot.label10.setText(" " + tokens[2]);
        int n = Integer.parseInt(tokens[3]) + Integer.parseInt(tokens[2]) + Integer.parseInt(tokens[1]);
        double ratio = 0;
        if (n != 0) {
            ratio = ((double) Integer.parseInt(tokens[1]) / n);

        }
        pProfileRoot.label11.setText(" " + ratio + " ");
        pProfileRoot.label7.setText(" " + n + " ");
  
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
        switch (f1) {
            case "00": // login
            {
                switch(msg.substring(2, 3))
                {
                    case "0":
                    {
                        this.State = false;
                        break;
                    }
                    case "T":
                    {
                        loginRoot.loginAlert.setTitle("Error");
                        loginRoot.loginAlert.setContentText("wrong data re-enter or go to register tab..");
                        loginRoot.loginAlert.showAndWait();
                        break;
                    }
                    default:
                    {
                        String temp = msg.substring(4);
                        StringTokenizer st1 = new StringTokenizer(temp, "_"); 
                        this.trueId = Integer.parseInt(st1.nextToken()); 
                        this.playerName = st1.nextToken();
                        this.avatarId =  Integer.parseInt(st1.nextToken());
                        this.State = true;
                        this.setGameRoot("into");
                        break;
                    }
                }
                break;
            }
            case "rg": //answer from register
            {
                if(msg.charAt(3) == '1')
                {
                    String temp = msg.substring(5);
                    StringTokenizer p = new StringTokenizer(temp, "_"); 
                    this.trueId = Integer.parseInt(p.nextToken());
                    this.playerName = p.nextToken();
                    this.avatarId = Integer.parseInt(p.nextToken());
                    this.setGameRoot("into");
                    loginRoot.register_alert.setContentText("you registered successfully... ");
                }
                else
                {
                    loginRoot.register_alert.setContentText("you can't enter this user name.. ");                    
                }
                loginRoot.register_alert.showAndWait();
                break;
            }
            
            case "ZD":
                msg = msg.substring(2);
                setProfileData(msg);
                break;

            case "04": // go to game
            {
                if (msg.charAt(2) != 'I') //with another player
                {
                    aiGame=false;
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
                if(!(msg.length() == 3)  )
                {
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
                }
                if(msg.charAt(2) != '_')
                {
                    System.out.println(msg.charAt(2));
                    String resualt = Character.toString(msg.charAt(2));
                    gridPaneButtons.getChildren().forEach(item->item.setDisable(true));
                    if(clientSymbol.equals(resualt))
                    {
                        winRoot = new WinnerBase(this);
                        this.setGameRoot("win");
                    }
                    else if("d".equals(resualt))
                    {
                        drawRoot = new DrawBase(this);
                        this.setGameRoot("draw");
                    }
                    else 
                    {
                        loseRoot = new LossBase(this);
                        this.setGameRoot("lose");
                    }
                }
                break;
                
            case "0M":
                gameRoot.textArea.appendText(msg.substring(2) + "\n");
                break;
            case "sm":
                simRoot= new Simulate_BordBase(this);
                this.setGameRoot("sim");
                break;
            case "sy": 
            {                          
                char playerTurn = 'x';
                String msg1 = msg.substring(2);
                String[] position = msg1.split("_");
                Timer timer = new Timer();
                SimulateTask t = new SimulateTask(timer,position,playerTurn);
                timer.schedule(t, 2000,2000);
                break;
            }
        }
    }

    class SimulateTask extends TimerTask
    {
        public String pos[];
        private char playerTurn;
        private int posNum=0;
        private final int len ;
        Timer refTimer ;
        public SimulateTask(Timer t,String[] pos,char pTurn)
        {
            this.pos=pos;
            this.playerTurn=pTurn;
            len = pos.length;
            this.refTimer = t;
        }
        @Override
        public void run()
        {           
            Platform.runLater(new Runnable() {
            @Override
            public void run() {               
                String p = pos[posNum];
                if (p.equals("X") || p.equals("O")) {
                    playerTurn = p.charAt(0);
                    posNum++;
                } 
                else 
                {
                   simRoot.gridPane.getChildren().get(Character.getNumericValue(p.charAt(0))).setDisable(true);
                   javafx.scene.control.Button btn = (javafx.scene.control.Button)simRoot.gridPane
                           .getChildren().get(Character.getNumericValue(p.charAt(0)));
                   String str = Character.toString(playerTurn);
                   btn.setText(str);
                   if (playerTurn == 'X') {
                       playerTurn = 'O';
                   } else if (playerTurn == 'O') {
                       playerTurn = 'X';
                   }
                   posNum++;  
                   if(posNum == len)
                       refTimer.cancel();                  
                }
            }});
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
