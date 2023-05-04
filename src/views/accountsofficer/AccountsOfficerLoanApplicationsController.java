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
import model.LoanApplication;
import users.AccountsOfficer;

/**
 * FXML Controller class
 *
 * @author Kazi
 */
public class AccountsOfficerLoanApplicationsController implements Initializable {
    
    private AccountsOfficer officer;
    @FXML
    private TableView<LoanApplication> accountsLoanApplicationTableView;
    @FXML
    private TableColumn<LoanApplication, ?> loanApplicantName;
    @FXML
    private TableColumn<LoanApplication, ?> loanType;
    @FXML
    private TableColumn<LoanApplication, ?> loanAmount;
    @FXML
    private TableColumn<LoanApplication, ?> loanStart;
    @FXML
    private TableColumn<LoanApplication, ?> loanEnd;
    @FXML
    private TableColumn<LoanApplication, ?> loanDetails;

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
        // TODO
    }    

    @FXML
    private void denyLoan(ActionEvent event) {
    }

    @FXML
    private void returnToDashboardOnClick(ActionEvent event) {
    }

    @FXML
    private void acceptLoan(ActionEvent event) {
    }
    
}
