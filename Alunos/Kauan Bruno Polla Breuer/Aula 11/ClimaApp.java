import org.json.JSONObject;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ClimaApp extends JFrame {

    private static final String API_KEY = "TEH2KAUKS9U46593W9HJUF8AN"; // 

    private JTextField campoCidade;
    private JLabel labelTemp, labelMax, labelMin;
    private JLabel labelUmidade, labelCondicao;
    private JLabel labelPrecipitacao, labelVento, labelDirecaoVento;

    public ClimaApp() {
        setTitle("Consulta de Clima");
        setSize(450, 380);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        
        JPanel painelBusca = new JPanel(new FlowLayout());
        campoCidade = new JTextField(20);
        JButton botaoBuscar = new JButton("Buscar");
        painelBusca.add(new JLabel("Cidade:"));
        painelBusca.add(campoCidade);
        painelBusca.add(botaoBuscar);

        
        JPanel painelResultados = new JPanel(new GridLayout(7, 1, 5, 5));
        painelResultados.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        labelTemp         = new JLabel("Temperatura atual: -");
        labelMax          = new JLabel("Maxima: -");
        labelMin          = new JLabel("Minima: -");
        labelUmidade      = new JLabel("Umidade: -");
        labelCondicao     = new JLabel("Condicao: -");
        labelPrecipitacao = new JLabel("Precipitacao: -");
        labelVento        = new JLabel("Vento: -");

        painelResultados.add(labelTemp);
        painelResultados.add(labelMax);
        painelResultados.add(labelMin);
        painelResultados.add(labelUmidade);
        painelResultados.add(labelCondicao);
        painelResultados.add(labelPrecipitacao);
        painelResultados.add(labelVento);

        add(painelBusca, BorderLayout.NORTH);
        add(painelResultados, BorderLayout.CENTER);

        
        botaoBuscar.addActionListener(e -> buscarClima());

        setVisible(true);
    }

    private void buscarClima() {
        String cidade = campoCidade.getText().trim();
        if (cidade.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite o nome de uma cidade.");
            return;
        }

        try {
            
            String urlStr = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"
                    + cidade.replace(" ", "%20")
                    + "/today?unitGroup=metric&key=" + API_KEY + "&contentType=json&lang=pt";

            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder resposta = new StringBuilder();
            String linha;
            while ((linha = reader.readLine()) != null) {
                resposta.append(linha);
            }
            reader.close();

            
            JSONObject json = new JSONObject(resposta.toString());
            JSONObject hoje = json.getJSONArray("days").getJSONObject(0);

            
            JSONObject current  = json.getJSONObject("currentConditions");

            double temp         = current.getDouble("temp");
            double max          = hoje.getDouble("tempmax");
            double min          = hoje.getDouble("tempmin");
            double umidade      = current.getDouble("humidity");
            String condicao     = current.getString("conditions");
            double precipitacao = hoje.getDouble("precip");
            double vento        = current.getDouble("windspeed");
            double direcao      = current.getDouble("winddir");

           
            labelTemp.setText(        "🌡 Temperatura atual: " + temp + " °C");
            labelMax.setText(         "🔺 Maxima: " + max + " °C");
            labelMin.setText(         "🔻 Minima: " + min + " °C");
            labelUmidade.setText(     "💧 Umidade: " + umidade + "%");
            labelCondicao.setText(    "☁ Condicao: " + condicao);
            labelPrecipitacao.setText("🌧 Precipitacao: " + precipitacao + " mm");
            labelVento.setText(       "💨 Vento: " + vento + " km/h | Direcao: " + direcao + "°");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar dados: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
        System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
        } catch (Exception e) {
        e.printStackTrace();
        }
        SwingUtilities.invokeLater(ClimaApp::new);
        }
}