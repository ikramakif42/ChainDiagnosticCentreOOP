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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import model.TeleQuery;
import users.Patient;

public class ViewQueryDetailsController implements Initializable {

    @FXML
    private TextArea queryTextArea;
    @FXML
    private TextArea answerTextArea;
    @FXML
    private Label usertypeLabel;
    private Patient patient;
    private TeleQuery telequery;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public TeleQuery getTelequery() {
        return telequery;
    }

    public void setTelequery(TeleQuery telequery) {
        this.telequery = telequery;
        usertypeLabel.setText(telequery.usertype);
        queryTextArea.setText(telequery.getQuery());
        if (telequery.isPending()){answerTextArea.setText("No response yet.");}
        else {answerTextArea.setText(telequery.getAnswer());}
        queryTextArea.setEditable(false);
        answerTextArea.setEditable(false);
    }
    
    @FXML
    private void returnToTelequeryOnClick(ActionEvent event) throws IOException {
        Parent root = null;
        FXMLLoader queryLoader = new FXMLLoader(getClass().getResource("ViewQuery.fxml"));
        ViewQueryController q = new ViewQueryController(this.patient);
        queryLoader.setController(q);
        root = (Parent) queryLoader.load();

        Scene queryScene = new Scene(root);
        Stage queryStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        queryStage.setScene(queryScene);
        queryStage.show();
    }
    
}
