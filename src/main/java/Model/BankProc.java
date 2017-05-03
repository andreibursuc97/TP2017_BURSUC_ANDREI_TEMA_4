package Model;

/**
 * Interfata BankProc defineste principalele metode ce vor fi mai departe implementate de clasa Bank.
 */
public interface BankProc {

        public void addPerson(Person person, Account account);

        public void removePerson(Person person);

        public void addAccount(Person person, Account account);


}
