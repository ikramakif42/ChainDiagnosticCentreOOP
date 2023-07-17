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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Policy;
import users.Director;
import users.User;

/**
 * FXML Controller class
 *
 * @author Kazi
 */
public class DirectorPoliciesController implements Initializable {
    
    
     private Director director;
    @FXML
    private TableView<Policy> policyTableView;
    @FXML
    private TableColumn<Policy, Integer> polictNumber;
    @FXML
    private TableColumn<Policy, String> policy;
    
    private Policy tempPolicy;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        polictNumber.setCellValueFactory(new PropertyValueFactory<Policy, Integer>("number"));
        policy.setCellValueFactory(new PropertyValueFactory<Policy, String>("content"));
        
        policyTableView.setItems(User.viewPolicies());
        
        
    }
    
    public Director getDirector() {
        return director;
    }
        
    public void setDirector(Director director) {
        this.director = director;
    }
    

    @FXML
    private void editPolicy(ActionEvent event) throws IOException {
        Parent root = null;
        FXMLLoader scheduleLoader = new FXMLLoader(getClass().getResource("DirectorEditPolicy.fxml"));
        DirectorEditPolicyController q = new DirectorEditPolicyController(this.tempPolicy, this.director);
        scheduleLoader.setController(q);
        root = (Parent) scheduleLoader.load();

        Scene scheduleScene = new Scene(root);
        Stage scheduleStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        scheduleStage.setScene(scheduleScene);
        scheduleStage.show();
        
    }

    @FXML
    private void returnToDashboardOnClick(ActionEvent event) throws IOException {
        Parent directorDashboard = null;
        FXMLLoader directorLoader = new FXMLLoader(getClass().getResource("DirectorDashboard.fxml"));
        directorDashboard = (Parent) directorLoader.load();
        Scene directorScene = new Scene(directorDashboard);

        DirectorDashboardController di = directorLoader.getController();
        di.setDirector(this.director);

        Stage directorStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        directorStage.setScene(directorScene);
        directorStage.show();
    }

    @FXML
    private void clickedPolicy(MouseEvent event) {
        Policy policy = policyTableView.getSelectionModel().getSelectedItem();
        
        tempPolicy = policy;
        System.out.println(tempPolicy.toString());
    }
    
    
}
