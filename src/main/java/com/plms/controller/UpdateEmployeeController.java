package com.plms.controller;

import com.plms.dao.EmployeeDao;
import com.plms.entities.Employee;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateEmployeeController implements Initializable {
    @FXML
    private Button backBtn,clearBtn,updateBtn;
    @FXML
    private TextArea addressTA;
    @FXML
    private Button closeBtn, minimizeBtn;
    @FXML
    private FontAwesomeIcon closeIcon, minimizeIcon;
    @FXML
    private TextField empIDTB,contactTB,emailTB,fullnameTB,usernameTB;
    @FXML
    private PasswordField passwordTB;
    @FXML
    private ComboBox<String> typeCB;

    @FXML
    private Label validationLbl;

    private Employee emp;

    public Employee getEmp() {
        return emp;
    }

    public void setEmp(Employee emp) {
        this.emp = emp;
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
    void clickOnBackBtn(MouseEvent event) {
        Stage stg = (Stage) backBtn.getScene().getWindow();
        stg.close();
    }
    @FXML
    void clickOnClearIcon(MouseEvent event) {
        clickOnBackBtn(event);
    }
    @FXML
    void clickOnBackIcon(MouseEvent event) {
        clickOnBackBtn(event);
    }

    @FXML
    void clickOnUpdateBtn(MouseEvent event) {
        Employee emp = getEmpDetails();
        EmployeeDao dao =new EmployeeDao();
        int result = dao.updateEmployee(emp);
        Stage stg = (Stage) updateBtn.getScene().getWindow();
        stg.close();
    }
    @FXML
    void clickOnUpdateIcon(MouseEvent event) {
        clickOnUpdateBtn(event);
    }

    private Employee getEmpDetails() {
        Employee emp = new Employee();
        emp.setEmpId(Integer.parseInt(empIDTB.getText()));
        emp.setEmpName(fullnameTB.getText());
        emp.setUsername(usernameTB.getText());
        emp.setPassword(passwordTB.getText());
        emp.setUserType(typeCB.getValue());
        emp.setContactNo(contactTB.getText());
        emp.setEmailId(emailTB.getText());
        emp.setAddress(addressTA.getText());
        return emp;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setValuesToTypeComboBox();
    }

    public void loadDataIntoScene(Employee emp) {
        empIDTB.setText(String.valueOf(emp.getEmpId()));
        fullnameTB.setText(emp.getEmpName());
        usernameTB.setText(emp.getUsername());
        passwordTB.setText(emp.getPassword());
        typeCB.setValue(emp.getUserType());
        contactTB.setText(emp.getContactNo());
        emailTB.setText(emp.getEmailId());
        addressTA.setText(emp.getAddress());
    }
    public void setValuesToTypeComboBox() {
        ObservableList<String> empTypes = FXCollections.observableArrayList("Admin", "Receptionist", "Lab Technician", "Others");
        typeCB.setItems(empTypes);
    }

    public void clearAllFields() {
        fullnameTB.clear();
        usernameTB.clear();
        passwordTB.clear();
        //typeCB.setPromptText(typeCB.getPromptText());
        typeCB.getSelectionModel().clearSelection();
        typeCB.setPromptText("Type");
        contactTB.clear();
        emailTB.clear();
        addressTA.clear();
    }
}
