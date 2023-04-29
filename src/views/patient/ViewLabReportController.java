package views.patient;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.LabReport;
import users.Patient;

public class ViewLabReportController implements Initializable {

    @FXML
    private TableView<LabReport> reportTableView;
    @FXML
    private TableColumn<LabReport, String> titleTableColumn;
    @FXML
    private TableColumn<LabReport, LocalDate> dateTableColumn;
    @FXML
    private TableColumn<LabReport, String> authorTableColumn;
    @FXML
    private TextArea detailsTextArea;
    private Patient patient;
    
    Alert noReport = new Alert(AlertType.WARNING, "Error, select a report to view!");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        titleTableColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        dateTableColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        authorTableColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
        reportTableView.setItems(this.patient.getReportList());
    }

    @FXML
    private void loadReportOnClick(ActionEvent event) {
        LabReport reportToLoad = reportTableView.getSelectionModel().getSelectedItem();
        if (reportToLoad==null) {noReport.show();return;}
        detailsTextArea.setText(reportToLoad.toStringDisplay());
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
