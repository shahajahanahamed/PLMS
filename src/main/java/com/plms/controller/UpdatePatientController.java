package com.plms.controller;

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
import java.util.ResourceBundle;

public class UpdatePatientController implements Initializable {
    @FXML
    private Button PtnClearBtn, ptnBackBtn, ptnUpdateBtn;
    @FXML
    private FontAwesomeIcon clearbtn;
    @FXML
    private TextArea ptntAddressTA;

    @FXML
    private TextField ptntAgeTB, ptntContactTB, ptntIDTB, ptntNameTB;

    @FXML
    private DatePicker ptntCollectedOnDP;

    @FXML
    private ComboBox<String> ptntTestTypeCB;

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
    void clickOnClearBtn(MouseEvent event) {
        clearAllFields();
    }

    @FXML
    void clickOnBackBtn(MouseEvent event) {
        Stage stg = (Stage) ptnBackBtn.getScene().getWindow();
        stg.close();
    }

    @FXML
    void clickOnUpdateButton(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //loadDataIntoScene(getEmp());
        //setValuesToTypeComboBox();
    }

    @FXML
    private void clickOnUpdateButton() {
    }

    public void loadDataIntoScene(Patient ptn) {
        ptntIDTB.setText(String.valueOf(ptn.getPtnId()));
        ptntNameTB.setText(ptn.getPtnName());
        ptntAgeTB.setText(ptn.getAge());
        ptntContactTB.setText(ptn.getPtnContact());
        ptntTestTypeCB.setValue(ptn.getTestType());
        ptntCollectedOnDP.setValue(LocalDate.parse(ptn.getPtnTestCollectedDate()));
        ptntAddressTA.setText(ptn.getPtnAddress());
    }

    public void clearAllFields() {
        ptntNameTB.clear();
        ptntTestTypeCB.getSelectionModel().clearSelection();
        ptntTestTypeCB.setPromptText("Type");
        ptntAgeTB.clear();
        ptntContactTB.clear();
        ptntAddressTA.clear();
        ptntCollectedOnDP.setValue(LocalDate.now());

    }

    /*
    public void setValuesToTypeComboBox() {
        ObservableList<String> empTypes = FXCollections.observableArrayList("Admin", "Receptionist", "Lab Technician", "Others");
      //  typeCB.setItems(empTypes);
    }

    */
}
