package com.tvtracker.ui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Define a paleta de cores e estilos visuais do aplicativo.
 * Centraliza o tema para facilitar mudanças futuras.
 */
public class AppTheme {

    // Cores principais da identidade visual
    public static final Color BG_DARK        = new Color(18, 18, 28);
    public static final Color BG_PANEL       = new Color(28, 28, 42);
    public static final Color BG_CARD        = new Color(38, 38, 56);
    public static final Color ACCENT         = new Color(229, 57, 53);   // vermelho Netflix-like
    public static final Color TEXT_PRIMARY   = new Color(240, 240, 255);
    public static final Color TEXT_SECONDARY = new Color(160, 160, 190);
    public static final Color TEXT_MUTED     = new Color(100, 100, 130);
    public static final Color GOLD           = new Color(255, 193, 7);   // estrela de nota
    public static final Color GREEN          = new Color(76, 175, 80);   // "em exibição"
    public static final Color GRAY_STATUS    = new Color(120, 120, 140); // "encerrada"

    // Fontes da aplicação
    public static final Font FONT_TITLE    = new Font("Segoe UI", Font.BOLD, 22);
    public static final Font FONT_SUBTITLE = new Font("Segoe UI", Font.BOLD, 14);
    public static final Font FONT_BODY     = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font FONT_SMALL    = new Font("Segoe UI", Font.PLAIN, 11);

    // Bordas reutilizáveis
    public static final Border BORDER_FOCUS = BorderFactory.createLineBorder(ACCENT, 2);
    public static final Border BORDER_CARD  = BorderFactory.createLineBorder(new Color(60, 60, 80), 1);

    // Aplica configurações globais do Look and Feel ao iniciar
    public static void apply() {
        UIManager.put("Panel.background", BG_PANEL);
        UIManager.put("OptionPane.background", BG_PANEL);
        UIManager.put("OptionPane.messageForeground", TEXT_PRIMARY);
        UIManager.put("Button.background", BG_CARD);
        UIManager.put("Button.foreground", TEXT_PRIMARY);
        UIManager.put("TextField.background", BG_CARD);
        UIManager.put("TextField.foreground", TEXT_PRIMARY);
        UIManager.put("TextField.caretForeground", TEXT_PRIMARY);
        UIManager.put("List.background", BG_CARD);
        UIManager.put("List.foreground", TEXT_PRIMARY);
        UIManager.put("List.selectionBackground", ACCENT);
        UIManager.put("List.selectionForeground", Color.WHITE);
        UIManager.put("ScrollPane.background", BG_PANEL);
        UIManager.put("ScrollBar.background", BG_PANEL);
        UIManager.put("TabbedPane.background", BG_DARK);
        UIManager.put("TabbedPane.foreground", TEXT_PRIMARY);
        UIManager.put("TabbedPane.selected", BG_PANEL);
        UIManager.put("ComboBox.background", BG_CARD);
        UIManager.put("ComboBox.foreground", TEXT_PRIMARY);
        UIManager.put("Label.foreground", TEXT_PRIMARY);
    }

    // Cria um botão estilizado com as cores do tema e efeito hover.
    // Força BasicButtonUI para evitar que o L&F do Windows sobrescreva as cores.
    public static JButton createButton(String text, final Color bg) {
        JButton btn = new JButton(text);
        // Substitui o UI do sistema pelo básico — impede o Windows de pintar o botão de branco
        btn.setUI(new BasicButtonUI());
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFont(FONT_BODY);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));

        // Efeito hover para tornar a UI mais responsiva ao mouse
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(bg.brighter());
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(bg);
            }
        });

        return btn;
    }

    // Retorna a cor adequada para o status da série
    public static Color getStatusColor(String status) {
        if (status == null) return GRAY_STATUS;
        switch (status) {
            case "Running": return GREEN;
            case "Ended":   return GRAY_STATUS;
            default:        return TEXT_SECONDARY;
        }
    }
}
