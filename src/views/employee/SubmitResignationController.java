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
import javafx.scene.control.Alert;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import users.Employee;

public class SubmitResignationController implements Initializable {

    @FXML
    private DatePicker resignationDatePicker;
    @FXML
    private TextArea resignationTextArea;
    private Employee employee;
    Alert resigned = new Alert(Alert.AlertType.WARNING, "Error, already submitted resignation!");
    Alert noDate = new Alert(Alert.AlertType.WARNING, "Error, select resignation date!");
    Alert noDetails = new Alert(Alert.AlertType.WARNING, "Error, enter resignation details!");
    Alert failure = new Alert(Alert.AlertType.WARNING, "Error, failed to submit resignation!");
    Alert success = new Alert(Alert.AlertType.WARNING, "Resignation submitted successfully!");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        resignationDatePicker.setDayCellFactory(dp -> new DateCell(){
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (date.isBefore(LocalDate.now().plusDays(14))) {
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
    }

    @FXML
    private void submitResignationOnClick(ActionEvent event) {
        if (this.employee.isResigned()){resigned.show();return;}
        LocalDate date = resignationDatePicker.getValue();
        if (date==null){noDate.show();return;}
        String details = resignationTextArea.getText();
        if (details.isEmpty()){noDetails.show();return;}
        
        if (this.employee.submitResignation(date, details)){success.show();}
        else {failure.show();}
        
        resignationDatePicker.setValue(null);
        resignationTextArea.clear();
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
