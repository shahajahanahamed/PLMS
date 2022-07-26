package com.plms.controller;

import com.plms.modules.SceneLoader;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginController {
    private String Username;
    @FXML
    private Label valLbl;
    @FXML
    private TextField usernameTB;
    @FXML
    private PasswordField passwordTB;
    @FXML
    private Button closeBtn;

    @FXML
    private FontAwesomeIcon closeIcon;
    @FXML
    private Button minimizeBtn;

    @FXML
    private FontAwesomeIcon minimizeIcon;
    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        LoginController.stage = stage;
    }

    @FXML
    public void doLogin(MouseEvent mouseEvent) {
        userLogin("admin", "123");
    }


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
    public void userLogin(String username, String password) {
        try {
            //if (usernameTB.getText().equals(username) && passwordTB.getText().equals(password))
            if (usernameTB.getText().equals("") && passwordTB.getText().equals("")){
                valLbl.setStyle("fx-background-color:White;-fx-text-fill:Green; -fx-font-size: 14;");
                valLbl.setText("Login Successful");
                //loading scene
                FXMLLoader loader = new SceneLoader().loadScene(getClass(), "Homepage");

                //printing user profile name
                Homepage hpage = loader.getController();
                hpage.printUserProfileName(username);
            } else if (usernameTB.getText().isEmpty() || passwordTB.getText().isEmpty()) {
                valLbl.setStyle("-fx-text-fill:Red; -fx-font-size: 14;");
                valLbl.setText("Please enter Username and Password");
            } else {
                valLbl.setStyle("-fx-text-fill:Red; -fx-font-size: 14;");
                valLbl.setText("Invalid Username or Password");
            }
        } catch (Exception e) {
            System.out.println("Exception Occured: " + e);

        }
    }
    /*public void loadScene(String viewName) throws IOException {
        Parent root;
        root = FXMLLoader.load(getClass().getResource("/com/plms/design/"+viewName+".fxml"));
        stage.getScene().setRoot(root);
        new Homepage().getUserProfileName(username);
        System.out.println(getClass());
    }*/
}