package com.plms.controller;

import com.plms.dao.EmployeeDao;
import com.plms.dao.PatientDao;
import com.plms.dao.TestDao;
import com.plms.entities.Employee;
import com.plms.entities.Patient;
import com.plms.entities.Test;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;

public class AddPatientController implements Initializable {
    @FXML
    private FontAwesomeIcon clearbtn;
    @FXML
    private DatePicker collectionDateDP;
    @FXML
    private Button ptntAddBtn, ptntBackBtn, ptntClearBtn;
    @FXML
    private TextArea ptntAddressTA;
    @FXML
    private Button closeBtn, minimizeBtn,leftArrowBtn,rightArrowBtn;
    @FXML
    private FontAwesomeIcon closeIcon, minimizeIcon,leftArrowIcon,rightArrowIcon;
    @FXML
    private TextField ptntAgeTB, ptntContactTB, ptntNameTB;
    @FXML
    private ComboBox<String> ptntGenderCB, testTypeCB;
    @FXML
    private ListView<String> availableTestLV, selectedTestLV;
    @FXML
    private Label validationLbl;
    private int ptnId;
    protected
    String successMessage = String.format("-fx-text-fill: GREEN;");
    String errorMessage = String.format("-fx-text-fill: RED;");
    String errorStyle = String.format("-fx-border-color: RED; -fx-border-width: 2; -fx-border-radius: 5;");
    String successStyle = String.format("-fx-border-color: #A9A9A9; -fx-border-width: 2; -fx-border-radius: 5;");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setValuesToTestTypeComboBox();
        setValuesToGenderComboBox();
        setValuesToAvailabeList();
        availableTestLV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        selectedTestLV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    private void setValuesToAvailabeList() {
        HashSet<String> testNameList = new HashSet<String>();
        List<Test> testList = new TestDao().getAllTestShortDetails();
        for (int i = 0; i < testList.size(); i++) {
            Test tst = (Test) testList.get(i);
            testNameList.add(tst.getTestName());
        }
        ObservableList<String> allTests = FXCollections.observableArrayList(testNameList);
        availableTestLV.setItems(allTests);
    }

    @FXML
    void clickOnCloseBtn(MouseEvent event) {
        Stage stage1 = (Stage) closeBtn.getScene().getWindow();
        stage1.close();
    }

    @FXML
    void clickOnCloseIcon(MouseEvent event) {
        clickOnCloseBtn(event);
    }

    @FXML
    void clickOnMinimizeBtn(MouseEvent event) {
        Stage stage1 = (Stage) closeBtn.getScene().getWindow();
        stage1.setIconified(true);
    }

    @FXML
    void clickOnMinimizeIcon(MouseEvent event) {
        clickOnMinimizeBtn(event);
    }



    @FXML
    void clickOnLeftArrowBtn(MouseEvent event) {

    }

    @FXML
    void clickOnLeftArrowIcon(MouseEvent event) {
        clickOnLeftArrowBtn(event);
    }
    HashSet<String> testNameLists = new HashSet<String>();
    @FXML
    void clickOnRightArrowBtn(MouseEvent event) {
        //testNameList=availableTestLV.getSelectionModel().getSelectedItems();
        testNameLists.addAll(availableTestLV.getSelectionModel().getSelectedItems());
        ObservableList<String> selectedTests = FXCollections.observableArrayList(testNameLists);
        selectedTestLV.setItems(selectedTests);
    }

    @FXML
    void clickOnRightArrowIcon(MouseEvent event) {
        clickOnRightArrowBtn(event);
    }

    @FXML
    void clickOnAddBtn(MouseEvent event) {
        addPatientDetails();
        clearAllFields();
    }

    @FXML
    void clickOnAddIcon(MouseEvent event) {
        clickOnAddBtn(event);
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
        Stage stage = (Stage) ptntBackBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    void clickOnBackIcon(MouseEvent event) {
        clickOnBackBtn(event);
    }

    //Adding Test types to the Type Combo box
    public void setValuesToTestTypeComboBox() {
        HashSet<String> testTypeList = new HashSet<String>();
        List<Test> testList = new TestDao().getAllTestShortDetails();
        for (int i = 0; i < testList.size(); i++) {
            Test tst = (Test) testList.get(i);
            testTypeList.add(tst.getTestName());
        }
        ObservableList<String> testTypes = FXCollections.observableArrayList(testTypeList);
        testTypeCB.setItems(testTypes);
    }

    public void setValuesToGenderComboBox() {
        ObservableList<String> gender = FXCollections.observableArrayList("Male", "Female", "Others");
        ptntGenderCB.getItems().setAll(gender);
    }

    @FXML

    public void addPatientDetails() {
        //Getting values from fields
        String ptnName = ptntNameTB.getText();
        String testType = testTypeCB.getValue();
        String ptnAge = ptntAgeTB.getText();
        String gender = ptntGenderCB.getValue();
        String contactNo = ptntContactTB.getText();
        String address = ptntAddressTA.getText();
        String CollectedOn = collectionDateDP.getValue().toString();

        //adding to the employee object
        Patient ptnt = new Patient();
        ptnt.setPtnName(ptnName);
        ptnt.setTestType(testType);
        ptnt.setAge(ptnAge);
        ptnt.setPtnGender(gender);
        ptnt.setPtnContact(contactNo);
        ptnt.setPtnAddress(address);
        ptnt.setPtnTestCollectedDate(CollectedOn);

        //
        PatientDao pDao = new PatientDao();
        int result = pDao.insertData(ptnt);
        if (result == 1) {
            validationLbl.setText("Patient Added Successfully");
            validationLbl.setStyle(successMessage);
        } else {
            validationLbl.setText("Patient Addition Failed");
            validationLbl.setStyle(errorMessage);
        }
    }

    public void clearAllFields() {
        ptntNameTB.clear();
        //typeCB.setPromptText(typeCB.getPromptText());
        testTypeCB.getSelectionModel().clearSelection();
        testTypeCB.setPromptText("Type");
        ptntAgeTB.clear();
        ptntGenderCB.getSelectionModel().clearSelection();
        ptntContactTB.clear();
        ptntAddressTA.clear();
        collectionDateDP.setPromptText(collectionDateDP.getPromptText());

    }


}
