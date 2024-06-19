import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

class DodajMeczWidok extends JFrame {
    private JFrame okno;
    private JComboBox<Druzyna> druzynyComboBox1, druzynyComboBox2;
    private JComboBox<String> kolorKartkiComboBox;
    private JComboBox<Pilkarz> pilkarzKartkiComboBox, pilkarzGolComboBox;
    private Druzyna wybranaDruzyna1, wybranaDruzyna2;
    private JTable tabelkaKartki, tabelkaGole;
    private JTextField minutaKartkiField, minutaGolaField;
    private JButton dodajGolaButton, dodajKartkeButton;

    public DodajMeczWidok(Tabela tabela) {
        okno = new JFrame("Dodaj Mecz");
        okno.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        okno.setLayout(new BorderLayout(10, 10));
        okno.setBackground(new Color(0, 100, 0));

        JPanel panelDruzyn = new JPanel();
        panelDruzyn.setLayout(new GridLayout(1, 2, 10, 10));
        panelDruzyn.setBackground(new Color(0, 100, 0));

        JPanel panelDruzyna1 = createTeamPanel(tabela, druzynyComboBox1 = new JComboBox<>(), "Wybierz drużynę 1:");
        JPanel panelDruzyna2 = createTeamPanel(tabela, druzynyComboBox2 = new JComboBox<>(), "Wybierz drużynę 2:");

        panelDruzyn.add(panelDruzyna1);
        panelDruzyn.add(panelDruzyna2);
        okno.add(panelDruzyn, BorderLayout.NORTH);

        JPanel panelZdarzenia = new JPanel();
        panelZdarzenia.setLayout(new GridLayout(2, 1, 10, 10));
        panelZdarzenia.add(createGoalPanel());
        panelZdarzenia.add(stworzPanelKartek());
        okno.add(panelZdarzenia, BorderLayout.CENTER);

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
        pilkarzGolComboBox = new JComboBox<>();
        panel.add(playerLabel);
        panel.add(pilkarzGolComboBox);

        dodajGolaButton = new JButton("Dodaj gola");
        panel.add(dodajGolaButton);

        return panel;
    }

    private JPanel stworzPanelKartek() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBackground(new Color(0, 150, 0));

        JLabel minuteLabel = new JLabel("Minuta kartki:");
        minutaKartkiField = new JTextField(5);
        panel.add(minuteLabel);
        panel.add(minutaKartkiField);

        JLabel colorLabel = new JLabel("Kolor kartki:");
        kolorKartkiComboBox = new JComboBox<>(new String[]{"Żółta", "Czerwona"});
        panel.add(colorLabel);
        panel.add(kolorKartkiComboBox);

        JLabel playerLabel = new JLabel("Zawodnik:");
        pilkarzKartkiComboBox = new JComboBox<>();
        panel.add(playerLabel);
        panel.add(pilkarzKartkiComboBox);

        dodajKartkeButton = new JButton("Dodaj kartkę");
        panel.add(dodajKartkeButton);

        return panel;
    }
}
