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
import model.Policy;
import model.Report;
import model.Schedule;
import users.Director;
import views.LoginController;
import views.employee.MyWorkplaceController;

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
//        LocalDate date1 = LocalDate.of(2023, 4, 1);
//        LocalDate date2 = LocalDate.of(2023, 4, 2);
//        LocalDate date3 = LocalDate.of(2023, 4, 3);
//        try {
//            f = new File("ReportObjects.bin");
//            if(f.exists()){
//                fos = new FileOutputStream(f,true);
//                oos = new AppendableObjectOutputStream(fos);                
//            }
//            else{
//                fos = new FileOutputStream(f);
//                oos = new ObjectOutputStream(fos);               
//            }
//            
//        Report test = new Report ("One Thing", "Guy", "HR", "There is a concerning lack.", 123, date1);
//        Report test2 = new Report("Two Things", "Guy", "HR", "There is a concerning lack.", 123, date2);
//        Report test3 = new Report("Three Things", "Guy", "HR", "There is a concerning lack.", 123, date3);
//        Report test4 = new Report("Four Things", "Guy", "Finance", "There is a concerning lack.", 123, date1);
//        Report test5 = new Report("Five Things", "Guy", "Finance", "There is a concerning lack.", 123, date2);
//        Report test6 = new Report("Six Things", "Guy", "Finance", "There is a concerning lack.", 123, date3);
//        Report test7 = new Report("Seven Things", "Guy", "Branch", "There is a concerning lack.", 123, date1);
//        Report test8 = new Report("Eight Things", "Guy", "Branch", "There is a concerning lack.", 123, date2);
//        Report test9 = new Report("Nine Things", "Guy", "Branch", "There is a concerning lack.", 123, date3);
//                
//        oos.writeObject(test);
//        oos.writeObject(test2);
//        oos.writeObject(test3);
//        oos.writeObject(test4);
//        oos.writeObject(test5);
//        oos.writeObject(test6);
//        oos.writeObject(test7);
//        oos.writeObject(test8);
//        oos.writeObject(test9);
//           
//            
//        } catch (IOException ex) {
//            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        System.out.println("Hello World2! Initialised");
//    
        
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

    @FXML
    private void myWorkplace(ActionEvent event) throws IOException {
        Parent empDashboard = null;
        FXMLLoader empLoader = new FXMLLoader(getClass().getResource("/views/employee/MyWorkplace.fxml"));
        empDashboard = (Parent) empLoader.load();
        Scene empScene = new Scene(empDashboard);

        MyWorkplaceController emp = empLoader.getController();
        emp.setEmployee(this.director);

        Stage empStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        empStage.setScene(empScene);
        empStage.show();        
    }
    
}
