package users;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import model.Schedule;

public abstract class Employee extends User implements Serializable{
    private static final long serialVersionUID = 13L;
    
    public String designation, department;
    protected Float salary;
    final LocalDate DOJ;
    public String branchName;
    protected ArrayList<Schedule> scheduleRoster = new ArrayList<Schedule>();

    public Employee(String designation, String department, Float salary, LocalDate DOJ, String branchName, String name, int ID, String password, String email, String gender, String contactNo, String address, LocalDate DOB) {
        super(name, ID, password, email, gender, contactNo, address, DOB);
        this.designation = designation;
        this.department = department;
        this.salary = salary;
        this.DOJ = DOJ;
        this.branchName = branchName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public LocalDate getDOJ() {
        return DOJ;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public ArrayList<Schedule> getScheduleRoster() {
        return scheduleRoster;
    }

    public void setScheduleRoster(ArrayList<Schedule> scheduleRoster) {
        this.scheduleRoster = scheduleRoster;
    }    

    public static boolean updatePersonalInfo(int curID, String newName, String newEmail, String newContactNo, String newAddress, Float newSalary){
        
        String path = null;
        ObservableList<Employee> DocList = Doctor.getAllDoctors();
        ObservableList<Employee> AccList = AccountsOfficer.getAllAccounts();
        ObservableList<Employee> HRList = HROfficer.getAllHROfficers();
        ObservableList<Employee> LabList = LabTechnician.getAllLabTechnicians();
        ObservableList<Employee> NurseList = Nurse.getAllNurses();
        ObservableList<Employee> PhList = Pharmacist.getAllPharmacists();
        
        for(Employee e : DocList){
            if (e.getID() == curID){
                path = "DoctorObjects.bin";
                System.out.println(DocList);
            }
        }        
        
        for(Employee e : AccList){
            if (e.getID() == curID){
                path = "AccountsOfficerObjects.bin";
                System.out.println(AccList);
            }
        }
        
        for(Employee e : HRList){
            if (e.getID() == curID){
                path = "HROfficerObjects.bin";
                System.out.println(HRList);
            }
        }
        
        for(Employee e : LabList){
            if (e.getID() == curID){
                path = "LabTechnicianObjects.bin";
                System.out.println(LabList);
            }
        }
        
        for(Employee e : NurseList){
            if (e.getID() == curID){
                path = "NurseObjects.bin";
                System.out.println(NurseList);
            }
        }
        
        for(Employee e : PhList){
            if (e.getID() == curID){
                path = "PharmacistObjects.bin";
                System.out.println(PhList);
            }
        }
        
        
        try {
            File file = new File(path);
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Employee> emp = new ArrayList<>();
            
            try{
                while(true){
                    Employee tempE = (Employee) ois.readObject();
                    System.out.println(tempE);
                    emp.add(tempE);
                }
            }
            catch (EOFException eof){
                System.out.println("End of file");
            }
            catch(IOException | ClassNotFoundException e){
                System.out.println(e.toString());
                System.out.println("IOException | ClassNotFoundException in reading bin file");
            }
            
            ois.close();
            System.out.println(emp);

            for (Employee currentE : emp) {
                if (currentE.getID() == curID) {
                    currentE.setName(newName);
                    currentE.setEmail(newEmail);
                    currentE.setAddress(newAddress);
                    currentE.setContactNo(newContactNo);
                    currentE.setSalary(newSalary);
                }
            }

            System.out.println(emp);
            if(file.delete()){
                System.out.println("Deleted File!");
                File f = new File(path);
                FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                for (Employee currentE : emp) {
                    oos.writeObject(currentE);
                }
                oos.close();
                System.out.println("Fixed File!");
                return true;
            }
            else{
                System.out.println("Could not delete file");
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
            Logger.getLogger(Patient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println(ex);
            Logger.getLogger(Patient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
    }    

    public boolean updateSchedule(Schedule newSch) {
        String path = null;
        ObservableList<Employee> DocList = Doctor.getAllDoctors();
        ObservableList<Employee> AccList = AccountsOfficer.getAllAccounts();
        ObservableList<Employee> HRList = HROfficer.getAllHROfficers();
        ObservableList<Employee> LabList = LabTechnician.getAllLabTechnicians();
        ObservableList<Employee> NurseList = Nurse.getAllNurses();
        ObservableList<Employee> PhList = Pharmacist.getAllPharmacists();
        
        for(Employee e : DocList){
            if (e.getID() == newSch.getAssignedToID()){
                path = "DoctorObjects.bin";
                System.out.println(DocList);
            }
        }        
        
        for(Employee e : AccList){
            if (e.getID() == newSch.getAssignedToID()){
                path = "AccountsOfficerObjects.bin";
                System.out.println(AccList);
            }
        }
        
        for(Employee e : HRList){
            if (e.getID() == newSch.getAssignedToID()){
                path = "HROfficerObjects.bin";
                System.out.println(HRList);
            }
        }
        
        for(Employee e : LabList){
            if (e.getID() == newSch.getAssignedToID()){
                path = "LabTechnicianObjects.bin";
                System.out.println(LabList);
            }
        }
        
        for(Employee e : NurseList){
            if (e.getID() == newSch.getAssignedToID()){
                path = "NurseObjects.bin";
                System.out.println(NurseList);
            }
        }
        
        for(Employee e : PhList){
            if (e.getID() == newSch.getAssignedToID()){
                path = "PharmacistObjects.bin";
                System.out.println(PhList);
            }
        }
        
        
        try {
            File file = new File(path);
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Employee> emp = new ArrayList<>();
            
            try{
                while(true){
                    Employee tempE = (Employee) ois.readObject();
                    System.out.println(tempE);
                    emp.add(tempE);
                }
            }
            catch (EOFException eof){
                System.out.println("End of file");
            }
            catch(IOException | ClassNotFoundException e){
                System.out.println(e.toString());
                System.out.println("IOException | ClassNotFoundException in reading bin file");
            }
            
            ois.close();
            System.out.println(emp);

            for (Employee currentE : emp) {
                if (currentE.getID() == newSch.getAssignedToID()) {
                    ArrayList<Schedule> schList = currentE.getScheduleRoster();
                    if (schList==null){
                        ArrayList<Schedule> addList = new ArrayList<>();
                        addList.add(newSch);
                        currentE.setScheduleRoster(addList);
                    }
                    else {
                        schList.add(newSch);
                        currentE.setScheduleRoster(schList);
                    }
                }
            }

            System.out.println(emp);
            if(file.delete()){
                System.out.println("Deleted File!");
                File f = new File(path);
                FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                for (Employee currentE : emp) {
                    oos.writeObject(currentE);
                }
                oos.close();
                System.out.println("Fixed File!");
                return true;
            }
            else{
                System.out.println("Could not delete file");
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
            Logger.getLogger(Patient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println(ex);
            Logger.getLogger(Patient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    
}
