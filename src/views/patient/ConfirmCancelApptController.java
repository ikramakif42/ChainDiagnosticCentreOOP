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
import javafx.stage.Stage;
import model.Appointment;
import users.Patient;

public class ConfirmCancelApptController implements Initializable {
    private Patient patient;
    private Appointment appt;

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

    public Appointment getAppt() {
        return appt;
    }

    public void setAppt(Appointment appt) {
        this.appt = appt;
    }
    
    @FXML
    private void cancelApptOnClick(ActionEvent event) throws IOException {
        Appointment.cancelAppt(this.appt);
        back(event);
    }

    @FXML
    private void returnToScheduleOnClick(ActionEvent event) throws IOException {
        back(event);
    }
    
    private void back(ActionEvent event) throws IOException {
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
}
