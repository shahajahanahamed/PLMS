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
    private Button updateBtn,backBtn,clearBtn;
    @FXML
    private TextArea addressTA;
    @FXML
    private TextField contactTB,emailTB,fullnameTB,usernameTB;
    @FXML
    private PasswordField passwordTB;
    @FXML
    private ComboBox<String> typeCB;

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
    void clickOnUpdateButton(MouseEvent event) {

    }

    @FXML
    void clickOnClearBtn(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //loadDataIntoScene(getEmp());
        setValuesToTypeComboBox();
    }

    public void loadDataIntoScene(Employee emp) {
        fullnameTB.setText(emp.getEmpName());
        usernameTB.setText(emp.getUsername());
        passwordTB.setText(emp.getPassword());
        typeCB.setValue(emp.getUserType());
        contactTB.setText(emp.getContactNo());
        emailTB.setText(emp.getEmailId());
        addressTA.setText(emp.getAddress());
    }
    public void setValuesToTypeComboBox() {
        ObservableList<String> empTypes = FXCollections.observableArrayList("Admin", "Receptionist", "Lab Technician", "Others");
        typeCB.setItems(empTypes);
    }
}
