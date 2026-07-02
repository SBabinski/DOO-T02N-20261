package com.tvtracker;

import com.tvtracker.ui.AppTheme;
import com.tvtracker.ui.MainWindow;

import javax.swing.*;

/**
 * Ponto de entrada da aplicação TV Tracker.
 */
public class Main {

    public static void main(String[] args) {
        // Configura o look and feel do sistema
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Continua com o look and feel padrão
        }

        // Aplica configurações globais de UI
        UIManager.put("OptionPane.background", AppTheme.BG_PANEL);
        UIManager.put("Panel.background", AppTheme.BG_PANEL);
        UIManager.put("OptionPane.messageForeground", AppTheme.TEXT_PRIMARY);
        UIManager.put("Button.background", AppTheme.BG_CARD);
        UIManager.put("Button.foreground", AppTheme.TEXT_PRIMARY);
        UIManager.put("ComboBox.background", AppTheme.BG_INPUT);
        UIManager.put("ComboBox.foreground", AppTheme.TEXT_PRIMARY);
        UIManager.put("TextField.background", AppTheme.BG_INPUT);
        UIManager.put("TextField.foreground", AppTheme.TEXT_PRIMARY);
        UIManager.put("TextField.caretForeground", AppTheme.ACCENT_LIGHT);
        UIManager.put("ScrollBar.thumb", AppTheme.BORDER);
        UIManager.put("ScrollBar.background", AppTheme.BG_DARK);

        // Inicia a aplicação na thread de eventos do Swing
        SwingUtilities.invokeLater(() -> {
            try {
                MainWindow window = new MainWindow();
                window.setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "Erro fatal ao iniciar o TV Tracker:\n" + e.getMessage(),
                        "Erro Crítico",
                        JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
        });
    }
}
