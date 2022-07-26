package com.plms.controller;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

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
    @FXML
    private Button closeBtn,minimizeBtn;
    @FXML
    private FontAwesomeIcon closeIcon,minimizeIcon;


    @FXML
    void clickOnCloseBtn(MouseEvent event) {
        Stage stage1 = (Stage) closeBtn.getScene().getWindow();
        stage1.close();
    }

    @FXML
    void clickOnCloseIcon(MouseEvent event) {
        clickOnCloseBtn(event);
    }

    @FXML
    void clickOnMinimizeBtn(MouseEvent event) {
        Stage stage1 = (Stage) closeBtn.getScene().getWindow();
        stage1.setIconified(true);
    }

    @FXML
    void clickOnMinimizeIcon(MouseEvent event) {
        clickOnMinimizeBtn(event);
    }
    @FXML
    public void clickOnHomeMenu(javafx.scene.input.MouseEvent mouseEvent) {
        bp.setCenter(ap);
    }
    @FXML
    public void clickOnEmployeeMenu(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        loadContent("ViewEmployeePage");
    }
    @FXML
    public void clickOnPatientMenu(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        loadContent("ViewPatientPage");
    }
    public void clickOnTestMenu(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        loadContent("ViewTestPage");
    }
    private void loadContent(String page) throws IOException {
        Parent content;
        content = FXMLLoader.load(getClass().getResource("/com/plms/views/"+page+".fxml"));
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
