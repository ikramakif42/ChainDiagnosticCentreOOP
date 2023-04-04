package model;

public class Prescription {
    String medName, dosage;
    int duration;

    public Prescription(String medName, String dosage, int duration) {
        this.medName = medName;
        this.dosage = dosage;
        this.duration = duration;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
    
}
