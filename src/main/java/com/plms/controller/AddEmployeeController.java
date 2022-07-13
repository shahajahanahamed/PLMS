package com.plms.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.plms.dao.EmployeeDao;
import com.plms.entities.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class AddEmployeeController implements Initializable {
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
    private DatePicker dobDP;

    @FXML
    private TextField emailTB;

    @FXML
    private TextField fullnameTB;

    @FXML
    private ComboBox<String> genderCB;

    @FXML
    private PasswordField passwordTB;

    @FXML
    private ComboBox<String> typeCB;

    @FXML
    private TextField usernameTB;
    @FXML
    private Label validationLbl;

    private int empId;
    private String empFullname;
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
        setValuesToTypeComboBox();
        setValuesToGenderComboBox();
    }

    //Adding employee types to the Type Combobox
    public void setValuesToTypeComboBox(){
        ObservableList<String> employeeTypes = FXCollections.observableArrayList("Admin","Receptionist","Lab Technician","Others");
        typeCB.getItems().setAll(employeeTypes);
    }
    public void setValuesToGenderComboBox(){
        ObservableList<String> gender = FXCollections.observableArrayList("Male","Female","Others");
        genderCB.getItems().setAll(gender);
    }

    @FXML
    void clickOnAddButton(MouseEvent event) {
        addEmployeeDetails();
    }
    public void addEmployeeDetails(){
        //Getting values from fields
        empFullname = fullnameTB.getText();
        username = usernameTB.getText();
        password = passwordTB.getText();
        userType = typeCB.getValue();
        dob = dobDP.getValue().toString();
        gender = genderCB.getValue();
        contactNo = contactTB.getText();
        emailId = emailTB.getText();
        address = addressTA.getText();

        //adding to the employee object
        Employee employee = new Employee();
        employee.setEmpName(empFullname);
        employee.setUsername(username);
        employee.setPassword(password);
        employee.setUserType(userType);
        employee.setDob(dob);
        employee.setGender(gender);
        employee.setContactNo(contactNo);
        employee.setEmailId(emailId);
        employee.setAddress(address);

        //
        EmployeeDao empDao = new EmployeeDao();
        int result = empDao.insertData(employee);
        if(result==1){
            validationLbl.setText("Employee Added Successfully");
        }else {
            validationLbl.setText("Employee Addition Failed");
        }
    }
}
