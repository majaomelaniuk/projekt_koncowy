import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class DruzynyWidok extends JPanel{

    DruzynyWidok(Tabela tabela){
        setBackground(new Color(0, 100, 0));

        String[] nazwy_kolumn = {"Klub", "Pkt", "RM", "W", "R", "P", "BZ", "BS"};

        Object[][] dane = new Object[tabela.druzyny.size()][8];
        for (int i = 0; i < tabela.druzyny.size(); i++) {
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


        DefaultTableModel model = new DefaultTableModel(dane, nazwy_kolumn) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable wiersze = new JTable(model);
        wiersze.setRowHeight(30);
        wiersze.setShowVerticalLines(false);
        ((DefaultTableCellRenderer) wiersze.getDefaultRenderer(Object.class)).setBorder(BorderFactory.createLineBorder(Color.WHITE));

        wiersze.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = wiersze.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    new DruzynaPodgladWidok(tabela.druzyny.get(row));
                }
            }
        });

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 1; i < wiersze.getColumnCount(); i++) {
            wiersze.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        wiersze.setBackground(new Color(0, 100, 0));
        wiersze.setForeground(Color.WHITE);

        JTableHeader header = wiersze.getTableHeader();
        header.setFont(header.getFont().deriveFont(Font.BOLD));

        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setBorder(BorderFactory.createEmptyBorder());

        header.setBackground(new Color(0, 100, 0));
        header.setForeground(Color.WHITE);

        JScrollBar przewijak = new JScrollBar() {
            @Override
            public void updateUI() {
                setUI(new BasicScrollBarUI() {
                    @Override
                    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
                        g.setColor(Color.WHITE);
                        g.fillRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height);
                    }

                    @Override
                    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
                        g.setColor(new Color(0, 100, 0));
                        g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
                    }

                    @Override
                    protected JButton createDecreaseButton(int orientation) {
                        return brak_przycisku();
                    }

                    @Override
                    protected JButton createIncreaseButton(int orientation) {
                        return brak_przycisku();
                    }

                    private JButton brak_przycisku() {
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

        this.repaint();
        this.revalidate();
        add(przewijalne);
    }
}
