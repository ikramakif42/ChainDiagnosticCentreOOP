package model;

import java.io.Serializable;

public class LoginInfo implements Serializable{
    private int id;
    private String pass;
    String type;

    public LoginInfo(int id, String pass, String type) {
        this.id = id;
        this.pass = pass;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "LoginInfo: " + "id=" + id + ", pass=" + pass + ", type=" + type;
    }
    
}
