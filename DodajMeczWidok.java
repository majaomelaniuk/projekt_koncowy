import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

class DodajMeczWidok extends JDialog {
    private Tabela nowa_tabela;
    private MeczeWidok meczeWidok;
    private JComboBox<Druzyna> druzynyComboBox1, druzynyComboBox2;
    private JComboBox<Pilkarz> pilkarzKartkiComboBox, pilkarzGolComboBox;
    private JComboBox<String> kolorKartkiComboBox;
    private JTextField minutaKartkiField, minutaGolaField;
    private DefaultTableModel modelGole, modelKartki;
    private JTable tabelaGole, tabelaKartki;
    private JButton dodajGolaButton, dodajKartkeButton, dodajMeczButton;

    public DodajMeczWidok(Tabela tabela, MeczeWidok meczeWidok) {
        this.nowa_tabela = tabela;
        this.meczeWidok = meczeWidok;

        setTitle("Dodaj Mecz");
        setSize(800, 600);
        setMinimumSize(new Dimension(800, 600));
        getContentPane().setBackground(new Color(0, 100, 0));
        getContentPane().setLayout(new BorderLayout(10, 10));

        JPanel tytulPanel = new JPanel(new BorderLayout());
        tytulPanel.setBackground(new Color(0, 100, 0));
        tytulPanel.setPreferredSize(new Dimension(tytulPanel.getPreferredSize().width, 60));
        JLabel tytulLabel = new JLabel("Nowy Mecz", SwingConstants.CENTER);
        tytulLabel.setFont(new Font("Arial", Font.BOLD, 24));
        tytulLabel.setForeground(Color.WHITE);
        tytulPanel.add(tytulLabel, BorderLayout.CENTER);
        add(tytulPanel, BorderLayout.NORTH);

        JPanel panelDruzyn = new JPanel();
        panelDruzyn.setLayout(new GridLayout(1, 2, 10, 10));
        panelDruzyn.setBackground(new Color(0, 100, 0));
        TitledBorder tytulDruzyny = BorderFactory.createTitledBorder("Wybierz drużyny");
        tytulDruzyny.setTitleColor(Color.WHITE);
        panelDruzyn.setBorder(tytulDruzyny);


        JPanel panelDruzyna1 = createTeamPanel(tabela, druzynyComboBox1 = new JComboBox<>(), "Drużyna 1 (gol/kartka):");
        JPanel panelDruzyna2 = createTeamPanel(tabela, druzynyComboBox2 = new JComboBox<>(), "Drużyna 2:");

        panelDruzyn.add(panelDruzyna1);
        panelDruzyn.add(panelDruzyna2);
        add(panelDruzyn, BorderLayout.WEST);

        JPanel panelZdarzenia = new JPanel();
        panelZdarzenia.setLayout(new GridLayout(3, 1, 10, 10));
        panelZdarzenia.setBackground(new Color(0, 100, 0));
        TitledBorder tytulZdarzenia = BorderFactory.createTitledBorder("Zdarzenia");
        tytulZdarzenia.setTitleColor(Color.WHITE);
        panelZdarzenia.setBorder(tytulZdarzenia);

        panelZdarzenia.add(createGoalPanel());
        panelZdarzenia.add(createCardPanel());
        panelZdarzenia.add(createSummaryPanel());

        add(panelZdarzenia, BorderLayout.CENTER);

        JPanel dodajMeczPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        dodajMeczPanel.setBackground(new Color(0, 100, 0));

        dodajMeczButton = new JButton("Dodaj mecz");
        dodajMeczButton.setBackground(new Color(0, 100, 0));
        dodajMeczButton.setForeground(Color.WHITE);
        dodajMeczButton.setFont(new Font("Dialog", Font.BOLD, 12));
        dodajMeczButton.addActionListener(e -> {
            if (druzynyComboBox1.getSelectedItem().equals(druzynyComboBox2.getSelectedItem())) {
                JOptionPane.showMessageDialog(null, "Drużyny nie mogą mieć tej samej nazwy.", "Błąd", JOptionPane.ERROR_MESSAGE);
                return;
            }
            addMatchToTable(tabela);
            tabela.zapisz("tabela.ser");
        });

        dodajMeczPanel.add(dodajMeczButton);
        add(dodajMeczPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel createTeamPanel(Tabela tabela, JComboBox<Druzyna> comboBox, String labelText) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(0, 100, 0));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        for (Druzyna druzyna : tabela.druzyny) {
            comboBox.addItem(druzyna);
        }
        setupComboBoxRenderer(comboBox);

        JLabel label = new JLabel(labelText);
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(SwingConstants.CENTER);

        Dimension squareSize = new Dimension(100, 200);
        comboBox.setPreferredSize(squareSize);
        comboBox.setMaximumSize(squareSize);
        comboBox.setMinimumSize(squareSize);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(label, gbc);

        gbc.gridy = 1;
        panel.add(comboBox, gbc);

        return panel;
    }

