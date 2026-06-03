import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class UIHelper {

    private UIHelper() {}

    public static JLabel label(String text, Font font, Color color) {
        JLabel l = new JLabel(text);
        l.setFont(font);
        l.setForeground(color);
        return l;
    }

    /** Campo de texto com estilo glass escuro e focus highlight. */
    public static void styleField(JTextField field) {
        field.setFont(AppConfig.F_BODY);
        field.setForeground(AppConfig.TEXT_PRI);
        field.setBackground(AppConfig.INPUT_BG);
        field.setCaretColor(Color.WHITE);
        field.setOpaque(false);
        field.setBorder(new CompoundBorder(
            new RoundBorder(10, AppConfig.INPUT_BDR),
            BorderFactory.createEmptyBorder(9, 14, 9, 14)));
        field.setPreferredSize(new Dimension(field.getPreferredSize().width, 40));

        // Highlight azul no foco
        field.addFocusListener(new FocusAdapter() {
            @Override public void focusGained(FocusEvent e) {
                field.setBorder(new CompoundBorder(
                    new RoundBorder(10, AppConfig.INPUT_BDR_FOCUS),
                    BorderFactory.createEmptyBorder(9, 14, 9, 14)));
            }
            @Override public void focusLost(FocusEvent e) {
                field.setBorder(new CompoundBorder(
                    new RoundBorder(10, AppConfig.INPUT_BDR),
                    BorderFactory.createEmptyBorder(9, 14, 9, 14)));
            }
        });
    }

    /** Botão accent com gradiente azul e glow no hover. */
    public static JButton buildButton(String text, int width) {
        JButton btn = new JButton(text) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                boolean hover   = getModel().isRollover();
                boolean pressed = getModel().isPressed();

                // Glow externo no hover
                if (hover && !pressed) {
                    for (int i = 4; i > 0; i--) {
                        g2.setColor(new Color(77, 166, 255, 20 * i));
                        g2.fill(new RoundRectangle2D.Float(-i, -i, getWidth() + i*2, getHeight() + i*2, 14+i, 14+i));
                    }
                }

                // Gradiente do botão
                Color c1 = pressed ? new Color(0x2563EB) : hover ? new Color(0x60B4FF) : new Color(0x4DA6FF);
                Color c2 = pressed ? new Color(0x1D4ED8) : hover ? new Color(0x3B82F6) : new Color(0x2563EB);
                GradientPaint gp = new GradientPaint(0, 0, c1, 0, getHeight(), c2);
                g2.setPaint(gp);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 10, 10));

                // Brilho no topo
                g2.setColor(new Color(255, 255, 255, 40));
                g2.fill(new RoundRectangle2D.Float(1, 1, getWidth()-2, getHeight()/2f, 9, 9));

                g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setFont(AppConfig.F_BTN);
        btn.setForeground(Color.WHITE);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(width, 40));
        btn.setBorder(BorderFactory.createEmptyBorder());
        return btn;
    }

    /**
     * Card de métrica glass com ícone emoji grande, rótulo e valor.
     * Índice dos filhos: 0=ícone, 1=label, 2=valor
     */
    public static RoundedPanel metricCard(String emoji, String labelText) {
        RoundedPanel card = new RoundedPanel(16);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        JLabel ico = new JLabel(emoji);
        ico.setFont(AppConfig.F_EMOJI_SM);
        ico.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        JLabel lbl = label(labelText, AppConfig.F_LABEL, AppConfig.TEXT_MUTED);
        lbl.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        JLabel val = new JLabel("—");
        val.setFont(AppConfig.F_VALUE);
        val.setForeground(AppConfig.TEXT_PRI);
        val.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        card.add(ico);
        card.add(Box.createVerticalStrut(4));
        card.add(lbl);
        card.add(Box.createVerticalStrut(6));
        card.add(val);
        return card;
    }

    /** Extrai o JLabel de valor de um card criado por metricCard(). */
    public static JLabel getValueLabel(RoundedPanel card) {
        return (JLabel) card.getComponent(4);
    }

    // ── Borda arredondada auxiliar ────────────────────────────────────────
    public static class RoundBorder extends AbstractBorder {
        private final int radius; private final Color color;
        public RoundBorder(int r, Color c) { radius = r; color = c; }
        @Override public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.setStroke(new BasicStroke(1.2f));
            g2.draw(new RoundRectangle2D.Float(x+0.6f, y+0.6f, w-1.2f, h-1.2f, radius, radius));
            g2.dispose();
        }
        @Override public Insets getBorderInsets(Component c) { return new Insets(1,1,1,1); }
    }
}
