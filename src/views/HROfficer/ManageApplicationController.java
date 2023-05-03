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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import users.HROfficer;

/**
 * FXML Controller class
 *
 * @author arafath
 */
public class ManageApplicationController implements Initializable {

    private HROfficer HR;
    @FXML
    private TableView<HROfficer> DateOfLeaveTableView;
    @FXML
    private TableColumn<HROfficer, String> EmployeeNameTableColumn;
    @FXML
    private TableColumn<HROfficer, Integer> DateofLeaveTableColumn;
    @FXML
    private TextArea InfoTextAreaTextArea;
    
    Alert failure = new Alert(Alert.AlertType.WARNING, "Error,Update Manage Application failed!");
    Alert failureNull = new Alert(Alert.AlertType.WARNING, "Error, fill up all fields!");
    Alert success = new Alert(Alert.AlertType.INFORMATION, "Update Manage Application successful!");


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
    private void ViewApplicationOnClick(ActionEvent event) {
        
         String name = EmployeeNameTableColumn.getText();
        if (name.isEmpty()) {failureNull.show();return;}
        
    }

    @FXML
    private void RejectaaplicationOnClick(ActionEvent event) {
    }

    @FXML
    private void ApproveOnClick(ActionEvent event) {
    }

    @FXML
    private void ReturnManageApplicationOnClick(ActionEvent event) throws IOException {
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
