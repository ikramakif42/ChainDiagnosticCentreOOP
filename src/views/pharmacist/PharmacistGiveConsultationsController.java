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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import users.Pharmacist;

/**
 * FXML Controller class
 *
 * @author User
 */
public class PharmacistGiveConsultationsController implements Initializable {

    @FXML
    private TextArea pharmaGiveConsultationTextArea;
    @FXML
    private Label pharmaQueries;
    
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
    private void pharmaGiveConsultationsGoBackToPendingConsultOnClick(ActionEvent event) throws IOException {
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
    
}
