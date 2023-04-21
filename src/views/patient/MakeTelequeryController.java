package views.patient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import main.AppendableObjectOutputStream;
import model.TeleQuery;
import users.Patient;

public class MakeTelequeryController implements Initializable {

    @FXML
    private ComboBox<String> selectUserComboBox;
    @FXML
    private Label errorLabel;
    @FXML
    private TextArea queryTextArea;
    private Patient patient;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        errorLabel.setText("");
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
        if (usertype==null){errorLabel.setText("Error, select user type!");return;}
        String query = queryTextArea.getText();
        if (query==null){errorLabel.setText("Error, enter query!");return;}
        System.out.println("Entered info: "+usertype+", "+query);
        TeleQuery q = new TeleQuery(patient.getID(), usertype, query);
        writeQuery(q);
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

    private void writeQuery(TeleQuery q) throws IOException {
        File f = null;
        FileOutputStream fos = null;      
        ObjectOutputStream oos = null;
        try {
            f = new File("TeleQueryObjects.bin");
            if(f.exists()){
                fos = new FileOutputStream(f,true);
                oos = new AppendableObjectOutputStream(fos);                
            }
            else{
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);               
            }
            oos.writeObject(q);
        }
        catch (IOException ex){System.out.println(ex);}
        System.out.println("Query written");
        oos.close();
        }
}
