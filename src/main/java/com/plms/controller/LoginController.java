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
        userLogin("Admin","123");
    }

    public void userLogin(String username,String password){
        try {
            if(usernameTB.getText().equals(username) && passwordTB.getText().equals(password)){
                valLbl.setVisible(true);
                valLbl.setStyle("-fx-text-fill:Green; -fx-font-size: 14;");
                valLbl.setText("Login Successful");
                Thread.sleep(10000);
                valLbl.setVisible(false);
                loadScene("/com/plms/design/homepage-view.fxml");
            } else if (usernameTB.getText().isEmpty() || passwordTB.getText().isEmpty()) {
                valLbl.setText("Please enter Username and Password");
                valLbl.setStyle("-fx-text-fill:Red; -fx-font-size: 18;");
                //Thread.sleep(2000);
                //valLbl.setVisible(false);
            }else {
                valLbl.setText("Invalid Username or Password");
                valLbl.setStyle("-fx-text-fill:Red; -fx-font-size: 18;");
                //Thread.sleep(2000);
                //valLbl.setVisible(false);
            }
        }catch (Exception e){
            System.out.println("Exception Occured: "+e);

        }
    }
    public void loadScene(String fxml){
        Parent pane = null;
        try {
            pane = FXMLLoader.load(Homepage.class.getResource(fxml));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.getScene().setRoot(pane);
    }
}