/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author eslam
 */
public class TicTacToe extends Application {
    Stage primaryStage;
    public ClientPlayer client = new ClientPlayer();
    LoginPageBase loginRoot;
    TicTacBase boardGame = new TicTacBase(client);
    Scene scene;
    @Override
    public void start(Stage primary) {
        primaryStage = primary;
        loginRoot = new LoginPageBase(client);
        scene = new Scene(loginRoot, 950, 530);
        StartGameMenuBase startGameRoot = new StartGameMenuBase(client);
        client.setClientRootsAndScene(scene, loginRoot, boardGame , startGameRoot);
        
        loginRoot.getStyleClass().add("root");
        primaryStage.setTitle("TicTaacToe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
