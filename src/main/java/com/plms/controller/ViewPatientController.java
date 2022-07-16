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
    private TableColumn<Patient, String> testType;
    @FXML
    private TableColumn<Employee, String> contactCol;
    @FXML
    private TableColumn<Employee, String> emailCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDataIntoTable();
    }
    @FXML
    void clickOnCreateNewButton(MouseEvent event) throws IOException {
        //Add patient page instead of AddEmployeePage
        //new SceneLoader().loadSceneInDifferentStage(getClass(),"AddEmployeePage");
    }

    public void loadDataIntoTable() {
        List<Patient> patientList = new PatientDao().getAllPatientShortDetails();
        idCol.setCellValueFactory(new PropertyValueFactory<>("ptnId"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("ptnName"));
        testType.setCellValueFactory(new PropertyValueFactory<>("testType"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("emailId"));

        ObservableList<Patient> patients = FXCollections.observableArrayList(patientList);
        patientTV.setItems(patients);
    }
}
