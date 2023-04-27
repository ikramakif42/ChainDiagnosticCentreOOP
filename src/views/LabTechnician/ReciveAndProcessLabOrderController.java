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
import javafx.scene.control.Button;
import javafx.stage.Stage;
import users.LabTechnician;

/**
 * FXML Controller class
 *
 * @author arafath
 */
public class ReciveAndProcessLabOrderController implements Initializable {
    
    private LabTechnician labtechnician;


    @FXML
    private Button ConfirmLabOrderButton;
    @FXML
    private Button ReturnLabOrderButton;
    private LabTechnician LabTechnician;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public LabTechnician getlabtechnician() {
        return labtechnician;
    }
    public void setlabtechnician(LabTechnician labtechnician) {
        this.labtechnician = labtechnician;
    }

    @FXML
    private void ConfirmLabOrderButtonOnClick(ActionEvent event) {
    }

    @FXML
    private void ReturnLabOrderButtonOnClick(ActionEvent event) throws IOException {
        
         Parent parent = null;
        FXMLLoader LabTechnicainLoader = new FXMLLoader(getClass().getResource("LabTechnicianDashboard.fxml"));
        parent = (Parent) LabTechnicainLoader.load();
        Scene LabTechnicainScene = new Scene(parent);
        
        LabTechnicianDashboardController l  = LabTechnicainLoader.getController();
        l.setLabTechnician(this.LabTechnician);

        Stage LabTechnicainStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        LabTechnicainStage.setScene(LabTechnicainScene);
        LabTechnicainStage.show();
    }

    void setLabTechnician(LabTechnician LabTechnician) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
