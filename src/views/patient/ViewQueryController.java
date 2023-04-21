package views.patient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.TeleQuery;
import users.Patient;

public class ViewQueryController implements Initializable {

    @FXML
    private TableView<TeleQuery> queryTableView;
    @FXML
    private TableColumn<TeleQuery, String> sentToTableColumn;
    @FXML
    private TableColumn<TeleQuery, String> statusTableColumn;
    private Patient patient;

    public ViewQueryController(Patient patient) {
        this.patient = patient;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Loaded "+this.patient.toString());
        Callback<TableColumn.CellDataFeatures<TeleQuery, String>, ObservableValue<String>> newCVF = feature -> {
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
        
        sentToTableColumn.setCellValueFactory(new PropertyValueFactory<>("usertype"));
        statusTableColumn.setCellValueFactory(newCVF);
        if(this.patient!=null){queryTableView.setItems(getQuery());}
    }

    public Patient getPatient() {
        return patient;
    }

    @FXML
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @FXML
    private void returnToDashboardOnClick(ActionEvent event) throws IOException {
        Parent patientDashboard = null;
        FXMLLoader patientLoader = new FXMLLoader(getClass().getResource("PatientDashboard.fxml"));
        patientDashboard = (Parent) patientLoader.load();
        Scene patientScene = new Scene(patientDashboard);

        PatientDashboardController p = patientLoader.getController();
        p.setPatient(this.patient);

        Stage patientStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        patientStage.setScene(patientScene);
        patientStage.show();
    }

    @FXML
    private void viewQueryDetailsOnClick(ActionEvent event) {
        TeleQuery selectedTQ = queryTableView.getSelectionModel().getSelectedItem();
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
                    System.out.println(this.patient.getID());
//                    if (tempQuery.getSenderID()==this.patient.getID()){
                        queryList.add((TeleQuery)tempQuery);
//                    }
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
