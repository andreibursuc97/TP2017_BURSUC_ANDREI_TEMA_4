package Model;

import java.io.Serializable;

/**
 * Created by Andrei on 30/04/2017.
 */
public abstract class Account implements Serializable {



    private float balance;




    public abstract void addMoney(float amount);

    public abstract void withdrawMoney(float amount);

    public float getBalance() {
        return balance;
    }


    public void setBalance(float balance) {
        this.balance = balance;
    }
}
