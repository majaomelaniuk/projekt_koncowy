import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Czlowiek {
    protected String imie;
    protected String nazwisko;

    public Czlowiek(String imie, String nazwisko) {
        this.imie = imie;
        this.nazwisko = nazwisko;
    }
}
class Pilkarz extends Czlowiek {
    protected int numer;

    public Pilkarz(String imie, String nazwisko, int numer) {
        super(imie, nazwisko);
        this.numer = numer;
    }

    @Override
    public String toString() {
        return imie + " " + nazwisko + " (Numer: " + numer + ")";
    }
}

class Trener extends Czlowiek {
    public Trener(String imie, String nazwisko) {
        super(imie, nazwisko);
    }
}

class Napastnik extends Pilkarz {
    public Napastnik(String imie, String nazwisko, int numer) {
        super(imie, nazwisko, numer);
    }
}

class Kapitan extends Pilkarz {
    public Kapitan(String imie, String nazwisko, int numer) {
        super(imie, nazwisko, numer);
    }
}

class Bramkarz extends Pilkarz {
    private int liczbaObron;

    public Bramkarz(String imie, String nazwisko, int numer) {
        super(imie, nazwisko, numer);
        this.liczbaObron = 0;
    }

    public void dodajObrone() {
        liczbaObron++;
    }

    public int getLiczbaObron() {
        return liczbaObron;
    }

    @Override
    public String toString() {
        return super.toString() + " (Bramkarz, Obrony: " + liczbaObron + ")";
    }
}


class Gol {
    private Pilkarz strzelec;
    private int minuta;
    public Gol(Pilkarz strzelec, int minuta) {
        this.strzelec = strzelec;
        this.minuta = minuta;
    }
    public Pilkarz getStrzelec() {
        return strzelec;
    }

    @Override
    public String toString() {
        return strzelec + " w minucie " + minuta;
    }
}

class Kartka {
    private Pilkarz pilkarz;
    private String kolor;
    private int minuta;

    public Kartka(Pilkarz pilkarz, String kolor, int minuta) {
        this.pilkarz = pilkarz;
        this.kolor = kolor;
        this.minuta = minuta;
    }

    @Override
    public String toString() {
        return kolor + " kartka dla " + pilkarz + " w minucie " + minuta;
    }
}

class Mecz {
    private String druzyna1;
    private String druzyna2;
    private List<Gol> gole;
    private List<Kartka> kartki;
    private int bramki1;
    private int bramki2;
    private boolean czyZaktualizowano;

    public Mecz(String druzyna1, String druzyna2) {
        this.druzyna1 = druzyna1;
        this.druzyna2 = druzyna2;
        this.gole = new ArrayList<>();
        this.kartki = new ArrayList<>();
        this.bramki1 = 0;
        this.bramki2 = 0;
        this.czyZaktualizowano = false;
    }

    public void dodajGol(String druzyna, Pilkarz strzelec, int minuta) {
        gole.add(new Gol(strzelec, minuta));
        if (druzyna.equals(druzyna1)) {
            bramki1++;
        } else if (druzyna.equals(druzyna2)) {
            bramki2++;
        }
    }

    public void dodajKartke(Pilkarz pilkarz, String kolor, int minuta) {
        kartki.add(new Kartka(pilkarz, kolor, minuta));
    }

    public String getDruzyna1() {
        return druzyna1;
    }

    public String getDruzyna2() {
        return druzyna2;
    }

    public int getBramki1() {
        return bramki1;
    }

    public int getBramki2() {
        return bramki2;
    }

    public boolean czyStrzelec(Pilkarz pilkarz) {
        for (Gol gol : gole) {
            if (gol.getStrzelec().equals(pilkarz)) {
                return true;
            }
        }
        return false;
    }

    public boolean isCzyZaktualizowano() {
        return czyZaktualizowano;
    }

    public void setCzyZaktualizowano(boolean czyZaktualizowano) {
        this.czyZaktualizowano = czyZaktualizowano;
    }

    @Override
    public String toString() {
        return "Mecz: " + druzyna1 + " vs " + druzyna2 + "\nGole: " + gole + "\nKartki: " + kartki + "\nWynik: " + bramki1 + " - " + bramki2;
    }
}



