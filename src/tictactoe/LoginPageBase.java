package tictactoe;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginPageBase extends BorderPane {

    protected final TabPane tabPane;
    protected final Tab tab;
    protected final Pane pane;
    protected final TextField textField;
    protected final PasswordField passwordField;
    protected final Button button;
    protected final Label label;
    protected final Label label0;
    protected final Tab tab0;
    protected final Pane pane0;
    protected final TextField textField0;
    protected final TitledPane titledPane;
    protected final AnchorPane anchorPane;
    protected final FlowPane flowPane;
    protected final Button button0;
    protected final PasswordField passwordField0;
    protected final PasswordField passwordField1;
    
      
    protected String userName;
    protected String password;
    protected int avatarId;
    
  
    
    public class loginUserData
    {
        String userName;
        String password;
    }
    
    
    public LoginPageBase(ClientPlayer client,Scene s,TicTacBase nextRoot) {
       
       
        tabPane = new TabPane();
        tab = new Tab();
        pane = new Pane();
        textField = new TextField();
        passwordField = new PasswordField();
        button = new Button();
        label = new Label();
        label0 = new Label();
        tab0 = new Tab();
        pane0 = new Pane();
        textField0 = new TextField();
        titledPane = new TitledPane();
        anchorPane = new AnchorPane();
        flowPane = new FlowPane();
        button0 = new Button();
        passwordField0 = new PasswordField();
        passwordField1 = new PasswordField();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        BorderPane.setAlignment(tabPane, javafx.geometry.Pos.CENTER);

        tab.setText("login");

        textField.setLayoutX(195.0);
        textField.setLayoutY(114.0);

        passwordField.setLayoutX(195.0);
        passwordField.setLayoutY(175.0);

        button.setLayoutX(241.0);
        button.setLayoutY(230.0);
        button.setMnemonicParsing(false);
        button.setText("login");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ev)
            {
                setLoginUserData(textField.getText(),passwordField.getText());
                //DataBase db = new DataBase();
                //int  f = db.checkLoginUser(userName, password);
               // System.out.println(f);
                //TicTacToe.switchScenes(2);
                int userLength = userName.length();
                int passwordLength = password.length();
                if(client.falseId < 10)
                {
                    
                    client.sendDataToServer("01"+"0"+client.falseId+userLength+userName+passwordLength+password); 
                }
                else
                     client.sendDataToServer("01"+client.falseId+userLength+userName+passwordLength+password);    
               
                client.setGameScene(s,nextRoot);
               // s.setRoot(nextRoot);
            }
        });

        label.setLayoutX(133.0);
        label.setLayoutY(118.0);
        label.setText("user name");

        label0.setLayoutX(133.0);
        label0.setLayoutY(179.0);
        label0.setText("password");
        tab.setContent(pane);

        tab0.setText("register");

        textField0.setLayoutX(197.0);
        textField0.setLayoutY(41.0);
        textField0.setPromptText("enter username");

        titledPane.setExpanded(false);
        titledPane.setLayoutX(192.0);
        titledPane.setLayoutY(208.0);
        titledPane.setPrefHeight(148.0);
        titledPane.setPrefWidth(159.0);
        titledPane.setText("avatar");

        anchorPane.setMinHeight(0.0);
        anchorPane.setMinWidth(0.0);
        anchorPane.setPrefHeight(24.0);
        anchorPane.setPrefWidth(142.0);
        anchorPane.setStyle("-fx-background-color: red;");

        flowPane.setLayoutX(-86.0);
        flowPane.setLayoutY(9.0);
        flowPane.setPrefHeight(91.0);
        flowPane.setPrefWidth(124.0);
        titledPane.setContent(anchorPane);

        button0.setLayoutX(438.0);
        button0.setLayoutY(270.0);
        button0.setMnemonicParsing(false);
        button0.setText("Register");
        button0.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ev)
            {
                if(!textField0.getText().equals("") && passwordField0.getText().equals(passwordField1.getText()) 
                        && !passwordField0.getText().equals("")  )
                {
                     setRegisterationUserData(textField0.getText(),passwordField0.getText(),1);
                     DataBase db = new DataBase();
                     int  f = db.RegisterNewUser(userName,password,1);
                     System.out.println(f);
                }
                else{
                    if (textField0.getText().equals(""))
                    {
                        System.out.println("please enter user name...");
                    }

                    if ( passwordField0.getText().equals("")  )
                    {
                        System.out.println("password not matches....");
                    }
                    
                    if ( passwordField1.getText().equals("")  )
                    {
                        System.out.println("enter confirm ....");
                    }
                }
            }
        });

        passwordField0.setLayoutX(197.0);
        passwordField0.setLayoutY(100.0);
        passwordField0.setPromptText("enter new password");

        passwordField1.setLayoutX(197.0);
        passwordField1.setLayoutY(149.0);
        passwordField1.setPromptText("confirm");
        tab0.setContent(pane0);
        setCenter(tabPane);

        pane.getChildren().add(textField);
        pane.getChildren().add(passwordField);
        pane.getChildren().add(button);
        pane.getChildren().add(label);
        pane.getChildren().add(label0);
        tabPane.getTabs().add(tab);
        pane0.getChildren().add(textField0);
        anchorPane.getChildren().add(flowPane);
        pane0.getChildren().add(titledPane);
        pane0.getChildren().add(button0);
        pane0.getChildren().add(passwordField0);
        pane0.getChildren().add(passwordField1);
        tabPane.getTabs().add(tab0);
        
    }
    
     public void setLoginUserData(String userName,String userPassword)
    {
        this.userName = userName;
        this.password = userPassword; 
    }
    
    public loginUserData getLoginUserData()
    {
        loginUserData data = new loginUserData();
        data.userName = this.userName;
        data.password = this.password;
        return data;
    }
    
    public void setRegisterationUserData(String userName,String userPassword,int avatar)
    {
        this.userName = userName;
        this.password = userPassword; 
        this.avatarId = avatar;
    }
    
//    public void setClient(ClientPlayer c)
//    {
//        client = c;
//    }
    
}
