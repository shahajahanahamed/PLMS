package com.plms.modules;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

public class Main extends Application {
    private Stage stage;
    @Override
    public void start(Stage primaryStage) throws IOException {
        stage=primaryStage;
        primaryStage.setResizable(false);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/plms/views/LoginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());
        stage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("PLMS");
        primaryStage.setScene(scene);
        primaryStage.show();

        SceneLoader.setStage(stage);
    }
    public static void main(String[] args) {
        launch();
    } //....
}