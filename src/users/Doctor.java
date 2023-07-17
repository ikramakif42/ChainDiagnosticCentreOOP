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
import model.Appointment;
import model.Bill;
import model.LabOrder;
import model.Prescription;
import model.Schedule;
import model.Task;
import model.TeleQuery;

public class Doctor extends Employee implements Serializable{
    private static final long serialVersionUID = 13L;
    
    public Doctor(String designation, String department, Float salary, String branchName, String name, int ID, String password, String email, String gender, String contactNo, String address, LocalDate DOB) {
        super(designation, department, salary, branchName, name, ID, password, email, gender, contactNo, address, DOB);
        this.scheduleRoster = super.scheduleRoster;
    }
    
    @Override
    public String toString() {
        return "Doctor: " + "Desig="+designation+" Dept= "+department+" Salary= "+salary+" DOJ= "+DOJ+" branch= "+branchName+" Name= "+name+" ID= "+ID+" pass= "+password+" mail= "+email+" gender"+gender+" contact= "+contactNo+" addr= "+address+" DOJ= "+DOB+" sche="+scheduleRoster;
    }
    
    //Goal 1
    public ObservableList<Appointment> getApptList() {
        ObservableList<Appointment> apptList = FXCollections.observableArrayList();
        File f = null;
        FileInputStream fis = null;      
        ObjectInputStream ois = null;
        String path = "AppointmentObjects.bin";
        try {
            f = new File(path);
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            Appointment tempAppt = null;
            try{
                System.out.println("Printing Appt objects");
                while(true){
                    tempAppt = (Appointment) ois.readObject();
                    System.out.println("Populated appt: "+tempAppt.getDoctorID()+", "+tempAppt.getPatientID());
                    if (tempAppt.getDoctorID()==this.getID()){
                        apptList.add(tempAppt);
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
        return apptList;
    }
    
    //Goal 2
    public boolean cancelAppt(Appointment toCancel) {
        try {
            File file = new File("AppointmentObjects.bin");
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Appointment> apptList = new ArrayList<>();
            try{
                while(true){
                    Appointment temp = (Appointment) ois.readObject();
                    System.out.println(temp);
                    apptList.add(temp);
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
            System.out.println(apptList);
            
            ArrayList<Appointment> newApptList = new ArrayList<>();
            for (Appointment tempAppt : apptList) {
                if (tempAppt.getPatientID()==toCancel.getPatientID()) {
                    if (tempAppt.getDoctorID()==toCancel.getDoctorID()){
                        if (tempAppt.getDate().equals(toCancel.getDate())){
                            if (tempAppt.getTime().equals(toCancel.getTime())){
                                continue;
                            }
                        }
                    }
                }
                newApptList.add(tempAppt);
            }

            System.out.println(newApptList);
            if(file.delete()){
                System.out.println("Deleted Appointments File!");
                File f = new File("AppointmentObjects.bin");
                FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                for (Appointment appt : newApptList) {
                    oos.writeObject(appt);
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
    
    //Goal 3
    public boolean prescribeMed(String medName, String dosage, String duration, int patientID) {
        Prescription tempPres = new Prescription(medName, dosage, duration, this.getID(), patientID);
        System.out.println("New Prescription is: "+tempPres.toString());
        File f = null;
        FileOutputStream fos = null; 
        ObjectOutputStream oos = null;
        File f2 = null;
        FileOutputStream fos2 = null; 
        ObjectOutputStream oos2 = null;
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
            f2 = new File("RefillRequests.bin");
            if(f2.exists()){
                fos2 = new FileOutputStream(f2,true);
                oos2 = new AppendableObjectOutputStream(fos2);                
            }
            else{
                fos2 = new FileOutputStream(f2);
                oos2 = new ObjectOutputStream(fos2);
            }
            oos.writeObject(tempPres);
            oos2.writeObject(tempPres);
            System.out.println("Prescription written successfully!");
            oos.close();
            oos2.close();
            return true;
        } catch (IOException ex) {
            System.out.println(ex.toString());
        } finally {
            try {
                if(oos != null) {oos.close();oos2.close();}
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
        }
        return false;
    }
    
    //Goal 4
    public boolean submitLabOrder(int patientID, String testName, String priority) {
        LabOrder newOrder = new LabOrder(patientID, this.getID(), testName, priority);
        System.out.println("New Lab Order is: "+newOrder.toString());
        File f = null;
        FileOutputStream fos = null; 
        ObjectOutputStream oos = null;
        try {
            f = new File("LabOrderObjects.bin");
            if(f.exists()){
                fos = new FileOutputStream(f,true);
                oos = new AppendableObjectOutputStream(fos);                
            }
            else{
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);
            }
            oos.writeObject(newOrder);
            oos.close();
            System.out.println("Lab Order submitted successfully!");
            return true;
        } catch (IOException ex) {
            System.out.println(ex.toString());
        } finally {
            try {
                if(oos != null) oos.close();
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
        }
        return false;
    }
    
    //Goal 5
    public boolean assignTask(int toID, String task){
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
            System.out.println("Task written successfully!");
            oos.close();
            return true;
        } catch (IOException ex) {
            System.out.println(ex.toString());
        } finally {
            try {
                if(oos != null) oos.close();
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
        }
        return false;
    }
    
    //Goal 6
    public boolean updatePatientRecord(int patientID, String newRecord) {
        try {
            File file = new File("PatientObjects.bin");
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Patient> patientList = new ArrayList<>();
            try{
                while(true){
                    Patient tempPat = (Patient) ois.readObject();
                    System.out.println(tempPat);
                    patientList.add(tempPat);
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
            System.out.println(patientList);

            for (Patient currentPat : patientList) {
                if (currentPat.getID()==patientID) {
                    ArrayList<String> newList = currentPat.getMedicalRecords();
                    newList.add(newRecord);
                    currentPat.setMedicalRecords(newList);
                }
            }

            System.out.println(patientList);
            if(file.delete()){
                System.out.println("Deleted Patients File!");
                File f = new File("PatientObjects.bin");
                FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                for (Patient currentPat : patientList) {
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
    
    //Goal 7
    public ObservableList<Bill> getBillList(int patientID) {
        ObservableList<Bill> billList = FXCollections.observableArrayList();
        File f = null;
        FileInputStream fis = null;      
        ObjectInputStream ois = null;
        String path = "BillObjects.bin";
        try {
            f = new File(path);
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            Bill temp = null;
            try{
                System.out.println("Printing objects");
                while(true){
                    temp = (Bill) ois.readObject();
                    System.out.println("Populated bill: "+temp.toString());
                    if (temp.getPatientID()==patientID){
                        billList.add(temp);
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
    
    //Goal 8
    public static ObservableList<TeleQuery> getQueryList() {
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
            try{
                System.out.println("Printing TQ objects");
                while(true){
                    tempQuery = (TeleQuery) ois.readObject();
                    System.out.println("Populated query: "+tempQuery.getSenderID()+", "+tempQuery.getQuery());
                    if (tempQuery.getUsertype().equals("Doctor")){
                        queryList.add(tempQuery);
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
        return queryList;
    }
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
    
    //Goal 9
    public ObservableList<Patient> getPats(ObservableList<Appointment> apptList) {
        ObservableList<Patient> patList = FXCollections.observableArrayList();
        for (Appointment appt : apptList){
            Patient pat = (Patient) User.getInstance(appt.getPatientID(), "Patient");
            patList.add(pat);
        }
        return patList;
    }
    
    //Helper methods
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
    
}
