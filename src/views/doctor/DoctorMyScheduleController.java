/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.doctor;

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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import users.Doctor;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class DoctorMyScheduleController implements Initializable {

    @FXML
    private TableView<?> patientTableView;
    @FXML
    private TableColumn<?, ?> patientIDTableColumn;
    @FXML
    private TableColumn<?, ?> patientNameTableColumn;
    @FXML
    private TableColumn<?, ?> patientAgeTableColumn;
    @FXML
    private TableColumn<?, ?> apptDateTableColumn;
    @FXML
    private TableColumn<?, ?> apptTimeTableColumn;
    @FXML
    private TableColumn<?, ?> lastApptTableColumn;
    @FXML
    private TextField nameSearchTextField;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;
    @FXML
    private TextField IDSearchTextField;
    private Doctor doc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public Doctor getDoc() {
        return doc;
    }

    public void setDoc(Doctor doc) {
        this.doc = doc;
    }
    
    @FXML
    private void returnToDashboardOnClick(ActionEvent event) throws IOException {
        Parent doctorDashboard = null;
        FXMLLoader doctorLoader = new FXMLLoader(getClass().getResource("DoctorDashboard.fxml"));
        doctorDashboard = (Parent) doctorLoader.load();
        Scene doctorScene = new Scene(doctorDashboard);

        DoctorDashboardController d = doctorLoader.getController();
        d.setDoc(this.doc);

        Stage doctorStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        doctorStage.setScene(doctorScene);
        doctorStage.show();
    }

    @FXML
    private void clearFiltersOnClick(ActionEvent event) {
    }

    @FXML
    private void applyFIltersOnClick(ActionEvent event) {
    }

    @FXML
    private void confirmCancelOnClick(ActionEvent event) {
    }
    
}
