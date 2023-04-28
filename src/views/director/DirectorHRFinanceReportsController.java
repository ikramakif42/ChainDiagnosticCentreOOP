/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.director;

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
import users.Director;

/**
 * FXML Controller class
 *
 * @author Kazi
 */
public class DirectorHRFinanceReportsController implements Initializable {

    @FXML
    private TableView<Report> hrFinanceReportTableView;
    @FXML
    private TableColumn<Report, String> hrFinanceReportTitle;
    @FXML
    private TableColumn<Report, String> hrFinanceReportAuthor;
    @FXML
    private TableColumn<Report, LocalDate> hrFinanceReportDate;
    @FXML
    private TableColumn<Report, String> reportType;
    @FXML
    private TableColumn<Report, Integer> hrFinanceReportAuthorID;
    
    private Director director;
    private Report tempReport;



    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }
   
    
    
    /**
     * Initializes the controller class.
     */
    @Override
        public void initialize(URL url, ResourceBundle rb) {
        hrFinanceReportTitle.setCellValueFactory(new PropertyValueFactory<Report, String>("title"));
        hrFinanceReportAuthor.setCellValueFactory(new PropertyValueFactory<Report, String>("author"));
        hrFinanceReportDate.setCellValueFactory(new PropertyValueFactory<Report, LocalDate>("date"));
        reportType.setCellValueFactory(new PropertyValueFactory<Report, String>("type"));        
        hrFinanceReportAuthorID.setCellValueFactory(new PropertyValueFactory<Report, Integer>("authorID"));    
        
        hrFinanceReportTableView.setItems(Report.getAllHRFinanceReports());
        
    }    

    @FXML
    private void openReportView(ActionEvent event) throws IOException {
        Parent root = null;
        FXMLLoader scheduleLoader = new FXMLLoader(getClass().getResource("DirectorReportDetails.fxml"));
        DirectorReportDetailsController q = new DirectorReportDetailsController(this.director, this.tempReport);
        scheduleLoader.setController(q);
        root = (Parent) scheduleLoader.load();

        Scene scheduleScene = new Scene(root);
        Stage scheduleStage = new Stage();
        scheduleStage.setScene(scheduleScene);
        scheduleStage.show();
    }

    @FXML
    private void returnToDashboardOnClick(ActionEvent event) throws IOException {
        Parent directorDashboard = null;
        FXMLLoader directorLoader = new FXMLLoader(getClass().getResource("DirectorReportSelection.fxml"));
        directorDashboard = (Parent) directorLoader.load();
        Scene directorScene = new Scene(directorDashboard);

        DirectorReportSelectionController di = directorLoader.getController();
        di.setDirector(this.director);

        Stage directorStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        directorStage.setScene(directorScene);
        directorStage.show();        
    }

    @FXML
    private void clickedReport(MouseEvent event) {
        Report report = hrFinanceReportTableView.getSelectionModel().getSelectedItem();
        
        tempReport = report;
        System.out.println(tempReport);
    }

}
