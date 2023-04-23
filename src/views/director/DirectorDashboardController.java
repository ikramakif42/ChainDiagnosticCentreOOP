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
import javafx.stage.Stage;
import users.Director;

/**
 * FXML Controller class
 *
 * @author Kazi
 */
public class DirectorDashboardController implements Initializable {
    
    private Director director;
    @FXML
    private Label directorIDLabel;
    @FXML
    private Label directorNameLabel;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public Director getDirector() {
        return director;
    }
        
    public void setDirector(Director director) {
        this.director = director;
    }
    
    
    
    
    @FXML
    private void viewEmployees(ActionEvent event) throws IOException {
        Parent employeeList = null;
        FXMLLoader directorLoader = new FXMLLoader(
            getClass().getResource("DirectorViewEmployees.fxml")
        );
        employeeList = (Parent) directorLoader.load();
        Scene employeeListScene = new Scene(employeeList);
        
        DirectorViewEmployeesController e = directorLoader.getController();
        e.setDirector(this.director);
        
        Stage directorStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        directorStage.setScene(employeeListScene);
        directorStage.show();
        
        
    }

    @FXML
    private void viewReports(ActionEvent event) {
    }

    @FXML
    private void viewPolicies(ActionEvent event) {
    }

    @FXML
    private void logOut(ActionEvent event) {
    }
    
}
