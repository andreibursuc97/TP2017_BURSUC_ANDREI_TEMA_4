package Model;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Andrei on 30/04/2017.
 */
public class EventListener implements IEL {

    private HashMap<Person,ArrayList<String>> messages;
    private Bank bank;

    public EventListener(Bank bank)
    {
        this.bank=bank;
        messages=new HashMap<Person, ArrayList<String>>();
        for(Person person:bank.getPersons())
        {
            messages.put(person,new ArrayList<String>());
        }

    }
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

    public void addPerson(Bank bank,Person person1) {
        for(Person person:bank.getPersons())
        {
            if(person.equals(person1))
                messages.put(person,new ArrayList<String>());
        }
    }
}
