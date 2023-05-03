package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Bill implements Serializable {
    private static final long serialVersionUID = 13L;
    
    private LocalDate createdDate, dueDate;
    private boolean paidStatus;
    private Float amount;
    private String details;
    private int patientID;
    private int billedByID;

    public Bill(LocalDate createdDate, LocalDate dueDate, boolean paidStatus, Float amount, String details, int patientID, int billedByID) {
        this.createdDate = createdDate;
        this.dueDate = dueDate;
        this.paidStatus = paidStatus;
        this.amount = amount;
        this.details = details;
        this.patientID = patientID;
        this.billedByID = billedByID;
    }



    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isPaidStatus() {
        return paidStatus;
    }

    
    public void setPaidStatus(boolean paidStatus) {
        this.paidStatus = paidStatus;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public int getBilledByID() {
        return billedByID;
    }

    public void setBilledByID(int billedByID) {
        this.billedByID = billedByID;
    }

    @Override
    public String toString() {
        return "Bill: " + "createdDate=" + createdDate + ", dueDate=" + dueDate + ", paidStatus=" + paidStatus + ", amount=" + amount + ", details=" + details + ", patientID=" + patientID + ", billedByID=" + billedByID;
    }
    
    public static ObservableList<Bill> getAllPendingBills(){
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
                    if (!(tempBill.paidStatus)){
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
    }
}