    private void setupComboBoxRenderer(JComboBox<Druzyna> comboBox) {
        comboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Druzyna) {
                    setText(((Druzyna) value).getNazwa());
                }
                return this;
            }
        });
    }

    private JPanel createGoalPanel() {

        DefaultComboBoxModel<Pilkarz> model = new DefaultComboBoxModel<Pilkarz>();
        pilkarzGolComboBox = new JComboBox<Pilkarz>(model);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBackground(new Color(0, 100, 0));
        TitledBorder tytul = BorderFactory.createTitledBorder("Dodaj Gola");
        tytul.setTitleColor(Color.WHITE);
        panel.setBorder(tytul);

        JLabel minuteLabel = new JLabel("Minuta gola:");
        minuteLabel.setForeground(Color.WHITE);
        minutaGolaField = new JTextField(5);
        panel.add(minuteLabel);
        panel.add(minutaGolaField);

        JLabel playerLabel = new JLabel("Zawodnik:");
        playerLabel.setForeground(Color.WHITE);
        druzynyComboBox1.addActionListener(e -> {
            Druzyna selectedTeam = (Druzyna) druzynyComboBox1.getSelectedItem();
        
            model.removeAllElements();
        
            for (Pilkarz pilkarz : selectedTeam.getPilkarze()) {
                model.addElement(pilkarz);
            }
        });
        panel.add(playerLabel);
        panel.add(pilkarzGolComboBox);

        dodajGolaButton = new JButton("Dodaj gola");
        dodajGolaButton.setBackground(new Color(0, 100, 0));
        dodajGolaButton.setForeground(Color.WHITE);
        dodajGolaButton.addActionListener(e -> addGoal());
        panel.add(new JLabel());
        panel.add(dodajGolaButton);

        return panel;
    }

    private JPanel createCardPanel() {

        String[] kolory = {"żółta", "czerwona"};
        
        DefaultComboBoxModel<Pilkarz> model = new DefaultComboBoxModel<Pilkarz>();
        pilkarzKartkiComboBox = new JComboBox<Pilkarz>(model);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBackground(new Color(0, 100, 0));
        TitledBorder tytul = BorderFactory.createTitledBorder("Dodaj Kartkę");
        tytul.setTitleColor(Color.WHITE);
        panel.setBorder(tytul);

        JLabel minuteLabel = new JLabel("Minuta kartki:");
        minuteLabel.setForeground(Color.WHITE);
        minutaKartkiField = new JTextField(5);
        panel.add(minuteLabel);
        panel.add(minutaKartkiField);

        JLabel colorLabel = new JLabel("Kolor kartki:");
        colorLabel.setForeground(Color.WHITE);
        kolorKartkiComboBox = new JComboBox(kolory);
        panel.add(colorLabel);
        panel.add(kolorKartkiComboBox);

        JLabel playerLabel = new JLabel("Zawodnik:");
        playerLabel.setForeground(Color.WHITE);
        druzynyComboBox1.addActionListener(e -> {
            Druzyna selectedTeam = (Druzyna) druzynyComboBox1.getSelectedItem();
        
            // Wyczyszczenie modelu
            model.removeAllElements();
        
            // Dodanie piłkarzy do modelu
            for (Pilkarz pilkarz : selectedTeam.getPilkarze()) {
                model.addElement(pilkarz);
            }
        });
        
        panel.add(playerLabel);
        panel.add(pilkarzKartkiComboBox);

        dodajKartkeButton = new JButton("Dodaj kartkę");
        dodajKartkeButton.setBackground(new Color(0, 100, 0));
        dodajKartkeButton.setForeground(Color.WHITE);
        dodajKartkeButton.addActionListener(e -> addCard());
        panel.add(new JLabel());
        panel.add(dodajKartkeButton);

        return panel;
    }

    private JPanel createSummaryPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 10, 10));
        panel.setBackground(new Color(0, 100, 0));
        TitledBorder tytul = BorderFactory.createTitledBorder("Podsumowanie");
        tytul.setTitleColor(Color.WHITE);
        panel.setBorder(tytul);

        modelGole = new DefaultTableModel(new Object[]{"Minuta", "Zawodnik", "Drużyna"}, 0);
        tabelaGole = new JTable(modelGole) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                c.setBackground(row % 2 == 0 ? new Color(0, 150, 0) : new Color(0, 100, 0));
                c.setForeground(Color.WHITE);
                return c;
            }
        };
        customizeTableHeader(tabelaGole);
        JScrollPane goleScrollPane = new JScrollPane(tabelaGole);
        goleScrollPane.getViewport().setBackground(new Color(0, 100, 0));
        panel.add(goleScrollPane);

        modelKartki = new DefaultTableModel(new Object[]{"Minuta", "Zawodnik", "Kolor", "Drużyna"}, 0);
        tabelaKartki = new JTable(modelKartki) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                c.setBackground(row % 2 == 0 ? new Color(0, 150, 0) : new Color(0, 100, 0));
                c.setForeground(Color.WHITE);
                return c;
            }
        };
        customizeTableHeader(tabelaKartki);
        JScrollPane kartkiScrollPane = new JScrollPane(tabelaKartki);
        kartkiScrollPane.getViewport().setBackground(new Color(0, 100, 0));
        panel.add(kartkiScrollPane);

        return panel;
    }

    private void customizeTableHeader(JTable table) {
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(0, 100, 0));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Arial", Font.BOLD, 12));
        header.setReorderingAllowed(false);
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                return label;
            }
        });
    }

    private void addGoal() {
        String minuta = minutaGolaField.getText();
        Pilkarz zawodnik = (Pilkarz) pilkarzGolComboBox.getSelectedItem();
        zawodnik.dodajBramke();
        Druzyna druzyna = (Druzyna) druzynyComboBox1.getSelectedItem();
        modelGole.addRow(new Object[]{minuta, zawodnik, druzyna.getNazwa()});
        System.out.println("Goal added: " + minuta + " minute by " + zawodnik + " for " + druzyna.getNazwa());
    }

    private void addCard() {
        String minuta = minutaKartkiField.getText();
        String kolor = (String) kolorKartkiComboBox.getSelectedItem();
        Pilkarz zawodnik = (Pilkarz) pilkarzKartkiComboBox.getSelectedItem();
        Druzyna druzyna = (Druzyna) druzynyComboBox1.getSelectedItem();
        modelKartki.addRow(new Object[]{minuta, zawodnik, kolor, druzyna.getNazwa()});
        System.out.println("Card added: " + minuta + " minute, " + kolor + " card for " + zawodnik + " of " + druzyna.getNazwa());
    }

    private void addMatchToTable(Tabela tabela) {
        Druzyna druzyna1 = (Druzyna) druzynyComboBox1.getSelectedItem();
        Druzyna druzyna2 = (Druzyna) druzynyComboBox2.getSelectedItem();
        String nazwaDruzyna1 = druzyna1.getNazwa();
        String nazwaDruzyna2 = druzyna2.getNazwa();

        Mecz mecz = new Mecz(nazwaDruzyna1, nazwaDruzyna2);

        for (int i = 0; i < modelGole.getRowCount(); i++) {
            String minuta = (String) modelGole.getValueAt(i, 0);
            Pilkarz zawodnik = (Pilkarz) modelGole.getValueAt(i, 1);
            String nazwaDruzyny = (String) modelGole.getValueAt(i, 2);
            mecz.dodajGol(nazwaDruzyny, zawodnik, Integer.parseInt(minuta));
            System.out.println("Adding card " + i + ": " + minuta + ", " + zawodnik + ", " + nazwaDruzyny);
        }

        for (int i = 0; i < modelKartki.getRowCount(); i++) {
            String minuta = (String) modelKartki.getValueAt(i, 0);
            Pilkarz zawodnik = (Pilkarz) modelKartki.getValueAt(i, 1);
            String kolor = (String) modelKartki.getValueAt(i, 2);
            String nazwaDruzyny = (String) modelKartki.getValueAt(i, 3);
            System.out.println("Adding card " + i + ": " + minuta + ", " + zawodnik + ", " + kolor + ", " + nazwaDruzyny);
            mecz.dodajKartke(zawodnik, kolor, Integer.parseInt(minuta), nazwaDruzyny);
        }

        tabela.dodajMecz(mecz);
        meczeWidok.odswiez();
        System.out.println("Match added: " + nazwaDruzyna1 + " vs " + nazwaDruzyna2);
        tabela.zapisz("tabela.ser");
        dispose();
    }
}
