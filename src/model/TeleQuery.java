package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TeleQuery implements Serializable{
    private static final long serialVersionUID = 13L;
    
    public String usertype;
    private String query, answer;
    private int senderID;
    private int answerID;

    public TeleQuery(int id, String usertype, String query) {
        this.senderID = id;
        this.usertype = usertype;
        this.query = query;

        this.answerID = 0;
        this.answer = "";
    }

    public String getUsertype() {
        return usertype;
    }

    public void setID(String usertype) {
        this.usertype = usertype;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getSenderID() {
        return senderID;
    }

    public void setSenderID(int senderID) {
        this.senderID = senderID;
    }

    public int getAnswerID() {
        return answerID;
    }

    public void setAnswerID(int answerID) {
        this.answerID = answerID;
    }

    @Override
    public String toString() {
        return "TeleQuery=" + "usertype=" + usertype + ", query=" + query + ", answer=" + answer + ", senderID=" + senderID + ", answerID=" + answerID;
    }
    
    public boolean isPending(){
        if (this.answer.isEmpty()){return true;}
        else {return false;}
    }
    
}