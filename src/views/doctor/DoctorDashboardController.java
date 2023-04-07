package views.doctor;

import java.io.IOException;
import java.net.URL;
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
import users.Doctor;
import users.User;

public class DoctorDashboardController implements Initializable {

    private Doctor doc;
    @FXML
    private Label doctorIDLabel;
    @FXML
    private Label doctorNameLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    @FXML
    private void viewMyPatients(ActionEvent event) throws IOException {
        Parent doctorMyPatients = null;
        FXMLLoader doctorLoader = new FXMLLoader(getClass().getResource("DoctorMyPatients.fxml"));
        doctorMyPatients = (Parent) doctorLoader.load();
        Scene doctorMyPatientsScene = new Scene(doctorMyPatients);

        DoctorMyPatientsController d = doctorLoader.getController();
        d.setDoc(this.doc);

        Stage doctorStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        doctorStage.setScene(doctorMyPatientsScene);
        doctorStage.show();
    }

    @FXML
    private void viewMyWorkplace(ActionEvent event) {
    }

    @FXML
    private void viewMySchedule(ActionEvent event) throws IOException {
        Parent doctorMySchedule = null;
        FXMLLoader doctorLoader = new FXMLLoader(getClass().getResource("DoctorMySchedule.fxml"));
        doctorMySchedule = (Parent) doctorLoader.load();
        Scene doctorScene = new Scene(doctorMySchedule);

        DoctorMyScheduleController d = doctorLoader.getController();
        d.setDoc(this.doc);

        Stage doctorStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        doctorStage.setScene(doctorScene);
        doctorStage.show();
    }

    //check medical records, prescribe medication, add/track lab test, view bill info
            

    @FXML
    private void logOut(ActionEvent event) {
        Parent login=null;
        try {
            login = FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(DoctorDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene1 = new Scene(login);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene1);
        window.show();
    }

    public Doctor getDoc() {
        return doc;
    }

    public void setDoc(Doctor doc) {
        this.doc = doc;
        System.out.println("What's up doc");
        doctorIDLabel.setText(String.valueOf(this.doc.ID));
        doctorNameLabel.setText(this.doc.name);
    }

    @FXML
    private void assignTasksOnClick(ActionEvent event) throws IOException {
        Parent assignTask=null;
        FXMLLoader assignTaskLoader = new FXMLLoader(getClass().getResource("AssignTask.fxml"));
        assignTask = (Parent) assignTaskLoader.load();
        Scene assignTaskScene = new Scene(assignTask);
        
        AssignTaskController a = assignTaskLoader.getController();
        a.setDoc(this.doc);
        
        Stage assignTaskStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        assignTaskStage.setScene(assignTaskScene);
        assignTaskStage.show();
    }

}
