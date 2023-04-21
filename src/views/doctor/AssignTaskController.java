package views.doctor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import users.AccountsOfficer;
import users.Director;
import users.Doctor;
import users.Patient;
import users.User;
import views.LoginController;

public class AssignTaskController implements Initializable {

    private Doctor doc;
    @FXML
    private ComboBox<String> userTypeComboBox;
    @FXML
    private ComboBox<String> userNameComboBox;
    @FXML
    private TextArea taskTextArea;
    @FXML
    private Label errorLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String[] temp = {"Nurse", "Technician", "Pharmacist", "Patient"};
        userTypeComboBox.getItems().addAll(temp);
    }

    public Doctor getDoc() {
        return doc;
    }

    public void setDoc(Doctor doc) {
        this.doc = doc;
    }
    
    @FXML
    private void returnToDashboardOnClick(ActionEvent event) throws IOException {
        Parent doctorDashboard = null;
        FXMLLoader doctorLoader = new FXMLLoader(getClass().getResource("DoctorDashboard.fxml"));
        doctorDashboard = (Parent) doctorLoader.load();
        Scene doctorScene = new Scene(doctorDashboard);

        DoctorDashboardController d = doctorLoader.getController();
        d.setDoc(this.doc);

        Stage doctorStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        doctorStage.setScene(doctorScene);
        doctorStage.show();
    }

    @FXML
    private void assignTaskOnClick(ActionEvent event) {
        errorLabel.setText("");
        String usertype = userTypeComboBox.getSelectionModel().getSelectedItem();
        String username = userNameComboBox.getSelectionModel().getSelectedItem();
        String task = taskTextArea.getText();
        if (usertype==null){errorLabel.setText("Error, select user type");return;}
        else if (username==null){errorLabel.setText("Error, select user name");return;}
        else if (task.isEmpty()){errorLabel.setText("Error, enter task");return;}
        System.out.println(usertype+username+task);
        String[] nameID = username.split(" ");System.out.println(nameID);
        User assigned = User.getInstance(Integer.parseInt(nameID[nameID.length-1]), usertype);
        System.out.println(assigned.toString());
    }

    @FXML
    private void updateUserName(MouseEvent event) {
        String usertype = userTypeComboBox.getSelectionModel().getSelectedItem();
        System.out.println(usertype);
        if (!usertype.isEmpty()){
            ArrayList<String> usersToAdd = new ArrayList<>();
            File f = null;
            FileInputStream fis = null;      
            ObjectInputStream ois = null;
            String path = "";
            switch(usertype){
                case "Doctor":
                    path="DoctorObjects.bin";
                    break;
                case "Patient":
                    path="PatientObjects.bin";
                    break;
                case "Director":
                    path="DirectorObjects.bin";
                    break;
                case "Accounts Officer":
                    path="AccountsOfficerObjects.bin";
                    break;
            }
            try {
                f = new File(path);
                fis = new FileInputStream(f);
                ois = new ObjectInputStream(fis);
                User tempUser = null;
                try{
                    System.out.println("Printing objects");
                    while(true){
                        switch(usertype){
                            case "Doctor": 
                                tempUser = (Doctor) ois.readObject();
                                System.out.println("Uh well");
                                System.out.println(tempUser.toString());
                                break;
                            case "Patient": 
                                tempUser = (Patient) ois.readObject();
                                System.out.println("Hmm");
                                System.out.println(tempUser.toString());
                                break;
                            case "Director": 
                                tempUser = (Director) ois.readObject();                            
                                System.out.println("Eh");
                                System.out.println(tempUser.toString());
                                break;
                            case "Accounts Officer": 
                                tempUser = (AccountsOfficer) ois.readObject();
                                System.out.println("Oh");
                                System.out.println(tempUser.toString());
                                break;
                        }
                        System.out.println(tempUser.toString());
                        if (tempUser.getClass().getSimpleName().equals(usertype)){
                            System.out.println("User found");
                            usersToAdd.add(tempUser.getName()+" "+String.valueOf(tempUser.getID()));
                        }
                    }
                }
                catch(IOException | ClassNotFoundException e){
                    System.out.println(e.toString());
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
            System.out.println(usersToAdd);
            if(usersToAdd!=null){
                userNameComboBox.getItems().clear();
                userNameComboBox.getItems().addAll(usersToAdd);}
        }
    }
    
}
