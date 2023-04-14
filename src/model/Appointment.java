package model;

import java.time.LocalDate;

public class Appointment {
    String doctorName, patientName;
    LocalDate date;
    String time;

    public Appointment(String doctorName, String patientName, LocalDate date, String time) {
        this.doctorName = doctorName;
        this.patientName = patientName;
        this.date = date;
        this.time = time;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
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
        
    
    // comment example
}
