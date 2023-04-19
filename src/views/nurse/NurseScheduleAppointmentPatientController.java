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

/**
 * FXML Controller class
 *
 * @author User
 */
public class NurseScheduleAppointmentPatientController implements Initializable {

    @FXML
    private TableView<?> nurseSheduleAppointmentPatientTable;
    @FXML
    private Button nurseSheduleAppointmentPatientConfirm;
    @FXML
    private Button nurseSheduleAppointmentPatientSearch;
    @FXML
    private TextField nurseSheduleAppointmentPatientSearchTextField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void nurseSheduleAppointmentPatientConfirmOnClick(ActionEvent event) {
    }

    @FXML
    private void nurseSheduleAppointmentPatientSearchOnClick(ActionEvent event) {
    }
    
}
