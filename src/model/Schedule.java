package model;

public class Schedule {
    String day, time, task;

    public Schedule(String day, String time, String task) {
        this.day = day;
        this.time = time;
        this.task = task;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
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
