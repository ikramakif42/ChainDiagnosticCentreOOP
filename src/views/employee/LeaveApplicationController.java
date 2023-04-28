package views.employee;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import model.LeaveApplication;
import users.Employee;

public class LeaveApplicationController implements Initializable {

    @FXML
    private ComboBox<String> leaveRequestTypeComboBox;
    @FXML
    private DatePicker leaveStartDatePicker;
    @FXML
    private DatePicker leaveEndDatePicker;
    @FXML
    private TextArea leaveApplicationTextArea;
    @FXML
    private Label errorLabel;
    private Employee employee;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String[] types = {"Sick", "Vacation", "Emergency", "Maternity"};
        leaveRequestTypeComboBox.getItems().addAll(types);
        
        leaveStartDatePicker.setDayCellFactory(dp -> new DateCell(){
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (date.isBefore(LocalDate.now())) {
                    setDisable(true);
                }
            }
        });
        leaveEndDatePicker.setDayCellFactory(dp -> new DateCell(){
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (date.isBefore(leaveStartDatePicker.getValue())) {
                    setDisable(true);
                }
            }
        });
    }    

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
        System.out.println("Leave "+this.employee.toString());
    }

    @FXML
    private void submitApplicationOnClick(ActionEvent event) {
        String type = leaveRequestTypeComboBox.getSelectionModel().getSelectedItem();
        if (type==null){errorLabel.setText("Error, select leave request type!");return;}
        LocalDate start = leaveStartDatePicker.getValue();
        LocalDate end = leaveEndDatePicker.getValue();
        if(start==null || end==null){errorLabel.setText("Error, select leave dates!");return;}
        String details = leaveApplicationTextArea.getText();
        if (details.isEmpty()){errorLabel.setText("Error, enter application details!");return;}
        
        this.employee.submitLeaveApplication(start, end, type, details);
        errorLabel.setText("Leave Application submitted successfully!");
        
        leaveRequestTypeComboBox.getSelectionModel().clearSelection();
        leaveStartDatePicker.setValue(null);
        leaveEndDatePicker.setValue(null);
        leaveApplicationTextArea.clear();
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