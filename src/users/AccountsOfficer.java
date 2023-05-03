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
import model.Bill;
import model.Policy;
import model.Schedule;

public class AccountsOfficer extends Employee implements Serializable {
    
    private static final long serialVersionUID = 13L;
    
    public AccountsOfficer(String designation, String department, Float salary, String branchName, String name, int ID, String password, String email, String gender, String contactNo, String address, LocalDate DOB) {
        super(designation, department, salary, branchName, name, ID, password, email, gender, contactNo, address, DOB);
        this.scheduleRoster = super.scheduleRoster;
    }

    
    @Override
    public String toString() {
        return "Accounts Officer: " + "Desig="+designation+" Dept= "+department+" Salary= "+salary+" DOJ= "+DOJ+" branch= "+branchName+" Name= "+name+" ID= "+ID+" pass= "+password+" mail= "+email+" gender"+gender+" contact= "+contactNo+" addr= "+address+" DOJ= "+DOB+" sche="+scheduleRoster;
    }
    
    public static ObservableList<Bill> viewPatientBillingInfo(){
        ObservableList<Bill> billList = FXCollections.observableArrayList();
        File f = null;
        FileInputStream fis = null;      
        ObjectInputStream ois = null;
        String path = "BillObjects.bin";
        try {
            f = new File(path);
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            Bill tempBill = null;
            try{
                System.out.println("Printing objects");
                while(true){
                    tempBill = (Bill) ois.readObject();
                    if (!(tempBill.isPaidStatus())){
                        billList.add((Bill)tempBill);
                    }
                    else{
                    System.out.println("Populated bill: "+tempBill.toString());

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
        System.out.println(billList);        
        return billList;    
    };
        
    public static boolean makeBill(LocalDate createdDate, LocalDate dueDate, boolean paidStatus, float amount, String details, int patientID, int billedByID) {
        Bill toAdd = new Bill(createdDate, dueDate, paidStatus, amount, details, patientID, billedByID);
        File f = null;
        FileOutputStream fos = null;      
        ObjectOutputStream oos = null;
        try {
            f = new File("BillObjects.bin");
            if(f.exists()){
                fos = new FileOutputStream(f,true);
                oos = new AppendableObjectOutputStream(fos);
                oos.writeObject(toAdd);
                System.out.println("Bill added successfully!");
                return true;
            }
            else{
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);
                oos.writeObject(toAdd);
                System.out.println("Bill added successfully!");
                return true;
            }

                        
        } catch (IOException ex) {
            System.out.println(ex.toString());
            System.out.println("IOException | ClassNotFoundException in reading bin file");
            return false;
        }

    }    
    
    public static boolean editBillInfo(Bill curBill, LocalDate newDue, boolean newStatus, float newAmount, String newDetails){
            try {
            String path = "BillObjects.bin";
            File file = new File(path);
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Bill> b = new ArrayList<>();
            
            try{
                while(true){
                    Bill tempB = (Bill) ois.readObject();
                    System.out.println(tempB);
                    b.add(tempB);
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
            System.out.println(b);

            for (Bill currentB : b) {
                boolean detailCheck = currentB.getDetails().equals(curBill.getDetails());
                boolean dateCheck = currentB.getCreatedDate().equals(curBill.getCreatedDate());
                boolean dueCheck = currentB.getDueDate().equals(curBill.getDueDate());
                boolean amountCheck = currentB.getAmount().equals(curBill.getAmount());
                boolean pIDCheck = currentB.getPatientID() == curBill.getPatientID();
                boolean bIDCheck = currentB.getBilledByID() == curBill.getBilledByID();
                boolean statusCheck = currentB.isPaidStatus() == curBill.isPaidStatus();
                
                if (detailCheck & dateCheck & dueCheck & amountCheck & pIDCheck & bIDCheck & statusCheck){
                    currentB.setDueDate(newDue);
                    currentB.setPaidStatus(newStatus);
                    currentB.setAmount(newAmount);
                    currentB.setDetails(newDetails);
                }
            }

            System.out.println(b);
            if(file.delete()){
                System.out.println("Deleted File!");
                File f = new File(path);
                FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                for (Bill currentB : b) {
                    oos.writeObject(currentB);
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
    
    public void manageRestockOrders(){};
    
    public static ObservableList<Bill> viewPastRecords(){
        ObservableList<Bill> billList = FXCollections.observableArrayList();
        File f = null;
        FileInputStream fis = null;      
        ObjectInputStream ois = null;
        String path = "BillObjects.bin";
        try {
            f = new File(path);
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            Bill tempBill = null;
            try{
                System.out.println("Printing objects");
                while(true){
                    tempBill = (Bill) ois.readObject();
                    if (!(tempBill.isPaidStatus())){
                       ;
                    }
                    else{
                        System.out.println("Populated bill: "+tempBill.toString());
                        billList.add((Bill)tempBill);

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
        System.out.println(billList);        
        return billList;    
    };
    
    public void createReport(){};
    public void updateOldReport(){};
    public void approveLoanApplications(){};
    public void updateSalaries(){};
    
    public static ObservableList<Employee> getAllAccounts(){
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
    
}
