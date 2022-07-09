package com.plms.controller;

import com.plms.modules.SceneLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class ViewEmployeeController {

    @FXML
    private Button createNewBtn;

    @FXML
    private ComboBox<String> filterComboBox;

    @FXML
    private TextField searchBox;

    @FXML
    void clickOnCreateNewButton(MouseEvent event) throws IOException {
        new SceneLoader().loadSceneInDifferentStage("AddEmployeePage");
    }
}
