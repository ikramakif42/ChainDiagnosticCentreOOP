package model;

import java.io.Serializable;

public abstract class Application implements Serializable {
    private static final long serialVersionUID = 13L;
    
    protected String type, details;
    protected int applicantID;

    public Application(String type, String details, int applicantID) {
        this.type = type;
        this.details = details;
        this.applicantID = applicantID;
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

    @Override
    public String toString() {
        return "Application: " + "type=" + type + ", details=" + details + ", applicantID="+ applicantID;
    }
    
}
