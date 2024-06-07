import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.net.URL;

import java.awt.*;
import java.io.IOException;

import javax.imageio.ImageIO;

class TabelaWidok extends JFrame
{
    TabelaWidok(Tabela tabela)
    {
        UIManager.put("TextField.selectionBackground", new Color(124, 252, 0));
        UIManager.put("TextArea.selectionBackground", new Color(124, 252, 0));
        UIManager.put("TextPane.selectionBackground", new Color(124, 252, 0));
        UIManager.put("EditorPane.selectionBackground", new Color(124, 252, 0));
        UIManager.put("FormattedTextField.selectionBackground", new Color(124, 252, 0));
        UIManager.put("PasswordField.selectionBackground", new Color(124, 252, 0));
        UIManager.put("List.selectionBackground", new Color(124, 252, 0));
        UIManager.put("Table.selectionBackground", new Color(124, 252, 0));
        UIManager.put("ComboBox.selectionBackground", new Color(124, 252, 0));

        setTitle("Tabela druzyn");
        getContentPane().setBackground(new Color(0, 100, 0));

        String[] nazwy_kolumn = {"Klub", "Pkt", "RM", "W", "R", "P", "BZ", "BS"};

        Object[][] dane = new Object[tabela.druzyny.size()][8];
        for (int i = 0; i < tabela.druzyny.size(); i++)
        {
            Druzyna d = tabela.druzyny.get(i);
            dane[i][0] = d.getNazwa();
            dane[i][1] = d.getPunkty();
            dane[i][2] = d.getRozegraneMecze();
            dane[i][3] = d.getWygrane();
            dane[i][4] = d.getRemisy();
            dane[i][5] = d.getPrzegrane();
            dane[i][6] = d.getBramkiZdobyte();
            dane[i][7] = d.getBramkiStracone();
        }

        JTable wiersze = new JTable(dane, nazwy_kolumn);
        wiersze.setRowHeight(30);
        wiersze.setShowVerticalLines(false);
        ((DefaultTableCellRenderer)wiersze.getDefaultRenderer(Object.class)).setBorder(BorderFactory.createLineBorder(Color.WHITE));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        for (int i=1; i<wiersze.getColumnCount(); i++)
        {
            wiersze.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
        }

        wiersze.setBackground(new Color(0, 100, 0));
        wiersze.setForeground(Color.WHITE);
        

        JTableHeader header = wiersze.getTableHeader();
        header.setFont(header.getFont().deriveFont(Font.BOLD));

        ((DefaultTableCellRenderer)header.getDefaultRenderer()).setBorder(BorderFactory.createEmptyBorder());

        header.setBackground(new Color(0, 100, 0));
        header.setForeground(Color.WHITE);


        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(0, 100, 0));

        JScrollBar przewijak = new JScrollBar() {
            @Override
            public void updateUI()
            {
                setUI(new BasicScrollBarUI()
                {
                    @Override
                    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds)
                    {
                        g.setColor(Color.WHITE);
                        g.fillRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height);
                    }

                    @Override
                    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds)
                    {
                        g.setColor(new Color(0, 100, 0));
                        g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
                    }

                    @Override
                    protected JButton createDecreaseButton(int orientation)
                    {
                        return brak_przycisku();
                    }
        
                    @Override
                    protected JButton createIncreaseButton(int orientation)
                    {
                        return brak_przycisku();
                    }
        
                    private JButton brak_przycisku()
                    {
                        JButton button = new JButton();
                        button.setPreferredSize(new Dimension(0, 0));
                        button.setMinimumSize(new Dimension(0, 0));
                        button.setMaximumSize(new Dimension(0, 0));
                        return button;
                    }
                });
            }
        };
        
        JPanel naroznik = new JPanel();
        naroznik.setBackground(new Color(0, 100, 0));

        JScrollPane przewijalne = new JScrollPane(wiersze);
        przewijalne.setCorner(JScrollPane.UPPER_RIGHT_CORNER, naroznik);
        przewijalne.setVerticalScrollBar(przewijak);
        przewijalne.getViewport().setBackground(new Color(0, 100, 0));
        panel.add(przewijalne, BorderLayout.CENTER);
        panel.setSize(300, 150);
        panel.setVisible(true);
        
        
        JPanel gorny_panel = new JPanel();
        gorny_panel.setLayout(new BorderLayout());
        gorny_panel.setBackground(new Color(0, 100, 0));

        try
        {
            Image obrazek_pilki = ImageIO.read(new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/e/ec/Soccer_ball.svg/440px-Soccer_ball.svg.png"));
            Image wyskalowany_obrazek_pilki = obrazek_pilki.getScaledInstance(90, 90, Image.SCALE_DEFAULT);
            ImageIcon ikonka_obrazka_pilki = new ImageIcon(wyskalowany_obrazek_pilki);
            JLabel obszar_obrazka_pilki = new JLabel(ikonka_obrazka_pilki);
            obszar_obrazka_pilki.setHorizontalAlignment(JLabel.CENTER);
            obszar_obrazka_pilki.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            gorny_panel.add(obszar_obrazka_pilki, BorderLayout.CENTER);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        JPanel gorne_przyciski = new JPanel();
        gorne_przyciski.setLayout(new GridLayout(1, 2));

        JButton druzyny = new JButton("Druzyny");
        druzyny.setBackground(new Color(0, 100, 0));
        druzyny.setForeground(Color.WHITE);
        druzyny.setFont(new Font("Dialog", Font.BOLD, 16));
        gorne_przyciski.add(druzyny);
        
        JButton mecze = new JButton("Mecze");
        mecze.setBackground(new Color(0, 100, 0));
        mecze.setForeground(Color.WHITE);
        mecze.setFont(new Font("Dialog", Font.BOLD, 16));
        gorne_przyciski.add(mecze);
        
        gorny_panel.add(gorne_przyciski, BorderLayout.SOUTH);
    
        JButton dodaj = new JButton("Dodaj druzyne");

        JPanel dolny_panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        dodaj.setBackground(new Color(0, 100, 0));
        dodaj.setForeground(Color.WHITE);
        dodaj.setFont(new Font("Dialog", Font.BOLD, 12));

        dolny_panel.add(dodaj);
        dolny_panel.setBackground(new Color(0, 100, 0));
        dolny_panel.setForeground(Color.WHITE);

        panel.add(gorny_panel, BorderLayout.NORTH);
        panel.add(dolny_panel, BorderLayout.SOUTH);

        add(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pack();
        setVisible(true);
    }
}
