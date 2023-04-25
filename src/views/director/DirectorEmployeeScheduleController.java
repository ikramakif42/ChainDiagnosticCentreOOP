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
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private Employee employee;
    private Director director;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        employeeTask.setCellValueFactory(new PropertyValueFactory<Schedule, String>("task"));
        taskDay.setCellValueFactory(new PropertyValueFactory<Schedule, LocalDate>("day"));
        taskTime.setCellValueFactory(new PropertyValueFactory<Schedule, String>("time"));
        
        employeeTaskTableView.setItems(getSchedule());
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
    private void createNewTask(ActionEvent event) {
    }

    @FXML
    private void deleteTask(ActionEvent event) {
    }
    
    public ObservableList<Schedule> getSchedule(){
        ObservableList<Schedule> scheduleList = FXCollections.observableArrayList();
        File f = null;
        FileInputStream fis = null;      
        ObjectInputStream ois = null;
        String path = "ScheduleObjects.bin";
        try {
            f = new File(path);
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            Schedule tempSch = null;
            try{
                while(true){
                    tempSch = (Schedule) ois.readObject();
                    scheduleList.add(tempSch);
                }
            }
            catch(IOException | ClassNotFoundException e){
                System.out.println(e.toString());
                System.out.println("IOException | ClassNotFoundException in reading bin file");
            }
            System.out.println("End of file\n");
        } catch (IOException ex) {
            System.out.println(ex.toString());
            System.out.println("IOException on entire file handling");
        }
        finally {
            try {
                if(ois != null) ois.close();
            } catch (IOException ex) { }
        }
        System.out.println(scheduleList);        
        return scheduleList;
    }         
    
}
