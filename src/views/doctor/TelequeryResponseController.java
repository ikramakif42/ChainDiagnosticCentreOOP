package views.doctor;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
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
import users.Doctor;
import users.Patient;
import users.User;

public class TelequeryResponseController implements Initializable {

    @FXML
    private Label patientIDLabel;
    @FXML
    private Label patientNameLabel;
    @FXML
    private Label patientAgeLabel;
    @FXML
    private TextArea queryTextArea;
    @FXML
    private TextArea answerTextArea;
    @FXML
    private Label errorLabel;
    private Doctor doc;
    private TeleQuery TQ;
    private boolean answerFlag;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public Doctor getDoc() {
        return doc;
    }

    public void setDoc(Doctor doc) {
        this.doc = doc;
    }

    public TeleQuery getTQ() {
        return TQ;
    }

    public void setTQ(TeleQuery TQ) {
        this.TQ = TQ;
        Patient pat = (Patient) User.getInstance(TQ.getSenderID(), "Patient");
        patientIDLabel.setText(String.valueOf(pat.getID()));
        patientNameLabel.setText(pat.getName());
        int age = Period.between(pat.getDOB(), LocalDate.now()).getYears();
        patientAgeLabel.setText(String.valueOf(age));
        queryTextArea.setText(TQ.getQuery());
        queryTextArea.setEditable(false);
        if (!TQ.isPending()){
            answerTextArea.setText(TQ.getAnswer());
            answerTextArea.setEditable(false);
            this.answerFlag = true;
        }
    }
    
    @FXML
    private void returnToTelequeryOnClick(ActionEvent event) throws IOException {
        Parent root = null;
        FXMLLoader queryLoader = new FXMLLoader(getClass().getResource("ViewTelequeryList.fxml"));
        ViewTelequeryListController q = new ViewTelequeryListController(this.doc);
        queryLoader.setController(q);
        root = (Parent) queryLoader.load();

        Scene queryScene = new Scene(root);
        Stage queryStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        queryStage.setScene(queryScene);
        queryStage.show();
    }

    @FXML
    private void submitAnswerOnClick(ActionEvent event) {
        String answer = answerTextArea.getText();
        if (answer.isEmpty()){errorLabel.setText("Error, answer query!");return;}
        else if (this.answerFlag){errorLabel.setText("Error, already answered!");return;}
        this.TQ.setAnswer(answer);
        this.TQ.setAnswerID(this.doc.getID());
        System.out.println("Writing answer...");
        if (this.doc.answerQuery(TQ)){
            errorLabel.setText("Query answered successfully!");
            this.answerFlag = true;
        }
        else{
            errorLabel.setText("Error, please try again!");
        }
    }
    
}
