package model;

import java.io.Serializable;

/**
 *
 * @author Kazi
 */
public class Policy implements Serializable {
    private static final long serialVersionUID = 13L;
    
    public final int number;
    public String content;

    public Policy(int number, String content) {
        this.number = number;
        this.content = content;
    }

    public int getNumber() {
        return number;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
}
