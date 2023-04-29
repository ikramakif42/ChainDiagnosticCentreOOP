package views.patient;

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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import model.Bill;
import users.Patient;
import users.User;


public class ConfirmPayBillController implements Initializable {

    @FXML
    private PasswordField passwordField;
    private Patient patient;
    private Bill toPay;
    Alert wrongPW = new Alert(AlertType.WARNING, "Error, wrong password!");
    Alert paid = new Alert(AlertType.INFORMATION, "Pay bill successful!");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Bill getToPay() {
        return toPay;
    }

    public void setToPay(Bill toPay) {
        this.toPay = toPay;
    }

    @FXML
    private void confirmPaymentOnClick(ActionEvent event) {
        String pw = passwordField.getText();
        if (User.userLogin(this.patient.getID(), pw)==4){
            this.toPay.setPaidStatus(true);
            paid.show();
        }
        else {wrongPW.show();}
    }

    @FXML
    private void returnToMyBillsOnClick(ActionEvent event) throws IOException {
        Parent bill = null;
        FXMLLoader billLoader = new FXMLLoader(getClass().getResource("ViewPayBill.fxml"));
        bill = (Parent) billLoader.load();
        Scene billScene = new Scene(bill);

        ViewPayBillController q = billLoader.getController();
        q.setPatient(this.patient);

        Stage billStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        billStage.setScene(billScene);
        billStage.show();
    }
    
}
