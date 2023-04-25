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
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import users.Pharmacist;

/**
 * FXML Controller class
 *
 * @author User
 */
public class PharmacistMedicationsOrderPendingController implements Initializable {

    @FXML
    private TableView<?> pharmaMedOrderPendingtListTable;
    @FXML
    private Button pharmaMedOrderDeclineAll;
    
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
    private void pharmaMedOrderApproveSelectedOnClick(ActionEvent event) {
    }

    @FXML
    private void pharmaMedOrderDeclineSelectedOnClick(ActionEvent event) {
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
