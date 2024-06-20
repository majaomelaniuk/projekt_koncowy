import javax.swing.*;
import java.awt.*;

class MeczPodgladWidok extends JDialog {
    private Mecz mecz;

    MeczPodgladWidok(JFrame parent, Mecz mecz) {
        super(parent, "Podgląd Meczu", true);
        this.mecz = mecz;

        setSize(600, 400);
        setLocationRelativeTo(parent);
        getContentPane().setBackground(new Color(0, 100, 0));
        setLayout(new BorderLayout());

        JLabel wynikLabel = new JLabel(mecz.getDruzyna1() + " " + mecz.getBramki1() + " - " + mecz.getBramki2() + " " + mecz.getDruzyna2(), SwingConstants.CENTER);
        wynikLabel.setFont(new Font("Arial", Font.BOLD, 24));
        wynikLabel.setForeground(Color.WHITE);
        add(wynikLabel, BorderLayout.NORTH);

        JEditorPane detailsArea = new JEditorPane();
        detailsArea.setContentType("text/html");
        detailsArea.setEditable(false);
        detailsArea.setForeground(Color.WHITE);
        detailsArea.setBackground(new Color(0, 100, 0));
        detailsArea.setFont(new Font("Arial", Font.PLAIN, 14));

        StringBuilder details = new StringBuilder();
        details.append("<html><body style='color:white; font-family:Arial;'>");
        details.append("<div style='display:flex; justify-content:space-around;'>");

        details.append("<div><h4>Gole:</h4>");
        for (Gol gol : mecz.getGole()) {
            details.append(gol.getStrzelec().getImie()).append(" ").append(gol.getStrzelec().getNazwisko())
                    .append(" - minuta ").append(gol.getMinuta()).append(" (")
                    .append(mecz.getDruzyna1().equals(gol.getStrzelec()) ? mecz.getDruzyna1() : mecz.getDruzyna2())
                    .append(")").append("<br>");
        }
        details.append("</div>");

        details.append("<div><h4>Kartki:</h4>");
        for (Kartka kartka : mecz.getKartki()) {
            details.append(kartka.getPilkarz().getImie()).append(" ").append(kartka.getPilkarz().getNazwisko())
                    .append(" - minuta ").append(kartka.getMinuta())
                    .append(" - ");
            if (kartka.getKolor().equals("żółta")) {
                details.append("<span style='color:yellow;'>■</span>");
            } else if (kartka.getKolor().equals("czerwona")) {
                details.append("<span style='color:red;'>■</span>");
            }
            details.append(" (").append(kartka.getDruzyna()).append(")").append("<br>");
        }
        details.append("</div>");

        details.append("</div></body></html>");

        detailsArea.setText(details.toString());

        JScrollPane scrollPane = new JScrollPane(detailsArea);
        add(scrollPane, BorderLayout.CENTER);

        JButton closeButton = new JButton("Zamknij");
        closeButton.addActionListener(e -> dispose());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(0, 100, 0));
        buttonPanel.add(closeButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
