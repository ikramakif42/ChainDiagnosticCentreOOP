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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Report;
import users.AccountsOfficer;

/**
 * FXML Controller class
 *
 * @author Kazi
 */
public class AccountsOfficerCreateFinanceReportController implements Initializable {

    @FXML
    private TextArea reportBody;
    @FXML
    private TextField reportTitle;
    private AccountsOfficer officer;
    Alert a = new Alert(Alert.AlertType.INFORMATION, "Finance Report Created");
    Alert b = new Alert(Alert.AlertType.WARNING, "Error Creating Branch Report");    

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
    private void returnToDashboardOnClick(ActionEvent event) throws IOException {
        Parent officerDashboard = null;
        FXMLLoader officerLoader = new FXMLLoader(getClass().getResource("AccountsOfficerFinanceReports.fxml"));
        officerDashboard = (Parent) officerLoader.load();
        Scene directorScene = new Scene(officerDashboard);

        AccountsOfficerFinanceReportsController e = officerLoader.getController();
        e.setOfficer(officer);

        Stage directorStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        directorStage.setScene(directorScene);
        directorStage.show();               
        
    }

    @FXML
    private void saveFinanceReport(ActionEvent event) {
        LocalDate today = LocalDate.now();
        
        Report newFinance = new Report(reportTitle.getText(), officer.getName(), "Finance", reportBody.getText(), officer.getID(), today);
        
        boolean success = AccountsOfficer.createReport(newFinance);
        if (success){
            a.show();
        }
        else {
            b.show();
        }                
        
    }
    
}
