package views.patient;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Bill;
import users.Patient;

public class ViewPayBillController implements Initializable {

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
    private RadioButton paidStatusRadioButton;
    @FXML
    private ToggleGroup billStatusTG;
    @FXML
    private RadioButton dueStatusRadioButton;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;
    private Patient patient;
    @FXML
    private RadioButton allStatusRadioButton;
    private FilteredList<Bill> filterBillList;
    Alert noBill = new Alert(AlertType.WARNING, "Error, select a bill!");
    Alert paid = new Alert(AlertType.WARNING, "Error, selected bill already paid!");
    
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

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
        ObservableList<Bill> BillList = this.patient.getBillList();
        this.filterBillList = new FilteredList<>(BillList, b -> true);
        billingInfoTableView.setItems(this.filterBillList);
    }

    @FXML
    private void applyFIltersOnClick(ActionEvent event) {
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
    private void payBillsOnClick(ActionEvent event) throws IOException {
        Bill toPay = billingInfoTableView.getSelectionModel().getSelectedItem();
        if (toPay==null){noBill.show();return;}
        else if (toPay.isPaidStatus()){paid.show();return;}
        
        Parent payBill = null;
        FXMLLoader payBillLoader = new FXMLLoader(getClass().getResource("ConfirmPayBill.fxml"));
        payBill = (Parent) payBillLoader.load();
        Scene payBillScene = new Scene(payBill);

        ConfirmPayBillController cb = payBillLoader.getController();
        cb.setPatient(this.patient);
        cb.setToPay(toPay);

        Stage payBillStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        payBillStage.setScene(payBillScene);
        payBillStage.show();
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
    
}
