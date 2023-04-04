package views.doctor;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class AssignTaskController implements Initializable {

    @FXML
    private ComboBox<String> userTypeComboBox;
    @FXML
    private ComboBox<String> userNameComboBox;
    @FXML
    private TextArea taskTextArea;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String[] temp = {"Nurse", "Technician", "Pharmacist"};
        userTypeComboBox.getItems().addAll();
    }

    @FXML
    private void returnToDashboardOnClick(ActionEvent event) {
    }

    @FXML
    private void assignTaskOnClick(ActionEvent event) {
    }
    
}
