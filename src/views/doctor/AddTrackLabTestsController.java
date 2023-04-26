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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import users.Doctor;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AddTrackLabTestsController implements Initializable {

    @FXML
    private TableView<?> reportTableView;
    @FXML
    private TableColumn<?, ?> nameTableColumn;
    @FXML
    private TableColumn<?, ?> orderDateTableColumn;
    @FXML
    private TableColumn<?, ?> statusTableColumn;
    @FXML
    private TableColumn<?, ?> viewReportTableColumn;
    @FXML
    private Label patientIDLabel;
    @FXML
    private Label patientNameLabel;
    @FXML
    private Label patientAgeLabel;
    @FXML
    private ComboBox<?> testNameComboBox;
    @FXML
    private ComboBox<?> testPriorityComboBox;
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
    private void returnToMyPatientsOnClick(ActionEvent event) throws IOException {
        Parent parent = null;
        FXMLLoader doctorLoader = new FXMLLoader(getClass().getResource("DoctorMyPatients.fxml"));
        parent = (Parent) doctorLoader.load();
        Scene doctorScene = new Scene(parent);

        DoctorMyPatientsController d = doctorLoader.getController();
        d.setDoc(this.doc);

        Stage doctorStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        doctorStage.setScene(doctorScene);
        doctorStage.show();
    }

    @FXML
    private void submitLabOrderOnClick(ActionEvent event) {
    }
    
}
