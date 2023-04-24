package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Schedule implements Serializable {
    private static final long serialVersionUID = 13L;
    
    private LocalDate day;
    private String time, task, assignedTo;

    public Schedule(LocalDate day, String time, String task, String assignedTo) {
        this.day = day;
        this.time = time;
        this.task = task;
        this.assignedTo = assignedTo;
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

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }
    
}
