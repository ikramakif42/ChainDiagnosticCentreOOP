/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.accountsofficer;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import users.AccountsOfficer;

/**
 * FXML Controller class
 *
 * @author Kazi
 */
public class AccountsOfficerPatientBillsController implements Initializable {

    @FXML
    private TableView<?> accountsBillsTableView;
    @FXML
    private TableColumn<?, ?> billPatientID;
    @FXML
    private TableColumn<?, ?> billStart;
    @FXML
    private TableColumn<?, ?> billDue;
    @FXML
    private TableColumn<?, ?> billAmount;
    @FXML
    private TableColumn<?, ?> billDescription;
    private AccountsOfficer officer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    private void acceptBill(ActionEvent event) {
    }
    
}
