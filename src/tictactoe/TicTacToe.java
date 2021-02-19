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
    static Stage primaryStage;
    public ClientPlayer client = new ClientPlayer();
    LoginPageBase root;
    TicTacBase childGame=null;
    Scene scene ;
    @Override
    public void start(Stage primary) {
        primaryStage = primary;
        Pane o = new Pane();
        childGame = new TicTacBase(client); 
        scene = new Scene(o, 950, 530);
        root = new LoginPageBase(client,scene,childGame);
        scene.setRoot(root);
        
        //String css = this.getClass().getResource("mycss.css").toExternalForm();
       // scene.getStylesheets().add(css);
        root.getStyleClass().add("root");
        primaryStage.setTitle("TicTaacToe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
     
    
//     public static void switchScenes (int sceneNumber)
//    {
//        if(sceneNumber == 1)
//        {
//            LoginPageBase root = new LoginPageBase();
//            root.setClient(client);
//            Scene scene = new Scene(root, 800, 420);
//            primaryStage.setScene(scene);
//        }
//        else if(sceneNumber == 2)
//        {
//            TicTacBase root = new TicTacBase();
//            Scene scene = new Scene(root, 800, 420);
//            primaryStage.setScene(scene);
//        }
//    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
