package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import main.AppendableObjectOutputStream;

public class Task implements Serializable {
    private static final long serialVersionUID = 13L;
    
    public int senderID;
    public int receiverID;
    private String taskDetails;
    private LocalDate assignedDate;

    public Task(int senderID, int receiverID, String taskDetails) {
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.taskDetails = taskDetails;
        this.assignedDate = LocalDate.now();
    }
    
    public int getSenderID() {
        return senderID;
    }

    public void setSenderID(int senderID) {
        this.senderID = senderID;
    }

    public int getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(int receiverID) {
        this.receiverID = receiverID;
    }

    public String getTaskDetails() {
        return taskDetails;
    }

    public void setTaskDetails(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    public LocalDate getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(LocalDate assignedDate) {
        this.assignedDate = assignedDate;
    }

    @Override
    public String toString() {
        return "Task: " + "senderID=" + senderID + ", receiverID=" + receiverID + ", taskDetails=" + taskDetails+ ", assignedDate=" + assignedDate;
    }
    
}
