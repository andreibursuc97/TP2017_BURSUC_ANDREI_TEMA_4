package Model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrei on 30/04/2017.
 */
public class Bank implements BankProc,java.io.Serializable {


    private HashMap<Person,ArrayList<Account>> bankList;
    private ArrayList<Person> persons;
    private float interest;


    public Bank(float interest) {
        bankList=new HashMap<Person, ArrayList<Account>>();
        persons=new ArrayList<Person>();
        this.interest=interest;

    }

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

    public void addPerson(Person person, Account account) {

        assert !persons.contains(person);

            ArrayList<Account>accounts=new ArrayList<Account>();
            persons.add(person);
            accounts.add(account);
            bankList.put(person,accounts);

        assert persons.contains(person);
    }

    public void removePerson(Person person) {

        assert persons.contains(person);
        bankList.remove(person);
        persons.remove(person);

        assert !persons.contains(person);
    }

    public void addAccount(Person person, Account account) {

        for (Map.Entry<Person, ArrayList<Account>> entry2 : bankList.entrySet()) {
            if(person.equals(entry2.getKey()))
                entry2.getValue().add(account);
        }

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

    public void setInterest(float interest) {
        this.interest = interest;
    }
}
