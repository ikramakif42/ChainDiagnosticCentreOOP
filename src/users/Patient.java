package users;

import java.io.Serializable;
import java.time.LocalDate;

public class Patient extends User implements Serializable{
    
    public Patient(String name, int ID, String password, String email, String contactNo, String address, LocalDate DOB) {
        super(name, ID, password, email, contactNo, address, DOB);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDOB() {
        return DOB;
    }

    public void setDOB(LocalDate DOB) {
        this.DOB = DOB;
    }

    @Override
    public String toString() {
        return "Patient: name="+name+" ID="+ID+" pass="+password+" mail="+email+" contact="+contactNo+" addr="+address+" DOB="+DOB;
    }
    
    public void makeAppt(){}
    public void cancelAppt(){}
    public void viewPersonalInfo(){}
    public void updatePersonalInfo(){}
    public void viewTeleQuery(){}
    public void makeQuery(){}
    
//    + submitComplaint(): void
//    + viewLabReports(): void
//    + viewPayBills(): void
//    + requestRefills(): void

    public String getAge() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
