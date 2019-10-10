package jv.usinggdm;

import jv.usinggdm.Noteandpad.Notepad;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            Notepad notepad = new Notepad();
                notepad.start();
        } catch (ClassNotFoundException ex){
            ex.printStackTrace();
        } catch (IOException ex){
            ex.printStackTrace();
            System.out.println("File reading error");
        }
    }
}
