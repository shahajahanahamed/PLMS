package com.plms.controller;

import com.plms.entities.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateEmployeeController implements Initializable {
    @FXML
    private Button addBtn;

    @FXML
    private TextArea addressTA;

    @FXML
    private Button backBtn;

    @FXML
    private Button clearBtn;

    @FXML
    private TextField contactTB;

    @FXML
    private TextField emailTB;

    @FXML
    private TextField fullnameTB;

    @FXML
    private PasswordField passwordTB;

    @FXML
    private ComboBox<?> typeCB;

    @FXML
    private TextField usernameTB;

    @FXML
    private Label validationLbl;

    private Employee emp;

    public Employee getEmp() {
        return emp;
    }

    public void setEmp(Employee emp) {
        this.emp = emp;
    }

    @FXML
    void clickOnAddButton(MouseEvent event) {

    }

    @FXML
    void clickOnClearBtn(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //loadDataIntoScene(getEmp());
    }

    public void loadDataIntoScene(Employee emp) {
        fullnameTB.setText(emp.getEmpName());
        usernameTB.setText(emp.getUsername());
        passwordTB.setText(emp.getPassword());
        typeCB.getItems().indexOf(emp.getUserType());
        contactTB.setText(emp.getContactNo());
        emailTB.setText(emp.getEmailId());
        addressTA.setText(emp.getAddress());
    }

}
