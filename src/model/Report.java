package model;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Report implements Serializable {
    private static final long serialVersionUID = 13L;
    
    public String title, author, type, body;
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
