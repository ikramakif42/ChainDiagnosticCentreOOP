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
public class PharmacistCheckSupplyController implements Initializable {

    @FXML
    private TableView<?> pharmaDrugSupplyTableList;

    private Pharmacist pharmacist;
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
    private void pharmaDrugSupplyAcceptOnClick(ActionEvent event) {
    }

    @FXML
    private void pharmaDrugSupplyRejectOnClick(ActionEvent event) {
    }

    @FXML
    private void pharmaCheckSupplyBackToDashOnClick(ActionEvent event) throws IOException {
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
