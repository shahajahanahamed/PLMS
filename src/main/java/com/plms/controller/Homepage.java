package com.plms.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class Homepage {


    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;
    @FXML
    private Button homeBtn;
    @FXML
    public Label lblUserProfile;
    @FXML
    public Label lblLabName;
    @FXML
    Label lblWlcm;
    @FXML
    Label lblHome;
    @FXML
    public void clickOnHomeMenu(javafx.scene.input.MouseEvent mouseEvent) {
        bp.setCenter(ap);
        printUserProfileName("Reeju");
    }

    @FXML
    public void clickOnEmployeeMenu(javafx.scene.input.MouseEvent mouseEvent) {
        loadContent("employee");
    }

    @FXML
    public void clickOnPatientMenu(javafx.scene.input.MouseEvent mouseEvent) {
        loadContent("patient");
    }

    private void loadContent(String page) {
        Parent content;
        try {
            content = FXMLLoader.load(getClass().getResource("/com/plms/design/" + page + ".fxml"));
            System.out.println(getClass());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        bp.setCenter(content);
    }

    public void userLogOut(ActionEvent event) throws IOException {
        LoginController m = new LoginController();
        //m.changeScene("login-view.fxml");

    }

    public void printUserProfileName(String username) {

        System.out.println(username);
        lblUserProfile.setText(username);
        System.out.println(lblUserProfile.getText());
        lblWlcm.setText("Welcome - "+username);
        System.out.println(lblWlcm.getText());
        System.out.println(lblHome.getText());
        System.out.println(lblLabName.getText());
    }
}
