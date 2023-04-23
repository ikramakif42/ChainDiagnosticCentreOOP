package views;

//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.time.LocalDate;
//import main.AppendableObjectOutputStream;
//import model.LoginInfo;
//import java.util.logging.Level;
//import java.util.logging.Logger;

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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import users.*;
import views.accountsofficer.AccountsOfficerDashboardController;
import views.director.DirectorDashboardController;
import views.doctor.DoctorDashboardController;
import views.nurse.NurseDashboardController;
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
//        LocalDate date1 = LocalDate.of(2001, 2, 1);
//        try {
//            f = new File("LoginInfoObjects.bin");
//            if(f.exists()){
//                fos = new FileOutputStream(f,true);
//                oos = new AppendableObjectOutputStream(fos);                
//            }
//            else{
//                fos = new FileOutputStream(f);
//                oos = new ObjectOutputStream(fos);               
//            }
////            LoginInfo l1 = new LoginInfo(111, "Pass1", "AccountsOfficer");
////            LoginInfo l2 = new LoginInfo(222, "Pass1", "AccountsOfficer");
////            LoginInfo l3 = new LoginInfo(333, "Pass1", "AccountsOfficer");
////            LoginInfo l4 = new LoginInfo(444, "Pass1", "Director");
////            LoginInfo l5 = new LoginInfo(555, "Pass1", "Director");
////            LoginInfo l6 = new LoginInfo(666, "Pass1", "Director");
////            LoginInfo l7 = new LoginInfo(123, "Pass1", "Doctor");
////            LoginInfo l8 = new LoginInfo(456, "Pass1", "Doctor");
////            LoginInfo l9 = new LoginInfo(789, "Pass1", "Doctor");
////            LoginInfo l10 = new LoginInfo(777, "Pass1", "HROfficer");
////            LoginInfo l11 = new LoginInfo(888, "Pass1", "HROfficer");
////            LoginInfo l12 = new LoginInfo(999, "Pass1", "HROfficer");
////            LoginInfo l13 = new LoginInfo(321, "Pass1", "LabTechnician");
////            LoginInfo l14 = new LoginInfo(654, "Pass1", "LabTechnician");
////            LoginInfo l15 = new LoginInfo(987, "Pass1", "LabTechnician");
////            LoginInfo l16 = new LoginInfo(741, "Pass1", "Nurse");
////            LoginInfo l17 = new LoginInfo(852, "Pass1", "Nurse");
////            LoginInfo l18 = new LoginInfo(963, "Pass1", "Nurse");
////            LoginInfo l19 = new LoginInfo(147, "Pass1", "Patient");
////            LoginInfo l20 = new LoginInfo(258, "Pass1", "Patient");
////            LoginInfo l21 = new LoginInfo(369, "Pass1", "Patient");
////            LoginInfo l22 = new LoginInfo(101, "Pass1", "Pharmacist");
////            LoginInfo l23 = new LoginInfo(202, "Pass1", "Pharmacist");
////            LoginInfo l24 = new LoginInfo(303, "Pass1", "Pharmacist");
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
////            oos.writeObject(l13);
////            oos.writeObject(l14);
////            oos.writeObject(l15);
////            oos.writeObject(l16);
////            oos.writeObject(l17);
////            oos.writeObject(l18);
////            oos.writeObject(l19);
////            oos.writeObject(l20);
////            oos.writeObject(l21);
////            oos.writeObject(l22);
////            oos.writeObject(l23);
////            oos.writeObject(l24);
////
////
////            AccountsOfficer a1 = new AccountsOfficer("Senior", "Accounts", 123f, date1, "Dhaka", "Abdul", 111, "Pass1", "Mail", "Male", "conNO", "addr", date1);
////            AccountsOfficer a2 = new AccountsOfficer("Junior", "Accounts", 456f, date1, "Dhaka", "Bashar", 222, "Pass1", "Mail", "Female", "conNO", "addr", date1);
////            AccountsOfficer a3 = new AccountsOfficer("intern", "Accounts", 789f, date1, "Dhaka", "Karim", 333, "Pass1", "Mail", "Male", "conNO", "addr", date1);
////            oos.writeObject(a1);
////            oos.writeObject(a2);
////            oos.writeObject(a3);
////
////            Director d1 = new Director("Boss", "Dept", 123456789f, date1, "Dhaka", "Bossman", 444, "Pass1", "mail", "Male", "contactNo", "Address", date1);
////            Director d2 = new Director("Sir", "Dept", 1234789f, date1, "Dhaka", "Sirman", 555, "Pass1", "mail", "Female", "contactNo", "Address", date1);
////            Director d3 = new Director("Ruler", "Dept", 1345789f, date1, "Dhaka", "Ruler Guy", 666, "Pass1", "mail", "Male", "contactNo", "Address", date1);
////            oos.writeObject(d1);
////            oos.writeObject(d2);
////            oos.writeObject(d3);
////
////            Doctor doc1 = new Doctor("Senior Dr", "Ortho", 11000f, date1, "Dhaka", "A Doc1", 123, "Pass1", "Mail1", "Male", "Contact1", "Addr1", date1);
////            Doctor doc2 = new Doctor("Junior Dr", "Pedia", 22000f, date1, "Chittagong", "B Doc2", 456, "Pass1", "Mail2", "Male", "Contact2", "Addr2", date1);
////            Doctor doc3 = new Doctor("New Dr", "Neuro", 33000f, date1, "Dubai", "C Doc3", 789, "Pass1", "Mail3", "Female", "Contact3", "Addr3", date1);
////            oos.writeObject(doc1);
////            oos.writeObject(doc2);
////            oos.writeObject(doc3);
////
////            HROfficer hr1 = new HROfficer("Senior", "Ortho", 11000f, date1, "Dhaka", "Toby", 777, "Pass1", "Mail1", "Male", "Contact1", "Addr1", date1);
////            HROfficer hr2 = new HROfficer("Senior", "Pedia", 22000f, date1, "Chittagong", "Scott", 888, "Pass1", "Mail2", "Male", "Contact2", "Addr2", date1);
////            HROfficer hr3 = new HROfficer("Junior", "Neuro", 33000f, date1, "Dubai", "Jim", 999, "Pass1", "Mail3", "Female", "Contact3", "Addr3", date1);
////            oos.writeObject(hr1);
////            oos.writeObject(hr2);
////            oos.writeObject(hr3);
////
////            LabTechnician lt1 = new LabTechnician("Big", "Bio", 11000f, date1, "Dhaka", "Einstein", 321, "Pass1", "Mail1", "Male", "Contact1", "Addr1", date1);
////            LabTechnician lt2 = new LabTechnician("Senior", "Radio", 22000f, date1, "Chittagong", "Oppenheimer", 654, "Pass1", "Mail2", "Male", "Contact2", "Addr2", date1);
////            LabTechnician lt3 = new LabTechnician("Smol", "Neuro", 33000f, date1, "Dubai", "Sokina", 987, "Pass1", "Mail3", "Female", "Contact3", "Addr3", date1);
////            oos.writeObject(lt1);
////            oos.writeObject(lt2);
////            oos.writeObject(lt3);
////
////            Nurse n1 = new Nurse("Big", "ER", 11000f, date1, "Dhaka", "Nightingale", 741, "Pass1", "Mail1", "Female", "Contact1", "Addr1", date1);
////            Nurse n2 = new Nurse("Senior", "Attending", 22000f, date1, "Chittagong", "Joy", 852, "Pass1", "Mail2", "Female", "Contact2", "Addr2", date1);
////            Nurse n3 = new Nurse("Smol", "ER", 33000f, date1, "Dubai", "John", 963, "Pass1", "Mail3", "Male", "Contact3", "Addr3", date1);
////            oos.writeObject(n1);
////            oos.writeObject(n2);
////            oos.writeObject(n3);
////
////            Patient p1 = new Patient("A P1", 147, "Pass1", "Mail1", "Male", "Contact1", "Addr1", date1);
////            Patient p2 = new Patient("B P2", 258, "Pass2", "Mail2", "Male", "Contact2", "Addr2", date1);
////            Patient p3 = new Patient("C P3", 369, "Pass3", "Mail3", "Female", "Contact3", "Addr3", date1);
////            oos.writeObject(p1);
////            oos.writeObject(p2);
////            oos.writeObject(p3);
////
////            Pharmacist ph1 = new Pharmacist("Dispenser", "Bio", 11000f, date1, "Dhaka", "Heisenberg", 101, "Pass1", "Mail1", "Male", "Contact1", "Addr1", date1);
////            Pharmacist ph2 = new Pharmacist("Packager", "Radio", 22000f, date1, "Chittagong", "Escobar", 202, "Pass1", "Mail2", "Male", "Contact2", "Addr2", date1);
////            Pharmacist ph3 = new Pharmacist("Clerk", "Neuro", 33000f, date1, "Dubai", "Skyler", 303, "Pass1", "Mail3", "Female", "Contact3", "Addr3", date1);
////            oos.writeObject(ph1);
////            oos.writeObject(ph2);
////            oos.writeObject(ph3);
////
////
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
            case 0: errorLabel.setText("Error, enter proper login info!"); break;        //Some exception
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
            case 5: 
                errorLabel.setText("Login Successful - Pharmacist");                       //Pharmacist authenticated
