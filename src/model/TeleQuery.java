package model;

public class TeleQuery {
    public String email, query, answer;

    public TeleQuery(String email, String query) {
        this.email = email;
        this.query = query;
        this.answer = "";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
    public boolean isPending(){
        if (this.answer.isEmpty()){return true;}
        else {return false;}
    }
}