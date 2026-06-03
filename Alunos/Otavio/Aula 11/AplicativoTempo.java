import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AplicativoTempo extends JFrame {
    private JTextField campoCidade;
    private JButton botaoBusca;

    private JLabel cidadeTexto;
    private JLabel temperaturaTexto;
    private JLabel condicaoTexto;
    private JLabel minMaxTexto;
    private JLabel umidadeTexto;
    private JLabel precipitacaoTexto;
    private JLabel ventoTexto;

    private ComunicaApiTempo comunicaApi;

    private final Color COR_FUNDO = new Color(241, 245, 249);       // Cinza claro azulado
    private final Color COR_CARD = Color.WHITE;
    private final Color COR_PRIMARIA = new Color(37, 99, 235);    // Azul
    private final Color COR_TEXTO = new Color(15, 23, 42);   // Quase preto
    private final Color COR_TEXTO_CINZA = new Color(100, 116, 139); // Cinza

    public AplicativoTempo() {
        comunicaApi = new ComunicaApiTempo();
        IniciaPagina();
    }

    private void IniciaPagina() {
        setTitle("Aplicativo de tempo :)");
        setSize(460, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        getContentPane().setBackground(COR_FUNDO);
        setLayout(new BorderLayout(15, 15));
        ((JPanel)getContentPane()).setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel painelTopo = new JPanel(new BorderLayout(10, 0));
        painelTopo.setOpaque(false);

        campoCidade = new JTextField();
        campoCidade.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campoCidade.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(203, 213, 225), 1),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));

        botaoBusca = new JButton("Buscar Clima");
        botaoBusca.setFont(new Font("Segoe UI", Font.BOLD, 13));
        botaoBusca.setBackground(COR_PRIMARIA);
        botaoBusca.setForeground(COR_TEXTO);
        botaoBusca.setFocusPainted(false);
        botaoBusca.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoBusca.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));

        painelTopo.add(campoCidade, BorderLayout.CENTER);
        painelTopo.add(botaoBusca, BorderLayout.EAST);

        JPanel painelCartao = new JPanel(new BorderLayout(15, 15));
        painelCartao.setBackground(COR_CARD);
        painelCartao.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(226, 232, 240), 1),
                BorderFactory.createEmptyBorder(25, 25, 25, 25)
        ));

        JPanel campoPrincipal = new JPanel();
        campoPrincipal.setLayout(new BoxLayout(campoPrincipal, BoxLayout.Y_AXIS));
        campoPrincipal.setOpaque(false);

        cidadeTexto = new JLabel("Instruções");
        cidadeTexto.setFont(new Font("Segoe UI", Font.BOLD, 22));
        cidadeTexto.setForeground(COR_PRIMARIA);
        cidadeTexto.setAlignmentX(Component.CENTER_ALIGNMENT);

        temperaturaTexto = new JLabel("-- °C");
        temperaturaTexto.setFont(new Font("Segoe UI", Font.BOLD, 54));
        temperaturaTexto.setForeground(COR_TEXTO);
        temperaturaTexto.setAlignmentX(Component.CENTER_ALIGNMENT);
        temperaturaTexto.setBorder(new EmptyBorder(10, 0, 5, 0));

        condicaoTexto = new JLabel("Digite uma cidade para consultar o tempo atual.");
        condicaoTexto.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        condicaoTexto.setForeground(COR_TEXTO_CINZA);
        condicaoTexto.setAlignmentX(Component.CENTER_ALIGNMENT);

        campoPrincipal.add(cidadeTexto);
        campoPrincipal.add(temperaturaTexto);
        campoPrincipal.add(condicaoTexto);

        JPanel painelCampos = new JPanel(new GridLayout(2, 2, 15, 15));
        painelCampos.setOpaque(false);
        painelCampos.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(241, 245, 249)));
        painelCampos.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(241, 245, 249)),
                BorderFactory.createEmptyBorder(15, 0, 0, 0)
        ));

        minMaxTexto = CriarCampoTexto("🌡️ Extremos", "Máx: -- | Mín: --");
        umidadeTexto = CriarCampoTexto("💧 Umidade", "-- %");
        precipitacaoTexto = CriarCampoTexto("🌧️ Precipitação", "-- mm");
        ventoTexto = CriarCampoTexto("💨 Vento", "-- km/h");

        painelCampos.add(minMaxTexto);
        painelCampos.add(umidadeTexto);
        painelCampos.add(precipitacaoTexto);
        painelCampos.add(ventoTexto);

        painelCartao.add(campoPrincipal, BorderLayout.CENTER);
        painelCartao.add(painelCampos, BorderLayout.SOUTH);

        add(painelTopo, BorderLayout.NORTH);
        add(painelCartao, BorderLayout.CENTER);

        botaoBusca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarClima();
            }
        });

        campoCidade.addActionListener(e -> buscarClima());
    }

    private JLabel CriarCampoTexto(String title, String value) {
        JLabel label = new JLabel();
        label.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        AtualizaTextoCampos(label, title, value);
        return label;
    }

    private void AtualizaTextoCampos(JLabel label, String title, String value) {
        label.setText("<html><font color='#64748b'>" + title + "</font><br><b><font size='4' color='#0f172a'>" + value + "</font></b></html>");
    }

    private void buscarClima() {
        String cidade = campoCidade.getText().trim();
        if (cidade.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, introduza o nome de uma cidade.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        cidadeTexto.setText("Buscando...");
        temperaturaTexto.setText("--");
        condicaoTexto.setText("A carregar informações da API...");

        new Thread(() -> {
            try {
                informacoesTempo data = comunicaApi.fetchWeather(cidade);

                SwingUtilities.invokeLater(() -> {
                    cidadeTexto.setText(data.getNomeCidade());
                    temperaturaTexto.setText(String.format("%.1f °C", data.getTemperaturaAtual()));
                    condicaoTexto.setText(data.getCondicoes());

                    AtualizaTextoCampos(minMaxTexto, "🌡️ Extremos", String.format("%.1f°C / %.1f°C", data.getTemperaturaMaxima(), data.getTemperaturaMinima()));
                    AtualizaTextoCampos(umidadeTexto, "💧 Umidade", String.format("%.0f %%", data.getUmidade()));
                    AtualizaTextoCampos(precipitacaoTexto, "🌧️ Precipitação", String.format("%.1f mm", data.getPrecipitacao()));
                    AtualizaTextoCampos(ventoTexto, "💨 Vento", String.format("%.1f km/h (%s)", data.getVelocidadeVento(), traduzirDirecaoVento(data.getDirecaoVento())));
                });

            } catch (Exception ex) {
                SwingUtilities.invokeLater(() -> {
                    cidadeTexto.setText("Erro na Consulta");
                    temperaturaTexto.setText("❌");
                    condicaoTexto.setText("Verifique a conexão, o nome digitado ou sua API Key.");

                    AtualizaTextoCampos(minMaxTexto, "🌡️ Extremos", "N/D");
                    AtualizaTextoCampos(umidadeTexto, "💧 Umidade", "N/D");
                    AtualizaTextoCampos(precipitacaoTexto, "🌧️ Precipitação", "N/D");
                    AtualizaTextoCampos(ventoTexto, "💨 Vento", "N/D");
                });
                ex.printStackTrace();
            }
        }).start();
    }

    private String traduzirDirecaoVento(double graus) {
        if (graus >= 337.5 || graus < 22.5) return "N";
        if (graus >= 22.5 && graus < 67.5) return "NE";
        if (graus >= 67.5 && graus < 112.5) return "L";
        if (graus >= 112.5 && graus < 157.5) return "SE";
        if (graus >= 157.5 && graus < 202.5) return "S";
        if (graus >= 202.5 && graus < 247.5) return "SO";
        if (graus >= 247.5 && graus < 292.5) return "O";
        return "NO";
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> {
            AplicativoTempo app = new AplicativoTempo();
            app.setVisible(true);
        });
    }
}