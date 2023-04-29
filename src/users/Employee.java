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
import main.AppendableObjectOutputStream;
import model.Complaint;
import model.LeaveApplication;
import model.LoanApplication;
import model.Resignation;
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


    

    public void submitLeaveApplication(LocalDate start, LocalDate end, String type, String details) {
        LeaveApplication leave = new LeaveApplication(start, end, type, details, this.getID());
        File f = null;
        FileOutputStream fos = null; 
        ObjectOutputStream oos = null;
        try {
            f = new File("LeaveApplicationObjects.bin");
            if(f.exists()){
                fos = new FileOutputStream(f,true);
                oos = new AppendableObjectOutputStream(fos);                
            }
            else{
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);               
            }
            oos.writeObject(leave);
        } catch (IOException ex) {
            System.out.println(ex.toString());
        } finally {
            try {
                if(oos != null) oos.close();
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
        }
        System.out.println("Leave application written successfully!");
    }    
    public void submitLoanApplication(float amount, LocalDate date, String duration, String type, String details) {
        LoanApplication loan = new LoanApplication(amount, date, duration, type, details, this.getID());
        File f = null;
        FileOutputStream fos = null; 
        ObjectOutputStream oos = null;
        try {
            f = new File("LoanApplicationObjects.bin");
            if(f.exists()){
                fos = new FileOutputStream(f,true);
                oos = new AppendableObjectOutputStream(fos);                
            }
            else{
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);               
            }
            oos.writeObject(loan);
        } catch (IOException ex) {
            System.out.println(ex.toString());
        } finally {
            try {
                if(oos != null) oos.close();
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
        }
        System.out.println("Loan application written successfully!");
    } 
    public void submitComplaint(String subject, String details) {
        Complaint complaint = new Complaint(this.getID(), subject, details);
        File f = null;
        FileOutputStream fos = null; 
        ObjectOutputStream oos = null;
        try {
            f = new File("ComplaintObjects.bin");
            if(f.exists()){
                fos = new FileOutputStream(f,true);
                oos = new AppendableObjectOutputStream(fos);                
            }
            else{
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);               
            }
            oos.writeObject(complaint);
        } catch (IOException ex) {
            System.out.println(ex.toString());
        } finally {
            try {
                if(oos != null) oos.close();
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
        }
        System.out.println("Loan application written successfully!");
    }
    public void submitResignation(LocalDate date, String details) {
        Resignation resignation = new Resignation(this.getID(), date, details);
        File f = null;
        FileOutputStream fos = null; 
        ObjectOutputStream oos = null;
        try {
            f = new File("ResignationObjects.bin");
            if(f.exists()){
                fos = new FileOutputStream(f,true);
                oos = new AppendableObjectOutputStream(fos);                
            }
            else{
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);               
            }
            oos.writeObject(resignation);
        } catch (IOException ex) {
            System.out.println(ex.toString());
        } finally {
            try {
                if(oos != null) oos.close();
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
        }
        System.out.println("Resignation written successfully!");
    }
    public boolean isResigned() {
        File file = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try{
            file = new File("ResignationObjects.bin");
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            try{
                while(true){
                    Resignation temp = (Resignation) ois.readObject();
                    if (temp.getApplicantID()==this.getID()){
                        ois.close();
                        return true;
                    }
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
        }
        catch (FileNotFoundException ex){
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
}
