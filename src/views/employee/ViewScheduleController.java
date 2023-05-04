package views.employee;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Schedule;
import users.Employee;

public class ViewScheduleController implements Initializable {

    @FXML
    private TableView<Schedule> taskTableView;
    @FXML
    private TableColumn<Schedule, LocalDate> dateTableColumn;
    @FXML
    private TableColumn<Schedule, String> timeTableColumn;
    @FXML
    private TableColumn<Schedule, String> detailsTableColumn;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;
    private Employee employee;
    Alert noDates = new Alert(Alert.AlertType.WARNING, "Error, select start and end dates to filter!");
    Alert noDetails = new Alert(Alert.AlertType.WARNING, "Error, enter complaint details!");
    Alert failure = new Alert(Alert.AlertType.WARNING, "Error, failed to submit complaint!");
    Alert success = new Alert(Alert.AlertType.WARNING, "Complaint submitted successfully!");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dateTableColumn.setCellValueFactory(new PropertyValueFactory<>("day"));
        timeTableColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        detailsTableColumn.setCellValueFactory(new PropertyValueFactory<>("task"));
        
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
    }    

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
        taskTableView.setItems((ObservableList<Schedule>) this.employee.getScheduleRoster());
    }

    @FXML
    private void clearFiltersOnClick(ActionEvent event) {
        startDatePicker.setValue(null);
        endDatePicker.setValue(null);
        taskTableView.setItems((ObservableList<Schedule>) this.employee.getScheduleRoster());
    }

    @FXML
    private void applyFiltersOnClick(ActionEvent event) {
        LocalDate start = startDatePicker.getValue();
        LocalDate end = endDatePicker.getValue();
        if (start==null || end==null) {noDates.show();return;}
        
        ObservableList<Schedule> newList = FXCollections.observableArrayList();
        for (Schedule sch : taskTableView.getItems()){
            if (sch.getDay().isBefore(end) && sch.getDay().isAfter(start)){
                newList.add(sch);
            }
        }
        taskTableView.setItems(newList);
    }
    
}
