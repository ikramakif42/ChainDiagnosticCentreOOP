package model;

import java.io.Serializable;
import java.time.LocalDate;

public class LoanApplication extends Application implements Serializable {
    private static final long serialVersionUID = 13L;
    
    private float amount;
    private LocalDate date;
    private String duration;

    public LoanApplication(float amount, LocalDate date, String duration, String type, String details, int applicantID) {
        super(type, details, applicantID);
        this.amount = amount;
        this.date = date;
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getApplicantID() {
        return applicantID;
    }

    public void setApplicantID(int applicantID) {
        this.applicantID = applicantID;
    }

    
    
    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "LoanApplication: " + "applicantID=" + applicantID + ", type="+ type + ", details=" + details + ", amount=" + amount + ", date=" + date + ", duration=" + duration;
    }
           
}
