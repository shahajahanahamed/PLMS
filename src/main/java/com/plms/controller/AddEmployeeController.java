package com.plms.controller;

import com.plms.dao.EmployeeDao;
import com.plms.entities.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddEmployeeController implements Initializable {
    @FXML
    private Button addBtn, backBtn, clearBtn;
    @FXML
    private TextArea addressTA;
    @FXML
    private TextField fullnameTB, usernameTB, contactTB, emailTB;
    @FXML
    private DatePicker dobDP;
    @FXML
    private ComboBox<String> genderCB, typeCB;
    @FXML
    private PasswordField passwordTB;
    @FXML
    private Label validationLbl;
    private int empId;
    private String empFullname, username, password, userType, dob, gender, contactNo, emailId, address;
    boolean validate = false;
    boolean fullnameV,usernameV,passwordV,usertypeV,dobV,genderV,contactV,emailV,addressV;
    protected
    String successMessage = String.format("-fx-text-fill: GREEN;");
    String errorMessage = String.format("-fx-text-fill: RED;");
    String errorStyle = String.format("-fx-border-color: RED; -fx-border-width: 2; -fx-border-radius: 30;");
    String successStyle = String.format("-fx-border-color: #A9A9A9; -fx-border-width: 2; -fx-border-radius: 5;");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setValuesToTypeComboBox();
        setValuesToGenderComboBox();
    }

    //Adding employee types to the Type Combobox
    public void setValuesToTypeComboBox() {
        ObservableList<String> employeeTypes = FXCollections.observableArrayList("Admin", "Receptionist", "Lab Technician", "Others");
        typeCB.getItems().setAll(employeeTypes);
    }

    public void setValuesToGenderComboBox() {
        ObservableList<String> gender = FXCollections.observableArrayList("Male", "Female", "Others");
        genderCB.getItems().setAll(gender);
    }

    @FXML
    void clickOnAddButton(MouseEvent event) {
        validate = checkValidation();
        if (validate) {
            validationLbl.setText("Please insert proper data");
        } else {
            setEmployee();
            clearAllFields();
        }
    }
    @FXML
    void clickOnAddIcon(MouseEvent event) {
        clickOnAddButton(event);
    }
    @FXML
    void clickOnClearBtn(MouseEvent event) {
        clearAllFields();
    }
    @FXML
    void clickOnClearIcon(MouseEvent event) {
        clickOnClearBtn(event);
    }
    @FXML
    void clickOnBackBtn(MouseEvent event) {
        Stage stage=(Stage) backBtn.getScene().getWindow();
        stage.close();
    }
    @FXML
    void clickOnBackIcon(MouseEvent event) {
        clickOnBackBtn(event);
    }
    public void clearAllFields() {
        fullnameTB.clear();
        usernameTB.clear();
        passwordTB.clear();
        //typeCB.setPromptText(typeCB.getPromptText());
        typeCB.getSelectionModel().clearSelection();
        typeCB.setPromptText("Type");
        dobDP.setPromptText(dobDP.getPromptText());
        genderCB.getSelectionModel().clearSelection();
        contactTB.clear();
        emailTB.clear();
        addressTA.clear();
    }

    private boolean checkValidation() {

        //We need to check the validation of all the fields
            empFullname = fullnameTB.getText();
            username = usernameTB.getText();
            password = passwordTB.getText();
            userType = typeCB.getValue().toString();
            dob = dobDP.getValue().toString();
            gender = genderCB.getValue().toString();
            contactNo = contactTB.getText();
            emailId = emailTB.getText();
            address = addressTA.getText();

        return false;
    }

    private void setEmployee() {
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

        insertingData(employee);
    }

    private void insertingData(Employee employee) {
        EmployeeDao empDao = new EmployeeDao();
        int result = empDao.insertData(employee);
        if (result == 1) {
            validationLbl.setText("Employee Added Successfully");
            validationLbl.setStyle(successMessage);
        } else {
            validationLbl.setText("Employee Addition Failed");
            validationLbl.setStyle(errorMessage);
        }
    }


}
