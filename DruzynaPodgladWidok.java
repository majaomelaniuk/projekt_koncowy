import javax.swing.*;
import java.awt.*;

class DruzynaPodgladWidok extends JFrame {
    
    public DruzynaPodgladWidok(Druzyna druzyna) {

        setTitle("Drużyna");
        getContentPane().setBackground(new Color(0, 100, 0));

//------------------ Nazwa --------------------------

        JPanel tytulPanel = new JPanel(new BorderLayout());
        tytulPanel.setBackground(new Color(0, 100, 0));
        tytulPanel.setPreferredSize(new Dimension(tytulPanel.getPreferredSize().width, 100));        
        JLabel tytulLabel = new JLabel(druzyna.getNazwa(), SwingConstants.CENTER);
        tytulLabel.setFont(new Font("Arial", Font.BOLD, 24));
        tytulLabel.setForeground(Color.WHITE);
        tytulPanel.add(tytulLabel);

        add(tytulPanel);

//------------------ Trener ----------------------------

        JPanel trenerPanel = new JPanel(new BorderLayout());
        trenerPanel.setBackground(new Color(0, 100, 0));
        trenerPanel.setForeground(Color.WHITE);
        JLabel trenerLabel = new JLabel("Trener: " + druzyna.getTrener().toString());
        trenerPanel.add(trenerLabel);

        add(trenerPanel);

//------------------ Kapitan ----------------------------

        JPanel kapitanPanel = new JPanel(new BorderLayout());
        kapitanPanel.setBackground(new Color(0, 100, 0));
        kapitanPanel.setForeground(Color.WHITE);
        JLabel kapitJLabel = new JLabel("Kapitan: " + druzyna.getKapitan().toString() + "   Bramki: " + druzyna.getKapitan().getBramki());
        kapitanPanel.add(kapitJLabel);

        add(kapitanPanel);

//----------------- Pilkarze -----------------------------

        JPanel pilkarzePanel = new JPanel(new BorderLayout());
        pilkarzePanel.setBackground(new Color(0, 100, 0));
        pilkarzePanel.setForeground(Color.WHITE);

        for (Pilkarz pilkarz : druzyna.getPilkarze()) {
            String pilkarzInfo = pilkarz.toString();
            if (pilkarz instanceof Bramkarz) {
                pilkarzInfo += "   Obrony: " + Integer.toString(((Bramkarz) pilkarz).getLiczbaObron());
            } else {
                pilkarzInfo += "   Bramki: " + pilkarz.getBramki();
            }
            JLabel pilkarzLabel = new JLabel(pilkarzInfo);
            pilkarzePanel.add(pilkarzLabel);
        }

        add(pilkarzePanel);

        pack();
        setVisible(true);
    }
}