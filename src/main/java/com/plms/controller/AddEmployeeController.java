package com.plms.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;

import java.net.URL;
import java.util.ResourceBundle;

public class AddEmployeeController implements Initializable {
    @FXML
    private JFXTextArea addressTA;
    @FXML
    private JFXTextField contactTB;
    @FXML
    private DatePicker dobDP;
    @FXML
    private JFXTextField emailTB;
    @FXML
    private JFXTextField fullnameTB;
    @FXML
    private JFXComboBox<String> genderCB;
    @FXML
    private JFXPasswordField passwordTB;
    @FXML
    private JFXComboBox<String> typeCB;
    @FXML
    private JFXTextField usernameTB;

    private int empId;
    private String empName;
    private String username;
    private String password;
    private String userType;
    private String dob;
    private String gender;
    private String contactNo;
    private String emailId;
    private String address;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*empName = fullnameTB.getText();
        username=usernameTB.getText();
        password = passwordTB.getText();
        userType = typeCB.getValue();
        gender = genderCB.getValue();
        contactNo = contactTB.getText();
        emailId = emailTB.getText();
        address = addressTA.getText();*/

    }
}
