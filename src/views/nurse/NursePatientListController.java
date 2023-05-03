/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.nurse;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import users.Nurse;
import users.Patient;

/**
 * FXML Controller class
 *
 * @author User
 */
public class NursePatientListController implements Initializable {

    @FXML
    private TableView<Patient> nursePatientListTable;
    private Nurse nurse;
    @FXML
    private TableColumn<Patient, String> nursePatientNameTable;
    @FXML
    private TableColumn<Patient, Integer> nursePatientIdTable;
    @FXML
    private TableColumn<Patient, Integer> nursePatientAgeTable;
    @FXML
    private TableColumn<Patient, String> nursePatientContactTable;
    @FXML
    private TextField nurseSearchPatientListnurseSearchPatientListByIDOnClickTextField;

    private Patient tempPatient;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Callback<TableColumn.CellDataFeatures<Patient, Integer>, ObservableValue<Integer>> ageCVF = feature -> {
            Patient pat = feature.getValue();

            LocalDate birthdate = pat.getDOB();
            int age = Period.between(birthdate, LocalDate.now()).getYears();
            return new SimpleObjectProperty<Integer>(age);
        };

        nursePatientIdTable.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("ID"));
        nursePatientNameTable.setCellValueFactory(new PropertyValueFactory<Patient, String>("name"));
        nursePatientAgeTable.setCellValueFactory(ageCVF);
        nursePatientContactTable.setCellValueFactory(new PropertyValueFactory<Patient, String>("contactNo"));
        nursePatientListTable.setItems(Patient.getPatients());

        // TODO
    }

    @FXML
    private void handleRowSelect(MouseEvent event) {
        Patient selectedPatient = nursePatientListTable.getSelectionModel().getSelectedItem();
        if (selectedPatient != null) {
            System.out.println("selected patient" + selectedPatient.email);
            // do something with the selected patient's values
        }
    }

    public Nurse getNurse() {
        return nurse;
    }

    public void setNurse(Nurse nurse) {
        this.nurse = nurse;
    }

    @FXML
    private void nurseUpdatePatientOnClick(ActionEvent event) throws IOException {
        Parent root = null;
        FXMLLoader scheduleLoader = new FXMLLoader(getClass().getResource("NurseUpdatePatientMedicalRecords.fxml"));
        NurseUpdatePatientMedicalRecordsController q = new NurseUpdatePatientMedicalRecordsController(this.nurse, this.tempPatient);
        scheduleLoader.setController(q);
        root = (Parent) scheduleLoader.load();

        Scene scheduleScene = new Scene(root);
        Stage scheduleStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scheduleStage.setScene(scheduleScene);
        scheduleStage.show();

    }

    @FXML
    private void nurseSchedulePatientOnClick(ActionEvent event) throws IOException {
        Parent nurseSchedulePatient = null;
        FXMLLoader scheduleLoader = new FXMLLoader(getClass().getResource("NurseScheduleAppointmentPatient.fxml"));
        nurseSchedulePatient = (Parent) scheduleLoader.load();
        Scene nurseViewPatientListScene = new Scene(nurseSchedulePatient);

        NurseScheduleAppointmentPatientController n = scheduleLoader.getController();
        n.setNurse(this.nurse);

        Stage nurseScheduleStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        nurseScheduleStage.setScene(nurseViewPatientListScene);
        nurseScheduleStage.show();
    }

    @FXML
    private void nursePatientBillOnClick(ActionEvent event) throws IOException {
        FXMLLoader nursePatientBillLoader = new FXMLLoader(getClass().getResource("NursePatientBill.fxml"));
        Parent nursePatientBill = (Parent) nursePatientBillLoader.load();
        Scene nursePatientBilltScene = new Scene(nursePatientBill);

        NursePatientBillController n = nursePatientBillLoader.getController();
        n.setNurse(this.nurse);

        Stage nursePatientBillStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        nursePatientBillStage.setScene(nursePatientBilltScene);
        nursePatientBillStage.show();
    }

    @FXML
    private void nursePatientPrescriptionOnClick(ActionEvent event) throws IOException {
        FXMLLoader nursePatientPrescriptionLoader = new FXMLLoader(getClass().getResource("NurseReviewPrescriptionRefills.fxml"));
        Parent nursePatientPrescription = (Parent) nursePatientPrescriptionLoader.load();
        Scene nursePatientPrescriptionScene = new Scene(nursePatientPrescription);

        NurseReviewPrescriptionRefillsController n = nursePatientPrescriptionLoader.getController();
        n.setNurse(this.nurse);

        Stage nursePatientPrescriptionStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        nursePatientPrescriptionStage.setScene(nursePatientPrescriptionScene);
        nursePatientPrescriptionStage.show();
    }

    @FXML
    private void nursePatientLabReportsOnClick(ActionEvent event) throws IOException {
        FXMLLoader nursePatientLabReportsLoader = new FXMLLoader(getClass().getResource("NurseViewLabReports.fxml"));
        Parent nursePatientLabReports = (Parent) nursePatientLabReportsLoader.load();
        Scene nursePatientLabReportsScene = new Scene(nursePatientLabReports);

        NurseViewLabReportsController n = nursePatientLabReportsLoader.getController();
        n.setNurse(this.nurse);

        Stage nursePatientLabReportsStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        nursePatientLabReportsStage.setScene(nursePatientLabReportsScene);
        nursePatientLabReportsStage.show();
    }

    @FXML
    private void nursePatientListToDashboardOnClick(ActionEvent event) throws IOException {
        Parent nurseDashboard = null;
        FXMLLoader nurseLoader = new FXMLLoader(getClass().getResource("NurseDashboard.fxml"));
        nurseDashboard = (Parent) nurseLoader.load();
        Scene nurseScene = new Scene(nurseDashboard);

        NurseDashboardController nu = nurseLoader.getController();
        nu.setNurse(this.nurse);

        Stage nurseStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        nurseStage.setScene(nurseScene);
        nurseStage.show();

    }

    @FXML
    private void nurseSearchPatientListByIDOnClick(ActionEvent event) {

    }

    @FXML
    private void clickedPatient(MouseEvent event) {
        Patient patient = nursePatientListTable.getSelectionModel().getSelectedItem();
        tempPatient = patient;
    }

}
