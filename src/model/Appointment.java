package model;

import java.io.Serializable;
import java.time.LocalDate;

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
    
}
