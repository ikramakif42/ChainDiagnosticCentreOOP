package model;

<<<<<<< HEAD
import java.time.LocalDate;

public class Schedule {
    LocalDate day; 
    String time, task;
=======
import java.io.Serializable;
import java.time.LocalDate;

public class Schedule implements Serializable {
    private static final long serialVersionUID = 13L;
    
    private LocalDate day;
    private String time, task;
>>>>>>> parent of 64527af (Fixed SerialUID+abstractions)

    public Schedule(LocalDate day, String time, String task) {
        this.day = day;
        this.time = time;
        this.task = task;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
    
}
