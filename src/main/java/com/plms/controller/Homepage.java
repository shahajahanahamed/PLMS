package com.plms.controller;

import com.plms.modules.SceneLoader;
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
    private Button closeBtn, minimizeBtn;
    @FXML
    private FontAwesomeIcon closeIcon, minimizeIcon;

    @FXML
    private Label lblHome;
    @FXML
    private Label lblLabName;

    @FXML
    private Button aboutUsMenuBtn, contactUsMenuBtn, dashboardMenuBtn, employeeMenuBtn, helpMenuBtn, homeMenuBtn, patientMenuBtn, priceMenuBtn, reportsMenuBtn, testMenuBtn;
    @FXML
    private Button userProfileBtn;
    private Button selectedButton;

    String normalBtnStyle = String.format("-fx-background-color: #6F5CC2;-fx-border-color: linear-gradient(to top right, #06fff3de, #408379);");
    String selectedBtnStyle = String.format("-fx-background-color: #4f3f98;\n" +
            "    -fx-border-color: linear-gradient(to right, #FFFFFF, #4decf492);\n" +
            "    -fx-border-width:2px;-fx-border-radius:15px;");

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
    void clickOnUserProfileBtn(MouseEvent event) throws IOException {
        new SceneLoader().loadSceneInDifferentStage(getClass(),"UserProfilePage");
    }
    @FXML
    void clickOnUserProfileIcon(MouseEvent event) throws IOException {
        clickOnUserProfileBtn(event);
    }
    @FXML
    public void clickOnHomeMenu(javafx.scene.input.MouseEvent mouseEvent) {
        bp.setCenter(ap);
        selectedButton.setStyle(normalBtnStyle);
        homeMenuBtn.setStyle(selectedBtnStyle);
        selectedButton=homeMenuBtn;

    }
    @FXML
    void clickOnHomeMenuIcon(MouseEvent event) {
        clickOnHomeMenu(event);
    }


    @FXML
    public void clickOnEmployeeMenu(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        loadContent("ViewEmployeePage");
        selectedButton.setStyle(normalBtnStyle);
        employeeMenuBtn.setStyle(selectedBtnStyle);
        selectedButton=employeeMenuBtn;
    }
    @FXML
    void clickOnEmployeeMenuIcon(MouseEvent event) throws IOException {
        clickOnEmployeeMenu(event);
    }
    @FXML
    public void clickOnPatientMenu(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        loadContent("ViewPatientPage");
        selectedButton.setStyle(normalBtnStyle);
        patientMenuBtn.setStyle(selectedBtnStyle);
        selectedButton=patientMenuBtn;
    }
    @FXML
    void clickOnPatientMenuIcon(MouseEvent event) throws IOException {
        clickOnPatientMenu(event);
    }
    public void clickOnTestMenu(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        loadContent("ViewTestPage");
        selectedButton.setStyle(normalBtnStyle);
        testMenuBtn.setStyle(selectedBtnStyle);
        selectedButton=testMenuBtn;
    }
    @FXML
    void clickOnTestMenuIcon(MouseEvent event) throws IOException {
        clickOnTestMenu(event);
    }
    @FXML
    void clickOnAboutUsMenu(MouseEvent event) {
        selectedButton.setStyle(normalBtnStyle);
        aboutUsMenuBtn.setStyle(selectedBtnStyle);
        selectedButton=aboutUsMenuBtn;
    }

    @FXML
    void clickOnContactUsMenu(MouseEvent event) {
        selectedButton.setStyle(normalBtnStyle);
        contactUsMenuBtn.setStyle(selectedBtnStyle);
        selectedButton=contactUsMenuBtn;
    }
    @FXML
    void clickOnDashboardMenu(MouseEvent event) {
        selectedButton.setStyle(normalBtnStyle);
        dashboardMenuBtn.setStyle(selectedBtnStyle);
        selectedButton=dashboardMenuBtn;
    }
    @FXML
    void clickOnDashboardMenuIcon(MouseEvent event) {
        clickOnDashboardMenu(event);
    }

    @FXML
    void clickOnHelpMenu(MouseEvent event) {
        selectedButton.setStyle(normalBtnStyle);
        helpMenuBtn.setStyle(selectedBtnStyle);
        selectedButton=helpMenuBtn;
    }
    @FXML
    void clickOnHelpMenuIcon(MouseEvent event) {
        clickOnHelpMenu(event);
    }
    @FXML
    void clickOnPriceMenu(MouseEvent event) {
        selectedButton.setStyle(normalBtnStyle);
        priceMenuBtn.setStyle(selectedBtnStyle);
        selectedButton=priceMenuBtn;
    }
    @FXML
    void clickOnPriceMenuIcon(MouseEvent event) {
        clickOnPriceMenu(event);
    }
    @FXML
    void clickOnReportsMenu(MouseEvent event) {
        selectedButton.setStyle(normalBtnStyle);
        reportsMenuBtn.setStyle(selectedBtnStyle);
        selectedButton=reportsMenuBtn;
    }
    @FXML
    void clickOnReportsMenuIcon(MouseEvent event) {
        clickOnReportsMenu(event);
    }


    private void loadContent(String page) throws IOException {
        Parent content;
        content = FXMLLoader.load(getClass().getResource("/com/plms/views/" + page + ".fxml"));
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
        homeMenuBtn.setStyle(selectedBtnStyle);
        selectedButton = homeMenuBtn;
    }




































}
