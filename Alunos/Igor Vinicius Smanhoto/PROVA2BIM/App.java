package com.tvtracker;

import com.tvtracker.model.UserData;
import com.tvtracker.service.DataPersistenceService;
import com.tvtracker.ui.AppTheme;
import com.tvtracker.ui.MainFrame;
import com.tvtracker.ui.WelcomeDialog;

import javax.swing.*;

/**
 * Ponto de entrada do aplicativo TV Tracker.
 * Inicializa o tema visual, carrega os dados do usuário e abre a janela principal.
 */
public class App {

    public static void main(String[] args) {
        // Toda inicialização de UI deve ocorrer na Event Dispatch Thread do Swing
        SwingUtilities.invokeLater(App::start);
    }

    private static void start() {
        // Usa o L&F básico multiplataforma (Metal/Nimbus) como base —
        // o L&F do Windows sobrescreve cores de botões, abas e campos de texto,
        // quebrando o tema escuro mesmo com UIManager.put() aplicado.
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (Exception e) {
            // Silencioso: continuará com o L&F padrão
        }

        // Aplica o tema escuro personalizado após definir o L&F base
        AppTheme.apply();

        DataPersistenceService persistenceService = new DataPersistenceService();
        UserData userData;

        // Carrega os dados persistidos em execuções anteriores
        try {
            userData = persistenceService.load();
        } catch (Exception e) {
            // Se falhar ao carregar (arquivo corrompido, etc.), começa com dados novos
            userData = new UserData();
            JOptionPane.showMessageDialog(null,
                    "Não foi possível carregar os dados salvos.\n"
                    + "Um novo perfil será criado.\n\nDetalhe: " + e.getMessage(),
                    "Aviso de inicialização", JOptionPane.WARNING_MESSAGE);
        }

        // Exibe a tela de cadastro de nome se não houver dados salvos (primeira execução ou após reset).
        // Usa um UserData limpo nesse caso para garantir que nenhum nome anterior apareça.
        boolean precisaCadastro = !persistenceService.dataFileExists()
                || userData.getUserName() == null
                || userData.getUserName().trim().isEmpty();

        if (precisaCadastro) {
            userData = new UserData(); // garante objeto limpo, sem resquício de nome anterior
            WelcomeDialog welcome = new WelcomeDialog(userData, persistenceService);
            welcome.setVisible(true);

            // Se o usuário fechar sem confirmar o nome, encerra o aplicativo
            if (!welcome.isConfirmed()) {
                System.exit(0);
            }
        }

        // Abre a janela principal com os dados do usuário (novo ou carregado)
        MainFrame mainFrame = new MainFrame(userData, persistenceService);
        mainFrame.setVisible(true);
    }
}
