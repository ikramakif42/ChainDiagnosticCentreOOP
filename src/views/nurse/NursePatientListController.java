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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import users.Nurse;

/**
 * FXML Controller class
 *
 * @author User
 */
public class NursePatientListController implements Initializable {

    @FXML
    private TableView<?> nursePatientListTable;
    @FXML
    private TextField nurseSearchPatientListTextField;
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
    private void nurseUpdatePatientOnClick(ActionEvent event) {
    }

    @FXML
    private void nurseSchedulePatientOnClick(ActionEvent event) {
    }

    @FXML
    private void nursePatientBillOnClick(ActionEvent event) {
    }

    @FXML
    private void nursePatientPrescriptionOnClick(ActionEvent event) {
    }

    @FXML
    private void nursePatientLabReportsOnClick(ActionEvent event) {
    }

    @FXML
    private void nurseSearchPatientListOnClick(ActionEvent event) {
    }

    @FXML
    private void nursePatientListToDashboardOnClick(ActionEvent event) {
    }
    
}
