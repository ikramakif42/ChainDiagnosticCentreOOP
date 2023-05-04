/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.accountsofficer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import users.AccountsOfficer;
import users.Employee;

/**
 * FXML Controller class
 *
 * @author Kazi
 */
public class AccountsOfficerAddOrSubtractSalaryController implements Initializable {

    @FXML
    private Label employeeSalary;
    @FXML
    private Label employeeName;
    @FXML
    private TextField addOrSubtractAmount;
    private AccountsOfficer officer;
    private Employee tempEmployee;
    @FXML
    private Label employeeID;
    @FXML
    private RadioButton addRadioButton;
    @FXML
    private ToggleGroup addSub;
    @FXML
    private RadioButton subtractRadioButton;

    public AccountsOfficer getOfficer() {
        return officer;
    }

    public void setOfficer(AccountsOfficer officer) {
        this.officer = officer;
    }

    public AccountsOfficerAddOrSubtractSalaryController(AccountsOfficer officer, Employee tempEmployee) {
        this.officer = officer;
        this.tempEmployee = tempEmployee;
    }
    
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        employeeSalary.setText(String.valueOf(tempEmployee.getSalary()));
        employeeName.setText(tempEmployee.getName());
        employeeID.setText(String.valueOf(tempEmployee.getID()));
    }    

    @FXML
    private void confirmAddOrSubtractOnClick(ActionEvent event) {
        float newSal = 0;
        if (addRadioButton.isSelected()){
            if (!(addOrSubtractAmount.getText().isEmpty())){
                newSal = tempEmployee.getSalary() + Float.valueOf(addOrSubtractAmount.getText());
        }}
        if (subtractRadioButton.isSelected()){
            if (!(addOrSubtractAmount.getText().isEmpty())){
                newSal = tempEmployee.getSalary() - Float.valueOf(addOrSubtractAmount.getText());
        }}
        
        tempEmployee.setSalary(newSal);
        employeeSalary.setText(String.valueOf(newSal));
        AccountsOfficer.updateSalaries(tempEmployee.getID(), tempEmployee.getName(), tempEmployee.getEmail(), tempEmployee.getContactNo(), tempEmployee.getAddress(), newSal, tempEmployee.getDepartment(), tempEmployee.getDesignation());
                
    }

    @FXML
    private void returnToDashboardOnClick(ActionEvent event) throws IOException {
        Parent officerDashboard = null;
        FXMLLoader officerLoader = new FXMLLoader(getClass().getResource("AccountsOfficerSalary.fxml"));
        officerDashboard = (Parent) officerLoader.load();
        Scene directorScene = new Scene(officerDashboard);

        AccountsOfficerSalaryController e = officerLoader.getController();
        e.setOfficer(officer);

        Stage directorStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        directorStage.setScene(directorScene);
        directorStage.show();                                  
    }
    
}
