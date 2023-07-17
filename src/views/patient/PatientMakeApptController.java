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
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
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
    private Patient patient;
    private Doctor doc;
    Alert noDept = new Alert(Alert.AlertType.WARNING, "Error, select a department!");
    Alert noDoc = new Alert(Alert.AlertType.WARNING, "Error, select a doctor!");
    Alert noDate = new Alert(Alert.AlertType.WARNING, "Error, select a date!");
    Alert noTime = new Alert(Alert.AlertType.WARNING, "Error, select a time!");
    Alert anotherTime = new Alert(Alert.AlertType.WARNING, "Error, select another day!");
    Alert success = new Alert(Alert.AlertType.INFORMATION, "Appointment made successfully!");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        deptComboBox.setItems(Doctor.getDeptList());
        datePicker.setDayCellFactory(dp -> new DateCell(){
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (date.isBefore(LocalDate.now())) {
                    setDisable(true);
                }
            }
        });
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
        if (dept.isEmpty()){noDept.show();return;}
        doctorComboBox.setItems(Doctor.getDocList(dept));
    }

    @FXML
    private void populateTimeOnClick(MouseEvent event) {
        LocalDate apptDate = datePicker.getValue();
        String username = doctorComboBox.getSelectionModel().getSelectedItem();
        if (username==null){noDoc.show();return;}
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
        if (timeList==null){anotherTime.show();}
        else{timeComboBox.setItems(timeList);}
    }
    

    @FXML
    private void confirmApptOnClick(ActionEvent event) {
        String dept = deptComboBox.getSelectionModel().getSelectedItem();
        if (dept.isEmpty()){noDept.show();return;}
        LocalDate apptDate = datePicker.getValue();
        if (apptDate==null){noDate.show();return;}
        String apptTime = timeComboBox.getSelectionModel().getSelectedItem();
        if (apptTime.isEmpty()){noTime.show();return;}
        
        this.patient.writeAppt(doc.getID(), apptDate, apptTime);
        
        Schedule newSchedule = new Schedule(apptDate, apptTime, "Patient Appointment", doc.getID());
        this.doc.addSchedule(newSchedule);
        
        success.show();
        doc = null;
        deptComboBox.getSelectionModel().clearSelection();
        doctorComboBox.getItems().clear();
        datePicker.getEditor().clear();
        datePicker.setValue(null);
        timeComboBox.getItems().clear();
    }
}
