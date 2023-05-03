package views.patient;

import java.io.IOException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import users.Patient;
import users.User;

public class PatientSignUpController implements Initializable {

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField IDTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField genderTextField;
    @FXML
    private TextField contactTextField;
    @FXML
    private DatePicker DOBPicker;
    @FXML
    private TextField addressTextField;
    
    Alert idNumError = new Alert(AlertType.WARNING, "Error, ID must be a number only, less than 5 digits!");
    Alert idExistsError = new Alert(AlertType.WARNING, "Error, ID exists, choose another!");
    Alert passError = new Alert(AlertType.WARNING, "Error, password must be at least 6 characters long!");
    Alert emailError = new Alert(AlertType.WARNING, "Error, valid email address!");
    Alert failure = new Alert(AlertType.WARNING, "Error, sign up failed!");
    Alert failureNull = new Alert(AlertType.WARNING, "Error, fill up all fields!");
    Alert success = new Alert(AlertType.INFORMATION, "Sign up successful!");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DOBPicker.setDayCellFactory(dp -> new DateCell(){
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (date.isAfter(LocalDate.now())) {
                    setDisable(true);
                }
            }
        });
    }    

    @FXML
    private void signUpOnClick(ActionEvent event) {
        String name = nameTextField.getText();
        if (name.isEmpty()) {failureNull.show();return;}
        
        String id = IDTextField.getText();
        if (id.isEmpty()) {failureNull.show();return;}
        if (!User.isNumeric(id) || id.length()>=5) {idNumError.show();return;}
        if(User.userLogin(Integer.parseInt(id), "")==2) {idExistsError.show();return;}
        int ID = Integer.parseInt(id);
        
        String password = passwordTextField.getText();
        if (password.isEmpty()) {failureNull.show();return;}
        if (password.length()<6){passError.show();return;}
        
        String email = emailTextField.getText();
        if (email.isEmpty()) {failureNull.show();return;}
        if (!email.contains("@")){emailError.show();return;}
        
        String gender = genderTextField.getText();
        if (gender.isEmpty()) {failureNull.show();return;}
        String contactNo = contactTextField.getText();
        if (contactNo.isEmpty()) {failureNull.show();return;}
        LocalDate DOB = DOBPicker.getValue();
        if (DOB==null) {failureNull.show();return;}
        String address = addressTextField.getText();
        if (address.isEmpty()) {failureNull.show();return;}
        
        Patient toAdd = new Patient(name, ID, password, email, gender, contactNo, address, DOB);
        if (Patient.addPatient(toAdd)){success.show();}
        else {failure.show();}
    }

    @FXML
    private void returnToLoginOnClick(ActionEvent event) throws IOException {
        Parent login=null;
        try {
            login = FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(PatientSignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene1 = new Scene(login);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene1);
        window.show();
    }
}
