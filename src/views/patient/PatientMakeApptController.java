package views.patient;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import users.Doctor;
import users.Patient;

public class PatientMakeApptController implements Initializable {

    @FXML
    private ComboBox<String> deptComboBox;
    @FXML
    private ComboBox<String> doctorComboBox;
    @FXML
    private ComboBox<String> timeComboBox;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Label errorLabel;
    private Patient patient;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        deptComboBox.setItems(Doctor.getDeptList());
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @FXML
    private void returnToMyScheduleOnClick(ActionEvent event) throws IOException {
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
    private void confirmApptOnClick(ActionEvent event) {        
    }

    @FXML
    private void populateDocListOnClick(ActionEvent event) {
        String dept = deptComboBox.getSelectionModel().getSelectedItem();
        if (dept.isEmpty()){errorLabel.setText("Error, select dept!");return;}
        doctorComboBox.setItems(Doctor.getDocList(dept));
    }

    @FXML
    private void populateTimeOnClick(ActionEvent event) {
    }
    
}
