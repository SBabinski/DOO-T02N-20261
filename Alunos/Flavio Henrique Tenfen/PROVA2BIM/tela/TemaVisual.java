package tela;

import java.awt.*;

public final class TemaVisual {

    private TemaVisual() {}

    public static final Color FUNDO_BASE      = new Color(12,  15,  24);
    public static final Color FUNDO_PAINEL    = new Color(18,  22,  36);
    public static final Color FUNDO_CARD      = new Color(26,  30,  48);
    public static final Color FUNDO_CARD_HOVER= new Color(34,  40,  62);
    public static final Color FUNDO_CABECALHO = new Color(15,  19,  32);

    public static final Color ACENTO          = new Color(100, 80,  220);
    public static final Color ACENTO_CLARO    = new Color(140, 120, 255);
    public static final Color DOURADO         = new Color(245, 185, 50);

    public static final Color COR_NO_AR       = new Color(52,  200, 140);
    public static final Color COR_ENCERRADA   = new Color(100, 112, 140);
    public static final Color COR_CANCELADA   = new Color(220, 90,  90);

    public static final Color TEXTO_PRINCIPAL = new Color(230, 234, 250);
    public static final Color TEXTO_SECUNDARIO= new Color(148, 158, 185);
    public static final Color TEXTO_DISCRETO  = new Color(80,  90,  120);

    public static final Color BORDA           = new Color(38,  44,  70);
    public static final Color BORDA_ATIVA     = new Color(100, 80, 220, 100);

    public static final Font FONTE_TITULO  = new Font("Dialog", Font.BOLD,  22);
    public static final Font FONTE_H2      = new Font("Dialog", Font.BOLD,  15);
    public static final Font FONTE_CORPO   = new Font("Dialog", Font.PLAIN, 13);
    public static final Font FONTE_PEQUENA = new Font("Dialog", Font.PLAIN, 11);
    public static final Font FONTE_NEGRITO = new Font("Dialog", Font.BOLD,  13);
    public static final Font FONTE_LABEL   = new Font("Dialog", Font.BOLD,  10);

    public static final int ARREDONDAMENTO = 12;
    public static final int MARGEM         = 14;
    public static final int MARGEM_GRANDE  = 24;


    public static Color corEstado(String estado) {
        if (estado == null) return COR_ENCERRADA;
        return switch (estado.toLowerCase()) {
            case "running"               -> COR_NO_AR;
            case "canceled", "cancelled" -> COR_CANCELADA;
            default                      -> COR_ENCERRADA;
        };
    }

    public static String rotuloEstado(String estado) {
        if (estado == null) return "Desconhecido";
        return switch (estado.toLowerCase()) {
            case "running"               -> "No Ar";
            case "ended"                 -> "Encerrada";
            case "canceled", "cancelled" -> "Cancelada";
            default                      -> estado;
        };
    }
}
