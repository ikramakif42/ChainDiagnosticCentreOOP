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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import users.Director;
import users.Employee;

/**
 * FXML Controller class
 *
 * @author Kazi
 */
public class DirectorPromoteTransferEmployeeController implements Initializable {

    @FXML
    private ComboBox<String> newDepartmentSelection;
    @FXML
    private ComboBox<String> newDesignationSelection;
    @FXML
    private TextField curDept;
    @FXML
    private TextField curDesig;
    private Director director;
    private Employee tempEmployee;
    Alert a = new Alert(Alert.AlertType.INFORMATION, "Successful");
    Alert b = new Alert(Alert.AlertType.WARNING, "Unsuccessful");

    public DirectorPromoteTransferEmployeeController(Director director, Employee tempEmployee) {
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

    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        curDept.setText(tempEmployee.getDepartment());
        curDesig.setText(tempEmployee.getDesignation());
        
        newDepartmentSelection.getItems().add("Accounts");
        newDepartmentSelection.getItems().add("Ortho");  
        newDepartmentSelection.getItems().add("Pedia");        
        newDepartmentSelection.getItems().add("Neuro");
        newDepartmentSelection.getItems().add("Bio");
        newDepartmentSelection.getItems().add("Radio");
        newDepartmentSelection.getItems().add("ER");
        newDepartmentSelection.getItems().add("Attending");      
        
        newDesignationSelection.getItems().add("Senior");
        newDesignationSelection.getItems().add("Junior");
        newDesignationSelection.getItems().add("Intern");
        newDesignationSelection.getItems().add("Senior Dr");
        newDesignationSelection.getItems().add("Junior Dr");
        newDesignationSelection.getItems().add("Dispenser");        
        newDesignationSelection.getItems().add("Packager");        
        
        
    }    

    @FXML
    private void saveButton(ActionEvent event) {
        boolean success = Director.editPersonalInfo(tempEmployee.getID(), tempEmployee.getName(), tempEmployee.getEmail(), tempEmployee.getContactNo(), tempEmployee.getAddress(), tempEmployee.getSalary(), newDepartmentSelection.getValue(), newDesignationSelection.getValue());
        if (success){
            a.show();
        }
        else {
            b.show();
        }        
    }

    @FXML
    private void backButton(ActionEvent event) throws IOException {
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
