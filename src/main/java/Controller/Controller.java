package Controller;

import Model.Bank;
import Model.IEL;
import Model.Person;
import View.BankView;
import View.ClientView;
import View.StartingPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Andrei on 01/05/2017.
 */
public class Controller {

    private BankView bankView;
    private Bank bank;
    private StartingPanel startingPanel;
    private IEL listener;

    public Controller(Bank bank, StartingPanel startingPanel,IEL listener)
    {
        this.listener=listener;
        this.bank=bank;
        this.bankView=new BankView(bank,listener);
        this.startingPanel=startingPanel;
        startingPanel.setLogInAsAdminButton(new LogInAdminButton());
        startingPanel.setLogInAsClientButton(new LogInClientButton());
        startingPanel.setVisible(true);
        bankView.setVisible(true);
    }

    public class LogInAdminButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                if (!(startingPanel.getUsernameField().getText().equals("Admin")))
                    throw new IllegalArgumentException("The username doesn't exist!");
                if(!(startingPanel.getPasswordField().getText().equals("badger")))
                    throw new IllegalArgumentException("You have inserted an incorrect password!");
                bankView.setVisible(true);
            }catch (IllegalArgumentException err)
            {
                JOptionPane.showMessageDialog(null,err.getMessage());
            }
        }
    }

    public class LogInClientButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            try {

                boolean ok=false;
                Person person1=null;
                for(Person person:bank.getPersons())
                {
                    if(person.getUsername().equals(startingPanel.getUsernameField().getText()))
                    {
                        ok=true;
                        person1=person;
                        break;
                    }
                }
                if(!ok)
                    throw new IllegalArgumentException("The username does not exist!");

                if(!startingPanel.getPasswordField().getText().equals(person1.getPassword()))
                    throw new IllegalArgumentException("The password is incorrect!");

                for ( Person pers : bank.getBankList().keySet() ) {
                    if(pers.equals(person1))
                        pers.setLogat(true);
                }

                for(Person person:bank.getPersons())
                {
                    if(person.equals(person1))
                        person.setLogat(true);
                }

                ClientView clientView = new ClientView(bank, person1.getUsername(),listener);


            }catch (IllegalArgumentException err)
            {
                JOptionPane.showMessageDialog(null,err.getMessage());
            }
        }
    }
}
