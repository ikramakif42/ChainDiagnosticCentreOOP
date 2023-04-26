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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.AppendableObjectOutputStream;
import model.Schedule;
import users.Director;
import views.LoginController;

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
//        File f = null;
//        FileOutputStream fos = null;      
//        ObjectOutputStream oos = null;
//        LocalDate date1 = LocalDate.of(2001, 2, 1);
//        try {
//            f = new File("ScheduleObjects.bin");
//            if(f.exists()){
//                fos = new FileOutputStream(f,true);
//                oos = new AppendableObjectOutputStream(fos);                
//            }
//            else{
//                fos = new FileOutputStream(f);
//                oos = new ObjectOutputStream(fos);               
//            }
//            
//        Schedule test = new Schedule(date1, "9:30", "Do Something", 444);
//        Schedule test2 = new Schedule(date1, "9:30", "Do Something", 666);
//        Schedule test3 = new Schedule(date1, "9:30", "Do Something", 202);
//        oos.writeObject(test);
//        oos.writeObject(test2);
//        oos.writeObject(test3);
//            
//            
//        } catch (IOException ex) {
//            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        System.out.println("Hello World2! Initialised");
    
        
    }    
    
    public Director getDirector() {
        return director;
    }
        
    public void setDirector(Director director) {
        this.director = director;
        directorIDLabel.setText(String.valueOf(this.director.getID()));
        directorNameLabel.setText(this.director.getName());
    }
    
    
    
    
    @FXML
    private void viewEmployees(ActionEvent event) throws IOException {
        Parent parent = null;
        FXMLLoader directorLoader = new FXMLLoader(
            getClass().getResource("DirectorViewEmployees.fxml")
        );
        parent = (Parent) directorLoader.load();
        Scene scene = new Scene(parent);
        
        DirectorViewEmployeesController e = directorLoader.getController();
        e.setDirector(this.director);
        
        Stage directorStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        directorStage.setScene(scene);
        directorStage.show();
    }

    @FXML
    private void viewReports(ActionEvent event) throws IOException {
        Parent parent = null;
        FXMLLoader directorLoader = new FXMLLoader(
            getClass().getResource("DirectorReportSelection.fxml")
        );
        parent = (Parent) directorLoader.load();
        Scene scene = new Scene(parent);
        
        DirectorReportSelectionController e = directorLoader.getController();
        e.setDirector(this.director);
        
        Stage directorStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        directorStage.setScene(scene);
        directorStage.show();
    }

    @FXML
    private void viewPolicies(ActionEvent event) throws IOException {
        Parent parent = null;
        FXMLLoader directorLoader = new FXMLLoader(
            getClass().getResource("DirectorPolicies.fxml")
        );
        parent = (Parent) directorLoader.load();
        Scene scene = new Scene(parent);
        
        DirectorPoliciesController e = directorLoader.getController();
        e.setDirector(this.director);
        
        Stage directorStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        directorStage.setScene(scene);
        directorStage.show();
    }

    @FXML
    private void logOut(ActionEvent event) throws IOException {
        Parent login=null;
        try {
            login = FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(DirectorDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene1 = new Scene(login);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene1);
        window.show();
    }
    
}
