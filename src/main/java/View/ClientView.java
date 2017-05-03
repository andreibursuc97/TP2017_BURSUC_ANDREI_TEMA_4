package View;

import Model.*;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

/**
 * O parte din codul pentru aceasta clasa a fost generat folosindu-se plugin-ul special din IntelIJ IDEA, ea reprezinta interfata prin care clientul poate sa-si consulte conturile si sa extraga
 * bani din conturile lui.
 */
public class ClientView extends JFrame {
    private JScrollPane scrollPane2;
    private JTable table2;
    private JTextField idAccountField;
    private JTextField amountField;
    private JButton withdrawMoneyButton;
    private JTextField ownerField;
    private JPanel panel1;
    private JButton logOutButton;
    private Bank bank;
    private MyModel modelAccounts;
    private String clientUsername;
    private IEL listener;

    /**
     * Constructorul seteaza un listener pe tabelul ce arata conturile, iar apoi seteaza listenerii pe cele doua butoane.
     * @param bank
     * @param username
     * @param listener
     */
    public ClientView(Bank bank, String username,IEL listener) {
        this.bank = bank;
        this.clientUsername = username;
        this.listener=listener;
        modelAccounts = new MyModel();

        $$$setupUI$$$();
        table2.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                String id = idAccountField.getText();

                if (table2.getSelectedRow() != -1)
                    id = table2.getValueAt(table2.getSelectedRow(), 0).toString();
                ClientView.this.idAccountField.setText(id);
            }
        });

        withdrawMoneyButton.addActionListener(new WithdrawMoney());
        logOutButton.addActionListener(new LogOutButtonListener());
        ownerField.setText(username);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setContentPane(panel1);
        Person person=new Person(username);
        for(String message:listener.getMessages().get(person))
        {
            JOptionPane.showMessageDialog(null,message);
        }
        listener.getMessages().get(person).clear();
        this.setVisible(true);
    }

    /**
     * Aceasta metoda este folosita pentru a instantia tabelul din fereastra clientului cu scrollPane-ul aferent si pentru a seta modelul de tip
     * MyModel pe tabel.
     */
    private void createUIComponents() {
        // TODO: place custom component creation code here

        table2 = new JTable();
        modelUpdateAccounts(clientUsername);
        table2.setModel(modelAccounts);
        table2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table2.add(table2.getTableHeader(), BorderLayout.PAGE_START);

        scrollPane2 = new JScrollPane(table2);
    }

    /**
     * Aceasta metoda preia datele din map-ul bankList, din ArrayList-ul de Account din obiectul bank si le adauga intr-un ArrayList de tip String[].
     * Dupa seteaza in model noul ArrayList obtinut si astfel se updateaza tabelul in functie de situatia conturilor clientului.
     * @param username
     */
    public void modelUpdateAccounts(String username) {
        String[] columnNames = {"Id", "Owner", "Balance", "Type"};


        // ProdusBLL produsBLL = new ProdusBLL();
        ArrayList<String[]> date = new ArrayList<String[]>();
        String[] dateTabel = null;
        //date = produsBLL.afiseazaProduse();
        for (Map.Entry<Person, ArrayList<Account>> entry2 : bank.getBankList().entrySet()) {
            if (entry2.getKey().getUsername().equals(username)) {
                for (Account account : entry2.getValue()) {
                    SpendingAccount spendingAccount = null;
                    SavingAccount savingAccount = null;

                    if (account instanceof SpendingAccount) {
                        spendingAccount = (SpendingAccount) account;
                        dateTabel = new String[]{Integer.toString(spendingAccount.getId()), spendingAccount.getOwner(), Float.toString(spendingAccount.getBalance()), "Spending Account"};

                    } else {
                        savingAccount = (SavingAccount) account;
                        dateTabel = new String[]{Integer.toString(savingAccount.getId()), savingAccount.getOwner(), Float.toString(savingAccount.getBalance()), "Saving Acoount"};
                    }
                    date.add(dateTabel);
                }

                break;
            }


        }
        modelAccounts.setColumnNames(columnNames);
        modelAccounts.setList(date);
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(3, 2, new Insets(20, 10, 20, 10), -1, -1));
        panel1.add(scrollPane2, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        scrollPane2.setViewportView(table2);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(8, 1, new Insets(20, 10, 20, 10), -1, -1));
        panel1.add(panel2, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Amount");
        panel2.add(label1, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel2.add(spacer1, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        amountField = new JTextField();
        panel2.add(amountField, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        withdrawMoneyButton = new JButton();
        withdrawMoneyButton.setText("Withdraw Money");
        panel2.add(withdrawMoneyButton, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Account ID");
        panel2.add(label2, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        idAccountField = new JTextField();
        idAccountField.setEditable(false);
        idAccountField.setText("");
        panel2.add(idAccountField, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Owner");
        panel2.add(label3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ownerField = new JTextField();
        ownerField.setEditable(false);
        panel2.add(ownerField, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 20, 0), -1, -1));
        panel1.add(panel3, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        logOutButton = new JButton();
        logOutButton.setText("Log Out");
        panel3.add(logOutButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

    public class WithdrawMoney implements ActionListener {

        /**
         * Metoda face ca la apasarea butonului "Withdraw Money" sa fie colectate datele necesare din casute ca mai apoi sa fie cautat contul dupa id si verificat tipul contului pentru a se putea
         * apleca metoda withdrawMoney specifica doar claselor SpendingAccount si SavingAccount. La final se apeleaza metoda modelUpdateAccounts.
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                int id = Integer.parseInt(idAccountField.getText());
                float amount = Float.parseFloat(amountField.getText());

                Person person = new Person(clientUsername);
                if (bank.getBankList().get(person).get(id - 1) instanceof SpendingAccount) {
                    SpendingAccount account = (SpendingAccount) bank.getBankList().get(person).get(id - 1);
                    if(amount>account.getBalance())
                        throw new IllegalArgumentException("You don't have enough money to withdraw!");
                    account.withdrawMoney(amount);
                } else {
                    SavingAccount account = (SavingAccount) bank.getBankList().get(person).get(id - 1);
                    if(amount>account.getBalance() || amount<account.getBalance())
                        throw new IllegalArgumentException("From a Saving Account you can only extract the entire balance!");
                    account.withdrawMoney(amount);
                    bank.getBankList().get(person).remove(id-1);
                }
                modelUpdateAccounts(clientUsername);
            } catch (IllegalArgumentException err) {
                JOptionPane.showMessageDialog(null, err.getMessage());
            }
        }
    }

    public class LogOutButtonListener implements ActionListener {

        /**
         * Metoda inchide fereastra de interfata a clientului si seteaza campul logat al clientului logat in aceas interfata pe false. In final scirem obiectul bank in fisierul employee.ser .
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                ClientView.this.dispose();
                Person person1=new Person(ownerField.getText());
                for ( Person pers : bank.getBankList().keySet() ) {
                    if(pers.equals(person1))
                        pers.setLogat(false);
                }

                for(Person person:bank.getPersons()) {
                    if (person.equals(person1))
                        person.setLogat(false);
                }

                ClientView.this.bank.writeObject();
            } catch (IllegalArgumentException err) {
                JOptionPane.showMessageDialog(null, err.getMessage());
            }
        }
    }

}
