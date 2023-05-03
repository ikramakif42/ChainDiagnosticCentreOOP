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
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import users.Employee;

public class SubmitComplaintController implements Initializable {

    @FXML
    private TextField complaintSubjectTextField;
    @FXML
    private TextArea complaintTextArea;
    private Employee employee;
    Alert noSub = new Alert(Alert.AlertType.WARNING, "Error, enter complaint subject!");
    Alert noDetails = new Alert(Alert.AlertType.WARNING, "Error, enter complaint details!");
    Alert failure = new Alert(Alert.AlertType.WARNING, "Error, failed to submit complaint!");
    Alert success = new Alert(Alert.AlertType.WARNING, "Complaint submitted successfully!");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @FXML
    private void submitComplaintOnClick(ActionEvent event) {
        String subject = complaintSubjectTextField.getText();
        if (subject.isEmpty()){noSub.show();return;}
        String details = complaintTextArea.getText();
        if (details.isEmpty()){noDetails.show();return;}
        
        if (this.employee.submitComplaint(subject, details)) {success.show();}
        else {failure.show();return;}
        
        complaintSubjectTextField.clear();
        complaintTextArea.clear();
    }

    @FXML
    private void returnToDashboardOnClick(ActionEvent event) throws IOException {
        Parent empDashboard = null;
        FXMLLoader empLoader = new FXMLLoader(getClass().getResource("MyWorkplace.fxml"));
        empDashboard = (Parent) empLoader.load();
        Scene empScene = new Scene(empDashboard);

        MyWorkplaceController emp = empLoader.getController();
        emp.setEmployee(this.employee);

        Stage empStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        empStage.setScene(empScene);
        empStage.show();
    }
    
}
