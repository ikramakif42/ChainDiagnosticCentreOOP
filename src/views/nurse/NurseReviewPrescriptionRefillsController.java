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

/**
 * FXML Controller class
 *
 * @author User
 */
public class NurseReviewPrescriptionRefillsController implements Initializable {

    @FXML
    private TableView<?> nursePrescriptionRefillstListTable;
    @FXML
    private Button nursePatientRefillApproveAll;
    @FXML
    private Button nursePatientRefillDeclineAll;
    @FXML
    private Button nursePatientRefillSelect;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void nursePatientRefillApproveAllOnClick(ActionEvent event) {
    }

    @FXML
    private void nursePatientRefillDeclineAllOnClick(ActionEvent event) {
    }

    @FXML
    private void nursePatientRefillSelectOnClick(ActionEvent event) {
    }
    
}
