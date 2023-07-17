/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.director;

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
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import users.Director;
import users.Employee;
import views.accountsofficer.AccountsOfficerPatientBillsController;

/**
 * FXML Controller class
 *
 * @author Kazi
 */
public class DirectorAddOrSubtractSalaryController implements Initializable {

    @FXML
    private Label employeeSalary;
    @FXML
    private Label employeeName;
    @FXML
    private TextField addOrSubtractAmount;
    @FXML
    private Label employeeID;
    private Director director;
    private Employee tempEmployee;
    @FXML
    private RadioButton addRadioButton;
    @FXML
    private RadioButton subtractRadioButton;
    @FXML
    private ToggleGroup addSub;
    Alert a = new Alert(Alert.AlertType.INFORMATION, "Successful");
    Alert b = new Alert(Alert.AlertType.WARNING, "Unsuccessful");
    
    
    public DirectorAddOrSubtractSalaryController(Director director, Employee tempEmployee) {
        this.director = director;
        this.tempEmployee = tempEmployee;
    }
    
    
    
    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public Employee getTempEmployee() {
        return tempEmployee;
    }

    public void setTempEmployee(Employee tempEmployee) {
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
        boolean success = Director.editPersonalInfo(tempEmployee.getID(), tempEmployee.getName(), tempEmployee.getEmail(), tempEmployee.getContactNo(), tempEmployee.getAddress(), newSal, tempEmployee.getDepartment(), tempEmployee.getDesignation());
                if (success){
            a.show();
        }
        else {
            b.show();
        }        
        
    }

    @FXML
    private void returnToDashboardOnClick(ActionEvent event) throws IOException {
        Parent bills = null;
        FXMLLoader officerLoader = new FXMLLoader(
            getClass().getResource("DirectorViewEmployees.fxml")
        );
        bills = (Parent) officerLoader.load();
        
        DirectorViewEmployeesController e = officerLoader.getController();
        e.setDirector(this.director);
        
        Scene employeeListScene = new Scene(bills);
        

        
        Stage directorStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        directorStage.setScene(employeeListScene);
        directorStage.show();
    }
    
}
