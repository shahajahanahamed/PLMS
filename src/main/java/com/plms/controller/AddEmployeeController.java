package com.plms.controller;

import com.plms.dao.EmployeeDao;
import com.plms.entities.Employee;
import javafx.animation.Animation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
    protected
    String successMessage = String.format("-fx-text-fill: GREEN;");
    String errorMessage = String.format("-fx-text-fill: RED;");
    String errorStyle = String.format("-fx-border-color: RED; -fx-border-width: 2; -fx-border-radius: 30;-fx-text-fill: RED;-fx-font-size: 14px;");
    String successStyle = String.format("-fx-border-color: #A9A9A9; -fx-border-width: 2; -fx-border-radius: 5;-fx-text-fill: White;-fx-font-size: 14px;");

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
        if(validate){
            validationLbl.setText("Please insert proper data");
        }else{
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
        genderCB.setPromptText(genderCB.getPromptText());
        contactTB.clear();
        emailTB.clear();
        addressTA.clear();
    }

    private boolean checkValidation(){
        //Getting values from fields
        if (fullnameTB.getText().isBlank()) {
            fullnameTB.setText("Full name cannot be blank");
            fullnameTB.setStyle(errorStyle);
            validate = true;
        } else {
            empFullname = fullnameTB.getText();
        }
        if (usernameTB.getText().isBlank()) {
            usernameTB.setText("Username cannot be blank");
            usernameTB.setStyle(errorStyle);
            validate = true;
        } else {
            username = usernameTB.getText();
        }
        if(passwordTB.getText().isBlank()) {
            passwordTB.setText("Password");
            passwordTB.setStyle(errorStyle);
            validate = true;
        }else {
            password = passwordTB.getText();
        }
        if(typeCB.getValue()==null){
            typeCB.setValue("Select user type");
            typeCB.setStyle(errorStyle);
            validate = true;
        }else {
            userType = typeCB.getValue().toString();
        }
        if(dobDP.getValue()==null){
            dobDP.setPromptText("Choose date of birth");
            dobDP.setStyle(errorStyle);
            validate = true;
        }else {
            dob = dobDP.getValue().toString();
        }
        if(genderCB.getValue()==null){
            genderCB.setValue("Select gender");
            genderCB.setStyle(errorStyle);
            validate = true;
        }else {
            gender = genderCB.getValue().toString();
        }
        if(contactTB.getText().isBlank()){
            contactTB.setText("Contact number cannot be blank");
            contactTB.setStyle(errorStyle);
            validate = true;
        }else {
            contactNo = contactTB.getText();
            for(int i=0;i<contactNo.length();i++){
                if(contactNo.charAt(i)==)
            }
        }

        emailId = emailTB.getText();
        address = addressTA.getText();

        return validate;
    }

    private void setEmployee(){
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

    private void insertingData(Employee employee){
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
