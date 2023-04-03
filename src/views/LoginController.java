package views;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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
            errorLabel.setText("Error, enter a User ID");
        }
        else {
        int id = Integer.parseInt(userIDTextField.getText());
        String pass = passwordField.getText();
        
        File f = null;
        FileInputStream fis = null;      
        ObjectInputStream ois = null;
        int idflag=0;
        int passflag=0;
        try {
            f = new File("EmpObjects.bin");
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            User tempUser;
            try{
                System.out.println("Printing objects");
                while(true){
                    if (idflag==1){break;}
                    tempUser = (User) ois.readObject();
                    System.out.println(tempUser.toString());
                    if (id==tempUser.ID){
                        idflag=1;
                        if (pass.equals(tempUser.getPassword())){
                            passflag=1;
                            break;
                        }
                    }
                }
            }
            catch(IOException | ClassNotFoundException e){
                System.out.println("IOException | ClassNotFoundException in reading bin file");
            }
            System.out.println("End of file\n");
            
            if (idflag==1){
                if(passflag==1){
                    errorLabel.setText("Login Successful");
                    //Stuff goes here
                }
                else{errorLabel.setText("Error, wrong password");}
            }
            else{errorLabel.setText("Error, user not found");}
        
        } catch (IOException ex) {System.out.println("IOException on entire file handling");}
        finally {
            try {
                if(ois != null) ois.close();
            } catch (IOException ex) { }
        }
        
    }
  }
}
