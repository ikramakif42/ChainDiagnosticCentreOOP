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
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Prescription;
import users.Patient;

public class RefillRequestController implements Initializable {

    @FXML
    private TableView<Prescription> prescriptionTableView;
    @FXML
    private TableColumn<Prescription, String> medicineTableColumn;
    @FXML
    private TableColumn<Prescription, String> dosageTableColumn;
    @FXML
    private TableColumn<Prescription, String> durationTableColumn;
    private Patient patient;
    Alert success = new Alert(Alert.AlertType.INFORMATION, "Refill requested successfully!");
    Alert noMed = new Alert(Alert.AlertType.WARNING, "Error, select a medicine to request refill!");
    Alert error = new Alert(Alert.AlertType.WARNING, "Error, refill request failed!");

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
        prescriptionTableView.setItems(this.patient.getPrescriptions());
    }

    @FXML
    private void submitRefillRequestOnClick(ActionEvent event) {
        Prescription refill = prescriptionTableView.getSelectionModel().getSelectedItem();
        if (refill==null){noMed.show();return;}
        if (this.patient.submitRefill(refill)) {success.show();}
        else {error.show();}
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
    
}
