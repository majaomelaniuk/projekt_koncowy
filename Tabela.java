import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.io.*;

class Czlowiek implements Serializable
{
    protected String imie;
    protected String nazwisko;

    public Czlowiek()
    {
        this.imie = "";
        this.nazwisko = "";
    }

    public Czlowiek(String imie, String nazwisko) {
        this.imie = imie;
        this.nazwisko = nazwisko;
    }

    public String getImie() { return imie; }
    public String getNazwisko() { return nazwisko; }

    public void zapisz(String plik)
    {
        try
        {
            FileOutputStream plikOut = new FileOutputStream(plik);
            ObjectOutputStream out = new ObjectOutputStream(plikOut);

            out.writeObject(this);
            out.close();
            System.out.println("Zapisano");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void odczytaj(String plik)
    {
        try
        {
            FileInputStream plikIn = new FileInputStream(plik);
            ObjectInputStream in = new ObjectInputStream(plikIn);

            Czlowiek pom = (Czlowiek) in.readObject();

            this.imie = pom.imie;
            this.nazwisko = pom.nazwisko;

            in.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Nie znaleziono klasy");
            e.printStackTrace();
        }
    }
}

class Trener extends Czlowiek {

    private int rok;

    public Trener()
    {
        super();
        this.rok = 0;
    }

    public Trener(String imie, String nazwisko, int rok) {
        super(imie, nazwisko);
        this.rok = rok;
    }

    public int getRok() { return this.rok; }

    @Override
    public String toString() {
        return imie + " " + nazwisko + "    (" + rok + ")";
    }

    @Override
    public void odczytaj(String plik)
    {
        try
        {
            FileInputStream plikIn = new FileInputStream(plik);
            ObjectInputStream in = new ObjectInputStream(plikIn);

            Trener pom = (Trener) in.readObject();

            this.imie = pom.imie;
            this.nazwisko = pom.nazwisko;
            this.rok = pom.rok;

            in.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Nie znaleziono klasy");
            e.printStackTrace();
        }
    }
}

class Pilkarz extends Czlowiek {
    protected int numer;
    protected int bramki = 0;

    public Pilkarz()
    {
        super();
        this.numer = 0;
    }

    public Pilkarz(String imie, String nazwisko, int numer) {
        super(imie, nazwisko);
        this.numer = numer;
    }

    public int getNumer() { return numer; }

    public void dodajBramke()
    {
        bramki++;
    }

    public int getBramki() { return this.bramki; }

    @Override
    public String toString() {
        return numer + ". " + imie + " " + nazwisko;
    }

    @Override
    public void odczytaj(String plik)
    {
        try
        {
            FileInputStream plikIn = new FileInputStream(plik);
            ObjectInputStream in = new ObjectInputStream(plikIn);

            Pilkarz pom = (Pilkarz) in.readObject();

            this.imie = pom.imie;
            this.nazwisko = pom.nazwisko;
            this.numer = pom.numer;

            in.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Nie znaleziono klasy");
            e.printStackTrace();
        }
    }
}

class Kapitan extends Pilkarz {
    private int rok;

    public Kapitan()
    {
        super();
        rok = 0;
    }

    public Kapitan(String imie, String nazwisko, int numer, int rok) {
        super(imie, nazwisko, numer);
        this.rok = rok;
    }

    public int getRok() { return rok; }

    @Override
    public String toString() {
        return super.toString() + "     (" + rok + ")";
    }

    @Override
    public void odczytaj(String plik)
    {
        try
        {
            FileInputStream plikIn = new FileInputStream(plik);
            ObjectInputStream in = new ObjectInputStream(plikIn);

            Kapitan pom = (Kapitan) in.readObject();

            this.imie = pom.imie;
            this.nazwisko = pom.nazwisko;
            this.numer = pom.numer;
            this.rok = pom.rok;

            in.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Nie znaleziono klasy");
            e.printStackTrace();
        }
    }
}

class Bramkarz extends Pilkarz {
    private int liczbaObron = 0;

    public Bramkarz() { super(); }

    public Bramkarz(String imie, String nazwisko, int numer) {
        super(imie, nazwisko, numer);
    }

    public void dodajObrone() {
        liczbaObron++;
    }

    public int getLiczbaObron() {
        return liczbaObron;
    }

    @Override
    public String toString() {
        return "(B) " + super.toString();
    }

    @Override
    public void odczytaj(String plik)
    {
        try
        {
            FileInputStream plikIn = new FileInputStream(plik);
            ObjectInputStream in = new ObjectInputStream(plikIn);

            Bramkarz pom = (Bramkarz) in.readObject();

            this.imie = pom.imie;
            this.nazwisko = pom.nazwisko;
            this.numer = pom.numer;
            this.liczbaObron = pom.liczbaObron;

            in.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Nie znaleziono klasy");
            e.printStackTrace();
        }
    }
}

class Gol implements Serializable {
    private Pilkarz strzelec;
    private int minuta;

    public Gol(Pilkarz strzelec, int minuta) {
        this.strzelec = strzelec;
        this.minuta = minuta;
    }

    public Pilkarz getStrzelec() {
        return strzelec;
    }

    public int getMinuta() {
        return minuta;
    }

    @Override
    public String toString() {
        return strzelec + " w minucie " + minuta;
    }

    public void zapisz(String plik) {
        try {
            FileOutputStream plikOut = new FileOutputStream(plik);
            ObjectOutputStream out = new ObjectOutputStream(plikOut);

            out.writeObject(this);
            out.close();
            System.out.println("Zapisano");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void odczytaj(String plik) {
        try {
            FileInputStream plikIn = new FileInputStream(plik);
            ObjectInputStream in = new ObjectInputStream(plikIn);

            Gol pom = (Gol) in.readObject();

            this.strzelec = pom.strzelec;
            this.minuta = pom.minuta;

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Nie znaleziono klasy");
            e.printStackTrace();
        }
    } 
}

class Kartka implements Serializable {
    private Pilkarz pilkarz;
    private String kolor;
    private int minuta;
    private String druzyna;

    public Kartka(Pilkarz pilkarz, String kolor, int minuta, String druzyna) {
        this.pilkarz = pilkarz;
        this.kolor = kolor;
        this.minuta = minuta;
        this.druzyna = druzyna;
    }

    public Pilkarz getPilkarz() {
        return pilkarz;
    }

    public String getKolor() {
        return kolor;
    }

    public int getMinuta() {
        return minuta;
    }

    public String getDruzyna() {
        return druzyna;
    }

    @Override
    public String toString() {
        return kolor + " kartka dla " + pilkarz + " w minucie " + minuta;
    }

    public void zapisz(String plik) {
        try {
            FileOutputStream plikOut = new FileOutputStream(plik);
            ObjectOutputStream out = new ObjectOutputStream(plikOut);

            out.writeObject(this);
            out.close();
            System.out.println("Zapisano");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void odczytaj(String plik) {
        try {
            FileInputStream plikIn = new FileInputStream(plik);
            ObjectInputStream in = new ObjectInputStream(plikIn);

            Kartka pom = (Kartka) in.readObject();

            this.pilkarz = pom.pilkarz;
            this.kolor = pom.kolor;
            this.minuta = pom.minuta;
            this.druzyna = pom.druzyna;

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Nie znaleziono klasy");
            e.printStackTrace();
        }
    }
}


class Mecz implements Serializable {
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

    public void dodajKartke(Pilkarz pilkarz, String kolor, int minuta, String druzyna) {
        kartki.add(new Kartka(pilkarz, kolor, minuta, druzyna));
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

    public List<Gol> getGole() {
        return gole;
    }

    public List<Kartka> getKartki() {
        return kartki;
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

    public void zapisz(String plik) {
        try {
            FileOutputStream plikOut = new FileOutputStream(plik);
            ObjectOutputStream out = new ObjectOutputStream(plikOut);

            out.writeObject(this);
            out.close();
            System.out.println("Zapisano");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void odczytaj(String plik) {
        try {
            FileInputStream plikIn = new FileInputStream(plik);
            ObjectInputStream in = new ObjectInputStream(plikIn);

            Mecz pom = (Mecz) in.readObject();

            this.druzyna1 = pom.druzyna1;
            this.druzyna2 = pom.druzyna2;
            this.gole = pom.gole;
            this.kartki = pom.kartki;
            this.bramki1 = pom.bramki1;
            this.bramki2 = pom.bramki2;
            this.czyZaktualizowano = pom.czyZaktualizowano;

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Nie znaleziono klasy");
            e.printStackTrace();
        }
    }
}


class Druzyna implements Serializable
{
    private String nazwa;
    private int punkty;
    private int rozegraneMecze;
    private int wygrane;
    private int remisy;
    private int przegrane;
    private int bramkiZdobyte;
    private int bramkiStracone;
    private Trener trener;
    private Kapitan kapitan;
    private List <Pilkarz> pilkarze;

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
        this.trener = new Trener();
        this.kapitan = new Kapitan();
        this.pilkarze = new ArrayList<>();
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

    public int getWygrane() {
        return wygrane;
    }

    public int getRemisy() {
        return remisy;
    }

    public int getPrzegrane() {
        return przegrane;
    }

    public int getBramkiZdobyte() {
        return bramkiZdobyte;
    }

    public int getBramkiStracone() {
        return bramkiStracone;
    }

    public void setTrener(Trener trener)
    {
        this.trener = trener;
    }

    public void setKapitan(Kapitan kapitan)
    {
        this.kapitan = kapitan;
    }

    public void dodajPilkarza(Pilkarz pilkarz)
    {
        this.pilkarze.add(pilkarz);
    }

    public Trener getTrener()
    {
        return trener;
    }

    public Kapitan getKapitan()
    {
        return kapitan;
    }

    public List<Pilkarz> getPilkarze()
    {
        return pilkarze;
    }

    @Override
    public String toString() {
        return String.format("%-20s %-10d %-10d %-10d %-10d %-10d %-10d", nazwa, punkty, rozegraneMecze, wygrane, remisy, przegrane, (bramkiZdobyte - bramkiStracone));
    }

    public void zapisz(String plik)
    {
        try
        {
            FileOutputStream plikOut = new FileOutputStream(plik);
            ObjectOutputStream out = new ObjectOutputStream(plikOut);

            out.writeObject(this);
            out.close();
            System.out.println("Zapisano");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void odczytaj(String plik)
    {
        try
        {
            FileInputStream plikIn = new FileInputStream(plik);
            ObjectInputStream in = new ObjectInputStream(plikIn);

            Druzyna pom = (Druzyna) in.readObject();

            this.nazwa = pom.nazwa;
            this.punkty = pom.punkty;
            this.rozegraneMecze = pom.rozegraneMecze;
            this.wygrane = pom.wygrane;
            this.remisy = pom.remisy;
            this.przegrane = pom.przegrane;
            this.bramkiZdobyte = pom.bramkiZdobyte;
            this.bramkiStracone = pom.bramkiStracone;

            in.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Nie znaleziono klasy");
            e.printStackTrace();
        }
    }
}

public class Tabela implements Serializable
{
    public List<Druzyna> druzyny;
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

    private Druzyna znajdzDruzyne(String nazwa) {
        for (Druzyna druzyna: druzyny) {
            if (druzyna.getNazwa().equals(nazwa)) {
                return druzyna;
            }
        }
        return null;
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

    public void zapisz(String plik)
    {
        try
        {
            FileOutputStream plikOut = new FileOutputStream(plik);
            ObjectOutputStream out = new ObjectOutputStream(plikOut);

            out.writeObject(this);
            out.close();
            plikOut.close();
            System.out.println("Zapisano");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void odczytaj(String plik)
    {
        try
        {
            FileInputStream plikIn = new FileInputStream(plik);
            ObjectInputStream in = new ObjectInputStream(plikIn);

            Tabela pom = (Tabela) in.readObject();

            this.druzyny = pom.druzyny;
            this.mecze = pom.mecze;

            in.close();
            plikIn.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Nie znaleziono klasy");
            e.printStackTrace();
        }
    }
}
