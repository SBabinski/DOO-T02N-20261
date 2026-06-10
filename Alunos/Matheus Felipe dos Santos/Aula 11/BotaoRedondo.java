import javax.swing.*;
import java.awt.*;

public class BotaoRedondo extends JButton {

    public BotaoRedondo(Icon icon) {

        super(icon);

        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(new Color(45, 110, 255));

        g2.fillOval(
                0,
                0,
                getWidth(),
                getHeight());

        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
    }
}