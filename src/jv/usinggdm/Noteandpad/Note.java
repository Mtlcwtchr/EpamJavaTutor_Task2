package jv.usinggdm.Noteandpad;

import java.io.Serializable;
import java.util.GregorianCalendar;

public class Note implements Serializable {

    // Variables
    private String theme;
    private GregorianCalendar dateOfCreating;
    private String email;
    private String body;
    // Variables

    public Note(String theme){
        this.theme = theme;
    }
    public Note(){
        this.theme = "";
    }

    // Getters and Setters
    public String getTheme() {
        return this.theme;
    }
    public void setTheme(String theme) {
        this.theme = theme;
    }
    public GregorianCalendar getDateOfCreating() {
        return this.dateOfCreating;
    }
    public void setDateOfCreating(GregorianCalendar dateOfCreating) {
        this.dateOfCreating = dateOfCreating;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getBody() {
        return this.body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    // Getters and Setters

    // Functions
    public void print(){
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.println("\t"+this.getTheme()+"\n"+this.getDateOfCreating().getTime()+"\n"+this.getEmail()+"\n"+this.getBody());
        System.out.println("------------------------------------------------------------------------------------------");
    }
    // Functions

}
