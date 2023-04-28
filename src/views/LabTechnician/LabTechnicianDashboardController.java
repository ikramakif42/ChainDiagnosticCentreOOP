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
import javafx.scene.control.Label;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import users.LabTechnician;
import users.User;
import views.LabTechnician.LabTechnicianDashboardController;
import views.LabTechnician.OrderSuppliesRequiredController;
import views.LabTechnician.PatientBillingInformationController;
import views.LabTechnician.RecordLabResultsReportsController;
import views.LabTechnician.RecordManageLabInventoryController;
import views.LabTechnician.ReportTrackEquipmentIssuesController;
import views.LabTechnician.RestockDrugsRequiredController;
import views.LabTechnician.RetrievePatientHistoryTestResultsController;
import views.LabTechnician.ReciveProcessLabOrderController;


/**
 * FXML Controller class
 *
 * @author arafath
 */
public class LabTechnicianDashboardController implements Initializable {
    
    private LabTechnician LabTechnician;

    public LabTechnician getLabTechnician() {
        return LabTechnician;
    }

    public void setLabTechnician(LabTechnician LabTechnician) {
        this.LabTechnician = LabTechnician;
    }

    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

     
     

    @FXML
    private void ReciveProcessLabOrderOnClick(ActionEvent event) throws IOException {
        
        Parent parent = null;
        FXMLLoader LabTechnicianLoader = new FXMLLoader(getClass().getResource("ReciveProcessLabOrder.fxml"));
        parent = (Parent) LabTechnicianLoader.load();
        Scene LabTechnicianScene = new Scene(parent);
        
        ReciveProcessLabOrderController l  = LabTechnicianLoader.getController();
        l.setLabTechnician(this.LabTechnician);

        Stage LabTechnicianStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        LabTechnicianStage.setScene(LabTechnicianScene);
        LabTechnicianStage.show();
    }

    @FXML
    private void RecordLabResultReportOnClick(ActionEvent event) throws IOException {
        
        
        Parent parent = null;
        FXMLLoader LabTechnicainLoader = new FXMLLoader(getClass().getResource("RecordLabResultsReports.fxml"));
        parent = (Parent) LabTechnicainLoader.load();
        Scene LabTechnicainScene = new Scene(parent);
        
        RecordLabResultsReportsController l  = LabTechnicainLoader.getController();
        l.setLabTechnician(this.LabTechnician);

        Stage LabTechnicainStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        LabTechnicainStage.setScene(LabTechnicainScene);
        LabTechnicainStage.show();
            
        
    }

    @FXML
    private void RestockDrugsOnClick(ActionEvent event) throws IOException {
        
        Parent parent = null;
        FXMLLoader LabTechnicainLoader = new FXMLLoader(getClass().getResource("RestockDrugsRequired.fxml"));
        parent = (Parent) LabTechnicainLoader.load();
        Scene LabTechnicainScene = new Scene(parent);
        
        RestockDrugsRequiredController l  = LabTechnicainLoader.getController();
        l.setLabTechnician(this.LabTechnician);

        Stage LabTechnicainStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        LabTechnicainStage.setScene(LabTechnicainScene);
        LabTechnicainStage.show();
        
    }

    @FXML
    private void RetrivePatientHistoryTestResultOnClick(ActionEvent event) throws IOException  {
        
        
        Parent parent = null;
        FXMLLoader LabTechnicainLoader = new FXMLLoader(getClass().getResource("RetrievePatientHistoryTestResults.fxml"));
        parent = (Parent) LabTechnicainLoader.load();
        Scene LabTechnicainScene = new Scene(parent);
        
        RetrievePatientHistoryTestResultsController l  = LabTechnicainLoader.getController();
        l.setLabTechnician(this.LabTechnician);

        Stage LabTechnicainStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        LabTechnicainStage.setScene(LabTechnicainScene);
        LabTechnicainStage.show();
    }

    @FXML
    private void PatientBillingInfoOnClick(ActionEvent event) throws IOException  {
        
        Parent parent = null;
        FXMLLoader LabTechnicainLoader = new FXMLLoader(getClass().getResource("PatientBillingInformation.fxml"));
        parent = (Parent) LabTechnicainLoader.load();
        Scene LabTechnicainScene = new Scene(parent);
        
        PatientBillingInformationController l  = LabTechnicainLoader.getController();
        l.setLabTechnician(this.LabTechnician);

        Stage LabTechnicainStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        LabTechnicainStage.setScene(LabTechnicainScene);
        LabTechnicainStage.show();
    }

    @FXML
    private void OrderSuppliesRequiredOnClick(ActionEvent event) throws IOException  {
        
        Parent parent = null;
        FXMLLoader LabTechnicainLoader = new FXMLLoader(getClass().getResource("OrderSuppliesRequired.fxml"));
        parent = (Parent) LabTechnicainLoader.load();
        Scene LabTechnicainScene = new Scene(parent);
        
        OrderSuppliesRequiredController l  = LabTechnicainLoader.getController();
        l.setLabTechnician(this.LabTechnician);

        Stage LabTechnicainStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        LabTechnicainStage.setScene(LabTechnicainScene);
        LabTechnicainStage.show();
        
    }

    @FXML
    private void RecordManageLabInventoryOnClick(ActionEvent event) throws IOException  {
        
        Parent parent = null;
        FXMLLoader LabTechnicainLoader = new FXMLLoader(getClass().getResource("RecordManageLabInventory.fxml"));
        parent = (Parent) LabTechnicainLoader.load();
        Scene LabTechnicainScene = new Scene(parent);
        
        RecordManageLabInventoryController l  = LabTechnicainLoader.getController();
        l.setLabTechnician(this.LabTechnician);

        Stage LabTechnicainStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        LabTechnicainStage.setScene(LabTechnicainScene);
        LabTechnicainStage.show();
        
    }

    @FXML
    private void ReportrackOnClick(ActionEvent event) throws IOException  {
        
        Parent parent = null;
        FXMLLoader LabTechnicainLoader = new FXMLLoader(getClass().getResource("ReportTrackEquipmentIssues.fxml"));
        parent = (Parent) LabTechnicainLoader.load();
        Scene LabTechnicainScene = new Scene(parent);
        
        ReportTrackEquipmentIssuesController l  = LabTechnicainLoader.getController();
        l.setLabTechnician(this.LabTechnician);

        Stage LabTechnicainStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        LabTechnicainStage.setScene(LabTechnicainScene);
        LabTechnicainStage.show();
        
    }
    @FXML
    private void logOut(ActionEvent event) {
        Parent login;
        login = null;
        try {
            login = FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(LabTechnicianDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene1 = new Scene(login);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene1);
        window.show();
    }

   

   
}
    

