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
import users.Doctor;
import users.User;

public class LoginController implements Initializable {

    @FXML
    private TextField userIDTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    @FXML
    private void userLogin(ActionEvent event) {
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
            case 3: errorLabel.setText("Login Successful - Doctor"); break;              //Doctor authenticated
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
}
