package com.plms.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Homepage implements Initializable {
    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;
    @FXML
    private Label lblUserProfile;
    private String username;

    @FXML
    public void clickOnHomeMenu(javafx.scene.input.MouseEvent mouseEvent) {
        bp.setCenter(ap);
    }
    @FXML
    public void clickOnEmployeeMenu(javafx.scene.input.MouseEvent mouseEvent) {
        loadContent("ViewEmployeePage");
    }
    @FXML
    public void clickOnPatientMenu(javafx.scene.input.MouseEvent mouseEvent) {
        loadContent("ViewPatientPage");
    }
    private void loadContent(String page) {
        Parent content;
        try {
            content = FXMLLoader.load(getClass().getResource("/com/plms/views/" +page+".fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        bp.setCenter(content);
    }
    public void userLogOut(ActionEvent event) throws IOException {
        LoginController m = new LoginController();
        //m.changeScene("LoginPage.fxml");

    }
    public void printUserProfileName(String username) {
        lblUserProfile.setText(username);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
