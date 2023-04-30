package views.patient;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Appointment;
import users.Doctor;
import users.Patient;
import users.User;

public class PatientMyScheduleController implements Initializable {
    
    private Patient patient;
    @FXML
    private TableView<Appointment> apptTableView;
    @FXML
    private TableColumn<Appointment, String> doctorNameTableColumn;
    @FXML
    private TableColumn<Appointment, LocalDate> apptDateTableColumn;
    @FXML
    private TableColumn<Appointment, String> apptTimeTableColumn;
    Alert noAppt = new Alert(Alert.AlertType.WARNING, "Error, select an appointment first!");
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Callback<TableColumn.CellDataFeatures<Appointment, String>, ObservableValue<String>> nameCVF = feature -> {
            Appointment appt = feature.getValue();
            String name = ((Doctor)User.getInstance(appt.getDoctorID(), "Doctor")).getName();
            return new SimpleStringProperty(name);
            };
        
        doctorNameTableColumn.setCellValueFactory(nameCVF);
        apptDateTableColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        apptTimeTableColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
    }
    
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
        ObservableList<Appointment> apptList = Appointment.getApptList(this.patient.getID());
        System.out.println(apptList);
        apptTableView.setItems(apptList);
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
    private void confirmCancelOnClick(ActionEvent event) throws IOException {
        Appointment apptToCancel = apptTableView.getSelectionModel().getSelectedItem();
        if (apptToCancel==null){noAppt.show();return;}
        
        Parent confirmCancel = null;
        FXMLLoader cancelLoader = new FXMLLoader(getClass().getResource("ConfirmCancelAppt.fxml"));
        confirmCancel = (Parent) cancelLoader.load();
        Scene patientScene = new Scene(confirmCancel);

        ConfirmCancelApptController cc = cancelLoader.getController();
        cc.setPatient(this.patient);
        cc.setAppt(apptToCancel);

        Stage patientStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        patientStage.setScene(patientScene);
        patientStage.show();        
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
