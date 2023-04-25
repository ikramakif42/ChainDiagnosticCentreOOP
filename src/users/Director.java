package users;

import java.io.Serializable;
import java.time.LocalDate;
import model.Schedule;

public class Director extends Employee implements Serializable {
    private static final long serialVersionUID = 13L;
    private int stockOptions;
    
    
    public Director(String designation, String department, Float salary, LocalDate DOJ, String branchName, String name, int ID, String password, String email, String gender, String contactNo, String address, LocalDate DOB) {
        super(designation, department, salary, DOJ, branchName, name, ID, password, email, gender, contactNo, address, DOB);
        this.scheduleRoster = super.scheduleRoster;
    }

    public int getStockOptions() {
        return stockOptions;
    }

    public void setStockOptions(int stockOptions) {
        this.stockOptions = stockOptions;
    }
    
    @Override
    public String toString() {
        return "Director: " + "Desig="+designation+" Dept= "+department+" Salary= "+salary+" DOJ= "+DOJ+" branch= "+branchName+" Name= "+name+" ID= "+ID+" pass= "+password+" mail= "+email+" gender"+gender+" contact= "+contactNo+" addr= "+address+" DOJ= "+DOB;
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
