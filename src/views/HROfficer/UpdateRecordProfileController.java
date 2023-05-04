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
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.stage.Stage;
import users.HROfficer;
import users.User;



/**
 * FXML Controller class
 *
 * @author arafath
 */
public class UpdateRecordProfileController implements Initializable {
    
    private HROfficer HR;
    @FXML
    private TextField EmailTextField;
    private TextField ContractTextField;
    @FXML
    private TextField AddressTextField;
    private TextField DesignationTextField;
    @FXML
    private TextField DepartmentTextField;
    @FXML
    private TextField BranchNameTextField;
    @FXML
    private TreeTableView<HROfficer> RecordProfileTreeTableView;
    @FXML
    private TreeTableColumn<?, ?> EmployeeIDTableColumn;
    @FXML
    private TreeTableColumn<?, ?> NameTableColumn;
    @FXML
    private TreeTableColumn<?, ?> DesiganationTableColumn;
    @FXML
    private TreeTableColumn<?, ?> DepartmentTableColumn;
    
    
    Alert emailError = new Alert(Alert.AlertType.WARNING, "Error, valid email address!");
    Alert failure = new Alert(Alert.AlertType.WARNING, "Error, Update Profile failed!");
    Alert failureNull = new Alert(Alert.AlertType.WARNING, "Error, fill up all fields!");
    Alert success = new Alert(Alert.AlertType.INFORMATION, "Update Record Profile successful!");
    @FXML
    private TextField ContractNumberTextField;
    @FXML
    private TextField DesiganationTextField;
    


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
    private void UpdateRecordOnClick(ActionEvent event) {
        
          
        
       
    }
    

    @FXML
    private void SaveupdaterecordOnClick(ActionEvent event) {
        
        String email = EmailTextField.getText();
        if (email.isEmpty()) {failureNull.show();return;}
        if (!email.contains("@")){emailError.show();return;}
        
        String contact = ContractTextField.getText();
        if (contact.isEmpty()) {failureNull.show();return;}
        
        String address = AddressTextField.getText();
        if (address.isEmpty()) {failureNull.show();return;}
        
        String designation = DesignationTextField.getText();
        if (designation.isEmpty()) {failureNull.show();return;}
        
        String department = DepartmentTextField.getText();
        if (designation.isEmpty()) {failureNull.show();return;}
        
        String branchName = BranchNameTextField.getText();
        if (branchName.isEmpty()) {failureNull.show();return;}
    }

    @FXML
    private void ReturnUpdateEmployeeRecordOnClick(ActionEvent event) throws IOException {
        
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
