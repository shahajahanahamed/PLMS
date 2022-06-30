package com.plms.modules;

//import com.sun.javafx.tk.quantum.PaintRenderJob;
import com.plms.controller.AfterLogin;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginModule extends Application {
    private static Stage stg;
    @Override
    public void start(Stage primaryStage) throws IOException {
        stg=primaryStage;
        primaryStage.setResizable(false);
        FXMLLoader fxmlLoader = new FXMLLoader(LoginModule.class.getResource("/com/plms/design/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        primaryStage.setTitle("Hello!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(AfterLogin.class.getResource(fxml));

        stg.getScene().setRoot(pane);
    }
    public static void main(String[] args) {
        launch();
    }
}