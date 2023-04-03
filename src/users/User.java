package users;

import java.time.LocalDate;

public class User {
    public String name;
    public final int ID;
    private String password;
    public String email;
    private String contactNo, address;
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
    
    public boolean userLogin(int ID, String pass){
        return true;
    }
    
    public void viewPolicies(){
        
    }
}
