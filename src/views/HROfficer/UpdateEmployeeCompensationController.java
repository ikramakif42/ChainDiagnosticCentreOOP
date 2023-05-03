/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.HROfficer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import users.HROfficer;

/**
 * FXML Controller class
 *
 * @author arafath
 */
public class UpdateEmployeeCompensationController implements Initializable {
    private HROfficer HR;
    @FXML
    private TextField SalaryTextField;
     @FXML
    private TableView<HROfficer> CompensationTableView;
    @FXML
    private TableColumn<HROfficer, Integer> IDTableColumn;
    @FXML
    private TableColumn<HROfficer, String> NameTableColumn;
    @FXML
    private TableColumn<HROfficer, String> DesignationTableColumn;
     
    
    
    Alert salaryNumError = new Alert(Alert.AlertType.WARNING, "Error, enter valid salary!");
    Alert failure = new Alert(Alert.AlertType.WARNING, "Error,Update Employee Compensation failed!");
    Alert failureNull = new Alert(Alert.AlertType.WARNING, "Error, fill up all fields!");
    Alert success = new Alert(Alert.AlertType.INFORMATION, "Update Employee Compensation successful!");

    
    


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
    private void UpdateCompensationOnClick(ActionEvent event) {
         String salaryValue = SalaryTextField.getText();
        if (salaryValue.isEmpty()) {failureNull.show();return;}
        Float salary = 0f;
        try {
            salary = Float.parseFloat(salaryValue);
        } catch (NumberFormatException e) {salaryNumError.show();return;}
        
        SalaryTextField.clear();
        
       
        
    }

   

    @FXML
    private void ConfirmEmployeeCompensionOnClick(ActionEvent event) {
    }

    @FXML
    private void ReturnEmployeeCompestionOnClick(ActionEvent event) throws IOException {
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
