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
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import users.Pharmacist;
import users.User;

/**
 * FXML Controller class
 *
 * @author User
 */
public class PharmacistViewPatientController implements Initializable {

    @FXML
    private TableView<?> pharmaViewPatientListTable;
    
    private Pharmacist pharmacist;
    private Pharmacist Pharmacist;
    
    

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
    private void pharmaMedOrderPendingOnClick(ActionEvent event) throws IOException {
        Parent pharmaMedsOrderPend = null;
        FXMLLoader pharmaMedsOrderPendLoader = new FXMLLoader(getClass().getResource("PharmacistMedicationsOrderPending.fxml"));
        pharmaMedsOrderPend = (Parent) pharmaMedsOrderPendLoader.load();
        Scene pharmaMedsOrderPendScene = new Scene(pharmaMedsOrderPend);

        PharmacistMedicationsOrderPendingController ph = pharmaMedsOrderPendLoader.getController();
        ph.setPharmacist(this.Pharmacist);

        Stage pharmaMedsOrderPendStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        pharmaMedsOrderPendStage.setScene(pharmaMedsOrderPendScene);
        pharmaMedsOrderPendStage.show();
    }

    @FXML
    private void pharmaViewBillOnClick(ActionEvent event) throws IOException {
        Parent pharmaViewBill = null;
        FXMLLoader pharmaViewBillLoader = new FXMLLoader(getClass().getResource("PharmacistBillingInformation.fxml"));
        pharmaViewBill = (Parent) pharmaViewBillLoader.load();
        Scene pharmaViewBillScene = new Scene(pharmaViewBill);

        PharmacistBillingInformationController ph = pharmaViewBillLoader.getController();
        ph.setPharmacist(this.Pharmacist);

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
