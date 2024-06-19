import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

class DodajDruzyneWidok extends JFrame {
    public DodajDruzyneWidok(Tabela tabela) {
        setTitle("Dodaj drużynę");
        setSize(600, 500);
        getContentPane().setBackground(new Color(0, 100, 0));
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JTextField nazwaField = new JTextField();
        JTextField trenerImieField = new JTextField();
        JTextField trenerNazwiskoField = new JTextField();
        JTextField trenerRokField = new JTextField();
        JTextField kapitanImieField = new JTextField();
        JTextField kapitanNazwiskoField = new JTextField();
        JTextField kapitanRokField = new JTextField();
        JTextField kapitanNumerField = new JTextField();
        JTextField zawodnikNumerField = new JTextField();
        JTextField zawodnikImieField = new JTextField();
        JTextField zawodnikNazwiskoField = new JTextField();

//------------------- Tytul -----------------------------

        JPanel tytulPanel = new JPanel(new BorderLayout());
        tytulPanel.setBackground(new Color(0, 100, 0));
        tytulPanel.setPreferredSize(new Dimension(tytulPanel.getPreferredSize().width, 100));        
        JLabel tytulLabel = new JLabel("Nowa drużyna", SwingConstants.CENTER);
        tytulLabel.setFont(new Font("Arial", Font.BOLD, 24));
        tytulLabel.setForeground(Color.WHITE);
        tytulPanel.add(tytulLabel, BorderLayout.CENTER);

        add(tytulPanel);

//------------------ Nazwa -------------------------------
        
        JPanel nazwaPanel = new JPanel(new BorderLayout());
        nazwaPanel.setBackground(new Color(0, 100, 0));
        TitledBorder tytul = BorderFactory.createTitledBorder("Nazwa");
        tytul.setTitleColor(Color.WHITE);
        nazwaPanel.setBorder(tytul);

        JPanel textFieldPanel = new JPanel();
        textFieldPanel.setOpaque(false);
        nazwaField.setColumns(30);
        nazwaField.setHorizontalAlignment(JTextField.CENTER);
        textFieldPanel.add(nazwaField);

        nazwaPanel.add(textFieldPanel, BorderLayout.CENTER);

        Dimension max = nazwaPanel.getMaximumSize();
        max.height = 10;
        nazwaPanel.setMaximumSize(max);

        add(nazwaPanel);

//-------------------- Trener ----------------------------
        
        JPanel trenerImiePanel = new JPanel(new BorderLayout());
        trenerImiePanel.setBackground(new Color(0, 100, 0));
        JLabel trenerImieLabel = new JLabel("Imię", SwingConstants.CENTER);
        trenerImieLabel.setForeground(Color.WHITE);
        trenerImieField.setColumns(10);
        trenerImieField.setHorizontalAlignment(JTextField.CENTER);
        trenerImiePanel.add(trenerImieLabel, BorderLayout.NORTH);
        trenerImiePanel.add(trenerImieField, BorderLayout.SOUTH);

        JPanel trenerNazwiskoPanel = new JPanel(new BorderLayout());
        trenerNazwiskoPanel.setBackground(new Color(0, 100, 0));
        JLabel trenerNazwiskoLabel = new JLabel("Nazwisko", SwingConstants.CENTER);
        trenerNazwiskoLabel.setForeground(Color.WHITE);
        trenerNazwiskoField.setColumns(10);
        trenerNazwiskoField.setHorizontalAlignment(JTextField.CENTER);
        trenerNazwiskoPanel.add(trenerNazwiskoLabel, BorderLayout.NORTH);
        trenerNazwiskoPanel.add(trenerNazwiskoField, BorderLayout.SOUTH);

        JPanel trenerRokPanel = new JPanel(new BorderLayout());
        trenerRokPanel.setBackground(new Color(0, 100, 0));
        JLabel trenerRokLabel = new JLabel("Rok objęcia drużyny", SwingConstants.CENTER);
        trenerRokLabel.setForeground(Color.WHITE);
        trenerRokField.setColumns(10);
        trenerRokField.setHorizontalAlignment(JTextField.CENTER);
        trenerRokPanel.add(trenerRokLabel, BorderLayout.NORTH);
        trenerRokPanel.add(trenerRokField, BorderLayout.SOUTH);

        JPanel trenerPanel = new JPanel(new FlowLayout());
        tytul = BorderFactory.createTitledBorder("Trener");
        tytul.setTitleColor(Color.WHITE);
        trenerPanel.setBorder(tytul);
        trenerPanel.add(trenerImiePanel);
        trenerPanel.add(trenerNazwiskoPanel);
        trenerPanel.add(trenerRokPanel);
        trenerPanel.setForeground(Color.WHITE);
        trenerPanel.setBackground(new Color(0, 100, 0));
        
        add(trenerPanel);
        
//----------------- Kapitan ---------------------

        JPanel kapitanNumerPanel = new JPanel(new BorderLayout());
        kapitanNumerPanel.setBackground(new Color(0, 100, 0));
        JLabel kapitanNumerLabel = new JLabel("Nr", SwingConstants.CENTER);
        kapitanNumerLabel.setForeground(Color.WHITE);
        kapitanNumerField.setColumns(5);
        kapitanNumerField.setHorizontalAlignment(JTextField.CENTER);
        kapitanNumerPanel.add(kapitanNumerLabel, BorderLayout.NORTH);
        kapitanNumerPanel.add(kapitanNumerField, BorderLayout.SOUTH);

        JPanel kapitanImiePanel = new JPanel(new BorderLayout());
        kapitanImiePanel.setBackground(new Color(0, 100, 0));
        JLabel kapitanImieLabel = new JLabel("Imię", SwingConstants.CENTER);
        kapitanImieLabel.setForeground(Color.WHITE);
        kapitanImieField.setColumns(10);
        kapitanImieField.setHorizontalAlignment(JTextField.CENTER);
        kapitanImiePanel.add(kapitanImieLabel, BorderLayout.NORTH);
        kapitanImiePanel.add(kapitanImieField, BorderLayout.SOUTH);

        JPanel kapitanNazwiskoPanel = new JPanel(new BorderLayout());
        kapitanNazwiskoPanel.setBackground(new Color(0, 100, 0));
        JLabel kapitanNazwiskoLabel = new JLabel("Nazwisko", SwingConstants.CENTER);
        kapitanNazwiskoLabel.setForeground(Color.WHITE);
        kapitanNazwiskoField.setColumns(10);
        kapitanNazwiskoField.setHorizontalAlignment(JTextField.CENTER);
        kapitanNazwiskoPanel.add(kapitanNazwiskoLabel, BorderLayout.NORTH);
        kapitanNazwiskoPanel.add(kapitanNazwiskoField, BorderLayout.SOUTH);

        JPanel kapitanRokPanel = new JPanel(new BorderLayout());
        kapitanRokPanel.setBackground(new Color(0, 100, 0));
        JLabel kapitanRokLabel = new JLabel("Rok objęcia kapitaństwa", SwingConstants.CENTER);
        kapitanRokLabel.setForeground(Color.WHITE);
        kapitanRokField.setColumns(10);
        kapitanRokField.setHorizontalAlignment(JTextField.CENTER);
        kapitanRokPanel.add(kapitanRokLabel, BorderLayout.NORTH);
        kapitanRokPanel.add(kapitanRokField, BorderLayout.SOUTH);

        JPanel kapitanPanel = new JPanel(new FlowLayout());
        kapitanPanel.setBackground(new Color(0, 100, 0));
        tytul = BorderFactory.createTitledBorder("Kapitan");
        tytul.setTitleColor(Color.WHITE);
        kapitanPanel.setBorder(tytul);
        kapitanPanel.add(kapitanNumerPanel);
        kapitanPanel.add(kapitanImiePanel);
        kapitanPanel.add(kapitanNazwiskoPanel);
        kapitanPanel.add(kapitanRokPanel);
        kapitanPanel.setForeground(Color.WHITE);

        add(kapitanPanel);

//--------------- Zawodnicy -------------

        JPanel zawodnikNumerPanel = new JPanel(new BorderLayout());
        zawodnikNumerPanel.setBackground(new Color(0, 100, 0));
        JLabel zawodnikNumerLabel = new JLabel("Nr", SwingConstants.CENTER);
        zawodnikNumerLabel.setForeground(Color.WHITE);
        zawodnikNumerField.setColumns(5);
        zawodnikNumerField.setHorizontalAlignment(JTextField.CENTER);
        zawodnikNumerPanel.add(zawodnikNumerLabel, BorderLayout.NORTH);
        zawodnikNumerPanel.add(zawodnikNumerField, BorderLayout.SOUTH);

        JPanel zawodnikImiePanel = new JPanel(new BorderLayout());
        zawodnikImiePanel.setBackground(new Color(0, 100, 0));
        JLabel zawodnikImieLabel = new JLabel("Imię", SwingConstants.CENTER);
        zawodnikImieLabel.setForeground(Color.WHITE);
        zawodnikImieField.setColumns(10);
        zawodnikImieField.setHorizontalAlignment(JTextField.CENTER);
        zawodnikImiePanel.add(zawodnikImieLabel, BorderLayout.NORTH);
        zawodnikImiePanel.add(zawodnikImieField, BorderLayout.SOUTH);

        JPanel zawodnikNazwiskoPanel = new JPanel(new BorderLayout());
        zawodnikNazwiskoPanel.setBackground(new Color(0, 100, 0));
        JLabel zawodnikNazwiskoLabel = new JLabel("Nazwisko", SwingConstants.CENTER);
        zawodnikNazwiskoLabel.setForeground(Color.WHITE);
        zawodnikNazwiskoField.setColumns(10);
        zawodnikNazwiskoField.setHorizontalAlignment(JTextField.CENTER);
        zawodnikNazwiskoPanel.add(zawodnikNazwiskoLabel, BorderLayout.NORTH);
        zawodnikNazwiskoPanel.add(zawodnikNazwiskoField, BorderLayout.SOUTH);

        JPanel bramkarzPanel = new JPanel(new BorderLayout());
        bramkarzPanel.setBackground(new Color(0, 100, 0));
        JLabel bramkarzLabel = new JLabel("Bramkarz", SwingConstants.CENTER);
        bramkarzLabel.setForeground(Color.WHITE);
        JCheckBox bramkarzCheckBox = new JCheckBox();
        bramkarzCheckBox.setBackground(new Color(0, 100, 0));
        bramkarzCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
        bramkarzCheckBox.setVerticalAlignment(SwingConstants.CENTER);
        bramkarzPanel.add(bramkarzLabel, BorderLayout.NORTH);
        bramkarzPanel.add(bramkarzCheckBox, BorderLayout.SOUTH);

        JPanel zawodnikPanel = new JPanel(new BorderLayout());
        zawodnikPanel.setBackground(new Color(0, 100, 0));
        zawodnikPanel.setLayout(new BoxLayout(zawodnikPanel, BoxLayout.Y_AXIS));
        tytul = BorderFactory.createTitledBorder("Zawodnicy");
        tytul.setTitleColor(Color.WHITE);
        zawodnikPanel.setBorder(tytul);

        JPanel polaPanel = new JPanel();
        polaPanel.setBackground(new Color(0, 100, 0));
        polaPanel.add(zawodnikNumerPanel);
        polaPanel.add(zawodnikImiePanel);
        polaPanel.add(zawodnikNazwiskoPanel);
        polaPanel.add(bramkarzPanel);
        
        JButton dodajZawodnikaButton = new JButton("Dodaj piłkarza");
        
        JPanel przyciskPanel = new JPanel(new FlowLayout());
        przyciskPanel.setBackground(new Color(0, 100, 0));
        przyciskPanel.add(Box.createVerticalGlue());
        przyciskPanel.add(dodajZawodnikaButton);

        List<Pilkarz> zawodnicy = new ArrayList<>();
        
        JPanel listaZawodnikowPanel = new JPanel();
        listaZawodnikowPanel.setBackground(new Color(0, 100, 0));
        listaZawodnikowPanel.setForeground(Color.WHITE);
        listaZawodnikowPanel.setLayout(new BoxLayout(listaZawodnikowPanel, BoxLayout.Y_AXIS));

        dodajZawodnikaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pilkarz nowyZawodnik;
                
                if (bramkarzCheckBox.isSelected())
                {
                    nowyZawodnik = new Bramkarz(zawodnikImieField.getText(), zawodnikNazwiskoField.getText(), Integer.parseInt(zawodnikNumerField.getText()));
                }
                else
                {
                    nowyZawodnik = new Napastnik(zawodnikImieField.getText(), zawodnikNazwiskoField.getText(), Integer.parseInt(zawodnikNumerField.getText()));    
                }

                zawodnicy.add(nowyZawodnik);

                listaZawodnikowPanel.removeAll();
                for (Pilkarz zawodnik : zawodnicy) {
                    JLabel label = new JLabel(zawodnik.toString());
                    label.setForeground(Color.WHITE); 
                    listaZawodnikowPanel.add(label);
                }

                revalidate();
                repaint();
            }
        });

        JPanel goraPanel = new JPanel(new BorderLayout());
        goraPanel.add(polaPanel, BorderLayout.CENTER);
        goraPanel.add(przyciskPanel, BorderLayout.EAST);
        
        JScrollPane scrollPaneZawodnicy = new JScrollPane(listaZawodnikowPanel);
        scrollPaneZawodnicy.getViewport().setBackground(new Color(0, 100, 0));
        scrollPaneZawodnicy.getVerticalScrollBar().setBackground(new Color(0, 100, 0));
        scrollPaneZawodnicy.getHorizontalScrollBar().setBackground(new Color(0, 100, 0));
        scrollPaneZawodnicy.setPreferredSize(new Dimension(300, 400));

        zawodnikPanel.add(goraPanel);
        zawodnikPanel.add(scrollPaneZawodnicy);

        add(zawodnikPanel);

        setVisible(true);
    }
}

