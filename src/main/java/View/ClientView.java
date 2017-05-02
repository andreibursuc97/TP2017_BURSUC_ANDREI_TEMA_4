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
 * Created by Andrei on 02/05/2017.
 */
public class ClientView extends JFrame {
    private JScrollPane scrollPane2;
    private JTable table2;
    private JTextField idAccountField;
    private JTextField amountField;
    private JButton withdrawMoneyButton;
    private JTextField textField2;
    private JPanel panel1;
    private JButton logOutButton;
    private Bank bank;
    private MyModel modelAccounts;
    private String clientUsername;

    public ClientView(Bank bank, String username) {
        this.bank = bank;
        this.clientUsername = username;
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
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setContentPane(panel1);
        this.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here

        table2 = new JTable();
        modelUpdateAccounts(clientUsername);
        table2.setModel(modelAccounts);
        table2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table2.add(table2.getTableHeader(), BorderLayout.PAGE_START);

        scrollPane2 = new JScrollPane(table2);
    }


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
                        dateTabel = new String[]{Integer.toString(spendingAccount.getId()), spendingAccount.getOwner(), Integer.toString(spendingAccount.getBalance()), "Spending Account"};

                    } else {
                        savingAccount = (SavingAccount) account;
                        dateTabel = new String[]{Integer.toString(savingAccount.getId()), savingAccount.getOwner(), Integer.toString(savingAccount.getBalance()), "Saving Acoount"};
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
        textField2 = new JTextField();
        textField2.setEditable(false);
        panel2.add(textField2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
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

        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                int id = Integer.parseInt(idAccountField.getText());
                int amount = Integer.parseInt(amountField.getText());

                Person person = new Person(clientUsername);
                if (bank.getBankList().get(person).get(id - 1) instanceof SpendingAccount) {
                    SpendingAccount account = (SpendingAccount) bank.getBankList().get(person).get(id - 1);
                    account.withdrawMoney(amount);
                } else {
                    SavingAccount account = (SavingAccount) bank.getBankList().get(person).get(id - 1);
                    account.withdrawMoney(amount);
                }
                modelUpdateAccounts(clientUsername);
            } catch (IllegalArgumentException err) {
                JOptionPane.showMessageDialog(null, err.getMessage());
            }
        }
    }

    public class LogOutButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                ClientView.this.dispose();
                ;
                ClientView.this.bank.writeObject();
            } catch (IllegalArgumentException err) {
                JOptionPane.showMessageDialog(null, err.getMessage());
            }
        }
    }

}
