package views.patient;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import users.Patient;

public class ConfirmCancelApptController implements Initializable {
    private Patient patient;

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
    private void cancelApptOnClick(ActionEvent event) {
    }

    @FXML
    private void returnToScheduleOnClick(ActionEvent event) {
    }
    
}
