package com.plms.modules;

import com.plms.controller.Homepage;
import com.plms.controller.LoginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneLoader {
    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        SceneLoader.stage = stage;
    }
    /*
    * For loading any scene, just call the method loadScene and pass the viewName, and the controller class
    *
    * */
    public void loadScene(Class controllerName,String viewName) throws IOException {
        /*Parent root;
        root = FXMLLoader.load(controllerName.getResource("/com/plms/design/"+viewName+".fxml"));
        stage.getScene().setRoot(root);
        stage.show();*/

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/plms/design/"+viewName+".fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        //stage.setTitle("PLMS");
        stage.setScene(scene);
        stage.show();
    }
}
