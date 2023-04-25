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
import model.Appointment;
import model.TeleQuery;

public class Patient extends User implements Serializable{
    private static final long serialVersionUID = 13L;
    
    public Patient(String name, int ID, String password, String email, String gender, String contactNo, String address, LocalDate DOB) {
        super(name, ID, password, email, gender, contactNo, address, DOB);
    }

    @Override
    public String toString() {
        return "Patient: name="+name+" ID="+ID+" pass="+password+" mail="+email+" gender="+gender+" contact="+contactNo+" addr="+address+" DOB="+DOB;
    }
    
    public boolean updatePersonalInfo(String newName, String newEmail, String newAddr, String newContactNo){
        try {
            File file = new File("PatientObjects.bin");
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Patient> appts = new ArrayList<>();
            try{
                while(true){
                    Patient tempPat = (Patient) ois.readObject();
                    System.out.println(tempPat);
                    appts.add(tempPat);
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
            System.out.println(appts);

            for (Patient currentPat : appts) {
                if (currentPat.getID()==this.ID) {
                    currentPat.setName(newName);
                    currentPat.setEmail(newEmail);
                    currentPat.setAddress(newAddr);
                    currentPat.setContactNo(newContactNo);
                }
            }

            System.out.println(appts);
            if(file.delete()){
                System.out.println("Deleted Patients File!");
                File f = new File("PatientObjects.bin");
                FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                for (Patient currentPat : appts) {
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
            Logger.getLogger(Patient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println(ex);
            Logger.getLogger(Patient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public ObservableList<TeleQuery> getQueryList() {
        ObservableList<TeleQuery> queryList = FXCollections.observableArrayList();
        File f = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        String path = "TeleQueryObjects.bin";
        try {
            f = new File(path);
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            TeleQuery tempQuery = null;
            try {
                System.out.println("Printing TQ objects");
                while (true) {
                    tempQuery = (TeleQuery) ois.readObject();
                    System.out.println("Populated query: " + tempQuery.getSenderID() + ", " + tempQuery.getQuery());
                    System.out.println(this.ID);
                    if (tempQuery.getSenderID() == this.ID) {
                        queryList.add(tempQuery);
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println(e.toString());
                System.out.println("IOException | ClassNotFoundException in reading bin file");
            }
            System.out.println("End of file\n");
        } catch (IOException ex) {
            System.out.println("IOException on entire file handling");
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException ex) {
            }
        }
        return queryList;
    }
    
    public void writeQuery(String usertype, String query) throws IOException {
        TeleQuery q = new TeleQuery(this.ID, usertype, query);
        File f = null;
        FileOutputStream fos = null;      
        ObjectOutputStream oos = null;
        try {
            f = new File("TeleQueryObjects.bin");
            if(f.exists()){
                fos = new FileOutputStream(f,true);
                oos = new AppendableObjectOutputStream(fos);                
            }
            else{
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);               
            }
            oos.writeObject(q);
        }
        catch (IOException ex){System.out.println(ex);}
        System.out.println("Query written");
        oos.close();
    }
    
    public void writeAppt(int docID, LocalDate date, String time) {
        Appointment newAppt = new Appointment(docID, this.getID(), date, time);
        System.out.println("New Appt is: "+newAppt.toString());
        File f = null;
        FileOutputStream fos = null; 
        ObjectOutputStream oos = null;
        try {
            f = new File("AppointmentObjects.bin");
            if(f.exists()){
                fos = new FileOutputStream(f,true);
                oos = new AppendableObjectOutputStream(fos);                
            }
            else{
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);
            }
            oos.writeObject(newAppt);
        } catch (IOException ex) {
            System.out.println(ex.toString());
        } finally {
            try {
                if(oos != null) oos.close();
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
        }
        System.out.println("Appt written successfully!");
    }
    
    public void cancelAppt(Appointment apptToCancel){
        try {
            File file = new File("AppointmentObjects.bin");
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Appointment> appts = new ArrayList<>();
            try{
                while(true){
                    Appointment tempAppt = (Appointment) ois.readObject();
                    System.out.println(tempAppt);
                    if (tempAppt.getPatientID()==this.getID()){
                        if (tempAppt.getDoctorID()==apptToCancel.getDoctorID()){
                            if (tempAppt.getDate().equals(apptToCancel.getDate())){
                                if (tempAppt.getTime().equals(apptToCancel.getTime())){
                                    continue;
                                }
                            }
                        }
                    }
                    appts.add(tempAppt);
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

            System.out.println(appts);
            if(file.delete()){
                System.out.println("Deleted Patients File!");
                File f = new File("AppointmentObjects.bin");
                FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                for (Appointment tempAppt : appts) {
                    oos.writeObject(tempAppt);
                }
                oos.close();
                System.out.println("Fixed Patients File!");
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
    }
    
//    + submitComplaint(): void
//    + viewLabReports(): void
//    + viewPayBills(): void
//    + requestRefills(): void
    
}
