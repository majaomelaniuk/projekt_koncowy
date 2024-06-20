import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

class DruzynaPodgladWidok extends JFrame {
    
    public DruzynaPodgladWidok(Druzyna druzyna) {

        setTitle("Drużyna");
        getContentPane().setBackground(new Color(0, 100, 0));
        setSize(500, 500);
        setLayout(new BorderLayout());


//------------------ Nazwa --------------------------

        JPanel tytulPanel = new JPanel(new BorderLayout());
        tytulPanel.setBackground(new Color(0, 100, 0));
        tytulPanel.setPreferredSize(new Dimension(tytulPanel.getPreferredSize().width, 100));        
        JLabel tytulLabel = new JLabel(druzyna.getNazwa(), SwingConstants.CENTER);
        tytulLabel.setFont(new Font("Arial", Font.BOLD, 24));
        tytulLabel.setForeground(Color.WHITE);
        tytulPanel.add(tytulLabel);

        add(tytulPanel, BorderLayout.NORTH);


        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(new Color(0, 100, 0));
        infoPanel.setForeground(Color.WHITE);
        infoPanel.setPreferredSize(new Dimension(200, 100));
        
        JLabel trenerLabel = new JLabel("Trener: " + druzyna.getTrener().toString());
        trenerLabel.setForeground(Color.WHITE);
        infoPanel.add(trenerLabel);
        infoPanel.add(Box.createVerticalStrut(10));
        
        JLabel kapitanLabel = new JLabel("Kapitan: " + druzyna.getKapitan().toString() + "   Bramki: " + druzyna.getKapitan().getBramki());
        kapitanLabel.setForeground(Color.WHITE);
        infoPanel.add(kapitanLabel);
        infoPanel.add(Box.createVerticalStrut(10));


        JPanel pilkarzePanel = new JPanel();
        pilkarzePanel.setBackground(new Color(0, 100, 0));
        pilkarzePanel.setForeground(Color.WHITE);
        pilkarzePanel.setLayout(new BoxLayout(pilkarzePanel, BoxLayout.Y_AXIS));

        for (Pilkarz pilkarz : druzyna.getPilkarze()) {
            String pilkarzInfo = pilkarz.toString();
            if (pilkarz instanceof Bramkarz) {
                pilkarzInfo += "   Obrony: " + Integer.toString(((Bramkarz) pilkarz).getLiczbaObron());
            } else {
                pilkarzInfo += "   Bramki: " + pilkarz.getBramki();
            }
            JLabel pilkarzLabel = new JLabel(pilkarzInfo);
            pilkarzLabel.setForeground(Color.WHITE);
            pilkarzePanel.add(pilkarzLabel);
        }

        JScrollPane scrollPane = new JScrollPane(pilkarzePanel);
        scrollPane.getViewport().setBackground(new Color(0, 100, 0));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        infoPanel.add(scrollPane);

        add(infoPanel, BorderLayout.CENTER);

        setVisible(true);
    }
}