/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.accountsofficer;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Kazi
 */
public class AccountsOfficerReportDetailsViewController implements Initializable {

    @FXML
    private TextArea financeReportDetailsViewBody;
    @FXML
    private TextField financeReportDetailsViewTitle;
    @FXML
    private TextField financeReportDetailsViewPublisher;
    @FXML
    private TextField financeReportDetailsViewDate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
