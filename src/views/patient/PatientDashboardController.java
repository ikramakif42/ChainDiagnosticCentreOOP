package views.patient;

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
import users.Patient;
import views.doctor.DoctorDashboardController;

public class PatientDashboardController implements Initializable {
    
    private Patient patient;
    @FXML
    private Label patientIDLabel;
    @FXML
    private Label patientNameLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
        System.out.println("Be patient");
        patientIDLabel.setText(String.valueOf(patient.ID));
        patientNameLabel.setText(patient.name);
    }

    @FXML
    private void viewMySchedule(ActionEvent event) throws IOException {
        Parent root = null;
        FXMLLoader scheduleLoader = new FXMLLoader(getClass().getResource("PatientMySchedule.fxml"));
        root = (Parent) scheduleLoader.load();
        Scene doctorScene = new Scene(root);

        PatientMyScheduleController p = scheduleLoader.getController();
        p.setPatient(this.patient);

        Stage scheduleStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        scheduleStage.setScene(doctorScene);
        scheduleStage.show();
    }

    @FXML
    private void viewMyProfile(ActionEvent event) throws IOException {
        Parent root = null;
        FXMLLoader profileLoader = new FXMLLoader(getClass().getResource("PatientMyProfile.fxml"));
        root = (Parent) profileLoader.load();
        Scene profileScene = new Scene(root);

        PatientMyProfileController p = profileLoader.getController();
        p.setPatient(this.patient);

        Stage profileStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        profileStage.setScene(profileScene);
        profileStage.show();
    }

    @FXML
    private void viewQueryOnClick(ActionEvent event) throws IOException {
        Parent root = null;
        FXMLLoader queryLoader = new FXMLLoader(getClass().getResource("ViewQuery.fxml"));
        ViewQueryController q = new ViewQueryController(this.patient);
        queryLoader.setController(q);
        root = (Parent) queryLoader.load();

        Scene queryScene = new Scene(root);
        Stage queryStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        queryStage.setScene(queryScene);
        queryStage.show();
    }

    @FXML
    private void makeQueryOnClick(ActionEvent event) throws IOException {
        Parent root = null;
        FXMLLoader queryLoader = new FXMLLoader(getClass().getResource("MakeTelequery.fxml"));
        root = (Parent) queryLoader.load();
        Scene queryScene = new Scene(root);

        MakeTelequeryController q = queryLoader.getController();
        q.setPatient(this.patient);

        Stage queryStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        queryStage.setScene(queryScene);
        queryStage.show();
    }

    @FXML
    private void submitComplaintOnClick(ActionEvent event) {
    }

    @FXML
    private void refillRequestOnClick(ActionEvent event) {
    }

    @FXML
    private void viewPayBIllOnClick(ActionEvent event) {
    }

    @FXML
    private void viewReportOnClick(ActionEvent event) {
    }
    
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
    
}
