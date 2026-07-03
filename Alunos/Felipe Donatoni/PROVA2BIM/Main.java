import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {}

            String nome = JOptionPane.showInputDialog(null,
                "Como deseja ser chamado?", "Login", JOptionPane.QUESTION_MESSAGE);

            if (nome == null || nome.trim().isEmpty()) return;

            Usuario usuario    = new Usuario(nome.trim());
            ListaSeries listas = new ListaSeries();

            try {
                BancoDados bd  = new BancoDados();
                Object[] dados = bd.carregar();
                if (dados != null) {
                    usuario = (Usuario)     dados[0];
                    listas  = (ListaSeries) dados[1];
                }
            } catch (Exception e) {}

            new Janela(usuario, listas);
        });
    }
}
