package com.caroline;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.URL;

public class TelaClima extends JFrame {

    private JTextField campoCidade;
    private JLabel labelIcone;
    private JLabel labelTemperatura;
    private JLabel labelCondicao;
    private JLabel labelMaxMin;
    private JLabel labelUmidade;
    private JLabel labelPrecipitacao;
    private JLabel labelVento;
    private ServicoClima servicoClima;

    public TelaClima() {
        try {
            servicoClima = new ServicoClima();
        } catch (IllegalStateException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro de Configuração", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        setTitle("Consulta de Clima e Tempo");
        setSize(450, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel painelInput = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        JLabel labelInstrucao = new JLabel("Cidade:");
        labelInstrucao.setFont(new Font("Arial", Font.BOLD, 14));
        campoCidade = new JTextField(18);
        campoCidade.setFont(new Font("Arial", Font.PLAIN, 14));
        painelInput.add(labelInstrucao);
        painelInput.add(campoCidade);

        JButton botaoBuscar = new JButton("Buscar Clima");
        botaoBuscar.setFont(new Font("Arial", Font.BOLD, 14));
        botaoBuscar.setAlignmentX(Component.CENTER_ALIGNMENT);

        labelIcone = new JLabel("Aguardando...", SwingConstants.CENTER);
        labelIcone.setFont(new Font("Arial", Font.ITALIC, 14));
        labelIcone.setAlignmentX(Component.CENTER_ALIGNMENT);

        labelTemperatura = new JLabel("Temperatura Atual: --", SwingConstants.CENTER);
        labelTemperatura.setFont(new Font("Arial", Font.BOLD, 22));
        labelTemperatura.setAlignmentX(Component.CENTER_ALIGNMENT);

        labelCondicao = new JLabel("Condição: --", SwingConstants.CENTER);
        labelCondicao.setFont(new Font("Arial", Font.ITALIC, 16));
        labelCondicao.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel painelDetalhes = new JPanel(new GridLayout(5, 1, 5, 5));
        painelDetalhes.setBorder(BorderFactory.createTitledBorder("Informações Detalhadas"));
        painelDetalhes.setMaximumSize(new Dimension(400, 180));

        labelMaxMin = new JLabel(" Mínima: --  |  Máxima: --");
        labelUmidade = new JLabel(" Humidade do Ar: --");
        labelPrecipitacao = new JLabel(" Quantidade de Precipitação: --");
        labelVento = new JLabel(" Vento: --");

        Font fontDetalhes = new Font("Arial", Font.PLAIN, 14);
        labelMaxMin.setFont(fontDetalhes);
        labelUmidade.setFont(fontDetalhes);
        labelPrecipitacao.setFont(fontDetalhes);
        labelVento.setFont(fontDetalhes);

        painelDetalhes.add(labelMaxMin);
        painelDetalhes.add(labelUmidade);
        painelDetalhes.add(labelPrecipitacao);
        painelDetalhes.add(labelVento);

        painelPrincipal.add(painelInput);
        painelPrincipal.add(Box.createVerticalStrut(15));
        painelPrincipal.add(botaoBuscar);
        painelPrincipal.add(Box.createVerticalStrut(20));
        painelPrincipal.add(labelIcone);
        painelPrincipal.add(Box.createVerticalStrut(10));
        painelPrincipal.add(labelTemperatura);
        painelPrincipal.add(Box.createVerticalStrut(5));
        painelPrincipal.add(labelCondicao);
        painelPrincipal.add(Box.createVerticalStrut(20));
        painelPrincipal.add(painelDetalhes);

        add(painelPrincipal);

        botaoBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executarBusca();
            }
        });
    }

    private void executarBusca() {
        String cidade = campoCidade.getText().trim();

        if (cidade.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite o nome de uma cidade.", "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            RespostaClima resposta = servicoClima.obterDadosClima(cidade);

            if (resposta != null && resposta.getCondicoesAtuais() != null) {
                double tempAtual = resposta.getCondicoesAtuais().getTemperaturaAtual();
                String condicao = resposta.getCondicoesAtuais().getCondicaoTempo();
                double umidade = resposta.getCondicoesAtuais().getUmidadeAr();
                double velVento = resposta.getCondicoesAtuais().getVelocidadeVento();
                double dirVento = resposta.getCondicoesAtuais().getDirecaoVento();
                String textoIcone = resposta.getCondicoesAtuais().getIcone();

                labelTemperatura.setText(String.format("%.1f °C", tempAtual));
                labelCondicao.setText(condicao);
                labelUmidade.setText(String.format(" Humidade do Ar: %.0f%%", umidade));

                String direcaoTexto = converterGrausParaDirecao(dirVento);
                labelVento.setText(
                        String.format(" Vento: %.1f km/h | Direção: %s (%.0f°)", velVento, direcaoTexto, dirVento));

                carregarIconeOficial(textoIcone);

                if (resposta.getDias() != null && !resposta.getDias().isEmpty()) {
                    PrevisaoDia hoje = resposta.getDias().get(0);
                    double max = hoje.getTemperaturaMaxima();
                    double min = hoje.getTemperaturaMinima();
                    double precip = hoje.getPrecipitacao();

                    labelMaxMin.setText(String.format(" Mínima: %.1f °C  |  Máxima: %.1f °C", min, max));
                    labelPrecipitacao.setText(String.format(" Quantidade de Precipitação: %.1f mm", precip));
                }
            }

        } catch (ConexaoException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro de API", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro inesperado ao processar os dados: " + ex.getMessage(), "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private String converterGrausParaDirecao(double graus) {
        String[] direcoes = { "Norte", "Nordeste", "Leste", "Sudeste", "Sul", "Sudoeste", "Oeste", "Noroeste" };
        int index = (int) Math.round(((graus % 360) / 45.0)) % 8;
        return direcoes[index];
    }

    private void carregarIconeOficial(String iconeAPI) {
        if (iconeAPI == null || iconeAPI.isEmpty()) {
            iconeAPI = "clear-day";
        }

        try {
            String urlImagem = "https://raw.githubusercontent.com/visualcrossing/WeatherIcons/main/PNG/1st%20Set%20-%20Color/"
                    + iconeAPI + ".png";
            java.net.URL url = java.net.URI.create(urlImagem).toURL();

            java.net.HttpURLConnection conexao = (java.net.HttpURLConnection) url.openConnection();
            conexao.setRequestProperty("User-Agent", "Mozilla/5.0");
            conexao.connect();

            Image img = javax.imageio.ImageIO.read(conexao.getInputStream());

            if (img != null) {
                Image imgRedimensionada = img.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
                labelIcone.setIcon(new ImageIcon(imgRedimensionada));
                labelIcone.setText("");
            } else {
                throw new Exception("Imagem retornou vazia do servidor.");
            }

        } catch (Exception ex) {
            labelIcone.setIcon(null);
            labelIcone.setText("[" + iconeAPI + "]");
        }
    }
}