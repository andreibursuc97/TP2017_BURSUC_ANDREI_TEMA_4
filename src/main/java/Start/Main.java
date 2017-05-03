package Start;

import Controller.Controller;
import Model.*;
import View.StartingPanel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Din clasa main citim obiectul de tip Bank din fisierul employee.ser, initializam obiectele de tip StartingPanel si IEL si instantiam Controllerul ce va afisa interfata programului.
 */
public class Main {

    public static void main(String[] args) {

        /*Person person1=new Person(1,"Andrei Bursuc","andreibursuc","paswd");
        Account account1=new SpendingAccount(1,"andreibursuc");
        Person person2=new Person(2,"Barbu Alexandru","barbualex","omugnom");
        Account account2=new SpendingAccount(1,"barbualex");
        Bank bank=new Bank((float) 0.05);
        bank.addPerson(person1,account1);
        bank.addPerson(person2,account2);
        bank.writeObject();*/
        Bank e = null;
        try {
            FileInputStream fileIn = new FileInputStream("employee.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            e = (Bank) in.readObject();
            in.close();
            fileIn.close();
            StartingPanel startingPanel=new StartingPanel();
            IEL listener=new EventListener(e);
            Controller controller=new Controller(e,startingPanel,listener);

        }catch(IOException i) {
            i.printStackTrace();
            return;
        }catch(ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
            return;
        }


    }
}
