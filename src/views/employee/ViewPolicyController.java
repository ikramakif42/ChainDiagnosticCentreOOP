package views.employee;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import model.Policy;
import users.Employee;
import users.User;

public class ViewPolicyController implements Initializable {

    @FXML
    private TextArea policyTextArea;
    private Employee employee;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Policy> policyList = User.viewPolicies();
        for (Policy temp : policyList){
            policyTextArea.appendText(temp.getNumber()+": "+temp.getContent()+"\n");
        }
    }    

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @FXML
    private void returnToMyWorkplaceOnClick(ActionEvent event) throws IOException {
        Parent empDashboard = null;
        FXMLLoader empLoader = new FXMLLoader(getClass().getResource("MyWorkplace.fxml"));
        empDashboard = (Parent) empLoader.load();
        Scene empScene = new Scene(empDashboard);

        MyWorkplaceController emp = empLoader.getController();
        emp.setEmployee(this.employee);

        Stage empStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        empStage.setScene(empScene);
        empStage.show();
    }
    
}
