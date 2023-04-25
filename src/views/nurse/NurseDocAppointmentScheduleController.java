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
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import users.Nurse;

/**
 * FXML Controller class
 *
 * @author User
 */
public class NurseDocAppointmentScheduleController implements Initializable {

    @FXML
    private TableView<?> nurseDocAppointmentTable;
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
    private void nurseDocAppointmentEditOnClick(ActionEvent event) throws IOException {
        FXMLLoader nurseDocAppEditLoader = new FXMLLoader(getClass().getResource("NurseModifySchedules.fxml"));
        Parent nursePatientLabReports = (Parent) nurseDocAppEditLoader.load();
        Scene nurseDocAppEditScene = new Scene(nursePatientLabReports);

       NurseModifySchedulesController n = nurseDocAppEditLoader.getController();
        n.setNurse(this.nurse);

        Stage nurseDocAppEditStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        nurseDocAppEditStage.setScene(nurseDocAppEditScene);
        nurseDocAppEditStage.show();
    }

    @FXML
    private void nurseDocAppointmentBackToDashOnClick(ActionEvent event) throws IOException {
        Parent nurseDashboard = null;
        FXMLLoader nurseLoader = new FXMLLoader(getClass().getResource("NurseDashboard.fxml"));
        nurseDashboard = (Parent) nurseLoader.load();
        Scene nurseScene = new Scene(nurseDashboard);

        NurseDashboardController nu = nurseLoader.getController();
        nu.setNurse(this.nurse);

        Stage nurseStage  = (Stage)((Node)event.getSource()).getScene().getWindow();
        nurseStage.setScene(nurseScene);
        nurseStage.show();
    }
    
}
