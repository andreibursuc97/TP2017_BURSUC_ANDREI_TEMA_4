package Model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Andrei on 30/04/2017.
 */
public interface IEL {

    public void notifyClient(Person person,String message);
    public HashMap<Person,ArrayList<String>> getMessages();
    public void addPerson(Bank bank,Person person);
}
