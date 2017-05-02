package Model;

import java.io.Serializable;

/**
 * Created by Andrei on 30/04/2017.
 */
public abstract class Account implements Serializable {



    private int balance;




    public abstract void addMoney(int amount);

    public abstract void withdrawMoney(int amount);

    public int getBalance() {
        return balance;
    }


    public void setBalance(int balance) {
        this.balance = balance;
    }
}
