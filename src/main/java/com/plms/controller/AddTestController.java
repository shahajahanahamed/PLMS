package com.plms.controller;

import com.plms.dao.EmployeeDao;
import com.plms.dao.TestDao;
import com.plms.entities.Employee;
import com.plms.entities.Test;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddTestController implements Initializable {
    @FXML
    private Button addBtn, backBtn, clearBtn;
    @FXML
    private TextField testnameTB, groupnameTB, unitTB, rangeTB,costTB;

    @FXML
    private Label validationLbl;
    private int empId;
    private String testName, groupName, testUnit, testRange, testCost;
    boolean validate = false;
    //boolean fullnameV,usernameV,passwordV,usertypeV,dobV,genderV,contactV,emailV,addressV;
    protected
    String successMessage = String.format("-fx-text-fill: GREEN;");
    String errorMessage = String.format("-fx-text-fill: RED;");
    String errorStyle = String.format("-fx-border-color: RED; -fx-border-width: 2; -fx-border-radius: 30;");
    String successStyle = String.format("-fx-border-color: #A9A9A9; -fx-border-width: 2; -fx-border-radius: 5;");


    @FXML
    void clickOnAddButton(MouseEvent event) {
        validate = checkValidation();
        if (validate) {
            validationLbl.setText("Please insert proper data");
        } else {
            setTest();
            clearAllFields();
        }
    }
    @FXML
    void clickOnAddIcon(MouseEvent event) {
        clickOnAddButton(event);
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
        Stage stage=(Stage) backBtn.getScene().getWindow();
        stage.close();
    }
    @FXML
    void clickOnBackIcon(MouseEvent event) {
        clickOnBackBtn(event);
    }
    public void clearAllFields() {
        testnameTB.clear();
        groupnameTB.clear();
        unitTB.clear();
        rangeTB.clear();
        costTB.clear();
    }

    private boolean checkValidation() {

        //We need to check the validation of all the fields
            testName = testnameTB.getText();
            groupName = groupnameTB.getText();
            testUnit=unitTB.getText();
            testRange=rangeTB.getText();
            testCost=costTB.getText();
            return false;
    }

    private void setTest() {
        //adding to the employee object
        Test test = new Test();
        test.setTestName(testName);
        test.setGroupName(groupName);
        test.setUnit(testUnit);
        test.setNormalRange(testRange);
        test.setCost(testCost);
        insertingData(test);
    }

    private void insertingData(Test test) {
        TestDao testDao = new TestDao();
        int result = testDao.insertData(test);
        if (result == 1) {
            validationLbl.setText("Test Added Successfully");
            validationLbl.setStyle(successMessage);
        } else {
            validationLbl.setText("Test Addition Failed");
            validationLbl.setStyle(errorMessage);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
