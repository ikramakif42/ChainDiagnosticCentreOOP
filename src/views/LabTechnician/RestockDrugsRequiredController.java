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

/**
 * FXML Controller class
 *
 * @author arafath
 */
public class RestockDrugsRequiredController implements Initializable {
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
    private void ConfirmTheReqOnClick(ActionEvent event) {
    }

    @FXML
    private void ReturnInternalParmacistsOnClick(ActionEvent event) throws IOException {
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
    private void RestockDrugsOnClick(ActionEvent event) {
    }
    
}
