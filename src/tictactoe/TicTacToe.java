/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;

/**
 *
 * @author eslam , ahmed
 */
public class TicTacToe extends Application {
    Pane root = new Pane();
    Scene scene= new Scene(root,900,700) ;
    ConnectToServerBase connectToServer =  new ConnectToServerBase(scene) ;
    @Override
    public void start(Stage primaryStage) {
        scene.setRoot(connectToServer);
        primaryStage.setTitle("TicTaacToe");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> {
            System.exit(0);
        });
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
