/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.HROfficer;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import users.HROfficer;

/**
 * FXML Controller class
 *
 * @author arafath
 */
public class AddNewEmployeeController implements Initializable {
    
    private HROfficer HR;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    public HROfficer getHR() {
        return HR;
    }

    public void setHR(HROfficer HR) {
        this.HR = HR;
    }
    
    
    
    @FXML
    private void HireNewEmployeeOnClick(ActionEvent event) {
    }

    @FXML
    private void ConfirmJobOnClick(ActionEvent event) {
    }

    @FXML
    private void ReturnHireEmployeeOnClick(ActionEvent event) {
    }
    
}
