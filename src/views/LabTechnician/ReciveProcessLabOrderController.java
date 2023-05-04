/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.LabTechnician;

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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.stage.Stage;
import users.LabTechnician;
import users.User;

/**
 * FXML Controller class
 *
 * @author Kazi
 */
public class ReciveProcessLabOrderController implements Initializable {
    
    
    
    private LabTechnician LabTechnician;
    private LabTechnician labTechnician;
    @FXML
    private TreeTableView<?> PatientTableView;
    @FXML
    private TreeTableColumn<?, ?> TestNameTableColumn;
    @FXML
    private TreeTableColumn<?, ?> PriorityTableColumn;
    @FXML
    private TextField TitleTextField;
    @FXML
    private TextField TypeTextField;
    @FXML
    private TextArea BodyTextArea;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public LabTechnician getLabTechnician() {
        return labTechnician;
    }

    public void setLabTechnician(LabTechnician labTechnician) {
        this.labTechnician = labTechnician;
    }
    
    
    @FXML
    private void AddReportOnClick(ActionEvent event) {
    }
    
    @FXML
    private void ConfirmLabOrderOnClick(ActionEvent event) {
    }

    @FXML
    private void ReturnLabOrderButtonOnClick(ActionEvent event) throws IOException {
        Parent LabTechnicianDashboard = null;
        FXMLLoader labLoader = new FXMLLoader(getClass().getResource("LabTechnicianDashboard.fxml"));
        LabTechnicianDashboard = (Parent) labLoader.load();
        Scene labScene = new Scene(LabTechnicianDashboard);

        LabTechnicianDashboardController l = labLoader.getController();
        l.setLabTechnician(this.LabTechnician);

        Stage labStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        labStage.setScene(labScene);
        labStage.show(); 
    }

    
}
