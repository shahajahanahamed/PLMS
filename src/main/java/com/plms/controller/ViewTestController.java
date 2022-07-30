package com.plms.controller;

import com.plms.dao.PatientDao;
import com.plms.dao.TestDao;
import com.plms.entities.Patient;
import com.plms.entities.Test;
import com.plms.modules.SceneLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;

public class ViewTestController implements Initializable {
    Test test = null;
    @FXML
    private Button createNewBtn;
    @FXML
    private ComboBox<String> filterComboBox;
    @FXML
    private TextField searchBox;
    @FXML
    private TableView<Test> testTV;
    @FXML
    private TableColumn<Test, String> TestIdCol;
    @FXML
    private TableColumn<Test, String> testnameCol;
    @FXML
    private TableColumn<Test, String> groupNameCol;
    @FXML
    private TableColumn<Test, String> unitCol;
    @FXML
    private TableColumn<Test, String> normalRangeCol;
    @FXML
    private TableColumn<Test, String> costCol;

    FXMLLoader loader;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDataIntoTable();
        setGroupNameInFilterCombo();
    }

    private void setGroupNameInFilterCombo() {
        HashSet<String> testGroupList = new HashSet<String>();
        List<Test> testList = new TestDao().getAllTestShortDetails();
        for(int i = 0 ; i < testList.size() ; i++){
            Test test = (Test) testList.get(i);
            testGroupList.add(test.getGroupName());
        }
        ObservableList<String> testGroups = FXCollections.observableArrayList(testGroupList);
        filterComboBox.setItems(testGroups);
    }

    @FXML
    void clickOnCreateNewButton(MouseEvent event) throws IOException {
        //Add Test page instead of AddTestPage
        new SceneLoader().loadSceneInDifferentStage(getClass(), "AddTestPage");
    }

    @FXML
    void clickOnCreateNewIcon(MouseEvent event) throws IOException {
        new SceneLoader().loadSceneInDifferentStage(getClass(), "AddTestPage");
    }
    @FXML
    void clickOnSearchIcon(MouseEvent event) {
        String name = searchBox.getText();
        loadDataIntoTable(name);
    }

    @FXML
    void clickOnFilterIcon(MouseEvent event) {
        String testType = filterComboBox.getValue();
        loadDataIntoTableByGroupName(testType);
    }

    private void loadDataIntoTableByGroupName(String groupName) {
        clearTableData();
        List<Test> testList = new TestDao().getAllTestDetailsByGroupName(groupName);
        TestIdCol.setCellValueFactory(new PropertyValueFactory<>("testId"));
        testnameCol.setCellValueFactory(new PropertyValueFactory<>("testName"));
        groupNameCol.setCellValueFactory(new PropertyValueFactory<>("groupName"));
        unitCol.setCellValueFactory(new PropertyValueFactory<>("tstUnit"));
        normalRangeCol.setCellValueFactory(new PropertyValueFactory<>("normalRange"));
        costCol.setCellValueFactory(new PropertyValueFactory<>("cost"));
        ObservableList<Test> tests = FXCollections.observableArrayList(testList);
        testTV.setItems(tests);
    }
    private void clearTableData(){
        testTV.getItems().clear();
    }

    public void loadDataIntoTable() {
        List<Test> testList = new TestDao().getAllTestShortDetails();
        TestIdCol.setCellValueFactory(new PropertyValueFactory<>("testId"));
        testnameCol.setCellValueFactory(new PropertyValueFactory<>("testName"));
        groupNameCol.setCellValueFactory(new PropertyValueFactory<>("groupName"));
        unitCol.setCellValueFactory(new PropertyValueFactory<>("tstUnit"));
        normalRangeCol.setCellValueFactory(new PropertyValueFactory<>("normalRange"));
        costCol.setCellValueFactory(new PropertyValueFactory<>("cost"));
        ObservableList<Test> tests = FXCollections.observableArrayList(testList);
        addUpdateButtonToTable();
        addDeleteButtonToTable();
        testTV.setItems(tests);
    }
    private void loadDataIntoTable(String name) {
        if(name==""){
            loadDataIntoTableTemp();
        }else{
            clearTableData();
            List<Test> testList = new TestDao().getAllTestShortDetails(name);
            TestIdCol.setCellValueFactory(new PropertyValueFactory<>("testId"));
            testnameCol.setCellValueFactory(new PropertyValueFactory<>("testName"));
            groupNameCol.setCellValueFactory(new PropertyValueFactory<>("groupName"));
            unitCol.setCellValueFactory(new PropertyValueFactory<>("tstUnit"));
            normalRangeCol.setCellValueFactory(new PropertyValueFactory<>("normalRange"));
            costCol.setCellValueFactory(new PropertyValueFactory<>("cost"));
            ObservableList<Test> tests = FXCollections.observableArrayList(testList);
            testTV.setItems(tests);
        }
    }

    private void loadDataIntoTableTemp() {
        clearTableData();
        List<Test> testList = new TestDao().getAllTestShortDetails();
        TestIdCol.setCellValueFactory(new PropertyValueFactory<>("testId"));
        testnameCol.setCellValueFactory(new PropertyValueFactory<>("testName"));
        groupNameCol.setCellValueFactory(new PropertyValueFactory<>("groupName"));
        unitCol.setCellValueFactory(new PropertyValueFactory<>("tstUnit"));
        normalRangeCol.setCellValueFactory(new PropertyValueFactory<>("normalRange"));
        costCol.setCellValueFactory(new PropertyValueFactory<>("cost"));
        ObservableList<Test> tests = FXCollections.observableArrayList(testList);
        testTV.setItems(tests);
    }

    private void addUpdateButtonToTable() {
        TableColumn<Test, Void> updateBtnCol = new TableColumn("Update Action");
        updateBtnCol.setPrefWidth(70);
        updateBtnCol.setResizable(false);
        updateBtnCol.setStyle("-fx-alignment: BASELINE-CENTER;");
        Callback<TableColumn<Test, Void>, TableCell<Test, Void>> cellFactory = new Callback<TableColumn<Test, Void>, TableCell<Test, Void>>() {
            @Override
            public TableCell<Test, Void> call(final TableColumn<Test, Void> param) {
                final TableCell<Test, Void> cell = new TableCell<Test, Void>() {
                    private final Button updateBtn = new Button("Update");

                    {
                        updateBtn.setOnAction((ActionEvent event) -> {
                            System.out.println("Update button clicked");
                            Test testrecord = getTableView().getItems().get(getIndex());
                            int testId = testrecord.getTestId();
                            System.out.println("Update button clicked");
                            try {
                                loader = new SceneLoader().loadSceneInDifferentStage(getClass(), "UpdateTestPage");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            TestDao dao = new TestDao();
                            Test test = dao.getSingleTestDetails(testId);
                            UpdateTestController controller = loader.getController();
                            controller.loadDataIntoScene(test);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(updateBtn);
                            setUpdateButtonStyle(updateBtn);
                            updateBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent e) {
                                    setUpdateButtonEnterStyle(updateBtn);
                                }
                            });
                            updateBtn.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent e) {
                                    setUpdateButtonExitStyle(updateBtn);
                                }
                            });
                        }
                    }
                };
                return cell;
            }
        };

        updateBtnCol.setCellFactory(cellFactory);
        testTV.getColumns().add(updateBtnCol);
    }

    private void addDeleteButtonToTable() {
        TableColumn<Test, Void> deleteBtnCol = new TableColumn("Delete");
        deleteBtnCol.setPrefWidth(70);
        deleteBtnCol.setStyle("-fx-alignment: BASELINE-CENTER;");
        deleteBtnCol.setResizable(false);
        Callback<TableColumn<Test, Void>, TableCell<Test, Void>> cellFactory = new Callback<TableColumn<Test, Void>, TableCell<Test, Void>>() {
            @Override
            public TableCell<Test, Void> call(final TableColumn<Test, Void> param) {
                final TableCell<Test, Void> cell = new TableCell<Test, Void>() {
                    private final Button deleteBtn = new Button("Delete");

                    {
                        deleteBtn.setOnAction((ActionEvent event) -> {
                            Test ptnRecord = getTableView().getItems().get(getIndex());
                            System.out.println("Delete button clicked");
                            TestDao testDao = new TestDao();
                            int result = testDao.deleteSingleTest(ptnRecord.getTestId());
                            System.out.println("Result : " + result);
                            loadDataIntoTable();
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(deleteBtn);
                            setDeleteButtonStyle(deleteBtn);
                            deleteBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent e) {
                                    setDeleteButtonEnterStyle(deleteBtn);
                                }
                            });
                            deleteBtn.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent e) {
                                    setDeleteButtonExitStyle(deleteBtn);
                                }
                            });
                        }
                    }
                };
                return cell;
            }
        };
        deleteBtnCol.setCellFactory(cellFactory);
        testTV.getColumns().add(deleteBtnCol);
    }

    public void setDeleteButtonStyle(Button deleteBtn) {
        deleteBtn.setStyle("-fx-background-color:White; " +
                "-fx-text-fill:Red; " +
                "-fx-background-radius:30; " +
                "-fx-border-radius:30;" +
                "-fx-border-width:1.5; " +
                "-fx-border-color:Red");
    }

    public void setDeleteButtonEnterStyle(Button deleteBtn) {
        deleteBtn.setStyle("-fx-background-color:White; " +
                "-fx-text-fill:Red; " +
                "-fx-background-radius:30; " +
                "-fx-border-radius:30;" +
                "-fx-border-width:1.5; " +
                "-fx-border-color:Yellow");
    }

    public void setDeleteButtonExitStyle(Button deleteBtn) {
        deleteBtn.setStyle("-fx-background-color:White; " +
                "-fx-text-fill:Red; " +
                "-fx-background-radius:30; " +
                "-fx-border-radius:30;" +
                "-fx-border-width:1.5; " +
                "-fx-border-color:Red");
    }

    public void setUpdateButtonStyle(Button updateBtn) {
        updateBtn.setStyle("-fx-background-color:White; " +
                "-fx-text-fill:Green; " +
                "-fx-background-radius:30; " +
                "-fx-border-radius:30;" +
                "-fx-border-width:1.5; " +
                "-fx-border-color:Green");
    }

    public void setUpdateButtonEnterStyle(Button updateBtn) {
        updateBtn.setStyle("-fx-background-color:White; " +
                "-fx-text-fill:Green; " +
                "-fx-background-radius:30; " +
                "-fx-border-radius:30;" +
                "-fx-border-width:1.5; " +
                "-fx-border-color:Yellow");
    }

    public void setUpdateButtonExitStyle(Button updateBtn) {
        updateBtn.setStyle("-fx-background-color:White; " +
                "-fx-text-fill:Green; " +
                "-fx-background-radius:30; " +
                "-fx-border-radius:30;" +
                "-fx-border-width:1.5; " +
                "-fx-border-color:Green");
    }

}
