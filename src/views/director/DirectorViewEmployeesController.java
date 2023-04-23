/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.director;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import users.Director;

/**
 * FXML Controller class
 *
 * @author Kazi
 */
public class DirectorViewEmployeesController implements Initializable {

    @FXML
    private TableView<?> employeeTableView;
    @FXML
    private TableColumn<?, ?> employeeIDTableColumn;
    @FXML
    private TableColumn<?, ?> employeeNameTableColumn;
    @FXML
    private TableColumn<?, ?> employeeAddressTableColumn1;
    @FXML
    private TableColumn<?, ?> employeeEmailTableColumn;
    @FXML
    private TableColumn<?, ?> employeePhoneTableColumn;
    @FXML
    private TableColumn<?, ?> employeDeptTableColumn;
    @FXML
    private TableColumn<?, ?> employeeDesigTableColumn;
    @FXML
    private TableColumn<?, ?> employeeDOBTableColumn;
    @FXML
    private TableColumn<?, ?> employeeDOJTableColumn;
    @FXML
    private TableColumn<?, ?> employeeAddressTableColumn;
    @FXML
    private TextField nameSearchTextField;
    @FXML
    private TextField IDSearchTextField;
    private Director director;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public Director getDirector() {
        return director;
    }
        
    public void setDirector(Director director) {
        this.director = director;
    }

    @FXML
    private void returnToDashboardOnClick(ActionEvent event) {
    }

    @FXML
    private void addOrSubtractSalary(ActionEvent event) {
    }
    
}
