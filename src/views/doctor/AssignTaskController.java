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
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import users.Doctor;
import users.Nurse;
import users.LabTechnician;
import users.Pharmacist;
import users.User;

public class AssignTaskController implements Initializable {

    private Doctor doc;
    @FXML
    private ComboBox<String> userTypeComboBox;
    @FXML
    private ComboBox<String> userNameComboBox;
    @FXML
    private TextArea taskTextArea;
    Alert noType = new Alert(Alert.AlertType.WARNING, "Error, select user type!");
    Alert noName = new Alert(Alert.AlertType.WARNING, "Error, select user name!");
    Alert noTask = new Alert(Alert.AlertType.WARNING, "Error, select user task!");
    Alert failure = new Alert(Alert.AlertType.WARNING, "Error, failed to assign task!");
    Alert success = new Alert(Alert.AlertType.INFORMATION, "Task assigned successfully!");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String[] temp = {"Nurse", "LabTechnician", "Pharmacist"};
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
        String usertype = userTypeComboBox.getSelectionModel().getSelectedItem();
        if (usertype==null){noType.show();return;}
        String username = userNameComboBox.getSelectionModel().getSelectedItem();
        if (username==null){noName.show();return;}
        String task = taskTextArea.getText();
        if (task.isEmpty()){noTask.show();return;}
        System.out.println(usertype+" "+username+" "+task);
        
        String[] nameID = username.split(" ");
        int receiverID = Integer.parseInt(nameID[nameID.length-1]);
        if (this.doc.assignTask(receiverID, task)){success.show();}
        else {failure.show();}
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
                case "Nurse":
                    path="NurseObjects.bin";
                    break;
                case "LabTechnician":
                    path="LabTechnicianObjects.bin";
                    break;
                case "Pharmacist":
                    path="PharmacistObjects.bin";
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
                            case "Nurse": 
                                tempUser = (Nurse) ois.readObject();
                                System.out.println("Assign: nurse object");
                                System.out.println(tempUser.toString());
                                break;
                            case "LabTechnician": 
                                tempUser = (LabTechnician) ois.readObject();
                                System.out.println("Assign: technician object");
                                System.out.println(tempUser.toString());
                                break;
                            case "Pharmacist": 
                                tempUser = (Pharmacist) ois.readObject();                            
                                System.out.println("Assign: pharmacist object");
                                System.out.println(tempUser.toString());
                                break;
                        }
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
