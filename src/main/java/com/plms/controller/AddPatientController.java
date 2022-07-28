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
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddPatientController implements Initializable {
    @FXML
    private TextField ptnNameTB,ptnContactTB,ptnAgeTB;

    @FXML
    private Button ptnAddBtn,ptnBackBtn,PtnClearBtn;
    @FXML
    private TextArea ptnAddressTA;
    @FXML
    private ComboBox<String> ptnGenderCB,testTypeCB;
    @FXML
    private DatePicker collectionDateDP;
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
        setValuesToTypeComboBox();
        setValuesToGenderComboBox();
    }

    //Adding Test types to the Type Combo box
    public void setValuesToTypeComboBox(){
        ObservableList<String> testTypeList = FXCollections.observableArrayList("ACTH(Adreno Corticotropic Hormone) Test","AFT(Alpha Feto Protein)Test","Albumin Test","Alkaline Phophatase(ALP)Test","Allergy Test","Amylase Test","ANA(Antinuclear Antibody)Test","ANCA Profile","Anti CCP(ACCP)Test","Anti TPO Test","Antibody Test","APTT(Active Partial Thromboplastin Time)Test",
                "ASO Test","Beta HCG Test","Bicarbonate Test","Billirubin Test","Bleeding/Clotting Time Test","Blood Culture Test","Blood Group Test","Blood Sugar Test","Blood Urea Nitrogen Test","CA 15.3 Test","CA 19.9 Test","CA-125(Tumor Marker)Test","Calcium Test","CBC/Hemogram Test","CD4 Test","CEA(Carcinoembryonic Antigen)Test","Chloride Test","Cholesterol Test","CK-MB Test",
                "Cortisol Test","CPK(Creatine Phossphokinase)Test","Creatinine Test","CRP(C-Reactive Protein)Test","Dengue IgG Test","Dengue IgM Test","DHEA Test","Double Marker Test","Electrolytes Test","ESR(Erythrocyte Sedimentation Rate)Test","Estradiol(E2)Test","Ferritin Test","Fluoride Test","FNAC Test","Folic Acid test","FSH(Follicle Stimulating Hormone)Test","G6PD Test",
                "Gamma GT(GGTP)Test","Globulin Test","Glucose Tolerance Test","Hb1AC Test","HBsAg Test","HCV Antibody Test","HDL Cholesterol Test",
                "Hemoglobin(Hb)Test","HGH Test","HIV Test","HLA B27 Test","Homocysteine Test","Insulin Test","Iron Test",
                "Kidney/Renal Function Test","Kidney Profile","LDH(Lactate Dehydrogenase)Test","LDL Cholesterol","LH(Luteinizing Hormone)Test",
                "Lipid Profile","Liver Function Test(LFT)","Mammography");
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
    void clickOnBackBtn(MouseEvent event) {
        Stage stage=(Stage) ptnBackBtn.getScene().getWindow();
        stage.close();
    }
    @FXML
    void clickOnBackIcon(MouseEvent event) {
        clickOnBackBtn(event);
    }
    public void addPatientDetails(){
        //Getting values from fields
        String ptnName = ptnNameTB.getText();
        String testType = testTypeCB.getValue();
        String ptnAge = ptnAgeTB.getText();
        String gender = ptnGenderCB.getValue();
        String contactNo = ptnContactTB.getText();
        String address = ptnAddressTA.getText();
        String CollectedOn=collectionDateDP.getValue().toString();

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
        ptnAgeTB.setPromptText(ptnAgeTB.getPromptText());
        ptnGenderCB.setPromptText(ptnGenderCB.getPromptText());
        ptnContactTB.clear();
        ptnAddressTA.clear();
        collectionDateDP.setPromptText(collectionDateDP.getPromptText());

    }

    public void clickOnUpdateButton(MouseEvent mouseEvent) {
    }
}
