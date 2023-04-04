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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Kazi
 */
public class DirectorEmployeeListFilterViewController implements Initializable {

    @FXML
    private ComboBox<?> directorFilterViewField;
    @FXML
    private DatePicker directorFilterDOBStart;
    @FXML
    private DatePicker directorFilterDOBEnd;
    @FXML
    private TextField directorFilterViewCriteria;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void directorRunFilter(ActionEvent event) {
    }

    @FXML
    private void directorResetFilter(ActionEvent event) {
    }

    
}
