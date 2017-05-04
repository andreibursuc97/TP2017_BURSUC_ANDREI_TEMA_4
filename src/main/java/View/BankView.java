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
 * O parte din codul pentru aceasta clasa a fost generat folosindu-se plugin-ul special din IntelIJ IDEA, ea reprezinta interfata prin care adiministratorul poate
 * adauga clienti noi, adauga conturi noi pentru clienti sau adauga bani in conturile clientilor, de asemenea de aici dobanda se poate schimba. La fiecare modificare
 * efectuata asupra contului de catre administrator clientul daca nu este deja logat va fi notificat la prima logare in legatura cu faptul ca o anumita actiune a fost
 * efectuata asupra contului sau.
 */
public class BankView extends JFrame {
    private JPanel panel1;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JTextField usernameField;
    private JTextField nameField;
    private JTextField passwordField;
    private JButton addClientButton;
    private JScrollPane scrollPane2;
    private JTable table2;
    private JTextField amountField;
    private JTextField idAccountField;
    private JRadioButton spendingAccountRadioButton;
    private JRadioButton savingAccountRadioButton;
    private JTextField balanceField;
    private JButton logOutButton;
    private JTextField ownerField;
    private JButton addMoneyButton;
    private JButton addAccountButton;
    private JTextField currentRateField;
    private JTextField newRateField;
    private JButton changeRateButton;
    //private JTextField ownerField;
    private MyModel modelClients;
    private MyModel modelAccounts;
    private Bank bank;
    private IEL listener;


