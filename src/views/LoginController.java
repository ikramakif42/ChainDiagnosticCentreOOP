package views;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.time.LocalDate;
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
import main.AppendableObjectOutputStream;
import model.LoginInfo;
import users.AccountsOfficer;
import users.Director;
import users.Doctor;
import users.Patient;
import users.User;
import views.accountsofficer.AccountsOfficerDashboardController;
import views.director.DirectorDashboardController;
import views.doctor.DoctorDashboardController;
import views.patient.PatientDashboardController;

public class LoginController implements Initializable {

    @FXML
    private TextField userIDTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        File f = null;
//        FileOutputStream fos = null;      
//        ObjectOutputStream oos = null;
//        LocalDate date1 = LocalDate.of(2020, 2, 1);
//        try {
//            f = new File("DirectorObjects.bin");
//            if(f.exists()){
//                fos = new FileOutputStream(f,true);
//                oos = new AppendableObjectOutputStream(fos);                
//            }
//            else{
//                fos = new FileOutputStream(f);
//                oos = new ObjectOutputStream(fos);               
//            }
////            LoginInfo l1 = new LoginInfo(123, "Pass1", "Doctor");
////            LoginInfo l2 = new LoginInfo(456, "Pass2", "Doctor");
////            LoginInfo l3 = new LoginInfo(789, "Pass2", "Doctor");
////            LoginInfo l4 = new LoginInfo(147, "Pass1", "Patient");
////            LoginInfo l5 = new LoginInfo(258, "Pass2", "Patient");
////            LoginInfo l6 = new LoginInfo(369, "Pass4", "Patient");
////            LoginInfo l7 = new LoginInfo(111, "PassA", "Accounts Officer");
////            LoginInfo l8 = new LoginInfo(222, "PassA", "Accounts Officer");
////            LoginInfo l9 = new LoginInfo(333, "PassA", "Accounts Officer");
////            LoginInfo l10 = new LoginInfo(444, "Pass", "Director");
////            LoginInfo l11 = new LoginInfo(555, "Pass", "Director");
////            LoginInfo l12 = new LoginInfo(666, "Pass", "Director");
////            oos.writeObject(l1);
////            oos.writeObject(l2);
////            oos.writeObject(l3);
////            oos.writeObject(l4);
////            oos.writeObject(l5);
////            oos.writeObject(l6);
////            oos.writeObject(l7);
////            oos.writeObject(l8);
////            oos.writeObject(l9);
////            oos.writeObject(l10);
////            oos.writeObject(l11);
////            oos.writeObject(l12);
////            Doctor doc1 = new Doctor("Senior Dr", "Ortho", 11000f, date1, "Dhaka", "A Doc1", 123, "Pass1", "Mail1", "Contact1", "Addr1", date1);
////            Doctor doc2 = new Doctor("Junior Dr", "Pedia", 22000f, date1, "Chittagong", "B Doc2", 456, "Pass2", "Mail2", "Contact2", "Addr2", date1);
////            Doctor doc3 = new Doctor("New Dr", "Neuro", 33000f, date1, "Dubai", "C Doc3", 789, "Pass2", "Mail3", "Contact3", "Addr3", date1);
////            oos.writeObject(doc1);
////            oos.writeObject(doc2);
////            oos.writeObject(doc3);
////            Patient p1 = new Patient("A P1", 147, "Pass1", "Mail1", "Contact1", "Addr1", date1);
////            Patient p2 = new Patient("B P2", 258, "Pass2", "Mail2", "Contact2", "Addr2", date1);
////            Patient p3 = new Patient("C P3", 369, "Pass3", "Mail3", "Contact3", "Addr3", date1);
////            oos.writeObject(p1);
////            oos.writeObject(p2);
////            oos.writeObject(p3);
////            AccountsOfficer a1 = new AccountsOfficer("Senior", "Accounts", 123f, date1, "Dhaka", "Abdul", 111, "PassA", "Mail", "conNO", "addr", date1);
////            AccountsOfficer a2 = new AccountsOfficer("Junior", "Accounts", 456f, date1, "Dhaka", "Bashar", 222, "PassA", "Mail", "conNO", "addr", date1);
////            AccountsOfficer a3 = new AccountsOfficer("intern", "Accounts", 789f, date1, "Dhaka", "Karim", 333, "PassA", "Mail", "conNO", "addr", date1);
////            oos.writeObject(a1);
////            oos.writeObject(a2);
////            oos.writeObject(a3);
////            Director d1 = new Director("Boss", "Dept", 123456789f, date1, "Dhaka", "Bossman", 444, "Pass", "mail", "contactNo", "Address", date1);
////            Director d2 = new Director("Sir", "Dept", 1234789f, date1, "Dhaka", "Sirman", 555, "Pass", "mail", "contactNo", "Address", date1);
////            Director d3 = new Director("Ruler", "Dept", 1345789f, date1, "Dhaka", "Ruler Guy", 666, "Pass", "mail", "contactNo", "Address", date1);
////            oos.writeObject(d1);
////            oos.writeObject(d2);
////            oos.writeObject(d3);
//        } catch (IOException ex) {
//            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                if(oos != null) oos.close();
//            } catch (IOException ex) {
//                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        System.out.println("Hello World2! Initialised");
    }
    
    @FXML
    private void userLogin(ActionEvent event) throws IOException {
        if (userIDTextField.getText() == null || userIDTextField.getText().trim().isEmpty()){
            errorLabel.setText("Error, enter a User ID");                                //Empty text field for User ID
        }
        
        else {
        int id = Integer.parseInt(userIDTextField.getText());
        String pass = passwordField.getText();
        int login = User.userLogin(id, pass);
        
        switch(login){
            case 0: errorLabel.setText("Error, enter proper login info!"); break;        //Unhandled exception
            case 1: errorLabel.setText("Error, user not found"); break;                  //User not found in database
            case 2: errorLabel.setText("Error, wrong password"); break;                  //Authorisation failed
            case 3: 
                errorLabel.setText("Login Successful - Doctor");                         //Doctor authenticated
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
                errorLabel.setText("Login Successful - Patient");                          //Patient authenticated
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
//            case 5: errorLabel.setText("Login Successful - Pharmacist"); break;          //Pharmacist authenticated
//            case 6: errorLabel.setText("Login Successful - Nurse"); break;               //Nurse authenticated
            case 7: 
                errorLabel.setText("Login Successful - Managing Director");                //Managing Director authenticated
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
                errorLabel.setText("Login Successful - Accounts Officer");                  //Accounts Officer authenticated
                Parent accountsOfficerDashboard = null;
                FXMLLoader accountsLoader = new FXMLLoader(getClass().getResource("accountsofficer/AccountsOfficerDashboard.fxml"));
                accountsOfficerDashboard = (Parent) accountsLoader.load();
                Scene accountsScene = new Scene(accountsOfficerDashboard);
                
                AccountsOfficerDashboardController a = accountsLoader.getController();
                a.setOfficer((AccountsOfficer) User.getInstance(id, "Accounts Officer"));
                
                Stage accountsStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
                accountsStage.setScene(accountsScene);
                accountsStage.show();
                break;    
//            case 9: errorLabel.setText("Login Successful - HR Officer"); break;          //HR Officer authenticated
//            case 10: errorLabel.setText("Login Successful - Lab Technician"); break;     //Lab Technician authenticated
        }
    }
  }
}
