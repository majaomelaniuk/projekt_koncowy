import javax.swing.*;
import java.awt.*;

class MeczeWidok extends JFrame {
    MeczeWidok(Tabela tabela) {
        setTitle("Przegląd Meczów");
        getContentPane().setBackground(new Color(0, 100, 0));
        setLayout(new GridLayout(0, 2, 10, 10));

        for (Mecz mecz : tabela.mecze) {
            String buttonText = "<html><center>" + mecz.getDruzyna1() + "<br>" +
                    mecz.getBramki1() + " - " + mecz.getBramki2() + "<br>" +
                    mecz.getDruzyna2() + "</center></html>";

            JButton button = new JButton(buttonText);
            button.setBackground(new Color(0, 100, 0));
            button.setForeground(Color.WHITE);
            button.setFont(new Font("Dialog", Font.BOLD, 16));
            button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
            button.setFocusPainted(false);

            add(button);
        }

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
