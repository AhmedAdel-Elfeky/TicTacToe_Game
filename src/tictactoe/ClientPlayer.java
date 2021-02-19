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
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class ClientPlayer {

    public Socket mySocket;
    public DataInputStream dis;
    public PrintStream ps;
    public Thread th1;
    public String msgFromServer = "";
    public Button[][] gridButtons;
   

    public ClientPlayer() {
     
        try {
            mySocket = new Socket("127.0.0.1", 5005);
            dis = new DataInputStream(mySocket.getInputStream());
            ps = new PrintStream(mySocket.getOutputStream());
            th1 = new ThreadOne();
            th1.start();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
       
    }
    
     public void sendPlayMoveToserver(String rowcol) 
     {
        ps.println(rowcol);
     }
     
     public String ReadPlayMoveFromserver() 
     {
         String msg=null;
         try
         {
            msg = dis.readLine();
            
         }
         catch ( IOException ex)
	{
            ex.printStackTrace();
	}      
         return msg;
     }
     
     public void setButtonData(Button[][] gridB)
     {
         this.gridButtons = gridB;
     }
     
     public void DrawRecievedData(String msg)
     {
         int row ,col;
         String f1 = msg.substring(0,1);
         row = Integer.parseInt(f1);
         System.out.println(row);
         f1 = msg.substring(1);
         col = Integer.parseInt(f1);
         System.out.println(col);
         this.gridButtons[row][col].setText("x");
         this.gridButtons[row][col].setDisable(true);
     }

    class ThreadOne extends Thread
	{
            public void run()
            {
                while(true)
                {
                    try
                    {
                        msgFromServer = ReadPlayMoveFromserver();
                        Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                             DrawRecievedData(msgFromServer);    
                        }
                         });
                        
                    }
                    catch ( Exception ex)
                    {
                        System.out.println("Server is offline");
                        ex.printStackTrace();
                    }				
//                    try
//                    {
//                            Thread.sleep(30);
//                    }
//                    catch (Exception e)
//                    {
//                            e.printStackTrace();
//                    }
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
