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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import users.Nurse;

/**
 * FXML Controller class
 *
 * @author User
 */
public class NurseModifySchedulesController implements Initializable {

    @FXML
    private DatePicker nurseDocAppointmentDateChange;
    @FXML
    private TextField nurseDocAppointmentTimeChange;
    private Nurse nurse;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void nurseDocAppointmentSaveEditOnClick(ActionEvent event) {
        
    }

    @FXML
    private void nurseDocAppointmentBackOnClick(ActionEvent event) throws IOException {
        Parent docAppt = null;
        FXMLLoader docApptLoader = new FXMLLoader(getClass().getResource("NurseDocAppointmentSchedule.fxml"));
        docAppt = (Parent) docApptLoader.load();
        Scene docApptScene = new Scene(docAppt);

        NurseDocAppointmentScheduleController n = docApptLoader.getController();
        n.setNurse(this.nurse);

        Stage docApptStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        docApptStage.setScene(docApptScene);
        docApptStage.show();
    }

    void setNurse(Nurse nurse) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
