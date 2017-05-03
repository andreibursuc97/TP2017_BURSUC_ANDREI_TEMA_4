package Model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Interfata este folosita pentru a defini diveriti listeneri ce sunt folosit pentru a monitoriza modificarile efectuate asupra obiectelor din clasa.
 */
public interface IEL {

    public void notifyClient(Person person,String message);
    public HashMap<Person,ArrayList<String>> getMessages();
    public void addPerson(Bank bank,Person person);
}
