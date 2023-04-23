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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Kazi
 */
public class DirectorAddOrSubtractSalaryController implements Initializable {

    @FXML
    private Label employeeSalary;
    @FXML
    private Label employeeName;
    @FXML
    private TextField addOrSubtractAmount;
    @FXML
    private CheckBox addCheckbox;
    @FXML
    private CheckBox subtractCheckbox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void confirmAddOrSubtractOnClick(ActionEvent event) {
    }

    @FXML
    private void returnToDashboardOnClick(ActionEvent event) {
    }
    
}
