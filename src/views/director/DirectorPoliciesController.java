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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Kazi
 */
public class DirectorPoliciesController implements Initializable {

    @FXML
    private TableView<?> directorPolicyTable;
    @FXML
    private TextField directorPolicyCreationTextField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void directorSavePolicyEditOnClick(ActionEvent event) {
    }

    @FXML
    private void directorAddNewPolicyOnClick(ActionEvent event) {
    }


    @FXML
    private void backButton(ActionEvent event) {
        try{
        javafx.scene.Parent root = FXMLLoader.load(getClass().getResource("DirectorDashboard.fxml"));
        Scene scene = new Scene(root);
        Stage stg = (Stage)((Node)event.getSource()).getScene().getWindow();
        stg.setScene(scene);
        stg.show();
    }
    catch(Exception e){
        e.printStackTrace();
    }
    }

    @FXML
    private void directorDeletePolicyOnClick(ActionEvent event) {
    }
    
}