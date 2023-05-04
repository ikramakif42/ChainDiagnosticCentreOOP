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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Report;
import users.AccountsOfficer;
import users.Director;

/**
 * FXML Controller class
 *
 * @author Kazi
 */
public class AccountsOfficerFinanceReportsController implements Initializable {

    private AccountsOfficer officer;
    private Report tempReport;
    @FXML
    private TableView<Report> FinanceReportTableView;
    @FXML
    private TableColumn<Report, String> FinanceReportTitle;
    @FXML
    private TableColumn<Report, String> FinanceReportAuthor;
    @FXML
    private TableColumn<Report, Integer> FinanceReportAuthorID;
    @FXML
    private TableColumn<Report, LocalDate> FinanceReportDate;

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
        FinanceReportTitle.setCellValueFactory(new PropertyValueFactory<Report, String>("title"));
        FinanceReportAuthor.setCellValueFactory(new PropertyValueFactory<Report, String>("author"));
        FinanceReportDate.setCellValueFactory(new PropertyValueFactory<Report, LocalDate>("date"));
        FinanceReportAuthorID.setCellValueFactory(new PropertyValueFactory<Report, Integer>("authorID"));    
        
        FinanceReportTableView.setItems(AccountsOfficer.viewFinanceReports());
    }    

    @FXML
    private void openReportView(ActionEvent event) throws IOException {
        Parent root = null;
        FXMLLoader scheduleLoader = new FXMLLoader(getClass().getResource("AccountsOfficerReportDetails.fxml"));
        AccountsOfficerReportDetailsController q = new AccountsOfficerReportDetailsController(this.officer, this.tempReport);
        scheduleLoader.setController(q);
        root = (Parent) scheduleLoader.load();

        Scene scheduleScene = new Scene(root);
        Stage scheduleStage = new Stage();
        scheduleStage.setScene(scheduleScene);
        scheduleStage.show();        
    }

    @FXML
    private void openFinanceReportCreator(ActionEvent event) throws IOException {
        Parent officerDashboard = null;
        FXMLLoader officerLoader = new FXMLLoader(getClass().getResource("AccountsOfficerCreateFinanceReport.fxml"));
        officerDashboard = (Parent) officerLoader.load();
        Scene directorScene = new Scene(officerDashboard);

        AccountsOfficerCreateFinanceReportController e = officerLoader.getController();
        e.setOfficer(officer);

        Stage directorStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        directorStage.setScene(directorScene);
        directorStage.show();                
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
    private void clickedReport(MouseEvent event) {
        Report report = FinanceReportTableView.getSelectionModel().getSelectedItem();
        
        tempReport = report;
        System.out.println(tempReport);        
    }
    
}
