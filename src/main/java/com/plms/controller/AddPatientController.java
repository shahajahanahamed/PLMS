package com.plms.controller;

import com.plms.dao.EmployeeDao;
import com.plms.dao.PatientDao;
import com.plms.entities.Employee;
import com.plms.entities.Patient;
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
    private TextField ptnNameTB;

    @FXML
    private Button ptnAddBtn;

    @FXML
    private TextArea ptnAddressTA;

    @FXML
    private Button ptnBackBtn;

    @FXML
    private Button PtnClearBtn;

    @FXML
    private TextField ptnContactTB;

    @FXML
    private DatePicker ptnDobDP;

    @FXML
    private TextField ptnEmailTB;

    @FXML
    private ComboBox<String> ptnGenderCB;

    @FXML
    private ComboBox<String> testTypeCB;

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

    //Adding Test types to the Type Combo box
    public void setValuesToTypeComboBox(){
        ObservableList<String> testTypeList = FXCollections.observableArrayList("Blood Test","Sugar Test","Urine Test","Others");
        testTypeCB.getItems().setAll(testTypeList);
    }
    public void setValuesToGenderComboBox(){
        ObservableList<String> gender = FXCollections.observableArrayList("Male","Female","Others");
        ptnGenderCB.getItems().setAll(gender);
    }
    @FXML
    void clickOnAddButton(MouseEvent event) {
        addPatientDetails();
        clearAllFields();
    }
    @FXML
    void clickOnClearBtn(MouseEvent event) {
        clearAllFields();
    }
    public void addPatientDetails(){
        //Getting values from fields
        String patientName = ptnNameTB.getText();
        String testType = testTypeCB.getValue();
        String dob = ptnDobDP.getValue().toString();
        String gender = ptnGenderCB.getValue();
        String contactNo = ptnContactTB.getText();
        String emailId = ptnEmailTB.getText();
        String address = ptnAddressTA.getText();

        //adding to the employee object
        Patient ptnt = new Patient();
        ptnt.setPatientName(patientName);
        ptnt.setTestType(testType);
        ptnt.setDob(dob);
        ptnt.setGender(gender);
        ptnt.setContactNo(contactNo);
        ptnt.setEmailId(emailId);
        ptnt.setAddress(address);

        //
        PatientDao pDao = new PatientDao();
        int result = pDao.insertData(ptnt);
        if(result==1){
            validationLbl.setText("Patient Added Successfully");
            validationLbl.setStyle(successMessage);
        }else {
            validationLbl.setText("Patient Addition Failed");
            validationLbl.setStyle(errorMessage);
        }
    }

    public void clearAllFields(){
        ptnNameTB.clear();
        //typeCB.setPromptText(typeCB.getPromptText());
        testTypeCB.getSelectionModel().clearSelection();
        testTypeCB.setPromptText("Type");
        ptnDobDP.setPromptText(ptnDobDP.getPromptText());
        ptnGenderCB.setPromptText(ptnGenderCB.getPromptText());
        ptnContactTB.clear();
        ptnEmailTB.clear();
        ptnAddressTA.clear();
    }
}
