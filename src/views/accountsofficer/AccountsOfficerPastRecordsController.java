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
import model.Bill;
import users.AccountsOfficer;
import users.Director;
import users.Employee;

/**
 * FXML Controller class
 *
 * @author Kazi
 */
public class AccountsOfficerPastRecordsController implements Initializable {

    @FXML
    private TableView<Bill> accountsBillsTableView;
    @FXML
    private TableColumn<Bill, Integer> billPatientID;
    @FXML
    private TableColumn<Bill, LocalDate> billStart;
    @FXML
    private TableColumn<Bill, LocalDate> billDue;
    @FXML
    private TableColumn<Bill, Float> billAmount;
    @FXML
    private TableColumn<Bill, String> billDescription;
    private AccountsOfficer officer;
    private Bill tempBill;
    @FXML
    private TableColumn<Bill, Integer> billedBy;
    @FXML
    private TextField searchField;

    public AccountsOfficer getOfficer() {
        return officer;
    }

    public void setOfficer(AccountsOfficer officer) {
        this.officer = officer;
    }
    
    private final ObservableList<Bill> dataList = FXCollections.observableArrayList();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        billPatientID.setCellValueFactory(new PropertyValueFactory<Bill, Integer>("patientID"));
        billStart.setCellValueFactory(new PropertyValueFactory<Bill, LocalDate>("createdDate"));
        billDue.setCellValueFactory(new PropertyValueFactory<Bill, LocalDate>("dueDate"));
        billAmount.setCellValueFactory(new PropertyValueFactory<Bill, Float>("amount"));        
        billDescription.setCellValueFactory(new PropertyValueFactory<Bill, String>("details"));
        billedBy.setCellValueFactory(new PropertyValueFactory<Bill, Integer>("billedByID"));    
        
        
                dataList.addAll(AccountsOfficer.viewPastRecords());
                FilteredList<Bill> filteredData = new FilteredList<>(dataList, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		searchField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(bill -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (String.valueOf(bill.getPatientID()).toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (String.valueOf(bill.getBilledByID()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                                else if (String.valueOf(bill.getAmount()).toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches last name.
				}
                                
				else if (bill.getDetails().toLowerCase().indexOf(lowerCaseFilter) != -1 )
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Bill> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(accountsBillsTableView.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		accountsBillsTableView.setItems(sortedData);
        
    }    

    @FXML
    private void returnToDashboardOnClick(ActionEvent event) throws IOException {
        Parent directorDashboard = null;
        FXMLLoader directorLoader = new FXMLLoader(getClass().getResource("AccountsOfficerDashboard.fxml"));
        directorDashboard = (Parent) directorLoader.load();
        Scene directorScene = new Scene(directorDashboard);

        AccountsOfficerDashboardController di = directorLoader.getController();
        di.setOfficer(officer);

        Stage directorStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        directorStage.setScene(directorScene);
        directorStage.show();
    }


    
}
