package tictactoe;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class StartGameMenuBase extends BorderPane {

    protected final Pane pane;
    protected final Button button;
    protected final Button button0;
    protected final Button button1;

    public StartGameMenuBase(ClientPlayer client) {

        pane = new Pane();
        button = new Button();
        button0 = new Button();
        button1 = new Button();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        BorderPane.setAlignment(pane, javafx.geometry.Pos.CENTER);
        pane.setPrefHeight(200.0);
        pane.setPrefWidth(200.0);

        button.setLayoutX(241.0);
        button.setLayoutY(108.0);
        button.setMnemonicParsing(false);
        button.setPrefHeight(38.0);
        button.setPrefWidth(65.0);
        button.setText("vs AI");
        button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    client.sendDataToServer("03"+"0");  //03 start new game
                }
            });
      

        button0.setLayoutX(240.0);
        button0.setLayoutY(181.0);
        button0.setMnemonicParsing(false);
        button0.setPrefHeight(38.0);
        button0.setPrefWidth(67.0);
        button0.setText("vs Player");
        button0.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                     client.sendDataToServer("03"+"1");  //03 start new game  1 for 2 player
//                     Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                     alert.setTitle("wating another player");  
//                     alert.setContentText("");
//                     alert.setHeaderText("");
//                     alert.setWidth(200);
//                     alert.setHeight(200);
//                  //   alert.
//                     Image image = new Image("file:./src/tictactoe/img/loading.jpg", 200, 200, false, true);
//                     ImageView imageView = new ImageView(image);
//                     alert.setGraphic(imageView);
//                     alert.showAndWait();
                }
            });

        button1.setLayoutX(362.0);
        button1.setLayoutY(274.0);
        button1.setMnemonicParsing(false);
        button1.setPrefHeight(31.0);
        button1.setPrefWidth(71.0);
        button1.setText("Sign Out");
        button1.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    client.setGameRoot("login");
                    client.sendDataToServer("SO");
                }
            });
        setCenter(pane);

        pane.getChildren().add(button);
        pane.getChildren().add(button0);
        pane.getChildren().add(button1);

    }
}
