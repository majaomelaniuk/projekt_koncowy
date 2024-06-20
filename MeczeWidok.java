import javax.swing.*;
import java.awt.*;

class MeczeWidok extends JPanel {
    private Tabela tabela;

    MeczeWidok(Tabela tabela) {
        this.tabela = tabela;
        setBackground(new Color(0, 100, 0));
        setLayout(new GridLayout(0, 2, 10, 10));
        odswiez();
    }

    public void odswiez() {
        removeAll();
        setLayout(new GridLayout(0, 2, 10, 10));

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
            button.setFont(new Font("Dialog", Font.BOLD, 16));
            button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
            button.setFocusPainted(false);

            add(button);
        }

        this.repaint();
        this.revalidate();
    }
}
