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
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.AppendableObjectOutputStream;
import model.Prescription;
import model.Schedule;
import model.Task;
import model.TeleQuery;

public class Doctor extends Employee implements Serializable{
    private static final long serialVersionUID = 13L;
    
    public Doctor(String designation, String department, Float salary, LocalDate DOJ, String branchName, String name, int ID, String password, String email, String gender, String contactNo, String address, LocalDate DOB) {
        super(designation, department, salary, DOJ, branchName, name, ID, password, email, gender, contactNo, address, DOB);
        this.scheduleRoster = super.scheduleRoster;
    }
    
    @Override
    public String toString() {
        return "Doctor: " + "Desig="+designation+" Dept= "+department+" Salary= "+salary+" DOJ= "+DOJ+" branch= "+branchName+" Name= "+name+" ID= "+ID+" pass= "+password+" mail= "+email+" gender"+gender+" contact= "+contactNo+" addr= "+address+" DOJ= "+DOB+" sche="+scheduleRoster;
    }
    
    public void checkAppt(){}
    public void cancelAppt(){}
    public void viewEditRecords(int PatientID){}
    public void viewPatientBillingInfo(int PatientID){}
    //    + trackLabTests(PatientID): void
    //    + answerTeleQuery(): void
    
    public boolean answerQuery(TeleQuery q){
        System.out.println("Updated Query: "+q.toString());
        try {
            File file = new File("TeleQueryObjects.bin");
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<TeleQuery> queryList = new ArrayList<>();
            try{
                while(true){
                    TeleQuery temp = (TeleQuery) ois.readObject();
                    System.out.println(temp);
                    queryList.add(temp);
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
            System.out.println(queryList);

            for (TeleQuery tempQ : queryList) {
                if (tempQ.getSenderID()==q.getSenderID() && tempQ.getQuery().equals(q.getQuery())) {
                    tempQ.setAnswer(q.getAnswer());
                    tempQ.setAnswerID(q.getAnswerID());
                }
            }

            System.out.println(queryList);
            if(file.delete()){
                System.out.println("Deleted Telequery File!");
                File f = new File("TeleQueryObjects.bin");
                FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                for (TeleQuery tq : queryList) {
                    oos.writeObject(tq);
                }
                oos.close();
                System.out.println("Fixed Telequery File!");
                return true;
            }
            else{
                System.out.println("Could not delete file");
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
            Logger.getLogger(TeleQuery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println(ex);
            Logger.getLogger(TeleQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public void assignTask(int toID, String task){
        File f = null;
        FileOutputStream fos = null; 
        ObjectOutputStream oos = null;
        try {
            f = new File("TaskObjects.bin");
            if(f.exists()){
                fos = new FileOutputStream(f,true);
                oos = new AppendableObjectOutputStream(fos);                
            }
            else{
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);               
            }
            Task newTask = new Task(this.ID, toID, task);
            oos.writeObject(newTask);
        } catch (IOException ex) {
            System.out.println(ex.toString());
        } finally {
            try {
                if(oos != null) oos.close();
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
        }
        System.out.println("Task written successfully!");
    }
    
    public static ObservableList<String> getDeptList(){
        Set<String> set = new HashSet<>();
        File f = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            f = new File("DoctorObjects.bin");
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            Doctor tempUser = null;
            try{
                System.out.println("Printing doctor objects");
                while(true){
                    tempUser = (Doctor) ois.readObject();                            
                    System.out.println(tempUser.toString());
                    set.add(tempUser.getDepartment());
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
        ObservableList<String> deptList = FXCollections.observableArrayList(set);
        return deptList;
    }
    
    public static ObservableList<String> getDocList(String dept) {
        ObservableList<String> docList = FXCollections.observableArrayList();
        File f = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            f = new File("DoctorObjects.bin");
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            Doctor tempUser = null;
            try{
                System.out.println("Printing doctor objects");
                while(true){
                    tempUser = (Doctor) ois.readObject();                            
                    System.out.println(tempUser.toString());
                    if (tempUser.getDepartment().equals(dept)){
                        docList.add(tempUser.getName()+" "+tempUser.getID());
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
        return docList;
    }
    
    public static ObservableList<Employee> getAllDoctors(){
        ObservableList<Employee> doctorList = FXCollections.observableArrayList();
        File f = null;
        FileInputStream fis = null;      
        ObjectInputStream ois = null;
        String path = "DoctorObjects.bin";
        try {
            f = new File(path);
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            User tempUser = null;
            try{
                System.out.println("Printing objects");
                while(true){
                    tempUser = (Doctor) ois.readObject();
                    System.out.println("Populate Employee (Doctor):");
                    System.out.println(tempUser.toString());
                    doctorList.add((Doctor)tempUser);
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
        System.out.println(doctorList);        
        return doctorList;
    }        

    public boolean addSchedule(Schedule newSchedule) {
        try {
            File file = new File("DoctorObjects.bin");
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Doctor> docList = new ArrayList<>();
            try{
                while(true){
                    Doctor temp = (Doctor) ois.readObject();
                    System.out.println(temp);
                    docList.add(temp);
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
            System.out.println(docList);

            for (Doctor currentDoc : docList) {
                if (currentDoc.getID()==this.ID) {
                    ArrayList<Schedule> schList = currentDoc.getScheduleRoster();
                    schList.add(newSchedule);
                    currentDoc.setScheduleRoster(schList);
                }
            }

            System.out.println(docList);
            if(file.delete()){
                System.out.println("Deleted Doctors File!");
                File f = new File("DoctorObjects.bin");
                FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                for (Doctor currentDoc : docList) {
                    oos.writeObject(currentDoc);
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
    
    public void prescribeMed(String medName, String dosage, String duration, int patientID) {
        Prescription tempPres = new Prescription(medName, dosage, duration, this.getID(), patientID);
        System.out.println("New Prescription is: "+tempPres.toString());
        File f = null;
        FileOutputStream fos = null; 
        ObjectOutputStream oos = null;
        try {
            f = new File("PrescriptionObjects.bin");
            if(f.exists()){
                fos = new FileOutputStream(f,true);
                oos = new AppendableObjectOutputStream(fos);                
            }
            else{
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);
            }
            oos.writeObject(tempPres);
        } catch (IOException ex) {
            System.out.println(ex.toString());
        } finally {
            try {
                if(oos != null) oos.close();
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
        }
        System.out.println("Prescription written successfully!");
    }

    
}
