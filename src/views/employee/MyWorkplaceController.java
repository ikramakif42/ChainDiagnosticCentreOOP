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
import users.Doctor;
import users.Employee;
import users.User;
import views.doctor.DoctorDashboardController;
import views.doctor.DoctorMyPatientsController;

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
    private void returnToDashboardOnClick(ActionEvent event) throws IOException {
//        Parent doctorDashboard = null;
//        FXMLLoader doctorLoader = new FXMLLoader(getClass().getResource("/views/doctor/DoctorDashboard.fxml"));
//        doctorDashboard = (Parent) doctorLoader.load();
//        Scene doctorScene = new Scene(doctorDashboard);
//
//        DoctorDashboardController d = doctorLoader.getController();
//        d.setDoc(this.employee);
//
//        Stage doctorStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
//        doctorStage.setScene(doctorScene);
//        doctorStage.show();
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
    
}
