package users;

import java.io.Serializable;
import java.time.LocalDate;

public class Patient extends User implements Serializable{
    
    public Patient(String name, int ID, String password, String email, String gender, String contactNo, String address, LocalDate DOB) {
        super(name, ID, password, email, gender, contactNo, address, DOB);
    }

    @Override
    public String toString() {
        return "Patient: name="+name+" ID="+ID+" pass="+password+" mail="+email+" gender="+gender+" contact="+contactNo+" addr="+address+" DOB="+DOB;
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
    
}
