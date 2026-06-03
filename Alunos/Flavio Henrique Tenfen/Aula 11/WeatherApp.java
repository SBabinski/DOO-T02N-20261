import java.awt.*;
import java.util.concurrent.*;
import javax.swing.*;

public class WeatherApp extends JFrame {

    private final WeatherService service = new WeatherService();
    private final WeatherPanel   weatherPanel;
    private       TopPanel       topPanel;

    public WeatherApp() {
        setTitle("Weather App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(580, 760);
        setMinimumSize(new Dimension(480, 620));
        setLocationRelativeTo(null);
        setBackground(AppConfig.GRAD_TOP);

        // Painel raiz com gradiente
        JPanel root = new WeatherPanel.GradientBackground();
        root.setLayout(new BorderLayout());
        setContentPane(root);

        weatherPanel = new WeatherPanel();
        topPanel     = new TopPanel(this::onSearch);

        root.add(topPanel,     BorderLayout.NORTH);
        root.add(weatherPanel, BorderLayout.CENTER);
    }

    private void onSearch(String city, String apiKey) {
        if (apiKey.isBlank()) { weatherPanel.showError("Insira sua API Key da Visual Crossing."); return; }
        if (city.isBlank())   { weatherPanel.showError("Insira o nome de uma cidade."); return; }

        topPanel.setSearchEnabled(false);
        weatherPanel.showLoading();

        SwingWorker<WeatherData, Void> w = new SwingWorker<>() {
            @Override protected WeatherData doInBackground() throws Exception {
                return service.fetch(city, apiKey);
            }
            @Override protected void done() {
                topPanel.setSearchEnabled(true);
                try {
                    weatherPanel.showData(get());
                } catch (ExecutionException ex) {
                    weatherPanel.showError(ex.getCause().getMessage());
                } catch (Exception ex) {
                    weatherPanel.showError("Erro inesperado: " + ex.getMessage());
                }
            }
        };
        w.execute();
    }

    public static void main(String[] args) {
        // Suavização de fontes
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");

        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
        catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> new WeatherApp().setVisible(true));
    }
}
