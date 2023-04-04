package model;

import java.time.LocalDate;

public class Report {
    public String title, author;
    public LocalDate date;
    public String type, body;

    public Report(String title, String author, LocalDate date, String type, String body) {
        this.title = title;
        this.author = author;
        this.date = date;
        this.type = type;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
    
    
}
