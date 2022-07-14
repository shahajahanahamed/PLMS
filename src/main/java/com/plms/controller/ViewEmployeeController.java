package com.plms.controller;

import com.plms.entities.Employee;
import com.plms.modules.SceneLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewEmployeeController implements Initializable {

    @FXML
    private Button createNewBtn;

    @FXML
    private ComboBox<String> filterComboBox;

    @FXML
    private TextField searchBox;
    @FXML
    private TableView<Employee> employeeTV;



    @FXML
    private TableColumn<Employee, String> idCol;
    @FXML
    private TableColumn<Employee, String> nameCol;
    @FXML
    private TableColumn<Employee, String> usernameCol;
    @FXML
    private TableColumn<Employee, String> typeCol;
    @FXML
    private TableColumn<Employee, String> contactCol;
    @FXML
    private TableColumn<Employee, String> emailCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }



    @FXML
    void clickOnCreateNewButton(MouseEvent event) throws IOException {
        new SceneLoader().loadSceneInDifferentStage(getClass(),"AddEmployeePage");
    }

    public void loadData() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("empId"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("empName"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("userType"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("emailId"));
    }
}
