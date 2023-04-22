/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.nurse;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import users.Nurse;

/**
 * FXML Controller class
 *
 * @author User
 */
public class NurseDashboardController implements Initializable {

    @FXML
    private Button nurseViewPatientList;
    @FXML
    private Button nurseAppointment;
    @FXML
    private Button nurseViewStock;
    @FXML
    private Button nursePolicies;
    @FXML
    private Button nurseLogOut;
    @FXML
    private Button nurseAssignedTask;
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
    private void nurseViewPatientListOnClick(ActionEvent event) {
    }

    @FXML
    private void nurseAppointmentOnClick(ActionEvent event) {
    }

    @FXML
    private void nurseViewStockOnClick(ActionEvent event) {
    }

    @FXML
    private void nursePoliciesOnClick(ActionEvent event) {
    }

    @FXML
    private void nurseLogOutOnClick(ActionEvent event) {
    }

    @FXML
    private void nurseAssignedTaskOnClick(ActionEvent event) {
    }
    
}
