/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.nurse;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
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
import model.Task;
import users.Nurse;
import users.Patient;

/**
 * FXML Controller class
 *
 * @author User
 */
public class NurseAssignedTaskTableController implements Initializable {

    @FXML
    private TableView<Task> nurseAssignedTaskTable;
    private Nurse nurse;
    @FXML
    private TableColumn<Task, String> nurseAssignedTaskDocNameTable;
    @FXML
    private TableColumn<Task, String> nurseAssignedTaskTaskTable;
    @FXML
    private TableColumn<Task,LocalDate > nurseAssignedTaskDateTable;
    @FXML
    private TableColumn<Task,Integer> nurseAssignedDocIDTable;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        nurseAssignedDocIDTable.setCellValueFactory(new PropertyValueFactory<Task,Integer>("ID"));
        nurseAssignedTaskDocNameTable.setCellValueFactory(new PropertyValueFactory<Task,String>("name"));
        nurseAssignedTaskTaskTable.setCellValueFactory(new PropertyValueFactory<Task,String>("contactNo"));
        nurseAssignedTaskDateTable.setCellValueFactory(new PropertyValueFactory<Task,String>("contactNo"));
        nurseAssignedTaskTable.setItems(Patient.getPatients());
        // TODO
    }    

    public Nurse getNurse() {
        return nurse;
    }

    public void setNurse(Nurse nurse) {
        this.nurse = nurse;
    }

    @FXML
    private void NurseAssignedTasksBackToDashOnClick(ActionEvent event) throws IOException {
        Parent nurseDashboard = null;
        FXMLLoader nurseLoader = new FXMLLoader(getClass().getResource("NurseDashboard.fxml"));
        nurseDashboard = (Parent) nurseLoader.load();
        Scene nurseScene = new Scene(nurseDashboard);

        NurseDashboardController nu = nurseLoader.getController();
        nu.setNurse(this.nurse);

        Stage nurseStage  = (Stage)((Node)event.getSource()).getScene().getWindow();
        nurseStage.setScene(nurseScene);
        nurseStage.show();
    }
    
}
