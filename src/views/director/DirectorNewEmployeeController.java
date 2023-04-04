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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Kazi
 */
public class DirectorNewEmployeeController implements Initializable {

    @FXML
    private TextField newEmployeeName;
    @FXML
    private TextField newEmployeeID;
    @FXML
    private TextField newEmployeeEmail;
    @FXML
    private TextField newEmployeePassword;
    @FXML
    private TextField newEmployeeContact;
    @FXML
    private TextField newEmployeeAddress;
    @FXML
    private TextField newEmployeeeDesig;
    @FXML
    private ComboBox<?> newEmployeeDept;
    @FXML
    private DatePicker newEmployeeDOB;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void newEmployeeSaveOnClick(ActionEvent event) {
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
    
}
