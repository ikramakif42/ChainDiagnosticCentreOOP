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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Report implements Serializable {
    private static final long serialVersionUID = 13L;
    
    public String title, author, type, body;
    public int authorID;
    public LocalDate date;

    
    public Report(String title, String author, String type, String body, int authorID) {
        this.title = title;
        this.author = author;
        this.type = type;
        this.body = body;
        this.authorID = authorID;
        this.date = LocalDate.now();
    }    
    
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public static ObservableList<Report> getAllBranchReports(){
        ObservableList<Report> branchReportList = FXCollections.observableArrayList();
        File f = null;
        FileInputStream fis = null;      
        ObjectInputStream ois = null;
        String path = "ReportObjects.bin";
        try {
            f = new File(path);
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            Report tempBr = null;
            try{
                System.out.println("Printing objects");
                while(true){
                    tempBr = (Report) ois.readObject();
  
                    if (tempBr.type.equals("Branch")){
                        System.out.println(tempBr);
                    branchReportList.add(tempBr);
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
        System.out.println(branchReportList);
        return branchReportList;
    }
    
    
    public static ObservableList<Report> getAllHRFinanceReports(){
        ObservableList<Report> branchReportList = FXCollections.observableArrayList();
        File f = null;
        FileInputStream fis = null;      
        ObjectInputStream ois = null;
        String path = "ReportObjects.bin";
        try {
            f = new File(path);
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            Report tempBr = null;
            try{
                System.out.println("Printing objects");
                while(true){
                    tempBr = (Report) ois.readObject();
  
                    if (tempBr.type.equals("HR")){
                    System.out.println(tempBr);
                    branchReportList.add(tempBr);}
                    
                    if (tempBr.type.equals("Finance")){
                    System.out.println(tempBr);
                    branchReportList.add(tempBr);
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
        System.out.println(branchReportList);
        return branchReportList;
    }            
    
    public static boolean editReport(String curTitle, String curAuthor, String curType, String curBody, int curAuthID, LocalDate curDate, String newTitle, String newBody){      
        
        try {
            String path = "ReportObjects.bin";
            File file = new File(path);
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Report> r = new ArrayList<>();
            
            try{
                while(true){
                    Report tempR = (Report) ois.readObject();
                    System.out.println(tempR);
                    r.add(tempR);
                }
            }
            catch (EOFException eof){
                System.out.println("End of file");
            }
            catch(IOException | ClassNotFoundException e){
                System.out.println(e.toString());
                System.out.println("IOException | ClassNotFoundException in reading bin file");
            }
            
            ois.close();
            System.out.println(r);

            for (Report currentR : r) {
                if (currentR.getTitle().equals(curTitle) && currentR.getAuthor().equals(curAuthor) && currentR.getBody().equals(curBody) && currentR.getAuthorID() == curAuthID && currentR.getDate() == curDate) {
                    currentR.setTitle(newTitle);
                    currentR.setBody(newBody);
                }
            }

            System.out.println(r);
            if(file.delete()){
                System.out.println("Deleted File!");
                File f = new File(path);
                FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                for (Report currentR : r) {
                    oos.writeObject(currentR);
                }
                oos.close();
                System.out.println("Fixed File!");
                return true;
            }
            else{
                System.out.println("Could not delete file");
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
            Logger.getLogger(Policy.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println(ex);
            Logger.getLogger(Policy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
    }   
    

    @Override
    public String toString() {
        return "Report{" + "title=" + title + ", author=" + author + ", type=" + type + ", body=" + body + ", authorID=" + authorID + ", date=" + date + '}';
    }
    
    
    
}
