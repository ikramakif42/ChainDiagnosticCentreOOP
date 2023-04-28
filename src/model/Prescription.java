package model;

import java.io.Serializable;

public class Prescription implements Serializable {
    private static final long serialVersionUID = 13L;
    
    private String medName, dosage;
    private String duration;
    private int doctorID, patientID;

    public Prescription(String medName, String dosage, String duration, int doctorID, int patientID) {
        this.medName = medName;
        this.dosage = dosage;
        this.duration = duration;
        this.doctorID = doctorID;
        this.patientID = patientID;
    }

    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
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

    @Override
    public String toString() {
        return "Prescription: " + "medName=" + medName + ", dosage=" + dosage + ", duration=" + duration + ", doctorID=" + doctorID + ", patientID=" + patientID;
    }
}
