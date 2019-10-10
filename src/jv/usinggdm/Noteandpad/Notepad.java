package jv.usinggdm.Noteandpad;

import jv.usinggdm.DynamicArray.DynamicArray;

import java.io.*;
import java.util.Date;
import java.util.GregorianCalendar;


public class Notepad extends Thread {

                // Variables
                private DynamicArray noteDynamicArray;
                    private ObjectInputStream ois;
                     private ObjectOutputStream oos;
                     private FileInputStream fis;
                      private BufferedReader reader;
                // Variables

                    public Notepad() throws IOException, ClassNotFoundException {
                        noteDynamicArray = new DynamicArray();
                        reader = new BufferedReader(new InputStreamReader(System.in));
                             fis = new FileInputStream("D:\\Java\\Projs\\Notepad\\Database\\notes.dat");
                                if(fis.available()>0)
                                    ois = new ObjectInputStream(fis);
                        while(fis.available()>0 && ois!=null){
                            Note note = (Note) ois.readObject();
                                noteDynamicArray.append(note);
                        }
                        if(ois!=null) ois.close();
                    }

                // Functions
                private Note[] find(String theme){
                    DynamicArray requestedNotes = new DynamicArray();
                        for(int i=0; i<noteDynamicArray.getArray().length; i++)
                            if((noteDynamicArray.getArray()[i].getTheme().equals(theme)))
                                requestedNotes.append(noteDynamicArray.getArray()[i]);
                        return requestedNotes.getArray();
                }
                private Note[] find(GregorianCalendar date){
                    DynamicArray requestedNotes = new DynamicArray();
                        for(int i=0; i<noteDynamicArray.getArray().length; i++)
                            if((noteDynamicArray.getArray()[i].getDateOfCreating().getTime().getYear()==(date.getTime().getYear())&&
                                    (noteDynamicArray.getArray()[i].getDateOfCreating().getTime().getMonth()==(date.getTime().getMonth())&&
                                            noteDynamicArray.getArray()[i].getDateOfCreating().getTime().getDate()==(date.getTime().getDate()))))
                                requestedNotes.append(noteDynamicArray.getArray()[i]);
                    return requestedNotes.getArray();
                }
                private Note[] find(GregorianCalendar date, String email){
                    DynamicArray requestedNotes = new DynamicArray();
                        for(int i=0; i<noteDynamicArray.getArray().length; i++)
                            if((noteDynamicArray.getArray()[i].getDateOfCreating().getTime().getYear()==(date.getTime().getYear())&&
                                    (noteDynamicArray.getArray()[i].getDateOfCreating().getTime().getMonth()==(date.getTime().getMonth())&&
                                            noteDynamicArray.getArray()[i].getDateOfCreating().getTime().getDate()==(date.getTime().getDate())&&
                                            noteDynamicArray.getArray()[i].getEmail().equals(email))))
                                requestedNotes.append(noteDynamicArray.getArray()[i]);
                    return requestedNotes.getArray();
                }
                private Note[] findByWord(String word){
                    DynamicArray requestedNotes = new DynamicArray();
                        for(int i=0; i<noteDynamicArray.getArray().length; i++)
                            if(noteDynamicArray.getArray()[i].getBody().contains(word))
                                requestedNotes.append(noteDynamicArray.getArray()[i]);
                        return requestedNotes.getArray();
                }
                private void find() throws IOException {
                    Note[] notes = null;
                    String theme;
                    String email;
                    String word;
                    String dateString;
                    GregorianCalendar date = new GregorianCalendar();
                    String userInput = reader.readLine();
                    switch (userInput.toLowerCase()){
                        case "1" :
                            System.out.print("Write theme: ");
                                theme = reader.readLine();
                                    if(theme.matches("[\\w ]++"))
                                        notes = find(theme);
                                    else {
                                        System.out.println("Incorrect input");
                                        return;
                                    }
                            if(notes==null) return;
                            sortByTheme(notes);
                            for(Note i : notes){
                                i.print();
                            }
                            break;
                        case "2" :
                            System.out.println("Write date: ");
                            dateString = reader.readLine();
                            if(dateString.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}")) {       // mm/dd/yyyy
                                date.set(Integer.valueOf(dateString.substring(6,10))+0, Integer.valueOf(dateString.substring(0,2))-1, Integer.valueOf(dateString.substring(3,5))+0);
                                System.out.println(date.getTime());
                                notes = find(date);
                            }
                            else {
                                System.out.println("Incorrect input");
                                return;
                            }
                            if(notes==null) return;
                            sortByDate(notes);
                            for(Note i : notes){
                                i.print();
                            }
                            break;
                        case "3" :
                            System.out.println("Write date: ");
                            dateString = reader.readLine();
                            System.out.print("Write email: ");
                            email = reader.readLine();
                            if(email.matches("\"[a-zA-Z0-9][-._ ]+?@gmail\\.com\"")&&
                                    dateString.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}"))
                                notes = find(date, email);
                            else{
                                System.out.println("Incorrect input");
                                return;
                            }
                            if(notes==null) return;
                            sortByDateNEmail(notes);
                            for(Note i : notes){
                                i.print();
                            }
                            break;
                        case "4" :
                            System.out.println("Write word");
                            word = reader.readLine();
                            if(word.matches("[.[^ ]]+?"))
                                notes = findByWord(word);
                            else {
                                System.out.println("Incorrect input");
                                return;
                            }
                            if(notes==null) return;
                            sortByTheme(notes);
                            for(Note i : notes){
                                i.print();
                            }
                            break;
                    }
                }

                private void sortByTheme(Note[] notes){
                    if(notes==null) return;
                    for(int i=0; i<notes.length; i++)
                        for(int j=i; j<notes.length; j++)
                            if(notes[i].getTheme().compareToIgnoreCase(notes[j].getTheme())<0){
                                Note p = notes[i];
                                notes[i] = notes[j];
                                notes[j] = p;
                            }
                }
                private void sortByDate(Note[] notes){
                    if(notes==null) return;
                    for(int i=0; i<notes.length; i++)
                        for(int j=i; j<notes.length; j++)
                            if(notes[i].getDateOfCreating().getTime().compareTo(notes[j].getDateOfCreating().getTime())<0){
                                Note p = notes[i];
                                notes[i] = notes[j];
                                notes[j] = p;
                            }
                }
                private void sortByDateNEmail(Note[] notes){
                    if(notes==null) return;
                    for(int i=0; i<notes.length; i++)
                        for(int j=i; j<notes.length; j++)
                            if(notes[i].getDateOfCreating().getTime().compareTo(notes[j].getDateOfCreating().getTime())<0){
                                Note p = notes[i];
                                notes[i] = notes[j];
                                notes[j] = p;
                            } else if((notes[i].getDateOfCreating().getTime().compareTo(notes[j].getDateOfCreating().getTime())==0) &&
                                    (notes[i].getEmail().compareToIgnoreCase(notes[j].getEmail())<0)){
                                    Note p = notes[i];
                                    notes[i] = notes[j];
                                    notes[j] = p;
                            }
                }

                private void addNote(Note note){
                        if(note == null) return;
                        this.noteDynamicArray.append(note);
                }
                private void addNote() throws IOException{
                        Note note = new Note();
                    System.out.println("Write note theme: ");
                        String theme = reader.readLine();
                            if(theme.matches("[\\w ]++"))
                                note.setTheme(theme);
                            else {
                                System.out.println("Incorrect input");
                                return;
                            }
                        GregorianCalendar currentDate = new GregorianCalendar();
                            currentDate.setTime(new Date());
                        note.setDateOfCreating(currentDate);
                    System.out.println("Note date has been set: " + currentDate.getTime());
                    System.out.println("Write note email: ");
                        String email = reader.readLine();
                            if(email.matches("[A-Za-z0-9\\-._ ]+?@[a-z]+?\\.[a-z]+?"))
                                note.setEmail(email);
                            else {
                                System.out.println("Incorrect input");
                                return;
                            }
                    System.out.println("Write note body: ");
                        StringBuilder body = new StringBuilder();
                            String bodyPart = reader.readLine();
                                while (!bodyPart.toLowerCase().equals("body end")){
                                    body.append(bodyPart);
                                    bodyPart = reader.readLine();
                                }
                            note.setBody(body.toString());
                                this.addNote(note);
                }
                // Functions


            @Override
            public void run(){
                try {
                        while (true){
                            String userInput = reader.readLine();
                                switch (userInput.toLowerCase()){
                                    case "exit" :
                                        oos = new ObjectOutputStream(new FileOutputStream("D:\\Java\\Projs\\Notepad\\Database\\notes.dat"));
                                            for(int i = 0; i < noteDynamicArray.getArray().length; i++)
                                                oos.writeObject(noteDynamicArray.getArray()[i]);
                                            oos.flush();
                                            return;
                                    case "add note"  :
                                        addNote();
                                        break;
                                    case "search" :
                                        System.out.print("Write search mode(By theme[1], by Date[2], by Date and email[3], by Word[4]): ");
                                        find();
                                        break;
                                    case "help" :
                                        System.out.println("Commands:\n - Add note\n - Search\n - Exit");
                                        break;
                                    case "print" :
                                            if(noteDynamicArray.getArray()==null) break;
                                        for(int i=0; i<noteDynamicArray.getArray().length; i++)
                                             noteDynamicArray.getArray()[i].print();
                                        break;
                                        default: break;
                                }
                        }
                    } catch (IOException ex){
                        ex.printStackTrace();
                        System.out.println("File writing error");
                    }
            }

}
