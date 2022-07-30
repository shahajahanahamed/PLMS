package com.plms.controller;

import com.plms.dao.TestDao;
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
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;

public class UpdateTestController implements Initializable {
    @FXML
    private FontAwesomeIcon updateIcon,backIcon,clearIcon;
    @FXML
    private Button backBtn,clearBtn,updateBtn;
    @FXML
    private Button closeBtn, minimizeBtn;
    @FXML
    private FontAwesomeIcon closeIcon, minimizeIcon;
    @FXML
    private TextField testIDTB,costTB,rangeTB,testNameTB,unitTB;
    @FXML
    private ComboBox<String> groupNameCB;
    @FXML
    private Label validationLbl;
    private Test tst;

    public Test getTst() {
        return tst;
    }

    public void setTst(Test tst) {
        this.tst = tst;
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
        Test test = getTestDetails();
        TestDao dao =new TestDao();
        int result = dao.updateTest(test);
        Stage stg = (Stage) updateBtn.getScene().getWindow();
        stg.close();
    }

    @FXML
    void clickOnUpdateIcon(MouseEvent event) {
        clickOnUpdateBtn(event);
    }
    private Test getTestDetails() {
        Test tst = new Test();
        tst.setTestId(Integer.parseInt(testIDTB.getText()));
        tst.setTestName(testNameTB.getText());
        tst.setGroupName(groupNameCB.getValue());
        tst.setTstUnit(unitTB.getText());
        tst.setNormalRange(rangeTB.getText());
        tst.setCost(costTB.getText());
        return tst;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setValuesToGroupComboBox();
    }

    private void setValuesToGroupComboBox() {
        HashSet<String> groupList = new HashSet<String>();
        List<Test> TestList = new TestDao().getAllTestShortDetails();
        for(int i = 0 ; i < TestList.size() ; i++){
            Test tst = (Test) TestList.get(i);
            groupList.add(tst.getGroupName());
        }
        ObservableList<String> groups = FXCollections.observableArrayList(groupList);
        groupNameCB.setItems(groups);
    }

    public void loadDataIntoScene(Test tst) {
        testIDTB.setText(String.valueOf(tst.getTestId()));
        testNameTB.setText(tst.getTestName());
        groupNameCB.setValue(tst.getGroupName());
        unitTB.setText(tst.getTstUnit());
        rangeTB.setText(tst.getNormalRange());
        costTB.setText(tst.getCost());
    }

    public void clearAllFields() {
        testIDTB.clear();
        testNameTB.clear();
        groupNameCB.getSelectionModel().clearSelection();
        unitTB.clear();
        rangeTB.clear();
        costTB.clear();
    }

}
