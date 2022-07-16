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

public class AddPatientController implements Initializable {
    @FXML
    private TextField PatnFullNameTB;

    @FXML
    private PasswordField PatnIdPB;

    @FXML
    private Button PatnaddBtn;

    @FXML
    private TextArea PatnaddressTA;

    @FXML
    private Button PatnbackBtn;

    @FXML
    private Button PatnclearBtn;

    @FXML
    private TextField PatncontactTB;

    @FXML
    private DatePicker PatndobDP;

    @FXML
    private TextField PatnemailTB;

    @FXML
    private ComboBox<String> PatngenderCB;

    @FXML
    private ComboBox<String> TestTypeCB;

    @FXML
    private Label validationLbl;

    protected
    String successMessage = String.format("-fx-text-fill: GREEN;");
    String errorMessage = String.format("-fx-text-fill: RED;");
    String errorStyle = String.format("-fx-border-color: RED; -fx-border-width: 2; -fx-border-radius: 5;");
    String successStyle = String.format("-fx-border-color: #A9A9A9; -fx-border-width: 2; -fx-border-radius: 5;");
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setValuesToTypeComboBox();
        setValuesToGenderComboBox();
    }

    //Adding employee types to the Type Combobox
    public void setValuesToTypeComboBox(){
        ObservableList<String> employeeTypes = FXCollections.observableArrayList("Blood Test","Sugar Test","Urine Test","Others");
        TestTypeCB.getItems().setAll(employeeTypes);
    }
    public void setValuesToGenderComboBox(){
        ObservableList<String> gender = FXCollections.observableArrayList("Male","Female","Others");
        PatngenderCB.getItems().setAll(gender);
    }

    @FXML
    void clickOnAddButton(MouseEvent event) {
        addEmployeeDetails();
        clearAllFields();
    }

    @FXML
    void clickOnClearBtn(MouseEvent event) {
        clearAllFields();
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
            validationLbl.setStyle(successMessage);
        }else {
            validationLbl.setText("Employee Addition Failed");
            validationLbl.setStyle(errorMessage);
        }
    }

    public void clearAllFields(){
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

}
