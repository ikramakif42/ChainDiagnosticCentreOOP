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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import users.Patient;

public class MakeTelequeryController implements Initializable {

    @FXML
    private ComboBox<String> selectUserComboBox;
    @FXML
    private TextArea queryTextArea;
    private Patient patient;
    Alert noUser = new Alert(Alert.AlertType.WARNING, "Error, select a user type!");
    Alert noQuery = new Alert(Alert.AlertType.WARNING, "Error, enter a query!");
    Alert failure = new Alert(Alert.AlertType.WARNING, "Error, submitting query failed!");
    Alert success = new Alert(Alert.AlertType.INFORMATION, "Telemedicine query submitted successfully!");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String[] temp = {"Doctor", "Pharmacist"};
        selectUserComboBox.getItems().addAll(temp);
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    
    @FXML
    private void submitTelequeryOnClick(ActionEvent event) throws IOException {
        String usertype = selectUserComboBox.getSelectionModel().getSelectedItem();
        if (usertype==null){noUser.show();return;}
        String query = queryTextArea.getText();
        if (query==null|query.isEmpty()){noQuery.show();return;}
        System.out.println("Entered info: "+usertype+", "+query);
        if (this.patient.writeQuery(usertype, query)){success.show();}
        else {failure.show();}
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
