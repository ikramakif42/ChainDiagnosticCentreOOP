package users;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Schedule;

public class LabTechnician extends Employee implements Serializable {
    private static final long serialVersionUID = 13L;
    
    public LabTechnician(String designation, String department, Float salary, String branchName, String name, int ID, String password, String email, String gender, String contactNo, String address, LocalDate DOB) {
        super(designation, department, salary, branchName, name, ID, password, email, gender, contactNo, address, DOB);
        this.scheduleRoster = super.scheduleRoster;
    }
    
    @Override
    public String toString() {
        return "LabTechnician: " + "Desig="+designation+" Dept= "+department+" Salary= "+salary+" DOJ= "+DOJ+" branch= "+branchName+" Name= "+name+" ID= "+ID+" pass= "+password+" mail= "+email+" gender"+gender+" contact= "+contactNo+" addr= "+address+" DOJ= "+DOB+" sche="+scheduleRoster;
    }
    
    public static ObservableList<Employee> getAllLabTechnicians(){
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
}
