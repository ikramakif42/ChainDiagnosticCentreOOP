package users;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.time.LocalDate;
import model.LoginInfo;

public abstract class User implements Serializable{
    
    public String name;
    public final int ID;
    protected String password;
    public String email;
    protected String contactNo, address;
    protected LocalDate DOB;

    public User(String name, int ID, String password, String email, String contactNo, String address, LocalDate DOB) {
        this.name = name;
        this.ID = ID;
        this.password = password;
        this.email = email;
        this.contactNo = contactNo;
        this.address = address;
        this.DOB = DOB;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDOB() {
        return DOB;
    }

    public void setDOB(LocalDate DOB) {
        this.DOB = DOB;
    }

    @Override
    public String toString() {
        return "User{" + "name=" + name + ", ID=" + ID + ", password=" + password + ", email=" + email + ", contactNo=" + contactNo + ", address=" + address + ", DOB=" + DOB + '}';
    }
    
    public static int userLogin(int idcheck, String passcheck){
        File f = null;
        FileInputStream fis = null;      
        ObjectInputStream ois = null;
        int idflag=0;
        int passflag=0;
        int userType=0;
        try {
            f = new File("LoginInfoObjects.bin");
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            LoginInfo tempLogin;
            try{
                System.out.println("Printing objects");
                while(true){
                    if (idflag==1){break;}
                    tempLogin = (LoginInfo) ois.readObject();
                    System.out.println(tempLogin.toString());
                    if (idcheck==tempLogin.getId()){
                        idflag=1;
                        if (passcheck.equals(tempLogin.getPass())){
                            passflag=1;
                            if (tempLogin.getType().equals("Doctor")){userType=3;}
                            else if (tempLogin.getType().equals("Patient")){userType=4;}
//                            else if (tempUser instanceof Pharmacist){userType=5;}
//                            else if (tempUser instanceof Nurse){userType=6;}
                            else if (tempLogin.getType().equals("Director")){userType=7;}
                            else if (tempLogin.getType().equals("Accounts Officer")){userType=8;}
//                            else if (tempUser instanceof HR){userType=9;}
//                            else {userType=10;}
                            break;
                        }
                    }
                }
            }
            catch(IOException | ClassNotFoundException e){
                System.out.println(e.toString());
                System.out.println("IOException | ClassNotFoundException in reading bin file");
            }
            System.out.println("End of file\n");
            
            if (idflag==1){
                if(passflag==1){
                    //errorLabel.setText("Login Successful");
                    return userType;
                }
                else{return 2;}
                //code 2 - errorLabel.setText("Error, wrong password");
            }
            else{return 1;}
                //code 1 - errorLabel.setText("Error, user not found");
        
        } catch (IOException ex) {
            System.out.println("IOException on entire file handling");
            return 0;
        }
        finally {
            try {
                if(ois != null) ois.close();
            } catch (IOException ex) { }
        }
        //code 0 - unhandled exception
    }
   
    public void viewPolicies(){
        
    }
    public static User getInstance(int id, String type){
        File f = null;
        FileInputStream fis = null;      
        ObjectInputStream ois = null;
        String path = "";
        switch(type){
            case "Doctor":
                path="DoctorObjects.bin";
                break;
            case "Patient":
                path="PatientObjects.bin";
                break;
            case "Director":
                path="DirectorObjects.bin";
                break;
            case "Accounts Officer":
                path="AccountsOfficerObjects.bin";
                break;
        }
        
        try {
            f = new File(path);
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            User tempUser = null;
            try{
                System.out.println("Printing objects");
                while(true){
                    switch(type){
                        case "Doctor": 
                            tempUser = (Doctor) ois.readObject();
                            System.out.println("Uh well");
                            System.out.println(tempUser.toString());
                            break;
                        case "Patient": 
                            tempUser = (Patient) ois.readObject();
                            System.out.println("Hmm");
                            System.out.println(tempUser.toString());
                            break;
                        case "Director": 
                            tempUser = (Director) ois.readObject();                            
                            System.out.println("Eh");
                            System.out.println(tempUser.toString());
                            break;
                        case "Accounts Officer": 
                            tempUser = (AccountsOfficer) ois.readObject();
                            System.out.println("Oh");
                            System.out.println(tempUser.toString());
                            break;
                    }
                    if (id==tempUser.getID()){
                        System.out.println("User found");
                        System.out.print("tempUser:");
                        System.out.println(tempUser.toString());
                        return tempUser;
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
        return null;
    }
}
