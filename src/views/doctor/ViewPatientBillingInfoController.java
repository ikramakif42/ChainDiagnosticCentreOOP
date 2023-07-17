package views.doctor;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Bill;
import users.Doctor;
import users.Patient;

public class ViewPatientBillingInfoController implements Initializable {

    @FXML
    private TableView<Bill> billingInfoTableView;
    @FXML
    private TableColumn<Bill, LocalDate> billDateTableColumn;
    @FXML
    private TableColumn<Bill, String> detailsTableColumn;
    @FXML
    private TableColumn<Bill, Float> amountTableColumn;
    @FXML
    private TableColumn<Bill, String> statusTableColumn;
    @FXML
    private Label patientIDLabel;
    @FXML
    private Label patientNameLabel;
    @FXML
    private Label patientAgeLabel;
    @FXML
    private ToggleGroup billStatusTG;
    @FXML
    private RadioButton paidStatusRadioButton;
    @FXML
    private RadioButton dueStatusRadioButton;
    @FXML
    private RadioButton allStatusRadioButton;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;
    
    private Doctor doc;
    private Patient pat;
    private FilteredList<Bill> filterBillList;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Callback<TableColumn.CellDataFeatures<Bill, String>, ObservableValue<String>> paidCVF = feature -> {
            Bill temp = feature.getValue();
            if (temp.isPaidStatus()){return new SimpleStringProperty("Paid");}
            else{return new SimpleStringProperty("Due");}
        };
        
        billDateTableColumn.setCellValueFactory(new PropertyValueFactory<>("createdDate"));
        detailsTableColumn.setCellValueFactory(new PropertyValueFactory<>("details"));
        amountTableColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        statusTableColumn.setCellValueFactory(paidCVF);
        
        startDatePicker.setDayCellFactory(dp -> new DateCell(){
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (endDatePicker.getValue()!=null){
                    if (date.isAfter(endDatePicker.getValue())) {
                        setDisable(true);
                    }
                }
            }
        });
        
        endDatePicker.setDayCellFactory(dp -> new DateCell(){
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (startDatePicker.getValue()!=null){
                    if (date.isBefore(startDatePicker.getValue())) {
                        setDisable(true);
                    }
                }
            }
        });
        allStatusRadioButton.setSelected(true);
    }

    public Doctor getDoc() {
        return doc;
    }

    public void setDoc(Doctor doc) {
        this.doc = doc;
    }

    public Patient getPat() {
        return pat;
    }

    public void setPat(Patient pat) {
        this.pat = pat;
        patientIDLabel.setText(String.valueOf(pat.getID()));
        patientNameLabel.setText(pat.getName());
        patientAgeLabel.setText(String.valueOf(Period.between(pat.getDOB(), LocalDate.now()).getYears()));
        ObservableList<Bill> BillList = this.doc.getBillList(this.pat.getID());
        this.filterBillList = new FilteredList<>(BillList, b -> true);
        billingInfoTableView.setItems(this.filterBillList);
    }

    @FXML
    private void applyFiltersOnClick(ActionEvent event) {
        if (paidStatusRadioButton.isSelected()){
            filterBillList.setPredicate(temp ->{
                if (temp.isPaidStatus()){
                    return true;
                } else {return false;}
            }
            );
        }
        else if (dueStatusRadioButton.isSelected()){
            filterBillList.setPredicate(temp ->{
                if (!temp.isPaidStatus()){
                    return true;
                } else {return false;}
            }
            );
        }
        else{
            filterBillList.setPredicate(tempTQ -> true);
        }
        LocalDate start = startDatePicker.getValue();
        LocalDate end = endDatePicker.getValue();
        if (start!=null && end!=null){
            filterBillList.setPredicate(temp ->{
                LocalDate billDate = temp.getCreatedDate();
                if (billDate.isAfter(start)&&billDate.isBefore(end)){
                    return true;
                } else {return false;}
            }
            );
        }
    }

    @FXML
    private void clearFiltersOnClick(ActionEvent event) {
        allStatusRadioButton.setSelected(true);
        startDatePicker.setValue(null);
        endDatePicker.setValue(null);
    }

    @FXML
    private void returnToMyPatientsOnClick(ActionEvent event) throws IOException {
        Parent parent = null;
        FXMLLoader doctorLoader = new FXMLLoader(getClass().getResource("DoctorMyPatients.fxml"));
        parent = (Parent) doctorLoader.load();
        Scene doctorScene = new Scene(parent);

        DoctorMyPatientsController d = doctorLoader.getController();
        d.setDoc(this.doc);

        Stage doctorStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        doctorStage.setScene(doctorScene);
        doctorStage.show();
    }
    
}
