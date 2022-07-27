package com.plms.controller;

import de.jensd.fx.glyphs.fontawesome.*;
import com.plms.dao.EmployeeDao;
import com.plms.entities.Employee;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;

public class ViewEmployeeController implements Initializable {
    Employee employee = null;
    @FXML
    private Button createNewBtn;
    @FXML
    private ComboBox<String> filterComboBox;
    @FXML
    private TextField searchBox;
    @FXML
    private TableView<Employee> employeeTV;

    @FXML
    private TableColumn<Employee, String> idCol,nameCol,usernameCol,typeCol,contactCol,emailCol;
    FXMLLoader loader;
    @FXML
    private FontAwesomeIcon searchIcon,filterIcon;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDataIntoTable();
        setUserTypeInFilterCombo();
    }

    private void setUserTypeInFilterCombo() {
        //List<String> userTypeList = new ArrayList<>();
        HashSet<String> userTypeList = new HashSet<String>();
        List<Employee> employeeList = new EmployeeDao().getAllEmployeeShortDetails();
        for(int i = 0 ; i < employeeList.size() ; i++){
            Employee emp = (Employee) employeeList.get(i);
            userTypeList.add(emp.getUserType());
        }
        ObservableList<String> usertypes = FXCollections.observableArrayList(userTypeList);
        filterComboBox.setItems(usertypes);
        filterComboBox.setPromptText("Filter by User Type");
    }

    @FXML
    void clickOnCreateNewButton(MouseEvent event) throws IOException {
        new SceneLoader().loadSceneInDifferentStage(getClass(), "AddEmployeePage");
    }
    @FXML
    void clickOnCreateNewIcon(MouseEvent event) throws IOException {
        clickOnCreateNewButton(event);
    }

    @FXML
    void clickOnSearchIcon(MouseEvent event) {
        String name = searchBox.getText();
        loadDataIntoTable(name);
    }

    @FXML
    void clickOnFilterIcon(MouseEvent event) {
        String userType = filterComboBox.getValue();
        loadDataIntoTableByUserType(userType);
    }
    private void clearTableData(){
        employeeTV.getItems().clear();
    }
    private void loadDataIntoTable(String name) {
        if(name==""){
            loadDataIntoTable();
        }else{
            clearTableData();
            List<Employee> employeeList = new EmployeeDao().getAllEmployeeShortDetails(name);
            idCol.setCellValueFactory(new PropertyValueFactory<>("empId"));
            nameCol.setCellValueFactory(new PropertyValueFactory<>("empName"));
            usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
            typeCol.setCellValueFactory(new PropertyValueFactory<>("userType"));
            contactCol.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
            emailCol.setCellValueFactory(new PropertyValueFactory<>("emailId"));
            ObservableList<Employee> employees = FXCollections.observableArrayList(employeeList);
            employeeTV.setItems(employees);
        }
    }

    public void loadDataIntoTable() {
        clearTableData();
        List<Employee> employeeList = new EmployeeDao().getAllEmployeeShortDetails();
        idCol.setCellValueFactory(new PropertyValueFactory<>("empId"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("empName"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("userType"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("emailId"));
        ObservableList<Employee> employees = FXCollections.observableArrayList(employeeList);
        employeeTV.setItems(employees);
        addUpdateButtonToTable();
        addDeleteButtonToTable();
    }

    private void loadDataIntoTableByUserType(String userType) {
        clearTableData();
        List<Employee> employeeList = new EmployeeDao().getAllEmployeeShortDetailsByUserType(userType);
        idCol.setCellValueFactory(new PropertyValueFactory<>("empId"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("empName"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("userType"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("emailId"));
        ObservableList<Employee> employees = FXCollections.observableArrayList(employeeList);
        employeeTV.setItems(employees);
    }

    private void addUpdateButtonToTable() {
        TableColumn<Employee, Void> updateBtnCol = new TableColumn("Update Action");
        updateBtnCol.setPrefWidth(70);
        updateBtnCol.setResizable(false);
        updateBtnCol.setStyle("-fx-alignment: BASELINE-CENTER;");
        Callback<TableColumn<Employee, Void>, TableCell<Employee, Void>> cellFactory = new Callback<TableColumn<Employee, Void>, TableCell<Employee, Void>>() {
            @Override
            public TableCell<Employee, Void> call(final TableColumn<Employee, Void> param) {
                final TableCell<Employee, Void> cell = new TableCell<Employee, Void>() {
                    private final Button updateBtn = new Button("Update");

                    {
                        updateBtn.setOnAction((ActionEvent event) -> {
                            Employee empRecord = getTableView().getItems().get(getIndex());
                            System.out.println("Update button clicked");
                            int empId = empRecord.getEmpId();
                            System.out.println(empId);
                            try {
                                loader = new SceneLoader().loadSceneInDifferentStage(getClass(),"UpdateEmployeePage");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            EmployeeDao dao = new EmployeeDao();
                            Employee emp = dao.getSingleEmployeeDetails(empId);
                            UpdateEmployeeController controller = loader.getController();
                            controller.loadDataIntoScene(emp);

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
        employeeTV.getColumns().add(updateBtnCol);
    }

    private void addDeleteButtonToTable() {
        TableColumn<Employee, Void> deleteBtnCol = new TableColumn("Delete");
        deleteBtnCol.setPrefWidth(70);
        deleteBtnCol.setStyle("-fx-alignment: BASELINE-CENTER;");
        deleteBtnCol.setResizable(false);
        Callback<TableColumn<Employee, Void>, TableCell<Employee, Void>> cellFactory = new Callback<TableColumn<Employee, Void>, TableCell<Employee, Void>>() {
            @Override
            public TableCell<Employee, Void> call(final TableColumn<Employee, Void> param) {
                final TableCell<Employee, Void> cell = new TableCell<Employee, Void>() {
                    private final Button deleteBtn = new Button("Delete");

                    {
                        deleteBtn.setOnAction((ActionEvent event) -> {
                            Employee empRecord = getTableView().getItems().get(getIndex());
                            System.out.println("Delete button clicked");
                            EmployeeDao eDao = new EmployeeDao();
                            int result = eDao.deleteSingleEmployee(empRecord.getEmpId());
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
        employeeTV.getColumns().add(deleteBtnCol);
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



    @FXML
    void clickOnFilterComboBox(MouseEvent event) {
        searchBox.clear();
    }

    @FXML
    void clickOnSearchBox(MouseEvent event) {
        filterComboBox.setValue("");
        filterComboBox.setPromptText("Filter by User Type");
    }
}
