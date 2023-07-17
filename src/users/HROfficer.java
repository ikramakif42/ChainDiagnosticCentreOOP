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
import model.LoginInfo;
import model.Policy;
import model.Schedule;

public class HROfficer extends Employee implements Serializable {
    private static final long serialVersionUID = 13L;
    
    public HROfficer(String designation, String department, Float salary, String branchName, String name, int ID, String password, String email, String gender, String contactNo, String address, LocalDate DOB) {
        super(designation, department, salary, branchName, name, ID, password, email, gender, contactNo, address, DOB);
        this.scheduleRoster = super.scheduleRoster;
    }
    
    @Override
    public String toString() {
        return "HROfficer: " + "Desig="+designation+" Dept= "+department+" Salary= "+salary+" DOJ= "+DOJ+" branch= "+branchName+" Name= "+name+" ID= "+ID+" pass= "+password+" mail= "+email+" gender"+gender+" contact= "+contactNo+" addr= "+address+" DOJ= "+DOB+" sche="+scheduleRoster;
    }
    
    
    public static ObservableList<Employee> getAllHROfficers(){
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

    public boolean addNewEmployee(String usertype, String designation, String department, Float salary, String branchName, String name, int ID, String password, String email, String gender, String contact, String address, LocalDate DOB) {
        String path = "";
        Employee toAdd = null;
        if (usertype=="AccountsOfficer"){
            path = "AccountsOfficerObjects.bin";
            toAdd = new AccountsOfficer(designation, department, salary, branchName, name, ID, password, email, gender, contact, address, DOB);
        }
        else if (usertype=="Director"){
            path = "DirectorObjects.bin";
            toAdd = new Director(designation, department, salary, branchName, name, ID, password, email, gender, contact, address, DOB);
        }
        else if (usertype=="Doctor"){
            path = "DoctorObjects.bin";
            toAdd = new Doctor(designation, department, salary, branchName, name, ID, password, email, gender, contact, address, DOB);
        }
        else if (usertype=="HROfficer"){
            path = "HROfficerObjects.bin";
            toAdd = new HROfficer(designation, department, salary, branchName, name, ID, password, email, gender, contact, address, DOB);
        }
        else if (usertype=="LabTechnician"){
            path = "LabTechnicianObjects.bin";
            toAdd = new LabTechnician(designation, department, salary, branchName, name, ID, password, email, gender, contact, address, DOB);
        }
        else if (usertype=="Nurse"){
            path = "NurseObjects.bin";
            toAdd = new Nurse(designation, department, salary, branchName, name, ID, password, email, gender, contact, address, DOB);
        }
        else if (usertype=="Pharmacist"){
            path = "PharmacistObjects.bin";
            toAdd = new Pharmacist(designation, department, salary, branchName, name, ID, password, email, gender, contact, address, DOB);
        }
        
        
        File f = null;
        FileOutputStream fos = null;      
        ObjectOutputStream oos = null;
        File f2 = null;
        FileOutputStream fos2 = null;      
        ObjectOutputStream oos2 = null;
        try {
            f = new File(path);
            if(f.exists()){
                fos = new FileOutputStream(f,true);
                oos = new AppendableObjectOutputStream(fos);                
            }
            else{
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);               
            }
            f2 = new File("LoginInfoObjects.bin");
            if(f2.exists()){
                fos2 = new FileOutputStream(f2,true);
                oos2 = new AppendableObjectOutputStream(fos2);                
            }
            else{
                fos2 = new FileOutputStream(f2);
                oos2 = new ObjectOutputStream(fos2);               
            }
            LoginInfo toAddLogin = new LoginInfo(toAdd.getID(), toAdd.getPassword(), usertype);
            oos.writeObject(toAdd);
            oos2.writeObject(toAddLogin);
            oos.close();
            oos2.close();
            System.out.println("Employee add success");
            return true;
        } catch (IOException ex) {
            Logger.getLogger(HROfficer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(oos != null) {oos.close();oos2.close();}
            } catch (IOException ex) {
                Logger.getLogger(HROfficer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
      
    }
    
    public void MakePolicy(int serialNO, String details) {
        Policy toAdd = new Policy(serialNO, details);
        File f = null;
        FileOutputStream fos = null;      
        ObjectOutputStream oos = null;
        try {
            f = new File("PolicyObjects.bin");
            if(f.exists()){
                fos = new FileOutputStream(f,true);
                oos = new AppendableObjectOutputStream(fos);                
            }
            else{
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);               
            }
        oos.writeObject(toAdd);
                        
        } catch (IOException ex) {
            System.out.println(ex.toString());
            System.out.println("IOException | ClassNotFoundException in reading bin file");
        }
        System.out.println("Policy added successfully!");
    }

    public boolean UpdatePolicies(Policy NewPolicy) throws FileNotFoundException, IOException {
        try{
        File file = new File("PolicyObjects.bin");
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Policy> PolicyList = new ArrayList<>();
            try{
                while(true){
                    Policy tempPat = (Policy) ois.readObject();
                    System.out.println(tempPat);
                    PolicyList.add(tempPat);
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
            System.out.println(PolicyList);

            for (Policy policy : PolicyList) {
                if (policy.getNumber()==NewPolicy.getNumber()) {
                    policy.setContent(NewPolicy.getContent());
                    
                }
            }

            System.out.println(PolicyList);
            if(file.delete()){
                System.out.println("Deleted Policy File!");
                File f = new File("PolicyObjects.bin");
                FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                for (Policy policy : PolicyList) {
                    oos.writeObject(policy);
                }
                oos.close();
                System.out.println("Fixed Policy File!");
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
        
    }
}
