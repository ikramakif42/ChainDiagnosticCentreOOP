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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import users.HROfficer;
import users.User;

/**
 * FXML Controller class
 *
 * @author arafath
 */
public class CreateReportsOnEmployeesController implements Initializable {
    
    private HROfficer HR;
    @FXML
    private TableView<HROfficer> EmployeeTableView;
    @FXML
    private TextField EnterTitleTextField;  
    @FXML
    private TableColumn<HROfficer, String> NameTableColumn;
    @FXML
    private TableColumn<HROfficer, String> DesignationTableColumn; 
    @FXML
    private TableColumn<HROfficer, Integer> IDTableColumn;
    @FXML
    private TextArea EnterDetalisTextArea;
    
    Alert idNumError = new Alert(Alert.AlertType.WARNING, "Error, ID must be a number only, less than 5 digits!");
    Alert emailError = new Alert(Alert.AlertType.WARNING, "Error, valid email address!");
    Alert failure = new Alert(Alert.AlertType.WARNING, "Error, Create Report failed!");
    Alert failureNull = new Alert(Alert.AlertType.WARNING, "Error, fill up all fields!");
    Alert success = new Alert(Alert.AlertType.INFORMATION, "Create Report On Employee successful!");


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
    

    private void CreateReportsOnClick(ActionEvent event) {
        
        String id = IDTableColumn.getText();
        if (id.isEmpty()) {failureNull.show();return;}
        if (!User.isNumeric(id) || id.length()>=5) {idNumError.show();return;}
       
        
        String name = NameTableColumn.getText();
        if (name.isEmpty()) {failureNull.show();return;}
        
        String designation = DesignationTableColumn.getText();
        if (designation.isEmpty()) {failureNull.show();return;}
    }

    @FXML
    private void ReturnCreateOnEmployee(ActionEvent event) throws IOException {
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
    
     @FXML
    private void SubmitCreateReportOnClick(ActionEvent event) {
    }

    @FXML
    private void SelectEmployeeOnClick(ActionEvent event) {
    }

    @FXML
    private void ReportTypeComboBox(ActionEvent event) {
    }
    
}
    
    
