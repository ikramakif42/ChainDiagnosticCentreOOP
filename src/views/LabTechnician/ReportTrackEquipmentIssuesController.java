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
 * @author arafath
 */
public class ReportTrackEquipmentIssuesController implements Initializable {
     private LabTechnician labtechnician;

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
    private void ReportEquipmentIssueOnClick(ActionEvent event) {
    }

    @FXML
    private void SubmitTechnicianReportOnClick(ActionEvent event) {
    }

    @FXML
    private void ReturnReportTrackOnClick(ActionEvent event) {
    }

    void setLabTechnician(LabTechnician LabTechnician) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
