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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import users.AccountsOfficer;

/**
 * FXML Controller class
 *
 * @author Kazi
 */
public class AccountsOfficerDashboardController implements Initializable {

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
//        System.out.println("What's up doc");
//        doctorIDLabel.setText(String.valueOf(this.doc.ID));
//        doctorNameLabel.setText(this.doc.name);
    }
    
    
    @FXML
    private void accountsOfficerRestockRequestsOnClick(ActionEvent event) {
        try{
        Parent root = FXMLLoader.load(getClass().getResource("AccountsOfficerRestockOrders.fxml"));
        Scene scene = new Scene(root);
        Stage stg = (Stage)((Node)event.getSource()).getScene().getWindow();
        stg.setScene(scene);
        stg.show();
    }
    catch(Exception e){
        e.printStackTrace();
    }
    }

    @FXML
    private void accountsOfficerPastRecordsOnClick(ActionEvent event) {
        try{
        Parent root = FXMLLoader.load(getClass().getResource("AccountsOfficerPastRecords.fxml"));
        Scene scene = new Scene(root);
        Stage stg = (Stage)((Node)event.getSource()).getScene().getWindow();
        stg.setScene(scene);
        stg.show();
    }
    catch(Exception e){
        e.printStackTrace();
    }
    }

    @FXML
    private void accountsOfficerPatientBillingOnClick(ActionEvent event) {
        try{
        Parent root = FXMLLoader.load(getClass().getResource("AccountsOfficerPatientBilling.fxml"));
        Scene scene = new Scene(root);
        Stage stg = (Stage)((Node)event.getSource()).getScene().getWindow();
        stg.setScene(scene);
        stg.show();
    }
    catch(Exception e){
        e.printStackTrace();
    }
    }

    @FXML
    private void accountsOfficerReportsOnClick(ActionEvent event) {
        try{
        Parent root = FXMLLoader.load(getClass().getResource("AccountsOfficerReports.fxml"));
        Scene scene = new Scene(root);
        Stage stg = (Stage)((Node)event.getSource()).getScene().getWindow();
        stg.setScene(scene);
        stg.show();
    }
    catch(Exception e){
        e.printStackTrace();
    }
    }

    @FXML
    private void accountsOfficerLoanApplicationsOnClick(ActionEvent event) {
                try{
        Parent root = FXMLLoader.load(getClass().getResource("AccountsOfficerLoanApplications.fxml"));
        Scene scene = new Scene(root);
        Stage stg = (Stage)((Node)event.getSource()).getScene().getWindow();
        stg.setScene(scene);
        stg.show();
    }
    catch(Exception e){
        e.printStackTrace();
    }
    }

    @FXML
    private void accountsOfficerSalariesOnClick(ActionEvent event) {
                try{
        Parent root = FXMLLoader.load(getClass().getResource("AccountsOfficerSalaries.fxml"));
        Scene scene = new Scene(root);
        Stage stg = (Stage)((Node)event.getSource()).getScene().getWindow();
        stg.setScene(scene);
        stg.show();
    }
    catch(Exception e){
        e.printStackTrace();
    }
    }
    
}
