/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.nurse;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Bill;
import model.Task;
import users.Nurse;
import static users.Nurse.getPatientBills;
import users.Patient;
import users.Pharmacist;

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
    private Patient selectedPatient;
    @FXML
    private Label nurseBillInfoPatientIdLabel;
    @FXML
    private Label nurseBillInfoPatientNameLabel;
    @FXML
    private Label nurseBillInfoPatientAgeLabel;
    
    Alert dateError = new Alert(Alert.AlertType.WARNING, "Error, no date is Selected!");
    Alert paymentError = new Alert(Alert.AlertType.WARNING, "Error, no payment status is Selected!");
    
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
        
        System.out.println(Bill.getAllPendingBills());
    }    
    public Patient getSelectedPatient() {
        return selectedPatient;
    }

    public void setSelectedPatient(Patient selectedPatient) {
        this.selectedPatient = selectedPatient;
        nurseBillInfoPatientIdLabel.setText(String.valueOf(selectedPatient.getID()));
        nurseBillInfoPatientNameLabel.setText(selectedPatient.getName());
        int age = Period.between(selectedPatient.getDOB(), LocalDate.now()).getYears();

        nurseBillInfoPatientAgeLabel.setText(String.valueOf(age));
        nursePatientBillTableList.setItems(Nurse.getPatientBills(selectedPatient.getID()));
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
    private void nurseApplyDateFilter(ActionEvent event) {
        ObservableList<Bill> nurseApplyDateList = Nurse.getPatientBills(selectedPatient.getID());
        ObservableList<Bill> newList = FXCollections.observableArrayList();
        LocalDate startDate = nurseFilterByDateStart.getValue();
        LocalDate endDate = nurseFilterByDateEnd.getValue();
        if (startDate==null || endDate == null){dateError.show();return;} 
        for (Bill tempBill : nurseApplyDateList) {
          if (tempBill.getCreatedDate().isAfter(startDate) && tempBill.getCreatedDate().isBefore(endDate)){
            newList.add(tempBill);
            }
          }
          nursePatientBillTableList.setItems(newList);

    }

    @FXML
    private void nurseApplyPaymentFilter(ActionEvent event) {
        ObservableList<Bill> nurseApplyDateList = Nurse.getPatientBills(selectedPatient.getID());
        ObservableList<Bill> newList = FXCollections.observableArrayList();
        LocalDate startDate = nurseFilterByDateStart.getValue();
        LocalDate endDate = nurseFilterByDateEnd.getValue();
        boolean paidStatus;
        if (nurseFilterByPaid.isSelected()){
            paidStatus= true;
            
        
        }
        else if(nurseFilterByDue.isSelected()){
            paidStatus= false;
        }
        else{
            paymentError.show();
            return;
        }
        for (Bill tempBill : nurseApplyDateList) {
          if (tempBill.isPaidStatus()== paidStatus){
            newList.add(tempBill);
            }
          }
          nursePatientBillTableList.setItems(newList);

    }

    @FXML
    private void nurseBillInfoClearFilter(ActionEvent event) {
        nursePatientBillTableList.setItems(Nurse.getPatientBills(selectedPatient.getID()));
    }
}
