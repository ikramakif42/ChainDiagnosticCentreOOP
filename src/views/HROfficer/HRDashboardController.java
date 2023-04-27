package views.HROfficer;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import users.HROfficer;
import users.User;
import views.HROfficer.AddNewEmployeeController;
import views.HROfficer.CreateReportsOnEmployeesController;
import views.HROfficer.HandleResignationsController;
import views.HROfficer.ManageApplicationController;
import views.HROfficer.UpdateEmployeeCompensationController;
import views.HROfficer.UpdateHospitalPoliciesProceduresController;
import views.HROfficer.UpdateRecordProfileController;
import views.HROfficer.ViewEmployeeComplaintsController;


public class HRDashboardController implements Initializable {
    @FXML
    private Label HRIDLabel;
    @FXML
    private Label NameLabel;
    private HROfficer HR;
    
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
    
    private void updatePoliciesOnclick(ActionEvent event) throws IOException {
        
        Parent parent = null;
        FXMLLoader HRLoader = new FXMLLoader(getClass().getResource("UpdateHospitalPoliciesProcedures.fxml"));
        parent = (Parent) HRLoader.load();
        Scene HRScene = new Scene(parent);
        
        UpdateHospitalPoliciesProceduresController h = HRLoader.getController();
        h.setHR(this.HR);

        Stage HRStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        HRStage.setScene(HRScene);
        HRStage.show();
        // comment
    }

    private void manageApplicationsOnClick(ActionEvent event) throws IOException {
        
        Parent parent = null;
        FXMLLoader HRLoader = new FXMLLoader(getClass().getResource("ManageApplication.fxml"));
        parent = (Parent) HRLoader.load();
        Scene HRScene = new Scene(parent);
        
        ManageApplicationController m = HRLoader.getController();
        m.setHR(this.HR);

        Stage HRStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        HRStage.setScene(HRScene);
        HRStage.show();
    }

    private void updateEmployeesOnClick(ActionEvent event) throws IOException {
        
        Parent parent = null;
        FXMLLoader HRLoader = new FXMLLoader(getClass().getResource("UpdateRecordProfile.fxml"));
        parent = (Parent) HRLoader.load();
        Scene HRScene = new Scene(parent);
        
        UpdateRecordProfileController m = HRLoader.getController();
        m.setHR(this.HR);

        Stage HRStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        HRStage.setScene(HRScene);
        HRStage.show();
        
        
        
        
    }

    private void viewComplaints(ActionEvent event) throws IOException {
        
        Parent parent = null;
        FXMLLoader HRLoader = new FXMLLoader(getClass().getResource("ViewEmployeeComplaints.fxml"));
        parent = (Parent) HRLoader.load();
        Scene HRScene = new Scene(parent);
        
        ViewEmployeeComplaintsController m = HRLoader.getController();
        m.setHR(this.HR);

        Stage HRStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        HRStage.setScene(HRScene);
        HRStage.show();
    }

    private void handleResignations(ActionEvent event) throws IOException {
        
        Parent parent = null;
        FXMLLoader HRLoader = new FXMLLoader(getClass().getResource("HandleResignations.fxml"));
        parent = (Parent) HRLoader.load();
        Scene HRScene = new Scene(parent);
        
        HandleResignationsController m = HRLoader.getController();
        m.setHR(this.HR);

        Stage HRStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        HRStage.setScene(HRScene);
        HRStage.show();
    }

    private void addNewEmployee(ActionEvent event) throws IOException {
        
        Parent parent = null;
        FXMLLoader HRLoader = new FXMLLoader(getClass().getResource("AddNewEmployee.fxml"));
        parent = (Parent) HRLoader.load();
        Scene HRScene = new Scene(parent);
        
        AddNewEmployeeController m = HRLoader.getController();
        m.setHR(this.HR);

        Stage HRStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        HRStage.setScene(HRScene);
        HRStage.show();
        
    }

    private void createReport(ActionEvent event) throws IOException {
        
        Parent parent = null;
        FXMLLoader HRLoader = new FXMLLoader(getClass().getResource("CreateReportsOnEmployees.fxml"));
        parent = (Parent) HRLoader.load();
        Scene HRScene = new Scene(parent);
        
        CreateReportsOnEmployeesController m = HRLoader.getController();
        m.setHR(this.HR);

        Stage HRStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        HRStage.setScene(HRScene);
        HRStage.show();
    }
    
    
    
    private void UpdateCompensation(ActionEvent event) throws IOException {
        
        Parent parent = null;
        FXMLLoader HRLoader = new FXMLLoader(getClass().getResource("update employee compensation.fxml"));
        parent = (Parent) HRLoader.load();
        Scene HRScene = new Scene(parent);
        
        UpdateEmployeeCompensationController m = HRLoader.getController();
        m.setHR(this.HR);

        Stage HRStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        HRStage.setScene(HRScene);
        HRStage.show();
    }
    
    private void logOut(ActionEvent event) {
        Parent login;
        login = null;
        try {
            login = FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(HRDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene1 = new Scene(login);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene1);
        window.show();
    }

    @FXML
    private void updatepolicesonclick(ActionEvent event) {
    }

    @FXML
    private void ManageApplicationsOnClick(ActionEvent event) {
    }

    @FXML
    private void UpdateEmployeeRecordsProfilesOnClick(ActionEvent event) {
    }

    @FXML
    private void EmployeeComplimentOnClick(ActionEvent event) {
    }

    @FXML
    private void HandleReginationOnClick(ActionEvent event) {
    }

    @FXML
    private void AddNewEmployeeOnClick(ActionEvent event) {
    }

    @FXML
    private void CreateReportOnEmployeeOnClick(ActionEvent event) {
    }

    @FXML
    private void UpateEmployeeCompensationOnClick(ActionEvent event) {
    }

    @FXML
    private void LogOutHROfficer(ActionEvent event) {
    }
    

    
}
