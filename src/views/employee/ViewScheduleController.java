package views.employee;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @FXML
    private void clearFiltersOnClick(ActionEvent event) {
    }

    @FXML
    private void applyFiltersOnClick(ActionEvent event) {
    }
    
}
