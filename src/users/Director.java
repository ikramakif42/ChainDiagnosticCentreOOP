
package users;

import java.io.Serializable;
import java.time.LocalDate;
import model.Schedule;

public class Director extends Employee implements Serializable {
    protected int stockOptions;
    
    
    public Director(String designation, String department, Float salary, LocalDate DOJ, String branchName, String name, int ID, String password, String email, String contactNo, String address, LocalDate DOB) {
        super(designation, department, salary, DOJ, branchName, name, ID, password, email, contactNo, address, DOB);
    }

    public int getStockOptions() {
        return stockOptions;
    }

    public void setStockOptions(int stockOptions) {
        this.stockOptions = stockOptions;
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
        return "Director: " + "Desig="+designation+" Dept= "+department+" Salary= "+salary+" DOJ= "+DOJ+" branch= "+branchName+" Name= "+name+" ID= "+ID+" pass= "+password+" mail= "+email+" contact= "+contactNo+" addr= "+address+" DOJ= "+DOB;
    }
    
      public void viewEmployeeList(){};
      public void viewEditPersonalInfo(){};
      public void editSchedule(){};
      public void changeDesignations(){};
      public void changeSalaryRequest(){};
      public void createBranchReport(){};
      public void viewPastReports(){};
      public void updatePolicies(){};
    
    
    
}
