package Model;

/**
 * Created by Andrei on 30/04/2017.
 */
public class SpendingAccount extends Account {

    private int id;
    private String owner;
    private static final int MAX_AMOUNT=1000000;

    public SpendingAccount(int id, String owner) {
        this.id = id;
        this.owner = owner;
    }


    public void addMoney(float amount) {
        assert amount>0 && amount<MAX_AMOUNT;

        float previousBalance=super.getBalance();
        super.setBalance(super.getBalance()+amount);

        assert super.getBalance()==previousBalance+amount;
    }

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
