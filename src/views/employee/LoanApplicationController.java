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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import users.Employee;

public class LoanApplicationController implements Initializable {

    @FXML
    private ComboBox<String> loanTypeComboBox;
    @FXML
    private TextField loanAmountTextField;
    @FXML
    private ComboBox<String> loanDurationComboBox;
    @FXML
    private TextArea loanApplicationTextArea;
    @FXML
    private DatePicker loanDatePicker;
    private Employee employee;
    Alert noType = new Alert(Alert.AlertType.WARNING, "Error, select loan type!");
    Alert noAmount = new Alert(Alert.AlertType.WARNING, "Error, enter loan amount!");
    Alert minAmount = new Alert(Alert.AlertType.WARNING, "Error, minimum loan amount is BDT 25,000!");
    Alert badAmount = new Alert(Alert.AlertType.WARNING, "Error, enter valid amount!");
    Alert noDate = new Alert(Alert.AlertType.WARNING, "Error, select date of disbursement!");
    Alert noDuration = new Alert(Alert.AlertType.WARNING, "Error, select loan duration!");
    Alert noDetails = new Alert(Alert.AlertType.WARNING, "Error, enter loan application details!");
    Alert failure = new Alert(Alert.AlertType.WARNING, "Error, failed to submit loan application!");
    Alert success = new Alert(Alert.AlertType.INFORMATION, "Loan application submitted successfully!");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String[] types = {"Personal", "Home", "Emergency", "Vehicle", "Business"};
        loanTypeComboBox.getItems().addAll(types);
        String[] durations = {"3 months", "6 months", "9 months", "1 year"};
        loanDurationComboBox.getItems().addAll(durations);
        loanDatePicker.setDayCellFactory(dp -> new DateCell(){
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (date.isBefore(LocalDate.now())) {
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
    private void submitApplicationOnClick(ActionEvent event){
        float amount=0f;
        String type = loanTypeComboBox.getSelectionModel().getSelectedItem();
        if (type==null){noType.show();return;}
        String amountText = loanAmountTextField.getText();
        if (amountText.isEmpty()){noAmount.show();return;}
        try {
            amount = Float.parseFloat(amountText);
            if (amount<10000){minAmount.show();return;}
        }
        catch (NumberFormatException n) {badAmount.show();return;}
        LocalDate date = loanDatePicker.getValue();
        if (date==null){noDate.show();return;}
        String duration = loanDurationComboBox.getSelectionModel().getSelectedItem();
        if (duration==null){noDuration.show();return;}
        String details = loanApplicationTextArea.getText();
        if (details.isEmpty()){noDetails.show();return;}
        
        
        if(this.employee.submitLoanApplication(amount, date, duration, type, details)){success.show();}
        else{failure.show();return;}
        
        loanTypeComboBox.getSelectionModel().clearSelection();
        loanAmountTextField.clear();
        loanDatePicker.setValue(null);
        loanDurationComboBox.getSelectionModel().clearSelection();
        loanApplicationTextArea.clear();
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
