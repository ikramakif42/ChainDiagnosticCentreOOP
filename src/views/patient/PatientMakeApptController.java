package views.patient;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Appointment;
import users.Doctor;
import users.Patient;
import users.User;
import model.Schedule;

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
    private Doctor doc;

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
    private void populateDocListOnClick(MouseEvent event) {
        String dept = deptComboBox.getSelectionModel().getSelectedItem();
        System.out.println(dept);
        if (dept.isEmpty()){errorLabel.setText("Error, select dept!");return;}
        doctorComboBox.setItems(Doctor.getDocList(dept));
    }

    @FXML
    private void populateTimeOnClick(MouseEvent event) {
        LocalDate apptDate = datePicker.getValue();
        String username = doctorComboBox.getSelectionModel().getSelectedItem();
        String[] nameID = username.split(" ");
        int docID = Integer.parseInt(nameID[nameID.length-1]);
        doc = (Doctor) User.getInstance(docID, "Doctor");
        ArrayList<Schedule> docScheduleList = doc.getScheduleRoster();

        ObservableList<String> timeList = FXCollections.observableArrayList();
        String[] times = {"09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30"};
        timeList.addAll(times);        
        Set<String> removeList = new HashSet<>();
        if (docScheduleList != null){
            for (Schedule temp: docScheduleList){
                if (temp.getDay().equals(apptDate)){
                    for (String t:times){
                        if (t.equals(temp.getTime())){
                            removeList.add(t);
                        }
                    }
                }
            }
        }
        timeList.removeAll(removeList);
        if (timeList==null){
            errorLabel.setText("Error, choose another day!");
        }
        else{timeComboBox.setItems(timeList);}
    }
    

    @FXML
    private void confirmApptOnClick(ActionEvent event) {
        String dept = deptComboBox.getSelectionModel().getSelectedItem();
        if (dept.isEmpty()){errorLabel.setText("Error, select dept!");return;}
        LocalDate apptDate = datePicker.getValue();
        if (apptDate==null){errorLabel.setText("Error, select date!");return;}
        String apptTime = timeComboBox.getSelectionModel().getSelectedItem();
        if (apptTime.isEmpty()){errorLabel.setText("Error, select time!");return;}
        
        this.patient.writeAppt(doc.getID(), apptDate, apptTime);
        
        Schedule newSchedule = new Schedule(apptDate, apptTime, "Patient Appointment", doc.getID());
        this.doc.addSchedule(newSchedule);
        
        errorLabel.setText("Appt made successfully!");
        doc = null;
        deptComboBox.getItems().clear();
        doctorComboBox.getItems().clear();
        datePicker.getEditor().clear();
        datePicker.setValue(null);
        timeComboBox.getItems().clear();
    }
}
