package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Report implements Serializable {
    private static final long serialVersionUID = 13L;
    
    protected String title;
    public String author, type;
    protected String body;
    public int authorID;
    public LocalDate date;

    public Report(String title, String author, String type, String body, int authorID, LocalDate date) {
        this.title = title;
        this.author = author;
        this.type = type;
        this.body = body;
        this.authorID = authorID;
        this.date = date;
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

    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Report{" + "title=" + title + ", author=" + author + ", type=" + type + ", body=" + body + ", authorID=" + authorID + ", date=" + date + '}';
    }
    
    
    
}
