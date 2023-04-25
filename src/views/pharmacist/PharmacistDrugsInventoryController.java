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

/**
 * FXML Controller class
 *
 * @author User
 */
public class PharmacistDrugsInventoryController implements Initializable {

    @FXML
    private TableView<?> pharmaDrugsDisplayTableList;
    
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
    private void pharmaDrugsInventoryUpdateOnClick(ActionEvent event) throws IOException {
        Parent pharmaDrugsInvUpList = null;
        FXMLLoader pharmaDrugsInvUpLoader = new FXMLLoader(getClass().getResource("PharmacistDrugsInventoryUpdate.fxml"));
        pharmaDrugsInvUpList = (Parent) pharmaDrugsInvUpLoader.load();
        Scene pharmaDrugsInvUpScene = new Scene(pharmaDrugsInvUpList);

        PharmacistDrugsInventoryUpdateController ph = pharmaDrugsInvUpLoader.getController();
        ph.setPharmacist(this.Pharmacist);

        Stage pharmaDrugsInvUpStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        pharmaDrugsInvUpStage.setScene(pharmaDrugsInvUpScene);
        pharmaDrugsInvUpStage.show();
    }

    @FXML
    private void pharmaDrugsInventoryRestockOnClick(ActionEvent event) throws IOException {
        
        Parent pharmaDrugsInvReSList = null;
        FXMLLoader pharmaDrugsInvReSLoader = new FXMLLoader(getClass().getResource("PharmacistDrugsInventoryRestock.fxml"));
        pharmaDrugsInvReSList = (Parent) pharmaDrugsInvReSLoader.load();
        Scene pharmaDrugsInvReSScene = new Scene(pharmaDrugsInvReSList);

        PharmacistDrugsInventoryRestockController ph = pharmaDrugsInvReSLoader.getController();
        ph.setPharmacist(this.Pharmacist);

        Stage pharmaDrugsInvReSStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        pharmaDrugsInvReSStage.setScene(pharmaDrugsInvReSScene);
        pharmaDrugsInvReSStage.show();
        
    }

    @FXML
    private void pharmaDrugInvtBackToDashOnClick(ActionEvent event) throws IOException {
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
