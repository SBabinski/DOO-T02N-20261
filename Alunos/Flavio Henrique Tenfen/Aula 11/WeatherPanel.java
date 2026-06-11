import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javax.swing.*;
import javax.swing.border.*;

public class WeatherPanel extends JScrollPane {

    private JLabel lblCity, lblTime, lblCondEmoji, lblCondText;
    private JLabel lblTemp, lblTempMax, lblTempMin;
    private JLabel lblHumidity, lblWind, lblWindDir;
    private JLabel lblVisibility, lblPressure;
    private JLabel lblPrecip, lblPrecipProb, lblError;

    private JPanel errorPanel, loadingPanel, resultPanel, precipPanel;
    private JProgressBar progressBar;

    public WeatherPanel() {
        JPanel container = new GradientBackground();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        buildErrorPanel(container);
        buildLoadingPanel(container);
        buildResultPanel(container);

        setBorder(null);
        getViewport().setView(container);
        getViewport().setBackground(AppConfig.GRAD_TOP);
        setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
        getVerticalScrollBar().setUnitIncrement(16);
    }


    public void showLoading() {
        errorPanel.setVisible(false);
        resultPanel.setVisible(false);
        loadingPanel.setVisible(true);
    }

    public void showError(String msg) {
        lblError.setText(msg);
        loadingPanel.setVisible(false);
        errorPanel.setVisible(true);
    }

    public void showData(WeatherData d) {
        loadingPanel.setVisible(false);
        errorPanel.setVisible(false);

        String addr = d.resolvedAddress;
        lblCity.setText(addr.length() > 40 ? addr.substring(0, 38) + "…" : addr);
        lblTime.setText(LocalDateTime.now().format(
            DateTimeFormatter.ofPattern("EEEE, d 'de' MMMM  •  HH:mm", new Locale("pt","BR"))));

        String[] parts = d.condition.translated.split("  ", 2);
        lblCondEmoji.setText(parts[0]);
        lblCondText.setText(parts.length > 1 ? parts[1] : d.condition.translated);

        lblTemp.setText(d.currentTemp.formattedNumber());

        lblTempMax.setText("▲ " + d.tempRange.formattedMax());
        lblTempMin.setText("▼ " + d.tempRange.formattedMin());

        lblHumidity.setText(d.humidity.formatted());

        lblWind.setText(d.wind.formattedSpeed());
        lblWindDir.setText(d.wind.formattedDirection());

        lblVisibility.setText(d.formattedVisibility());
        lblPressure.setText(d.formattedPressure());

        if (d.precipitation.isRelevant()) {
            lblPrecip.setText(d.precipitation.formattedAmount());
            lblPrecipProb.setText(d.precipitation.formattedProbability());
            precipPanel.setVisible(true);
        } else {
            precipPanel.setVisible(false);
        }

        resultPanel.setVisible(true);
        revalidate(); repaint();
    }


