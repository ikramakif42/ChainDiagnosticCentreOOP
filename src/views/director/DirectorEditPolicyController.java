/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.director;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import model.Policy;
import users.Director;

/**
 * FXML Controller class
 *
 * @author Kazi
 */
public class DirectorEditPolicyController implements Initializable {

    @FXML
    private Label policyNumber;
    @FXML
    private TextArea policyField;
    private Policy tempPolicy;
    private Director director;

    public Policy getTempPolicy() {
        return tempPolicy;
    }

    public void setTempPolicy(Policy tempPolicy) {
        this.tempPolicy = tempPolicy;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public DirectorEditPolicyController(Policy tempPolicy, Director director) {
        this.tempPolicy = tempPolicy;
        this.director = director;
    }
    
    

    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        policyNumber.setText(String.valueOf(tempPolicy.getNumber()));
        policyField.setText(tempPolicy.getContent());
    }    

    @FXML
    private void returnToDashboardOnClick(ActionEvent event) throws IOException {
        Parent dir = null;
        FXMLLoader officerLoader = new FXMLLoader(
            getClass().getResource("DirectorPolicies.fxml")
        );
        dir = (Parent) officerLoader.load();
        
        DirectorPoliciesController e = officerLoader.getController();
        e.setDirector(this.director);        
        Scene employeeListScene = new Scene(dir);
        
        Stage directorStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        directorStage.setScene(employeeListScene);
        directorStage.show();
    }

    @FXML
    private void saveEdit(ActionEvent event) {
        Director.updatePolicy(tempPolicy.getNumber(), policyField.getText());
    }
    
}
