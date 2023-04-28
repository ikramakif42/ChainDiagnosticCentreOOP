/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.director;

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
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import model.Report;
import users.Director;

/**
 * FXML Controller class
 *
 * @author Kazi
 */
public class DirectorReportDetailsController implements Initializable {

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
    private Director director;
    private Report tempReport;

    public DirectorReportDetailsController(Director director, Report tempReport) {
        this.director = director;
        this.tempReport = tempReport;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public Report getTempReport() {
        return tempReport;
    }

    public void setTempReport(Report tempReport) {
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
    
