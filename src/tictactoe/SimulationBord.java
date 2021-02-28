/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

/**
 *
 * @author Nihal
 */

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Eng.Aya
 */
public class SimulationBord extends Application {

    Simulate_BordBase sim = new Simulate_BordBase(new ClientPlayer());

    @Override
    public void start(Stage primaryStage) {

        //  StackPane root = new StackPane();
        Scene scene = new Scene(sim);

        primaryStage.setTitle("TicTaacToe");
        primaryStage.setResizable(false);
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
