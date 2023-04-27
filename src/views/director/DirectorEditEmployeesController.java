/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.director;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
        
        
    }    

    @FXML
    private void returnToDashboardOnClick(ActionEvent event) {
    }

    @FXML
    private void saveEdit(ActionEvent event) {
    }
    
}
