/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.pharmacist;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import users.Pharmacist;
import views.doctor.DoctorDashboardController;

/**
 * FXML Controller class
 *
 * @author User
 */
public class PharmacistDashboardController implements Initializable {
    private Pharmacist pharmacist;
    private Pharmacist Pharmacist;

    public Pharmacist getPharmacist() {
        return pharmacist;
    }

    public void setPharmacist(Pharmacist pharmacist) {
        this.pharmacist = pharmacist;
    }
    
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void pharmaLogOutOnClick(ActionEvent event) {
        Parent login=null;
        try {
            login = FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(DoctorDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene1 = new Scene(login);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene1);
        window.show();
    }

    @FXML
    private void pharmaViewPatientListOnClick(ActionEvent event) throws IOException {
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

    @FXML
    private void pharmaViewPendingConsutlationsListOnClick(ActionEvent event) throws IOException {
        Parent pharmaViewPendCons = null;
        FXMLLoader pharmaViewPendConsLoader = new FXMLLoader(getClass().getResource("PharmacistPendingConsultation.fxml"));
        pharmaViewPendCons = (Parent) pharmaViewPendConsLoader.load();
        Scene pharmaViewPendConsScene = new Scene(pharmaViewPendCons);

        PharmacistPendingConsultationController ph = pharmaViewPendConsLoader.getController();
        ph.setPharmacist(this.Pharmacist);

        Stage pharmaViewPendConsStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        pharmaViewPendConsStage.setScene(pharmaViewPendConsScene);
        pharmaViewPendConsStage.show();
    }

    @FXML
    private void pharmaDrugsInventoryOnClick(ActionEvent event) throws IOException {
        Parent pharmaDrugsInv = null;
        FXMLLoader pharmaDrugsInvLoader = new FXMLLoader(getClass().getResource("PharmacistDrugsInventory.fxml"));
        pharmaDrugsInv = (Parent) pharmaDrugsInvLoader.load();
        Scene pharmaDrugsInvScene = new Scene(pharmaDrugsInv);

        PharmacistDrugsInventoryController ph = pharmaDrugsInvLoader.getController();
        ph.setPharmacist(this.Pharmacist);

        Stage pharmaDrugsInvStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        pharmaDrugsInvStage.setScene(pharmaDrugsInvScene);
        pharmaDrugsInvStage.show();
    }

    @FXML
    private void pharmaCheckSupplyOptionOnClick(ActionEvent event) throws IOException {
        Parent pharmaCheckSupp = null;
        FXMLLoader pharmaCheckSuppLoader = new FXMLLoader(getClass().getResource("PharmacistCheckSupply.fxml"));
        pharmaCheckSupp = (Parent) pharmaCheckSuppLoader.load();
        Scene pharmaCheckSuppsScene = new Scene(pharmaCheckSupp);

        PharmacistCheckSupplyController ph = pharmaCheckSuppLoader.getController();
        ph.setPharmacist(this.Pharmacist);

        Stage pharmaCheckSuppsStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        pharmaCheckSuppsStage.setScene(pharmaCheckSuppsScene);
        pharmaCheckSuppsStage.show();
    }

    @FXML
    private void pharmaMedicationsAlertOnClick(ActionEvent event) throws IOException {
        Parent pharmaMedsAlert = null;
        FXMLLoader pharmaMedsAlertLoader = new FXMLLoader(getClass().getResource("PharmacistMedicationsAlert.fxml"));
        pharmaMedsAlert = (Parent) pharmaMedsAlertLoader.load();
        Scene pharmaMedsAlertScene = new Scene(pharmaMedsAlert);

        PharmacistMedicationsAlertController ph = pharmaMedsAlertLoader.getController();
        ph.setPharmacist(this.Pharmacist);

        Stage pharmaMedsAlertStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        pharmaMedsAlertStage.setScene(pharmaMedsAlertScene);
        pharmaMedsAlertStage.show();
    }
    
}
