package model;

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
import users.Patient;

public class Appointment implements Serializable {
    private static final long serialVersionUID = 13L;
    
    private int doctorID, patientID;
    private LocalDate date;
    private String time;

    public Appointment(int doctorID, int patientID, LocalDate date, String time) {
        this.doctorID = doctorID;
        this.patientID = patientID;
        this.date = date;
        this.time = time;
    }

    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Appointment: " + "doctorID=" + doctorID + ", patientID=" + patientID + ", date=" + date + ", time=" + time;
    }
    
    public static ObservableList<Appointment> getApptList(int id) {
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
                    if (tempAppt.getDoctorID()==id | tempAppt.getPatientID()==id){
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
    
    public static boolean cancelAppt(Appointment toCancel) {
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
}
