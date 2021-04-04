package tictactoe;

import java.io.IOException;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class ConnectToServerBase extends BorderPane {

    protected final Pane pane;
    protected final Label label;
    protected final DropShadow dropShadow;
    protected final Button button;
    protected final DropShadow dropShadow0;
    protected final TextField textField;
    protected final Label label0;
    protected final Label label1;
    protected final TextField textField0;
    LoginRegPageBase loginRoot;
    IntroPageBase intoRoot;
    WaitingPageBase waitRoot;
    StartGameMenuBase startGameRoot;
    public ClientPlayer client ;
   

    public ConnectToServerBase(Scene scene) {

        pane = new Pane();
        label = new Label();
        dropShadow = new DropShadow();
        button = new Button();
        dropShadow0 = new DropShadow();
        textField = new TextField();
        label0 = new Label();
        label1 = new Label();
        textField0 = new TextField();
        Alert serverAlert = new Alert(Alert.AlertType.INFORMATION);

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
        label.setText("Tic Tack Toe");
        label.setTextFill(javafx.scene.paint.Color.WHITE);
        label.setFont(new Font("Jokerman", 57.0));

        dropShadow.setColor(javafx.scene.paint.Color.valueOf("#eb02b1"));
        dropShadow.setHeight(255.0);
        dropShadow.setRadius(127.0);
        dropShadow.setSpread(0.76);
        dropShadow.setWidth(255.0);
        label.setEffect(dropShadow);

        button.setLayoutX(275.0);
        button.setLayoutY(552.0);
        button.setMnemonicParsing(false);
        button.setPrefHeight(65.0);
        button.setPrefWidth(301.0);
        button.setStyle("-fx-background-color: Black; -fx-background-radius: 100; -fx-border-color: white; -fx-border-radius: 100; -fx-border-width: 2;");
        button.setText("Connect");
        button.setTextFill(javafx.scene.paint.Color.WHITE);
        button.setFont(new Font("Jokerman", 27.0));

        dropShadow0.setColor(javafx.scene.paint.Color.valueOf("#c321bd"));
        dropShadow0.setHeight(50.65);
        dropShadow0.setRadius(26.307499999999997);
        dropShadow0.setSpread(0.59);
        dropShadow0.setWidth(56.58);
        button.setEffect(null);
        button.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent ev)
            {
                button.setEffect(dropShadow0);            
            }
        });
        button.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent ev)
            {
                 button.setEffect(null);          
            }
        });
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent ev)
            {
               String ip = textField.getText();
               int  port = Integer.parseInt(textField0.getText());
               try{
                    createClient(ip, port); 
                    loginRoot = new LoginRegPageBase(client);
                    waitRoot = new WaitingPageBase(client);
                    startGameRoot = new StartGameMenuBase(client);
                    intoRoot = new IntroPageBase(client);
                    client.setClientRootsAndScene(scene, loginRoot , startGameRoot
                            ,intoRoot,waitRoot);
                    client.setGameRoot("login");
               }
               catch(IOException x)
               {
                   serverAlert.setTitle("Error");
                   serverAlert.setContentText("sorry , the Server is down..");
                   serverAlert.showAndWait();
               }
            }
        });

        textField.setLayoutX(350.0);
        textField.setLayoutY(311.0);
        textField.setPrefHeight(51.0);
        textField.setPrefWidth(302.0);
        textField.setPromptText("   Enter Server IP...");
        textField.setStyle("-fx-background-color: black; -fx-background-radius: 100; -fx-border-color: white; -fx-border-radius: 100; -fx-border-width: 2;");
        textField.setText("127.0.0.1");
        textField.setFont(new Font(31.0));

       
        textField.setEffect(null);
        textField.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent ev)
            {
                textField.setEffect(dropShadow0);            
            }
        });
        textField.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent ev)
            {
                 textField.setEffect(null);          
            }
        });
       

        label0.setLayoutX(181.0);
        label0.setLayoutY(302.0);
        label0.setPrefHeight(80.0);
        label0.setPrefWidth(156.0);
        label0.setText("Server IP:");
        label0.setTextFill(javafx.scene.paint.Color.WHITE);
        label0.setFont(new Font("Jokerman", 31.0));

        label1.setLayoutX(184.0);
        label1.setLayoutY(411.0);
        label1.setPrefHeight(71.0);
        label1.setPrefWidth(156.0);
        label1.setText("Port Num:");
        label1.setTextFill(javafx.scene.paint.Color.WHITE);
        label1.setFont(new Font("Jokerman", 29.0));

        textField0.setLayoutX(349.0);
        textField0.setLayoutY(413.0);
        textField0.setPrefHeight(51.0);
        textField0.setPrefWidth(302.0);
        textField0.setPromptText("  Enter Port Num...");
        textField0.setStyle("-fx-background-color: black; -fx-background-radius: 100; -fx-border-color: white; -fx-border-radius: 100; -fx-border-width: 2;");
        textField0.setText("49000");
        textField0.setFont(new Font(31.0));

       
        textField0.setEffect(null);
        textField0.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent ev)
            {
                textField0.setEffect(dropShadow0);            
            }
        });
        textField0.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent ev)
            {
                 textField0.setEffect(null);          
            }
        });
        setCenter(pane);

        pane.getChildren().add(label);
        pane.getChildren().add(button);
        pane.getChildren().add(textField);
        pane.getChildren().add(label0);
        pane.getChildren().add(label1);
        pane.getChildren().add(textField0);

    }
    
    void createClient(String ip,int port) throws IOException
    {
        
         client = new ClientPlayer(ip, port);
       
    }
}
