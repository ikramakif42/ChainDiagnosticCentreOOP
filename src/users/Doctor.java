package users;

import java.io.Serializable;
import java.time.LocalDate;
import model.Schedule;

public class Doctor extends Employee implements Serializable{
    
    public Doctor(String designation, String department, Float salary, LocalDate DOJ, String branchName, String name, int ID, String password, String email, String contactNo, String address, LocalDate DOB) {
        super(designation, department, salary, DOJ, branchName, name, ID, password, email, contactNo, address, DOB);
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public Schedule[] getScheduleRoster() {
        return scheduleRoster;
    }

    public void setScheduleRoster(Schedule[] scheduleRoster) {
        this.scheduleRoster = scheduleRoster;
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
        return "Doctor: " + "Desig="+designation+" Dept= "+department+" Salary= "+salary+" DOJ= "+DOJ+" branch= "+branchName+" Name= "+name+" ID= "+ID+" pass= "+password+" mail= "+email+" contact= "+contactNo+" addr= "+address+" DOJ= "+DOB;
    }
    
    public void checkAppt(){}
    public void cancelAppt(){}    
    public void prescribeMeds(){}
    public void viewEditRecords(int PatientID){}
    public void viewPatientBillingInfo(int PatientID){}
//    + trackLabTests(PatientID): void
//    + assignTask(EmpID): void
//    + answerTeleQuery(): void
}
