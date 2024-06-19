import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

class TabelaWidok extends JFrame {

    CardLayout uklad_kart = new CardLayout();
    JPanel panel = new JPanel(uklad_kart);

    TabelaWidok(Tabela tabela) {
        UIManager.put("TextField.selectionBackground", new Color(124, 252, 0));
        UIManager.put("TextArea.selectionBackground", new Color(124, 252, 0));
        UIManager.put("TextPane.selectionBackground", new Color(124, 252, 0));
        UIManager.put("EditorPane.selectionBackground", new Color(124, 252, 0));
        UIManager.put("FormattedTextField.selectionBackground", new Color(124, 252, 0));
        UIManager.put("PasswordField.selectionBackground", new Color(124, 252, 0));
        UIManager.put("List.selectionBackground", new Color(124, 252, 0));
        UIManager.put("Table.selectionBackground", new Color(124, 252, 0));
        UIManager.put("ComboBox.selectionBackground", new Color(124, 252, 0));

        setTitle("Tabela drużyn");
        getContentPane().setBackground(new Color(0, 100, 0));

        JPanel panel_druzyn = new DruzynyWidok(tabela);
        JPanel panel_meczy = new MeczeWidok(tabela);

        panel.add(panel_druzyn, "Drużyny");
        panel.add(panel_meczy, "Mecze");

        uklad_kart.show(panel, "Drużyny");

        JPanel gorny_panel = new JPanel();
        gorny_panel.setLayout(new BorderLayout());
        gorny_panel.setBackground(new Color(0, 100, 0));

        try {
            Image obrazek_pilki = ImageIO.read(new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/e/ec/Soccer_ball.svg/440px-Soccer_ball.svg.png"));
            Image wyskalowany_obrazek_pilki = obrazek_pilki.getScaledInstance(90, 90, Image.SCALE_DEFAULT);
            ImageIcon ikonka_obrazka_pilki = new ImageIcon(wyskalowany_obrazek_pilki);
            JLabel obszar_obrazka_pilki = new JLabel(ikonka_obrazka_pilki);
            obszar_obrazka_pilki.setHorizontalAlignment(JLabel.CENTER);
            obszar_obrazka_pilki.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            gorny_panel.add(obszar_obrazka_pilki, BorderLayout.CENTER);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JPanel gorne_przyciski = new JPanel();
        gorne_przyciski.setLayout(new GridLayout(1, 2));

        JButton druzyny = new JButton("Drużyny");
        druzyny.setBackground(new Color(0, 100, 0));
        druzyny.setForeground(Color.WHITE);
        druzyny.setFont(new Font("Dialog", Font.BOLD, 16));
        gorne_przyciski.add(druzyny);

        druzyny.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uklad_kart.show(panel, "Drużyny");
            }
        });

        JButton mecze = new JButton("Mecze");
        mecze.setBackground(new Color(0, 100, 0));
        mecze.setForeground(Color.WHITE);
        mecze.setFont(new Font("Dialog", Font.BOLD, 16));
        gorne_przyciski.add(mecze);

        mecze.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uklad_kart.show(panel, "Mecze");
            }
        });

        gorny_panel.add(gorne_przyciski, BorderLayout.SOUTH);

        JButton dodaj = new JButton("Dodaj drużynę");

        JPanel dolny_panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        dodaj.setBackground(new Color(0, 100, 0));
        dodaj.setForeground(Color.WHITE);
        dodaj.setFont(new Font("Dialog", Font.BOLD, 12));

        dolny_panel.add(dodaj);
        dolny_panel.setBackground(new Color(0, 100, 0));
        dolny_panel.setForeground(Color.WHITE);

        add(gorny_panel, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        add(dolny_panel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pack();
        setVisible(true);
    }
}
