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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Bill;
import users.AccountsOfficer;

/**
 * FXML Controller class
 *
 * @author Kazi
 */
public class AccountsOfficerPatientBillsController implements Initializable {

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
    @FXML
    private TableColumn<Bill, Integer> billedBy;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        billPatientID.setCellValueFactory(new PropertyValueFactory<Bill, Integer>("patientID"));
        billStart.setCellValueFactory(new PropertyValueFactory<Bill, LocalDate>("date"));
        billDue.setCellValueFactory(new PropertyValueFactory<Bill, LocalDate>("dueDate"));
        billAmount.setCellValueFactory(new PropertyValueFactory<Bill, Float>("amount"));        
        billDescription.setCellValueFactory(new PropertyValueFactory<Bill, String>("details"));
        billedBy.setCellValueFactory(new PropertyValueFactory<Bill, Integer>("billedByID"));    
        
        accountsBillsTableView.setItems(Bill.getAllBills());
    }
    
    public AccountsOfficer getOfficer() {
        return officer;
    }
        
    public void setOfficer(AccountsOfficer officer) {
        this.officer = officer;
    }

    @FXML
    private void ediBill(ActionEvent event) {
    }

    @FXML
    private void returnToDashboardOnClick(ActionEvent event) {
    }

    @FXML
    private void createBill(ActionEvent event) throws IOException {
        Parent bills = null;
        FXMLLoader officerLoader = new FXMLLoader(
            getClass().getResource("AccountsOfficerMakeBill.fxml")
        );
        bills = (Parent) officerLoader.load();
        Scene employeeListScene = new Scene(bills);
        
        AccountsOfficerMakeBillController e = officerLoader.getController();
        e.setOfficer(this.officer);
        
        Stage directorStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        directorStage.setScene(employeeListScene);
        directorStage.show();
    }

    @FXML
    private void markAsPaid(ActionEvent event) {
    }
    
}
