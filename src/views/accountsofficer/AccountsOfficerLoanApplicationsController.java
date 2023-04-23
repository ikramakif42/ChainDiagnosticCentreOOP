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

/**
 * FXML Controller class
 *
 * @author Kazi
 */
public class AccountsOfficerLoanApplicationsController implements Initializable {

    @FXML
    private TableView<?> accountsLoanApplicationTableView;
    @FXML
    private TableColumn<?, ?> loanApplicantName;
    @FXML
    private TableColumn<?, ?> loanType;
    @FXML
    private TableColumn<?, ?> loanAmount;
    @FXML
    private TableColumn<?, ?> loanStart;
    @FXML
    private TableColumn<?, ?> loanEnd;
    @FXML
    private TableColumn<?, ?> loanDetails;

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
