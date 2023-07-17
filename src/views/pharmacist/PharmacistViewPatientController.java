/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.pharmacist;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import users.Patient;
import users.Pharmacist;
import users.User;

/**
 * FXML Controller class
 *
 * @author User
 */
public class PharmacistViewPatientController implements Initializable {

    @FXML
    private TableView<Patient> pharmaViewPatientListTable;
    
    private Pharmacist pharmacist;
//    private Pharmacist Pharmacist;
    @FXML
    private TableColumn<Patient,String> pharmaViewPatientPatientNameTableView;
    @FXML
    private TableColumn<Patient,Integer> pharmaViewPatientPatientIDTableView;
    @FXML
    private TableColumn<Patient,Integer> pharmaViewPatientPatientAgeTableView;
    @FXML
    private TableColumn<Patient,String> pharmaViewPatientPatientContactTableView;
    
    Alert noPatient = new Alert(Alert.AlertType.WARNING, "Error, select a patient from table first!");

    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Callback<TableColumn.CellDataFeatures<Patient, Integer>, ObservableValue<Integer>> ageCVF = feature -> {
            Patient pat = feature.getValue();
            LocalDate birthdate = pat.getDOB();
            int age = Period.between(birthdate, LocalDate.now()).getYears();
            return new SimpleObjectProperty<Integer>(age);
        
        // TODO
    };    
        pharmaViewPatientPatientIDTableView.setCellValueFactory(new PropertyValueFactory<Patient,Integer>("ID"));
        pharmaViewPatientPatientNameTableView.setCellValueFactory(new PropertyValueFactory<Patient,String>("name"));
        pharmaViewPatientPatientAgeTableView.setCellValueFactory(ageCVF);
        pharmaViewPatientPatientContactTableView.setCellValueFactory(new PropertyValueFactory<Patient,String>("contactNo"));
        pharmaViewPatientListTable.setItems(Patient.getPatients());
    }
   
    public Pharmacist getPharmacist() {
        return pharmacist;
    }

    public void setPharmacist(Pharmacist pharmacist) {
        this.pharmacist = pharmacist;
    }

    @FXML
    private void pharmaMedOrderPendingOnClick(ActionEvent event) throws IOException {
        Parent pharmaMedsOrderPend = null;
        FXMLLoader pharmaMedsOrderPendLoader = new FXMLLoader(getClass().getResource("PharmacistMedicationsOrderPending.fxml"));
        pharmaMedsOrderPend = (Parent) pharmaMedsOrderPendLoader.load();
        Scene pharmaMedsOrderPendScene = new Scene(pharmaMedsOrderPend);

        PharmacistMedicationsOrderPendingController ph = pharmaMedsOrderPendLoader.getController();
        ph.setPharmacist(this.pharmacist);

        Stage pharmaMedsOrderPendStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        pharmaMedsOrderPendStage.setScene(pharmaMedsOrderPendScene);
        pharmaMedsOrderPendStage.show();
    }

    @FXML
    private void pharmaViewBillOnClick(ActionEvent event) throws IOException {
        Patient selectedPatient = pharmaViewPatientListTable.getSelectionModel().getSelectedItem();
        if (selectedPatient == null){noPatient.show();
        return;
        }
        Parent pharmaViewBill = null;
        FXMLLoader pharmaViewBillLoader = new FXMLLoader(getClass().getResource("PharmacistBillingInformation.fxml"));
        pharmaViewBill = (Parent) pharmaViewBillLoader.load();
        Scene pharmaViewBillScene = new Scene(pharmaViewBill);

        PharmacistBillingInformationController ph = pharmaViewBillLoader.getController();
        ph.setPharmacist(this.pharmacist);
        ph.setSelectedPatient(selectedPatient);

        Stage pharmaViewBillStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        pharmaViewBillStage.setScene(pharmaViewBillScene);
        pharmaViewBillStage.show();
    }

    @FXML
    private void pharmaGoBackToDashOnClick(ActionEvent event) throws IOException {
                Parent pharmacistDashboard = null;
                FXMLLoader pharmaLoader = new FXMLLoader(getClass().getResource("PharmacistDashboard.fxml"));
                
                pharmacistDashboard = (Parent) pharmaLoader.load();
                Scene pharmaScene = new Scene(pharmacistDashboard);
                
                PharmacistDashboardController ph = pharmaLoader.getController();
                ph.setPharmacist(this.pharmacist);
                
                Stage pharmaStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
                pharmaStage.setScene(pharmaScene);
                pharmaStage.show();
    }
    
}
