import javax.swing.*;
import java.util.*;
import java.io.*;

class PersonInfo{
    String Name;
    String Location;
    String PhoneNumber;

    //parametarized constructor
    PersonInfo(String n, String a, String p){
        Name = n;
        Location = a;
        PhoneNumber = p;

    }

    // Display on GUI
    void display(){
        JOptionPane.showMessageDialog(null, "Name:" +Name + "\nAddress" + Location+ "\nPhone no: " + PhoneNumber);
    }

}

class AddressBook {
    ArrayList Contacts;

    //ctor

    AddressBook() {

        Contacts = new ArrayList();
        loadPersons();
    }

    //adding a Person
    void addPerson() {
        String name = JOptionPane.showInputDialog("Enter Name:");
        String add = JOptionPane.showInputDialog("Enter Address:");
        String pNum = JOptionPane.showInputDialog("Enter PhoneNo:");
        //creating a personInfo object
        PersonInfo p = new PersonInfo(name, add, pNum);
        Contacts.add(p);
    }

    //searching for person
    void searchPerson(String n) {
        for (int i = 0; i < Contacts.size(); i++) {
            PersonInfo p = (PersonInfo) Contacts.get(i);
            if (n.equals(p.Name)) {
                p.display();
            }
        }

    }

    void deletePerson(String n) {
        for (int i = 0; i < Contacts.size(); i++) {
            PersonInfo p = (PersonInfo) Contacts.get(i);
            if (n.equals(p.Name)) {
                Contacts.remove(i);
            }
        }
    }

    // saving Person Record

    void savePersons() {
        try {
            PersonInfo p;
            String line;
            FileWriter fw = new FileWriter("Persons.txt");
            PrintWriter pw = new PrintWriter(fw);
            for (int i = 0; i < Contacts.size(); i++) {
                p = (PersonInfo) Contacts.get(i);
                line = p.Name + " , " + p.Location;
                //write line to persons.txt
                pw.println(line);
            }
            pw.flush();
            pw.close();
            fw.close();

        } catch (IOException ioEx) {
            System.out.println(ioEx);
        }
    }


    //loading Person Record from .txt file

    void loadPersons() {
        String tokens[] = null;
        String name, add, ph;
        try {
            FileReader fr = new FileReader("Persons.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while (line != null) {
                tokens = line.split(",");
                name = tokens[0];
                add = tokens[1];
                ph = tokens[2];
                PersonInfo p = new PersonInfo(name, add, ph);
                Contacts.add(p);
                line = br.readLine();
            }
            br.close();
            fr.close();
        }catch(IOException ioEx) {
            System.out.println(ioEx);
        }
    }
}

public class phoneBook{
    public static void main(String[] args) {
        AddressBook ab = new AddressBook();
        String input, s;
        int ch;
        while(true){
            input = JOptionPane.showInputDialog("1. Add Contact \n2. Search for Contact \n3. Delete Contact \n4. Exit ");
            ch = Integer.parseInt(input);

            switch(ch){
                case 1:
                    ab.addPerson();
                    break;
                case 2:
                    s = JOptionPane.showInputDialog("Enter name to search:");
                    ab.searchPerson(s);
                    break;
                case 3:
                    s = JOptionPane.showInputDialog("Enter name to delete:");
                    ab.deletePerson(s);
                    break;
                case 4:
                    ab.savePersons();
                    System.exit(0);
            }
        }

    }
}