class Druzyna {
    private String nazwa;
    private int punkty;
    private int rozegraneMecze;
    private int wygrane;
    private int remisy;
    private int przegrane;
    private int bramkiZdobyte;
    private int bramkiStracone;

    public Druzyna(String nazwa) {
        this.nazwa = nazwa;
        resetujStatystyki();
    }

    public void dodajMecz(int zdobyte, int stracone) {
        rozegraneMecze++;
        bramkiZdobyte += zdobyte;
        bramkiStracone += stracone;
    }

    public void dodajWygrana() {
        wygrane++;
        punkty += 3;
    }

    public void dodajRemis() {
        remisy++;
        punkty += 1;
    }

    public void dodajPrzegrana() {
        przegrane++;
    }

    public void resetujStatystyki() {
        this.punkty = 0;
        this.rozegraneMecze = 0;
        this.wygrane = 0;
        this.remisy = 0;
        this.przegrane = 0;
        this.bramkiZdobyte =0;
        this.bramkiStracone = 0;
    }

    public String getNazwa() {
        return nazwa;
    }

    public int getPunkty() {
        return punkty;
    }

    public int getRozegraneMecze() {
        return rozegraneMecze;
    }

    @Override
    public String toString() {
        return String.format("%-20s %-10d %-10d %-10d %-10d %-10d %-10d", nazwa, punkty, rozegraneMecze, wygrane, remisy, przegrane, (bramkiZdobyte - bramkiStracone));
    }
}

class Tabela {
    private List<Druzyna> druzyny;
    public List<Mecz> mecze;

    public Tabela() {
        this.druzyny = new ArrayList<>();
        this.mecze = new ArrayList<>();
    }

    public void dodajDruzyne(Druzyna druzyna) {
        druzyny.add(druzyna);
    }

    public void dodajMecz(Mecz mecz) {
        mecze.add(mecz);
        aktualizujTabele();
    }

    public void aktualizujTabele() {
        for (Druzyna druzyna : druzyny) {
            druzyna.resetujStatystyki();
        }

        for (Mecz mecz : mecze) {
            Druzyna druzyna1 = znajdzDruzyne(mecz.getDruzyna1());
            Druzyna druzyna2 = znajdzDruzyne(mecz.getDruzyna2());

            if (druzyna1 != null && druzyna2 != null) {
                int bramki1 = mecz.getBramki1();
                int bramki2 = mecz.getBramki2();

                druzyna1.dodajMecz(bramki1, bramki2);
                druzyna2.dodajMecz(bramki2, bramki1);

                if (bramki1 > bramki2) {
                    druzyna1.dodajWygrana();
                    druzyna2.dodajPrzegrana();
                } else if (bramki1<bramki2) {
                    druzyna1.dodajPrzegrana();
                    druzyna2.dodajWygrana();
                } else {
                    druzyna1.dodajRemis();
                    druzyna2.dodajRemis();
                }
            }
        }

        sortujTabele();
    }

    private Druzyna znajdzDruzyne(String nazwa) {
        for (Druzyna druzyna: druzyny) {
            if (druzyna.getNazwa().equals(nazwa)) {
                return druzyna;
            }
        }
        return null;
    }

    public void sortujTabele() {
        Collections.sort(druzyny, new Comparator<Druzyna>() {
            @Override
            public int compare(Druzyna d1, Druzyna d2) {
                if (d2.getPunkty() != d1.getPunkty()) {
                    return Integer.compare(d2.getPunkty(), d1.getPunkty());
                } else {
                    return Integer.compare(d2.getRozegraneMecze(), d1.getRozegraneMecze());
                }
            }
        });
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-20s %-10s %-10s %-10s %-10s %-10s %-10s%n", "Drużyna", "Punkty", "Mecze", "Wygrane", "Remisy", "Przegrane", "Bilans bramek"));
        for (Druzyna druzyna : druzyny) {
            sb.append(druzyna).append("\n");
        }
        return sb.toString();
    }
}


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
                new Main().setVisible(true);
            }
        });
    }
}
