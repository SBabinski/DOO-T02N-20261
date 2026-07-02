package com.tvtracker.ui;

import java.awt.*;

/**
 * Constantes de tema visual da aplicação.
 */
public class AppTheme {

    // Cores principais
    public static final Color BG_DARK       = new Color(18, 18, 30);
    public static final Color BG_PANEL      = new Color(26, 26, 46);
    public static final Color BG_CARD       = new Color(35, 35, 60);
    public static final Color BG_INPUT      = new Color(40, 40, 70);
    public static final Color ACCENT        = new Color(99, 102, 241);   // roxo/índigo
    public static final Color ACCENT_HOVER  = new Color(129, 140, 248);
    public static final Color ACCENT_LIGHT  = new Color(199, 210, 254);
    public static final Color TEXT_PRIMARY  = new Color(236, 237, 255);
    public static final Color TEXT_SECONDARY= new Color(156, 163, 200);
    public static final Color TEXT_MUTED    = new Color(100, 110, 150);
    public static final Color BORDER        = new Color(55, 55, 90);
    public static final Color SUCCESS       = new Color(52, 211, 153);
    public static final Color WARNING       = new Color(251, 191, 36);
    public static final Color DANGER        = new Color(248, 113, 113);
    public static final Color STAR_YELLOW   = new Color(253, 224, 71);

    // Fontes
    public static final Font FONT_TITLE     = new Font("Segoe UI", Font.BOLD, 22);
    public static final Font FONT_SUBTITLE  = new Font("Segoe UI", Font.BOLD, 16);
    public static final Font FONT_BODY      = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font FONT_SMALL     = new Font("Segoe UI", Font.PLAIN, 12);
    public static final Font FONT_BOLD      = new Font("Segoe UI", Font.BOLD, 14);
    public static final Font FONT_LABEL     = new Font("Segoe UI", Font.BOLD, 12);

    // Dimensões
    public static final int BORDER_RADIUS   = 10;
    public static final int PADDING         = 16;
    public static final int GAP             = 8;

    private AppTheme() {}
}
