package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Resignation implements Serializable {
    private static final long serialVersionUID = 13L;
    
    private int applicantID;
    private LocalDate date;
    private String details;

    public Resignation(int applicantID, LocalDate date, String details) {
        this.applicantID = applicantID;
        this.date = date;
        this.details = details;
    }

    public int getApplicantID() {
        return applicantID;
    }

    public void setApplicantID(int applicantID) {
        this.applicantID = applicantID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "Resignation: " + "applicantID=" + applicantID + ", date=" + date + ", details=" + details;
    }
}