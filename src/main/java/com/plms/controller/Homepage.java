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
    public void clickOnEmployeeMenu(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        loadContent(ViewEmployeeController.class,"ViewEmployeePage");
    }
    @FXML
    public void clickOnPatientMenu(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        loadContent(ViewPatientController.class,"ViewPatientPage");
    }
    private void loadContent(Class controllerName,String page) throws IOException {
        Parent content;
        content = FXMLLoader.load(controllerName.getResource("/com/plms/views/"+page+".fxml"));
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
