package views.HROfficer;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import users.Employee;
import users.HROfficer;
import users.User;

/**
 * FXML Controller class
 *
 * @author arafath
 */
public class AddNewEmployeeController implements Initializable {
    
    private HROfficer HR;
    @FXML
    private TextField EmailTextField;
    @FXML
    private TextField ContractTextField;
    @FXML
    private TextField AddressTextField;
    @FXML
    private TextField DesignationTextField;
    @FXML
    private TextField DepartmentTextField;
    @FXML
    private TextField BranchNameTextField;
    @FXML
    private TextField IDTextField;
    @FXML
    private TextField NameTextField;
    @FXML
    private TextField SalaryTextField;
    @FXML
    private TextField GenderTextField;
    @FXML
    private TextField PasswordTextField;
    @FXML
    private ComboBox<String> typeComboBox;
    @FXML
    private DatePicker DOBPicker;
    Alert idNumError = new Alert(Alert.AlertType.WARNING, "Error, ID must be a number only, less than 5 digits!");
    Alert salaryNumError = new Alert(Alert.AlertType.WARNING, "Error, enter valid salary!");
    Alert idExistsError = new Alert(Alert.AlertType.WARNING, "Error, ID exists, choose another!");
    Alert passError = new Alert(Alert.AlertType.WARNING, "Error, password must be at least 6 characters long!");
    Alert emailError = new Alert(Alert.AlertType.WARNING, "Error, valid email address!");
    Alert failure = new Alert(Alert.AlertType.WARNING, "Error, add new employee failed!");
    Alert failureNull = new Alert(Alert.AlertType.WARNING, "Error, fill up all fields!");
    Alert success = new Alert(Alert.AlertType.INFORMATION, "Add employee successful!");
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String[] deptList = {"AccountsOfficer", "Director", "Doctor", "HROfficer", "LabTechnician", "Nurse", "Pharmacist"};
        typeComboBox.getItems().addAll(deptList);
        
        DOBPicker.setDayCellFactory(dp -> new DateCell(){
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (date.isAfter(LocalDate.now())) {
                    setDisable(true);
                }
            }
        });
    }

    public HROfficer getHR() {
        return HR;
    }

    public void setHR(HROfficer HR) {
        this.HR = HR;
    }

    @FXML
    private void AddNewEmployeeOnClick(ActionEvent event) {
        String usertype = typeComboBox.getSelectionModel().getSelectedItem();
        
        String designation = DesignationTextField.getText();
        if (designation.isEmpty()) {failureNull.show();return;}
        String department = DepartmentTextField.getText();
        if (designation.isEmpty()) {failureNull.show();return;}
        String salaryValue = SalaryTextField.getText();
        if (salaryValue.isEmpty()) {failureNull.show();return;}
        Float salary = 0f;
        try {
            salary = Float.parseFloat(salaryValue);
        } catch (NumberFormatException e) {salaryNumError.show();return;}
        
        String branchName = BranchNameTextField.getText();
        if (branchName.isEmpty()) {failureNull.show();return;}
        
        String name = NameTextField.getText();
        if (name.isEmpty()) {failureNull.show();return;}
        
        String id = IDTextField.getText();
        if (id.isEmpty()) {failureNull.show();return;}
        if (!User.isNumeric(id) || id.length()>=5) {idNumError.show();return;}
        if(User.userLogin(Integer.parseInt(id), "")==2) {idExistsError.show();return;}
        int ID = Integer.parseInt(id);
        
        String password = PasswordTextField.getText();
        if (password.isEmpty()) {failureNull.show();return;}
        if (password.length()<6){passError.show();return;}
        
        String email = EmailTextField.getText();
        if (email.isEmpty()) {failureNull.show();return;}
        if (!email.contains("@")){emailError.show();return;}
        
        String gender = GenderTextField.getText();
        if (gender.isEmpty()) {failureNull.show();return;}
        String contact = ContractTextField.getText();
        if (contact.isEmpty()) {failureNull.show();return;}
        String address = AddressTextField.getText();
        if (address.isEmpty()) {failureNull.show();return;}
        LocalDate DOB = DOBPicker.getValue();
        
        if (this.HR.addNewEmployee(usertype, designation, department, salary, branchName, name, ID, password, email, gender, contact, address, DOB)){
            success.show();
        }
        else{failure.show();}
    }

    @FXML
    private void ReturnDashboardOnClick(ActionEvent event) throws IOException {
        Parent parent = null;
        FXMLLoader HRLoader = new FXMLLoader(getClass().getResource("HRDashboard.fxml"));
        parent = (Parent) HRLoader.load();
        Scene HRScene = new Scene(parent);
        
        HRDashboardController m = HRLoader.getController();
        m.setHR(this.HR);

        Stage HRStage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
        HRStage.setScene(HRScene);
        HRStage.show();
    }
    
}
