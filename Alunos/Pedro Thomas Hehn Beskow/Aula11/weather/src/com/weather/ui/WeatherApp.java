package com.weather.ui;

import com.weather.model.WeatherData;
import com.weather.service.WeatherService;
import com.weather.util.WeatherLabels;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.ExecutionException;

public class WeatherApp extends JFrame {

    // Cores
    private static final Color BG_TOP    = new Color(20, 50, 110);
    private static final Color BG_BOTTOM = new Color(8, 20, 55);
    private static final Color WHITE     = Color.WHITE;
    private static final Color LIGHT     = new Color(190, 215, 255);
    private static final Color ACCENT    = new Color(90, 165, 255);
    private static final Color BTN_BG    = new Color(60, 130, 220);
    private static final Color BTN_HOV   = new Color(80, 155, 255);
    private static final Color ERR       = new Color(255, 110, 90);

    private JTextField  cityField;
    private JButton     searchButton;
    private JPanel      resultCard;
    private JLabel      lblCity;
    private JLabel      lblIcon;
    private JLabel      lblTemp;
    private JLabel      lblCondition;
    private JLabel      lblMaxMin;
    private JLabel      lblHumidity;
    private JLabel      lblPrecip;
    private JLabel      lblWind;
    private JLabel      lblStatus;

    private final WeatherService service = new WeatherService();

    public WeatherApp() {
        setTitle("Previsão do Tempo");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(460, 620);
        setMinimumSize(new Dimension(380, 540));
        setLocationRelativeTo(null);
        setResizable(true);
        buildUI();
    }

    // ---------- construção da UI ----------

    private void buildUI() {
        GradientPanel root = new GradientPanel(BG_TOP, BG_BOTTOM);
        root.setLayout(new BorderLayout(0, 0));
        root.setBorder(new EmptyBorder(28, 28, 20, 28));
        setContentPane(root);

        root.add(buildSearchBar(), BorderLayout.NORTH);
        root.add(buildResultCard(), BorderLayout.CENTER);
        root.add(buildStatusBar(), BorderLayout.SOUTH);
    }

    private JPanel buildSearchBar() {
        JPanel bar = transparent();
        bar.setLayout(new BorderLayout(10, 0));
        bar.setBorder(new EmptyBorder(0, 0, 18, 0));

        cityField = new RoundTextField("Cascavel, PR");
        cityField.addActionListener(e -> search());

        searchButton = new RoundButton("Buscar", BTN_BG, BTN_HOV);
        searchButton.setPreferredSize(new Dimension(96, 40));
        searchButton.addActionListener(e -> search());

        bar.add(cityField, BorderLayout.CENTER);
        bar.add(searchButton, BorderLayout.EAST);
        return bar;
    }

    private JPanel buildResultCard() {
        resultCard = new CardPanel();
        resultCard.setLayout(new GridBagLayout());
        resultCard.setVisible(false);

        GridBagConstraints g = new GridBagConstraints();
        g.gridx = 0; g.fill = GridBagConstraints.HORIZONTAL; g.weightx = 1;

        lblCity      = label("", 17, Font.BOLD, WHITE, SwingConstants.CENTER);
        lblIcon      = label("", 54, Font.PLAIN, WHITE, SwingConstants.CENTER);
        lblTemp      = label("", 52, Font.BOLD, WHITE, SwingConstants.CENTER);
        lblCondition = label("", 16, Font.PLAIN, LIGHT, SwingConstants.CENTER);

        addRow(g, resultCard, 0, lblCity,      new Insets(0, 0, 2, 0));
        addRow(g, resultCard, 1, lblIcon,      new Insets(4, 0, 0, 0));
        addRow(g, resultCard, 2, lblTemp,      new Insets(0, 0, 0, 0));
        addRow(g, resultCard, 3, lblCondition, new Insets(0, 0, 8, 0));

        g.gridy = 4; g.insets = new Insets(6, 0, 10, 0);
        resultCard.add(separator(), g);

        lblMaxMin   = infoRow("Máx / Mín",   "---");
        lblHumidity = infoRow("Humidade",     "---");
        lblPrecip   = infoRow("Precipitação", "---");
        lblWind     = infoRow("Vento",        "---");

        addRow(g, resultCard, 5, lblMaxMin,   new Insets(4, 0, 4, 0));
        addRow(g, resultCard, 6, lblHumidity, new Insets(4, 0, 4, 0));
        addRow(g, resultCard, 7, lblPrecip,   new Insets(4, 0, 4, 0));
        addRow(g, resultCard, 8, lblWind,     new Insets(4, 0, 4, 0));

        return resultCard;
    }

    private JPanel buildStatusBar() {
        JPanel bar = transparent();
        bar.setBorder(new EmptyBorder(10, 0, 0, 0));
        lblStatus = label(" ", 13, Font.PLAIN, LIGHT, SwingConstants.CENTER);
        bar.add(lblStatus);
        return bar;
    }

    // ---------- busca ----------

