package Model;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Clasa EventListener implementeaza metodele notifyClient si addPerson ea jucand rol de obiect care monitorizeaza toate schimbarile din conturile clientilor. Daca acesta e ogat va primi notifcarea
 * imediat daca nu, o va primi la logare.
 */
public class EventListener implements IEL,Serializable {

    private HashMap<Person,ArrayList<String>> messages;
    private Bank bank;

    /**
     * Initializeaza map-ul messages.
     * @param bank
     */
    public EventListener(Bank bank)
    {
        this.bank=bank;
        messages=new HashMap<Person, ArrayList<String>>();
        for(Person person:bank.getPersons())
        {
            messages.put(person,new ArrayList<String>());
        }

    }


    /**
     * Metoda notifica clientul in timp real daca s-a efectua o modificare asupra contului, in caz contrar va fi notifcat la logare. Aceasta metoda se apeleaza tot timpul cand administratorul
     * efectueaza o modificare asupra contului.
     * @param person
     * @param message
     */
    public void notifyClient(Person person,String message)
    {
        for(Person person1:bank.getPersons())
        {
            if(person1.equals(person))
                if(person1.isLogat())
                    JOptionPane.showMessageDialog(null,message);
                else
                {
                    messages.get(person1).add(message);

                }
        }
    }

    public HashMap<Person,ArrayList<String>> getMessages() {
        return messages;
    }

    /**
     * Adauga persoana data in map-ul messages.
     * @param bank
     * @param person1
     */
    public void addPerson(Bank bank,Person person1) {
        for(Person person:bank.getPersons())
        {
            if(person.equals(person1))
                messages.put(person,new ArrayList<String>());
        }
    }

}
