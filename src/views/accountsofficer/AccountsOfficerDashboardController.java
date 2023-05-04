/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.accountsofficer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.AppendableObjectOutputStream;
import model.Bill;
import model.MedRestock;
import users.AccountsOfficer;
import views.LoginController;

/**
 * FXML Controller class
 *
 * @author Kazi
 */
public class AccountsOfficerDashboardController implements Initializable {

    @FXML
    private Label accountsIDLabel;
    @FXML
    private Label accountsNameLabel;
    private AccountsOfficer officer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
//        File f = null;
//        FileOutputStream fos = null;      
//        ObjectOutputStream oos = null;
//        LocalDate today = LocalDate.now();
//        LocalDate date2 = LocalDate.of(2023, 5, 10);
//        try {
//            f = new File("RestockRequests.bin");
//            if(f.exists()){
//                fos = new FileOutputStream(f,true);
//                oos = new AppendableObjectOutputStream(fos);                
//            }
//            else{
//                fos = new FileOutputStream(f);
//                oos = new ObjectOutputStream(fos);               
//            }
//            
//        MedRestock test1 = new MedRestock("Ibuprofen", 2, false);
//        MedRestock test2 = new MedRestock("Napa", 3, false);
//        MedRestock test3 = new MedRestock("Montene", 1, false);        
//            
//        oos.writeObject(test1);
//        oos.writeObject(test2);
//        oos.writeObject(test3);
//            
//            
//        } catch (IOException ex) {
//                System.out.println(ex.toString());
//                System.out.println("IOException | ClassNotFoundException in reading bin file");
//        }
//        System.out.println("Hello World2! Initialised");




    }
    
    public AccountsOfficer getOfficer() {
        return officer;
    }
        
    public void setOfficer(AccountsOfficer officer) {
        this.officer = officer;
    }

    @FXML
    private void viewBills(ActionEvent event) throws IOException {
        Parent bills = null;
        FXMLLoader officerLoader = new FXMLLoader(
            getClass().getResource("AccountsOfficerPatientBills.fxml")
        );
        bills = (Parent) officerLoader.load();
        Scene employeeListScene = new Scene(bills);
        
        AccountsOfficerPatientBillsController e = officerLoader.getController();
        e.setOfficer(this.officer);
        
        Stage directorStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        directorStage.setScene(employeeListScene);
        directorStage.show();
        
    }

    @FXML
    private void viewRestocks(ActionEvent event) throws IOException {
        Parent bills = null;
        FXMLLoader officerLoader = new FXMLLoader(
            getClass().getResource("AccountsOfficerRestock.fxml")
        );
        bills = (Parent) officerLoader.load();
        Scene employeeListScene = new Scene(bills);
        
        AccountsOfficerRestockController e = officerLoader.getController();
        e.setOfficer(this.officer);
        
        Stage directorStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        directorStage.setScene(employeeListScene);
        directorStage.show();
    }

    @FXML
    private void viewPastRecords(ActionEvent event) throws IOException {
        Parent bills = null;
        FXMLLoader officerLoader = new FXMLLoader(
            getClass().getResource("AccountsOfficerPastRecords.fxml")
        );
        bills = (Parent) officerLoader.load();
        Scene employeeListScene = new Scene(bills);
        
        AccountsOfficerPastRecordsController e = officerLoader.getController();
        e.setOfficer(this.officer);
        
        Stage directorStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        directorStage.setScene(employeeListScene);
        directorStage.show();
        
    }

    @FXML
    private void logOut(ActionEvent event) {
    }

    @FXML
    private void viewReports(ActionEvent event) {
    }

    @FXML
    private void viewLoanApplications(ActionEvent event) {
    }

    @FXML
    private void viewSalary(ActionEvent event) {
    }
    
}
