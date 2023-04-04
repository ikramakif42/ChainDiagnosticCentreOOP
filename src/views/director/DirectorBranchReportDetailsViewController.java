/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.director;

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
public class DirectorBranchReportDetailsViewController implements Initializable {

    @FXML
    private TextArea branchReportDetailsViewBody;
    @FXML
    private TextField branchReportDetailsViewTitle;
    @FXML
    private TextField branchReportDetailsViewBranch;
    @FXML
    private TextField branchReportDetailsViewPublisher;
    @FXML
    private TextField branchReportDetailsViewDate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
}
