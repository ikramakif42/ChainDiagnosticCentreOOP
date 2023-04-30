package model;

import java.io.Serializable;

public class LabReport extends Report implements Serializable {
    private static final long serialVersionUID = 13L;
    private int patientID;

    public LabReport(int patientID, String title, String author, String type, String body, int authorID) {
        super(title, author, type, body, authorID);
        this.patientID = patientID;
    }
    
    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }
    
    @Override
    public String toString() {
        return "Report: " + "title=" + title + ", author=" + author + ", type=" + type + ", body=" + body + ", authorID=" + authorID + ", date=" + date + ", patientID" +patientID;
    }

    public String toStringDisplay() {
        String show = "Report title:" + "\n";
        show = show + this.title + "\n";
        show = show + "Report author:" + "\n";
        show = show + this.author + "\n";
        show = show + "Report date" + "\n";
        show = show + this.date + "\n";
        show = show + "Report details:" + "\n";
        show = show + this.body + "\n";
        return show;
    }
}
