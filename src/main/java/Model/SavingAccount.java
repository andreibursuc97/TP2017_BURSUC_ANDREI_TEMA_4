package Model;

/**
 * Created by Andrei on 30/04/2017.
 */
public class SavingAccount extends Account {

    private int id;
    private String owner;
    private static final int MAX_AMOUNT=1000000;

    public SavingAccount(int id,String owner)
    {
        this.id=id;
        this.owner=owner;

    }


    public void addMoney(float amount) {
        throw new IllegalArgumentException("You can't add money in a Saving Account!");
    }

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
