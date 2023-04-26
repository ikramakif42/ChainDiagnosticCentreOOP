package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Bill implements Serializable {
    private static final long serialVersionUID = 13L;
    
    private LocalDate date;
    private boolean due;
    private Float amount;
    private String details;

    public Bill(boolean due, Float amount, String details) {
        this.due = due;
        this.amount = amount;
        this.details = details;
    }

    public boolean isDue() {
        return due;
    }

    public void setDue(boolean due) {
        this.due = due;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
}
