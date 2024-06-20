import javax.swing.*;

class Main extends JFrame {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Tabela tabela = new Tabela();
                tabela.odczytaj("tabela.ser");

                tabela.sortujTabele();
                TabelaWidok tabelaWidok = new TabelaWidok(tabela);
                tabelaWidok.setVisible(true);
            }
        });
    }
}
