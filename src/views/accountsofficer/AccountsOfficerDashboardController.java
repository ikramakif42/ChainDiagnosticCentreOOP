/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.accountsofficer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import users.AccountsOfficer;

/**
 * FXML Controller class
 *
 * @author Kazi
 */
public class AccountsOfficerDashboardController implements Initializable {

    @FXML
    private Label accountsIDLabel;
    @FXML
    private Label accountsNameLabel;
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
    private void viewBills(ActionEvent event) throws IOException {
        Parent billsList = null;
        FXMLLoader officerLoader = new FXMLLoader(
            getClass().getResource("accountsofficer/AccountsOfficerPatientBills.fxml")
        );
        billsList = (Parent) officerLoader.load();
        Scene employeeListScene = new Scene(billsList);
        
        AccountsOfficerPatientBillsController e = officerLoader.getController();
        e.setOfficer(this.officer);
        
        Stage directorStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        directorStage.setScene(employeeListScene);
        directorStage.show();
        
    }

    @FXML
    private void viewRestocks(ActionEvent event) {
    }

    @FXML
    private void viewPastRecords(ActionEvent event) {
    }

    @FXML
    private void logOut(ActionEvent event) {
    }

    @FXML
    private void viewReports(ActionEvent event) {
    }

    @FXML
    private void viewLoanApplications(ActionEvent event) {
    }

    @FXML
    private void viewSalary(ActionEvent event) {
    }
    
}
