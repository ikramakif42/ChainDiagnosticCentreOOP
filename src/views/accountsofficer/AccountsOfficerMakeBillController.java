package views.accountsofficer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.AppendableObjectOutputStream;
import model.Bill;
import users.AccountsOfficer;

/**
 * FXML Controller class
 *
 * @author Kazi
 */
public class AccountsOfficerMakeBillController implements Initializable {

    @FXML
    private TextField amountField;
    @FXML
    private TextArea descriptionField;
    @FXML
    private DatePicker dueDate;
    private AccountsOfficer officer;
    @FXML
    private TextField patientIDField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {}    
    
    public AccountsOfficer getOfficer() {
        return officer;
    }

    public void setOfficer(AccountsOfficer officer) {
        this.officer = officer;
    }
    
    @FXML
    private void returnToDashboardOnClick(ActionEvent event) throws IOException {
        Parent billsBack = null;
        FXMLLoader officerLoader = new FXMLLoader(
            getClass().getResource("AccountsOfficerPatientBills.fxml")
        );
        billsBack = (Parent) officerLoader.load();
        Scene employeeListScene = new Scene(billsBack);
        
        AccountsOfficerPatientBillsController e = officerLoader.getController();
        e.setOfficer(this.officer);
        
        Stage directorStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        directorStage.setScene(employeeListScene);
        directorStage.show();
        
    }

    @FXML
    private void saveBill(ActionEvent event) {
        LocalDate due = dueDate.getValue(); 
        float amount = Float.parseFloat(amountField.getText());
        String details = descriptionField.getText();
        int patientID = Integer.parseInt(patientIDField.getText());
//        this.officer.makeBill(due, amount, details, patientID);
    }
    
}
