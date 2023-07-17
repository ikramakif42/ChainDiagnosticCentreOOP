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
import model.Bill;
import model.Prescription;
import model.Schedule;

public class Pharmacist extends Employee implements Serializable{
    private static final long serialVersionUID = 13L;

    public static ObservableList<Bill> getPatientBills(int id) {
        
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
                    if (tempBill.getPatientID()== id){
                    System.out.println("Populated bill: "+tempBill.toString());
                    billList.add(tempBill);
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
    }
    
    public Pharmacist(String designation, String department, Float salary, String branchName, String name, int ID, String password, String email, String gender, String contactNo, String address, LocalDate DOB) {
        super(designation, department, salary, branchName, name, ID, password, email, gender, contactNo, address, DOB);
        this.scheduleRoster = super.scheduleRoster;
    }

    @Override
    public String toString() {
        return "Pharmacist: " + "Desig="+designation+" Dept= "+department+" Salary= "+salary+" DOJ= "+DOJ+" branch= "+branchName+" Name= "+name+" ID= "+ID+" pass= "+password+" mail= "+email+" gender"+gender+" contact= "+contactNo+" addr= "+address+" DOJ= "+DOB+" sche="+scheduleRoster;
    }

    public static ObservableList<Employee> getAllPharmacists(){
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

    public boolean resolveRefillRequest(Prescription selectedOrder) {
        
        try {
            File file = new File("RefillRequests.bin");
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Prescription> refillList = new ArrayList<>();
            try{
                while(true){
                    Prescription tempRefill = (Prescription) ois.readObject();
                    System.out.println(tempRefill);
                    refillList.add(tempRefill);
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
            System.out.println(refillList);
            ArrayList<Prescription> newRefillList = new ArrayList<>();

            for (Prescription currentRefill : refillList) {
                if (currentRefill.getMedName().equals(selectedOrder.getMedName())) {
                    if(currentRefill.getDosage().equals(selectedOrder.getDosage())){
                        if(currentRefill.getDuration().equals(selectedOrder.getDuration())){
                            if(currentRefill.getDoctorID()==(selectedOrder.getDoctorID())){
                                if(currentRefill.getPatientID()==(selectedOrder.getPatientID())){
                                    continue;
                                }
                            }
                        }
                    }
                }
                newRefillList.add(currentRefill);
            }

            System.out.println(newRefillList);
            if(file.delete()){
                System.out.println("Deleted Patients File!");
                File f = new File("RefillRequests.bin");
                FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                for (Prescription currentPat : newRefillList) {
                    oos.writeObject(currentPat);
                }
                oos.close();
                System.out.println("Fixed Patients File!");
                return true;
            }
            else{
                System.out.println("Could not delete file");
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
            Logger.getLogger(Prescription.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println(ex);
            Logger.getLogger(Prescription.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false; //To change body of generated methods, choose Tools | Templates.
    }

    
    
}