    private void buildErrorPanel(JPanel c) {
        errorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));
        errorPanel.setOpaque(false);
        errorPanel.setBorder(new CompoundBorder(
            new UIHelper.RoundBorder(10, new Color(255, 100, 100, 120)),
            BorderFactory.createEmptyBorder(2, 8, 2, 8)));
        errorPanel.setBackground(new Color(200, 50, 50, 40));
        errorPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 48));
        errorPanel.setAlignmentX(LEFT_ALIGNMENT);
        errorPanel.add(UIHelper.label("⚠", new Font("Segoe UI", Font.PLAIN, 16), AppConfig.DANGER));
        lblError = UIHelper.label("", AppConfig.F_BODY, AppConfig.DANGER);
        errorPanel.add(lblError);
        errorPanel.setVisible(false);
        c.add(errorPanel);
        c.add(Box.createVerticalStrut(10));
    }

    private void buildLoadingPanel(JPanel c) {
        loadingPanel = new JPanel();
        loadingPanel.setLayout(new BoxLayout(loadingPanel, BoxLayout.Y_AXIS));
        loadingPanel.setOpaque(false);
        loadingPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        loadingPanel.setAlignmentX(LEFT_ALIGNMENT);

        JLabel lbl = UIHelper.label("Buscando dados do clima...", AppConfig.F_BODY, AppConfig.TEXT_SEC);
        lbl.setAlignmentX(CENTER_ALIGNMENT);

        progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        progressBar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 3));
        progressBar.setBackground(new Color(255,255,255,20));
        progressBar.setForeground(AppConfig.ACCENT);
        progressBar.setBorderPainted(false);

        loadingPanel.add(lbl);
        loadingPanel.add(Box.createVerticalStrut(10));
        loadingPanel.add(progressBar);
        loadingPanel.setVisible(false);
        c.add(loadingPanel);
        c.add(Box.createVerticalStrut(10));
    }

    private void buildResultPanel(JPanel c) {
        resultPanel = new JPanel();
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
        resultPanel.setOpaque(false);
        resultPanel.setVisible(false);

        buildHeroCard();
        resultPanel.add(Box.createVerticalStrut(14));
        buildMetricsRow();
        resultPanel.add(Box.createVerticalStrut(14));
        buildPrecipCard();
        resultPanel.add(Box.createVerticalStrut(14));
        buildFooter();

        c.add(resultPanel);
    }

    private void buildHeroCard() {
        RoundedPanel card = new RoundedPanel(20);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createEmptyBorder(26, 28, 26, 28));
        card.setAlignmentX(LEFT_ALIGNMENT);
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        lblCity = UIHelper.label("—", AppConfig.F_CITY, AppConfig.TEXT_PRI);
        lblCity.setAlignmentX(LEFT_ALIGNMENT);
        card.add(lblCity);
        card.add(Box.createVerticalStrut(4));

        lblTime = UIHelper.label("—", AppConfig.F_SMALL, AppConfig.TEXT_MUTED);
        lblTime.setAlignmentX(LEFT_ALIGNMENT);
        card.add(lblTime);
        card.add(Box.createVerticalStrut(22));

        JPanel tempRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        tempRow.setOpaque(false);
        tempRow.setAlignmentX(LEFT_ALIGNMENT);

        lblTemp = new JLabel("—");
        lblTemp.setFont(AppConfig.F_TEMP);
        lblTemp.setForeground(AppConfig.TEXT_PRI);

        JLabel deg = new JLabel("°C");
        deg.setFont(AppConfig.F_DEG);
        deg.setForeground(AppConfig.TEXT_SEC);
        deg.setVerticalAlignment(SwingConstants.BOTTOM);

        JPanel rangeCol = new JPanel();
        rangeCol.setLayout(new BoxLayout(rangeCol, BoxLayout.Y_AXIS));
        rangeCol.setOpaque(false);
        rangeCol.setBorder(BorderFactory.createEmptyBorder(18, 14, 0, 0));
        lblTempMax = UIHelper.label("▲ —", AppConfig.F_TSUB, AppConfig.DANGER);
        lblTempMin = UIHelper.label("▼ —", AppConfig.F_TSUB, AppConfig.ACCENT);
        rangeCol.add(lblTempMax);
        rangeCol.add(Box.createVerticalStrut(6));
        rangeCol.add(lblTempMin);

        tempRow.add(lblTemp);
        tempRow.add(deg);
        tempRow.add(rangeCol);
        card.add(tempRow);
        card.add(Box.createVerticalStrut(20));

        JPanel condRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        condRow.setOpaque(false);
        condRow.setAlignmentX(LEFT_ALIGNMENT);

        lblCondEmoji = new JLabel("—");
        lblCondEmoji.setFont(AppConfig.F_EMOJI);

        lblCondText = UIHelper.label("—", AppConfig.F_COND, AppConfig.TEXT_SEC);

        condRow.add(lblCondEmoji);
        condRow.add(lblCondText);
        card.add(condRow);

        resultPanel.add(card);
    }

    private void buildMetricsRow() {
        JPanel grid = new JPanel(new GridLayout(2, 2, 12, 12));
        grid.setOpaque(false);
        grid.setAlignmentX(LEFT_ALIGNMENT);
        grid.setMaximumSize(new Dimension(Integer.MAX_VALUE, 220));

        RoundedPanel humCard = UIHelper.metricCard("💧", "HUMIDADE");
        lblHumidity = UIHelper.getValueLabel(humCard);
        grid.add(humCard);

        RoundedPanel windCard = UIHelper.metricCard("💨", "VENTO");
        lblWind = UIHelper.getValueLabel(windCard);
        lblWindDir = UIHelper.label("—", AppConfig.F_SMALL, AppConfig.TEXT_MUTED);
        lblWindDir.setAlignmentX(CENTER_ALIGNMENT);
        windCard.add(Box.createVerticalStrut(2));
        windCard.add(lblWindDir);
        grid.add(windCard);

        RoundedPanel visCard = UIHelper.metricCard("👁", "VISIBILIDADE");
        lblVisibility = UIHelper.getValueLabel(visCard);
        grid.add(visCard);

        RoundedPanel presCard = UIHelper.metricCard("🌡", "PRESSÃO");
        lblPressure = UIHelper.getValueLabel(presCard);
        grid.add(presCard);

        resultPanel.add(grid);
    }

    private void buildPrecipCard() {
        precipPanel = new RoundedPanel(16,
            new Color(30, 100, 200, 40),
            new Color(80, 160, 255, 80),
            false);
        precipPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 12, 10));
        precipPanel.setAlignmentX(LEFT_ALIGNMENT);
        precipPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 58));

        JLabel ico = new JLabel("🌧");
        ico.setFont(AppConfig.F_EMOJI_SM);

        lblPrecip     = UIHelper.label("0,0 mm",  AppConfig.F_VALUE, AppConfig.RAIN_COLOR);
        lblPrecipProb = UIHelper.label("—",        AppConfig.F_BODY,  AppConfig.TEXT_SEC);

        JLabel dot = UIHelper.label("·", AppConfig.F_BODY, AppConfig.TEXT_MUTED);

        precipPanel.add(ico);
        precipPanel.add(lblPrecip);
        precipPanel.add(dot);
        precipPanel.add(lblPrecipProb);
        precipPanel.setVisible(false);
        resultPanel.add(precipPanel);
    }

    private void buildFooter() {
        JLabel footer = UIHelper.label(
            "Visual Crossing Weather API  •  Unidades métricas",
            AppConfig.F_SMALL, AppConfig.TEXT_MUTED);
        footer.setAlignmentX(LEFT_ALIGNMENT);
        resultPanel.add(footer);
    }

    static class GradientBackground extends JPanel {
        GradientBackground() { setOpaque(true); }
        @Override protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            GradientPaint gp = new GradientPaint(
                0, 0,           AppConfig.GRAD_TOP,
                getWidth(), getHeight(), AppConfig.GRAD_BOT);
            g2.setPaint(gp);
            g2.fillRect(0, 0, getWidth(), getHeight());
            g2.dispose();
        }
    }
}
