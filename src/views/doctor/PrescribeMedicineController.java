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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Prescription;
import users.Doctor;
import users.Patient;

public class PrescribeMedicineController implements Initializable {

    @FXML
    private TableView<Prescription> prescriptionTableView;
    @FXML
    private TableColumn<Prescription, String> medicineTableColumn;
    @FXML
    private TableColumn<Prescription, String> dosageTableColumn;
    @FXML
    private TableColumn<Prescription, String> durationTableColumn;
    @FXML
    private Label patientIDLabel;
    @FXML
    private Label patientNameLabel;
    @FXML
    private Label patientAgeLabel;
    @FXML
    private Label errorLabel;
    @FXML
    private TextField medNameTextField;
    @FXML
    private TextField dosageTextField;
    @FXML
    private TextField durationTextField;
    
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
        patientIDLabel.setText(String.valueOf(pat.getID()));
        patientNameLabel.setText(pat.getName());
        patientAgeLabel.setText(String.valueOf(Period.between(pat.getDOB(), LocalDate.now()).getYears()));
        prescriptionTableView.setItems(pat.getPrescriptions());
    }

    @FXML
    private void addNewPrescriptionOnClick(ActionEvent event) {
        String medName = medNameTextField.getText();
        if (medName.isEmpty()){errorLabel.setText("Error, enter medicine name!");return;}
        String dosage = dosageTextField.getText();
        if (dosage.isEmpty()){errorLabel.setText("Error, enter medicine dosage!");return;}
        String duration = durationTextField.getText();
        if (duration.isEmpty()){errorLabel.setText("Error, enter medicine duration!");return;}
        
        this.doc.prescribeMed(medName, dosage, duration, this.pat.getID());
        errorLabel.setText("Medicine prescribed successfully!");
        prescriptionTableView.setItems(pat.getPrescriptions());
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
