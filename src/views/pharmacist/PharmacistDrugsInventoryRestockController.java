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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import users.Pharmacist;

/**
 * FXML Controller class
 *
 * @author User
 */
public class PharmacistDrugsInventoryRestockController implements Initializable {

    @FXML
    private TextField pharmaDrugNameRestock;
    @FXML
    private TextField pharmaDrugQuantityRestock;
    
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
    private void pharmaDrugRestockPlaceOrderOnCLick(ActionEvent event) {
    }

    @FXML
    private void pharmaDrugRestockBackToDrugInvtOnClick(ActionEvent event) throws IOException {
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
    
}
