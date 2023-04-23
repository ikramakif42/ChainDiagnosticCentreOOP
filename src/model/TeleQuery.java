package model;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.AppendableObjectOutputStream;

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
    
    
    //Move to doctor and pharmacist
    public static ObservableList<TeleQuery> getQueryList(String usertype) {
        ObservableList<TeleQuery> queryList = FXCollections.observableArrayList();
        File f = null;
        FileInputStream fis = null;      
        ObjectInputStream ois = null;
        String path = "TeleQueryObjects.bin";
        try {
            f = new File(path);
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            TeleQuery tempQuery = null;
            try{
                System.out.println("Printing TQ objects");
                while(true){
                    tempQuery = (TeleQuery) ois.readObject();
                    System.out.println("Populated query: "+tempQuery.getSenderID()+", "+tempQuery.getQuery());
                    if (tempQuery.getUsertype().equals(usertype)){
                        queryList.add(tempQuery);
                    }
                }
            }
            catch(IOException | ClassNotFoundException e){
                System.out.println(e.toString());
                System.out.println("IOException | ClassNotFoundException in reading bin file");
            }
            System.out.println("End of file\n");
        } catch (IOException ex) {
            System.out.println("IOException on entire file handling");
        }
        finally {
            try {
                if(ois != null) ois.close();
            } catch (IOException ex) { }
        }
        return queryList;
    }
}