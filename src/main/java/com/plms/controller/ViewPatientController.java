package com.plms.controller;

import com.plms.dao.EmployeeDao;
import com.plms.dao.PatientDao;
import com.plms.entities.Employee;
import com.plms.entities.Patient;
import com.plms.modules.SceneLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ViewPatientController implements Initializable {
    @FXML
    private Button createNewBtn;
    @FXML
    private ComboBox<String> filterComboBox;
    @FXML
    private TextField searchBox;
    @FXML
    private TableView<Patient> patientTV;
    @FXML
    private TableColumn<Patient, String> idCol;
    @FXML
    private TableColumn<Patient, String> nameCol;
    @FXML
    private TableColumn<Patient, String> TestTypeCol;
    @FXML
    private TableColumn<Patient, String> DOBCol;
    @FXML
    private TableColumn<Patient, String> ContactCol;
    @FXML
    private TableColumn<Patient, String> EmailCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDataIntoTable();
    }
    @FXML
    void clickOnCreateNewButton(MouseEvent event) throws IOException {
        //Add patient page instead of AddEmployeePage
        new SceneLoader().loadSceneInDifferentStage(getClass(),"AddPatientPage");
    }

    public void loadDataIntoTable() {
        List<Patient> patientList = new PatientDao().getAllPatientShortDetails();
        idCol.setCellValueFactory(new PropertyValueFactory<>("pateintId"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        TestTypeCol.setCellValueFactory(new PropertyValueFactory<>("testType"));
        DOBCol.setCellValueFactory(new PropertyValueFactory<>("dob"));
        ContactCol.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        EmailCol.setCellValueFactory(new PropertyValueFactory<>("emailId"));
        ObservableList<Patient> patients = FXCollections.observableArrayList(patientList);
        patientTV.setItems(patients);
    }
}
