/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.director;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import users.AccountsOfficer;
import users.Director;
import users.Doctor;
import users.Employee;
import users.HROfficer;
import users.LabTechnician;
import users.Nurse;
import users.Pharmacist;
import users.User;

/**
 * FXML Controller class
 *
 * @author Kazi
 */
public class DirectorViewEmployeesController implements Initializable {

    @FXML
    private TableView<Employee> employeeTableView;
    @FXML
    private TableColumn<Employee, Integer> employeeIDTableColumn;
    @FXML
    private TableColumn<Employee, String> employeeNameTableColumn;
    @FXML
    private TableColumn<Employee, String> employeeEmailTableColumn;
    @FXML
    private TableColumn<Employee, String> employeePhoneTableColumn;
    @FXML
    private TableColumn<Employee, String> employeDeptTableColumn;
    @FXML
    private TableColumn<Employee, String> employeeDesigTableColumn;
    @FXML
    private TableColumn<Employee, LocalDate> employeeDOBTableColumn;
    @FXML
    private TableColumn<Employee, LocalDate> employeeDOJTableColumn;
    @FXML
    private TableColumn<Employee, String> employeeAddressTableColumn;
    @FXML
    private TextField nameSearchTextField;
    @FXML
    private TextField IDSearchTextField;
    private Director director;
    @FXML
    private TableColumn<Employee, String> employeeGenderColumn;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        employeeIDTableColumn.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("ID"));
        employeeNameTableColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
        employeeAddressTableColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("address"));
        employeeEmailTableColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("email"));        
        employeePhoneTableColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("contactNo"));        
        employeDeptTableColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("department"));        
        employeeDesigTableColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("designation"));        
        employeeDOBTableColumn.setCellValueFactory(new PropertyValueFactory<Employee, LocalDate>("DOB"));        
        employeeDOJTableColumn.setCellValueFactory(new PropertyValueFactory<Employee, LocalDate>("DOJ"));        
        employeeGenderColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("gender"));
               
        employeeTableView.setItems(getEmployees());
        
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
    

    @FXML
    private void employeeSchedule(ActionEvent event) {
    }
    
    public ObservableList<Employee> getAccounts(){
        ObservableList<Employee> accountsOfficerList = FXCollections.observableArrayList();
        File f = null;
        FileInputStream fis = null;      
        ObjectInputStream ois = null;
        String path = "AccountsOfficerObjects.bin";
        try {
            f = new File(path);
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            User tempUser = null;
            try{
                System.out.println("Printing objects");
                while(true){
                    tempUser = (AccountsOfficer) ois.readObject();
                    System.out.println("Populate Employee (Accounts Officer):");
                    System.out.println(tempUser.toString());
                    accountsOfficerList.add((AccountsOfficer)tempUser);
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
        System.out.println(accountsOfficerList);        
        return accountsOfficerList;
    }    
    
    public ObservableList<Employee> getDoctors(){
        ObservableList<Employee> doctorList = FXCollections.observableArrayList();
        File f = null;
        FileInputStream fis = null;      
        ObjectInputStream ois = null;
        String path = "DoctorObjects.bin";
        try {
            f = new File(path);
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            User tempUser = null;
            try{
                System.out.println("Printing objects");
                while(true){
                    tempUser = (Doctor) ois.readObject();
                    System.out.println("Populate Employee (Doctor):");
                    System.out.println(tempUser.toString());
                    doctorList.add((Doctor)tempUser);
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
        System.out.println(doctorList);        
        return doctorList;
    }    
    
    public ObservableList<Employee> getHROfficers(){
        ObservableList<Employee> hrOfficerList = FXCollections.observableArrayList();
        File f = null;
        FileInputStream fis = null;      
        ObjectInputStream ois = null;
        String path = "HROfficerObjects.bin";
        try {
            f = new File(path);
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            User tempUser = null;
            try{
                System.out.println("Printing objects");
                while(true){
                    tempUser = (HROfficer) ois.readObject();
                    System.out.println("Populate Employee (Doctor):");
                    System.out.println(tempUser.toString());
                    hrOfficerList.add((HROfficer)tempUser);
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
        System.out.println(hrOfficerList);        
        return hrOfficerList;
    } 
    
    public ObservableList<Employee> getLabTechnicians(){
        ObservableList<Employee> labTechnicianList = FXCollections.observableArrayList();
        File f = null;
        FileInputStream fis = null;      
        ObjectInputStream ois = null;
        String path = "LabTechnicianObjects.bin";
        try {
            f = new File(path);
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            User tempUser = null;
            try{
                System.out.println("Printing objects");
                while(true){
                    tempUser = (LabTechnician) ois.readObject();
                    System.out.println("Populate Employee (Doctor):");
                    System.out.println(tempUser.toString());
                    labTechnicianList.add((LabTechnician)tempUser);
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
        System.out.println(labTechnicianList);        
        return labTechnicianList;
    } 
    
    public ObservableList<Employee> getNurses(){
        ObservableList<Employee> nurseList = FXCollections.observableArrayList();
        File f = null;
        FileInputStream fis = null;      
        ObjectInputStream ois = null;
        String path = "NurseObjects.bin";
        try {
            f = new File(path);
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            User tempUser = null;
            try{
                System.out.println("Printing objects");
                while(true){
                    tempUser = (Nurse) ois.readObject();
                    System.out.println("Populate Employee (Doctor):");
                    System.out.println(tempUser.toString());
                    nurseList.add((Nurse)tempUser);
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
        System.out.println(nurseList);        
        return nurseList;
    } 
    
    public ObservableList<Employee> getPharmacists(){
        ObservableList<Employee> pharmacistList = FXCollections.observableArrayList();
        File f = null;
        FileInputStream fis = null;      
        ObjectInputStream ois = null;
        String path = "PharmacistObjects.bin";
        try {
            f = new File(path);
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            User tempUser = null;
            try{
                System.out.println("Printing objects");
                while(true){
                    tempUser = (Pharmacist) ois.readObject();
                    System.out.println("Populate Employee (Doctor):");
                    System.out.println(tempUser.toString());
                    pharmacistList.add((Pharmacist)tempUser);
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
        System.out.println(pharmacistList);        
        return pharmacistList;
    }     
    
    
    public ObservableList<Employee> getEmployees(){
        ObservableList<Employee> employeeList = FXCollections.observableArrayList();
        employeeList.addAll(getAccounts());
        employeeList.addAll(getDoctors());
        employeeList.addAll(getHROfficers());
        employeeList.addAll(getLabTechnicians());
        employeeList.addAll(getNurses());
        employeeList.addAll(getPharmacists());        
        
        return employeeList;
    }
    
}
    
    

