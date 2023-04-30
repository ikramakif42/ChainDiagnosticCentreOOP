package model;

import java.io.Serializable;

public class LabOrder implements Serializable {
    private static final long serialVersionUID = 13L;
    private int patientID, doctorID;
    private String testName, priority;

    public LabOrder(int patientID, int doctorID, String testName, String priority) {
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.testName = testName;
        this.priority = priority;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "LabOrder: " + "patientID=" + patientID + ", testName=" + testName + ", priority=" + priority;
    }
}
