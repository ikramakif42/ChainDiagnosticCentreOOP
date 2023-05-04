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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
    private TableColumn<Employee, String> employeeGenderColumn;

    
    private AccountsOfficer officer;
    private Employee tempEmployee;
    @FXML
    private TextField searchField;

    public AccountsOfficer getOfficer() {
        return officer;
    }

    public void setOfficer(AccountsOfficer officer) {
        this.officer = officer;
    }
    
    private final ObservableList<Employee> dataList = FXCollections.observableArrayList();

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
               
        dataList.addAll(AccountsOfficer.viewEmployees());
                FilteredList<Employee> filteredData = new FilteredList<>(dataList, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		searchField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(employee -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (employee.getName().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (String.valueOf(employee.getID()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                                else if (employee.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches last name.
				}
                                else if (employee.getDepartment().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches last name.
				}
                                else if (employee.getDesignation().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches last name.
				}
                                else if (employee.getAddress().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches last name.
				}
                                else if (String.valueOf(employee.getContactNo()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                                
                                
				else if (String.valueOf(employee.getSalary()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Employee> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(employeeTableView.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		employeeTableView.setItems(sortedData);
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
