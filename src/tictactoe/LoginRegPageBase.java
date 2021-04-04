package tictactoe;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class LoginRegPageBase extends BorderPane {

    protected final TabPane tabPane;
    protected final Tab tab;
    protected final Pane pane;
    protected final PasswordField passwordField;
    protected final DropShadow dropShadow;
    protected final Button button;
    protected final DropShadow dropShadow0;
    protected final Label label;
    protected final Label label0;
    protected final DropShadow dropShadow1;
    protected final Label label1;
    protected final TextField textField;
    protected final DropShadow dropShadow2;
    protected final Tab tab0;
    protected final Pane pane0;
    protected final Label label3;
    protected final DropShadow dropShadow3;
    protected final Label label4;
    protected final Label label5;
    protected final TextField textField0;
    protected final DropShadow dropShadow4;
    protected final PasswordField passwordField0;
    protected final DropShadow dropShadow5;
    protected final Label label6;
    protected final TextField textField1;
    protected final DropShadow dropShadow6;
    protected final Label label7;
    protected final PasswordField passwordField1;
    protected final DropShadow dropShadow7;
    protected final Label label8;
    protected final Button button0;
    protected final DropShadow dropShadow8;
    protected final Button button1;
    protected final DropShadow dropShadow9;
    protected final Button button2;
    protected final DropShadow dropShadow10;
    protected final Button button3;
    protected final DropShadow dropShadow11;
    protected final Button button4;
    protected final DropShadow dropShadow12;
    Image image1 = null;
    ImageView imageView1 = null;
    Image image2 = null;
    ImageView imageView2 = null;
    Image image3 = null;
    ImageView imageView3 = null;
    Image image4 = null;
    ImageView imageView4 = null;
    
    protected String userName;
    protected String password;
    protected int avatarId = 0;
    protected String email;
    
    Alert user_alert =null;
    Alert pass_alert  = null; 
    Alert confirm_alert = null; 
    Alert email_alert = null; 
    Alert register_alert=null ;
    Alert loginAlert=null ;
    
    public LoginRegPageBase(ClientPlayer client) {

        tabPane = new TabPane();
        tab = new Tab();
        pane = new Pane();
        passwordField = new PasswordField();
        dropShadow = new DropShadow();
        button = new Button();
        dropShadow0 = new DropShadow();
        label = new Label();
        label0 = new Label();
        dropShadow1 = new DropShadow();
        label1 = new Label();
        textField = new TextField();
        dropShadow2 = new DropShadow();
       
      
        tab0 = new Tab();
        pane0 = new Pane();
        label3 = new Label();
        dropShadow3 = new DropShadow();
        label4 = new Label();
        label5 = new Label();
        textField0 = new TextField();
        dropShadow4 = new DropShadow();
        passwordField0 = new PasswordField();
        dropShadow5 = new DropShadow();
        label6 = new Label();
        textField1 = new TextField();
        dropShadow6 = new DropShadow();
        label7 = new Label();
        passwordField1 = new PasswordField();
        dropShadow7 = new DropShadow();
        label8 = new Label();
        button0 = new Button();
        dropShadow8 = new DropShadow();
        button1 = new Button();
        dropShadow9 = new DropShadow();
        button2 = new Button();
        dropShadow10 = new DropShadow();
        button3 = new Button();
        dropShadow11 = new DropShadow();
        button4 = new Button();
        dropShadow12 = new DropShadow();
        
        user_alert = new Alert(Alert.AlertType.ERROR);
        pass_alert = new Alert(Alert.AlertType.ERROR);
        confirm_alert = new Alert(Alert.AlertType.ERROR);
        email_alert = new Alert(Alert.AlertType.ERROR);
        register_alert = new Alert(Alert.AlertType.INFORMATION);
        loginAlert= new Alert(Alert.AlertType.INFORMATION);

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(700.0);
        setPrefWidth(900.0);

        BorderPane.setAlignment(tabPane, javafx.geometry.Pos.CENTER);

        tab.setClosable(false);
        tab.setText("Login");

        pane.setStyle("-fx-background-color: black;");

        passwordField.setAlignment(javafx.geometry.Pos.CENTER);
        passwordField.setLayoutX(397.0);
        passwordField.setLayoutY(321.0);
        passwordField.setPrefHeight(49.0);
        passwordField.setPrefWidth(261.0);
        passwordField.setPromptText("Enter Password...");
        passwordField.setStyle("-fx-background-color: black; -fx-border-color: white; -fx-border-radius: 100; -fx-background-radius: 100;");
        passwordField.setFont(new Font(20.0));

        dropShadow.setColor(javafx.scene.paint.Color.valueOf("#da0eb5"));
        dropShadow.setHeight(55.84);
        dropShadow.setRadius(28.345);
        dropShadow.setSpread(0.55);
        dropShadow.setWidth(59.54);
        passwordField.setEffect(null);
        passwordField.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent ev)
            {
                passwordField.setEffect(dropShadow);            
            }
        });
        passwordField.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent ev)
            {
                 passwordField.setEffect(null);          
            }
        });

        button.setLayoutX(318.0);
        button.setLayoutY(427.0);
        button.setMnemonicParsing(false);
        button.setPrefHeight(69.0);
        button.setPrefWidth(200.0);
        button.setStyle("-fx-background-color: black; -fx-background-radius: 100; -fx-border-color: white; -fx-border-radius: 100;");
        button.setText("Login");
        button.setTextFill(javafx.scene.paint.Color.valueOf("#9a8698"));
        button.setFont(new Font("Jokerman", 29.0));
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent ev)
            {
                setLoginUserData(textField.getText(),passwordField.getText());
                if(userName.length()>0 && password.length()>0)
                {     
                    client.sendDataToServer("01"+"0_"+userName+"_"+password);         
                }
                else if(password.length() == 0 && userName.length() == 0)
                {
                    loginAlert.setTitle("Error");
                    loginAlert.setContentText("you must enter your username and your password");
                    loginAlert.showAndWait();
                }
                else if(userName.length() == 0)
                {
                    loginAlert.setTitle("Error");
                    loginAlert.setContentText("you must enter your username");
                    loginAlert.showAndWait();
                }
                else 
                {
                    loginAlert.setTitle("Error");
                    loginAlert.setContentText("you must enter your password");
                    loginAlert.showAndWait();
                }
            }
        });
        
        dropShadow0.setColor(javafx.scene.paint.Color.valueOf("#c308aa"));
        dropShadow0.setHeight(53.62);
        dropShadow0.setRadius(27.049999999999997);
        dropShadow0.setSpread(0.57);
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

       
        

        label.setLayoutX(187.0);
        label.setLayoutY(189.0);
        label.setPrefHeight(99.0);
        label.setPrefWidth(174.0);
        label.setText("User name:");
        label.setTextFill(javafx.scene.paint.Color.WHITE);
        label.setFont(new Font("Jokerman", 22.0));

        label0.setAlignment(javafx.geometry.Pos.CENTER);
        label0.setLayoutX(167.0);
        label0.setLayoutY(-35.0);
        label0.setPrefHeight(223.0);
        label0.setPrefWidth(504.0);
        label0.setStyle("-fx-background-color: black; -fx-region-background: white;");
        label0.setText("Tic Tac Toe");
        label0.setTextFill(javafx.scene.paint.Color.WHITE);
        label0.setFont(new Font("Jokerman", 57.0));

        dropShadow1.setColor(javafx.scene.paint.Color.valueOf("#eb02b1"));
        dropShadow1.setHeight(255.0);
        dropShadow1.setRadius(127.0);
        dropShadow1.setSpread(0.76);
        dropShadow1.setWidth(255.0);
        label0.setEffect(dropShadow1);

        label1.setLayoutX(186.0);
        label1.setLayoutY(288.0);
        label1.setPrefHeight(99.0);
        label1.setPrefWidth(174.0);
        label1.setText("Password:");
        label1.setTextFill(javafx.scene.paint.Color.WHITE);
        label1.setFont(new Font("Jokerman", 22.0));
        
        //login
        textField.setAlignment(javafx.geometry.Pos.CENTER);
        textField.setLayoutX(397.0);
        textField.setLayoutY(213.0);
        textField.setPrefHeight(47.0);
        textField.setPrefWidth(262.0);
        textField.setPromptText("Enter Username....");
        textField.setStyle("-fx-background-color: black; -fx-border-color: white; -fx-border-radius: 100; -fx-background-radius: 100;");
        textField.setFont(new Font(20.0));

        dropShadow2.setColor(javafx.scene.paint.Color.valueOf("#d316a7"));
        dropShadow2.setHeight(44.72);
        dropShadow2.setRadius(25.5675);
        dropShadow2.setSpread(0.56);
        dropShadow2.setWidth(59.55);
        textField.setEffect(null);
        textField.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent ev)
            {
               textField.setEffect(dropShadow2);
            }
        });
        textField.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent ev)
            {
              textField.setEffect(null);
            }
        });
        

        
        tab.setContent(pane);

        tab0.setClosable(false);
        tab0.setText("Register");

        pane0.setStyle("-fx-background-color: Black;");

        label3.setAlignment(javafx.geometry.Pos.CENTER);
        label3.setLayoutX(167.0);
        label3.setLayoutY(-35.0);
        label3.setPrefHeight(223.0);
        label3.setPrefWidth(504.0);
        label3.setStyle("-fx-background-color: black; -fx-region-background: white;");
        label3.setText("Tic Tac Toe");
        label3.setTextFill(javafx.scene.paint.Color.WHITE);
        label3.setFont(new Font("Jokerman", 57.0));

        dropShadow3.setColor(javafx.scene.paint.Color.valueOf("#eb02b1"));
        dropShadow3.setHeight(255.0);
        dropShadow3.setRadius(127.0);
        dropShadow3.setSpread(0.76);
        dropShadow3.setWidth(255.0);
        label3.setEffect(dropShadow3);

        label4.setLayoutX(196.0);
        label4.setLayoutY(126.0);
        label4.setPrefHeight(99.0);
        label4.setPrefWidth(174.0);
        label4.setText("User name:");
        label4.setTextFill(javafx.scene.paint.Color.WHITE);
        label4.setFont(new Font("Jokerman", 22.0));

        label5.setLayoutX(613.0);
        label5.setLayoutY(127.0);
        label5.setPrefHeight(99.0);
        label5.setPrefWidth(174.0);
        label5.setText("Password:");
        label5.setTextFill(javafx.scene.paint.Color.WHITE);
        label5.setFont(new Font("Jokerman", 22.0));
        
        //register
        textField0.setAlignment(javafx.geometry.Pos.CENTER);
        textField0.setLayoutX(134.0);
        textField0.setLayoutY(219.0);
        textField0.setPrefHeight(47.0);
        textField0.setPrefWidth(262.0);
        textField0.setPromptText("Enter Username....");
        textField0.setStyle("-fx-background-color: black; -fx-border-color: white; -fx-border-radius: 100; -fx-background-radius: 100;");
        textField0.setFont(new Font(20.0));

        dropShadow4.setColor(javafx.scene.paint.Color.valueOf("#d316a7"));
        dropShadow4.setHeight(44.72);
        dropShadow4.setRadius(25.5675);
        dropShadow4.setSpread(0.56);
        dropShadow4.setWidth(59.55);
        textField0.setEffect(null);
        textField0.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent ev)
            {
               textField0.setEffect(dropShadow4);
            }
        });
        textField0.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent ev)
            {
              textField0.setEffect(null);
            }
        });

        passwordField0.setAlignment(javafx.geometry.Pos.CENTER);
        passwordField0.setLayoutX(545.0);
        passwordField0.setLayoutY(218.0);
        passwordField0.setPrefHeight(47.0);
        passwordField0.setPrefWidth(262.0);
        passwordField0.setPromptText("Password...");
        passwordField0.setStyle("-fx-background-color: black; -fx-border-color: white; -fx-border-radius: 100; -fx-background-radius: 100;");
        passwordField0.setFont(new Font(20.0));

        dropShadow5.setColor(javafx.scene.paint.Color.valueOf("#da0eb5"));
        dropShadow5.setHeight(55.84);
        dropShadow5.setRadius(28.345);
        dropShadow5.setSpread(0.55);
        dropShadow5.setWidth(59.54);
        passwordField0.setEffect(null);
        passwordField0.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent ev)
            {
               passwordField0.setEffect(dropShadow5);
            }
        });
        passwordField0.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent ev)
            {
              passwordField0.setEffect(null);
            }
        });

        label6.setLayoutX(212.0);
        label6.setLayoutY(269.0);
        label6.setPrefHeight(71.0);
        label6.setPrefWidth(110.0);
        label6.setText("E-mail:");
        label6.setTextFill(javafx.scene.paint.Color.WHITE);
        label6.setFont(new Font("Jokerman", 22.0));

        textField1.setAlignment(javafx.geometry.Pos.CENTER);
        textField1.setLayoutX(136.0);
        textField1.setLayoutY(343.0);
        textField1.setPrefHeight(47.0);
        textField1.setPrefWidth(262.0);
        textField1.setPromptText("Enter E-mail...");
        textField1.setStyle("-fx-background-color: black; -fx-border-color: white; -fx-border-radius: 100; -fx-background-radius: 100;");
        textField1.setFont(new Font(20.0));
        

        dropShadow6.setColor(javafx.scene.paint.Color.valueOf("#d316a7"));
        dropShadow6.setHeight(44.72);
        dropShadow6.setRadius(25.5675);
        dropShadow6.setSpread(0.56);
        dropShadow6.setWidth(59.55);
        textField1.setEffect(null);
        textField1.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent ev)
            {
               textField1.setEffect(dropShadow6);
            }
        });
        textField1.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent ev)
            {
              textField1.setEffect(null);
            }
        });
        

        label7.setLayoutX(569.0);
        label7.setLayoutY(256.0);
        label7.setPrefHeight(99.0);
        label7.setPrefWidth(218.0);
        label7.setText("Confirm Password:");
        label7.setTextFill(javafx.scene.paint.Color.WHITE);
        label7.setFont(new Font("Jokerman", 22.0));

        passwordField1.setAlignment(javafx.geometry.Pos.CENTER);
        passwordField1.setLayoutX(548.0);
        passwordField1.setLayoutY(341.0);
        passwordField1.setPrefHeight(49.0);
        passwordField1.setPrefWidth(261.0);
        passwordField1.setPromptText("Confirm Password...");
        passwordField1.setStyle("-fx-background-color: black; -fx-border-color: white; -fx-border-radius: 100; -fx-background-radius: 100;");
        passwordField1.setFont(new Font(20.0));

        dropShadow7.setColor(javafx.scene.paint.Color.valueOf("#da0eb5"));
        dropShadow7.setHeight(55.84);
        dropShadow7.setRadius(28.345);
        dropShadow7.setSpread(0.55);
        dropShadow7.setWidth(59.54);
        passwordField1.setEffect(null);
        passwordField1.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent ev)
            {
               passwordField1.setEffect(dropShadow7);
            }
        });
        passwordField1.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent ev)
            {
              passwordField1.setEffect(null);
            }
        });

        label8.setLayoutX(179.0);
        label8.setLayoutY(386.0);
        label8.setPrefHeight(99.0);
        label8.setPrefWidth(174.0);
        label8.setText("Choose Avatar:");
        label8.setTextFill(javafx.scene.paint.Color.WHITE);
        label8.setFont(new Font("Jokerman", 22.0));
        
        try {
            image1 = new Image("file:src/tictactoe/img/av1.jpg",70, 50, true, true);
            imageView1 = new ImageView(image1);
            image2 = new Image("file:src/tictactoe/img/av2.jpg", 70, 50, false, true);
            imageView2 = new ImageView(image2);
            image3 = new Image("file:src/tictactoe/img/av3.jpg", 70, 50, false, true);
            imageView3 = new ImageView(image3);
            image4 = new Image("file:src/tictactoe/img/av4.jpg", 70 ,50, false, true);
            imageView4 = new ImageView(image4);
            
        } catch (Exception p) {
            p.printStackTrace();
        }

        button0.setLayoutX(162.0);
        button0.setLayoutY(474.0);
        button0.setMnemonicParsing(false);
        button0.setPrefHeight(74.0);
        button0.setPrefWidth(123.0);
        button0.setStyle("-fx-background-color: black; -fx-background-radius: 100; -fx-border-color: white; -fx-border-radius: 100;");
        dropShadow8.setColor(javafx.scene.paint.Color.valueOf("#a2c21d"));
        dropShadow8.setHeight(53.37);
        dropShadow8.setRadius(21.737499999999997);
        dropShadow8.setSpread(0.2);
        dropShadow8.setWidth(35.58);
        button0.setEffect(dropShadow8);
        button0.setGraphic(imageView1);
        button0.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent ev)
            {
               button0.setEffect(dropShadow8);
               button3.setEffect(null);
               button1.setEffect(null);
               button2.setEffect(null);
               avatarId=0;
            }
        });
       

        button1.setLayoutX(669.0);
        button1.setLayoutY(474.0);
        button1.setMnemonicParsing(false);
        button1.setPrefHeight(74.0);
        button1.setPrefWidth(123.0);
        button1.setStyle("-fx-background-color: black; -fx-background-radius: 100; -fx-border-color: white; -fx-border-radius: 100;");
        button1.setEffect(null);
        button1.setGraphic(imageView2);
        button1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent ev)
            {
               button1.setEffect(dropShadow8);
               button3.setEffect(null);
               button2.setEffect(null);
               button0.setEffect(null);
                avatarId=3;
            }
        });
       

        button2.setLayoutX(502.0);
        button2.setLayoutY(474.0);
        button2.setMnemonicParsing(false);
        button2.setPrefHeight(74.0);
        button2.setPrefWidth(123.0);
        button2.setEffect(null);
        button2.setGraphic(imageView3);
        button2.setStyle("-fx-background-color: black; -fx-background-radius: 100; -fx-border-color: white; -fx-border-radius: 100;");
        button2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent ev)
            {
               button2.setEffect(dropShadow8);
               button3.setEffect(null);
               button1.setEffect(null);
               button0.setEffect(null);
                avatarId=2;
            }
        });
       
        button3.setLayoutX(336.0);
        button3.setLayoutY(473.0);
        button3.setMnemonicParsing(false);
        button3.setPrefHeight(74.0);
        button3.setPrefWidth(123.0);
        button3.setStyle("-fx-background-color: black; -fx-background-radius: 100; -fx-border-color: white; -fx-border-radius: 100;");
        button3.setEffect(null);
        button3.setGraphic(imageView4);
        button3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent ev)
            {
               button3.setEffect(dropShadow8);
               button1.setEffect(null);
               button2.setEffect(null);
               button0.setEffect(null);
               avatarId=1;
            }
        });

        button4.setLayoutX(383.0);
        button4.setLayoutY(584.0);
        button4.setMnemonicParsing(false);
        button4.setPrefHeight(61.0);
        button4.setPrefWidth(142.0);
        button4.setStyle("-fx-background-color: black; -fx-background-radius: 100; -fx-border-color: white; -fx-border-radius: 100;");
        button4.setText("Register");
        button4.setTextFill(javafx.scene.paint.Color.valueOf("#9a8698"));
        button4.setFont(new Font("Jokerman", 18.0));
        button4.setEffect(null);
        button4.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent ev)
            {
               button4.setEffect(dropShadow0);
            }
        });
        button4.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent ev)
            {
              button4.setEffect(null);
            }
        });
         button4.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent ev) {
                if (textField0.getText().isEmpty()) {
                    user_alert.setTitle("Error");
                    user_alert.setContentText("Please enter your name");
                    user_alert.showAndWait();

                }

                else if (textField1.getText().isEmpty()) {
                    email_alert.setTitle("Error");
                    email_alert.setContentText("Please enter your email");
                    email_alert.showAndWait();

                }
                else if (!textField1.getText().isEmpty()) 
                {
                    String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
                    if (!textField1.getText().matches(regex)) {
                        email_alert.setTitle("Error");
                        email_alert.setContentText("Please enter falid email ");
                        email_alert.showAndWait();
                    }
                }
                if (passwordField0.getText().isEmpty()) {
                    pass_alert.setTitle("Error");
                    pass_alert.setContentText("Please enter a password ");
                    pass_alert.showAndWait();
                    //return;
                }
                else if (!passwordField0.getText().isEmpty()) {
                    if (8 > passwordField0.getText().length()) {
                        pass_alert.setTitle("Error");
                        pass_alert.setContentText("Password can't be less than 8 character");
                        pass_alert.showAndWait();

                    }

                }
                if (passwordField1.getText().isEmpty()) {
                    pass_alert.setTitle("Error");
                    pass_alert.setContentText("Please enter  password again");
                    pass_alert.showAndWait();

                }
                else if (!passwordField1.getText().isEmpty()) {
                    if (!passwordField1.getText().matches(passwordField0.getText())) {
                        pass_alert.setTitle("Error");
                        pass_alert.setContentText("Passwords don't match");
                        pass_alert.showAndWait();
                        //    showAlert(Alert.AlertType.ERROR, registerPane.getScene().getWindow(), "Form Error!", "Password can't be less than 8 character");
                        //return;
                    }

                }
                if (!textField0.getText().equals("") && passwordField0.getText().equals(passwordField1.getText())
                        && !passwordField0.getText().equals("") && !textField1.getText().isEmpty()) 
                {
                    userName = textField0.getText();
                    password = passwordField0.getText();
                    email = textField1.getText();
                    client.sendDataToServer("rg"+"_"+userName+"_"+password+"_"+email+"_"+avatarId);  
  
                }

            }
        });
        tab0.setContent(pane0);
        setCenter(tabPane);

        pane.getChildren().add(passwordField);
        pane.getChildren().add(button);
        pane.getChildren().add(label);
        pane.getChildren().add(label0);
        pane.getChildren().add(label1);
        pane.getChildren().add(textField);
        tabPane.getTabs().add(tab);
        pane0.getChildren().add(label3);
        pane0.getChildren().add(label4);
        pane0.getChildren().add(label5);
        pane0.getChildren().add(textField0);
        pane0.getChildren().add(passwordField0);
        pane0.getChildren().add(label6);
        pane0.getChildren().add(textField1);
        pane0.getChildren().add(label7);
        pane0.getChildren().add(passwordField1);
        pane0.getChildren().add(label8);
        pane0.getChildren().add(button0);
        pane0.getChildren().add(button1);
        pane0.getChildren().add(button2);
        pane0.getChildren().add(button3);
        pane0.getChildren().add(button4);
        tabPane.getTabs().add(tab0);

    }

    
     public void setLoginUserData(String userName,String userPassword)
    {
        this.userName = userName;
        this.password = userPassword; 
    }
    
  
    
    public void setRegisterationUserData(String userName,String userPassword,int avatar)
    {
        this.userName = userName;
        this.password = userPassword; 
        this.avatarId = avatar;
    }
}
