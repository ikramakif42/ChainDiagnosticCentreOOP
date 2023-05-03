/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.accountsofficer;

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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Bill;
import users.AccountsOfficer;

/**
 * FXML Controller class
 *
 * @author Kazi
 */
public class AccountsOfficerEditBillController implements Initializable {

    @FXML
    private TextField amountField;
    @FXML
    private TextArea descriptionField;
    @FXML
    private DatePicker dueDate;
    @FXML
    private Label billedByLabel;
    @FXML
    private Label patientIDLabel;
    @FXML
    private Label createdOn;
    private AccountsOfficer officer;
    private Bill tempBill;
    Alert a = new Alert(Alert.AlertType.INFORMATION, "Edit Successful");
    Alert b = new Alert(Alert.AlertType.WARNING, "Edit Unsuccessful");

    public AccountsOfficerEditBillController(AccountsOfficer officer, Bill tempBill) {
        this.officer = officer;
        this.tempBill = tempBill;
    }

    public AccountsOfficer getOfficer() {
        return officer;
    }

    public void setOfficer(AccountsOfficer officer) {
        this.officer = officer;
    }

    public Bill getTempBill() {
        return tempBill;
    }

    public void setTempBill(Bill tempBill) {
        this.tempBill = tempBill;
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        billedByLabel.setText(String.valueOf(tempBill.getBilledByID()));
        patientIDLabel.setText(String.valueOf(tempBill.getPatientID()));
        createdOn.setText(String.valueOf(tempBill.getCreatedDate()));
        dueDate.setValue(tempBill.getDueDate());
        amountField.setText(String.valueOf(tempBill.getAmount()));
        descriptionField.setText(tempBill.getDetails());
        
    }    

    @FXML
    private void returnToDashboardOnClick(ActionEvent event) throws IOException {
        Parent directorDashboard = null;
        FXMLLoader directorLoader = new FXMLLoader(getClass().getResource("AccountsOfficerPatientBills.fxml"));
        directorDashboard = (Parent) directorLoader.load();
        Scene directorScene = new Scene(directorDashboard);

        AccountsOfficerPatientBillsController di = directorLoader.getController();
        di.setOfficer(officer);

        Stage directorStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        directorStage.setScene(directorScene);
        directorStage.show();
    }

    @FXML
    private void saveEdit(ActionEvent event) {
        boolean success = AccountsOfficer.editBillInfo(tempBill, dueDate.getValue(), false, Float.valueOf(amountField.getText()), descriptionField.getText());
        if (success){
            a.show();
        }
        else {
            b.show();
        }        
    }
    
}
