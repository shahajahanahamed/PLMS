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
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
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
    private TableColumn<Test, String> costCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDataIntoTable();
    }
    @FXML
    void clickOnCreateNewButton(MouseEvent event) throws IOException {
        //Add Test page instead of AddTestPage
        //new SceneLoader().loadSceneInDifferentStage(getClass(),"AddPatientPage");
    }
    @FXML
    void clickOnCreateNewIcon(MouseEvent event) throws IOException {
        //new SceneLoader().loadSceneInDifferentStage(getClass(),"AddPatientPage");
    }

    public void loadDataIntoTable() {
        List<Test> testList = new TestDao().getAllTestShortDetails();
        TestIdCol.setCellValueFactory(new PropertyValueFactory<>("testId"));
        testnameCol.setCellValueFactory(new PropertyValueFactory<>("testName"));
        groupNameCol.setCellValueFactory(new PropertyValueFactory<>("grpName"));
        unitCol.setCellValueFactory(new PropertyValueFactory<>("testUnit"));
        normalRangeCol.setCellValueFactory(new PropertyValueFactory<>("testRange"));
        costCol.setCellValueFactory(new PropertyValueFactory<>("testCost"));
        ObservableList<Test> tests = FXCollections.observableArrayList(testList);
        addUpdateButtonToTable();
        addDeleteButtonToTable();
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
                            Test testrecord= getTableView().getItems().get(getIndex());
                            System.out.println("Update button clicked");
                            try {
                                new SceneLoader().loadSceneInDifferentStage(getClass(),"UpdatePatientPage");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
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
                            System.out.println("Result : "+result);
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
