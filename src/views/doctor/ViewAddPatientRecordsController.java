package views.doctor;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Prescription;
import users.Doctor;
import users.Patient;
import users.User;

public class ViewAddPatientRecordsController implements Initializable {

    @FXML
    private TableView<Prescription> prescriptionTableView;
    @FXML
    private TableColumn<Prescription, String> medicineTableColumn;
    @FXML
    private TableColumn<Prescription, String> dosageTableColumn;
    @FXML
    private TableColumn<Prescription, String> durationTableColumn;
    @FXML
    private TextArea historyTextArea;
    @FXML
    private TextArea newRecordTextArea;
    @FXML
    private Label patientIDLabel;
    @FXML
    private Label patientNameLabel;
    @FXML
    private Label patientAgeLabel;
    private Doctor doc;
    private Patient pat;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        medicineTableColumn.setCellValueFactory(new PropertyValueFactory<>("medName"));
        dosageTableColumn.setCellValueFactory(new PropertyValueFactory<>("dosage"));
        durationTableColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
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
        System.out.println("Loadedpat: "+pat.toString());
        patientIDLabel.setText(String.valueOf(pat.getID()));
        patientNameLabel.setText(pat.getName());
        patientAgeLabel.setText(String.valueOf(Period.between(pat.getDOB(), LocalDate.now()).getYears()));
        prescriptionTableView.setItems(pat.getPrescriptions());
        if (this.pat.getMedicalRecords().isEmpty()){historyTextArea.setText("No past records!");}
        else{
            ArrayList<String> recordList = this.pat.getMedicalRecords();
            for (String rec : recordList){
                historyTextArea.appendText(rec+"\n");
            }
        }
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

    @FXML
    private void addNewRecordsOnClick(ActionEvent event) {
        System.out.println("PAT: "+this.pat.getMedicalRecords());
        String newRecord = newRecordTextArea.getText();
        if (newRecord.isEmpty() || newRecord.equals("New record added successfully!") || newRecord.equals("Error, enter new record!!")){
            newRecordTextArea.setText("Error, enter new record!!");
            return;
        }
        this.pat.updatePersonalInfo(newRecord);
        newRecordTextArea.setText("New record added successfully!");
        
        historyTextArea.clear();
        this.pat = (Patient)User.getInstance(this.pat.getID(), "Patient");
        ArrayList<String> recordList = this.pat.getMedicalRecords();
        for (String rec : recordList){
            historyTextArea.appendText(rec+"\n");
        }
    }

    @FXML
    private void textAreaOnClick(MouseEvent event) {
        String newRecord = newRecordTextArea.getText();
        if (newRecord.equals("New record added successfully!") || newRecord.equals("Error, enter new record!!"))
        {newRecordTextArea.clear();}
    }
    
}
