import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;

class DodajMeczWidok extends JFrame {
    private JFrame okno;
    private JComboBox<Druzyna> druzynyComboBox1;
    private JComboBox<Druzyna> druzynyComboBox2;
    private JComboBox<String> kolorKartkiComboBox;
    private JComboBox<Pilkarz> pilkarzKartkiComboBox;
    private Druzyna wybranaDruzyna1;
    private Druzyna wybranaDruzyna2;
    private JTable _kartki;
    private JTable _bramki;
    private JTextField _minuta;

    public DodajMeczWidok(Tabela tabela) {
        okno = new JFrame("Dodaj Mecz");
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.setLayout(new GridLayout(5, 2));
        okno.setBackground(new Color(0, 100, 0));

        JPanel panelDruzyn = new JPanel(new FlowLayout());
        panelDruzyn.setBackground(new Color(0, 100, 0));

        JPanel panelDruzyna1 = new JPanel();
        panelDruzyna1.setLayout(new BoxLayout(panelDruzyna1, BoxLayout.PAGE_AXIS));
        panelDruzyna1.setBackground(new Color(0, 100, 0));

        druzynyComboBox1 = new JComboBox<>();
        for (Druzyna druzyna : tabela.druzyny) {
            druzynyComboBox1.addItem(druzyna);
        }
        druzynyComboBox1.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Druzyna) {
                    setText(((Druzyna) value).getNazwa());
                }
                return this;
            }
        });
        druzynyComboBox1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                wybranaDruzyna1 = (Druzyna)druzynyComboBox1.getSelectedItem();
            }
        });

        JPanel panelDruzyna2 = new JPanel();
        panelDruzyna2.setLayout(new BoxLayout(panelDruzyna2, BoxLayout.PAGE_AXIS));
        panelDruzyna2.setBackground(new Color(0, 100, 0));

        druzynyComboBox2 = new JComboBox<>();
        for (Druzyna druzyna : tabela.druzyny) {
            druzynyComboBox2.addItem(druzyna);
        }
        druzynyComboBox2.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Druzyna) {
                    setText(((Druzyna) value).getNazwa());
                }
                return this;
            }
        });
        druzynyComboBox2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                wybranaDruzyna2 = (Druzyna)druzynyComboBox2.getSelectedItem();
            }
        });

        JLabel labelDruzyna1 = new JLabel("Wybierz drużynę 1:");
        labelDruzyna1.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        labelDruzyna1.setMaximumSize(new Dimension(Integer.MAX_VALUE, labelDruzyna1.getMinimumSize().height));
        labelDruzyna1.setForeground(Color.WHITE);
        panelDruzyna1.add(labelDruzyna1);
        panelDruzyna1.add(druzynyComboBox1);

        JLabel labelDruzyna2 = new JLabel("Wybierz drużynę 2:");
        labelDruzyna2.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        labelDruzyna2.setMaximumSize(new Dimension(Integer.MAX_VALUE, labelDruzyna1.getMinimumSize().height));
        labelDruzyna2.setForeground(Color.WHITE);
        panelDruzyna2.add(labelDruzyna2);
        panelDruzyna2.add(druzynyComboBox2);

        panelDruzyn.add(panelDruzyna1);
        panelDruzyn.add(panelDruzyna2);

        okno.add(panelDruzyn);


        okno.pack();
        okno.setVisible(true);
    }
}

