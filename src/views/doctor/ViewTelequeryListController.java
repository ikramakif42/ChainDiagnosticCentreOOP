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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import users.Doctor;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class ViewTelequeryListController implements Initializable {

    @FXML
    private TableView<?> billingInfoTableView;
    @FXML
    private TableColumn<?, ?> billDateTableColumn;
    @FXML
    private TableColumn<?, ?> detailsTableColumn;
    @FXML
    private TableColumn<?, ?> amountTableColumn;
    @FXML
    private RadioButton paidStatusRadioButton;
    @FXML
    private ToggleGroup billStatusTG;
    @FXML
    private RadioButton dueStatusRadioButton;
    @FXML
    private RadioButton dueStatusRadioButton1;
    @FXML
    private ToggleGroup billStatusTG1;
    private Doctor doc;
    @FXML
    private Label errorLabel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
    private void applyFiltersOnClick(ActionEvent event) {
    }

    @FXML
    private void answerQueryOnClick(ActionEvent event) {
    }
    
}
