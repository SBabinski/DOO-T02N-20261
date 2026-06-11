import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class RoundedPanel extends JPanel {

    private final int     radius;
    private final Color   bg;
    private final Color   border;
    private final boolean shadow;

    /** Card glass padrão com sombra. */
    public RoundedPanel(int radius) {
        this(radius, AppConfig.GLASS_BG, AppConfig.GLASS_BDR, true);
    }

    /** Card com cor de fundo e borda customizadas. */
    public RoundedPanel(int radius, Color bg, Color border, boolean shadow) {
        this.radius = radius;
        this.bg     = bg;
        this.border = border;
        this.shadow = shadow;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,  RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING,     RenderingHints.VALUE_RENDER_QUALITY);

        int w = getWidth(), h = getHeight();

        if (shadow) {
            for (int i = 6; i > 0; i--) {
                float alpha = 0.06f * (7 - i);
                g2.setColor(new Color(0, 0, 0, (int)(alpha * 255)));
                g2.fill(new RoundRectangle2D.Float(i, i + 2, w - i * 2, h - i * 2, radius + 2, radius + 2));
            }
        }

        g2.setColor(bg);
        g2.fill(new RoundRectangle2D.Float(0, 0, w, h, radius, radius));

        GradientPaint shine = new GradientPaint(
            0, 0,    new Color(255, 255, 255, 30),
            0, h/3f, new Color(255, 255, 255, 0));
        g2.setPaint(shine);
        g2.fill(new RoundRectangle2D.Float(0, 0, w, h / 3f, radius, radius));

        g2.setColor(border);
        g2.setStroke(new BasicStroke(1.0f));
        g2.draw(new RoundRectangle2D.Float(0.5f, 0.5f, w - 1f, h - 1f, radius, radius));

        g2.dispose();
        super.paintComponent(g);
    }
}
