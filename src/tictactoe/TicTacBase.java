package tictactoe;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.util.Pair;

public  class TicTacBase extends BorderPane {

    protected final MenuBar menuBar;
    protected final Menu menu;
    protected final MenuItem menuItem;
    protected final Menu menu0;
    protected final MenuItem menuItem0;
    protected final Menu menu1;
    protected final MenuItem menuItem1;
    protected volatile  GridPane gridPane;
    protected final ColumnConstraints columnConstraints;
    protected final ColumnConstraints columnConstraints0;
    protected final ColumnConstraints columnConstraints1;
    protected final RowConstraints rowConstraints;
    protected final RowConstraints rowConstraints0;
    protected final RowConstraints rowConstraints1;
    protected final Button button;
    protected final Button button0;
    protected final Button button1;
    protected final Button button2;
    protected final Button button3;
    protected final Button button4;
    protected final Button button5;
    protected final Button button6;
    protected final Button button7;
    volatile String [][] board = {{"","",""},
                        {"","",""} ,
                        {"","",""}};
    Game myMatch ;
    public boolean playerturn = true ;
    public String myarr  ="X";
    
    ClientPlayer c = null;
    
    public TicTacBase(ClientPlayer c) {

        menuBar = new MenuBar();
        menu = new Menu();
        menuItem = new MenuItem();
        menu0 = new Menu();
        menuItem0 = new MenuItem();
        menu1 = new Menu();
        menuItem1 = new MenuItem();
        gridPane = new GridPane();
        columnConstraints = new ColumnConstraints();
        columnConstraints0 = new ColumnConstraints();
        columnConstraints1 = new ColumnConstraints();
        rowConstraints = new RowConstraints();
        rowConstraints0 = new RowConstraints();
        rowConstraints1 = new RowConstraints();
        button = new Button();
        button0 = new Button();
        button1 = new Button();
        button2 = new Button();
        button3 = new Button();
        button4 = new Button();
        button5 = new Button();
        button6 = new Button();
        button7 = new Button();
        
        Button[][] ButtonsArray = {{button,button0,button1},
                                  {button2,button3,button4},
                                  {button5,button6,button7}};
        //c = new ClientPlayer();
        c.setButtonData(ButtonsArray);
     
        
        myMatch= new Game();
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        BorderPane.setAlignment(menuBar, javafx.geometry.Pos.CENTER);

        menu.setMnemonicParsing(false);
        menu.setText("File");

        menuItem.setMnemonicParsing(false);
        menuItem.setText("Close");

        menu0.setMnemonicParsing(false);
        menu0.setText("Edit");

        menuItem0.setMnemonicParsing(false);
        menuItem0.setText("Delete");

        menu1.setMnemonicParsing(false);
        menu1.setText("Help");

        menuItem1.setMnemonicParsing(false);
        menuItem1.setText("About");
        setTop(menuBar);

        BorderPane.setAlignment(gridPane, javafx.geometry.Pos.CENTER);

        columnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints.setMinWidth(10.0);
        columnConstraints.setPrefWidth(100.0);

        columnConstraints0.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints0.setMinWidth(10.0);
        columnConstraints0.setPrefWidth(100.0);

        columnConstraints1.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints1.setMinWidth(10.0);
        columnConstraints1.setPrefWidth(100.0);

        rowConstraints.setMinHeight(10.0);
        rowConstraints.setPrefHeight(30.0);
        rowConstraints.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints0.setMinHeight(10.0);
        rowConstraints0.setPrefHeight(30.0);
        rowConstraints0.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints1.setMinHeight(10.0);
        rowConstraints1.setPrefHeight(30.0);
        rowConstraints1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
        button.getStyleClass().add("button");
        button0.getStyleClass().add("button");
        button1.getStyleClass().add("button");
        button2.getStyleClass().add("button");
        button3.getStyleClass().add("button");
        button4.getStyleClass().add("button");
        button5.getStyleClass().add("button");
        button6.getStyleClass().add("button");
        button7.getStyleClass().add("button");
        
        button.setTextFill(javafx.scene.paint.Color.WHITE);
        button0.setTextFill(javafx.scene.paint.Color.WHITE);
        button1.setTextFill(javafx.scene.paint.Color.WHITE);
        button2.setTextFill(javafx.scene.paint.Color.WHITE);
        button3.setTextFill(javafx.scene.paint.Color.WHITE);
        button4.setTextFill(javafx.scene.paint.Color.WHITE);
        button5.setTextFill(javafx.scene.paint.Color.WHITE);
        button6.setTextFill(javafx.scene.paint.Color.WHITE);
        button7.setTextFill(javafx.scene.paint.Color.WHITE);

        GridPane.setRowIndex(button, 0);
        GridPane.setColumnIndex(button, 0);
        button.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        button.setEllipsisString("");
        button.setMaxHeight(Double.MAX_VALUE);
        button.setMaxWidth(Double.MAX_VALUE);
        button.setMnemonicParsing(false);
        button.setPrefHeight(126.0);
        button.setPrefWidth(302.0);
        button.setFont(new Font("Jokerman", 52.0));
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ev)
            {
               c.sendPlayMoveToserver("00");
            }
        });


        GridPane.setColumnIndex(button0, 1);
        GridPane.setRowIndex(button0, 0);
        button0.setMaxHeight(Double.MAX_VALUE);
        button0.setMaxWidth(Double.MAX_VALUE);
        button0.setMnemonicParsing(false);
        button0.setPrefHeight(236.0);
        button0.setPrefWidth(200.0);
        button0.setFont(new Font("Jokerman", 52.0));
        button0.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ev)
            {
              
               c.sendPlayMoveToserver("01");
               
            }
        });


        GridPane.setColumnIndex(button1, 2);
        GridPane.setRowIndex(button1, 0);
        button1.setMaxHeight(Double.MAX_VALUE);
        button1.setMaxWidth(Double.MAX_VALUE);
        button1.setMnemonicParsing(false);
        button1.setPrefHeight(146.0);
        button1.setPrefWidth(401.0);
        button1.setFont(new Font("Jokerman", 52.0));
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ev)
            {
              
               c.sendPlayMoveToserver("02");
               
            }
        });


        GridPane.setColumnIndex(button2, 0);
        GridPane.setRowIndex(button2, 1);
        button2.setMaxHeight(Double.MAX_VALUE);
        button2.setMaxWidth(Double.MAX_VALUE);
        button2.setMnemonicParsing(false);
        button2.setPrefHeight(145.0);
        button2.setPrefWidth(404.0);
        button2.setFont(new Font("Jokerman", 52.0));
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ev)
            {
              
               c.sendPlayMoveToserver("10");
               
            }
        });


        GridPane.setColumnIndex(button3, 1);
        GridPane.setRowIndex(button3, 1);
        button3.setMaxHeight(Double.MAX_VALUE);
        button3.setMaxWidth(Double.MAX_VALUE);
        button3.setMnemonicParsing(false);
        button3.setPrefHeight(322.0);
        button3.setPrefWidth(200.0);
        button3.setFont(new Font("Jokerman", 52.0));
        button3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ev)
            {
              
               c.sendPlayMoveToserver("11");
               
            }
        });

        GridPane.setColumnIndex(button4, 2);
        GridPane.setRowIndex(button4, 1);
        button4.setMaxHeight(Double.MAX_VALUE);
        button4.setMaxWidth(Double.MAX_VALUE);
        button4.setMnemonicParsing(false);
        button4.setPrefHeight(142.0);
        button4.setPrefWidth(235.0);
        button4.setFont(new Font("Jokerman", 51.0));
        button4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ev)
            {
               
               c.sendPlayMoveToserver("12");
               
            }
        });

        GridPane.setColumnIndex(button5, 0);
        GridPane.setRowIndex(button5, 2);
        button5.setMaxHeight(Double.MAX_VALUE);
        button5.setMaxWidth(Double.MAX_VALUE);
        button5.setMnemonicParsing(false);
        button5.setPrefHeight(200.0);
        button5.setPrefWidth(458.0);
        button5.setFont(new Font("Jokerman", 52.0));
        button5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ev)
            {
              c.sendPlayMoveToserver("20");
               
            }
        });

        GridPane.setColumnIndex(button6, 1);
        GridPane.setRowIndex(button6, 2);
        button6.setMaxHeight(Double.MAX_VALUE);
        button6.setMaxWidth(Double.MAX_VALUE);
        button6.setMnemonicParsing(false);
        button6.setPrefHeight(175.0);
        button6.setPrefWidth(200.0);
        button6.setFont(new Font("Jokerman", 52.0));
        button6.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ev)
            {
                c.sendPlayMoveToserver("21");
               
            }
        });

        GridPane.setColumnIndex(button7, 2);
        GridPane.setRowIndex(button7, 2);
        button7.setMaxHeight(Double.MAX_VALUE);
        button7.setMaxWidth(Double.MAX_VALUE);
        button7.setMnemonicParsing(false);
        button7.setPrefHeight(148.0);
        button7.setPrefWidth(266.0);
        button7.setFont(new Font("Jokerman", 52.0));
        button7.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ev)
            {
               c.sendPlayMoveToserver("22");  
            }
        }); 

        setCenter(gridPane);
        menu.getItems().add(menuItem);
        menuBar.getMenus().add(menu);
        menu0.getItems().add(menuItem0);
        menuBar.getMenus().add(menu0);
        menu1.getItems().add(menuItem1);
        menuBar.getMenus().add(menu1);
        gridPane.getColumnConstraints().add(columnConstraints);
        gridPane.getColumnConstraints().add(columnConstraints0);
        gridPane.getColumnConstraints().add(columnConstraints1);
        gridPane.getRowConstraints().add(rowConstraints);
        gridPane.getRowConstraints().add(rowConstraints0);
        gridPane.getRowConstraints().add(rowConstraints1);
        gridPane.getChildren().add(button);
        gridPane.getChildren().add(button0);
        gridPane.getChildren().add(button1);
        gridPane.getChildren().add(button2);
        gridPane.getChildren().add(button3);
        gridPane.getChildren().add(button4);
        gridPane.getChildren().add(button5);
        gridPane.getChildren().add(button6);
        gridPane.getChildren().add(button7);
       
    }
    public void doAiRandomMove()
    {
        Pair<Integer,Integer > x = myMatch.getRandomMove(board);
        gridPane.getChildren().forEach(item->{
        if(GridPane.getRowIndex(item)==x.getKey()&&GridPane.getColumnIndex(item)==x.getValue())
        {
            playerturn =true ;
            board[GridPane.getRowIndex(item)][GridPane.getColumnIndex(item)]="O";
            item.setDisable(true);
            ((Button)item).setText("O"); ;
            
        }
        });

        
    }
    public int checkForWinner()
    {
            String stat = myMatch.checkWinner(board);
            int stop_continue = 0 ;
            if (stat=="X"||stat=="O"){
                System.out.println("Player "+stat+" Wins");
                gridPane.getChildren().forEach(item->{
                item.setOnMouseClicked(null);
                ((Button)item).setDisable(true);
            });    
            
            stop_continue =1;
            }
            
            else if (stat=="Draw")
            {
                System.out.println(stat);
                stop_continue=1;
            }
        return stop_continue; 
   }
}
