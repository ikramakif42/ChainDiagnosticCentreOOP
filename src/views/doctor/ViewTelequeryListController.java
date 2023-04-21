package views.doctor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private ToggleGroup queryStatusTG;
    @FXML
    private RadioButton resolvedStatusRadioButton;
    @FXML
    private RadioButton noneStatusRadioButton;
    private Doctor doc;

    ViewTelequeryListController(Doctor doc) {
        this.doc = doc;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Loaded "+this.doc.toString());
        
        Callback<TableColumn.CellDataFeatures<TeleQuery, String>, ObservableValue<String>> nameCVF = feature -> {
            TeleQuery tq = feature.getValue();
            String status = "";
            if(tq.isPending()){
                status = "Pending";
            }
            else{
                status = "Answered";
            }
            StringProperty statusProperty = new SimpleStringProperty(status);
            return statusProperty;
        };
        
        Callback<TableColumn.CellDataFeatures<TeleQuery, Integer>, ObservableValue<Integer>> ageCVF = feature -> {
            TeleQuery tq = feature.getValue();
            Patient pat = (Patient) User.getInstance(tq.getSenderID(), "Patient");
            LocalDate birthdate = pat.getDOB();
            int age = Period.between(birthdate, LocalDate.now()).getYears();
            return new SimpleObjectProperty<>(age);
        };
        
        Callback<TableColumn.CellDataFeatures<TeleQuery, String>, ObservableValue<String>> statusCVF = feature -> {
            TeleQuery tq = feature.getValue();
            String status = "";
            if(tq.isPending()){
                status = "Pending";
            }
            else{
                status = "Answered";
            }
            StringProperty statusProperty = new SimpleStringProperty(status);
            return statusProperty;
        };
        
        patientIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("senderID"));
        patientNameTableColumn.setCellValueFactory(nameCVF);
        patientAgeTableColumn.setCellValueFactory(ageCVF);
        statusTableColumn.setCellValueFactory(statusCVF);
        
        queryListTableView.setItems(getQuery());
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
    }

    @FXML
    private void answerQueryOnClick(ActionEvent event) {
        TeleQuery selectedTQ = queryListTableView.getSelectionModel().getSelectedItem();
        System.out.println(selectedTQ.toString());
    }
    
    private ObservableList<TeleQuery> getQuery() {
        ObservableList<TeleQuery> queryList = FXCollections.observableArrayList();
        
        File f = null;
        FileInputStream fis = null;      
        ObjectInputStream ois = null;
        String path = "TeleQueryObjects.bin";
        try {
            f = new File(path);
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            TeleQuery tempQuery = null;
            try{
                System.out.println("Printing TQ objects");
                while(true){
                    tempQuery = (TeleQuery) ois.readObject();
                    System.out.println("Populate query: "+tempQuery.getSenderID()+", "+tempQuery.getQuery());
                    if (tempQuery.getUsertype().equals("Doctor")){
                        queryList.add((TeleQuery)tempQuery);
                    }
                }
            }
            catch(IOException | ClassNotFoundException e){
                System.out.println(e.toString());
                System.out.println("IOException | ClassNotFoundException in reading bin file");
            }
            System.out.println("End of file\n");
        } catch (IOException ex) {
            System.out.println("IOException on entire file handling");
        }
        finally {
            try {
                if(ois != null) ois.close();
            } catch (IOException ex) { }
        }
        return queryList;
    }
    
}
