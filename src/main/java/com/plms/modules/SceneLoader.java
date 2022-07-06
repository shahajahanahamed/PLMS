package com.plms.modules;

import com.plms.controller.Homepage;
import com.plms.controller.LoginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneLoader {
    private static Stage primaryStage;

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void setPrimaryStage(Stage primaryStage) {
        SceneLoader.primaryStage = primaryStage;
    }

    /*public void loadScene(String viewName){
        primaryStage.setResizable(false);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/plms/design"+viewName+".fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/

    /*public void loadScene1(String controllerName,String fxml){
        Parent pane;
        try {
            url = controllerName+".class"+".getResource("'/com/plms/design'"+viewName+"'.fxml")"
            pane = FXMLLoader.load(controllerName+".class.getResource(fxml));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.getScene().setRoot(pane);
    }*/
}