    private void search() {
        String city = cityField.getText().trim();
        if (city.isEmpty()) {
            status("Digite o nome de uma cidade.", ERR);
            return;
        }

        searchButton.setEnabled(false);
        cityField.setEnabled(false);
        resultCard.setVisible(false);
        status("Buscando...", LIGHT);

        new SwingWorker<WeatherData, Void>() {
            @Override
            protected WeatherData doInBackground() throws Exception {
                return service.fetchWeather(city);
            }

            @Override
            protected void done() {
                searchButton.setEnabled(true);
                cityField.setEnabled(true);
                try {
                    render(get());
                } catch (InterruptedException | ExecutionException ex) {
                    Throwable cause = ex.getCause() != null ? ex.getCause() : ex;
                    status("Erro: " + cause.getMessage(), ERR);
                }
            }
        }.execute();
    }

    private void render(WeatherData d) {
        lblCity.setText(d.getCity());
        lblIcon.setText(WeatherLabels.iconToEmoji(d.getIcon()));
        lblTemp.setText(String.format("%.1f °C", d.getCurrentTemp()));
        lblCondition.setText(WeatherLabels.translateCondition(d.getCondition()));

        setInfoRow(lblMaxMin,   "Máx / Mín",   String.format("%.1f °C  /  %.1f °C", d.getMaxTemp(), d.getMinTemp()));
        setInfoRow(lblHumidity, "Humidade",     String.format("%.0f%%", d.getHumidity()));
        setInfoRow(lblPrecip,   "Precipitação", d.getPrecipitation() > 0
                ? String.format("%.1f mm", d.getPrecipitation()) : "Sem chuva");
        setInfoRow(lblWind,     "Vento",        String.format("%.1f km/h — %s", d.getWindSpeed(), d.getWindDirection()));

        resultCard.setVisible(true);
        status("Dados atualizados.", LIGHT);
        revalidate();
        repaint();
    }

    // ---------- helpers ----------

    private void status(String msg, Color color) {
        lblStatus.setText(msg);
        lblStatus.setForeground(color);
    }

    private JLabel infoRow(String lbl, String val) {
        JLabel l = new JLabel(infoHtml(lbl, val));
        l.setFont(new Font("SansSerif", Font.PLAIN, 15));
        return l;
    }

    private void setInfoRow(JLabel label, String lbl, String val) {
        label.setText(infoHtml(lbl, val));
    }

    private String infoHtml(String label, String value) {
        return "<html><span style='color:#80b0e8'>" + label + ":</span>&nbsp;&nbsp;"
             + "<b style='color:white'>" + value + "</b></html>";
    }

    private JLabel label(String text, int size, int style, Color color, int align) {
        JLabel l = new JLabel(text, align);
        l.setFont(new Font("SansSerif", style, size));
        l.setForeground(color);
        return l;
    }

    private JSeparator separator() {
        JSeparator s = new JSeparator();
        s.setForeground(new Color(255, 255, 255, 55));
        return s;
    }

    private JPanel transparent() {
        JPanel p = new JPanel();
        p.setOpaque(false);
        return p;
    }

    private void addRow(GridBagConstraints g, JPanel panel, int row, JComponent comp, Insets insets) {
        g.gridy = row;
        g.insets = insets;
        panel.add(comp, g);
    }

    // ---------- componentes visuais customizados ----------

    static class GradientPanel extends JPanel {
        private final Color top, bottom;
        GradientPanel(Color top, Color bottom) {
            this.top = top; this.bottom = bottom; setOpaque(true);
        }
        @Override protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setPaint(new GradientPaint(0, 0, top, 0, getHeight(), bottom));
            g2.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    // Card com fundo semi-transparente e cantos arredondados
    static class CardPanel extends JPanel {
        CardPanel() { setOpaque(false); setBorder(new EmptyBorder(18, 22, 18, 22)); }
        @Override protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(255, 255, 255, 22));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 18, 18);
            g2.setColor(new Color(255, 255, 255, 38));
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 18, 18);
            g2.dispose();
        }
    }

    // Campo de texto com bordas arredondadas sobre fundo transparente
    static class RoundTextField extends JTextField {
        RoundTextField(String text) {
            super(text);
            setOpaque(false);
            setForeground(Color.WHITE);
            setCaretColor(Color.WHITE);
            setFont(new Font("SansSerif", Font.PLAIN, 15));
            setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(90, 165, 255), 1, true),
                new EmptyBorder(7, 12, 7, 12)
            ));
        }
        @Override protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(255, 255, 255, 30));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    // Botão com cantos arredondados e efeito hover
    static class RoundButton extends JButton {
        private final Color normal, hover;
        private boolean hovered;

        RoundButton(String text, Color normal, Color hover) {
            super(text);
            this.normal = normal; this.hover = hover;
            setOpaque(false); setContentAreaFilled(false);
            setBorderPainted(false); setFocusPainted(false);
            setForeground(Color.WHITE);
            setFont(new Font("SansSerif", Font.BOLD, 14));
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            addMouseListener(new MouseAdapter() {
                @Override public void mouseEntered(MouseEvent e) { hovered = true;  repaint(); }
                @Override public void mouseExited(MouseEvent e)  { hovered = false; repaint(); }
            });
        }

        @Override protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(hovered ? hover : normal);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    // ---------- main ----------

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}
            new WeatherApp().setVisible(true);
        });
    }
}
