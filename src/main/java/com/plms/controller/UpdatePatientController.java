package com.plms.controller;

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

public class UpdatePatientController implements Initializable {
    @FXML
    private Button ptnUpdateBtn,ptnBackBtn,PtnClearBtn;
    @FXML
    private TextArea PatnaddressTA;
    @FXML
    private TextField ptnNameTB,PatnAgeTB,PatncontactTB;
    @FXML
    private ComboBox<String> PatngenderCB,TestTypeCB;

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
        ptnNameTB.setText(ptn.getPtnName());
        PatnAgeTB.setText(ptn.getAge());
        PatncontactTB.setText(ptn.getPtnContact());
        TestTypeCB.setValue(ptn.getTestType());
        PatngenderCB.setValue(ptn.getPtnGender());
        PatnaddressTA.setText(ptn.getPtnAddress());
    }
    /*
    public void setValuesToTypeComboBox() {
        ObservableList<String> empTypes = FXCollections.observableArrayList("Admin", "Receptionist", "Lab Technician", "Others");
      //  typeCB.setItems(empTypes);
    }

    */
}
