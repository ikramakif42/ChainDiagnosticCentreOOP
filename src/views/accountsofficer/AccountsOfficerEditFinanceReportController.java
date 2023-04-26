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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Kazi
 */
public class AccountsOfficerEditFinanceReportController implements Initializable {

    @FXML
    private TextArea reportBody;
    @FXML
    private TextField reportTitle;
    @FXML
    private TextField reportAuthor;
    @FXML
    private TextField reportDate;

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

    @FXML
    private void saveFinanceReportEdit(ActionEvent event) {
    }
    
}
