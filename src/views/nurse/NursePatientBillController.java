/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.nurse;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Bill;
import model.Task;
import users.Nurse;

/**
 * FXML Controller class
 *
 * @author User
 */
public class NursePatientBillController implements Initializable {

    @FXML
    private TableView<Bill> nursePatientBillTableList;
    
    private Nurse nurse;
    @FXML
    private DatePicker nurseFilterByDateStart;
    @FXML
    private DatePicker nurseFilterByDateEnd;
    @FXML
    private RadioButton nurseFilterByPaid;
    @FXML
    private ToggleGroup filterByPaid;
    @FXML
    private RadioButton nurseFilterByDue;
    @FXML
    private TableColumn<Bill, LocalDate> nursePatientBillDateOfBillTableView;
    @FXML
    private TableColumn<Bill, LocalDate> nursePatientDueDateOfBillTableView;
    @FXML
    private TableColumn<Bill, Boolean> nursePatientBillPaidStatusTableView;
    @FXML
    private TableColumn<Bill, String> nursePatientBillDetailsTableView;
    @FXML
    private TableColumn<Bill, Float> nursePatientBillAmountTableView;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        nursePatientBillDateOfBillTableView.setCellValueFactory(new PropertyValueFactory<Bill,LocalDate>("createdDate"));
        nursePatientDueDateOfBillTableView.setCellValueFactory(new PropertyValueFactory<Bill,LocalDate>("dueDate"));
        nursePatientBillPaidStatusTableView.setCellValueFactory(new PropertyValueFactory<Bill,Boolean>("paidStatus"));
        nursePatientBillAmountTableView.setCellValueFactory(new PropertyValueFactory<Bill,Float>("amount"));
        nursePatientBillDetailsTableView.setCellValueFactory(new PropertyValueFactory<Bill,String>("details"));
        // TODO
        nursePatientBillTableList.setItems(Bill.getAllBills());
    }    

    public Nurse getNurse() {
        return nurse;
    }

    public void setNurse(Nurse nurse) {
        this.nurse = nurse;
       
    }
    
    @FXML
    private void nursePatientBillBack(ActionEvent event) throws IOException {
        Parent nurseViewPatientList = null;
        FXMLLoader nurseLoader = new FXMLLoader(getClass().getResource("NursePatientList.fxml"));
        nurseViewPatientList = (Parent) nurseLoader.load();
        Scene nurseViewPatientListScene = new Scene(nurseViewPatientList);

        NursePatientListController n = nurseLoader.getController();
        n.setNurse(this.nurse);

        Stage nurseStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        nurseStage.setScene(nurseViewPatientListScene);
        nurseStage.show();
        
    }

    @FXML
    private void nurseApplyFilter(ActionEvent event) {
    }
    
}
