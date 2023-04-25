package users;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import model.Schedule;

public abstract class Employee extends User implements Serializable{
    private static final long serialVersionUID = 13L;
    
    public String designation, department;
    protected Float salary;
    final LocalDate DOJ;
    public String branchName;
    protected ArrayList<Schedule> scheduleRoster;

    public Employee(String designation, String department, Float salary, LocalDate DOJ, String branchName, String name, int ID, String password, String email, String gender, String contactNo, String address, LocalDate DOB) {
        super(name, ID, password, email, gender, contactNo, address, DOB);
        this.designation = designation;
        this.department = department;
        this.salary = salary;
        this.DOJ = DOJ;
        this.branchName = branchName;
        scheduleRoster = new ArrayList<>();
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

    public LocalDate getDOJ() {
        return DOJ;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public ArrayList<Schedule> getScheduleRoster() {
        return scheduleRoster;
    }

    public void setScheduleRoster(ArrayList<Schedule> scheduleRoster) {
        this.scheduleRoster = scheduleRoster;
    }    
    
}
