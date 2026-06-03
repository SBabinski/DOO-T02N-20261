import java.awt.*;
import java.net.URI;
import java.net.http.*;

import javax.swing.*;
import javax.swing.border.*;

import com.fasterxml.jackson.databind.*;

public class Main extends JFrame {

    private static final String API_KEY = "";

    private JTextField campoCidade;
    private JPanel painelResultado;

    public Main() {
        setTitle("Consulta Climática");
        setSize(520, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setBackground(new Color(240, 242, 245));

        JPanel painelPrincipal = new JPanel(new BorderLayout(0, 0));
        painelPrincipal.setBackground(new Color(240, 242, 245));

        // ── Cabeçalho azul ──────────────────────────────────────────────
        JPanel cabecalho = new JPanel(new FlowLayout(FlowLayout.LEFT, 16, 12));
        cabecalho.setBackground(new Color(26, 35, 126));
        JLabel icone = new JLabel("🌤");
        icone.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));
        JLabel titulo = new JLabel("Consulta Climática");
        titulo.setFont(new Font("Arial", Font.BOLD, 15));
        titulo.setForeground(Color.WHITE);
        cabecalho.add(icone);
        cabecalho.add(titulo);

        // ── Área de busca ────────────────────────────────────────────────
        JPanel painelBusca = new JPanel(new BorderLayout(8, 0));
        painelBusca.setBackground(new Color(240, 242, 245));
        painelBusca.setBorder(new EmptyBorder(20, 24, 12, 24));

        JPanel labelPanel = new JPanel(new BorderLayout());
        labelPanel.setBackground(new Color(240, 242, 245));
        JLabel labelCidade = new JLabel("CIDADE");
        labelCidade.setFont(new Font("Arial", Font.BOLD, 11));
        labelCidade.setForeground(new Color(92, 107, 192));
        labelCidade.setBorder(new EmptyBorder(0, 0, 6, 0));

