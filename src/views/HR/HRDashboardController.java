
package views.HR;

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
import users.hr;
import users.User;
import views.HROfficer.AddNewEmployeeController;
import views.HROfficer.CreateReportsOnEmployeesController;
import views.HROfficer.HandleResignationsController;
import views.HROfficer.ManageApplicationController;
import views.HROfficer.UpdateEmployeeCompensationController;
import views.HROfficer.UpdateHospitalPoliciesProceduresController;
import views.HROfficer.UpdateRecordProfileController;
import views.HROfficer.ViewEmployeeComplaintsController;


/**
 * FXML Controller class
 *
 * @author arafath
 */
public class HRDashboardController implements Initializable {
    private hr HR;
    @FXML
    private Label HRIDLabel;
    @FXML
    private Label NameLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    public hr gethr() {
        return hr;
    }
     public void setPatient(hr HR) {
        this.HR = HR;
        System.out.println("Be HR");
        HRIDLabel.setText(String.valueOf(HR.ID));
        HRNameLabel.setText(HR.name);
    }

    @FXML
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
    }

    @FXML
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

    @FXML
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

    @FXML
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

    @FXML
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

    @FXML
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

    @FXML
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
    
    
    
     @FXML
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
    
    @FXML
    private void logOut(ActionEvent event) {
        hr login;
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
    

    
}
