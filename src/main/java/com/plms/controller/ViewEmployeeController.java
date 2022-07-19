package com.plms.controller;
import de.jensd.fx.glyphs.fontawesome.*;
import com.plms.dao.EmployeeDao;
import com.plms.entities.Employee;
import com.plms.modules.SceneLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ViewEmployeeController implements Initializable {
    Employee employee=null;
    @FXML
    private Button createNewBtn;
    @FXML
    private ComboBox<String> filterComboBox;
    @FXML
    private TextField searchBox;
    @FXML
    private TableView<Employee> employeeTV;

    @FXML
    private TableColumn<Employee, String> idCol;
    @FXML
    private TableColumn<Employee, String> nameCol;
    @FXML
    private TableColumn<Employee, String> usernameCol;
    @FXML
    private TableColumn<Employee, String> typeCol;
    @FXML
    private TableColumn<Employee, String> contactCol;
    @FXML
    private TableColumn<Employee, String> emailCol;
    @FXML
    private TableColumn<Employee, String> actionCol;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDataIntoTable();
    }
    @FXML
    void clickOnCreateNewButton(MouseEvent event) throws IOException {
        new SceneLoader().loadSceneInDifferentStage(getClass(),"AddEmployeePage");
    }

    public void loadDataIntoTable() {
        List<Employee> employeeList = new EmployeeDao().getAllEmployeeShortDetails();
        idCol.setCellValueFactory(new PropertyValueFactory<>("empId"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("empName"));
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("userType"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("emailId"));
        actionCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
        ObservableList<Employee> employees = FXCollections.observableArrayList(employeeList);
        employeeTV.setItems(employees);
        //method();

        //addButtonToTable();

    }


    //Method starts here
    /*private void addButtonToTable() {
        FontAwesomeIcon deleteIcon = new FontAwesomeIcon();
        deleteIcon.setGlyphName("TRASH");
        FontAwesomeIcon editIcon = new FontAwesomeIcon();
        editIcon.setGlyphName("PENCIL_SQUARE");

        TableColumn<Employee, Void> colBtn = new TableColumn();

        Callback<TableColumn<Employee, FontAwesomeIcon>, TableCell<Employee, FontAwesomeIcon>> cellFactory = new Callback<TableColumn<Employee, FontAwesomeIcon>, TableCell<Employee, FontAwesomeIcon>>() {

            @Override
            public TableCell<Employee, FontAwesomeIcon> call(final TableColumn<Employee, FontAwesomeIcon> param) {
                final TableCell<Employee, FontAwesomeIcon> cell = new TableCell<Employee, FontAwesomeIcon>() {
                    private final Button btn = new Button("Action");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Employee empRecord = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + empRecord);
                            System.out.println("id: " + empRecord.getEmpId());
                        });
                    }
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        colBtn.setCellFactory(cellFactory);
        employeeTV.getColumns().add(colBtn);
        //This method ends here

    }*/

    /*private void loadDate() {
        //add cell of button edit
        Callback<TableColumn<Employee, String>, TableCell<Employee, String>> cellFoctory = (TableColumn<Employee, String> param) -> {
            // make cell containing buttons
            final TableCell<Employee, String> cell = new TableCell<>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {
                        FontAwesomeIcon deleteIcon = new FontAwesomeIcon();
                        deleteIcon.setGlyphName("TRASH");
                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#ff1744;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            employee = employeeTV.getSelectionModel().getSelectedItem();
                            int r = new EmployeeDao().deleteEmployee(employee.getEmpId());
                            loadDataIntoTable();

                        });
                        HBox managebtn = new HBox(deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));

                        setGraphic(managebtn);

                        setText(null);
                    }
                }
            };
            return cell;
        };
        editCol.setCellFactory(cellFoctory);
//        studentsTable.setItems(StudentList);
    }*/

    /*public void method(){
        Callback<TableColumn<Employee,String>,TableCell<Employee,String>> cellFactory = new Callback<TableColumn<Employee, String>, TableCell<Employee, String>>() {
            @Override
            public TableCell<Employee, String> call(TableColumn<Employee, String> employeeStringTableColumn) {
                final TableCell<Employee,String> cell = new TableCell<Employee,String>(){
                    FontAwesomeIcon deleteIcon = new FontAwesomeIcon();

                };
                return null;
            }
        };
    }*/
}
