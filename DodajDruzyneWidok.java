import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DodajDruzyneWidok extends JFrame {
    public DodajDruzyneWidok(Tabela tabela) {
        setTitle("Dodaj drużynę");
        setSize(500, 500);
        getContentPane().setBackground(new Color(0, 100, 0));
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JTextField nazwaField = new JTextField();
        JTextField trenerImieField = new JTextField();
        JTextField trenerNazwiskoField = new JTextField();
        JTextField trenerRokField = new JTextField();
        JTextField kapitanField = new JTextField();
        JTextField kapitanRokField = new JTextField();
        JTextArea zawodnicyArea = new JTextArea();
        JTextField zawodnikNumerField = new JTextField();

        JPanel tytulPanel = new JPanel(new BorderLayout());
        tytulPanel.setBackground(new Color(0, 100, 0));
        tytulPanel.setPreferredSize(new Dimension(tytulPanel.getPreferredSize().width, 50));        
        JLabel tytulLabel = new JLabel("Nowa drużyna", SwingConstants.CENTER);
        tytulLabel.setFont(new Font("Arial", Font.BOLD, 24));
        tytulLabel.setForeground(Color.WHITE);
        tytulPanel.add(tytulLabel, BorderLayout.CENTER);

        add(tytulPanel);
        
        JPanel nazwaPanel = new JPanel(new BorderLayout());
        nazwaPanel.setBackground(new Color(0, 100, 0));
        TitledBorder title = BorderFactory.createTitledBorder("Nazwa");
        title.setTitleColor(Color.WHITE);
        nazwaPanel.setBorder(title);

        JPanel textFieldPanel = new JPanel();
        textFieldPanel.setOpaque(false);
        nazwaField.setColumns(30);
        textFieldPanel.add(nazwaField);

        nazwaPanel.add(textFieldPanel, BorderLayout.CENTER);

        Dimension max = nazwaPanel.getMaximumSize();
        max.height = 10;
        nazwaPanel.setMaximumSize(max);

        add(nazwaPanel);
        
        JPanel trenerImiePanel = new JPanel(new BorderLayout());
        trenerImiePanel.setBackground(new Color(0, 100, 0));
        JLabel trenerImieLabel = new JLabel("Imię", SwingConstants.CENTER);
        trenerImieLabel.setForeground(Color.WHITE);
        trenerImieField.setColumns(10);
        trenerImiePanel.add(trenerImieLabel, BorderLayout.NORTH);
        trenerImiePanel.add(trenerImieField, BorderLayout.SOUTH);

        JPanel trenerNazwiskoPanel = new JPanel(new BorderLayout());
        trenerNazwiskoPanel.setBackground(new Color(0, 100, 0));
        JLabel trenerNazwiskoLabel = new JLabel("Nazwisko", SwingConstants.CENTER);
        trenerNazwiskoLabel.setForeground(Color.WHITE);
        trenerNazwiskoField.setColumns(10);
        trenerNazwiskoPanel.add(trenerNazwiskoLabel, BorderLayout.NORTH);
        trenerNazwiskoPanel.add(trenerNazwiskoField, BorderLayout.SOUTH);

        JPanel trenerRokPanel = new JPanel(new BorderLayout());
        trenerRokPanel.setBackground(new Color(0, 100, 0));
        JLabel trenerRokLabel = new JLabel("Rok objęcia drużyny", SwingConstants.CENTER);
        trenerRokLabel.setForeground(Color.WHITE);
        trenerRokField.setColumns(10);
        trenerRokPanel.add(trenerRokLabel, BorderLayout.NORTH);
        trenerRokPanel.add(trenerRokField, BorderLayout.SOUTH);

        JPanel trenerPanel = new JPanel(new FlowLayout());
        title = BorderFactory.createTitledBorder("Trener");
        title.setTitleColor(Color.WHITE);
        trenerPanel.setBorder(title);
        trenerPanel.add(trenerImiePanel);
        trenerPanel.add(trenerNazwiskoPanel);
        trenerPanel.add(trenerRokPanel);
        trenerPanel.setForeground(Color.WHITE);
        trenerPanel.setBackground(new Color(0, 100, 0));
        add(trenerPanel);
        
        add(new JLabel("Kapitan:"));
        add(kapitanField);
        add(new JLabel("Rok kapitana:"));
        add(kapitanRokField);
        
        add(new JLabel("Zawodnicy:"));
        add(zawodnicyArea);
        add(new JLabel("Numer zawodnika:"));
        add(zawodnikNumerField);
        
        JButton dodajButton = new JButton("Dodaj drużynę");
        dodajButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Druzyna druzyna = new Druzyna(nazwaField.getText());
                druzyna.setTrener(new Trener(trenerImieField.getText(), trenerNazwiskoField.getText(), Integer.parseInt(trenerRokField.getText())));
                tabela.dodajDruzyne(druzyna);
            }
        });
        
        add(dodajButton);

        setVisible(true);
    }
}

