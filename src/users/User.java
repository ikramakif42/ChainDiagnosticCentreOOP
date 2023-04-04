package users;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.time.LocalDate;

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
            f = new File("UserObjects.bin");
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            User tempUser;
            try{
                System.out.println("Printing objects");
                while(true){
                    if (idflag==1){break;}
                    tempUser = (User) ois.readObject();
                    System.out.println(tempUser.toString());
                    System.out.println("Instanceof test (doc):"+ (tempUser instanceof Doctor));
                    if (idcheck==tempUser.ID){
                        idflag=1;
                        if (passcheck.equals(tempUser.getPassword())){
                            passflag=1;
                            if (tempUser instanceof Doctor){userType=3;}
                            else if (tempUser instanceof Patient){userType=4;}
//                            else if (tempUser instanceof Pharmacist){userType=5;}
//                            else if (tempUser instanceof Nurse){userType=6;}
//                            else if (tempUser instanceof Director){userType=7;}
//                            else if (tempUser instanceof Accounts){userType=8;}
//                            else if (tempUser instanceof HR){userType=9;}
//                            else {userType=10;}
                            break;
                        }
                    }
                }
            }
            catch(IOException | ClassNotFoundException e){
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
}
