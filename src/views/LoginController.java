package views;

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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import users.*;
import views.HROfficer.HRDashboardController;
import views.LabTechnician.LabTechnicianDashboardController;
import views.accountsofficer.AccountsOfficerDashboardController;
import views.director.DirectorDashboardController;
import views.doctor.DoctorDashboardController;
import views.nurse.NurseDashboardController;
import views.patient.PatientDashboardController;
import views.patient.PatientSignUpController;
import views.pharmacist.PharmacistDashboardController;

public class LoginController implements Initializable {

    @FXML
    private TextField userIDTextField;
    @FXML
    private PasswordField passwordField;
    Alert idError = new Alert(Alert.AlertType.WARNING, "Error, enter a valid ID!");
    Alert someError = new Alert(Alert.AlertType.WARNING, "Error, enter proper login info!");
    Alert noUser = new Alert(Alert.AlertType.WARNING, "Error, user not found");
    Alert wrongPW = new Alert(Alert.AlertType.WARNING, "Error, wrong password");
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    @FXML
    private void userLogin(ActionEvent event) throws IOException {
        if (userIDTextField.getText() == null || userIDTextField.getText().trim().isEmpty() || !User.isNumeric(userIDTextField.getText())){
            idError.show();return;
        }
        else {
        int id = Integer.parseInt(userIDTextField.getText());
        String pass = passwordField.getText();
        int login = User.userLogin(id, pass);
        
        switch(login){
            case 0: someError.show(); break;        //Some exception
            case 1: noUser.show(); break;                  //User not found in database
            case 2: wrongPW.show(); break;                  //Authorisation failed
            case 3: 
                System.out.println("Login Successful - Doctor");                         //Doctor authenticated
                Parent doctorDashboard = null;
                FXMLLoader doctorLoader = new FXMLLoader(getClass().getResource("doctor/DoctorDashboard.fxml"));
                doctorDashboard = (Parent) doctorLoader.load();
                Scene doctorScene = new Scene(doctorDashboard);
                
                DoctorDashboardController d = doctorLoader.getController();
                d.setDoc((Doctor) User.getInstance(id, "Doctor"));
                
                Stage doctorStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
                doctorStage.setScene(doctorScene);
                doctorStage.show();
                break;
            case 4: 
                System.out.println("Login Successful - Patient");                          //Patient authenticated
                Parent patientDashboard = null;
                FXMLLoader patientLoader = new FXMLLoader(getClass().getResource("patient/PatientDashboard.fxml"));
                patientDashboard = (Parent) patientLoader.load();
                Scene patientScene = new Scene(patientDashboard);
                
                PatientDashboardController p = patientLoader.getController();
                p.setPatient((Patient) User.getInstance(id, "Patient"));
                
                Stage patientStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
                patientStage.setScene(patientScene);
                patientStage.show();
                break;             
            case 5: 
                System.out.println("Login Successful - Pharmacist");                       //Pharmacist authenticated
                Parent pharmacistDashboard = null;
                FXMLLoader pharmaLoader = new FXMLLoader(getClass().getResource("pharmacist/PharmacistDashboard.fxml"));
                pharmacistDashboard = (Parent) pharmaLoader.load();
                Scene pharmaScene = new Scene(pharmacistDashboard);
                
                PharmacistDashboardController ph = pharmaLoader.getController();
                ph.setPharmacist((Pharmacist) User.getInstance(id, "Pharmacist"));
                
                Stage pharmaStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
                pharmaStage.setScene(pharmaScene);
                pharmaStage.show();
                break;
            case 6: 
                System.out.println("Login Successful - Nurse");                            //Nurse authenticated
                Parent nurseDashboard = null;
                FXMLLoader nurseLoader = new FXMLLoader(getClass().getResource("nurse/NurseDashboard.fxml"));
                nurseDashboard = (Parent) nurseLoader.load();
                Scene nurseScene = new Scene(nurseDashboard);
                
                NurseDashboardController nu = nurseLoader.getController();
                nu.setNurse((Nurse) User.getInstance(id, "Nurse"));
                
                Stage nurseStage  = (Stage)((Node)event.getSource()).getScene().getWindow();
                nurseStage.setScene(nurseScene);
                nurseStage.show();
                break;
            case 7: 
                System.out.println("Login Successful - Managing Director");                //Managing Director authenticated
                Parent directorDashboard = null;
                FXMLLoader directorLoader = new FXMLLoader(getClass().getResource("director/DirectorDashboard.fxml"));
                directorDashboard = (Parent) directorLoader.load();
                Scene directorScene = new Scene(directorDashboard);
                
                DirectorDashboardController di = directorLoader.getController();
                di.setDirector((Director) User.getInstance(id, "Director"));
                
                Stage directorStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
                directorStage.setScene(directorScene);
                directorStage.show();            
                break;
            case 8: 
                System.out.println("Login Successful - Accounts Officer");                  //Accounts Officer authenticated
                Parent accountsOfficerDashboard = null;
                FXMLLoader accountsLoader = new FXMLLoader(getClass().getResource("accountsofficer/AccountsOfficerDashboard.fxml"));
                accountsOfficerDashboard = (Parent) accountsLoader.load();
                Scene accountsScene = new Scene(accountsOfficerDashboard);
                
                AccountsOfficerDashboardController a = accountsLoader.getController();
                a.setOfficer((AccountsOfficer) User.getInstance(id, "AccountsOfficer"));
                
                Stage accountsStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
                accountsStage.setScene(accountsScene);
                accountsStage.show();
                break;    
            case 9: 
                System.out.println("Login Successful - HR Officer");                       //HR Officer authenticated
                Parent HRDashboard = null;
                FXMLLoader HRLoader = new FXMLLoader(getClass().getResource("HROfficer/HRDashboard.fxml"));
                HRDashboard = (Parent) HRLoader.load();
                Scene HRScene = new Scene(HRDashboard);
                
                HRDashboardController hr = HRLoader.getController();
                hr.setHR((HROfficer) User.getInstance(id, "HROfficer"));
                
                Stage hrStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
                hrStage.setScene(HRScene);
                hrStage.show();
                break;
            case 10: 
                System.out.println("Login Successful - Lab Technician");                   //Lab Technician authenticated
                Parent LabTechnicianDashboard = null;
                FXMLLoader labLoader = new FXMLLoader(getClass().getResource("LabTechnician/LabTechnicianDashboard.fxml"));
                LabTechnicianDashboard = (Parent) labLoader.load();
                Scene labScene = new Scene(LabTechnicianDashboard);
                
                LabTechnicianDashboardController l = labLoader.getController();
                l.setLabTechnician((LabTechnician) User.getInstance(id, "LabTechnician"));
                
                Stage labStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
                labStage.setScene(labScene);
                labStage.show();        
                break;
        }
    }
  }

    @FXML
    private void patientSignUpOnClick(ActionEvent event) throws IOException {
        Parent signUp = null;
        FXMLLoader signUpLoader = new FXMLLoader(getClass().getResource("patient/PatientSignUp.fxml"));
        signUp = (Parent) signUpLoader.load();
        Scene signUpScene = new Scene(signUp);

        PatientSignUpController u = signUpLoader.getController();

        Stage signUpStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        signUpStage.setScene(signUpScene);
        signUpStage.show();
    }
}
