import javax.swing.*;
import java.awt.*;

public class TelaResultado {

    private JPanel painel;
    private JButton botaoVoltar;
    private JPanel painelCentral;

    private final Color azulPadrao = new Color(204, 224, 234);
    private final Color corFundoQuadrado = new Color(245, 245, 245);
    private final Color corEspecial = new Color(187, 57, 112);
    private final Color corClara = new Color(234, 241, 247);
    private final Font fonteTexto = new Font("Arial", Font.PLAIN, 20);
    private final Font fonteTitulo = new Font("Arial Black", Font.BOLD, 28);

    private Controlador controlador;

    public TelaResultado(Controlador controlador) {
        this.controlador = controlador;

        painel = new JPanel();
        painel.setLayout(null);
        painel.setBackground(azulPadrao);

        // Botão voltar
        botaoVoltar = new JButton("Voltar");
        botaoVoltar.setFont(fonteTexto);
        botaoVoltar.setBackground(corEspecial);
        botaoVoltar.setForeground(corClara);
        botaoVoltar.setFocusPainted(false);
        botaoVoltar.setBounds(275, 600, 200, 50);
        botaoVoltar.addActionListener(e -> controlador.mostrarTela("selecao"));
        painel.add(botaoVoltar);

        painelCentral = new JPanel();
        painelCentral.setLayout(null);
        painelCentral.setBackground(corFundoQuadrado);
        painelCentral.setBounds(125, 50, 500, 500);
        painel.add(painelCentral);
    }

    public JPanel getPainel() {
        return painel;
    }

    // Marquei cada label para não esquecer
    public void atualizarResultado(PrevisaoResultado previsao) {
        painelCentral.removeAll();

        int y = 20;
        int espacamento = 50;

        // Título
        JLabel titulo = new JLabel("Previsão do Tempo");
        titulo.setFont(fonteTitulo);
        titulo.setBounds(100, 0, 400, 30);
        painelCentral.add(titulo);

        y += 40;

        // Temperatura atual
        ImageIcon iconTemp = new ImageIcon(new ImageIcon("./icons/temperatura.png")
                .getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        JLabel tempAtual = new JLabel("Temperatura atual: " + 
        String.format("%.1f°C", previsao.getTemperaturaAtual()), iconTemp, JLabel.LEFT);
        tempAtual.setFont(fonteTexto);
        tempAtual.setBounds(20, y, 450, 30);
        painelCentral.add(tempAtual);

        y += espacamento;

        // Temperatura máxima
        ImageIcon iconTempMax = new ImageIcon(new ImageIcon("./icons/temperaturaMaxima.png")
                .getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        JLabel tempMax = new JLabel("Temperatura máxima: " + 
        String.format("%.1f°C", previsao.getTemperaturaMaxima()), iconTempMax, JLabel.LEFT);
        tempMax.setFont(fonteTexto);
        tempMax.setBounds(20, y, 450, 30);
        painelCentral.add(tempMax);

        y += espacamento;

        // Temperatura mínima
        ImageIcon iconTempMin = new ImageIcon(new ImageIcon("./icons/temperaturaMinima.png")
                .getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        JLabel tempMin = new JLabel("Temperatura mínima: " + 
        String.format("%.1f°C", previsao.getTemperaturaMinima()), iconTempMin, JLabel.LEFT);
        tempMin.setFont(fonteTexto);
        tempMin.setBounds(20, y, 450, 30);
        painelCentral.add(tempMin);

        y += espacamento;

        // Umidade
        ImageIcon iconUmidade = new ImageIcon(new ImageIcon("./icons/umidade.png")
                .getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        JLabel umidade = new JLabel("Umidade: " + previsao.getUmidade() + "%", iconUmidade, JLabel.LEFT);
        umidade.setFont(fonteTexto);
        umidade.setBounds(20, y, 450, 30);
        painelCentral.add(umidade);

        y += espacamento;

        // Condição
        ImageIcon iconCondicao = new ImageIcon(new ImageIcon("./icons/" + previsao.getIcon() + ".png")
                .getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        String condicaoPortugues = traduzirCondicao(previsao.getIcon());
        JLabel condicao = new JLabel("Condição: " + condicaoPortugues, iconCondicao, JLabel.LEFT);
        condicao.setFont(fonteTexto);
        condicao.setBounds(20, y, 450, 30);
        painelCentral.add(condicao);

        y += espacamento;

        // Precipitação
        ImageIcon iconPrecip = new ImageIcon(new ImageIcon("./icons/precipitacao.png")
                .getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        JLabel precip = new JLabel("Precipitação: " + 
        String.format("%.1f mm", previsao.getPrecipitacao()), iconPrecip, JLabel.LEFT);
        precip.setFont(fonteTexto);
        precip.setBounds(20, y, 450, 30);
        painelCentral.add(precip);

        y += espacamento;

        // Vento - velocidade
        ImageIcon iconVento = new ImageIcon(new ImageIcon("./icons/velocidadeVento.png")
                .getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        JLabel ventoVel = new JLabel("Velocidade do vento: " + 
        String.format("%.1f km/h", previsao.getVelocidadeVento()), iconVento, JLabel.LEFT);
        ventoVel.setFont(fonteTexto);
        ventoVel.setBounds(20, y, 450, 30);
        painelCentral.add(ventoVel);

        y += espacamento;

        // Direção do vento
        ImageIcon iconDirecao = new ImageIcon(new ImageIcon("./icons/direcaoVento.png")
                .getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        String direcaoCardeal = converterDirecao(previsao.getDirecaoVento());
        JLabel ventoDir = new JLabel("Direção do vento: " + direcaoCardeal, iconDirecao, JLabel.LEFT);
        ventoDir.setFont(fonteTexto);
        ventoDir.setBounds(20, y, 450, 30);
        painelCentral.add(ventoDir);
    }

    private String traduzirCondicao(String condicaoIngles) {
        switch (condicaoIngles.toLowerCase()) {
            case "clear-day": return "Ensolarado";
            case "clear-night": return "Noite limpa";
            case "rain": return "Chuva";
            case "snow": return "Neve";
            case "sleet": return "Granizo";
            case "wind": return "Vento";
            case "fog": return "Neblina";
            case "cloudy": return "Nublado";
            case "partly-cloudy-day": return "Parcialmente nublado";
            case "partly-cloudy-night": return "Parcialmente nublado à noite";
            case "overcast": return "Nublado fechado";
            default: return condicaoIngles;
        }
    }

    private String converterDirecao(double graus) {
        String[] direcoes = {"Norte", "Norte-Nordeste", "Nordeste", "Leste-Nordeste",
                            "Leste", "Leste-Sudeste", "Sudeste", "Sul-Sudeste",
                            "Sul", "Sul-Sudoeste", "Sudoeste", "Oeste-Sudoeste",
                            "Oeste", "Oeste-Noroeste", "Noroeste", "Norte-Noroeste"};
        int index = (int)Math.round(((graus % 360) / 22.5)) % 16;
        return direcoes[index];
    }

}