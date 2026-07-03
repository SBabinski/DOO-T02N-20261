import model.UserData;
import service.PersistenceService;
import service.TVMazeService;
import ui.MainFrame;
import ui.SetupDialog;

import javax.swing.*;
import java.io.IOException;

/**
 * Ponto de entrada do SeriesTracker.
 *
 * Fluxo de inicialização:
 *  1. Tenta carregar userData.json (dados persistidos).
 *  2. Se nao existe (primeira execucao): exibe SetupDialog para obter o nome
 *     do usuario e carrega dados de demonstracao pre-carregados.
 *  3. Abre a janela principal (MainFrame) na EDT do Swing.
 */
public class Main {

    public static void main(String[] args) {
        // Look & feel do sistema (melhor aparencia no Windows/Linux)
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ignorado) { /* Continua com o padrao */ }

        SwingUtilities.invokeLater(() -> {
            PersistenceService persistence = new PersistenceService();
            TVMazeService tvMaze           = new TVMazeService();
            UserData userData              = null;

            // ── Tentativa de carregar dados salvos ────────────────────────────
            try {
                userData = persistence.carregar();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null,
                    "Nao foi possivel ler o arquivo de dados.\n"
                    + "Um novo perfil sera criado.\n\nDetalhes: " + ex.getMessage(),
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            }

            // ── Primeira execucao: pede nome e carrega demo ───────────────────
            if (userData == null) {
                SetupDialog setup = new SetupDialog(null);
                setup.setVisible(true);               // bloqueia ate o usuario confirmar

                userData = new UserData(setup.getNomeUsuario());
                userData.carregarDadosIniciais();     // dados pre-carregados para demo

                try {
                    persistence.salvar(userData);     // persiste imediatamente
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null,
                        "Nao foi possivel criar o arquivo de dados.\n" + ex.getMessage(),
                        "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            }

            // ── Abre a janela principal ───────────────────────────────────────
            MainFrame frame = new MainFrame(userData, persistence, tvMaze);
            frame.setVisible(true);
        });
    }
}
