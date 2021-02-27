package tictactoe;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class WaitingPageBase extends Pane {

    protected final Label label;
    protected final DropShadow dropShadow;
    protected final Button button;
    protected final DropShadow dropShadow0;
    protected ImageView imageView=null;
    protected Image image = null;

    public WaitingPageBase(ClientPlayer client) {

        label = new Label();
        dropShadow = new DropShadow();
        button = new Button();
        dropShadow0 = new DropShadow();
        imageView = new ImageView();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(700.0);
        setPrefWidth(900.0);
        setStyle("-fx-background-color: black;");

        label.setLayoutX(189.0);
        label.setLayoutY(64.0);
        label.setPrefHeight(101.0);
        label.setPrefWidth(519.0);
        label.setStyle("-fx-background-color: black; -fx-region-background: black;");
        label.setText("Waiting for another player...");
        label.setTextFill(javafx.scene.paint.Color.valueOf("#fff9f9"));
        label.setFont(new Font("Jokerman", 35.0));

        dropShadow.setColor(javafx.scene.paint.Color.valueOf("#b8005f"));
        dropShadow.setHeight(199.65);
        dropShadow.setRadius(60.595);
        dropShadow.setSpread(0.67);
        dropShadow.setWidth(44.73);
        label.setEffect(dropShadow);

        button.setLayoutX(344.0);
        button.setLayoutY(522.0);
        button.setMnemonicParsing(false);
        button.setPrefHeight(94.0);
        button.setPrefWidth(226.0);
        button.setStyle("-fx-background-color: black; -fx-border-color: white; -fx-border-radius: 100; -fx-background-radius: 100;");
        button.setText("Cancel");
        button.setTextFill(javafx.scene.paint.Color.WHITE);
        button.setFont(new Font("Jokerman", 46.0));

        dropShadow0.setColor(javafx.scene.paint.Color.valueOf("#ff0099"));
        dropShadow0.setHeight(56.58);
        dropShadow0.setRadius(27.049999999999997);
        dropShadow0.setSpread(0.38);
        dropShadow0.setWidth(53.62);
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
              client.setGameRoot("startGame");
              client.sendDataToServer("CW");//cancel waiting
            }
        });
        
         
        try {
            image = new Image("file:src/tictactoe/img/waiting.gif",305, 537, true, true);
            imageView = new ImageView(image);
        } catch (Exception p) {
            p.printStackTrace();
        }


        imageView.setFitHeight(305.0);
        imageView.setFitWidth(537.0);
        imageView.setLayoutX(244.0);
        imageView.setLayoutY(187.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);

        this.getChildren().add(label);
        this.getChildren().add(button);
        this.getChildren().add(imageView);

    }
}
