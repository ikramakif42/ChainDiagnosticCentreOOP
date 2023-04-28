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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.LoanApplication;
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
    @FXML
    private Label errorLabel;
    private Employee employee;

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
        if (type==null){errorLabel.setText("Error, select loan type!");return;}
        String amountText = loanAmountTextField.getText();
        if (amountText.isEmpty()){errorLabel.setText("Error, select loan amount!");return;}
        try {
            amount = Float.parseFloat(amountText);
            if (amount<10000){errorLabel.setText("Error, minimum amount is 25,000!");return;}
        }
        catch (NumberFormatException n) {errorLabel.setText("Error, enter valid amount!");return;}
        LocalDate date = loanDatePicker.getValue();
        if (date==null){errorLabel.setText("Error, select disbursement date!");return;}
        String duration = loanDurationComboBox.getSelectionModel().getSelectedItem();
        if (duration==null){errorLabel.setText("Error, select loan duration!");return;}
        String details = loanApplicationTextArea.getText();
        if (details.isEmpty()){errorLabel.setText("Error, enter application details!");return;}
        
        
        this.employee.submitLoanApplication(amount, date, duration, type, details);
        errorLabel.setText("Leave Application submitted successfully!");
        
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
