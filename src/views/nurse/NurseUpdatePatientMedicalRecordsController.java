/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.nurse;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import users.Nurse;
import users.Patient;

/**
 * FXML Controller class
 *
 * @author User
 */
public class NurseUpdatePatientMedicalRecordsController implements Initializable {

    private TextField nurseUpdatePatientName;
    private TextField nurseUpdatePatientId;
    @FXML
    private TextArea nurseUpdatePatientMedicalRecordTextArea;
    
    private Nurse nurse;
    
    private Patient patient;
    @FXML
    private TextArea nursePatientMedicalRecordTextArea;
    @FXML
    private Label nurseUpdatePatientNameLabel;
    @FXML
    private Label nurseUpdatePatientIDLabel;

    public NurseUpdatePatientMedicalRecordsController(Nurse nurse, Patient patient) {
        this.nurse = nurse;
        this.patient = patient;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
    ArrayList<String> historyList = patient.getMedicalRecords();
    nurseUpdatePatientNameLabel.setText(patient.getName());
    nurseUpdatePatientIDLabel.setText(String.valueOf( patient.getID()));
    for (String s: historyList){
        nursePatientMedicalRecordTextArea.appendText(s + "\n");
        
    }
    }    

    public Nurse getNurse() {
        return nurse;
    }

    public void setNurse(Nurse nurse) {
        this.nurse = nurse;
    }
    
    @FXML
    private void nurseUpdatePatientSaveOnClick(ActionEvent event) {
        String s = nurseUpdatePatientMedicalRecordTextArea.getText();
        patient.updatePersonalInfo(s);
        nursePatientMedicalRecordTextArea.appendText(s + "\n");
        
        
    }

    @FXML
    private void nurseUpdatePatientBackToPatientListOnClick(ActionEvent event) throws IOException {
        Parent nurseViewPatientList = null;
        FXMLLoader nurseLoader = new FXMLLoader(getClass().getResource("NursePatientList.fxml"));
        nurseViewPatientList = (Parent) nurseLoader.load();
        Scene nurseViewPatientListScene = new Scene(nurseViewPatientList);

        NursePatientListController n = nurseLoader.getController();
        n.setNurse(this.nurse);

        Stage nurseStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        nurseStage.setScene(nurseViewPatientListScene);
        nurseStage.show();
        
    }
    
}
