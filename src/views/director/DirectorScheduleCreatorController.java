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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import users.Director;
import users.Employee;

/**
 * FXML Controller class
 *
 * @author Kazi
 */
public class DirectorScheduleCreatorController implements Initializable {

    @FXML
    private TextArea taskBodyField;
    @FXML
    private DatePicker taskDay;
    @FXML
    private ComboBox<?> taskTime;
    private Director director;
    private Employee tempEmployee;
    @FXML
    private Label employeeNameLabel;

    public Employee getTempEmployee() {
        return tempEmployee;
    }

    public void setTempEmployee(Employee tempEmployee) {
        this.tempEmployee = tempEmployee;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        employeeNameLabel.setText(tempEmployee.getName());
    }    

    @FXML
    private void returnToDashboardOnClick(ActionEvent event) {
    }

    @FXML
    private void saveTaskInSchedule(ActionEvent event) {
        
    }
    
}
