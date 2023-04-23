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
        try {
            File file = new File("PatientObjects.bin");
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Patient> pats = new ArrayList<>();
            try{
                while(true){
                    Patient tempPat = (Patient) ois.readObject();
                    System.out.println(tempPat);
                    pats.add(tempPat);
                }
            }
            catch (EOFException eof){
                System.out.println("End of file");
            }
            catch(IOException | ClassNotFoundException e){
                System.out.println(e.toString());
                System.out.println("IOException | ClassNotFoundException in reading bin file");
            }
            ois.close();
            System.out.println(pats);

            for (Patient currentPat : pats) {
                if (currentPat.getID()==this.patient.getID()) {
                    currentPat.setName(newName);
                    currentPat.setEmail(newEmail);
                    currentPat.setAddress(newAddr);
                    currentPat.setContactNo(newContactNo);
                }
            }

            System.out.println(pats);
            if(file.delete()){
                System.out.println("Deleted Patients File!");
                File f = new File("PatientObjects.bin");
                FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                for (Patient currentPat : pats) {
                    oos.writeObject(currentPat);
                }
                oos.close();
                System.out.println("Fixed Patients File!");
            }
            else{
                System.out.println("Could not delete file");
                errorLabel.setText("Error, update info failed!");
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
            Logger.getLogger(UpdatePersonalInfoController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println(ex);
            Logger.getLogger(UpdatePersonalInfoController.class.getName()).log(Level.SEVERE, null, ex);
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
