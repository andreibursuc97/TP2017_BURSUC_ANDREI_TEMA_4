package Model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import static org.junit.Assert.assertTrue;

/**
 * Created by Andrei on 04/05/2017.
 */
public class BankTest {

        private static Bank bank;
        private static int nrTesteExecutate = 0;
        private static int nrTesteCuSucces = 0;


        @BeforeClass
        public static void setUpBeforeClass() throws Exception {
            Person person1=new Person(1,"Andrei Bursuc","andreibursuc","paswd");
            Account account1=new SpendingAccount(1,"andreibursuc");
            Person person2=new Person(2,"Barbu Alexandru","barbualex","omugnom");
            Account account2=new SpendingAccount(1,"barbualex");
            bank=new Bank((float) 0.05);
            bank.addPerson(person1,account1);
            bank.addPerson(person2,account2);
            bank.writeObject();

        }

        @AfterClass
        public static void tearDownAfterClass() throws Exception {
            System.out.println("S-au executat " + nrTesteExecutate + " teste din care "+ nrTesteCuSucces + " au avut succes!");
        }

        @Before
        public void setUp() throws Exception {
            System.out.print("Incepe un nou test pentru");

        }

        @After
        public void tearDown() throws Exception {
            System.out.println("S-a terminat testul curent!");
            nrTesteExecutate++;
        }

        @org.junit.Test
        public void testAddPerson() throws Exception {
            System.out.print(" adaugare persoana!\n");
            Person person1=new Person(3,"Bursuc Alexnadru","luigi","paswd");
            Account account1=new SpendingAccount(1,"luigi");
            bank.addPerson(person1,account1);
            assertTrue(bank.getBankList().containsKey(person1));
            assertTrue(bank.getPersons().contains(person1));
            nrTesteCuSucces++;
        }

    @org.junit.Test
    public void testAddAccount() throws Exception {
        System.out.print(" adaugare cont!\n");
        Person person1=new Person("andreibursuc");
        Account account1=new SpendingAccount(1,"andreibursuc");
        bank.addAccount(person1,account1);
        assertTrue(bank.getBankList().get(person1).contains(account1));
        nrTesteCuSucces++;
    }

    @org.junit.Test
    public void testRemovePerson() throws Exception {
        System.out.print(" stergere persoana!\n");
        Person person1=new Person("andreibursuc");
        bank.removePerson(person1);
        assertTrue(!bank.getBankList().containsKey(person1));
        assertTrue(!bank.getPersons().contains(person1));
        nrTesteCuSucces++;
    }

    @org.junit.Test
    public void testSetInterest() throws Exception {
        System.out.print(" set interest!\n");
        bank.setInterest((float)0.04);
        assertTrue(bank.getInterest()==(float)0.04);

        nrTesteCuSucces++;
    }


}