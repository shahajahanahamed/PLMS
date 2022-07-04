package com.plms.controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private Label valLbl;
    @FXML
    private TextField usernameTB;
    @FXML
    private PasswordField passwordTB;

    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        LoginController.stage = stage;
    }

    public void doLogin(MouseEvent mouseEvent) {
        userLogin("admin","123");
    }

    public void userLogin(String username,String password){
        try {
            if(usernameTB.getText().equals(username) && passwordTB.getText().equals(password)){
                valLbl.setStyle("fx-background-color:White;-fx-text-fill:Green; -fx-font-size: 14;");
                valLbl.setText("Login Successful");
                loadScene("/com/plms/design/homepage-view.fxml");
                new Homepage().getUserProfileName(username);
            } else if (usernameTB.getText().isEmpty() || passwordTB.getText().isEmpty()) {
                valLbl.setStyle("-fx-text-fill:Red; -fx-font-size: 14;");
                valLbl.setText("Please enter Username and Password");
            }else {
                valLbl.setStyle("-fx-text-fill:Red; -fx-font-size: 14;");
                valLbl.setText("Invalid Username or Password");
            }
        }catch (Exception e){
            System.out.println("Exception Occured: "+e);

        }
    }
    public void loadScene(String fxml){
        Parent pane;
        try {
            pane = FXMLLoader.load(Homepage.class.getResource(fxml));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.getScene().setRoot(pane);
    }
}