/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.LabTechnician;

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
import javafx.stage.Stage;
import users.LabTechnician;
import users.User;

/**
 * FXML Controller class
 *
 * @author arafath
 */
public class PatientBillingInformationController implements Initializable {
    private LabTechnician labTechnician;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public LabTechnician getLabTechnician() {
        return labTechnician;
    }
    
    public void setLabTechnician(LabTechnician labTechnician) {
        this.labTechnician = labTechnician;
    }    

    @FXML
    private void BillingInformationOnClick(ActionEvent event) {
    }

    @FXML
    private void ReturnPatientbillingInfoOnClick(ActionEvent event) throws IOException {
        Parent LabTechnicianDashboard = null;
        FXMLLoader labLoader = new FXMLLoader(getClass().getResource("LabTechnicianDashboard.fxml"));
        LabTechnicianDashboard = (Parent) labLoader.load();
        Scene labScene = new Scene(LabTechnicianDashboard);

        LabTechnicianDashboardController l = labLoader.getController();
        l.setLabTechnician(this.labTechnician);

        Stage labStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        labStage.setScene(labScene);
        labStage.show(); 
    }

    @FXML
    private void TypeOfTestOnClick(ActionEvent event) {
    }

    @FXML
    private void SubmitBillingInfoOnClick(ActionEvent event) {
    }
    
}
