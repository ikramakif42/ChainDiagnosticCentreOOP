package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import main.AppendableObjectOutputStream;

public class Task implements Serializable {
    private static final long serialVersionUID = 13L;
    
    public int senderID;
    public int receiverID;
    private String taskDetails;

    public Task(int senderID, int receiverID, String taskDetails) {
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.taskDetails = taskDetails;
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

    @Override
    public String toString() {
        return "Task: " + "senderID=" + senderID + ", receiverID=" + receiverID + ", taskDetails=" + taskDetails;
    }
    
}
