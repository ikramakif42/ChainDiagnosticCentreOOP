package views.doctor;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Appointment;
import users.Doctor;
import users.Patient;
import users.User;

public class DoctorMyAppointmentsController implements Initializable {

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
    Alert noAppt = new Alert(Alert.AlertType.WARNING, "Error, select an appointment first!");
    Alert past = new Alert(Alert.AlertType.WARNING, "Error, cannot cancel past appointment!");

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
            LocalDate latestAppt = tempPat.getLatestAppt(tempPat.getApptList());
            return new SimpleObjectProperty<>(latestAppt);
        };
        
        patientIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("patientID"));
        patientNameTableColumn.setCellValueFactory(nameCVF);
        patientAgeTableColumn.setCellValueFactory(ageCVF);
        apptDateTableColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        apptTimeTableColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        lastApptTableColumn.setCellValueFactory(apptCVF);
        
        startDatePicker.setDayCellFactory(dp -> new DateCell(){
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (endDatePicker.getValue()!=null){
                    if (date.isAfter(endDatePicker.getValue())) {
                        setDisable(true);
                    }
                }
            }
        });
        endDatePicker.setDayCellFactory(dp -> new DateCell(){
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (startDatePicker.getValue()!=null){
                    if (date.isBefore(startDatePicker.getValue())) {
                        setDisable(true);
                    }
                }
            }
        });
    }    

    public Doctor getDoc() {
        return doc;
    }

    public void setDoc(Doctor doc) {
        this.doc = doc;
        ObservableList<Appointment> apptList = this.doc.getApptList();
        System.out.println(apptList);
        patientTableView.setItems(apptList);
        
        nameSearchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            String name = newValue.trim().toLowerCase();
            if (name.isEmpty()) {
                patientTableView.setItems(apptList);
            } else {
                ObservableList<Appointment> filterList = apptList.filtered(appt -> {
                    return ((Patient)User.getInstance(appt.getPatientID(), "Patient")).getName().toLowerCase().contains(name);
                });
                patientTableView.setItems(filterList);
            }
        });
        
        IDSearchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            String id = newValue.trim();
            if (id.isEmpty()) {
                patientTableView.setItems(apptList);
            } else {
                try {
                    ObservableList<Appointment> filterList = apptList.filtered(appt -> {
                        return String.valueOf(appt.getPatientID()).contains(id);
                    });
                    patientTableView.setItems(filterList);
                } catch (NumberFormatException e) {}
            }
        });
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
    private void applyFiltersOnClick(ActionEvent event) {
        nameSearchTextField.clear();
        IDSearchTextField.clear();
        
        ObservableList<Appointment> apptList = this.doc.getApptList();
        ObservableList<Appointment> newApptList = FXCollections.observableArrayList();
        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();
        for (Appointment appt : apptList){
            if (startDate != null || endDate != null){
                if (appt.getDate().isBefore(endDate) && appt.getDate().isAfter(startDate)){
                    newApptList.add(appt);
                }
            }
        }
        patientTableView.setItems(newApptList);
    }
    
    @FXML
    private void clearFiltersOnClick(ActionEvent event) {
        ObservableList<Appointment> apptList = this.doc.getApptList();
        System.out.println(apptList);
        patientTableView.setItems(apptList);
    }

    @FXML
    private void confirmCancelOnClick(ActionEvent event) throws IOException {
        Appointment toCancel = patientTableView.getSelectionModel().getSelectedItem();
        if (toCancel==null){noAppt.show();return;}
        else if (toCancel.getDate().isBefore(LocalDate.now())) {past.show();}
        
        Parent cancel = null;
        FXMLLoader cancelLoader = new FXMLLoader(getClass().getResource("ConfirmCancelAppt.fxml"));
        cancel = (Parent) cancelLoader.load();
        Scene cancelScene = new Scene(cancel);

        ConfirmCancelApptController cc = cancelLoader.getController();
        cc.setDoc(this.doc);
        cc.setAppt(toCancel);

        Stage cancelStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        cancelStage.setScene(cancelScene);
        cancelStage.show();
    }

    @FXML
    private void nameClick(MouseEvent event) {
        IDSearchTextField.clear();
    }

    @FXML
    private void idClick(MouseEvent event) {
        nameSearchTextField.clear();
    }
    
}
