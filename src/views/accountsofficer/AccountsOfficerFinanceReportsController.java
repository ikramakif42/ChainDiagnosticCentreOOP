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

/**
 * FXML Controller class
 *
 * @author Kazi
 */
public class AccountsOfficerFinanceReportsController implements Initializable {

    @FXML
    private TableView<?> financeReportTableView;
    @FXML
    private TableColumn<?, ?> financeReportTitle;
    @FXML
    private TableColumn<?, ?> financeReportAuthor;
    @FXML
    private TableColumn<?, ?> financeReportDate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void openReportView(ActionEvent event) {
    }

    @FXML
    private void openFinanceReportCreator(ActionEvent event) {
    }

    @FXML
    private void returnToDashboardOnClick(ActionEvent event) {
    }
    
}
