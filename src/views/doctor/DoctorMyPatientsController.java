package views.doctor;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
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

public class DoctorMyPatientsController implements Initializable {

    @FXML
    private TableView<Patient> patientTableView;
    @FXML
    private TableColumn<Patient, Integer> patientIDTableColumn;
    @FXML
    private TableColumn<Patient, String> patientNameTableColumn;
    @FXML
    private TableColumn<Patient, Integer> patientAgeTableColumn;
    @FXML
    private TableColumn<Patient, LocalDate> latestApptTableColumn;
    @FXML
    private TextField nameSearchTextField;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;
    @FXML
    private TextField IDSearchTextField;
    private Doctor doc;
    Alert noPat = new Alert(Alert.AlertType.WARNING, "Error, select a patient first!");
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Callback<TableColumn.CellDataFeatures<Patient, Integer>, ObservableValue<Integer>> ageCVF = feature -> {
            Patient pat = feature.getValue();
            LocalDate birthdate = pat.getDOB();
            int age = Period.between(birthdate, LocalDate.now()).getYears();
            return new SimpleObjectProperty<>(age);
        };
        
        Callback<TableColumn.CellDataFeatures<Patient, LocalDate>, ObservableValue<LocalDate>> apptCVF = feature -> {
            Patient pat = feature.getValue();
            LocalDate latestAppt = pat.getLatestAppt(Appointment.getApptList(pat.getID()));
            return new SimpleObjectProperty<>(latestAppt);
        };
        
        patientIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        patientNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        patientAgeTableColumn.setCellValueFactory(ageCVF);
        latestApptTableColumn.setCellValueFactory(apptCVF);
        
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
                if (date.isBefore(startDatePicker.getValue())) {
                    setDisable(true);
                }
            }
        });
    }    

    public Doctor getDoc(){
        return doc;
    }

    public void setDoc(Doctor doc) {
        this.doc = doc;
        ObservableList<Patient> patList = this.doc.getPats(Appointment.getApptList(this.doc.getID()));
        patientTableView.setItems(patList);
        
        nameSearchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            String name = newValue.trim().toLowerCase();
            if (name.isEmpty()) {
                patientTableView.setItems(patList);
            } else {
                ObservableList<Patient> filterList = patList.filtered(pat -> {
                    return pat.getName().toLowerCase().contains(name);
                });
                patientTableView.setItems(filterList);
            }
        });
        
        IDSearchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            String id = newValue.trim();
            if (id.isEmpty()) {
                patientTableView.setItems(patList);
            } else {
                try {
                    ObservableList<Patient> filterList = patList.filtered(pat -> {
                        return String.valueOf(pat.getID()).contains(id);
                    });
                    patientTableView.setItems(filterList);
                } catch (NumberFormatException e) {}
            }
        });
    }

    @FXML
    private void clearFiltersOnClick(ActionEvent event) {
        nameSearchTextField.clear();
        IDSearchTextField.clear();
        startDatePicker.setValue(null);
        endDatePicker.setValue(null);
        
        ObservableList<Patient> patList = this.doc.getPats(Appointment.getApptList(this.doc.getID()));
        patientTableView.setItems(patList);
    }

    @FXML
    private void applyFiltersOnClick(ActionEvent event) {
        nameSearchTextField.clear();
        IDSearchTextField.clear();        
        
        ObservableList<Appointment> apptList = Appointment.getApptList(this.doc.getID());
        ObservableList<Patient> patList = this.doc.getPats(apptList);
        ObservableList<Patient> newPatList = FXCollections.observableArrayList();
        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();
        
        for (Patient pat : patList){
            if (startDate != null || endDate != null){
                LocalDate latestAppt = pat.getLatestAppt(Appointment.getApptList(pat.getID()));
                if (latestAppt.isBefore(endDate) && latestAppt.isAfter(startDate)){
                    newPatList.add(pat);
                }
            }
        }
        patientTableView.setItems(newPatList);
    }

    @FXML
    private void medicalRecordsOnClick(ActionEvent event) throws IOException {
        Patient pat = patientTableView.getSelectionModel().getSelectedItem();
        if (pat==null){noPat.show();return;}
        
        Parent parent = null;
        FXMLLoader doctorLoader = new FXMLLoader(getClass().getResource("ViewAddPatientRecords.fxml"));
        parent = (Parent) doctorLoader.load();
        Scene doctorScene = new Scene(parent);

        ViewAddPatientRecordsController r = doctorLoader.getController();
        r.setDoc(this.doc);
        r.setPat(pat);

        Stage doctorStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        doctorStage.setScene(doctorScene);
        doctorStage.show();
    }

    @FXML
    private void prescribeMedsOnClick(ActionEvent event) throws IOException {
        Patient pat = patientTableView.getSelectionModel().getSelectedItem();
        if (pat==null){noPat.show();return;}
        
        Parent parent = null;
        FXMLLoader doctorLoader = new FXMLLoader(getClass().getResource("PrescribeMedicine.fxml"));
        parent = (Parent) doctorLoader.load();
        Scene doctorScene = new Scene(parent);

        PrescribeMedicineController m = doctorLoader.getController();
        m.setDoc(this.doc);
        m.setPat(pat);

        Stage doctorStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        doctorStage.setScene(doctorScene);
        doctorStage.show();
    }

    @FXML
    private void labResultsOnClick(ActionEvent event) throws IOException {
        Patient pat = patientTableView.getSelectionModel().getSelectedItem();
        if (pat==null){noPat.show();return;}
        
        Parent parent = null;
        FXMLLoader doctorLoader = new FXMLLoader(getClass().getResource("AddTrackLabTests.fxml"));
        parent = (Parent) doctorLoader.load();
        Scene doctorScene = new Scene(parent);

        AddTrackLabTestsController l = doctorLoader.getController();
        l.setDoc(this.doc);
        l.setPat(pat);

        Stage doctorStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        doctorStage.setScene(doctorScene);
        doctorStage.show();
    }

    @FXML
    private void billInfoOnClick(ActionEvent event) throws IOException {
        Patient pat = patientTableView.getSelectionModel().getSelectedItem();
        if (pat==null){noPat.show();return;}
        
        Parent parent = null;
        FXMLLoader billLoader = new FXMLLoader(getClass().getResource("ViewPatientBillingInfo.fxml"));
        parent = (Parent) billLoader.load();
        Scene billScene = new Scene(parent);

        ViewPatientBillingInfoController b = billLoader.getController();
        b.setDoc(this.doc);
        b.setPat(pat);

        Stage billStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        billStage.setScene(billScene);
        billStage.show();
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
    private void nameOnClick(MouseEvent event) {
        IDSearchTextField.clear();
    }

    @FXML
    private void IDOnClick(MouseEvent event) {
        nameSearchTextField.clear();
    }
}
