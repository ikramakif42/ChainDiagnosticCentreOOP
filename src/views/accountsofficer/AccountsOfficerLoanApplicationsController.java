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
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.LoanApplication;
import users.AccountsOfficer;


/**
 * FXML Controller class
 *
 * @author Kazi
 */
public class AccountsOfficerLoanApplicationsController implements Initializable {
    
    private AccountsOfficer officer;
    private LoanApplication tempLoan;
    @FXML
    private TableView<LoanApplication> accountsLoanApplicationTableView;
    @FXML
    private TableColumn<LoanApplication, String> loanType;
    @FXML
    private TableColumn<LoanApplication, Float> loanAmount;
    @FXML
    private TableColumn<LoanApplication, LocalDate> loanStart;
    @FXML
    private TableColumn<LoanApplication, String> loanDetails;
    @FXML
    private TableColumn<LoanApplication, String> loanDuration;
    @FXML
    private TableColumn<LoanApplication, Integer> loanApplicant;
    Alert a = new Alert(Alert.AlertType.INFORMATION, "Delete Successful");
    Alert b = new Alert(Alert.AlertType.WARNING, "Delete Unsuccessful");
    
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
        System.out.println(AccountsOfficer.viewLoanApplications());
        
        loanApplicant.setCellValueFactory(new PropertyValueFactory<>("applicantID"));        
        loanType.setCellValueFactory(new PropertyValueFactory<>("type"));
        loanAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        loanStart.setCellValueFactory(new PropertyValueFactory<>("date"));
        loanDetails.setCellValueFactory(new PropertyValueFactory<>("details"));        
        loanDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));        


               
        accountsLoanApplicationTableView.setItems(AccountsOfficer.viewLoanApplications());
    }    

    @FXML
    private void denyLoan(ActionEvent event) {        
        AccountsOfficer.approveLoanApplications(tempLoan.getApplicantID());
        accountsLoanApplicationTableView.setItems(AccountsOfficer.viewLoanApplications());
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
    private void acceptLoan(ActionEvent event) {        
        AccountsOfficer.approveLoanApplications(tempLoan.getApplicantID());
        accountsLoanApplicationTableView.setItems(AccountsOfficer.viewLoanApplications());
    }

    @FXML
    private void clickedLoan(MouseEvent event) {
        LoanApplication app = accountsLoanApplicationTableView.getSelectionModel().getSelectedItem();
        tempLoan = app;
        System.out.println(app.getApplicantID()); 
    }
    
}
