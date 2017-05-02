package Model;

/**
 * Created by Andrei on 30/04/2017.
 */
public interface BankProc {

        public void addPerson(Person person, Account account);

        public void removePerson(Person person);

        public void addAccount(Person person, Account account);


}
