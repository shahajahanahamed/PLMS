package com.plms.modules;

//import com.sun.javafx.tk.quantum.PaintRenderJob;
import com.plms.controller.Homepage;
import com.plms.controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static Stage stage;
    @Override
    public void start(Stage primaryStage) throws IOException {
        stage=primaryStage;
        primaryStage.setResizable(false);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/com/plms/design/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setTitle("PLMS");
        primaryStage.setScene(scene);
        primaryStage.show();

        LoginController.setStage(stage);
    }
    public static void main(String[] args) {
        launch();
    }
}