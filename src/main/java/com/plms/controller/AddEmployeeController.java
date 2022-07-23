package com.plms.controller;

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
    void clickOnClearBtn(MouseEvent event) {
        clearAllFields();
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

    /*public boolean fullNameValidation() {
        if (fullnameTB.getText().isBlank()) {
            validate=true;
            fullnameTB.setStyle(errorStyle);
            fullnameTB.setStyle(errorMessage);
            fullnameTB.setText("Fullname cannot be blank");
        }
        return validate;
    }

    public boolean usernameValidation() {
        if (usernameTB.getText().isBlank()) {
            validate=true;
            usernameTB.setStyle(errorStyle);
            usernameTB.setStyle(errorMessage);
            usernameTB.setText("Username cannot be blank");
        }
        return validate;
    }*/
        /*// In case the Username and Password fields are left blank then display the error message
        if (usernameTextField.getText().isBlank() || userPassword.getText().isBlank()) {
            invalidDetails.setStyle(errorMessage);

// When the username and password are blank
            if (usernameTextField.getText().isBlank() || userPassword.getText().isBlank()) {
                invalidDetails.setText("The Login fields are required!");
                usernameTextField.setStyle(errorStyle);
                userPassword.setStyle(errorStyle);


            } else // When only the username is blank
                if (usernameTextField.getText().isBlank()) {
                    usernameTextField.setStyle(errorStyle);
                    invalidDetails.setText("The Username or Email is required!");
                    userPassword.setStyle(successStyle);


                } else // When only the password is blank
                    if (userPassword.getText().isBlank()) {
                        userPassword.setStyle(errorStyle);
                        invalidDetails.setText("The Password is required!");
                        usernameTextField.setStyle(successStyle);


                    }
        } else // Check if password is less than four characters, if so display error message
            if (userPassword.getText().length() < 4) {
                invalidDetails.setText("The Password can't be less than 4 characters!");
                invalidDetails.setStyle(errorMessage);
                userPassword.setStyle(errorStyle);


            }
// If all login details are entered as required then display success message
            else {
                invalidDetails.setText("Login Successful!");
                invalidDetails.setStyle(successMessage);
                usernameTextField.setStyle(successStyle);
                userPassword.setStyle(successStyle);


            }*/

}
