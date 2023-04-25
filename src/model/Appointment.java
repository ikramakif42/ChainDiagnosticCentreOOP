package model;

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
    
    public static void writeAppt(Appointment newAppt) {
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
    
}
