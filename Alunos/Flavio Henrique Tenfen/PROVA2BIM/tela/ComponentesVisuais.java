package tela;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public final class ComponentesVisuais {

    private ComponentesVisuais() {}
    public static JButton botaoPrimario(String texto) {
        return criarBotao(texto, TemaVisual.ACENTO, TemaVisual.ACENTO_CLARO, Color.WHITE);
    }

    public static JButton botaoPerigo(String texto) {
        Color base  = new Color(175, 60, 60);
        Color hover = TemaVisual.COR_CANCELADA;
        return criarBotao(texto, base, hover, Color.WHITE);
    }

    public static JButton botaoSucesso(String texto) {
        Color base  = new Color(35, 140, 90);
        Color hover = TemaVisual.COR_NO_AR;
        return criarBotao(texto, base, hover, Color.WHITE);
    }

    public static JButton botaoSecundario(String texto) {
        JButton btn = new JButton(texto) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);
                g2.setColor(TemaVisual.BORDA);
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 8, 8);
                g2.dispose();
                FontMetrics fm = g.getFontMetrics();
                g.setColor(getForeground());
                g.setFont(getFont());
                int x = (getWidth()  - fm.stringWidth(getText())) / 2;
                int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
                g.drawString(getText(), x, y);
            }
        };
        btn.setFont(TemaVisual.FONTE_CORPO);
        btn.setForeground(TemaVisual.TEXTO_SECUNDARIO);
        btn.setBackground(TemaVisual.FUNDO_CARD);
        btn.setBorder(new EmptyBorder(6, 14, 6, 14));
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setOpaque(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) {
                btn.setBackground(TemaVisual.FUNDO_CARD_HOVER);
                btn.setForeground(TemaVisual.TEXTO_PRINCIPAL);
                btn.repaint();
            }
            @Override public void mouseExited(MouseEvent e) {
                btn.setBackground(TemaVisual.FUNDO_CARD);
                btn.setForeground(TemaVisual.TEXTO_SECUNDARIO);
                btn.repaint();
            }
        });
        return btn;
    }

    private static JButton criarBotao(String texto, Color normal, Color hover, Color fg) {
        JButton btn = new JButton(texto) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);
                g2.dispose();
                FontMetrics fm = g.getFontMetrics();
                g.setColor(fg);
                g.setFont(getFont());
                int x = (getWidth()  - fm.stringWidth(getText())) / 2;
                int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
                g.drawString(getText(), x, y);
            }
        };
        btn.setFont(TemaVisual.FONTE_NEGRITO);
        btn.setForeground(fg);
        btn.setBackground(normal);
        btn.setBorder(new EmptyBorder(8, 18, 8, 18));
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setOpaque(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) { btn.setBackground(hover); btn.repaint(); }
            @Override public void mouseExited(MouseEvent e)  { btn.setBackground(normal); btn.repaint(); }
        });
        return btn;
    }
    public static JTextField campoDeBusca(int colunas) {
        JTextField campo = new JTextField(colunas);
        campo.setFont(TemaVisual.FONTE_CORPO);
        campo.setForeground(TemaVisual.TEXTO_PRINCIPAL);
        campo.setBackground(TemaVisual.FUNDO_CARD);
        campo.setCaretColor(TemaVisual.ACENTO_CLARO);
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(TemaVisual.BORDA, 1),
                new EmptyBorder(8, 12, 8, 12)));
        campo.addFocusListener(new FocusAdapter() {
            @Override public void focusGained(FocusEvent e) {
                campo.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(TemaVisual.ACENTO, 1),
                        new EmptyBorder(8, 12, 8, 12)));
            }
            @Override public void focusLost(FocusEvent e) {
                campo.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(TemaVisual.BORDA, 1),
                        new EmptyBorder(8, 12, 8, 12)));
            }
        });
        return campo;
    }

    public static JLabel labelTitulo(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(TemaVisual.FONTE_TITULO);
        lbl.setForeground(TemaVisual.TEXTO_PRINCIPAL);
        return lbl;
    }

    public static JLabel labelSubtitulo(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(TemaVisual.FONTE_H2);
        lbl.setForeground(TemaVisual.TEXTO_PRINCIPAL);
        return lbl;
    }

    public static JLabel labelCorpo(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(TemaVisual.FONTE_CORPO);
        lbl.setForeground(TemaVisual.TEXTO_SECUNDARIO);
        return lbl;
    }

    public static JLabel labelDiscreto(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(TemaVisual.FONTE_PEQUENA);
        lbl.setForeground(TemaVisual.TEXTO_DISCRETO);
        return lbl;
    }

    public static JLabel labelSecao(String texto) {
        JLabel lbl = new JLabel(texto.toUpperCase());
        lbl.setFont(TemaVisual.FONTE_LABEL);
        lbl.setForeground(TemaVisual.TEXTO_DISCRETO);
        return lbl;
    }

    public static JScrollPane scrollEscuro(Component conteudo) {
        JScrollPane scroll = new JScrollPane(conteudo);
        scroll.setBackground(TemaVisual.FUNDO_BASE);
        scroll.getViewport().setBackground(TemaVisual.FUNDO_BASE);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(14);
        return scroll;
    }

    public static <T> JComboBox<T> comboEscuro(T[] itens) {
        JComboBox<T> combo = new JComboBox<>(itens) {
            @Override protected void paintComponent(Graphics g) {
                g.setColor(TemaVisual.FUNDO_CARD);
                g.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        };
        combo.setFont(TemaVisual.FONTE_CORPO);
        combo.setForeground(TemaVisual.TEXTO_PRINCIPAL);
        combo.setBackground(TemaVisual.FUNDO_CARD);
        combo.setOpaque(true);
        combo.setBorder(BorderFactory.createLineBorder(TemaVisual.BORDA, 1));

        Component editor = combo.getEditor().getEditorComponent();
        editor.setBackground(TemaVisual.FUNDO_CARD);
        editor.setForeground(TemaVisual.TEXTO_PRINCIPAL);

        for (Component filho : combo.getComponents()) {
            filho.setBackground(TemaVisual.FUNDO_CARD);
            filho.setForeground(TemaVisual.TEXTO_PRINCIPAL);
        }

        combo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value,
                    int index, boolean selected, boolean focused) {
                JLabel lbl = (JLabel) super.getListCellRendererComponent(
                        list, value, index, selected, focused);
                lbl.setBackground(selected ? TemaVisual.ACENTO : TemaVisual.FUNDO_CARD);
                lbl.setForeground(TemaVisual.TEXTO_PRINCIPAL);
                lbl.setOpaque(true);
                lbl.setBorder(new EmptyBorder(5, 10, 5, 10));
                return lbl;
            }
        });

        combo.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            @Override public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent e) {
                Object popup = combo.getUI().getAccessibleChild(combo, 0);
                if (popup instanceof javax.swing.plaf.basic.ComboPopup cp) {
                    cp.getList().setBackground(TemaVisual.FUNDO_CARD);
                    cp.getList().setForeground(TemaVisual.TEXTO_PRINCIPAL);
                    cp.getList().setSelectionBackground(TemaVisual.ACENTO);
                    cp.getList().setSelectionForeground(TemaVisual.TEXTO_PRINCIPAL);
                }
            }
            @Override public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent e) {}
            @Override public void popupMenuCanceled(javax.swing.event.PopupMenuEvent e) {}
        });

        return combo;
    }


    public static JLabel badgeEstado(String estado) {
        Color cor = TemaVisual.corEstado(estado);
        String rotulo = TemaVisual.rotuloEstado(estado);

        JLabel lbl = new JLabel("  " + rotulo + "  ") {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(cor.getRed(), cor.getGreen(), cor.getBlue(), 30));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.setColor(new Color(cor.getRed(), cor.getGreen(), cor.getBlue(), 90));
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
                g2.dispose();
                super.paintComponent(g);
            }
            @Override public boolean isOpaque() { return false; }
        };
        lbl.setFont(TemaVisual.FONTE_LABEL);
        lbl.setForeground(cor);
        lbl.setBorder(new EmptyBorder(3, 0, 3, 0));
        return lbl;
    }
}
