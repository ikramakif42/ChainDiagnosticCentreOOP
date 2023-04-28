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
        Report newBranchReport = new Report(reportTitle.getText(), director.getName(), "Branch", reportBody.getText(), director.getID(), today);
        
        File f = null;
        FileOutputStream fos = null;      
        ObjectOutputStream oos = null;
        try {
            f = new File("ReportObjects.bin");
            if(f.exists()){
                fos = new FileOutputStream(f,true);
                oos = new AppendableObjectOutputStream(fos);                
            }
            else{
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);               
            }          
        
            
        oos.writeObject(newBranchReport);

                        
        } catch (IOException ex) {
                System.out.println(ex.toString());
                System.out.println("IOException | ClassNotFoundException in reading bin file");

        }
        System.out.println("Hello World2! Initialised");      
                
    }
    
}
