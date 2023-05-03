/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.accountsofficer;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Bill;
import users.AccountsOfficer;

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
    private TextField IDSearchTextField;


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
        
        accountsBillsTableView.setItems(AccountsOfficer.viewPastRecords());
    }    

    @FXML
    private void returnToDashboardOnClick(ActionEvent event) {
    }

    
}
