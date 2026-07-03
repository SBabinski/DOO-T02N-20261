import javax.swing.*;

public final class Main {

    private Main() {
    }

    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler((thread, erro) -> {
            erro.printStackTrace();
            SwingUtilities.invokeLater(() ->
                    JOptionPane.showMessageDialog(null,
                            "Ocorreu um erro inesperado:\n" + erro.getMessage(),
                            "Erro", JOptionPane.ERROR_MESSAGE));
        });

        SwingUtilities.invokeLater(Main::iniciarInterface);
    }

    private static void iniciarInterface() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }

        try {
            RepositorioDados repositorio = new RepositorioDados("dados.json");
            Biblioteca biblioteca = new Biblioteca(repositorio);
            biblioteca.iniciar();

            TVMazeClient cliente = new TVMazeClient();

            JanelaPrincipal janela = new JanelaPrincipal(biblioteca, cliente);
            janela.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Nao foi possivel iniciar o sistema:\n" + e.getMessage(),
                    "Erro de inicializacao", JOptionPane.ERROR_MESSAGE);
        }
    }
}