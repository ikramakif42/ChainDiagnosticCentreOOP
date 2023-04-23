/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.patient;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import users.Patient;
import users.User;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class PatientMyScheduleController implements Initializable {
    
    private Patient patient;
    @FXML
    private TableView<?> apptTableView;
    @FXML
    private TableColumn<?, ?> doctorNameTableColumn;
    @FXML
    private TableColumn<?, ?> apptDateTableColumn;
    @FXML
    private TableColumn<?, ?> apptTimeTableColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @FXML
    private void returnToDashboardOnClick(ActionEvent event) throws IOException {
        Parent patientDashboard = null;
        FXMLLoader patientLoader = new FXMLLoader(getClass().getResource("PatientDashboard.fxml"));
        patientDashboard = (Parent) patientLoader.load();
        Scene patientScene = new Scene(patientDashboard);

        PatientDashboardController p = patientLoader.getController();
        p.setPatient(this.patient);

        Stage patientStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        patientStage.setScene(patientScene);
        patientStage.show();
    }

    @FXML
    private void confirmCancelOnClick(ActionEvent event) {
    }

    @FXML
    private void newApptOnClick(ActionEvent event) throws IOException {
        Parent patientMakeAppt = null;
        FXMLLoader apptLoader = new FXMLLoader(getClass().getResource("PatientMakeAppt.fxml"));
        patientMakeAppt = (Parent) apptLoader.load();
        Scene apptScene = new Scene(patientMakeAppt);

        PatientMakeApptController ap = apptLoader.getController();
        ap.setPatient(this.patient);

        Stage apptStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        apptStage.setScene(apptScene);
        apptStage.show();
    }
}
