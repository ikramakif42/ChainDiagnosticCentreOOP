/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.HROfficer;

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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Policy;
import users.HROfficer;
import users.User;

/**
 * FXML Controller class
 *
 * @author arafath
 */
public class UpdateHospitalPoliciesProceduresController implements Initializable {

    private HROfficer HR;
    private Policy selectedPolicy;
    @FXML
    private TextArea detailsTextArea;
    @FXML
    private TableView<Policy> policyTableView;
    @FXML
    private TableColumn<Policy, Integer> numberTableColumn;
    @FXML
    private TableColumn<Policy, String> detailsTableColumn;
    
    Alert SerialNoError = new Alert(Alert.AlertType.WARNING, "Error, enter valid SerialNo!");
    Alert failure = new Alert(Alert.AlertType.WARNING, "Error,Update Policies failed!");
    Alert failureNull = new Alert(Alert.AlertType.WARNING, "Error, fill up all fields!");
    Alert success = new Alert(Alert.AlertType.INFORMATION, "Update Hospital Policies successful!");


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        numberTableColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        detailsTableColumn.setCellValueFactory(new PropertyValueFactory<>("content"));
        policyTableView.setItems(User.viewPolicies());
    }
    
    
    public HROfficer getHR() {
        return HR;
    }

    public void setHR(HROfficer HR) {
        this.HR = HR;
    }
    
    
    @FXML
    private void LoadUpdatePolicesButtonOnClick(ActionEvent event) throws IOException {
        
        
        
        
        Policy selectedPolicy = policyTableView.getSelectionModel().getSelectedItem();
        
        
        if (selectedPolicy==null){
             {failureNull.show();return;}
        }
        detailsTextArea.setText(selectedPolicy.getContent());
            
        
        
    }

    public Policy getSelectedPolicy() {
        return selectedPolicy;
    }

    public void setSelectedPolicy(Policy selectedPolicy) {
        this.selectedPolicy = selectedPolicy;
    }
    

    

    @FXML
    private void ConfirmButtonOnClick(ActionEvent event) throws IOException {
        String newDetalis = detailsTextArea.getText();
        
        if (selectedPolicy==null)
        {return;}
        selectedPolicy.setContent(newDetalis);
        if(this.HR.UpdatePolicies(selectedPolicy)){success.show();}
        else{failure.show();}
    }

    @FXML
    private void ReturnButtonOnClick(ActionEvent event) throws IOException {
        Parent parent = null;
        FXMLLoader HRLoader = new FXMLLoader(getClass().getResource("HRDashboard.fxml"));
        parent = (Parent) HRLoader.load();
        Scene HRScene = new Scene(parent);
        
        HRDashboardController m = HRLoader.getController();
        m.setHR(this.HR);

        Stage HRStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        HRStage.setScene(HRScene);
        HRStage.show();
    }
    
}
