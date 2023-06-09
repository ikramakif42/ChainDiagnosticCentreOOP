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
import model.LoanApplication;
import model.MedRestock;
import users.AccountsOfficer;
import views.LoginController;
import views.employee.MyWorkplaceController;

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
//        
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
//        MedRestock test1 = new MedRestock("Ibuprofen", 10, false);
//        MedRestock test2 = new MedRestock("Napa", 10, false);
//        MedRestock test3 = new MedRestock("Montene", 10, false);
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
        accountsIDLabel.setText(String.valueOf(officer.getID()));
        accountsNameLabel.setText(officer.getName());        
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
        Parent login=null;
        try {
            login = FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(AccountsOfficerDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene1 = new Scene(login);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene1);
        window.show();        
    }

    @FXML
    private void viewReports(ActionEvent event) throws IOException {
        Parent bills = null;
        FXMLLoader officerLoader = new FXMLLoader(
            getClass().getResource("AccountsOfficerFinanceReports.fxml")
        );
        bills = (Parent) officerLoader.load();
        Scene employeeListScene = new Scene(bills);
        
        AccountsOfficerFinanceReportsController e = officerLoader.getController();
        e.setOfficer(this.officer);
        
        Stage directorStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        directorStage.setScene(employeeListScene);
        directorStage.show();        
    }

    @FXML
    private void viewLoanApplications(ActionEvent event) throws IOException {
        Parent bills = null;
        FXMLLoader officerLoader = new FXMLLoader(
            getClass().getResource("AccountsOfficerLoanApplications.fxml")
        );
        bills = (Parent) officerLoader.load();
        Scene employeeListScene = new Scene(bills);
        
        AccountsOfficerLoanApplicationsController e = officerLoader.getController();
        e.setOfficer(this.officer);
        
        Stage directorStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        directorStage.setScene(employeeListScene);
        directorStage.show();                
    }

    @FXML
    private void viewSalary(ActionEvent event) throws IOException {
        Parent parent = null;
        FXMLLoader directorLoader = new FXMLLoader(
            getClass().getResource("AccountsOfficerSalary.fxml")
        );
        parent = (Parent) directorLoader.load();
        Scene scene = new Scene(parent);
        
        AccountsOfficerSalaryController e = directorLoader.getController();
        e.setOfficer(this.officer);
        
        
        Stage directorStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        directorStage.setScene(scene);
        directorStage.show();   
    }

    @FXML
    private void myWorkplace(ActionEvent event) throws IOException {
        Parent empDashboard = null;
        FXMLLoader empLoader = new FXMLLoader(getClass().getResource("/views/employee/MyWorkplace.fxml"));
        empDashboard = (Parent) empLoader.load();
        Scene empScene = new Scene(empDashboard);

        MyWorkplaceController emp = empLoader.getController();
        emp.setEmployee(this.officer);

        Stage empStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        empStage.setScene(empScene);
        empStage.show();        
    }
    
}
