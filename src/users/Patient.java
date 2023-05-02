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
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.AppendableObjectOutputStream;
import model.Appointment;
import model.Bill;
import model.Complaint;
import model.LabReport;
import model.LoginInfo;
import model.Prescription;
import model.TeleQuery;
import views.patient.PatientSignUpController;

public class Patient extends User implements Serializable{
    private static final long serialVersionUID = 13L;
    private ArrayList<String> medicalRecords = new ArrayList<>();
    
    public Patient(String name, int ID, String password, String email, String gender, String contactNo, String address, LocalDate DOB) {
        super(name, ID, password, email, gender, contactNo, address, DOB);
    }

    public ArrayList<String> getMedicalRecords() {
        return medicalRecords;
    }

    public void setMedicalRecords(ArrayList<String> medicalRecords) {
        this.medicalRecords = medicalRecords;
    }

    @Override
    public String toString() {
        return "Patient: name="+name+" ID="+ID+" pass="+password+" mail="+email+" gender="+gender+" contact="+contactNo+" addr="+address+" DOB="+DOB+" medicalRecords="+medicalRecords;
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
                    if (tempAppt.getPatientID()==this.getID()){
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
    
    //Goal 2
    public ObservableList<Prescription> getPrescriptions() {
        ObservableList<Prescription> prescriptionList = FXCollections.observableArrayList();
        File f = null;
        FileInputStream fis = null;      
        ObjectInputStream ois = null;
        String path = "PrescriptionObjects.bin";
        try {
            f = new File(path);
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            Prescription temp = null;
            try{
                System.out.println("Printing objects");
                while(true){
                    temp = (Prescription) ois.readObject();
                    System.out.println("Populated prescription: "+temp.toString());
                    if (temp.getPatientID()==this.getID()){
                        prescriptionList.add(temp);
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
        System.out.println(prescriptionList);
        return prescriptionList;
    }
    public boolean updatePersonalInfo(String newName, String newEmail, String newAddr, String newContactNo){
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
                if (currentPat.getID()==this.ID) {
                    currentPat.setName(newName);
                    currentPat.setEmail(newEmail);
                    currentPat.setAddress(newAddr);
                    currentPat.setContactNo(newContactNo);
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
    
    //Goal 3
    public ObservableList<LabReport> getReportList() {
        ObservableList<LabReport> reportList = FXCollections.observableArrayList();
        File f = null;
        FileInputStream fis = null;      
        ObjectInputStream ois = null;
        String path = "LabReportObjects.bin";
        try {
            f = new File(path);
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            LabReport temp = null;
            try{
                System.out.println("Printing objects");
                while(true){
                    temp = (LabReport) ois.readObject();
                    System.out.println("Populated lab report: "+temp.toString());
                    if (temp.getPatientID()==this.getID()){
                        reportList.add(temp);
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
        System.out.println(reportList);
        return reportList;
    }
    
    //Goal 4
    public ObservableList<Bill> getBillList() {
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
                    if (temp.getPatientID()==this.getID()){
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
    
    //Goal 5
    public boolean submitRefill(Prescription refill) {
        System.out.println("New refill is: "+refill.toString());
        File f = null;
        FileOutputStream fos = null; 
        ObjectOutputStream oos = null;
        try {
            f = new File("RefillRequests.bin");
            if(f.exists()){
                fos = new FileOutputStream(f,true);
                oos = new AppendableObjectOutputStream(fos);                
            }
            else{
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);
            }
            oos.writeObject(refill);
            System.out.println("Refill Request submitted successfully!");
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
    public boolean submitComplaint(String subject, String details){
        Complaint complaint = new Complaint(this.getID(), subject, details);
        System.out.println("New complaint is: "+complaint.toString());
        File f = null;
        FileOutputStream fos = null; 
        ObjectOutputStream oos = null;
        try {
            f = new File("PatientComplaintObjects.bin");
            if(f.exists()){
                fos = new FileOutputStream(f,true);
                oos = new AppendableObjectOutputStream(fos);                
            }
            else{
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);
            }
            oos.writeObject(complaint);
            System.out.println("Complaint written successfully!");
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
    
    //Goal 7
    public boolean writeAppt(int docID, LocalDate date, String time) {
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
            System.out.println("Appt written successfully!");
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
    
    //Goal 8
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
    
    //Goal 9
    public boolean writeQuery(String usertype, String query) throws IOException {
        TeleQuery tq = new TeleQuery(this.ID, usertype, query);
        System.out.println("New TeleQuery is: "+tq.toString());
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
            oos.writeObject(tq);
            System.out.println("TeleQuery written successfully!");
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
    
    //Goal 10
    public static boolean addPatient(Patient toAdd) {
        File f = null;
        FileOutputStream fos = null;      
        ObjectOutputStream oos = null;
        File f2 = null;
        FileOutputStream fos2 = null;      
        ObjectOutputStream oos2 = null;
        try {
            f = new File("PatientObjects.bin");
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
            LoginInfo toAddLogin = new LoginInfo(toAdd.getID(), toAdd.getPassword(), "Patient");
            oos.writeObject(toAdd);
            oos2.writeObject(toAddLogin);
            oos.close();
            oos2.close();
            System.out.println("Sign up success");
            return true;
        } catch (IOException ex) {
            Logger.getLogger(PatientSignUpController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(oos != null) {oos.close();oos2.close();}
            } catch (IOException ex) {
                Logger.getLogger(PatientSignUpController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    
    //Helper methods
    public LocalDate getLatestAppt(ObservableList<Appointment> apptList) {
        ArrayList<LocalDate> dateList = new ArrayList<>();
        for (Appointment appt: apptList){
            dateList.add(appt.getDate());
        }
        System.out.println(dateList);
        LocalDate latestAppt = Collections.max(dateList, Comparator.naturalOrder());
        System.out.println("Latest appt: "+this.getID()+" "+latestAppt);
        return latestAppt;
    }
    public static ObservableList<Patient> getPatients(){
        ObservableList<Patient> patientList = FXCollections.observableArrayList();
        File f = null;
        FileInputStream fis = null;      
        ObjectInputStream ois = null;
        String path = "PatientObjects.bin";
        try {
            f = new File(path);
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            User tempUser = null;
            try{
                System.out.println("Printing objects");
                while(true){
                    tempUser = (Patient) ois.readObject();
                    System.out.println("Populate patient:");
                    System.out.println(tempUser.toString());
                    patientList.add((Patient)tempUser);
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
        System.out.println(patientList);
        return patientList;
    }
    
}