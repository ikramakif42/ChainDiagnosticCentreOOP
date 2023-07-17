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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.AppendableObjectOutputStream;
import model.Policy;
import model.Report;
import model.Schedule;

public class Director extends Employee implements Serializable {
    private static final long serialVersionUID = 13L;
    private int stockOptions;
    
    
    public Director(String designation, String department, Float salary, String branchName, String name, int ID, String password, String email, String gender, String contactNo, String address, LocalDate DOB) {
        super(designation, department, salary, branchName, name, ID, password, email, gender, contactNo, address, DOB);
        this.scheduleRoster = super.scheduleRoster;
    }

    public int getStockOptions() {
        return stockOptions;
    }

    public void setStockOptions(int stockOptions) {
        this.stockOptions = stockOptions;
    }
    
    @Override
    public String toString() {
        return "Director: " + "Desig="+designation+" Dept= "+department+" Salary= "+salary+" DOJ= "+DOJ+" branch= "+branchName+" Name= "+name+" ID= "+ID+" pass= "+password+" mail= "+email+" gender"+gender+" contact= "+contactNo+" addr= "+address+" DOJ= "+DOB;
    }
    
      public static ObservableList<Employee> viewEmployeeList(){
        ObservableList<Employee> employeeList = FXCollections.observableArrayList();

        employeeList.addAll(AccountsOfficer.getAllAccounts());
        employeeList.addAll(Doctor.getAllDoctors());
        employeeList.addAll(HROfficer.getAllHROfficers());
        employeeList.addAll(LabTechnician.getAllLabTechnicians());
        employeeList.addAll(Nurse.getAllNurses());
        employeeList.addAll(Pharmacist.getAllPharmacists());        
        
        return employeeList;
        
      };     
      
      public static boolean editPersonalInfo(int curID, String newName, String newEmail, String newContactNo, String newAddress, Float newSalary, String newDept, String newDesig){
        
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
                    currentE.setDepartment(newDept);
                    currentE.setDesignation(newDesig);
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
              
        
      };
      
      public static boolean editSchedule(Schedule newSch) {
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
      
      public static boolean deleteSchedule(Schedule toDelete) {
        String path = null;
        ObservableList<Employee> DocList = Doctor.getAllDoctors();
        ObservableList<Employee> AccList = AccountsOfficer.getAllAccounts();
        ObservableList<Employee> HRList = HROfficer.getAllHROfficers();
        ObservableList<Employee> LabList = LabTechnician.getAllLabTechnicians();
        ObservableList<Employee> NurseList = Nurse.getAllNurses();
        ObservableList<Employee> PhList = Pharmacist.getAllPharmacists();
        boolean foundFlag=false;        
        
        for(Employee e : DocList){
            if (foundFlag){break;}
            if (e.getID() == toDelete.getAssignedToID()){
                path = "DoctorObjects.bin";
                System.out.println(DocList);
                foundFlag = true;
            }
        }        
        
        for(Employee e : AccList){
            if (foundFlag){break;}
            if (e.getID() == toDelete.getAssignedToID()){
                path = "AccountsOfficerObjects.bin";
                System.out.println(AccList);
                foundFlag = true;
            }
        }
        
        for(Employee e : HRList){
            if (foundFlag){break;}
            if (e.getID() == toDelete.getAssignedToID()){
                path = "HROfficerObjects.bin";
                System.out.println(HRList);
                foundFlag = true;
            }
        }
        
        for(Employee e : LabList){
            if (foundFlag){break;}
            if (e.getID() == toDelete.getAssignedToID()){
                path = "LabTechnicianObjects.bin";
                System.out.println(LabList);
                foundFlag = true;
            }
        }
        
        for(Employee e : NurseList){
            if (foundFlag){break;}
            if (e.getID() == toDelete.getAssignedToID()){
                path = "NurseObjects.bin";
                System.out.println(NurseList);
                foundFlag = true;
            }
        }
        
        for(Employee e : PhList){
            if (foundFlag){break;}
            if (e.getID() == toDelete.getAssignedToID()){
                path = "PharmacistObjects.bin";
                System.out.println(PhList);
                foundFlag = true;
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
            foundFlag = false;
            System.out.println(emp);

            for (Employee currentE : emp) {
                if (currentE.getID() == toDelete.getAssignedToID()) {
                    ArrayList<Schedule> schList = new ArrayList<>();
                    for (Schedule tempSch : currentE.getScheduleRoster()){
                        if (tempSch.getDay().equals(toDelete.getDay())){
                            if (tempSch.getTime().equals(toDelete.getTime())){
                                continue;
                            }
                        }
                        schList.add(tempSch);
                    }
                    currentE.setScheduleRoster(schList);
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
      
      public static boolean createBranchReport(Report newBranchReport){
          
        File f = null;
        FileOutputStream fos = null;      
        ObjectOutputStream oos = null;
        try {
            f = new File("ReportObjects.bin");
            if(f.exists()){
                fos = new FileOutputStream(f,true);
                oos = new AppendableObjectOutputStream(fos); 
                oos.writeObject(newBranchReport);
                return true;
            }
            else{
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);     
                oos.writeObject(newBranchReport);
                return true;
            }          
        
                        
        } catch (IOException ex) {
                System.out.println(ex.toString());
                System.out.println("IOException | ClassNotFoundException in reading bin file");
                return false;

        }      

      }
      
      public static ObservableList<Report> viewBranchReports(){
        ObservableList<Report> branchReportList = FXCollections.observableArrayList();
        File f = null;
        FileInputStream fis = null;      
        ObjectInputStream ois = null;
        String path = "ReportObjects.bin";
        try {
            f = new File(path);
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            Report tempBr = null;
            try{
                System.out.println("Printing objects");
                while(true){
                    tempBr = (Report) ois.readObject();
  
                    if (tempBr.type.equals("Branch")){
                        System.out.println(tempBr);
                    branchReportList.add(tempBr);
                    }
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
        System.out.println(branchReportList);
        return branchReportList;
    }
      
      public static ObservableList<Report> viewHRFinanceReports(){
        ObservableList<Report> branchReportList = FXCollections.observableArrayList();
        File f = null;
        FileInputStream fis = null;      
        ObjectInputStream ois = null;
        String path = "ReportObjects.bin";
        try {
            f = new File(path);
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            Report tempBr = null;
            try{
                System.out.println("Printing objects");
                while(true){
                    tempBr = (Report) ois.readObject();
  
                    if (tempBr.type.equals("HR")){
                    System.out.println(tempBr);
                    branchReportList.add(tempBr);}
                    
                    if (tempBr.type.equals("Finance")){
                    System.out.println(tempBr);
                    branchReportList.add(tempBr);
                    }
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
        System.out.println(branchReportList);
        return branchReportList;
    }         
            
      public static boolean updatePolicy(int curNum, String newContent){
        try {
            String path = "PolicyObjects.bin";
            File file = new File(path);
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Policy> p = new ArrayList<>();
            
            try{
                while(true){
                    Policy tempE = (Policy) ois.readObject();
                    System.out.println(tempE);
                    p.add(tempE);
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
            System.out.println(p);

            for (Policy currentP : p) {
                if (currentP.getNumber() == curNum) {
                    currentP.setContent(newContent);
                }
            }

            System.out.println(p);
            if(file.delete()){
                System.out.println("Deleted File!");
                File f = new File(path);
                FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                for (Policy currentP : p) {
                    oos.writeObject(currentP);
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
            Logger.getLogger(Policy.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println(ex);
            Logger.getLogger(Policy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;      
      };
    
}
