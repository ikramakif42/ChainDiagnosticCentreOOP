package views.doctor;

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
import javafx.stage.Stage;
import model.Appointment;
import users.Doctor;

public class ConfirmCancelApptController implements Initializable {
    
    private Doctor doc;
    private Appointment appt;
    Alert failure = new Alert(Alert.AlertType.WARNING, "Error, appointment cancel failed!");
    Alert success = new Alert(Alert.AlertType.INFORMATION, "Appointment cancelled!");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    public Doctor getDoc() {
        return doc;
    }

    public void setDoc(Doctor doc) {
        this.doc = doc;
    }

    public Appointment getAppt() {
        return appt;
    }

    public void setAppt(Appointment appt) {
        this.appt = appt;
    }

    @FXML
    private void cancelApptOnClick(ActionEvent event) {
        if(this.doc.cancelAppt(this.appt)){success.show();}
        else{failure.show();}
    }

    @FXML
    private void returnToScheduleOnClick(ActionEvent event) throws IOException {
        Parent doctorMySchedule = null;
        FXMLLoader doctorLoader = new FXMLLoader(getClass().getResource("DoctorMyAppointments.fxml"));
        doctorMySchedule = (Parent) doctorLoader.load();
        Scene doctorScene = new Scene(doctorMySchedule);

        DoctorMyAppointmentsController d = doctorLoader.getController();
        d.setDoc(this.doc);

        Stage doctorStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        doctorStage.setScene(doctorScene);
        doctorStage.show();
    }
    
}
