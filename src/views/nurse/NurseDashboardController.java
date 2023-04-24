/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.nurse;

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
import javafx.scene.control.Button;
import javafx.stage.Stage;
import users.Nurse;

/**
 * FXML Controller class
 *
 * @author User
 */
public class NurseDashboardController implements Initializable {

    private Nurse nurse;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 

    public Nurse getNurse() {
        return nurse;
    }

    public void setNurse(Nurse nurse) {
        this.nurse = nurse;
    }
    
    

    @FXML
    private void nurseViewPatientListOnClick(ActionEvent event) throws IOException {
        
        Parent nurseViewPatientList = null;
        FXMLLoader nurseLoader = new FXMLLoader(getClass().getResource("NursePatientList.fxml"));
        nurseViewPatientList = (Parent) nurseLoader.load();
        Scene nurseViewPatientListScene = new Scene(nurseViewPatientList);

        NursePatientListController n = nurseLoader.getController();
        n.setNurse(this.nurse);

        Stage nurseStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        nurseStage.setScene(nurseViewPatientListScene);
        nurseStage.show();
    }

    @FXML
    private void nurseAppointmentOnClick(ActionEvent event) {
    }

    @FXML
    private void nurseViewStockOnClick(ActionEvent event) {
    }


    @FXML
    private void nurseLogOutOnClick(ActionEvent event) {
    }

    @FXML
    private void nurseAssignedTaskOnClick(ActionEvent event) {
    }
    
}
