package Model;

/**
 * Aceasta clasa defineste conturile de tip Spending Account ea extinzand clasa de tip Account si implementand metodele de acolo.Clasa SpendingAccount este definita de varibailele id de tip int si
 * owner de tip String.
 */
public class SpendingAccount extends Account {

    private int id;
    private String owner;
    private static final int MAX_AMOUNT=1000000;

    /**
     * Contructorul clasei SpendingAccount primeste ca parametrii varibilele id si owner si le seteaza.
     * @param id pozitiv
     * @param owner
     */
    public SpendingAccount(int id, String owner) {
        this.id = id;
        this.owner = owner;
    }

    /**
     * Creste valuarea atributului balance cu valoarea data de amount.
     * @param amount trebuie sa fie mai mare ca 0
     * @throws AssertionError daca amount este mai mic ca 0 sau mai mare decat valoarea maxima data
     * @throws AssertionError daca valoarea finala a contului nu este egala cu valoarea anterioara + amount
     */
    public void addMoney(float amount) {
        assert amount>0 && amount<MAX_AMOUNT;

        float previousBalance=super.getBalance();
        super.setBalance(super.getBalance()+amount);

        assert super.getBalance()==previousBalance+amount;
    }

    /**
     * Scade valoarea balance cu suma data.
     * @param amount trebuie sa fie mai mica decat suma curenta din cont si mai mare ca 0
     * @throws AssertionError daca valoare este mai mare ca 0 sau negativa
     * @throws AssertionError daca la final valoarea balance nu este egala cu valoarea anterioara - amount
     */
    public void withdrawMoney(float amount) {

        assert(amount<=super.getBalance() && amount>0);
        float previousBalance=super.getBalance();
        super.setBalance(super.getBalance()-amount);

        assert super.getBalance()==previousBalance-amount;

    }

    public int getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }
}
