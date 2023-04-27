/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.LabTechnician;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import users.LabTechnician;

/**
 * FXML Controller class
 *
 * @author Kazi
 */
public class ReciveProcessLabOrderController implements Initializable {
    
    
    private LabTechnician labTechnician;

    public LabTechnician getLabTechnician() {
        return labTechnician;
    }

    public void setLabTechnician(LabTechnician labTechnician) {
        this.labTechnician = labTechnician;
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ConfirmLabOrderOnClick(ActionEvent event) {
    }

    @FXML
    private void ReturnLabOrderButtonOnClick(ActionEvent event) {
    }
    
}
