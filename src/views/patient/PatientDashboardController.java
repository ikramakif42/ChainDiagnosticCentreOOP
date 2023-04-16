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

    @FXML
    private void viewMySchedule(ActionEvent event) {
    }

    @FXML
    private void viewMyProfile(ActionEvent event) {
    }

    @FXML
    private void viewQueryOnClick(ActionEvent event) {
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

    @FXML
    private void makeQueryOnClick(ActionEvent event) {
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
    
}