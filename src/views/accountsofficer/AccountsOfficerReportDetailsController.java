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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import model.Report;
import users.AccountsOfficer;

/**
 * FXML Controller class
 *
 * @author Kazi
 */
public class AccountsOfficerReportDetailsController implements Initializable {

    @FXML
    private Label reportAuthor;
    @FXML
    private Label reportTitle;
    @FXML
    private Label reportDate;
    @FXML
    private Label reportType;
    @FXML
    private TextArea reportBody;
    private AccountsOfficer officer;
    private Report tempReport;    

    public AccountsOfficer getOfficer() {
        return officer;
    }

    public void setOfficer(AccountsOfficer officer) {
        this.officer = officer;
    }

    public Report getTempReport() {
        return tempReport;
    }

    public void setTempReport(Report tempReport) {
        this.tempReport = tempReport;
    }

    public AccountsOfficerReportDetailsController(AccountsOfficer officer, Report tempReport) {
        this.officer = officer;
        this.tempReport = tempReport;
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        reportAuthor.setText(tempReport.getAuthor());
        reportTitle.setText(tempReport.getTitle());
        reportDate.setText(String.valueOf(tempReport.getDate()));
        reportType.setText(tempReport.getType());
        reportBody.setText(tempReport.getBody());
    }    

    
}
