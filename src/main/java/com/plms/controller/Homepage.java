package com.plms.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class Homepage {
    @FXML
    private Button logout;


    public void userLogOut(ActionEvent event) throws IOException {
        LoginController m = new LoginController();
        //m.changeScene("login-view.fxml");

    }
}
