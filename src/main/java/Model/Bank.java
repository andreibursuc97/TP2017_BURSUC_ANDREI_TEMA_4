package Model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Aceasta clasa defineste banca si contine un map in care fiecarei persoane ii sunt atribuite o lista de conturi, dar si o lista de persoane si o dobanda.
 */
public class Bank implements BankProc,java.io.Serializable {


    private HashMap<Person,ArrayList<Account>> bankList;
    private ArrayList<Person> persons;
    private float interest;

    /**
     * Constructorul initializeaza map-ul, lista de persoane si seteaza interest-ul.
     * @param interest pozitiv
     * @throws AssertionError daca valoarea interest este negativa
     */
    public Bank(float interest) {
        assert interest>0;
        bankList=new HashMap<Person, ArrayList<Account>>();
        persons=new ArrayList<Person>();
        this.interest=interest;

    }

    /**
     * Aceasta metoda este folosita pentru a salva in fisierul employee.ser obiectul de tip bank. Astfel evtam folosirea unei baze de date.
     */
    public void writeObject()
    {
        FileOutputStream fileOut = null;
        //Model.Bank bank=(Model.Bank) object;

        try {
            fileOut = new FileOutputStream("employee.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Adauga in BankList person si accout si adauga in lista persons obiectul person
     * @param person persoana nu trebuie sa existe deja in lista de persoane
     * @throws AssertionError daca persoana exista deja
     * @param account contul ce urmeaza adaugat
     * @throws AssertionError daca la final se constata cu metoda contains ca obiectul person nu a fost adaugat
     */
    public void addPerson(Person person, Account account) {

        assert !persons.contains(person);

            ArrayList<Account>accounts=new ArrayList<Account>();
            persons.add(person);
            accounts.add(account);
            bankList.put(person,accounts);

        assert persons.contains(person);
    }

    /**
     * Sterge persoana din bankList si persons.
     * @param person persoana trebuie sa existe
     * @throws AssertionError daca persoana nu exista
     * @throws AssertionError daca persoana nu a fost scoasa
     */
    public void removePerson(Person person) {

        assert persons.contains(person);
        bankList.remove(person);
        persons.remove(person);

        assert !persons.contains(person);
    }

    /**
     * Adauga contul in map unde cheia este o persoana data
     * @param person persoana trebuie sa existe in map-ul bankList
     * @throws AssertionError daca persoana nu exista
     * @param account
     * @throws AssertionError daca contul nu a fost adaugat in lista
     */
    public void addAccount(Person person, Account account) {

        assert bankList.containsKey(person);
        for (Map.Entry<Person, ArrayList<Account>> entry2 : bankList.entrySet()) {
            if(person.equals(entry2.getKey()))
                entry2.getValue().add(account);
        }
        assert bankList.get(person).contains(account);
    }


    public HashMap<Person, ArrayList<Account>> getBankList() {
        return bankList;
    }

    public ArrayList<Person> getPersons() {
        return persons;
    }


    public float getInterest() {
        return interest;
    }

    /**
     * Seteaza parametrul interest.
     * @param interest trebuie sa fie pozitiv
     * @throws AssertionError daca interest-ul nu e pozitiv
     */
    public void setInterest(float interest) {
        assert interest>0;
        this.interest = interest;
    }
}
