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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import users.Patient;

public class PatientSubmitComplaintController implements Initializable {

    @FXML
    private TextField complaintSubjectTextField;
    @FXML
    private TextArea complaintTextArea;
    private Patient patient;
    Alert success = new Alert(AlertType.INFORMATION, "Complaint submitted successfully!");
    Alert noSub = new Alert(AlertType.WARNING, "Error, enter subject!");
    Alert noBody = new Alert(AlertType.WARNING, "Error, enter details!");
    Alert failure = new Alert(AlertType.WARNING, "Error, complaint submission failed!");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @FXML
    private void submitComplaintOnClick(ActionEvent event) {
        String subject = complaintSubjectTextField.getText();
        if (subject.isEmpty()){noSub.show();return;}
        String details = complaintTextArea.getText();
        if (details.isEmpty()){noBody.show();return;}
        
        if (this.patient.submitComplaint(subject, details)) {success.show();}
        else{failure.show();}
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
    
}
