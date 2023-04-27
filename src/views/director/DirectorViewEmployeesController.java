/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.director;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Schedule;
import users.AccountsOfficer;
import users.Director;
import users.Doctor;
import users.Employee;
import users.HROfficer;
import users.LabTechnician;
import users.Nurse;
import users.Pharmacist;
import users.User;

/**
 * FXML Controller class
 *
 * @author Kazi
 */
public class DirectorViewEmployeesController implements Initializable {

    @FXML
    private TableView<Employee> employeeTableView;
    @FXML
    private TableColumn<Employee, Integer> employeeIDTableColumn;
    @FXML
    private TableColumn<Employee, String> employeeNameTableColumn;
    @FXML
    private TableColumn<Employee, String> employeeEmailTableColumn;
    @FXML
    private TableColumn<Employee, String> employeePhoneTableColumn;
    @FXML
    private TableColumn<Employee, String> employeDeptTableColumn;
    @FXML
    private TableColumn<Employee, String> employeeDesigTableColumn;
    @FXML
    private TableColumn<Employee, LocalDate> employeeDOBTableColumn;
    @FXML
    private TableColumn<Employee, LocalDate> employeeDOJTableColumn;
    @FXML
    private TableColumn<Employee, String> employeeAddressTableColumn;
    @FXML
    private TextField nameSearchTextField;
    @FXML
    private TextField IDSearchTextField;

    @FXML
    private TableColumn<Employee, String> employeeGenderColumn;
    @FXML
    private Label testLabel;
    
    private Director director;
    private Employee tempEmployee=null;

    public Employee getTempEmployee() {
        return tempEmployee;
    }

    public void setTempEmployee(Employee tempEmployee) {
        this.tempEmployee = tempEmployee;
    }    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        employeeIDTableColumn.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("ID"));
        employeeNameTableColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
        employeeAddressTableColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("address"));
        employeeEmailTableColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("email"));        
        employeePhoneTableColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("contactNo"));        
        employeDeptTableColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("department"));        
        employeeDesigTableColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("designation"));        
        employeeDOBTableColumn.setCellValueFactory(new PropertyValueFactory<Employee, LocalDate>("DOB"));        
        employeeDOJTableColumn.setCellValueFactory(new PropertyValueFactory<Employee, LocalDate>("DOJ"));        
        employeeGenderColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("gender"));
               
        employeeTableView.setItems(getEmployees());
        
    }
    
    public Director getDirector() {
        return director;
    }
        
    public void setDirector(Director director) {
        this.director = director;
    }

    @FXML
    private void returnToDashboardOnClick(ActionEvent event) throws IOException {
        Parent directorDashboard = null;
        FXMLLoader directorLoader = new FXMLLoader(getClass().getResource("DirectorDashboard.fxml"));
        directorDashboard = (Parent) directorLoader.load();
        Scene directorScene = new Scene(directorDashboard);

        DirectorDashboardController di = directorLoader.getController();
        di.setDirector(this.director);

        Stage directorStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        directorStage.setScene(directorScene);
        directorStage.show();
    }

    @FXML
    private void addOrSubtractSalary(ActionEvent event) throws IOException {
        Parent root = null;
        FXMLLoader scheduleLoader = new FXMLLoader(getClass().getResource("DirectorAddOrSubtractSalary.fxml"));
        DirectorAddOrSubtractSalaryController q = new DirectorAddOrSubtractSalaryController(this.director, this.tempEmployee);
        scheduleLoader.setController(q);
        root = (Parent) scheduleLoader.load();

        Scene scheduleScene = new Scene(root);
        Stage scheduleStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        scheduleStage.setScene(scheduleScene);
        scheduleStage.show();
    }
    

    @FXML
    private void employeeSchedule(ActionEvent event) throws IOException {
        if (tempEmployee==null){System.out.println("No emp");return;}
//        Parent parent = null;
//        FXMLLoader directorLoader = new FXMLLoader(
//            getClass().getResource("DirectorEmployeeSchedule.fxml")
//        );
//        parent = (Parent) directorLoader.load();
//        Scene scene = new Scene(parent);
//        
//        DirectorEmployeeScheduleController e = directorLoader.getController();
//        e.setDirector(this.director);
//        e.setTempEmployee(this.tempEmployee);
//        
//        Stage directorStage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        directorStage.setScene(scene);
//        directorStage.show();
        
        
        Parent root = null;
        FXMLLoader scheduleLoader = new FXMLLoader(getClass().getResource("DirectorEmployeeSchedule.fxml"));
        DirectorEmployeeScheduleController q = new DirectorEmployeeScheduleController(this.director, this.tempEmployee);
        scheduleLoader.setController(q);
        root = (Parent) scheduleLoader.load();

        Scene scheduleScene = new Scene(root);
        Stage scheduleStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        scheduleStage.setScene(scheduleScene);
        scheduleStage.show();
        
    }
    
    public ObservableList<Employee> getEmployees(){
        ObservableList<Employee> employeeList = FXCollections.observableArrayList();

        employeeList.addAll(AccountsOfficer.getAllAccounts());
        employeeList.addAll(Doctor.getAllDoctors());
        employeeList.addAll(HROfficer.getAllHROfficers());
        employeeList.addAll(LabTechnician.getAllLabTechnicians());
        employeeList.addAll(Nurse.getAllNurses());
        employeeList.addAll(Pharmacist.getAllPharmacists());        
        
        return employeeList;
    }    

    @FXML
    private void clickedEmployee(MouseEvent event) {
        Employee employee = employeeTableView.getSelectionModel().getSelectedItem();
        
        tempEmployee = employee;
        System.out.println(tempEmployee.toString());
        testLabel.setText(tempEmployee.getEmail());
        

        
    }


    
}
    
    

