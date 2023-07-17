package model;

import java.io.Serializable;
import java.time.LocalDate;

public class LeaveApplication extends Application implements Serializable {
    private static final long serialVersionUID = 13L;
    
    private LocalDate startDate, endDate;

    public LeaveApplication(LocalDate startDate, LocalDate endDate, String type, String details, int applicantID) {
        super(type, details, applicantID);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "LeaveApplication: " + "applicantID= "+ applicantID + "type=" + type + ", details=" + details + "startDate=" + startDate + ", endDate=" + endDate;
    }
        
}