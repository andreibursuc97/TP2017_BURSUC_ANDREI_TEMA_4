package Model;

import java.io.Serializable;

/**
 * Clasa abstracta Account este folosita pentru a defini conturile clientilor, ea contine metodele comune celor doua tipuri de conturi ce extind clasa. Principalul atribut al clasei este variabila
 * de tip float balance ce va fi setata prin metoda setBalance de clasele ce extind aceasta clasa abstracta.
 */
public abstract class Account implements Serializable {



    private float balance;




    public abstract void addMoney(float amount);

    public abstract void withdrawMoney(float amount);

    public float getBalance() {
        return balance;
    }


    /**
     * Seteaza valoarea atributului balance din Account
     * @param balance- trebuie sa fie mai mare ca 0.
     * @throws IllegalArgumentException- daca valoarea balance este mai mica decat 0.
     */
    public void setBalance(float balance) {
        if(balance<0)
            throw new IllegalArgumentException("The initial value of an account can't be 0");
        this.balance = balance;
    }
}
