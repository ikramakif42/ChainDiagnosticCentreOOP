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
import users.AccountsOfficer;
import users.Director;
import users.Doctor;
import users.Patient;
import users.User;
import views.doctor.DoctorDashboardController;

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
//        try {
//            f = new File("UserObjects.bin");
//            if(f.exists()){
//                fos = new FileOutputStream(f,true);
//                oos = new AppendableObjectOutputStream(fos);                
//            }
//            else{
//                fos = new FileOutputStream(f);
//                oos = new ObjectOutputStream(fos);               
//            }
//            LocalDate date1 = LocalDate.of(2020, 2, 1);
//            Doctor doc1 = new Doctor("Senior Dr", "Ortho", 11000f, date1, "Dhaka", "A Doc1", 123, "Pass1", "Mail1", "Contact1", "Addr1", date1);
//            Doctor doc2 = new Doctor("Junior Dr", "Pedia", 22000f, date1, "Chittagong", "B Doc2", 456, "Pass2", "Mail2", "Contact2", "Addr2", date1);
//            Doctor doc3 = new Doctor("New Dr", "Neuro", 33000f, date1, "Dubai", "C Doc3", 789, "Pass2", "Mail3", "Contact3", "Addr3", date1);
//            oos.writeObject(doc1);
//            oos.writeObject(doc2);
//            oos.writeObject(doc3);
//            Patient p1 = new Patient("A P1", 147, "Pass1", "Mail1", "Contact1", "Addr1", date1);
//            Patient p2 = new Patient("B P2", 258, "Pass2", "Mail2", "Contact2", "Addr2", date1);
//            Patient p3 = new Patient("C P3", 369, "Pass3", "Mail3", "Contact3", "Addr3", date1);
//            oos.writeObject(p1);
//            oos.writeObject(p2);
//            oos.writeObject(p3);
//            AccountsOfficer a1 = new AccountsOfficer("Senior", "Accounts", 123f, date1, "Dhaka", "Abdul", 321, "PassA", "Mail", "conNO", "addr", date1);
//            oos.writeObject(a1);
//            Director d1 = new Director("Boss", "Dept", 123456789f, date1, "Dhaka", "Bossman", 654, "Pass", "mail", "contactNo", "Address", date1);
//            oos.writeObject(d1);
//
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
        Parent dashboard = null;
        switch(login){
            case 0: errorLabel.setText("Error, enter proper login info!"); break;        //Unhandled exception
            case 1: errorLabel.setText("Error, user not found"); break;                  //User not found in database
            case 2: errorLabel.setText("Error, wrong password"); break;                  //Authorisation failed
            case 3: 
                errorLabel.setText("Login Successful - Doctor");                         //Doctor authenticated
                FXMLLoader loader = new FXMLLoader(getClass().getResource("doctor/DoctorDashboard.fxml"));
                dashboard = (Parent) loader.load();
                Scene scene2 = new Scene(dashboard);
                
                DoctorDashboardController d = loader.getController();
                d.setDoc((Doctor) getInstance(id, pass));
                
                Stage stg2 = (Stage)((Node)event.getSource()).getScene().getWindow(); 
                stg2.setScene(scene2);
                stg2.show();
                break;
            case 4: errorLabel.setText("Login Successful - Patient"); break;             //Patient authenticated
//            case 5: errorLabel.setText("Login Successful - Pharmacist"); break;          //Pharmacist authenticated
//            case 6: errorLabel.setText("Login Successful - Nurse"); break;               //Nurse authenticated
            case 7: errorLabel.setText("Login Successful - Managing Director"); break;   //Managing Director authenticated
            case 8: errorLabel.setText("Login Successful - Accounts Officer"); break;    //Accounts Officer authenticated
//            case 9: errorLabel.setText("Login Successful - HR Officer"); break;          //HR Officer authenticated
//            case 10: errorLabel.setText("Login Successful - Lab Technician"); break;     //Lab Technician authenticated
        }
    }
  }
    
  private User getInstance(int id, String pass){
    File f = null;
    FileInputStream fis = null;      
    ObjectInputStream ois = null;
    try {
        f = new File("UserObjects.bin");
        fis = new FileInputStream(f);
        ois = new ObjectInputStream(fis);
        User tempUser;
        try{
            System.out.println("Printing objects");
            while(true){
                tempUser = (User) ois.readObject();
                System.out.println(tempUser.toString());
                if (id==tempUser.ID){
                    if (pass.equals(tempUser.getPassword())){
                        System.out.println("User found");
                        System.out.print("tempUser:");
                        System.out.println(tempUser);
                        return tempUser;
                    }
                }
            }
        }
        catch(IOException | ClassNotFoundException e){
            System.out.println("IOException | ClassNotFoundException in reading bin file");
        }
        System.out.println("End of file\n");
    } catch (IOException ex) {
        System.out.println("IOException on entire file handling");
    }
    finally {
        try {
            if(ois != null) ois.close();
        } catch (IOException ex) { }
    }
    return null;
  }
}
