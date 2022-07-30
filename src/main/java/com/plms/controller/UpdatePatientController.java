package com.plms.controller;

import com.plms.dao.EmployeeDao;
import com.plms.dao.PatientDao;
import com.plms.entities.Employee;
import com.plms.entities.Patient;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;

public class UpdatePatientController implements Initializable {
    @FXML
    private Button backBtn, clearBtn, updateBtn;
    @FXML
    private FontAwesomeIcon backIcon, clearIcon, updateIcon;
    @FXML
    private TextArea ptntAddressTA;
    @FXML
    private TextField ptntAgeTB, ptntContactTB, ptntIDTB, ptntNameTB;

    @FXML
    private DatePicker ptntCollectedOnDP;

    @FXML
    private ComboBox<String> testTypeCB;
    @FXML
    private Button closeBtn, minimizeBtn;
    @FXML
    private FontAwesomeIcon closeIcon, minimizeIcon;
    @FXML
    private Label validationLbl;

    private Patient ptn;

    public Patient getPtn() {
        return ptn;
    }

    public void setPtn(Patient ptn) {
        this.ptn = ptn;
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
    void clickOnClearBtn(MouseEvent event) {
        clearAllFields();
    }

    @FXML
    void clickOnClearIcon(MouseEvent event) {
        clickOnClearBtn(event);
    }

    @FXML
    void clickOnBackBtn(MouseEvent event) {
        Stage stg = (Stage) backBtn.getScene().getWindow();
        stg.close();
    }

    @FXML
    void clickOnBackIcon(MouseEvent event) {
        clickOnBackBtn(event);
    }

    @FXML
    void clickOnUpdateBtn(MouseEvent event) {
        Patient ptnt = getPatientDetails();
        PatientDao dao = new PatientDao();
        int result = dao.updatePatient(ptnt);
        Stage stg = (Stage) updateBtn.getScene().getWindow();
        stg.close();
    }

    private Patient getPatientDetails() {
        Patient ptnt = new Patient();
        ptnt.setPtnId(Integer.parseInt(ptntIDTB.getText()));
        ptnt.setPtnName(ptntNameTB.getText());
        ptnt.setTestType(testTypeCB.getValue());
        ptnt.setAge(ptntAgeTB.getText());
        ptnt.setPtnContact(ptntContactTB.getText());
        ptnt.setPtnTestCollectedDate(String.valueOf(ptntCollectedOnDP.getValue()));
        ptnt.setPtnAddress(ptntAddressTA.getText());
        return ptnt;
    }

    @FXML
    void clickOnUpdateIcon(MouseEvent event) {
        clickOnUpdateBtn(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setValuesToTestTypeComboBox();
    }

    private void setValuesToTestTypeComboBox() {
        HashSet<String> testTypeList = new HashSet<String>();
        List<Patient> patientList = new PatientDao().getAllPatientShortDetails();
        for (int i = 0; i < patientList.size(); i++) {
            Patient ptnt = (Patient) patientList.get(i);
            testTypeList.add(ptnt.getTestType());
        }
        ObservableList<String> testTypes = FXCollections.observableArrayList(testTypeList);
        testTypeCB.setItems(testTypes);
    }

    public void loadDataIntoScene(Patient ptn) {
        ptntIDTB.setText(String.valueOf(ptn.getPtnId()));
        ptntNameTB.setText(ptn.getPtnName());
        ptntAgeTB.setText(ptn.getAge());
        ptntContactTB.setText(ptn.getPtnContact());
        testTypeCB.setValue(ptn.getTestType());
        ptntCollectedOnDP.setValue(LocalDate.parse(ptn.getPtnTestCollectedDate()));
        ptntAddressTA.setText(ptn.getPtnAddress());
    }

    public void clearAllFields() {
        ptntNameTB.clear();
        testTypeCB.getSelectionModel().clearSelection();
        ptntAgeTB.clear();
        ptntContactTB.clear();
        ptntAddressTA.clear();
        ptntCollectedOnDP.setValue(LocalDate.now());

    }

}
