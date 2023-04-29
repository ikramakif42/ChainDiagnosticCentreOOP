/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.director;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import model.Schedule;
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
    private ComboBox<String> taskTime;
    private Director director;
    private Employee tempEmployee;
    @FXML
    private Label employeeNameLabel;
    Alert a = new Alert(Alert.AlertType.INFORMATION, "New Task Created");
    Alert b = new Alert(Alert.AlertType.WARNING, "Edit Unsuccessful");


    public DirectorScheduleCreatorController(Director director, Employee tempEmployee) {
        this.director = director;
        this.tempEmployee = tempEmployee;

        
        
    }
    
    
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
        taskTime.getItems().add("9:00 AM");
        taskTime.getItems().add("9:30 AM");
        taskTime.getItems().add("10:00 AM");
        taskTime.getItems().add("10:30 AM");
        taskTime.getItems().add("11:00 AM");
        taskTime.getItems().add("11:30 AM");
        taskTime.getItems().add("12:00 PM");
        taskTime.getItems().add("12:30 PM");
        taskTime.getItems().add("1:00 PM");
        taskTime.getItems().add("1:30 PM");
        taskTime.getItems().add("2:00 PM");
        taskTime.getItems().add("2:30 PM");
        taskTime.getItems().add("3:00 PM");
        taskTime.getItems().add("3:30 PM");
        taskTime.getItems().add("4:00 PM");
        taskTime.getItems().add("4:30 PM");
    }    

    @FXML
    private void returnToDashboardOnClick(ActionEvent event) throws IOException {
        Parent root = null;
        FXMLLoader scheduleLoader = new FXMLLoader(getClass().getResource("DirectorEmployeeSchedule.fxml"));
        DirectorEmployeeScheduleController q = new DirectorEmployeeScheduleController(this.director, this.tempEmployee);
        scheduleLoader.setController(q);
        root = (Parent) scheduleLoader.load();

        Scene scheduleScene = new Scene(root);
        Stage scheduleStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        scheduleStage.setScene(scheduleScene);
        scheduleStage.show();
    }

    @FXML
    private void saveTaskInSchedule(ActionEvent event) {
        LocalDate date1 = LocalDate.of(2023, 5, 10);
        Schedule newSch = new Schedule(date1, taskTime.getValue(), taskBodyField.getText(), tempEmployee.getID());        
        boolean success = Director.editSchedule(newSch);
        
        if (success){
            a.show();
        }
        else {
            b.show();
        }
        
        
    }
    
}
