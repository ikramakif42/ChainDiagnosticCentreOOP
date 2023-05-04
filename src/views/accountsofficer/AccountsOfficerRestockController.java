/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.accountsofficer;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.MedRestock;
import users.AccountsOfficer;

/**
 * FXML Controller class
 *
 * @author Kazi
 */
public class AccountsOfficerRestockController implements Initializable {

    @FXML
    private TableView<MedRestock> medRestockTableView;
    @FXML
    private TableColumn<MedRestock, String> medName;
    @FXML
    private TableColumn<MedRestock, Integer> medQuantity;
    @FXML
    private TableColumn<MedRestock, Boolean> orderStatus;
    private AccountsOfficer officer;
    private MedRestock tempRes;

    public AccountsOfficer getOfficer() {
        return officer;
    }

    public void setOfficer(AccountsOfficer officer) {
        this.officer = officer;
    }
    
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    medName.setCellValueFactory(new PropertyValueFactory<MedRestock, String>("medName"));
    medQuantity.setCellValueFactory(new PropertyValueFactory<MedRestock, Integer>("Quantity"));
    orderStatus.setCellValueFactory(new PropertyValueFactory<MedRestock, Boolean>("ordered"));
    
    medRestockTableView.setItems(AccountsOfficer.viewRestocks());
    }    

    @FXML
    private void markAsOrdered(ActionEvent event) {
        AccountsOfficer.manageRestockOrders(tempRes, true);
        medRestockTableView.setItems(AccountsOfficer.viewRestocks());        
        
    }

    @FXML
    private void returnToDashboardOnClick(ActionEvent event) throws IOException {
        Parent directorDashboard = null;
        FXMLLoader directorLoader = new FXMLLoader(getClass().getResource("AccountsOfficerDashboard.fxml"));
        directorDashboard = (Parent) directorLoader.load();
        Scene directorScene = new Scene(directorDashboard);

        AccountsOfficerDashboardController di = directorLoader.getController();
        di.setOfficer(officer);

        Stage directorStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        directorStage.setScene(directorScene);
        directorStage.show();        
    }

    @FXML
    private void markAsUnordered(ActionEvent event) {
        AccountsOfficer.manageRestockOrders(tempRes, false);
        medRestockTableView.setItems(AccountsOfficer.viewRestocks());        
    }

    @FXML
    private void clickedRestock(MouseEvent event) {
        MedRestock restock = medRestockTableView.getSelectionModel().getSelectedItem();
        tempRes = restock;
    }
    
}