        campoCidade = new JTextField();
        campoCidade.setFont(new Font("Arial", Font.PLAIN, 15));
        campoCidade.setForeground(new Color(26, 35, 126));
        campoCidade.setBackground(new Color(243, 244, 252));
        campoCidade.setCaretColor(new Color(26, 35, 126));
        campoCidade.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(197, 202, 233), 1, true),
                new EmptyBorder(8, 12, 8, 12)));
        campoCidade.putClientProperty("JTextField.placeholderText",
                "Ex: Curitiba, São Paulo, Londrina...");

        JButton botao = new JButton("🔍 Consultar");
        botao.setFont(new Font("Arial", Font.BOLD, 13));
        botao.setBackground(new Color(57, 73, 171));
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setBorderPainted(false);
        botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botao.setBorder(new EmptyBorder(10, 16, 10, 16));

        JPanel inputRow = new JPanel(new BorderLayout(8, 0));
        inputRow.setBackground(new Color(240, 242, 245));
        inputRow.add(campoCidade, BorderLayout.CENTER);
        inputRow.add(botao, BorderLayout.EAST);

        labelPanel.add(labelCidade, BorderLayout.NORTH);
        labelPanel.add(inputRow, BorderLayout.CENTER);
        painelBusca.add(labelPanel, BorderLayout.CENTER);

        // ── Painel de resultado ──────────────────────────────────────────
        painelResultado = new JPanel();
        painelResultado.setLayout(new BoxLayout(painelResultado, BoxLayout.Y_AXIS));
        painelResultado.setBackground(new Color(243, 244, 252));
        painelResultado.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(197, 202, 233), 1, true),
                new EmptyBorder(16, 18, 16, 18)));

        JLabel placeholder = new JLabel("Digite uma cidade e clique em Consultar.");
        placeholder.setForeground(new Color(159, 168, 218));
        placeholder.setFont(new Font("Arial", Font.ITALIC, 13));
        placeholder.setAlignmentX(Component.LEFT_ALIGNMENT);
        painelResultado.add(placeholder);

        JPanel wrapResultado = new JPanel(new BorderLayout());
        wrapResultado.setBackground(new Color(240, 242, 245));
        wrapResultado.setBorder(new EmptyBorder(0, 24, 20, 24));
        wrapResultado.add(painelResultado, BorderLayout.CENTER);

        // ── Rodapé ──────────────────────────────────────────────────────
        JPanel rodape = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 8));
        rodape.setBackground(new Color(240, 242, 245));
        JLabel fonte = new JLabel("Visual Crossing Weather API");
        fonte.setFont(new Font("Arial", Font.PLAIN, 10));
        fonte.setForeground(new Color(176, 190, 197));
        rodape.add(fonte);

        painelPrincipal.add(cabecalho, BorderLayout.NORTH);
        JPanel centro = new JPanel(new BorderLayout());
        centro.setBackground(new Color(240, 242, 245));
        centro.add(painelBusca, BorderLayout.NORTH);
        centro.add(wrapResultado, BorderLayout.CENTER);
        painelPrincipal.add(centro, BorderLayout.CENTER);
        painelPrincipal.add(rodape, BorderLayout.SOUTH);

        add(painelPrincipal);

        // Consultar ao pressionar Enter também
        campoCidade.addActionListener(e -> consultarClima());
        botao.addActionListener(e -> consultarClima());
    }

    private void consultarClima() {
        String cidade = campoCidade.getText().trim();

        if (cidade.isEmpty()) {
            mostrarErro("Por favor, digite o nome de uma cidade.");
            return;
        }

        // Feedback visual de carregamento
        painelResultado.removeAll();
        JLabel carregando = new JLabel("Carregando...");
        carregando.setForeground(new Color(92, 107, 192));
        carregando.setFont(new Font("Arial", Font.ITALIC, 13));
        carregando.setAlignmentX(Component.LEFT_ALIGNMENT);
        painelResultado.add(carregando);
        painelResultado.revalidate();
        painelResultado.repaint();

        // Faz a requisição em thread separada para não travar a UI
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            String erro = null;
            String cidadeResposta, condicao;
            double tempAtual, tempMax, tempMin, umidade, precipitacao,
                    ventoVelocidade, ventoDirecao;

            @Override
            protected Void doInBackground() throws Exception {
                String endpoint =
                        "https://weather.visualcrossing.com/VisualCrossingWebServices"
                        + "/rest/services/timeline/"
                        + URI.create(cidade.replace(" ", "%20"))
                        + "?unitGroup=metric&lang=pt&key=" + API_KEY
                        + "&contentType=json";

                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(endpoint))
                        .GET()
                        .build();
                HttpResponse<String> response =
                        client.send(request, HttpResponse.BodyHandlers.ofString());

                // API retorna 400 para cidades inválidas
                if (response.statusCode() == 400 || response.statusCode() == 404) {
                    erro = "Cidade não encontrada: \"" + cidade + "\".\n"
                        + "Verifique o nome e tente novamente.";
                    return null;
                }

                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(response.body());

                // Verifica se a resposta tem os campos esperados
                if (!root.has("currentConditions") || !root.has("days")) {
                    erro = "Cidade não encontrada: \"" + cidade + "\".\n"
                        + "Verifique o nome e tente novamente.";
                    return null;
                }

                JsonNode current = root.get("currentConditions");
                JsonNode today   = root.get("days").get(0);
                cidadeResposta  = root.has("resolvedAddress")
                                ? root.get("resolvedAddress").asText()
                                : cidade;
                tempAtual       = current.get("temp").asDouble();
                tempMax         = today.get("tempmax").asDouble();
                tempMin         = today.get("tempmin").asDouble();
                umidade         = current.get("humidity").asDouble();
                condicao        = current.get("conditions").asText();
                precipitacao    = current.get("precip").asDouble();
                ventoVelocidade = current.get("windspeed").asDouble();
                ventoDirecao    = current.get("winddir").asDouble();
                return null;
            }

            @Override
            protected void done() {
                if (erro != null) {
                    mostrarErro(erro);
                } else {
                    mostrarResultado(cidadeResposta, condicao,
                            tempAtual, tempMax, tempMin,
                            umidade, precipitacao,
                            ventoVelocidade, ventoDirecao);
                }
            }
        };
        worker.execute();
    }

    private void mostrarResultado(String cidade, String condicao,
            double tempAtual, double tempMax, double tempMin,
            double umidade, double precipitacao,
            double vento, double ventoDirecao) {

        painelResultado.removeAll();
        painelResultado.setBackground(new Color(243, 244, 252));

        // Linha de cidade + condição
        JPanel headerLinha = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        headerLinha.setBackground(new Color(243, 244, 252));
        headerLinha.setAlignmentX(Component.LEFT_ALIGNMENT);
        headerLinha.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));

        JLabel nomeCidade = new JLabel(cidade);
        nomeCidade.setFont(new Font("Arial", Font.BOLD, 16));
        nomeCidade.setForeground(new Color(26, 35, 126));

        JLabel badge = new JLabel(condicao);
        badge.setFont(new Font("Arial", Font.PLAIN, 11));
        badge.setForeground(new Color(92, 107, 192));
        badge.setBackground(new Color(232, 234, 246));
        badge.setOpaque(true);
        badge.setBorder(new EmptyBorder(2, 10, 2, 10));

        headerLinha.add(nomeCidade);
        headerLinha.add(badge);
        painelResultado.add(headerLinha);
        painelResultado.add(Box.createVerticalStrut(12));

        // Grid de cards 2 colunas
        JPanel grid = new JPanel(new GridLayout(0, 2, 10, 10));
        grid.setBackground(new Color(243, 244, 252));
        grid.setAlignmentX(Component.LEFT_ALIGNMENT);

        grid.add(criarCard("🌡 Temperatura Atual",
                String.format("%.1f °C", tempAtual)));
        grid.add(criarCard("💧 Umidade",
                String.format("%.0f%%", umidade)));
        grid.add(criarCard("🔺 Máxima",
                String.format("%.1f °C", tempMax)));
        grid.add(criarCard("🔻 Mínima",
                String.format("%.1f °C", tempMin)));
        grid.add(criarCard("🌬 Velocidade do Vento",
                String.format("%.1f km/h", vento)));
        grid.add(criarCard("🧭 Direção do Vento",
                String.format("%.0f°", ventoDirecao)));
        grid.add(criarCard("🌧 Precipitação",
                String.format("%.1f mm", precipitacao)));

        painelResultado.add(grid);
        painelResultado.revalidate();
        painelResultado.repaint();
    }

    private JPanel criarCard(String label, String valor) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(232, 234, 246), 1, true),
                new EmptyBorder(10, 14, 10, 14)));

        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Arial", Font.PLAIN, 11));
        lbl.setForeground(new Color(159, 168, 218));

        JLabel val = new JLabel(valor);
        val.setFont(new Font("Arial", Font.BOLD, 17));
        val.setForeground(new Color(26, 35, 126));

        card.add(lbl);
        card.add(Box.createVerticalStrut(4));
        card.add(val);
        return card;
    }

    private void mostrarErro(String mensagem) {
        painelResultado.removeAll();
        painelResultado.setBackground(new Color(255, 243, 243));

        JPanel linhaIcone = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        linhaIcone.setBackground(new Color(255, 243, 243));
        linhaIcone.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel icone = new JLabel("⚠️");
        icone.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));

        JLabel titulo = new JLabel("Cidade não encontrada");
        titulo.setFont(new Font("Arial", Font.BOLD, 14));
        titulo.setForeground(new Color(163, 45, 45));

        linhaIcone.add(icone);
        linhaIcone.add(titulo);

        JLabel msg = new JLabel("<html>" + mensagem.replace("\n", "<br>") + "</html>");
        msg.setFont(new Font("Arial", Font.PLAIN, 13));
        msg.setForeground(new Color(163, 45, 45));
        msg.setAlignmentX(Component.LEFT_ALIGNMENT);
        msg.setBorder(new EmptyBorder(6, 0, 0, 0));

        painelResultado.add(linhaIcone);
        painelResultado.add(msg);
        painelResultado.revalidate();
        painelResultado.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}