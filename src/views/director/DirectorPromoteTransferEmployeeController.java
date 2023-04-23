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
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author Kazi
 */
public class DirectorPromoteTransferEmployeeController implements Initializable {

    @FXML
    private ComboBox<?> currentDesignationSelection;
    @FXML
    private ComboBox<?> currentDepartmentSelection;
    @FXML
    private ComboBox<?> newDepartmentSelection;
    @FXML
    private ComboBox<?> newDesignationSelection;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void saveButton(ActionEvent event) {
    }
    
}
