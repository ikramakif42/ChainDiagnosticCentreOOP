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
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Kazi
 */
public class DirectorEmployeeListEditViewController implements Initializable {

    @FXML
    private TextField directorEditName;
    @FXML
    private TextField directorEditEmail;
    @FXML
    private TextField directorEditContact;
    @FXML
    private TextField directorEditAddress;
    @FXML
    private TextField directorEditViewID;
    @FXML
    private TextField directorEditViewDepartment;
    @FXML
    private TextField directorEditViewDesignation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void editEmployeeDetailsOnClick(ActionEvent event) {
    }

    
}
