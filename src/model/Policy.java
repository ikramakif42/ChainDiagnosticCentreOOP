/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import users.Doctor;
import users.Employee;
import users.User;

/**
 *
 * @author Kazi
 */
public class Policy implements Serializable {
    private static final long serialVersionUID = 13L;
    
    public final int number;
    public String content;

    public Policy(int number, String content) {
        this.number = number;
        this.content = content;
    }

    public int getNumber() {
        return number;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    
    public static ObservableList<Policy> getAllPolicies(){
        ObservableList<Policy> policyList = FXCollections.observableArrayList();
        File f = null;
        FileInputStream fis = null;      
        ObjectInputStream ois = null;
        String path = "PolicyObjects.bin";
        try {
            f = new File(path);
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            Policy tempPol = null;
            try{
                System.out.println("Printing objects");
                while(true){
                    tempPol = (Policy) ois.readObject();
                    System.out.println("Populate Employee (Doctor):");
                    System.out.println(tempPol.toString());
                    policyList.add(tempPol);
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
        System.out.println(policyList);        
        return policyList;
    }            
    
public static boolean updatePolicy(int curNum, String newContent){      
        
        try {
            String path = "PolicyObjects.bin";
            File file = new File(path);
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Policy> p = new ArrayList<>();
            
            try{
                while(true){
                    Policy tempE = (Policy) ois.readObject();
                    System.out.println(tempE);
                    p.add(tempE);
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
            System.out.println(p);

            for (Policy currentP : p) {
                if (currentP.getNumber() == curNum) {
                    currentP.setContent(newContent);
                }
            }

            System.out.println(p);
            if(file.delete()){
                System.out.println("Deleted File!");
                File f = new File(path);
                FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                for (Policy currentP : p) {
                    oos.writeObject(currentP);
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
    
    
}
