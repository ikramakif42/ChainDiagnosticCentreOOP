/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.director;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Schedule;
import users.Director;
import users.Employee;

/**
 * FXML Controller class
 *
 * @author Kazi
 */
public class DirectorEmployeeScheduleController implements Initializable {

    @FXML
    private TableView<Schedule> employeeTaskTableView;
    @FXML
    private TableColumn<Schedule, String> employeeTask;
    @FXML
    private TableColumn<Schedule, LocalDate> taskDay;
    @FXML
    private TableColumn<Schedule, String> taskTime;
    private Employee tempEmployee;
    private Director director;

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
        employeeTask.setCellValueFactory(new PropertyValueFactory<Schedule, String>("task"));
        taskDay.setCellValueFactory(new PropertyValueFactory<Schedule, LocalDate>("day"));
        taskTime.setCellValueFactory(new PropertyValueFactory<Schedule, String>("time"));
        
        if (tempEmployee.getScheduleRoster() == null){
            ; 
        }
        
        else {
            employeeTaskTableView.setItems(getSchedule()); 
        }
        
    }    

    public Director getDirector() {
        return director;
    }
        
    public void setDirector(Director director) {
        this.director = director;
    }    
    
    @FXML
    private void returnToDashboardOnClick(ActionEvent event) {
    }

    @FXML
    private void createNewTask(ActionEvent event) throws IOException {
        Parent parent = null;
        FXMLLoader directorLoader = new FXMLLoader(
            getClass().getResource("DirectorScheduleCreator.fxml")
        );
        parent = (Parent) directorLoader.load();
        Scene scene = new Scene(parent);
        
        DirectorScheduleCreatorController e = directorLoader.getController();
        e.setDirector(this.director);
        e.setTempEmployee(tempEmployee);
        
        
        Stage directorStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        directorStage.setScene(scene);
        directorStage.show();
    }

    @FXML
    private void deleteTask(ActionEvent event) {
    }
    
    public ObservableList<Schedule> getSchedule(){
    ObservableList<Schedule> scheduleList = null;
    
        scheduleList = FXCollections.observableList(tempEmployee.getScheduleRoster());            
        return scheduleList;
        
        
        }
    
}         
    
    

    
    


