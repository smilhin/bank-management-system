package window.coworker;

import database.Database;

import javax.swing.*;
import java.awt.*;

public class CoworkerAccountsJPanel {
    private CoworkerBarJPanel coworkerBarJPanel;
    private JPanel accountsPanel;
    private JTextArea coworkerText;
    private JTextArea customerText;

    public CoworkerAccountsJPanel() {}

    public JPanel createAccountsPanel(Database db) {

        coworkerBarJPanel = new CoworkerBarJPanel();
        coworkerBarJPanel.createBarJPanel();

        accountsPanel = new JPanel(new BorderLayout());
        accountsPanel.setPreferredSize(new Dimension(1000, 600));

        customerText = new JTextArea(db.customersToString());
        customerText.setEditable(false);

        coworkerText = new JTextArea(db.coworkersToString());
        coworkerText.setEditable(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(5, 5, 5, 5);

        JScrollPane customerScroll = new JScrollPane(customerText);
        customerScroll.setBorder(BorderFactory.createTitledBorder("Customers"));

        JScrollPane coworkerScroll = new JScrollPane(coworkerText);
        coworkerScroll.setBorder(BorderFactory.createTitledBorder("Coworkers"));

        JSplitPane splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                customerScroll,
                coworkerScroll
        );

        splitPane.setResizeWeight(0.5);     // Redistribute space equally on resize
        splitPane.setContinuousLayout(true); // Smooth dragging

        accountsPanel.add(splitPane, BorderLayout.CENTER);
        accountsPanel.add(coworkerBarJPanel.getSidebar(), BorderLayout.WEST);
        accountsPanel.add(coworkerBarJPanel.getTopBar(), BorderLayout.NORTH);

        return accountsPanel;
    }

    public void refreshText(Database db) {
        customerText.setText(db.customersToString());
        coworkerText.setText(db.coworkersToString());
    }

    public CoworkerBarJPanel getBarJPanel() {return coworkerBarJPanel;}
}
