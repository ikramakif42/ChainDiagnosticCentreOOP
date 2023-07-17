package views.doctor;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.LabReport;
import users.Doctor;
import users.Patient;

public class AddTrackLabTestsController implements Initializable {
    
    @FXML
    private TableView<LabReport> reportTableView;
    @FXML
    private TableColumn<LabReport, String> titleTableColumn;
    @FXML
    private TableColumn<LabReport, String> typeTableColumn;
    @FXML
    private TableColumn<LabReport, LocalDate> dateTableColumn;
    @FXML
    private Label patientIDLabel;
    @FXML
    private Label patientNameLabel;
    @FXML
    private Label patientAgeLabel;
    @FXML
    private ComboBox<String> testPriorityComboBox;
    @FXML
    private TextField testNameTextField;
    @FXML
    private TextArea reportTextArea;
    private Doctor doc;
    private Patient pat;
    Alert nullReport = new Alert(Alert.AlertType.WARNING, "Error, choose a report first!");
    Alert noTest = new Alert(Alert.AlertType.WARNING, "Error, enter test name!");
    Alert noPriority = new Alert(Alert.AlertType.WARNING, "Error, choose a test priority!");
    Alert failure = new Alert(Alert.AlertType.WARNING, "Error, submitting lab order failed!");
    Alert success = new Alert(Alert.AlertType.INFORMATION, "Lab order submitted successfully!");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String[] priorities = {"Low", "Moderate", "High"};
        testPriorityComboBox.getItems().addAll(priorities);
        titleTableColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        typeTableColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        dateTableColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
    }    

    public Doctor getDoc() {
        return doc;
    }

    public void setDoc(Doctor doc) {
        this.doc = doc;
    }

    public Patient getPat() {
        return pat;
    }

    public void setPat(Patient pat) {
        this.pat = pat;
        patientIDLabel.setText(String.valueOf(this.pat.getID()));
        patientNameLabel.setText(this.pat.getName());
        int age = Period.between(this.pat.getDOB(), LocalDate.now()).getYears();
        patientAgeLabel.setText(String.valueOf(age));
        reportTableView.setItems(this.pat.getReportList());
    }

    @FXML
    private void submitLabOrderOnClick(ActionEvent event) {
        String testName = testNameTextField.getText();
        if (testName.isEmpty()) {noTest.show();return;}
        String priority = testPriorityComboBox.getSelectionModel().getSelectedItem();
        if (priority==null) {noPriority.show();return;}
        if (this.doc.submitLabOrder(this.pat.getID(), testName, priority)){success.show();}
        else {failure.show();}
    }

    @FXML
    private void viewReportOnClick(ActionEvent event) {
        LabReport selectedReport = reportTableView.getSelectionModel().getSelectedItem();
        if (selectedReport==null) {nullReport.show();return;}
        reportTextArea.clear();
        reportTextArea.setText(selectedReport.toStringDisplay());
    }
    
    @FXML
    private void returnToMyPatientsOnClick(ActionEvent event) throws IOException {
        Parent parent = null;
        FXMLLoader doctorLoader = new FXMLLoader(getClass().getResource("DoctorMyPatients.fxml"));
        parent = (Parent) doctorLoader.load();
        Scene doctorScene = new Scene(parent);

        DoctorMyPatientsController d = doctorLoader.getController();
        d.setDoc(this.doc);

        Stage doctorStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        doctorStage.setScene(doctorScene);
        doctorStage.show();
    }
}