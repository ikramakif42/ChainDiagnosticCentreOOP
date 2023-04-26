package views.doctor;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Appointment;
import model.TeleQuery;
import users.Doctor;
import users.Patient;
import users.User;

public class DoctorMyScheduleController implements Initializable {

    @FXML
    private TableView<Appointment> patientTableView;
    @FXML
    private TableColumn<Appointment, Integer> patientIDTableColumn;
    @FXML
    private TableColumn<Appointment, String> patientNameTableColumn;
    @FXML
    private TableColumn<Appointment, Integer> patientAgeTableColumn;
    @FXML
    private TableColumn<Appointment, LocalDate> apptDateTableColumn;
    @FXML
    private TableColumn<Appointment, String> apptTimeTableColumn;
    @FXML
    private TableColumn<Appointment, LocalDate> lastApptTableColumn;
    @FXML
    private TextField nameSearchTextField;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;
    @FXML
    private TextField IDSearchTextField;
    private Doctor doc;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Callback<TableColumn.CellDataFeatures<Appointment, String>, ObservableValue<String>> nameCVF = feature -> {
            Appointment appt = feature.getValue();
            String name = ((Patient)User.getInstance(appt.getPatientID(), "Patient")).getName();
            return new SimpleStringProperty(name);
        };
        
        Callback<TableColumn.CellDataFeatures<Appointment, Integer>, ObservableValue<Integer>> ageCVF = feature -> {
            Appointment appt = feature.getValue();
            LocalDate tempDOB = ((Patient) User.getInstance(appt.getPatientID(), "Patient")).getDOB();
            int age = Period.between(tempDOB, LocalDate.now()).getYears();
            return new SimpleObjectProperty<>(age);
        };
        
        Callback<TableColumn.CellDataFeatures<Appointment, LocalDate>, ObservableValue<LocalDate>> apptCVF = feature -> {
            Appointment appt = feature.getValue();
            Patient tempPat = (Patient) User.getInstance(appt.getPatientID(), "Patient");
            LocalDate latestAppt = tempPat.getLatestAppt(Appointment.getApptList(tempPat.getID()));
            return new SimpleObjectProperty<>(latestAppt);
        };
        
        patientIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("patientID"));
        patientNameTableColumn.setCellValueFactory(nameCVF);
        patientAgeTableColumn.setCellValueFactory(ageCVF);
        apptDateTableColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        apptTimeTableColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        lastApptTableColumn.setCellValueFactory(apptCVF);        
    }    

    public Doctor getDoc() {
        return doc;
    }

    public void setDoc(Doctor doc) {
        this.doc = doc;
        ObservableList<Appointment> apptList = Appointment.getApptList(this.doc.getID());
        System.out.println(apptList);
        patientTableView.setItems(apptList);
    }
    
    @FXML
    private void returnToDashboardOnClick(ActionEvent event) throws IOException {
        Parent doctorDashboard = null;
        FXMLLoader doctorLoader = new FXMLLoader(getClass().getResource("DoctorDashboard.fxml"));
        doctorDashboard = (Parent) doctorLoader.load();
        Scene doctorScene = new Scene(doctorDashboard);

        DoctorDashboardController d = doctorLoader.getController();
        d.setDoc(this.doc);

        Stage doctorStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        doctorStage.setScene(doctorScene);
        doctorStage.show();
    }

    @FXML
    private void clearFiltersOnClick(ActionEvent event) {
        ObservableList<Appointment> apptList = Appointment.getApptList(this.doc.getID());
        System.out.println(apptList);
        patientTableView.setItems(apptList);
    }

    @FXML
    private void applyFIltersOnClick(ActionEvent event) {
        
    }

    @FXML
    private void confirmCancelOnClick(ActionEvent event) {
    }
    
}
