package users;

import java.io.Serializable;
import java.time.LocalDate;
import model.Schedule;

public class Pharmacist extends Employee implements Serializable{
    private static final long serialVersionUID = 13L;
    
    public Pharmacist(String designation, String department, Float salary, LocalDate DOJ, String branchName, String name, int ID, String password, String email, String gender, String contactNo, String address, LocalDate DOB) {
        super(designation, department, salary, DOJ, branchName, name, ID, password, email, gender, contactNo, address, DOB);
    }

    @Override
    public String toString() {
        return "Pharmacist: " + "Desig="+designation+" Dept= "+department+" Salary= "+salary+" DOJ= "+DOJ+" branch= "+branchName+" Name= "+name+" ID= "+ID+" pass= "+password+" mail= "+email+" gender"+gender+" contact= "+contactNo+" addr= "+address+" DOJ= "+DOB+" sche="+scheduleRoster;
    }
    
}