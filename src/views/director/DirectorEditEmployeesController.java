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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import users.Director;
import users.Employee;

/**
 * FXML Controller class
 *
 * @author Kazi
 */
public class DirectorEditEmployeesController implements Initializable {

    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField addressField;
    @FXML
    private Label genderLabel;
    @FXML
    private Label idLabel;
    @FXML
    private Label dobLabel;    
    private Director director;
    private Employee tempEmployee;
    Alert a = new Alert(AlertType.INFORMATION, "Edit Successful");
    Alert b = new Alert(AlertType.WARNING, "Edit Unsuccessful");

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

    public DirectorEditEmployeesController(Director director, Employee tempEmployee) {
        this.director = director;
        this.tempEmployee = tempEmployee;
    }

    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nameField.setText(tempEmployee.getName());
        emailField.setText(tempEmployee.getEmail());
        phoneField.setText(tempEmployee.getContactNo());
        addressField.setText(tempEmployee.getAddress());
        genderLabel.setText(tempEmployee.getGender());
        idLabel.setText(String.valueOf(tempEmployee.getID()));
        dobLabel.setText(String.valueOf(tempEmployee.getDOB()));
        
        
        
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

    @FXML
    private void saveEdit(ActionEvent event) {

        boolean success = Director.editPersonalInfo(tempEmployee.getID(), nameField.getText(), emailField.getText(), phoneField.getText(), addressField.getText(), tempEmployee.getSalary(), tempEmployee.getDepartment(), tempEmployee.getDesignation());
        if (success){
            a.show();
        }
        else {
            b.show();
        }
    }
    
}
