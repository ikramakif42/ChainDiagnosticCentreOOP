package users;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.AppendableObjectOutputStream;
import model.Bill;
import model.Schedule;

public class AccountsOfficer extends Employee implements Serializable {
    
    private static final long serialVersionUID = 13L;
    
    public AccountsOfficer(String designation, String department, Float salary, LocalDate DOJ, String branchName, String name, int ID, String password, String email, String gender, String contactNo, String address, LocalDate DOB) {
        super(designation, department, salary, DOJ, branchName, name, ID, password, email, gender, contactNo, address, DOB);
        this.scheduleRoster = super.scheduleRoster;
    }

    
    @Override
    public String toString() {
        return "Accounts Officer: " + "Desig="+designation+" Dept= "+department+" Salary= "+salary+" DOJ= "+DOJ+" branch= "+branchName+" Name= "+name+" ID= "+ID+" pass= "+password+" mail= "+email+" gender"+gender+" contact= "+contactNo+" addr= "+address+" DOJ= "+DOB+" sche="+scheduleRoster;
    }
    
    public void viewPatientBillingInfo(){};
    public void editBillInfo(){};
    public void manageRestockOrders(){};
    public void viewPastRecords(){};
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

    public void makeBill(LocalDate dueDate, float amount, String details, int patientID) {
        Bill toAdd = new Bill(dueDate, amount, details, patientID, this.getID());
        File f = null;
        FileOutputStream fos = null;      
        ObjectOutputStream oos = null;
        try {
            f = new File("BillObjects.bin");
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
        System.out.println("Bill added successfully!");
    }

    
}
