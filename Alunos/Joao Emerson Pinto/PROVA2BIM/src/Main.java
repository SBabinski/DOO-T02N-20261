import persistence.JsonManager;
import ui.MainFrame;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                System.out.println("Não foi possível aplicar o visual do sistema.");
            }

            JsonManager jsonManager = new JsonManager();
            MainFrame frame = new MainFrame(jsonManager);
            frame.setVisible(true);
        });
    }
}
