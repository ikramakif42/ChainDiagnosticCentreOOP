package model;

import java.io.Serializable;

public class Complaint implements Serializable {
    private static final long serialVersionUID = 13L;
    
    protected int senderID;
    protected String subject, details;

    public Complaint(int senderID, String subject, String details) {
        this.senderID = senderID;
        this.subject = subject;
        this.details = details;
    }

    public int getSenderID() {
        return senderID;
    }

    public void setSenderID(int senderID) {
        this.senderID = senderID;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "EmployeeComplaint: " + "senderID=" + senderID + ", subject=" + subject + ", details=" + details;
    }

    
    
}
