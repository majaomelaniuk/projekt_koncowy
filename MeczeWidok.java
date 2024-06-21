import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MeczeWidok extends JPanel {
    private Tabela tabela;
    private JPanel meczePanel;

    MeczeWidok(Tabela tabela) {
        this.tabela = tabela;
        setBackground(new Color(0, 100, 0));
        setLayout(new BorderLayout());

        meczePanel = new JPanel();
        meczePanel.setLayout(new GridLayout(0, 2, 10, 10));
        meczePanel.setBackground(new Color(0, 100, 0));

        JScrollPane scrollPane = new JScrollPane(meczePanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        add(scrollPane, BorderLayout.CENTER);

        odswiez();
    }

    public void odswiez() {
        meczePanel.removeAll();
        meczePanel.setLayout(new GridLayout(0, 2, 10, 10));

        for (Mecz mecz : tabela.mecze) {
            StringBuilder buttonTextBuilder = new StringBuilder("<html><center>");
            buttonTextBuilder.append(mecz.getDruzyna1());

            for (Kartka kartka : mecz.getKartki()) {
                if (mecz.getDruzyna1().equals(kartka.getDruzyna())) {
                    if (kartka.getKolor().equals("żółta")) {
                        buttonTextBuilder.append(" <span style='color:yellow;'>■</span>");
                    } else if (kartka.getKolor().equals("czerwona")) {
                        buttonTextBuilder.append(" <span style='color:red;'>■</span>");
                    }
                }
            }

            buttonTextBuilder.append("<br>");
            buttonTextBuilder.append(mecz.getBramki1()).append(" - ").append(mecz.getBramki2());
            buttonTextBuilder.append("<br>");
            buttonTextBuilder.append(mecz.getDruzyna2());

            for (Kartka kartka : mecz.getKartki()) {
                if (mecz.getDruzyna2().equals(kartka.getDruzyna())) {
                    if (kartka.getKolor().equals("żółta")) {
                        buttonTextBuilder.append(" <span style='color:yellow;'>■</span>");
                    } else if (kartka.getKolor().equals("czerwona")) {
                        buttonTextBuilder.append(" <span style='color:red;'>■</span>");
                    }
                }
            }

            buttonTextBuilder.append("</center></html>");
            String buttonText = buttonTextBuilder.toString();

            JButton button = new JButton(buttonText);
            button.setBackground(new Color(0, 100, 0));
            button.setForeground(Color.WHITE);
            button.setFont(new Font("Dialog", Font.BOLD, 14));
            button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
            button.setFocusPainted(false);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new MeczPodgladWidok((JFrame) SwingUtilities.getWindowAncestor(MeczeWidok.this), mecz);
                }
            });

            meczePanel.add(button);
        }

        meczePanel.repaint();
        meczePanel.revalidate();
    }
}
