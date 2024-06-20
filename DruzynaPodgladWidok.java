import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

class DruzynaPodgladWidok extends JFrame {

    public DruzynaPodgladWidok(Druzyna druzyna) {

        setTitle("Podgląd Drużyny");
        getContentPane().setBackground(new Color(0, 100, 0));
        setSize(600, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());


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
        infoPanel.setFont(new Font("Arial", Font.BOLD, 12));

        JLabel trenerLabel = new JLabel();
        trenerLabel.setForeground(Color.WHITE);
        trenerLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        trenerLabel.setText("<html><span style='font-family:Arial; font-size:12px;'>Trener: </span>" + druzyna.getTrener().toString() + "</html>");
        infoPanel.add(trenerLabel);
        infoPanel.add(Box.createVerticalStrut(10));

        JLabel kapitanLabel = new JLabel();
        kapitanLabel.setForeground(Color.WHITE);
        kapitanLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        kapitanLabel.setText("<html><span style='font-family:Arial; font-size:12px;'>Kapitan: </span>" + druzyna.getKapitan().toString() + "   Bramki: " + druzyna.getKapitan().getBramki() + "</html>");
        infoPanel.add(kapitanLabel);
        infoPanel.add(Box.createVerticalStrut(10));

        // Panel z listą piłkarzy
        JPanel pilkarzePanel = new JPanel();
        pilkarzePanel.setBackground(new Color(0, 100, 0));
        pilkarzePanel.setForeground(Color.WHITE);
        pilkarzePanel.setLayout(new BoxLayout(pilkarzePanel, BoxLayout.Y_AXIS));

        for (Pilkarz pilkarz : druzyna.getPilkarze()) {
            String pilkarzInfo = pilkarz.toString();
            if (pilkarz instanceof Bramkarz) {
                pilkarzInfo += "   Obrony: " + ((Bramkarz) pilkarz).getLiczbaObron();
            } else {
                pilkarzInfo += "   Bramki: " + pilkarz.getBramki();
            }
            JLabel pilkarzLabel = new JLabel();
            pilkarzLabel.setForeground(Color.WHITE);
            pilkarzLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            pilkarzLabel.setText("<html>" + pilkarzInfo + "</html>");
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