//                Parent pharmacistDashboard = null;
//                FXMLLoader pharmaLoader = new FXMLLoader(getClass().getResource("pharmacist/PharmacistDashboard.fxml"));
//                pharmacistDashboard = (Parent) pharmaLoader.load();
//                Scene pharmaScene = new Scene(pharmacistDashboard);
//                
//                PharmacistDashboardController ph = pharmaLoader.getController();
//                ph.setPharmacist((Pharmacist) User.getInstance(id, "Pharmacist"));
//                
//                Stage pharmaStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
//                pharmaStage.setScene(pharmaScene);
//                pharmaStage.show();
                break;
            case 6: 
                errorLabel.setText("Login Successful - Nurse");                            //Nurse authenticated
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
                a.setOfficer((AccountsOfficer) User.getInstance(id, "AccountsOfficer"));
                
                Stage accountsStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
                accountsStage.setScene(accountsScene);
                accountsStage.show();
                break;    
            case 9: 
                errorLabel.setText("Login Successful - HR Officer");                       //HR Officer authenticated
//                Parent HRDashboard = null;
//                FXMLLoader HRLoader = new FXMLLoader(getClass().getResource("HROfficer/HR_Profile.fxml"));
//                HRDashboard = (Parent) HRLoader.load();
//                Scene HRScene = new Scene(HRDashboard);
//                
//                HR_ProfileController hr = HRLoader.getController();
//                hr.setOfficer((HROfficer) User.getInstance(id, "HROfficer"));
//                
//                Stage hrStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
//                hrStage.setScene(HRScene);
//                hrStage.show();
                break;
            case 10: 
                errorLabel.setText("Login Successful - Lab Technician");                   //Lab Technician authenticated
//                Parent labTechnicianDashboard = null;
//                FXMLLoader labLoader = new FXMLLoader(getClass().getResource("???.fxml"));
//                labTechnicianDashboard = (Parent) labLoader.load();
//                Scene labScene = new Scene(labTechnicianDashboard);
//                
//                AccountsOfficerDashboardController l = labLoader.getController();
//                l.setLabTechnician((LabTechnician) User.getInstance(id, "LabTechnician"));
//                
//                Stage labStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
//                labStage.setScene(labScene);
//                labStage.show();
                break;
        }
    }
  }
}
