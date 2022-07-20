package com.plms.controller;

import com.plms.dao.EmployeeDao;
import com.plms.dao.PatientDao;
import com.plms.entities.Employee;
import com.plms.entities.Patient;
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

public class ViewPatientController implements Initializable {
    @FXML
    private Button createNewBtn;
    @FXML
    private ComboBox<String> filterComboBox;
    @FXML
    private TextField searchBox;
    @FXML
    private TableView<Patient> patientTV;
    @FXML
    private TableColumn<Patient, String> idCol;
    @FXML
    private TableColumn<Patient, String> nameCol;
    @FXML
    private TableColumn<Patient, String> TestTypeCol;
    @FXML
    private TableColumn<Patient, String> DOBCol;
    @FXML
    private TableColumn<Patient, String> ContactCol;
    @FXML
    private TableColumn<Patient, String> EmailCol;
    @FXML
    private TableColumn<Patient, String> GenderCol;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDataIntoTable();
    }
    @FXML
    void clickOnCreateNewButton(MouseEvent event) throws IOException {
        //Add patient page instead of AddEmployeePage
        new SceneLoader().loadSceneInDifferentStage(getClass(),"AddPatientPage");
    }

    public void loadDataIntoTable() {
        List<Patient> patientList = new PatientDao().getAllPatientShortDetails();
        idCol.setCellValueFactory(new PropertyValueFactory<>("ptnId"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("ptnName"));
        TestTypeCol.setCellValueFactory(new PropertyValueFactory<>("testType"));
        DOBCol.setCellValueFactory(new PropertyValueFactory<>("ptnDOB"));
        GenderCol.setCellValueFactory(new PropertyValueFactory<>("ptnGender"));
        ContactCol.setCellValueFactory(new PropertyValueFactory<>("ptnContact"));
        EmailCol.setCellValueFactory(new PropertyValueFactory<>("ptnEmailId"));
        ObservableList<Patient> patients = FXCollections.observableArrayList(patientList);
        addUpdateButtonToTable();
        addDeleteButtonToTable();
        patientTV.setItems(patients);
    }
    private void addUpdateButtonToTable() {
        TableColumn<Patient, Void> updateBtnCol = new TableColumn("Update Action");
        updateBtnCol.setPrefWidth(70);
        updateBtnCol.setResizable(false);
        updateBtnCol.setStyle("-fx-alignment: BASELINE-CENTER;");
        Callback<TableColumn<Patient, Void>, TableCell<Patient, Void>> cellFactory = new Callback<TableColumn<Patient, Void>, TableCell<Patient, Void>>() {
            @Override
            public TableCell<Patient, Void> call(final TableColumn<Patient, Void> param) {
                final TableCell<Patient, Void> cell = new TableCell<Patient, Void>() {
                    private final Button updateBtn = new Button("Update");

                    {
                        updateBtn.setOnAction((ActionEvent event) -> {
                            Patient ptnRecord = getTableView().getItems().get(getIndex());
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
        patientTV.getColumns().add(updateBtnCol);
    }

    private void addDeleteButtonToTable() {
        TableColumn<Patient, Void> deleteBtnCol = new TableColumn("Delete");
        deleteBtnCol.setPrefWidth(70);
        deleteBtnCol.setStyle("-fx-alignment: BASELINE-CENTER;");
        deleteBtnCol.setResizable(false);
        Callback<TableColumn<Patient, Void>, TableCell<Patient, Void>> cellFactory = new Callback<TableColumn<Patient, Void>, TableCell<Patient, Void>>() {
            @Override
            public TableCell<Patient, Void> call(final TableColumn<Patient, Void> param) {
                final TableCell<Patient, Void> cell = new TableCell<Patient, Void>() {
                    private final Button deleteBtn = new Button("Delete");

                    {
                        deleteBtn.setOnAction((ActionEvent event) -> {
                            Patient ptnRecord = getTableView().getItems().get(getIndex());
                            System.out.println("Delete button clicked");
                            PatientDao pDao = new PatientDao();
                            int result = pDao.deleteSinglePatient(ptnRecord.getPtnId());
                            System.out.println("Result : "+result);
                            //loadDataIntoTable();
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
        patientTV.getColumns().add(deleteBtnCol);
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
