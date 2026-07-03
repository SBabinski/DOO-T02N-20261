package tvtracker;

import javax.swing.*;
import tvtracker.persistence.PersistenceException;
import tvtracker.service.LibraryService;
import tvtracker.ui.LoginDialog;
import tvtracker.ui.MainFrame;
import tvtracker.ui.UiUtils;


public final class Main {

    private Main() {
    }

    public static void main(String[] args) {
        
        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
            throwable.printStackTrace();
            SwingUtilities.invokeLater(() -> UiUtils.showError(null,
                    "Erro inesperado",
                    "Ocorreu um erro inesperado, mas o programa continuará em execução.\n\n"
                            + "Detalhes: " + throwable.getMessage()));
        });

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
   
            System.err.println("Não foi possível aplicar o visual do sistema: " + e.getMessage());
        }

        SwingUtilities.invokeLater(Main::startApplication);
    }

    private static void startApplication() {
        LibraryService service;
        try {
            service = new LibraryService();
        } catch (PersistenceException e) {
            UiUtils.showError(null, "Erro ao carregar dados",
                    "Não foi possível carregar ou criar o arquivo local de dados.\n\n"
                            + "Detalhes: " + e.getMessage()
                            + "\n\nVerifique se o programa tem permissão de escrita na sua pasta de usuário.");
            return;
        }

        
        LoginDialog loginDialog = new LoginDialog(null, service.getAllUserNames(), service.getCurrentUser().getName());
        loginDialog.setVisible(true);
        if (loginDialog.isConfirmed()) {
            try {
                service.switchUser(loginDialog.getSelectedUserName());
            } catch (PersistenceException | IllegalArgumentException e) {
                UiUtils.showError(null, "Erro ao definir usuário",
                        "Não foi possível salvar o usuário selecionado: " + e.getMessage()
                                + "\n\nO sistema continuará com o usuário anterior.");
            }
        }

        MainFrame frame = new MainFrame(service);
        frame.setVisible(true);
    }
}
