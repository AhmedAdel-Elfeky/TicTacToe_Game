package tictactoe;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class PlayerProfileBase extends BorderPane {

    protected final Pane pane;
    protected final Label label;
    protected final Label label0;
    protected final DropShadow dropShadow;
    protected final Label label1;
    protected final Label label2;
    protected final Label label3;
    protected final Button button;
    protected final DropShadow dropShadow0;
    protected final Label label4;
    protected final Label label5;
    protected  ImageView imageView;
    protected final Label label6;
    protected final Label label7;
    protected final Label label8;
    protected final Label label9;
    protected final Label label10;
    protected final Label label11;
    protected final SplitMenuButton splitMenuButton;
    protected final MenuItem menuItem;
    protected final MenuItem menuItem0;
    protected final DropShadow dropShadow1;
    protected final Label label12;
    protected final Button button0;
    protected final DropShadow dropShadow2;
    protected final SplitMenuButton splitMenuButton0;
    protected final MenuItem menuItem1;
  
    protected final MenuItem menuItem2;
   
    protected final MenuItem menuItem3;
  
    protected final MenuItem menuItem4;
  
    protected final DropShadow dropShadow3;
    protected final Label label13;
    
    Image image1 = null;
    ImageView imageView1 = null;
    Image image2 = null;
    ImageView imageView2 = null;
    Image image3 = null;
    ImageView imageView3 = null;
    Image image4 = null;
     Image playerImage = null;
     
     ImageView imageView4 = null;
     ImageView imageView11 = null;
     ImageView imageView12 = null;
     ImageView imageView13 = null;
     ImageView imageView14 = null;
     ImageView imageViewbar = null;
    
    public int selectedAvatar=0;

    public PlayerProfileBase(ClientPlayer client , String avatarPath) {

        pane = new Pane();
        label = new Label();
        label0 = new Label();
        dropShadow = new DropShadow();
        label1 = new Label();
        label2 = new Label();
        label3 = new Label();
        button = new Button();
        dropShadow0 = new DropShadow();
        label4 = new Label();
        label5 = new Label();
        imageView = new ImageView();
        label6 = new Label();
        label7 = new Label();
        label8 = new Label();
        label9 = new Label();
        label10 = new Label();
        label11 = new Label();
        splitMenuButton = new SplitMenuButton();
        menuItem = new MenuItem();
        menuItem0 = new MenuItem();
        dropShadow1 = new DropShadow();
        label12 = new Label();
        button0 = new Button();
        dropShadow2 = new DropShadow();
        splitMenuButton0 = new SplitMenuButton();
        menuItem1 = new MenuItem();
        
        menuItem2 = new MenuItem();
       
        menuItem3 = new MenuItem();
       
        menuItem4 = new MenuItem();
      
        dropShadow3 = new DropShadow();
        label13 = new Label();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(700.0);
        setPrefWidth(900.0);

        BorderPane.setAlignment(pane, javafx.geometry.Pos.CENTER);
        pane.setStyle("-fx-background-color: black;");

        label.setLayoutX(509.0);
        label.setLayoutY(206.0);
        label.setPrefHeight(99.0);
        label.setPrefWidth(190.0);
        label.setText("Wins             :");
        label.setTextFill(javafx.scene.paint.Color.WHITE);
        label.setFont(new Font("Jokerman", 22.0));

        label0.setAlignment(javafx.geometry.Pos.CENTER);
        label0.setLayoutX(53.0);
        label0.setLayoutY(45.0);
        label0.setPrefHeight(112.0);
        label0.setPrefWidth(424.0);
        label0.setStyle("-fx-background-color: black; -fx-region-background: white;");
        label0.setText("Your Plofile");
        label0.setTextFill(javafx.scene.paint.Color.WHITE);
        label0.setFont(new Font("Jokerman", 57.0));

        dropShadow.setColor(javafx.scene.paint.Color.valueOf("#eb02b1"));
        dropShadow.setHeight(255.0);
        dropShadow.setRadius(127.0);
        dropShadow.setSpread(0.76);
        dropShadow.setWidth(255.0);
        label0.setEffect(dropShadow);

        label1.setLayoutX(57.0);
        label1.setLayoutY(210.0);
        label1.setPrefHeight(81.0);
        label1.setPrefWidth(171.0);
        label1.setText("user_name  : ");
        label1.setTextFill(javafx.scene.paint.Color.WHITE);
        label1.setFont(new Font("Jokerman", 22.0));

        label2.setLayoutX(57.0);
        label2.setLayoutY(253.0);
        label2.setPrefHeight(99.0);
        label2.setPrefWidth(190.0);
        label2.setText("# of Games   :");
        label2.setTextFill(javafx.scene.paint.Color.WHITE);
        label2.setFont(new Font("Jokerman", 22.0));

        label3.setLayoutX(56.0);
        label3.setLayoutY(302.0);
        label3.setPrefHeight(99.0);
        label3.setPrefWidth(190.0);
        label3.setText("Draw              :");
        label3.setTextFill(javafx.scene.paint.Color.WHITE);
        label3.setFont(new Font("Jokerman", 22.0));

        button.setLayoutX(646.0);
        button.setLayoutY(596.0);
        button.setMnemonicParsing(false);
        button.setPrefHeight(69.0);
        button.setPrefWidth(200.0);
        button.setStyle("-fx-background-color: black; -fx-background-radius: 100; -fx-border-color: white; -fx-border-radius: 100;");
        button.setText("Back");
        button.setTextFill(javafx.scene.paint.Color.WHITE);
        button.setFont(new Font("Jokerman", 29.0));

        dropShadow0.setColor(javafx.scene.paint.Color.valueOf("#c308aa"));
        dropShadow0.setHeight(53.62);
        dropShadow0.setRadius(27.049999999999997);
        dropShadow0.setSpread(0.57);
        dropShadow0.setWidth(56.58);
        button.setEffect(null);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //client.sendDataToServer("03" + "0"); 
                client.setGameRoot("into");
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
         client.sendDataToServer("ZD");
        label4.setLayoutX(509.0);
        label4.setLayoutY(300.0);
        label4.setPrefHeight(99.0);
        label4.setPrefWidth(190.0);
        label4.setText("W/L Ratio  :");
        label4.setTextFill(javafx.scene.paint.Color.WHITE);
        label4.setFont(new Font("Jokerman", 22.0));

        label5.setLayoutX(508.0);
        label5.setLayoutY(251.0);
        label5.setPrefHeight(99.0);
        label5.setPrefWidth(190.0);
        label5.setText("Loses            :");
        label5.setTextFill(javafx.scene.paint.Color.WHITE);
        label5.setFont(new Font("Jokerman", 22.0));

        

        label6.setLayoutX(212.0);
        label6.setLayoutY(203.0);
        label6.setPrefHeight(99.0);
        label6.setPrefWidth(190.0);
        label6.setText("Name");
        label6.setTextFill(javafx.scene.paint.Color.WHITE);
        label6.setFont(new Font("Jokerman", 22.0));

        label7.setLayoutX(212.0);
        label7.setLayoutY(254.0);
        label7.setPrefHeight(99.0);
        label7.setPrefWidth(190.0);
        label7.setText("2");
        label7.setTextFill(javafx.scene.paint.Color.WHITE);
        label7.setFont(new Font("Jokerman", 22.0));

        label8.setLayoutX(654.0);
        label8.setLayoutY(208.0);
        label8.setPrefHeight(99.0);
        label8.setPrefWidth(190.0);
        label8.setText("4");
        label8.setTextFill(javafx.scene.paint.Color.WHITE);
        label8.setFont(new Font("Jokerman", 22.0));

        label9.setLayoutX(211.0);
        label9.setLayoutY(304.0);
        label9.setPrefHeight(99.0);
        label9.setPrefWidth(190.0);
        label9.setText("2");
        label9.setTextFill(javafx.scene.paint.Color.WHITE);
        label9.setFont(new Font("Jokerman", 22.0));

        label10.setLayoutX(655.0);
        label10.setLayoutY(253.0);
        label10.setPrefHeight(99.0);
        label10.setPrefWidth(190.0);
        label10.setText("3");
        label10.setTextFill(javafx.scene.paint.Color.WHITE);
        label10.setFont(new Font("Jokerman", 22.0));

        label11.setLayoutX(654.0);
        label11.setLayoutY(302.0);
        label11.setPrefHeight(99.0);
        label11.setPrefWidth(190.0);
        label11.setText("1.3");
        label11.setTextFill(javafx.scene.paint.Color.WHITE);
        label11.setFont(new Font("Jokerman", 22.0));

        splitMenuButton.setAlignment(javafx.geometry.Pos.CENTER);
        splitMenuButton.setLayoutX(213.0);
        splitMenuButton.setLayoutY(410.0);
        splitMenuButton.setMnemonicParsing(false);
        splitMenuButton.setOpacity(0.8);
        splitMenuButton.setPrefHeight(64.0);
        splitMenuButton.setPrefWidth(315.0);
        splitMenuButton.setStyle("-fx-background-color: black; -fx-region-background: black; -fx-region-border: 100; -fx-border-color: black; -fx-background-radius: 100;");
        splitMenuButton.setText("Choose one to play");

//        menuItem.setMnemonicParsing(false);
//        menuItem.setText("Action 1");
//
//        menuItem0.setMnemonicParsing(false);
//        menuItem0.setText("Action 2");

        dropShadow1.setColor(javafx.scene.paint.Color.valueOf("#e31087"));
        dropShadow1.setHeight(35.59);
        dropShadow1.setRadius(21.0);
        dropShadow1.setSpread(0.41);
        dropShadow1.setWidth(50.41);
        splitMenuButton.setEffect(dropShadow1);

        label12.setLayoutX(55.0);
        label12.setLayoutY(394.0);
        label12.setPrefHeight(99.0);
        label12.setPrefWidth(190.0);
        label12.setText("Records        :");
        label12.setTextFill(javafx.scene.paint.Color.WHITE);
        label12.setFont(new Font("Jokerman", 22.0));

        button0.setLayoutX(575.0);
        button0.setLayoutY(406.0);
        button0.setMnemonicParsing(false);
        button0.setPrefHeight(49.0);
        button0.setPrefWidth(137.0);
        button0.setStyle("-fx-background-color: black; -fx-background-radius: 100; -fx-border-color: white; -fx-border-radius: 100;");
        button0.setText("Play");
        button0.setTextFill(javafx.scene.paint.Color.WHITE);
        button0.setFont(new Font("Jokerman", 29.0));

       
        button0.setEffect(null);
        button0.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
              if(!splitMenuButton.getText().equals("Choose one to play"))
                    client.sendDataToServer("sm" + splitMenuButton.getText());
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

        splitMenuButton0.setAlignment(javafx.geometry.Pos.CENTER);
        splitMenuButton0.setLayoutX(212.0);
        splitMenuButton0.setLayoutY(501.0);
        splitMenuButton0.setMnemonicParsing(false);
        splitMenuButton0.setOpacity(0.8);
        splitMenuButton0.setPrefHeight(64.0);
        splitMenuButton0.setPrefWidth(315.0);
        splitMenuButton0.setStyle("-fx-background-color: black; -fx-region-background: black; -fx-region-border: 100; -fx-border-color: black; -fx-background-radius: 100;");
       

         try {
            image1 = new Image("file:src/tictactoe/img/av1.jpg",70, 50, false, true);
            imageView1 = new ImageView(image1);
            imageView11 = new ImageView(image1);
            image2 = new Image("file:src/tictactoe/img/av2.jpg", 70, 50, false, true);
            imageView2 = new ImageView(image2);
            imageView12 = new ImageView(image2);
            image3 = new Image("file:src/tictactoe/img/av3.jpg", 70, 50, false, true);
            imageView3 = new ImageView(image3);
            imageView13 = new ImageView(image3);
            image4 = new Image("file:src/tictactoe/img/av4.jpg", 70 ,50, false, true);
            imageView4 = new ImageView(image4);
            imageView14 = new ImageView(image4);
            playerImage = new Image(avatarPath,120, 100, false, true);
            imageView = new ImageView(playerImage);
            imageViewbar = new ImageView(playerImage);
            
        } catch (Exception p) {
            p.printStackTrace();
        }
         
        imageView.setFitHeight(146.0);
        imageView.setFitWidth(250.0);
        imageView.setLayoutX(542.0);
        imageView.setLayoutY(45.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
         
        splitMenuButton0.setGraphic(imageViewbar);
        
        menuItem1.setMnemonicParsing(false);
        menuItem1.setGraphic(imageView1);
        menuItem1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ev)
            {
                selectedAvatar = 0;
                splitMenuButton0.setGraphic(imageView11);
            }
        });

        menuItem2.setMnemonicParsing(false);
        menuItem2.setGraphic(imageView4);
        menuItem2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ev)
            {
                selectedAvatar = 3;
                splitMenuButton0.setGraphic(imageView14);
            }
        });

        menuItem3.setMnemonicParsing(false);

        menuItem3.setGraphic(imageView3);
        menuItem3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ev)
            {
                selectedAvatar = 2;
                splitMenuButton0.setGraphic(imageView13);
            }
        });

        menuItem4.setMnemonicParsing(false);

        menuItem4.setGraphic(imageView2);
        menuItem4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ev)
            {
                selectedAvatar = 1;
                splitMenuButton0.setGraphic(imageView12);
            }
        });

        splitMenuButton0.setEffect(dropShadow1);

        label13.setLayoutX(56.0);
        label13.setLayoutY(480.0);
        label13.setPrefHeight(99.0);
        label13.setPrefWidth(190.0);
        label13.setText("Avatar          :");
        label13.setTextFill(javafx.scene.paint.Color.WHITE);
        label13.setFont(new Font("Jokerman", 22.0));
        setCenter(pane);

        pane.getChildren().add(label);
        pane.getChildren().add(label0);
        pane.getChildren().add(label1);
        pane.getChildren().add(label2);
        pane.getChildren().add(label3);
        pane.getChildren().add(button);
        pane.getChildren().add(label4);
        pane.getChildren().add(label5);
      
        pane.getChildren().add(label6);
        pane.getChildren().add(label7);
        pane.getChildren().add(label8);
        pane.getChildren().add(label9);
        pane.getChildren().add(label10);
        pane.getChildren().add(label11);
        pane.getChildren().add(splitMenuButton);
        pane.getChildren().add(label12);
        pane.getChildren().add(button0);
        splitMenuButton0.getItems().add(menuItem1);
        splitMenuButton0.getItems().add(menuItem2);
        splitMenuButton0.getItems().add(menuItem3);
        splitMenuButton0.getItems().add(menuItem4);
        pane.getChildren().add(splitMenuButton0);
        pane.getChildren().add(label13);
        pane.getChildren().add(imageView);

    }
}
