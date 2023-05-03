package views.employee;

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
import users.AccountsOfficer;
import users.Director;
import users.Doctor;
import users.Employee;
import users.HROfficer;
import users.LabTechnician;
import users.Nurse;
import users.Pharmacist;
import users.User;
import views.HROfficer.HRDashboardController;
import views.LabTechnician.LabTechnicianDashboardController;
import views.accountsofficer.AccountsOfficerDashboardController;
import views.director.DirectorDashboardController;
import views.doctor.DoctorDashboardController;
import views.nurse.NurseDashboardController;
import views.pharmacist.PharmacistDashboardController;

public class MyWorkplaceController implements Initializable {
    private Employee employee;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
        System.out.println("Loaded: "+this.employee.toString()+employee.getClass().getSimpleName());
    }

    @FXML
    private void applyForLeaveOnClick(ActionEvent event) throws IOException {
        Parent leave = null;
        FXMLLoader leaveLoader = new FXMLLoader(getClass().getResource("LeaveApplication.fxml"));
        leave = (Parent) leaveLoader.load();
        Scene leaveScene = new Scene(leave);

        LeaveApplicationController d = leaveLoader.getController();
        d.setEmployee(this.employee);

        Stage doctorStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        doctorStage.setScene(leaveScene);
        doctorStage.show();
    }

    @FXML
    private void applyForLoanOnClick(ActionEvent event) throws IOException {
        Parent loan = null;
        FXMLLoader loanLoader = new FXMLLoader(getClass().getResource("LoanApplication.fxml"));
        loan = (Parent) loanLoader.load();
        Scene loanScene = new Scene(loan);

        LoanApplicationController d = loanLoader.getController();
        d.setEmployee(this.employee);

        Stage loanStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        loanStage.setScene(loanScene);
        loanStage.show();
    }

    @FXML
    private void submitComplaintOnClick(ActionEvent event) throws IOException {
        Parent complaint = null;
        FXMLLoader complaintLoader = new FXMLLoader(getClass().getResource("SubmitComplaint.fxml"));
        complaint = (Parent) complaintLoader.load();
        Scene complaintScene = new Scene(complaint);

        SubmitComplaintController sc = complaintLoader.getController();
        sc.setEmployee(this.employee);

        Stage complaintStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        complaintStage.setScene(complaintScene);
        complaintStage.show();
    }

    @FXML
    private void submitResignationOnClick(ActionEvent event) throws IOException {
        Parent resign = null;
        FXMLLoader resignLoader = new FXMLLoader(getClass().getResource("SubmitResignation.fxml"));
        resign = (Parent) resignLoader.load();
        Scene resignScene = new Scene(resign);

        SubmitResignationController rs = resignLoader.getController();
        rs.setEmployee(this.employee);

        Stage resignStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        resignStage.setScene(resignScene);
        resignStage.show();
    }
    
    
    @FXML
    private void viewScheduleOnClick(ActionEvent event) throws IOException {
        Parent schedule = null;
        FXMLLoader scheduleLoader = new FXMLLoader(getClass().getResource("ViewSchedule.fxml"));
        schedule = (Parent) scheduleLoader.load();
        Scene scheduleScene = new Scene(schedule);

        ViewScheduleController vs = scheduleLoader.getController();
        vs.setEmployee(this.employee);

        Stage scheduleStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        scheduleStage.setScene(scheduleScene);
        scheduleStage.show();
    }
    
    @FXML
    private void viewPoliciesOnClick(ActionEvent event) throws IOException {
        Parent policy = null;
        FXMLLoader policyLoader = new FXMLLoader(getClass().getResource("ViewSchedule.fxml"));
        policy = (Parent) policyLoader.load();
        Scene policyScene = new Scene(policy);

        ViewPolicyController vs = policyLoader.getController();
        vs.setEmployee(this.employee);

        Stage policyStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        policyStage.setScene(policyScene);
        policyStage.show();
    }

    @FXML
    private void returnToDashboardOnClick(ActionEvent event) throws IOException {
        String usertype = this.employee.getClass().getSimpleName();
        switch(usertype){
            case "Doctor": 
                Parent doctorDashboard = null;
                FXMLLoader doctorLoader = new FXMLLoader(getClass().getResource("/views/doctor/DoctorDashboard.fxml"));
                doctorDashboard = (Parent) doctorLoader.load();
                Scene doctorScene = new Scene(doctorDashboard);
                
                DoctorDashboardController d = doctorLoader.getController();
                d.setDoc((Doctor) User.getInstance(this.employee.getID(), "Doctor"));
                
                Stage doctorStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
                doctorStage.setScene(doctorScene);
                doctorStage.show();
                break;   
            case "Pharmacist": 
                Parent pharmacistDashboard = null;
                FXMLLoader pharmaLoader = new FXMLLoader(getClass().getResource("/views/pharmacist/PharmacistDashboard.fxml"));
                pharmacistDashboard = (Parent) pharmaLoader.load();
                Scene pharmaScene = new Scene(pharmacistDashboard);
                
                PharmacistDashboardController ph = pharmaLoader.getController();
                ph.setPharmacist((Pharmacist) User.getInstance(this.employee.getID(), "Pharmacist"));
                
                Stage pharmaStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
                pharmaStage.setScene(pharmaScene);
                pharmaStage.show();
                break;
            case "Nurse": 
                Parent nurseDashboard = null;
                FXMLLoader nurseLoader = new FXMLLoader(getClass().getResource("/views/nurse/NurseDashboard.fxml"));
                nurseDashboard = (Parent) nurseLoader.load();
                Scene nurseScene = new Scene(nurseDashboard);
                
                NurseDashboardController nu = nurseLoader.getController();
                nu.setNurse((Nurse) User.getInstance(this.employee.getID(), "Nurse"));
                
                Stage nurseStage  = (Stage)((Node)event.getSource()).getScene().getWindow();
                nurseStage.setScene(nurseScene);
                nurseStage.show();
                break;
            case "Director": 
                Parent directorDashboard = null;
                FXMLLoader directorLoader = new FXMLLoader(getClass().getResource("/views/director/DirectorDashboard.fxml"));
                directorDashboard = (Parent) directorLoader.load();
                Scene directorScene = new Scene(directorDashboard);
                
                DirectorDashboardController di = directorLoader.getController();
                di.setDirector((Director) User.getInstance(this.employee.getID(), "Director"));
                
                Stage directorStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
                directorStage.setScene(directorScene);
                directorStage.show();            
                break;
            case "AccountsOfficer": 
                Parent accountsOfficerDashboard = null;
                FXMLLoader accountsLoader = new FXMLLoader(getClass().getResource("/views/accountsofficer/AccountsOfficerDashboard.fxml"));
                accountsOfficerDashboard = (Parent) accountsLoader.load();
                Scene accountsScene = new Scene(accountsOfficerDashboard);
                
                AccountsOfficerDashboardController a = accountsLoader.getController();
                a.setOfficer((AccountsOfficer) User.getInstance(this.employee.getID(), "AccountsOfficer"));
                
                Stage accountsStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
                accountsStage.setScene(accountsScene);
                accountsStage.show();
                break;    
            case "HROfficer": 
                Parent HRDashboard = null;
                FXMLLoader HRLoader = new FXMLLoader(getClass().getResource("/views/HROfficer/HRDashboard.fxml"));
                HRDashboard = (Parent) HRLoader.load();
                Scene HRScene = new Scene(HRDashboard);
                
                HRDashboardController hr = HRLoader.getController();
                hr.setHR((HROfficer) User.getInstance(this.employee.getID(), "HROfficer"));
                
                Stage hrStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
                hrStage.setScene(HRScene);
                hrStage.show();
                break;
            case "LabTechnician": 
                Parent LabTechnicianDashboard = null;
                FXMLLoader labLoader = new FXMLLoader(getClass().getResource("/views/LabTechnician/LabTechnicianDashboard.fxml"));
                LabTechnicianDashboard = (Parent) labLoader.load();
                Scene labScene = new Scene(LabTechnicianDashboard);
                
                LabTechnicianDashboardController l = labLoader.getController();
                l.setLabTechnician((LabTechnician) User.getInstance(this.employee.getID(), "LabTechnician"));
                
                Stage labStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
                labStage.setScene(labScene);
                labStage.show();        
                break;
        }
    }
    
}
