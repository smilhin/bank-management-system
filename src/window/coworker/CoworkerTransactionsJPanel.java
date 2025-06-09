package window.coworker;

import database.Database;

import javax.swing.*;
import java.awt.*;

public class CoworkerTransactionsJPanel {
    private CoworkerBarJPanel coworkerBarJPanel;
    private JPanel coworkerTransactionsJPanel;
    private JTextArea transactionsTextArea;

    public CoworkerTransactionsJPanel() {}

    public JPanel createCoworkerTransactionsJPanel(Database db) {
        coworkerBarJPanel = new CoworkerBarJPanel();
        coworkerBarJPanel.createBarJPanel();

        coworkerTransactionsJPanel = new JPanel(new BorderLayout());
        coworkerTransactionsJPanel.setPreferredSize(new Dimension(1000, 600));

        transactionsTextArea = new JTextArea(db.transactionsToString());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(5, 5, 5, 5);

        JScrollPane transactionScroll = new JScrollPane(transactionsTextArea);
        transactionScroll.setBorder(BorderFactory.createTitledBorder("Transactions"));

        coworkerTransactionsJPanel.add(transactionScroll, BorderLayout.CENTER);
        coworkerTransactionsJPanel.add(coworkerBarJPanel.getSidebar(), BorderLayout.WEST);
        coworkerTransactionsJPanel.add(coworkerBarJPanel.getTopBar(), BorderLayout.NORTH);

        return coworkerTransactionsJPanel;

    }

    public void refreshText(Database db) {
        transactionsTextArea.setText(db.transactionsToString());
    }

    public CoworkerBarJPanel getBarJPanel() {return coworkerBarJPanel;}

}