    /**
     * Constructorul primeste ca parametrii un obiect de tip Bank si unul de tip Listener, de asemenea defineste listenerii pentru cele doua tabele din interfata
     * si seteaza listenerii pentru butoanele din fereastra.
     * @param bank
     * @param listener
     */
    public BankView(Bank bank,IEL listener) {
        modelClients = new MyModel();
        modelAccounts = new MyModel();
        this.listener=listener;
        this.bank = bank;
        //$$$setupUI$$$();
        //table1 = new JTable();
        //listSelectionModel = table1.getSelectionModel();
        //table1.setSelectionModel(listSelectionModel);

        $$$setupUI$$$();

        table1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                String selectedUsername = "";
                String name="";
                if (table1.getSelectedRow()!=-1)
                {
                    selectedUsername = table1.getValueAt(table1.getSelectedRow(), 1).toString();
                    name=table1.getValueAt(table1.getSelectedRow(), 2).toString();
                }
                ownerField.setText(selectedUsername);
                usernameField.setText(selectedUsername);
                nameField.setText(name);
                BankView.this.modelUpdateAccounts(selectedUsername);
            }
        });

        table2.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                String id = "";

                if (table2.getSelectedRow() != -1)
                    id = table2.getValueAt(table2.getSelectedRow(), 0).toString();
                BankView.this.getIdAccountField().setText(id);
            }
        });

        ButtonGroup group = new ButtonGroup();
        group.add(spendingAccountRadioButton);
        group.add(savingAccountRadioButton);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.setSize(650, 1000);
        currentRateField.setText(Float.toString(bank.getInterest()));
        addClientButton.addActionListener(new AddClient());
        logOutButton.addActionListener(new LogOutButon());
        addMoneyButton.addActionListener(new AddMoney());
        addAccountButton.addActionListener(new AddAccount());
        changeRateButton.addActionListener(new ChangeInterestListener());
        this.setLocationRelativeTo(null);
        this.setContentPane(panel1);

    }

    public JTextField getIdAccountField() {
        return idAccountField;
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
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(3, 2, new Insets(20, 10, 20, 10), -1, -1));
        panel2.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(550, 1400), new Dimension(2000, 2000), 0, false));
        panel1.add(scrollPane1, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        scrollPane1.setViewportView(table1);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(9, 1, new Insets(50, 10, 0, 0), -1, 5));
        panel1.add(panel3, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        usernameField = new JTextField();
        usernameField.setText("");
        panel3.add(usernameField, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Username");
        panel3.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Name");
        panel3.add(label2, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nameField = new JTextField();
        panel3.add(nameField, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Password");
        panel3.add(label3, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        passwordField = new JTextField();
        panel3.add(passwordField, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        addClientButton = new JButton();
        addClientButton.setText("Add New Client");
        panel3.add(addClientButton, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(5, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel3.add(panel4, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        spendingAccountRadioButton = new JRadioButton();
        spendingAccountRadioButton.setSelected(true);
        spendingAccountRadioButton.setText("Spending Account");
        panel4.add(spendingAccountRadioButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        savingAccountRadioButton = new JRadioButton();
        savingAccountRadioButton.setText("Saving Account");
        panel4.add(savingAccountRadioButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Account Type");
        panel4.add(label4, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Starting Balance");
        panel4.add(label5, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        balanceField = new JTextField();
        balanceField.setText("");
        panel4.add(balanceField, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        addAccountButton = new JButton();
        addAccountButton.setText("Add New Account");
        panel3.add(addAccountButton, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panel1.add(scrollPane2, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        scrollPane2.setViewportView(table2);
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel5, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new GridLayoutManager(8, 1, new Insets(80, 10, 0, 0), -1, -1));
        panel5.add(panel6, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        amountField = new JTextField();
        panel6.add(amountField, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel6.add(spacer1, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Amount");
        panel6.add(label6, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        addMoneyButton = new JButton();
        addMoneyButton.setText("Add Money");
        panel6.add(addMoneyButton, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("Account ID");
        panel6.add(label7, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        idAccountField = new JTextField();
        idAccountField.setEditable(false);
        idAccountField.setText("");
        panel6.add(idAccountField, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setText("Owner");
        panel6.add(label8, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ownerField = new JTextField();
        ownerField.setEditable(false);
        panel6.add(ownerField, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 20, 0), -1, -1));
        panel1.add(panel7, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        logOutButton = new JButton();
        logOutButton.setText("Log Out");
        panel7.add(logOutButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }



    /*



         */


    public class AddClient implements ActionListener {

        /**
         * Metoda actionPerformed din listener-ul pentru butonul de adaugare clienti din JFrame-ul BankView preia textul din filed-urile specifice si adauga in map-ul
         * bankList un client nou daca username-ul introdus nu exista deja. La final updatez modelul tabelelor de persoane si conturi dupa care notific  clientul nou
         * ca noul cont a fost creat cu succes.
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                String username = usernameField.getText();
                if(username.length()<3)
                    throw new IllegalArgumentException("The username you entered is too short!");
                String name = nameField.getText();
                String password = passwordField.getText();
                float balance = Float.parseFloat(balanceField.getText());
                if(balance<0 || balance>1000000)
                    throw new IllegalArgumentException("The initial balance is invalid!");
                int id = bank.getPersons().get(bank.getPersons().size() - 1).getId() + 1;
                Person person = new Person(id, name, username, password);
                Account account;
                boolean type=false;
                if (bank.getBankList().containsKey(person))
                    throw new IllegalArgumentException("This username is already used!");
                if (spendingAccountRadioButton.isSelected())
                {
                    account = new SpendingAccount(1, username);
                    account.setBalance(balance);
                }
                else {
                    account = new SavingAccount(1, username);
                    account.setBalance(balance+balance*bank.getInterest());
                    type=true;
                }
                bank.addPerson(person, account);
                modelUpdateClientsTable();
                modelUpdateAccounts(username);
                listener.addPerson(bank,person);
                listener.notifyClient(person,new String(name+" your new profile was created!"));
                if(type)
                {
                    listener.notifyClient(person,new String(name+" your first account is a Saving Account with the balance "+balance));
                }
                else
                    listener.notifyClient(person,new String(name+" your first account is a Spending Account with the balance "+balance));

            } catch (IllegalArgumentException err) {
                JOptionPane.showMessageDialog(null, err.getMessage());
            }
        }
    }

    /**
     * The type Add acount.
     */
    public class AddAccount implements ActionListener {

        /**
         * Metoda actionPerformed din listenerul pentru butonul "Add account" preia datele legate de username si balance din casutele aferente, intializeaza un obiect nou
         * de tip persoana dupa care verifica daca username-ul exista. Tot folosindu-se de obiect preia id-ul ultimului cont creat si il foloseste pentru a furniza un id
         * contului ce urmeaza a fi creat. Inainte de a crea contul verifica care din butoanele de tip JRadioButton sunt selectate si creaza un obiect cu tipul corespunzator. La
         * final apeleaza metoda AddAccount, updateaza tabelul corespunzator conturilor si notifica clientul ca un cont nou a fost adaugat.
         * @param e
         */

        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                String username = usernameField.getText();
                float balance = Float.parseFloat(balanceField.getText());
                if(balance<0 || balance>1000000)
                    throw new IllegalArgumentException("The initial balance is invalid!");
                Person person = new Person(username);
                Account account;
                boolean type=false;
                if (!bank.getBankList().containsKey(person))
                    throw new IllegalArgumentException("This username doesn't exist!");
                int id = bank.getBankList().get(person).size() + 1;

                if (spendingAccountRadioButton.isSelected())
                {
                    account = new SpendingAccount(id, username);
                    account.setBalance(balance);
                }
                else {
                    account = new SavingAccount(id, username);
                    account.setBalance(balance+balance*bank.getInterest());
                    type=true;
                }

                bank.addAccount(person, account);
                modelUpdateAccounts(username);

                if(type)
                    listener.notifyClient(person,new String(nameField.getText()+" a new Saving Account with the initial balance "+balance+ " and the id "+id+" was created!"));
                else
                    listener.notifyClient(person,new String(nameField.getText()+" a new Spending Account with the initial balance "+balance+" and the id "+id+ " was created!"));
            } catch (IllegalArgumentException err) {
                JOptionPane.showMessageDialog(null, err.getMessage());
            }
        }
    }

    /**
     * Listenerul pentru butonul de adaugare de bani in cont.
     */
    public class AddMoney implements ActionListener {

        /**
         * Metoda actionPerformed din listenerul pentru butonul AddMoney preia datele clientului din casute (id-ul contului, numele si username-ul clientului, suma de adaugat in cont) apoi cauta clientul si contul aferent in banca si apeleaza pentru acel cont metoda addMoney, daca contul este de tip "Saving Account" se va arunca o exceptie
         * la final notificam clientul ca banii au fost adaugati in cont si apelam metoda modelUpdateAccounts pentru a updata tabelul de conturi
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                int id = Integer.parseInt(idAccountField.getText());
                float amount = Float.parseFloat(amountField.getText());
                if(amount<0)
                    throw new IllegalArgumentException("The can't add a negative amount into the account!");
                if(amount>100000)
                    throw new IllegalArgumentException("You can't add so much money into the account!");
                String owner = ownerField.getText();
                String name=nameField.getText();
                Person person = new Person(owner);
                if (bank.getBankList().get(person).get(id - 1) instanceof SpendingAccount) {
                    SpendingAccount account = (SpendingAccount) bank.getBankList().get(person).get(id - 1);
                    account.addMoney(amount);
                } else {
                    throw new IllegalArgumentException("You can't add money in a Saving Account!");
                }
                listener.notifyClient(person,new String(name+" in your account with the id "+id+" was added "+amount+"!"));
                modelUpdateAccounts(owner);
            } catch (IllegalArgumentException err) {
                JOptionPane.showMessageDialog(null, err.getMessage());
            }
        }
    }


    public class ChangeInterestListener implements ActionListener {

        /**
         * Metoda actionPerformed din listenerul pentru butonul "ChangeListener" updateaza dobanda dupa care trimite mesaje de notificare la toti clientii, iar la final seteaza in textbox-ul din interfata noua dobanda.
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                float interest=Float.parseFloat(newRateField.getText());
                bank.setInterest(interest);
                for(Person person:bank.getPersons())
                {
                    listener.notifyClient(person,new String(person.getName()+" the rate of interest have been changed to "+interest+"!"));
                }
                currentRateField.setText(Float.toString(bank.getInterest()));

            } catch (IllegalArgumentException err) {
                JOptionPane.showMessageDialog(null, err.getMessage());
            }
        }
    }


    public class LogOutButon implements ActionListener {

        /**
         * Metoda actionPerformed din listenerul pentru butonul de delogare din interfata pentru admin, dupa apasarea butonului de delogare inchid frame-ul de admin si salvez datele modificate intr-un fisier serializabil.
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                BankView.this.setVisible(false);
                BankView.this.bank.writeObject();
            } catch (IllegalArgumentException err) {
                JOptionPane.showMessageDialog(null, err.getMessage());
            }
        }
    }

    /**
     * Aceasta metoda preia datele din lista de persoane din obiectul bank si le adauga intr-un ArrayList de tip String[], dupa seteaz in model ArrayList-ul obtinut si astfel se updateza tabelul.
     *
     */
    public void modelUpdateClientsTable() {
        String[] columnNames = {"Id", "Username", "Name", "Password"};


        // ProdusBLL produsBLL = new ProdusBLL();
        ArrayList<String[]> date = new ArrayList<String[]>();
        String[] dateTabel;
        //date = produsBLL.afiseazaProduse();
        for (Person person : bank.getPersons()) {
            dateTabel = new String[]{Integer.toString(person.getId()), person.getUsername(), person.getName(), person.getPassword()};
            date.add(dateTabel);
        }
        modelClients.setColumnNames(columnNames);
        modelClients.setList(date);

    }

    /**
     * Aceasta metoda preia datele din map-ul bankList, din ArrayList-ul de Account din obiectul bank si le adauga intr-un ArrayList de tip String[].
     * Dupa seteaza in model noul ArrayList obtinut si astfel se updateaza tabelul in functie de clientul selectat in primul tabel.
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
                    SavingAccount savingAccount = null;
                    SpendingAccount spendingAccount = null;
                    if (account instanceof SavingAccount) {
                        savingAccount = (SavingAccount) account;
                        dateTabel = new String[]{Integer.toString(savingAccount.getId()), savingAccount.getOwner(), Float.toString(savingAccount.getBalance()), "Saving Acoount"};
                    } else {
                        spendingAccount = (SpendingAccount) account;
                        dateTabel = new String[]{Integer.toString(spendingAccount.getId()), spendingAccount.getOwner(), Float.toString(spendingAccount.getBalance()), "Spending Account"};

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
     * Aceasta metoda este folosita pentru a instantia cele doua tabele din fereastra administratorului cu scrollPane-urile aferente si pentru a seta modelul de tip
     * MyModel pe tabele.
     *
     */
    private void createUIComponents() {
        // TODO: place custom component creation code here
        table1 = new JTable();
        table2 = new JTable();
        modelUpdateClientsTable();

        table1.setModel(modelClients);
        String selectedUsername = modelClients.getList().get(1)[1];
        modelUpdateAccounts(selectedUsername);
        table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table1.add(table1.getTableHeader(), BorderLayout.PAGE_START);


        table2.setModel(modelAccounts);
        table2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table2.add(table2.getTableHeader(), BorderLayout.PAGE_START);

        scrollPane1 = new JScrollPane(table1);
        scrollPane2 = new JScrollPane(table2);
    }

}