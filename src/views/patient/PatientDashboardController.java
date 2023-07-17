package views.patient;

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
import users.Patient;

public class PatientDashboardController implements Initializable {
    
    private Patient patient;
    @FXML
    private Label patientIDLabel;
    @FXML
    private Label patientNameLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
        System.out.println("Be patient");
        patientIDLabel.setText(String.valueOf(patient.ID));
        patientNameLabel.setText(patient.name);
    }

    @FXML
    private void viewMySchedule(ActionEvent event) throws IOException {
        Parent root = null;
        FXMLLoader scheduleLoader = new FXMLLoader(getClass().getResource("PatientMySchedule.fxml"));
        root = (Parent) scheduleLoader.load();
        Scene doctorScene = new Scene(root);

        PatientMyScheduleController p = scheduleLoader.getController();
        p.setPatient(this.patient);

        Stage scheduleStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        scheduleStage.setScene(doctorScene);
        scheduleStage.show();
    }

    @FXML
    private void viewMyProfile(ActionEvent event) throws IOException {
        Parent root = null;
        FXMLLoader profileLoader = new FXMLLoader(getClass().getResource("PatientMyProfile.fxml"));
        root = (Parent) profileLoader.load();
        Scene profileScene = new Scene(root);

        PatientMyProfileController p = profileLoader.getController();
        p.setPatient(this.patient);

        Stage profileStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        profileStage.setScene(profileScene);
        profileStage.show();
    }

    @FXML
    private void viewQueryOnClick(ActionEvent event) throws IOException {
        Parent root = null;
        FXMLLoader queryLoader = new FXMLLoader(getClass().getResource("ViewQuery.fxml"));
        ViewQueryController q = new ViewQueryController(this.patient);
        queryLoader.setController(q);
        root = (Parent) queryLoader.load();

        Scene queryScene = new Scene(root);
        Stage queryStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        queryStage.setScene(queryScene);
        queryStage.show();
    }

    @FXML
    private void makeQueryOnClick(ActionEvent event) throws IOException {
        Parent root = null;
        FXMLLoader queryLoader = new FXMLLoader(getClass().getResource("MakeTelequery.fxml"));
        root = (Parent) queryLoader.load();
        Scene queryScene = new Scene(root);

        MakeTelequeryController q = queryLoader.getController();
        q.setPatient(this.patient);

        Stage queryStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        queryStage.setScene(queryScene);
        queryStage.show();
    }

    @FXML
    private void submitComplaintOnClick(ActionEvent event) throws IOException {
        Parent complaint = null;
        FXMLLoader complaintLoader = new FXMLLoader(getClass().getResource("PatientSubmitComplaint.fxml"));
        complaint = (Parent) complaintLoader.load();
        Scene complaintScene = new Scene(complaint);

        PatientSubmitComplaintController q = complaintLoader.getController();
        q.setPatient(this.patient);

        Stage complaintStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        complaintStage.setScene(complaintScene);
        complaintStage.show();
    }

    @FXML
    private void refillRequestOnClick(ActionEvent event) throws IOException {
        Parent refill = null;
        FXMLLoader refillLoader = new FXMLLoader(getClass().getResource("RefillRequest.fxml"));
        refill = (Parent) refillLoader.load();
        Scene refillScene = new Scene(refill);

        RefillRequestController q = refillLoader.getController();
        q.setPatient(this.patient);

        Stage refillStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        refillStage.setScene(refillScene);
        refillStage.show();
    }

    @FXML
    private void viewPayBillOnClick(ActionEvent event) throws IOException {
        Parent bill = null;
        FXMLLoader billLoader = new FXMLLoader(getClass().getResource("ViewPayBill.fxml"));
        bill = (Parent) billLoader.load();
        Scene billScene = new Scene(bill);

        ViewPayBillController q = billLoader.getController();
        q.setPatient(this.patient);

        Stage billStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        billStage.setScene(billScene);
        billStage.show();
    }

    @FXML
    private void viewReportOnClick(ActionEvent event) throws IOException {
        Parent report = null;
        FXMLLoader reportLoader = new FXMLLoader(getClass().getResource("ViewLabReport.fxml"));
        report = (Parent) reportLoader.load();
        Scene reportScene = new Scene(report);

        ViewLabReportController lr = reportLoader.getController();
        lr.setPatient(this.patient);

        Stage reportStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        reportStage.setScene(reportScene);
        reportStage.show();
    }
    
    @FXML
    private void viewPoliciesOnClick(ActionEvent event) throws IOException {
        Parent policy = null;
        FXMLLoader policyLoader = new FXMLLoader(getClass().getResource("PatientViewPolicy.fxml"));
        policy = (Parent) policyLoader.load();
        Scene policyScene = new Scene(policy);

        PatientViewPolicyController vs = policyLoader.getController();
        vs.setPatient(this.patient);

        Stage policyStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        policyStage.setScene(policyScene);
        policyStage.show();
    }
    
    @FXML
    private void logOut(ActionEvent event) {
        Parent login=null;
        try {
            login = FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(PatientDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene1 = new Scene(login);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene1);
        window.show();
    }
    
}
