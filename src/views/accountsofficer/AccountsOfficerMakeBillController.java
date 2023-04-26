/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    public AccountsOfficer getOfficer() {
        return officer;
    }

    public void setOfficer(AccountsOfficer officer) {
        this.officer = officer;
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
        Alert a = new Alert(AlertType.NONE);
        Bill newBill = new Bill(dueDate.getValue(), Float.parseFloat(amountField.getText()), descriptionField.getText(), Integer.parseInt(patientIDField.getText()));
        
        File f = null;
        FileOutputStream fos = null;      
        ObjectOutputStream oos = null;
        LocalDate date1 = LocalDate.of(2023, 5, 10);
        try {
            f = new File("BillObjects.bin");
            if(f.exists()){
                fos = new FileOutputStream(f,true);
                oos = new AppendableObjectOutputStream(fos);                
            }
            else{
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);               
            }          
            
        oos.writeObject(newBill);
        a.setAlertType(AlertType.INFORMATION);
        a.show();
                        
        } catch (IOException ex) {
                System.out.println(ex.toString());
                System.out.println("IOException | ClassNotFoundException in reading bin file");
                a.setAlertType(AlertType.WARNING);
                a.show();
        }
        System.out.println("Hello World2! Initialised");      
        
        
        
       
    }
    
}
