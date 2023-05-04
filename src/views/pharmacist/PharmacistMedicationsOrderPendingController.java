/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.pharmacist;

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
import javafx.stage.Stage;
import model.Prescription;
import users.Patient;
import users.Pharmacist;

/**
 * FXML Controller class
 *
 * @author User
 */
public class PharmacistMedicationsOrderPendingController implements Initializable {
    Alert noOrder = new Alert(Alert.AlertType.WARNING, "Error, select a refill request to approve or decline!");
    Alert approve = new Alert(Alert.AlertType.INFORMATION, "APPROVED!");
    Alert decline = new Alert(Alert.AlertType.INFORMATION, "DECLINED!");
    @FXML
    private TableView<Prescription> pharmaMedOrderPendingtListTable;
    @FXML
    private Button pharmaMedOrderDeclineAll;
    
    private Pharmacist pharmacist;
    private Pharmacist Pharmacist;
    @FXML
    private TableColumn<Prescription, String> pharmaMedOrderPendPatientNameTableView;
    @FXML
    private TableColumn<Prescription,Integer> pharmaMedOrderPendPatientIDTableView;
    @FXML
    private TableColumn<Prescription,String> pharmaMedOrderPendPatientContactTableView;
    @FXML
    private TableColumn<Prescription,String> pharmaMedOrderPendAddressTableView;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
        // TODO
    }    

    public Pharmacist getPharmacist() {
        return pharmacist;
    }

    public void setPharmacist(Pharmacist pharmacist) {
        this.pharmacist = pharmacist;
    }

    @FXML
    private void pharmaMedOrderApproveSelectedOnClick(ActionEvent event) {
        Prescription selectedOrder = pharmaMedOrderPendingtListTable.getSelectionModel().getSelectedItem();
        if (selectedOrder == null){noOrder.show();
        return;}
        Pharmacist.resolveRefillRequest(selectedOrder);
        approve.show();
        
    }

    @FXML
    private void pharmaMedOrderDeclineSelectedOnClick(ActionEvent event) {
        Prescription selectedOrder = pharmaMedOrderPendingtListTable.getSelectionModel().getSelectedItem();
        if (selectedOrder == null){noOrder.show();
        return;}
        Pharmacist.resolveRefillRequest(selectedOrder);
        decline.show();
    }

    @FXML
    private void pharmaMedOrderPendingBackToPatientOnClick(ActionEvent event) throws IOException {
        Parent pharmaViewPatientList = null;
        FXMLLoader pharmaLoader = new FXMLLoader(getClass().getResource("PharmacistViewPatient.fxml"));
        pharmaViewPatientList = (Parent) pharmaLoader.load();
        Scene pharmaViewPatientListScene = new Scene(pharmaViewPatientList);

        PharmacistViewPatientController ph = pharmaLoader.getController();
        ph.setPharmacist(this.Pharmacist);

        Stage pharmaStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        pharmaStage.setScene(pharmaViewPatientListScene);
        pharmaStage.show();
    }
    
}
