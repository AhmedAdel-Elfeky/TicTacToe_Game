
package tictactoe;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import static javafx.scene.layout.Region.USE_PREF_SIZE;
import javafx.scene.text.Font;

public class StartGameMenuBase extends BorderPane {

    protected final Pane pane;
    protected final Label label;
    protected final DropShadow dropShadow;
    protected final Button button;
    protected final DropShadow dropShadow0;
    protected final Button button0;
    protected final DropShadow dropShadow1;
    protected final Button button1;
    protected final DropShadow dropShadow2;

    public StartGameMenuBase(ClientPlayer client) {

        pane = new Pane();
        label = new Label();
        dropShadow = new DropShadow();
        button = new Button();
        dropShadow0 = new DropShadow();
        button0 = new Button();
        dropShadow1 = new DropShadow();
        button1 = new Button();
        dropShadow2 = new DropShadow();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(700.0);
        setPrefWidth(900.0);

        BorderPane.setAlignment(pane, javafx.geometry.Pos.CENTER);
        pane.setPrefHeight(700.0);
        pane.setPrefWidth(900.0);
        pane.setStyle("-fx-background-color: black;");

        label.setAlignment(javafx.geometry.Pos.CENTER);
        label.setLayoutX(167.0);
        label.setLayoutY(18.0);
        label.setPrefHeight(223.0);
        label.setPrefWidth(504.0);
        label.setStyle("-fx-background-color: black; -fx-region-background: white;");
        label.setText("Tic Tac Toe");
        label.setTextFill(javafx.scene.paint.Color.WHITE);
        label.setFont(new Font("Jokerman", 57.0));

        dropShadow.setColor(javafx.scene.paint.Color.valueOf("#eb02b1"));
        dropShadow.setHeight(255.0);
        dropShadow.setRadius(127.0);
        dropShadow.setSpread(0.76);
        dropShadow.setWidth(255.0);
        label.setEffect(dropShadow);
        
        dropShadow0.setColor(javafx.scene.paint.Color.valueOf("#c321bd"));
        dropShadow0.setHeight(50.65);
        dropShadow0.setRadius(26.307499999999997);
        dropShadow0.setSpread(0.59);
        dropShadow0.setWidth(56.58);

        button.setLayoutX(281.0);
        button.setLayoutY(266.0);
        button.setMnemonicParsing(false);
        button.setPrefHeight(65.0);
        button.setPrefWidth(301.0);
        button.setStyle("-fx-background-color: Black; -fx-background-radius: 100; -fx-border-color: white; -fx-border-radius: 100; -fx-border-width: 2;");
        button.setText("VS AI");
        button.setTextFill(javafx.scene.paint.Color.WHITE);
        button.setFont(new Font("Jokerman", 27.0));
        button.setEffect(null);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                client.sendDataToServer("03" + "0"); 
            }
        });
        button.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent ev)
            {
                 button.setEffect(null);          
            }
        });
        button.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent ev)
            {
                button.setEffect(dropShadow0);            
            }
        });
        

        button0.setLayoutX(281.0);
        button0.setLayoutY(582.0);
        button0.setMnemonicParsing(false);
        button0.setPrefHeight(65.0);
        button0.setPrefWidth(301.0);
        button0.setStyle("-fx-background-color: Black; -fx-background-radius: 100; -fx-border-color: white; -fx-border-radius: 100; -fx-border-width: 2;");
        button0.setText("Back");
        button0.setTextFill(javafx.scene.paint.Color.WHITE);
        button0.setFont(new Font("Jokerman", 27.0));
        button0.setEffect(null);   
        button0.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                client.setGameRoot("into");  
            }
        });
        button0.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent ev)
            {
                 button0.setEffect(null);          
            }
        });
        button0.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent ev)
            {
                button0.setEffect(dropShadow0);            
            }
        });
        
     
        button1.setLayoutX(281.0);
        button1.setLayoutY(423.0);
        button1.setMnemonicParsing(false);
        button1.setPrefHeight(65.0);
        button1.setPrefWidth(301.0);
        button1.setStyle("-fx-background-color: Black; -fx-background-radius: 100; -fx-border-color: white; -fx-border-radius: 100; -fx-border-width: 2;");
        button1.setText("VS player");
        button1.setTextFill(javafx.scene.paint.Color.WHITE);
        button1.setFont(new Font("Jokerman", 27.0));
        button1.setEffect(null);   
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                client.setGameRoot("wait");
                client.sendDataToServer("03" + "1"); 
            }
        });
        button1.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent ev)
            {
                 button1.setEffect(null);          
            }
        });
        button1.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent ev)
            {
                button1.setEffect(dropShadow0);            
            }
        });
       
        setCenter(pane);

        pane.getChildren().add(label);
        pane.getChildren().add(button);
        pane.getChildren().add(button0);
        pane.getChildren().add(button1);

    }
}

