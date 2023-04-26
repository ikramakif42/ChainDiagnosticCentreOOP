package views.patient;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import users.Patient;

public class UpdatePersonalInfoController implements Initializable {
    @FXML
    private TextField nameTextField;
    @FXML
    private Label IDLabel;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField contactNoTextField;
    @FXML
    private Label DOBLabel;
    @FXML
    private TextField addressTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorLabel;
    @FXML
    private Label genderLabel;
    private Patient patient;

    @Override
    public void initialize(URL url, ResourceBundle rb) {}    

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
        IDLabel.setText(Integer.toString(patient.getID()));
        nameTextField.setText(patient.getName());
        emailTextField.setText(patient.getEmail());
        addressTextField.setText(patient.getAddress());
        contactNoTextField.setText(patient.getContactNo());
        DOBLabel.setText(patient.getDOB().toString());
        genderLabel.setText(patient.getGender());
    }
    
    @FXML
    private void updatePersonalInfoOnClick(ActionEvent event){
        String pw = passwordField.getText();
        if (!pw.equals(patient.getPassword())) {
            errorLabel.setText("Error, wrong password!");
            System.out.println(patient.getPassword());
            return;
        }
        
        String newName = nameTextField.getText();
        String newEmail = emailTextField.getText();
        String newAddr = addressTextField.getText();
        String newContactNo = contactNoTextField.getText();
        if (!newEmail.contains("@")){
            errorLabel.setText("Error, enter valid email!");
            return;
        }
        
        System.out.println("New Info collected: "+newName+", "+newEmail+", "+newAddr+", "+newContactNo+", ");
        if (this.patient.updatePersonalInfo(newName, newEmail, newAddr, newContactNo)){
            errorLabel.setText("Info updated successfully!");
        }
        else {
            errorLabel.setText("Error, update info failed!");
        }
    }

    @FXML
    private void returnToMyProfileOnClick(ActionEvent event) throws IOException {
        Parent root = null;
        FXMLLoader profileLoader = new FXMLLoader(getClass().getResource("PatientMyProfile.fxml"));
        root = (Parent) profileLoader.load();
        Scene profileScene = new Scene(root);

        PatientMyProfileController p = profileLoader.getController();
        p.setPatient(this.patient);

        Stage profileStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        profileStage.setScene(profileScene);
        profileStage.show();
    }
}
