/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.HROfficer;

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
import users.HROfficer;

/**
 * FXML Controller class
 *
 * @author arafath
 */
public class ViewEmployeeComplaintsController implements Initializable {
    private HROfficer HR;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    public HROfficer getHR() {
        return HR;
    }

    public void setHR(HROfficer HR) {
        this.HR = HR;
    }    

    @FXML
    private void EmployeeComplaintsOnClick(ActionEvent event) {
    }

    @FXML
    private void ReturnEmployeeCompliants(ActionEvent event) throws IOException {
        Parent parent = null;
        FXMLLoader HRLoader = new FXMLLoader(getClass().getResource("HRDashboard.fxml"));
        parent = (Parent) HRLoader.load();
        Scene HRScene = new Scene(parent);
        
        HRDashboardController m = HRLoader.getController();
        m.setHR(this.HR);

        Stage HRStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        HRStage.setScene(HRScene);
        HRStage.show();
    }
    
}
