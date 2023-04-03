package users;

import java.io.Serializable;
import java.time.LocalDate;
import model.Schedule;

public class Employee extends User implements Serializable{
    public String designation, department;
    private Float salary;
    final LocalDate DOJ;
    public String branchName;
    protected Schedule[] scheduleRoster;

    public Employee(String designation, String department, Float salary, LocalDate DOJ, String branchName, String name, int ID, String password, String email, String contactNo, String address, LocalDate DOB) {
        super(name, ID, password, email, contactNo, address, DOB);
        this.designation = designation;
        this.department = department;
        this.salary = salary;
        this.DOJ = DOJ;
        this.branchName = branchName;
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

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
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
    
    
}
