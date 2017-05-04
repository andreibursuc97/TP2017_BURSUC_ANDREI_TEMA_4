package Model;

/**
 * Aceasta clasa defineste conturile de tip Saving Account ea extinzand clasa de tip Account si implementand metodele de acolo.Clasa SavingAccount este definita de varibailele id de tip int si
 * owner de tip String.
 */
public class SavingAccount extends Account {

    private int id;
    private String owner;
    private static final int MAX_AMOUNT=1000000;

    /**
     * Contructorul clasei SavingAccount primeste ca parametrii varibilele id si owner si le seteaza.
     * @param id pozitiv
     * @param owner
     */
    public SavingAccount(int id,String owner)
    {
        this.id=id;
        this.owner=owner;

    }

    /**
     * @throws IllegalArgumentException pentru obiectele de tip obiectele de tipul SavingAccount metoda addMoney arunca o exceptie pentru ca bani se pot adauga intr-un astfel de cont doar la inceput.
     * @param amount
     */
    public void addMoney(float amount) {
        throw new IllegalArgumentException("You can't add money in a Saving Account!");
    }

    /**
     *
     *
     * @param amount suma ce trebuie extrasa trebuie sa fie egala cu valoarea contnutului contului.
     * @throws AssertionError daca suma ce trebuie extrasa nu e egala cu valoarea contnutului contului.
     * @throws AssertionError daca la final valoarea contului nu este 0.
     */
    public void withdrawMoney(float amount) {
        assert amount==super.getBalance();
        super.setBalance(super.getBalance()-amount);
        assert super.getBalance()==0;
        //return 0;
    }


    public int getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }
}
