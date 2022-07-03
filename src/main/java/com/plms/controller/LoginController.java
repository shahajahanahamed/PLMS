package com.plms.controller;

import com.plms.modules.LoginModule;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {
    public LoginController() {

    }
    @FXML
    private Label wrongLogIn;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    public void userLogIn(ActionEvent event) throws IOException {
        checkLogin();

    }
    private void checkLogin() throws IOException {
        LoginModule loginM = new LoginModule();
        if(username.getText().equals("admin") && password.getText().equals("123")) {
            wrongLogIn.setText("Success!");
            loginM.changeScene("/com/plms/design/homepage-view.fxml");
        }
        else if(username.getText().isEmpty() && password.getText().isEmpty()) {
            wrongLogIn.setText("Please enter username and password.");
        }
        else {
            wrongLogIn.setText("Invalid username or password!");
        }
    }


}