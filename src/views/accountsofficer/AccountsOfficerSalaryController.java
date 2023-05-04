/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.accountsofficer;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import users.AccountsOfficer;
import users.Employee;

/**
 * FXML Controller class
 *
 * @author Kazi
 */
public class AccountsOfficerSalaryController implements Initializable {

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

    
    private AccountsOfficer officer;
    private Employee tempEmployee;

    public AccountsOfficer getOfficer() {
        return officer;
    }

    public void setOfficer(AccountsOfficer officer) {
        this.officer = officer;
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        employeeIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        employeeNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        employeeAddressTableColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        employeeEmailTableColumn.setCellValueFactory(new PropertyValueFactory<>("email"));        
        employeePhoneTableColumn.setCellValueFactory(new PropertyValueFactory<>("contactNo"));        
        employeDeptTableColumn.setCellValueFactory(new PropertyValueFactory<>("department"));        
        employeeDesigTableColumn.setCellValueFactory(new PropertyValueFactory<>("designation"));        
        employeeDOBTableColumn.setCellValueFactory(new PropertyValueFactory<>("DOB"));        
        employeeDOJTableColumn.setCellValueFactory(new PropertyValueFactory<>("DOJ"));        
        employeeGenderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
               
        employeeTableView.setItems(AccountsOfficer.viewEmployees());
    }    

    @FXML
    private void returnToDashboardOnClick(ActionEvent event) throws IOException {
        Parent officerDashboard = null;
        FXMLLoader officerLoader = new FXMLLoader(getClass().getResource("AccountsOfficerDashboard.fxml"));
        officerDashboard = (Parent) officerLoader.load();
        Scene directorScene = new Scene(officerDashboard);

        AccountsOfficerDashboardController e = officerLoader.getController();
        e.setOfficer(officer);

        Stage directorStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        directorStage.setScene(directorScene);
        directorStage.show();                          
    }

    @FXML
    private void addOrSubtractSalary(ActionEvent event) throws IOException {
        Parent root = null;
        FXMLLoader scheduleLoader = new FXMLLoader(getClass().getResource("AccountsOfficerAddOrSubtractSalary.fxml"));
        AccountsOfficerAddOrSubtractSalaryController q = new AccountsOfficerAddOrSubtractSalaryController(this.officer, this.tempEmployee);
        scheduleLoader.setController(q);
        root = (Parent) scheduleLoader.load();

        Scene scheduleScene = new Scene(root);
        Stage scheduleStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        scheduleStage.setScene(scheduleScene);
        scheduleStage.show();
    }

    @FXML
    private void clickedEmployee(MouseEvent event) {
        
        Employee employee = employeeTableView.getSelectionModel().getSelectedItem();
        tempEmployee = employee;
        System.out.println(tempEmployee.toString());
    }
    
    
    
}
