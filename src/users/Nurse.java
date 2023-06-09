    package users;

    import java.io.File;
    import java.io.FileInputStream;
    import java.io.IOException;
    import java.io.ObjectInputStream;
    import java.io.Serializable;
    import java.time.LocalDate;
    import javafx.collections.FXCollections;
    import javafx.collections.ObservableList;
    import model.Appointment;
    import model.Bill;
import model.Report;
    import model.Schedule;
    import model.Task;

    public class Nurse extends Employee implements Serializable{
        private static final long serialVersionUID = 13L;

        public ObservableList<Task> getTaskList(){
            ObservableList<Task> taskList = FXCollections.observableArrayList();
            File f = null;
            FileInputStream fis = null;
            ObjectInputStream ois = null;
            String path = "TaskObjects.bin";
            try {
                f = new File(path);
                fis = new FileInputStream(f);
                ois = new ObjectInputStream(fis);
                Task tempTask = null;
                try {
                    System.out.println("Printing Task objects");
                    while (true) {
                        tempTask = (Task) ois.readObject();
                        System.out.println("Populated query: " + tempTask.getSenderID() + ", " + tempTask.getTaskDetails());
                        System.out.println(this.ID);
                        if (tempTask.getReceiverID() == this.ID) {
                            taskList.add(tempTask);
                        }
                    }
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println(e.toString());
                    System.out.println("IOException | ClassNotFoundException in reading bin file");
                }
                System.out.println("End of file\n");
            } catch (IOException ex) {
                System.out.println("IOException on entire file handling");
            } finally {
                try {
                    if (ois != null) {
                        ois.close();
                    }
                } catch (IOException ex) {
                }
            }
            return taskList;
        }
        
    public Nurse(String designation, String department, Float salary, String branchName, String name, int ID, String password, String email, String gender, String contactNo, String address, LocalDate DOB) {
        super(designation, department, salary, branchName, name, ID, password, email, gender, contactNo, address, DOB);
        this.scheduleRoster = super.scheduleRoster;
    }
    
    @Override
    public String toString() {
        return "Nurse: " + "Desig="+designation+" Dept= "+department+" Salary= "+salary+" DOJ= "+DOJ+" branch= "+branchName+" Name= "+name+" ID= "+ID+" pass= "+password+" mail= "+email+" gender"+gender+" contact= "+contactNo+" addr= "+address+" DOJ= "+DOB+" sche="+scheduleRoster;
    }
    
    public static ObservableList<Employee> getAllNurses(){
        ObservableList<Employee> nurseList = FXCollections.observableArrayList();
        File f = null;
        FileInputStream fis = null;      
        ObjectInputStream ois = null;
        String path = "NurseObjects.bin";
        try {
            f = new File(path);
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            User tempUser = null;
            try{
                System.out.println("Printing objects");
                while(true){
                    tempUser = (Nurse) ois.readObject();
                    System.out.println("Populate Employee (Doctor):");
                    System.out.println(tempUser.toString());
                    nurseList.add((Nurse)tempUser);
                }
            }
            catch(IOException | ClassNotFoundException e){
                System.out.println(e.toString());
                System.out.println("IOException | ClassNotFoundException in reading bin file");
            }
            System.out.println("End of file\n");
        } catch (IOException ex) {
            System.out.println("IOException on entire file handling");
        }
        finally {
            try {
                if(ois != null) ois.close();
            } catch (IOException ex) { }
        }
        System.out.println(nurseList);        
        return nurseList;
    }     
    public static ObservableList<Appointment> getDocApptList() {
        ObservableList<Appointment> apptList = FXCollections.observableArrayList();
        File f = null;
        FileInputStream fis = null;      
        ObjectInputStream ois = null;
        String path = "AppointmentObjects.bin";
        try {
            f = new File(path);
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            Appointment tempAppt = null;
            try{
                System.out.println("Printing Appt objects");
                while(true){
                    tempAppt = (Appointment) ois.readObject();
                    System.out.println("Populated appt: "+tempAppt.getDoctorID()+", "+tempAppt.getPatientID());
                    apptList.add(tempAppt);
                    
                }
            }
            catch(IOException | ClassNotFoundException e){
                System.out.println(e.toString());
                System.out.println("IOException | ClassNotFoundException in reading bin file");
            }
            System.out.println("End of file\n");
        } catch (IOException ex) {
            System.out.println("IOException on entire file handling");
        }
        finally {
            try {
                if(ois != null) ois.close();
            } catch (IOException ex) { }
        }
        return apptList;
    }
    
    public static ObservableList<Bill> getPatientBills(int id) {
        
        ObservableList<Bill> billList = FXCollections.observableArrayList();
        File f = null;
        FileInputStream fis = null;      
        ObjectInputStream ois = null;
        String path = "BillObjects.bin";
        try {
            f = new File(path);
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            Bill tempBill = null;
            try{
                System.out.println("Printing objects");
                while(true){
                    tempBill = (Bill) ois.readObject();
                    if (tempBill.getPatientID()== id){
                    System.out.println("Populated bill: "+tempBill.toString());
                    billList.add(tempBill);
                    }

                }
            }
            catch(IOException | ClassNotFoundException e){
                System.out.println(e.toString());
                System.out.println("IOException | ClassNotFoundException in reading bin file");
            }
            System.out.println("End of file\n");
        } catch (IOException ex) {
            System.out.println("IOException on entire file handling");
        }
        finally {
            try {
                if(ois != null) ois.close();
            } catch (IOException ex) { }
        }
        System.out.println(billList);        
        return billList;
    }
    public static ObservableList<Report> viewBranchReports(){
        ObservableList<Report> branchReportList = FXCollections.observableArrayList();
        File f = null;
        FileInputStream fis = null;      
        ObjectInputStream ois = null;
        String path = "ReportObjects.bin";
        try {
            f = new File(path);
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            Report tempBr = null;
            try{
                System.out.println("Printing objects");
                while(true){
                    tempBr = (Report) ois.readObject();
  
                    if (tempBr.type.equals("Branch")){
                        System.out.println(tempBr);
                    branchReportList.add(tempBr);
                    }
                }
            }
            catch(IOException | ClassNotFoundException e){
                System.out.println(e.toString());
                System.out.println("IOException | ClassNotFoundException in reading bin file");
            }
            System.out.println("End of file\n");
        } catch (IOException ex) {
            System.out.println("IOException on entire file handling");
        }
        finally {
            try {
                if(ois != null) ois.close();
            } catch (IOException ex) { }
        }
        System.out.println(branchReportList);
        return branchReportList;
    }
}