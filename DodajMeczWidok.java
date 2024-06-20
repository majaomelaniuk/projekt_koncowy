import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DodajMeczWidok extends JFrame {
    private JFrame okno;
    private JComboBox<Druzyna> druzynyComboBox1, druzynyComboBox2;
    private JTextField kolorKartkiField;
    private JTextField pilkarzKartkiField, pilkarzGolField;
    private JTextField minutaKartkiField, minutaGolaField;
    private JButton dodajGolaButton, dodajKartkeButton, dodajMeczButton;
    private DefaultTableModel modelGole, modelKartki;
    private JTable tabelaGole, tabelaKartki;
    private MeczeWidok meczeWidok;

    public DodajMeczWidok(Tabela tabela, MeczeWidok meczeWidok) {
        this.meczeWidok = meczeWidok;
        okno = new JFrame("Dodaj Mecz");
        okno.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        okno.setLayout(new BorderLayout(10, 10));
        okno.getContentPane().setBackground(new Color(0, 100, 0));

        JPanel panelDruzyn = new JPanel();
        panelDruzyn.setLayout(new GridLayout(1, 2, 10, 10));
        panelDruzyn.setBackground(new Color(0, 100, 0));

        JPanel panelDruzyna1 = createTeamPanel(tabela, druzynyComboBox1 = new JComboBox<>(), "Wybierz drużynę 1 (która zdobyła gola/kartkę):");
        JPanel panelDruzyna2 = createTeamPanel(tabela, druzynyComboBox2 = new JComboBox<>(), "Wybierz drużynę 2:");

        panelDruzyn.add(panelDruzyna1);
        panelDruzyn.add(panelDruzyna2);
        okno.add(panelDruzyn, BorderLayout.NORTH);

        JPanel panelZdarzenia = new JPanel();
        panelZdarzenia.setLayout(new GridLayout(3, 1, 10, 10));
        panelZdarzenia.add(createGoalPanel());
        panelZdarzenia.add(createCardPanel());
        panelZdarzenia.add(createSummaryPanel());

        okno.add(panelZdarzenia, BorderLayout.CENTER);

        dodajMeczButton = new JButton("Dodaj mecz");
        dodajMeczButton.addActionListener(e -> addMatchToTable(tabela));

        okno.add(dodajMeczButton, BorderLayout.SOUTH);

        okno.pack();
        okno.setVisible(true);
    }

    private JPanel createTeamPanel(Tabela tabela, JComboBox<Druzyna> comboBox, String labelText) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBackground(new Color(0, 100, 0));

        for (Druzyna druzyna : tabela.druzyny) {
            comboBox.addItem(druzyna);
        }
        setupComboBoxRenderer(comboBox);

        panel.add(createLabel(labelText));
        panel.add(comboBox);

        return panel;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        label.setForeground(Color.WHITE);
        return label;
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
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBackground(new Color(0, 150, 0));

        JLabel minuteLabel = new JLabel("Minuta gola:");
        minutaGolaField = new JTextField(5);
        panel.add(minuteLabel);
        panel.add(minutaGolaField);

        JLabel playerLabel = new JLabel("Zawodnik:");
        pilkarzGolField = new JTextField(15);
        panel.add(playerLabel);
        panel.add(pilkarzGolField);

        dodajGolaButton = new JButton("Dodaj gola");
        dodajGolaButton.addActionListener(e -> addGoal());

        panel.add(dodajGolaButton);

        return panel;
    }

    private JPanel createCardPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBackground(new Color(0, 150, 0));

        JLabel minuteLabel = new JLabel("Minuta kartki:");
        minutaKartkiField = new JTextField(5);
        panel.add(minuteLabel);
        panel.add(minutaKartkiField);

        JLabel colorLabel = new JLabel("Kolor kartki:");
        kolorKartkiField = new JTextField(10);
        panel.add(colorLabel);
        panel.add(kolorKartkiField);

        JLabel playerLabel = new JLabel("Zawodnik:");
        pilkarzKartkiField = new JTextField(15);
        panel.add(playerLabel);
        panel.add(pilkarzKartkiField);

        dodajKartkeButton = new JButton("Dodaj kartkę");
        dodajKartkeButton.addActionListener(e -> addCard());

        panel.add(dodajKartkeButton);

        return panel;
    }

    private JPanel createSummaryPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2, 10, 10));
        panel.setBackground(new Color(0, 100, 0));

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
        String zawodnik = pilkarzGolField.getText();
        Druzyna druzyna = (Druzyna) druzynyComboBox1.getSelectedItem();
        modelGole.addRow(new Object[]{minuta, zawodnik, druzyna.getNazwa()});
        System.out.println("Goal added: " + minuta + " minute by " + zawodnik + " for " + druzyna.getNazwa());
    }

    private void addCard() {
        String minuta = minutaKartkiField.getText();
        String kolor = kolorKartkiField.getText();
        String zawodnik = pilkarzKartkiField.getText();
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
            String zawodnik = (String) modelGole.getValueAt(i, 1);
            String nazwaDruzyny = (String) modelGole.getValueAt(i, 2);
            Pilkarz pilkarz = new Pilkarz(zawodnik, "", 0);
            mecz.dodajGol(nazwaDruzyny, pilkarz, Integer.parseInt(minuta));
        }
        for (int i = 0; i < modelKartki.getRowCount(); i++) {
            String minuta = (String) modelKartki.getValueAt(i, 0);
            String zawodnik = (String) modelKartki.getValueAt(i, 1);
            String kolor = (String) modelKartki.getValueAt(i, 2);
            String nazwaDruzyny = (String) modelKartki.getValueAt(i, 3);
            Pilkarz pilkarz = new Pilkarz(zawodnik, "", 0);
            mecz.dodajKartke(pilkarz, kolor, Integer.parseInt(minuta), nazwaDruzyny);
        }

        tabela.dodajMecz(mecz);
        meczeWidok.odswiez();
        System.out.println("Match added: " + nazwaDruzyna1 + " vs " + nazwaDruzyna2);
    }
}
