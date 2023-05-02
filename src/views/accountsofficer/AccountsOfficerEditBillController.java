/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.accountsofficer;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
        // TODO
    }    

    @FXML
    private void returnToDashboardOnClick(ActionEvent event) {
    }

    @FXML
    private void saveEdit(ActionEvent event) {
        
        
    }
    
}
