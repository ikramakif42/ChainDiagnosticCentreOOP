/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.accountsofficer;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Kazi
 */
public class AccountsOfficerPastRecordsController implements Initializable {

    @FXML
    private TableView<?> pastRecordsTableView;
    @FXML
    private TableColumn<?, ?> billPatientID;
    @FXML
    private TableColumn<?, ?> billStart;
    @FXML
    private TableColumn<?, ?> billDue;
    @FXML
    private TableColumn<?, ?> billAmount;
    @FXML
    private TableColumn<?, ?> billDescription;
    @FXML
    private TextField IDSearchTextField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void returnToDashboardOnClick(ActionEvent event) {
    }
    
}
