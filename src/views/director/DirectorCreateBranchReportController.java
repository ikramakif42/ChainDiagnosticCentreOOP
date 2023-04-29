/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.director;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
import main.AppendableObjectOutputStream;
import model.Bill;
import model.Report;
import users.Director;

/**
 * FXML Controller class
 *
 * @author Kazi
 */
public class DirectorCreateBranchReportController implements Initializable {

    @FXML
    private TextArea reportBody;
    @FXML
    private TextField reportTitle;
    private Director director;
    Alert a = new Alert(Alert.AlertType.INFORMATION, "Branch Report Created");
    Alert b = new Alert(Alert.AlertType.WARNING, "Error Creating Branch Report");

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
        // TODO
    }    

    @FXML
    private void returnToDashboardOnClick(ActionEvent event) throws IOException {
        Parent directorDashboard = null;
        FXMLLoader directorLoader = new FXMLLoader(getClass().getResource("DirectorBranchReports.fxml"));
        directorDashboard = (Parent) directorLoader.load();
        Scene directorScene = new Scene(directorDashboard);

        DirectorBranchReportsController di = directorLoader.getController();
        di.setDirector(this.director);

        Stage directorStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        directorStage.setScene(directorScene);
        directorStage.show();        
    }

    @FXML
    private void saveBranchReport(ActionEvent event) {
        LocalDate today = LocalDate.now();
        
        Report newBranchReport = new Report(reportTitle.getText(), director.getName(), "Branch", reportBody.getText(), director.getID());
        
        boolean success = Director.createBranchReport(newBranchReport);
        if (success){
            a.show();
        }
        else {
            b.show();
        }        
    }
    
}
