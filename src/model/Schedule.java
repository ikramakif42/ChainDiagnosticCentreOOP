package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Schedule implements Serializable {
    private static final long serialVersionUID = 13L;
    
    private LocalDate day;
    private String time, task;
    private int assignedToID;

    public Schedule(LocalDate day, String time, String task, int assignedToID) {
        this.day = day;
        this.time = time;
        this.task = task;
        this.assignedToID = assignedToID;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public int getAssignedToID() {
        return assignedToID;
    }

    public void setAssignedToID(int assignedToID) {
        this.assignedToID = assignedToID;
    }

    @Override
    public String toString() {
        return "Schedule: " + "day=" + day + ", time=" + time + ", task=" + task + ", assignedToID=" + assignedToID;
    }
    
    
    
}
