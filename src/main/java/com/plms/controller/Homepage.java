package com.plms.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;

public class Homepage {
    @FXML
    private Button logout;
    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;

    @FXML
    private Button homeBtn;


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


}
