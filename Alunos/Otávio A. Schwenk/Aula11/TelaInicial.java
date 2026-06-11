import javax.swing.*;
import java.awt.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;

public class TelaInicial {

    private JPanel painel;
    private JComboBox<String> estados;
    private JComboBox<String> cidades;
    private JButton botaoPesquisar;

    private Controlador controlador;

    private final String[] unidadesFederativas = {
            "AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO",
            "MA", "MG", "MS", "MT", "PA", "PB", "PE", "PI", "PR",
            "RJ", "RN", "RO", "RR", "RS", "SC", "SE", "SP", "TO"
    };

    private final Color azulPadrao = new Color(204, 224, 234);
    private final Color corFonte = new Color(43, 55, 99);
    private final Color corEspecial = new Color(187, 57, 112);
    private final Color corClara = new Color(234, 241, 247);

    private final Font fonteTitulo = new Font("Arial Black", Font.BOLD, 48);
    private final Font fonteTexto = new Font("Arial", Font.PLAIN, 24);
    private final Font fonteComboBox = new Font("Arial", Font.PLAIN, 16);

    public TelaInicial(Controlador controlador) {
        this.controlador = controlador;
        criarTela();
        configurarEventos();
        buscarCidades((String) estados.getSelectedItem());
    }

    public JPanel getPainel() {
        return painel;
    }

    private void criarTela() {
        painel = new JPanel();
        painel.setLayout(null);
        painel.setBackground(azulPadrao);

        JLabel titulo = new JLabel("Weather Report", JLabel.CENTER);
        titulo.setFont(fonteTitulo);
        titulo.setBounds(125, 150, 500, 100);
        titulo.setForeground(corFonte);
        painel.add(titulo);

        JPanel painelSelecao = new JPanel();
        painelSelecao.setBounds(125, 275, 500, 400);
        painelSelecao.setLayout(null);
        painelSelecao.setBackground(new Color(223, 234, 243));

        JLabel estadosTitulo = new JLabel("Selecione o estado:", JLabel.CENTER);
        estadosTitulo.setFont(fonteTexto);
        estadosTitulo.setBounds(0, 25, 500, 25);
        estadosTitulo.setForeground(corFonte);

        estados = new JComboBox<>(unidadesFederativas);
        estados.setBounds(35, 60, 430, 25);
        estados.setFont(fonteComboBox);
        estados.setForeground(Color.BLACK);

        JLabel cidadesTitulo = new JLabel("Selecione a cidade:", JLabel.CENTER);
        cidadesTitulo.setFont(fonteTexto);
        cidadesTitulo.setBounds(0, 110, 500, 25);
        cidadesTitulo.setForeground(corFonte);

        cidades = new JComboBox<>();
        cidades.setBounds(35, 145, 430, 25);
        cidades.setFont(fonteComboBox);
        cidades.setForeground(Color.BLACK);

        botaoPesquisar = new JButton("Pesquisar clima");
        botaoPesquisar.setBounds(75, 260, 350, 75);
        botaoPesquisar.setFont(fonteTexto);
        botaoPesquisar.setBackground(corEspecial);
        botaoPesquisar.setForeground(corClara);
        botaoPesquisar.setFocusPainted(false);

        painelSelecao.add(estadosTitulo);
        painelSelecao.add(estados);
        painelSelecao.add(cidadesTitulo);
        painelSelecao.add(cidades);
        painelSelecao.add(botaoPesquisar);

        painel.add(painelSelecao);
    }

    private void configurarEventos() {
        estados.addActionListener(e -> buscarCidades((String) estados.getSelectedItem()));

        botaoPesquisar.addActionListener(e -> {
            try {
                String estadoSelecionado = (String) estados.getSelectedItem();
                String cidadeSelecionada = (String) cidades.getSelectedItem();

                if (cidadeSelecionada == null || cidadeSelecionada.equals("Carregando...")) {
                    JOptionPane.showMessageDialog(painel,
                            "Nenhuma cidade selecionada!",
                            "Erro",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String cidadeCodificada = java.net.URLEncoder.encode(cidadeSelecionada, 
                    java.nio.charset.StandardCharsets.UTF_8);
                String estadoCodificado = java.net.URLEncoder.encode(estadoSelecionado, 
                    java.nio.charset.StandardCharsets.UTF_8);

                PrevisaoResultado previsao = Requisicao.obterPrevisao(cidadeCodificada, estadoCodificado);

                if (previsao != null) {
                    controlador.atualizarResultado(previsao);
                    controlador.mostrarTela("resultado");
                } else {
                    JOptionPane.showMessageDialog(painel,
                            "Não foi possível consultar a previsão do tempo",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(painel,
                        "Erro ao consultar a previsão do tempo:\n" + ex.getMessage(),
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });
    }

    private void buscarCidades(String uf) {
        cidades.removeAllItems();
        cidades.addItem("Carregando...");

        new Thread(() -> {
            try {
                String url = "https://servicodados.ibge.gov.br/api/v1/localidades/estados/"
                        + uf + "/municipios";

                HttpClient cliente = HttpClient.newHttpClient();
                HttpRequest requisicao = HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .GET()
                        .build();

                HttpResponse<String> resposta = cliente.send(requisicao, HttpResponse.BodyHandlers.ofString());

                JSONArray municipios = new JSONArray(resposta.body());

                SwingUtilities.invokeLater(() -> {
                    cidades.removeAllItems();
                    for (int i = 0; i < municipios.length(); i++) {
                        JSONObject municipio = municipios.getJSONObject(i);
                        cidades.addItem(municipio.getString("nome"));
                    }
                });
            } catch (Exception e) {
                SwingUtilities.invokeLater(() -> {
                    cidades.removeAllItems();
                    JOptionPane.showMessageDialog(painel,
                            "Não foi possível consultar as cidades!",
                            "Erro de consulta",
                            JOptionPane.WARNING_MESSAGE);
                });
                e.printStackTrace();
            }
        }).start();
    }
}