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
import javafx.scene.control.Button;
import javafx.stage.Stage;
import users.HROfficer;

/**
 * FXML Controller class
 *
 * @author arafath
 */
public class ManageApplicationController implements Initializable {

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
    private void LeaveButtonOnClick(ActionEvent event) {
    }

    private void ReturnButtonOnClick(ActionEvent event) throws IOException {
       
    }

    @FXML
    private void ViewApplicationOnClick(ActionEvent event) {
    }

    @FXML
    private void RejectaaplicationOnClick(ActionEvent event) {
    }

    @FXML
    private void ApproveOnClick(ActionEvent event) {
    }

    @FXML
    private void ReturnManageApplicationOnClick(ActionEvent event) throws IOException {
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
