package views.doctor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.TeleQuery;
import users.Doctor;
import users.Patient;
import users.User;

public class ViewTelequeryListController implements Initializable {
    @FXML
    private Label errorLabel;
    @FXML
    private TableView<TeleQuery> queryListTableView;
    @FXML
    private TableColumn<TeleQuery, Integer> patientIDTableColumn;
    @FXML
    private TableColumn<TeleQuery, String> patientNameTableColumn;
    @FXML
    private TableColumn<TeleQuery, Integer> patientAgeTableColumn;
    @FXML
    private TableColumn<TeleQuery, String> statusTableColumn;
    @FXML
    private RadioButton pendingStatusRadioButton;
    @FXML
    private RadioButton noneStatusRadioButton;
    @FXML
    private RadioButton answeredStatusRadioButton;
    @FXML
    private ToggleGroup queryStatusTG;
    private Doctor doc;
//    private ObservableList<TeleQuery> TQL = FXCollections.observableArrayList();
    private FilteredList<TeleQuery> filterTQL;

    ViewTelequeryListController(Doctor doc) {
        this.doc = doc;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Loaded "+this.doc.toString());
        
        Callback<TableColumn.CellDataFeatures<TeleQuery, String>, ObservableValue<String>> nameCVF = feature -> {
            TeleQuery tq = feature.getValue();
            String name = ((Patient)User.getInstance(tq.getSenderID(), "Patient")).getName();
            return new SimpleStringProperty(name);
        };
        
        Callback<TableColumn.CellDataFeatures<TeleQuery, Integer>, ObservableValue<Integer>> ageCVF = feature -> {
            TeleQuery tq = feature.getValue();
            LocalDate tempDOB = ((Patient) User.getInstance(tq.getSenderID(), "Patient")).getDOB();
            int age = Period.between(tempDOB, LocalDate.now()).getYears();
            return new SimpleObjectProperty<>(age);
        };
        
        Callback<TableColumn.CellDataFeatures<TeleQuery, String>, ObservableValue<String>> statusCVF = feature -> {
            TeleQuery tq = feature.getValue();
            String status = "";
            if(tq.isPending()){status = "Pending";}
            else{status = "Answered";}
            return new SimpleStringProperty(status);
        };
        
        patientIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("senderID"));
        patientNameTableColumn.setCellValueFactory(nameCVF);
        patientAgeTableColumn.setCellValueFactory(ageCVF);
        statusTableColumn.setCellValueFactory(statusCVF);
        
        ObservableList<TeleQuery> TQL = TeleQuery.getQueryList("Doctor");
        this.filterTQL = new FilteredList<>(TQL, b -> true);
        
        queryListTableView.setItems(filterTQL);
        noneStatusRadioButton.setSelected(true);
    }

    public Doctor getDoc() {
        return doc;
    }

    public void setDoc(Doctor doc) {
        this.doc = doc;
    }
    
    @FXML
    private void returnToDashboardOnClick(ActionEvent event) throws IOException {
        Parent doctorDashboard = null;
        FXMLLoader doctorLoader = new FXMLLoader(getClass().getResource("DoctorDashboard.fxml"));
        doctorDashboard = (Parent) doctorLoader.load();
        Scene doctorScene = new Scene(doctorDashboard);

        DoctorDashboardController d = doctorLoader.getController();
        d.setDoc(this.doc);

        Stage doctorStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        doctorStage.setScene(doctorScene);
        doctorStage.show();
    }


    @FXML
    private void applyFiltersOnClick(ActionEvent event) {
        if (pendingStatusRadioButton.isSelected()){
            filterTQL.setPredicate(tempTQ ->{
                if (tempTQ.isPending()){
                    return true;
                } else {return false;}
            }
            );
        }
        else if (answeredStatusRadioButton.isSelected()){
            filterTQL.setPredicate(tempTQ ->{
                if (!tempTQ.isPending()){
                    return true;
                } else {return false;}
            }
            );
        }
        else{
            filterTQL.setPredicate(tempTQ -> true);
        }
    }

    @FXML
    private void answerQueryOnClick(ActionEvent event) throws IOException {
        TeleQuery selectedTQ = queryListTableView.getSelectionModel().getSelectedItem();
        System.out.println(selectedTQ.toString());
        
        Parent answerQuery=null;
        FXMLLoader answerLoader = new FXMLLoader(getClass().getResource("TelequeryResponse.fxml"));
        answerQuery = (Parent) answerLoader.load();
        Scene answerScene = new Scene(answerQuery);
        
        TelequeryResponseController tr = answerLoader.getController();
        tr.setDoc(this.doc);
        tr.setTQ(selectedTQ);
        
        Stage answerStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        answerStage.setScene(answerScene);
        answerStage.show();
    }
    
}
