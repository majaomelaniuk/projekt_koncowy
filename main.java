import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private Tabela tabela;
    private JTextArea textArea;
    private JTextField druzyna1Field;
    private JTextField druzyna2Field;
    private JTextField druzynaField;
    private JTextField imieField;
    private JTextField nazwiskoField;
    private JTextField numerField;
    private JTextField minutaField;
    private JTextField kolorField;
    private JComboBox<String> typField;

    public Main() {
        tabela = new Tabela();
        setTitle("ZARZĄDZANIE STATYSTYKAMI MECZÓW PIŁKI NOŻNEJ");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        textArea = new JTextArea();
        textArea.setEditable(false);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(10, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Dodaj Statystyki"));

        inputPanel.add(new JLabel("Drużyna 1:"));
        druzyna1Field = new JTextField();
        inputPanel.add(druzyna1Field);

        inputPanel.add(new JLabel("Drużyna 2:"));
        druzyna2Field = new JTextField();
        inputPanel.add(druzyna2Field);

        inputPanel.add(new JLabel("Drużyna (która zdobyła gola/kartkę):"));
        druzynaField = new JTextField();
        inputPanel.add(druzynaField);

        inputPanel.add(new JLabel("Imię:"));
        imieField = new JTextField();
        inputPanel.add(imieField);

        inputPanel.add(new JLabel("Nazwisko:"));
        nazwiskoField = new JTextField();
        inputPanel.add(nazwiskoField);

        inputPanel.add(new JLabel("Numer:"));
        numerField = new JTextField();
        inputPanel.add(numerField);

        inputPanel.add(new JLabel("Minuta:"));
        minutaField = new JTextField();
        inputPanel.add(minutaField);

        inputPanel.add(new JLabel("Kolor (tylko dla kartki):"));
        kolorField = new JTextField();
        inputPanel.add(kolorField);

        inputPanel.add(new JLabel("Typ (Gol/Kartka):"));
        typField = new JComboBox<>(new String[]{"Gol", "Kartka"});
        inputPanel.add(typField);

        JButton addButton = new JButton("Dodaj");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodajStatystyke();
            }
        });
        inputPanel.add(addButton);

        add(inputPanel, BorderLayout.EAST);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Opcje");

        JMenuItem nowyMeczItem = new JMenuItem("Nowy Mecz");
        nowyMeczItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nowyMecz();
            }
        });
        menu.add(nowyMeczItem);

        JMenuItem nowaDruzynaItem = new JMenuItem("Dodaj Drużynę");
        nowaDruzynaItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodajDruzyne();
            }
        });
        menu.add(nowaDruzynaItem);

        JMenuItem przegladMeczowItem = new JMenuItem("Przegląd Meczów");
        przegladMeczowItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                przegladMeczow();
            }
        });
        menu.add(przegladMeczowItem);

        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    private void nowyMecz() {
        String druzyna1 = druzyna1Field.getText();
        String druzyna2 = druzyna2Field.getText();
        Mecz mecz = new Mecz(druzyna1, druzyna2);
        tabela.dodajMecz(mecz);
        tabela.aktualizujTabele();
        textArea.setText(tabela.toString());
        JOptionPane.showMessageDialog(this, "Nowy mecz został dodany.");
        wyczyscPola();
    }

    private void dodajStatystyke() {
        if (tabela.mecze.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Najpierw dodaj mecz.", "Błąd", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String imie = imieField.getText();
        String nazwisko = nazwiskoField.getText();
        String numerText = numerField.getText();
        String minutaText = minutaField.getText();
        String typ = (String) typField.getSelectedItem();
        String kolor = kolorField.getText();
        String druzyna = druzynaField.getText();

        if (imie.isEmpty() || nazwisko.isEmpty() || numerText.isEmpty() || minutaText.isEmpty() || (typ.equals("Kartka") && kolor.isEmpty())) {
            JOptionPane.showMessageDialog(this, "Wszystkie pola muszą być wypełnione.", "Błąd", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int numer = Integer.parseInt(numerText);
            int minuta = Integer.parseInt(minutaText);
            Pilkarz pilkarz = new Pilkarz(imie, nazwisko, numer);

            Mecz ostatniMecz = tabela.mecze.get(tabela.mecze.size()-1);
            if (typ.equals("Gol")) {
                ostatniMecz.dodajGol(druzyna, pilkarz, minuta);
            } else if (typ.equals("Kartka")) {
                ostatniMecz.dodajKartke(pilkarz, kolor, minuta);
            }

            textArea.setText(tabela.toString());
            JOptionPane.showMessageDialog(this, typ + " został dodany.");
            wyczyscPola();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Numer i minuta muszą być liczbami całkowitymi.", "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void dodajDruzyne() {
        String nazwa = JOptionPane.showInputDialog(this, "Podaj nazwę drużyny:");
        Druzyna druzyna = new Druzyna(nazwa);
        tabela.dodajDruzyne(druzyna);
        textArea.setText(tabela.toString());
        JOptionPane.showMessageDialog(this, "Drużyna " + nazwa + " została dodana.");
    }

    private void przegladMeczow() {
        String[] kolumny = {"Drużyna 1", "Drużyna 2", "Wynik"};
        Object[][] dane = new Object[tabela.mecze.size()][3];
        for (int i = 0; i<tabela.mecze.size(); i++) {
            Mecz mecz = tabela.mecze.get(i);
            dane[i][0] = mecz.getDruzyna1();
            dane[i][1] = mecz.getDruzyna2();
            dane[i][2] = mecz.getBramki1() + " - " + mecz.getBramki2();
        }

        JTable tabelaMeczy = new JTable(new DefaultTableModel(dane, kolumny));
        JScrollPane scrollPane = new JScrollPane(tabelaMeczy);
        JFrame frame = new JFrame("Przegląd meczów");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(scrollPane);
        frame.setSize(400, 300);
        frame.setVisible(true);
    }

    private void wyczyscPola() {
        imieField.setText("");
        nazwiskoField.setText("");
        numerField.setText("");
        minutaField.setText("");
        kolorField.setText("");
        druzynaField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Tabela tabela = new Tabela();

                Druzyna druzyna1 = new Druzyna("WKS");
                Druzyna druzyna2 = new Druzyna("Legia");
                Druzyna druzyna3 = new Druzyna("Lech");
                Druzyna druzyna4 = new Druzyna("Jagiellonia");
                Druzyna druzyna5 = new Druzyna("Widzew");
                Druzyna druzyna6 = new Druzyna("Piast");
                tabela.dodajDruzyne(druzyna1);
                tabela.dodajDruzyne(druzyna2);
                tabela.dodajDruzyne(druzyna3);
                tabela.dodajDruzyne(druzyna4);
                tabela.dodajDruzyne(druzyna5);
                tabela.dodajDruzyne(druzyna6);
                Mecz mecz1 = new Mecz("WKS", "Legia");
                mecz1.dodajGol("WKS", new Pilkarz(" ", " ", 10), 15);
                mecz1.dodajGol("Legia", new Pilkarz(" ", " ", 9), 25);
                tabela.dodajMecz(mecz1);

                Mecz mecz2 = new Mecz("Lech", "Jagiellonia");
                mecz2.dodajGol("Lech", new Pilkarz(" ", " ", 11), 10);
                mecz2.dodajGol("Lech", new Pilkarz(" ", " ", 11), 30);
                mecz2.dodajGol("Jagiellonia", new Pilkarz(" ", " ", 7), 45);
                tabela.dodajMecz(mecz2);

                Mecz mecz3 = new Mecz("WKS", "Lech");
                mecz3.dodajGol("WKS", new Pilkarz(" ", " ", 10), 20);
                mecz3.dodajGol("Lech", new Pilkarz(" ", " ", 11), 40);
                tabela.dodajMecz(mecz3);

                Mecz mecz4 = new Mecz("WKS", "Legia");
                mecz4.dodajGol("WKS", new Pilkarz(" ", " ", 10), 15);
                mecz4.dodajGol("Legia", new Pilkarz(" ", " ", 9), 25);
                tabela.dodajMecz(mecz4);

                Mecz mecz5 = new Mecz("WKS", "Legia");
                mecz5.dodajGol("WKS", new Pilkarz(" ", " ", 10), 15);
                mecz5.dodajGol("Legia", new Pilkarz(" ", " ", 9), 25);
                tabela.dodajMecz(mecz5);

                Mecz mecz6 = new Mecz("WKS", "Legia");
                mecz6.dodajGol("WKS", new Pilkarz(" ", " ", 10), 15);
                mecz6.dodajGol("Legia", new Pilkarz(" ", " ", 9), 25);
                tabela.dodajMecz(mecz6);

                tabela.aktualizujTabele();
                TabelaWidok tabelaWidok = new TabelaWidok(tabela);
                tabelaWidok.setVisible(true);
            }
        });
    }
}


