
package views.nurse;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import users.Nurse;
import views.doctor.DoctorDashboardController;
import views.employee.MyWorkplaceController;



public class NurseDashboardController implements Initializable {

    private Nurse nurse;

  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 

    public Nurse getNurse() {
        return nurse;
    }

    public void setNurse(Nurse nurse) {
        this.nurse = nurse;
    }
    
    

    @FXML
    private void nurseViewPatientListOnClick(ActionEvent event) throws IOException {
        Parent nurseViewPatientList = null;
        FXMLLoader nurseLoader = new FXMLLoader(getClass().getResource("NursePatientList.fxml"));
        nurseViewPatientList = (Parent) nurseLoader.load();
        Scene nurseViewPatientListScene = new Scene(nurseViewPatientList);

        NursePatientListController n = nurseLoader.getController();
        n.setNurse(this.nurse);

        Stage nurseStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        nurseStage.setScene(nurseViewPatientListScene);
        nurseStage.show();
    }

    @FXML
    private void nurseAppointmentOnClick(ActionEvent event) throws IOException {
        Parent docAppt = null;
        FXMLLoader docApptLoader = new FXMLLoader(getClass().getResource("NurseDocAppointmentSchedule.fxml"));
        docAppt = (Parent) docApptLoader.load();
        Scene docApptScene = new Scene(docAppt);

        NurseDocAppointmentScheduleController n = docApptLoader.getController();
        n.setNurse(this.nurse);

        Stage docApptStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        docApptStage.setScene(docApptScene);
        docApptStage.show();
    }

    @FXML
    private void nurseViewStockOnClick(ActionEvent event) throws IOException {
        Parent nurseViewStock = null;
        FXMLLoader nurseViewStockLoader = new FXMLLoader(getClass().getResource("NurseStock.fxml"));
        nurseViewStock = (Parent) nurseViewStockLoader.load();
        Scene nurseViewStockScene = new Scene(nurseViewStock);

        NurseStockController n = nurseViewStockLoader.getController();
        n.setNurse(this.nurse);

        Stage nurseViewStockStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        nurseViewStockStage.setScene(nurseViewStockScene);
        nurseViewStockStage.show();

    }


    @FXML
    private void nurseLogOutOnClick(ActionEvent event) {
        Parent login=null;
        try {
            login = FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(DoctorDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene1 = new Scene(login);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene1);
        window.show();
    }

    @FXML
    private void nurseAssignedTaskOnClick(ActionEvent event) throws IOException {
        Parent nurseAssignedTask = null;
        FXMLLoader nurseAssignedTaskLoader = new FXMLLoader(getClass().getResource("NurseAssignedTaskTable.fxml"));
        nurseAssignedTask = (Parent) nurseAssignedTaskLoader.load();
        Scene nurseViewPatientListScene = new Scene(nurseAssignedTask);

        NurseAssignedTaskTableController n = nurseAssignedTaskLoader.getController();
        n.setNurse(this.nurse);

        Stage nurseTaskStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        nurseTaskStage.setScene(nurseViewPatientListScene);
        nurseTaskStage.show();
    }

    @FXML
    private void nurseMyWorkplaceOnClick(ActionEvent event) throws IOException {
        Parent empDashboard = null;
        FXMLLoader empLoader = new FXMLLoader(getClass().getResource("/views/employee/MyWorkplace.fxml"));
        empDashboard = (Parent) empLoader.load();
        Scene empScene = new Scene(empDashboard);

        MyWorkplaceController emp = empLoader.getController();
        emp.setEmployee(this.nurse);

        Stage empStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        empStage.setScene(empScene);
        empStage.show();

    }
    
}
