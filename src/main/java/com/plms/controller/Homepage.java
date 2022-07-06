package com.plms.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class Homepage {
    @FXML
    private Button logout;
    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;

    @FXML
    private Label lblUserProfile;
    @FXML
    private HBox headerPane;
    @FXML
    private Pane pane;
    @FXML
    private Label wlcmLbl;
    @FXML
    public void clickOnHomeMenu(javafx.scene.input.MouseEvent mouseEvent) {
        bp.setCenter(ap);
    }
    @FXML
    public void clickOnEmployeeMenu(javafx.scene.input.MouseEvent mouseEvent) {
        loadPage("employee");
    }
    @FXML
    public void clickOnPatientMenu(javafx.scene.input.MouseEvent mouseEvent) {
        loadPage("patient");
    }
    private void loadPage(String page){
        Parent content=null;
        try {
            content = FXMLLoader.load(getClass().getResource("/com/plms/design/"+page+".fxml"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        bp.setCenter(content);
    }
    public void userLogOut(ActionEvent event) throws IOException {
        LoginController m = new LoginController();
        //m.changeScene("login-view.fxml");

    }

    public void getUserProfileName(String username){
        System.out.println(username);
        //lblUserProfile.setStyle("-fx-text-fill:White; -fx-font-size: 18;");
        lblUserProfile.setText(username.toString());
    }
}
