package views.patient;

import java.io.IOException;
import java.net.URL;
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
import javafx.stage.Stage;
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
    private TableView<?> prescriptionTableView;
    @FXML
    private TableColumn<?, ?> medicineTableColumn;
    @FXML
    private TableColumn<?, ?> dosageTableColumn;
    @FXML
    private TableColumn<?, ?> durationTableColumn;
    @FXML
    private TextArea medicalHistoryTextArea;
    private Patient patient;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
