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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Prescription;
import users.Patient;

public class PatientMyProfileController implements Initializable {

    @FXML
    private Label IDLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label contactNoLabel;
    @FXML
    private Label DOBLabel;
    @FXML
    private TableView<Prescription> prescriptionTableView;
    @FXML
    private TableColumn<Prescription, String> medicineTableColumn;
    @FXML
    private TableColumn<Prescription, String> dosageTableColumn;
    @FXML
    private TableColumn<Prescription, String> durationTableColumn;
    @FXML
    private TextArea medicalHistoryTextArea;
    @FXML
    private Label genderLabel;
    private Patient patient;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        medicineTableColumn.setCellValueFactory(new PropertyValueFactory<>("medName"));
        dosageTableColumn.setCellValueFactory(new PropertyValueFactory<>("dosage"));
        durationTableColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
        
        IDLabel.setText(Integer.toString(patient.getID()));
        nameLabel.setText(patient.getName());
        emailLabel.setText(patient.getEmail());
        addressLabel.setText(patient.getAddress());
        contactNoLabel.setText(patient.getContactNo());
        DOBLabel.setText(patient.getDOB().toString());
        genderLabel.setText(patient.getGender());
        
        prescriptionTableView.setItems(this.patient.getPrescriptions());
        if (this.patient.getMedicalRecords().isEmpty()){medicalHistoryTextArea.setText("No past records!");}
        else{
            ArrayList<String> recordList = this.patient.getMedicalRecords();
            for (String rec : recordList){
                medicalHistoryTextArea.appendText(rec+"\n");
            }
        }
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
    private void updatePersonalInfoOnClick(ActionEvent event) throws IOException {
        Parent root = null;
        FXMLLoader updateLoader = new FXMLLoader(getClass().getResource("UpdatePersonalInfo.fxml"));
        root = (Parent) updateLoader.load();
        Scene updateScene = new Scene(root);

        UpdatePersonalInfoController u = updateLoader.getController();
        u.setPatient(this.patient);

        Stage updateStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        updateStage.setScene(updateScene);
        updateStage.show();
    }
    
}
