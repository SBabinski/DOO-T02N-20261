package tvtracker;

import javax.swing.*;
import tvtracker.model.UserData;
import tvtracker.service.DataService;
import tvtracker.service.TvMazeService;
import tvtracker.ui.MainWindow;

public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Aviso: não foi possível aplicar o tema do sistema: " + e.getMessage());
        }

        // Iniciar na Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            try {
                DataService dataService = new DataService();
                TvMazeService tvMazeService = new TvMazeService();
                UserData userData = dataService.load();

                MainWindow mainWindow = new MainWindow(userData, dataService, tvMazeService);
                mainWindow.setVisible(true);
                mainWindow.promptProfileIfNeeded();

            } catch (Exception e) {
                String msg = "Erro ao iniciar o TV Tracker:\n" + e.getMessage();
                System.err.println(msg);
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, msg, "Erro Fatal", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
        });
    }
}
