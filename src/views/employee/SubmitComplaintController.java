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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import users.Employee;

public class SubmitComplaintController implements Initializable {

    @FXML
    private TextField complaintSubjectTextField;
    @FXML
    private TextArea complaintTextArea;
    @FXML
    private Label errorLabel;
    private Employee employee;

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
        if (subject.isEmpty()){errorLabel.setText("Error, enter subject!");return;}
        String details = complaintTextArea.getText();
        if (details.isEmpty()){errorLabel.setText("Error, enter details!");return;}
        
        this.employee.submitComplaint(subject, details);
        errorLabel.setText("Complaint submitted successfully!");
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
