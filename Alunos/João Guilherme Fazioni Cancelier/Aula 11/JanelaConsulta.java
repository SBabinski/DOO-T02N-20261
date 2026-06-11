import javax.swing.*;
import java.awt.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JanelaConsulta extends JFrame {
    private JComboBox<String> cbUf;
    private JComboBox<String> cbCidade;
    private JButton btnBuscar;

    private final String[] UFS = {
        "AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG", "MS", "MT", "PA", "PB", "PE", "PI", "PR",
        "RJ", "RN", "RO", "RR", "RS", "SC", "SE", "SP", "TO"
    };

    public JanelaConsulta() {
        setTitle("Consultar Clima");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 360);
        
        try {
            ImageIcon iconeApp = new ImageIcon("icons/clear-day.png");
            setIconImage(iconeApp.getImage());
        } catch (Exception e) {}

        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(30, 40, 35, 40));
        painelPrincipal.setBackground(new Color(245, 247, 250));

        JPanel painelUf = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 5));
        painelUf.setBackground(new Color(245, 247, 250));
        JLabel lblUf = new JLabel("Unidade Federativa (UF):");
        lblUf.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblUf.setForeground(new Color(33, 43, 54));
        painelUf.add(lblUf);

        cbUf = new JComboBox<>(UFS);
        cbUf.setPreferredSize(new Dimension(320, 35));
        cbUf.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JPanel painelCidade = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 5));
        painelCidade.setBackground(new Color(245, 247, 250));
        JLabel lblCidade = new JLabel("Cidade:");
        lblCidade.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblCidade.setForeground(new Color(33, 43, 54));
        painelCidade.add(lblCidade);

        cbCidade = new JComboBox<>(new String[]{"Selecione uma UF..."});
        cbCidade.setPreferredSize(new Dimension(320, 35));
        cbCidade.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        btnBuscar = new JButton("Buscar Clima");
        btnBuscar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnBuscar.setMaximumSize(new Dimension(320, 42));
        btnBuscar.setPreferredSize(new Dimension(320, 42));
        btnBuscar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnBuscar.setBackground(new Color(24, 144, 255));
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setContentAreaFilled(false);
        btnBuscar.setOpaque(true);
        btnBuscar.setFocusPainted(false);
        btnBuscar.setBorder(BorderFactory.createLineBorder(new Color(24, 144, 255), 1));
        btnBuscar.setEnabled(false);

        painelPrincipal.add(painelUf);
        painelPrincipal.add(cbUf);
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 15)));
        painelPrincipal.add(painelCidade);
        painelPrincipal.add(cbCidade);
        painelPrincipal.add(Box.createRigidArea(new Dimension(0, 25)));
        painelPrincipal.add(btnBuscar);

        add(painelPrincipal);

        cbUf.addActionListener(e -> {
            String ufSelecionada = (String) cbUf.getSelectedItem();
            if (ufSelecionada != null) {
                carregarCidadesDoIbge(ufSelecionada);
            }
        });

        btnBuscar.addActionListener(e -> {
            String cidade = cbCidade.getSelectedItem().toString();
            String uf = cbUf.getSelectedItem().toString();

            if (cidade.startsWith("Selecione") || cidade.startsWith("Carregando")) {
                return;
            }

            btnBuscar.setText("Buscando...");
            btnBuscar.setEnabled(false);

            new Thread(() -> {
                try {
                    ClimaService servico = new ClimaService();
                    ClimaDados resultado = servico.buscar(cidade, uf); 
                    
                    SwingUtilities.invokeLater(() -> {
                        dispose(); 
                        new JanelaResultado(resultado).setVisible(true); 
                    });
                } catch (Exception ex) {
                    SwingUtilities.invokeLater(() -> {
                        btnBuscar.setText("Buscar Clima");
                        btnBuscar.setBackground(new Color(24, 144, 255));
                        btnBuscar.setBorder(BorderFactory.createLineBorder(new Color(24, 144, 255), 1));
                        btnBuscar.setEnabled(true);
                        JOptionPane.showMessageDialog(this, "Erro ao buscar clima:\n" + ex.getMessage(), "Erro na API", JOptionPane.ERROR_MESSAGE);
                    });
                }
            }).start();
        });

        setResizable(false);
        setLocationRelativeTo(null);
        carregarCidadesDoIbge((String) cbUf.getSelectedItem());
    }

    private void carregarCidadesDoIbge(String uf) {
        cbCidade.removeAllItems();
        cbCidade.addItem("Carregando...");
        cbCidade.setEnabled(false);
        btnBuscar.setEnabled(false);
        btnBuscar.setBackground(new Color(180, 210, 245)); 
        btnBuscar.setBorder(BorderFactory.createLineBorder(new Color(180, 210, 245), 1));

        new Thread(() -> {
            List<String> cidades = new ArrayList<>();
             String url = "https://servicodados.ibge.gov.br/api/v1/localidades/estados/%s/municipios".formatted(uf);

            try (HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10)).build()) {
                HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() == 200) {
                    ObjectMapper mapper = new ObjectMapper();
                    JsonNode raiz = mapper.readTree(response.body());

                    for (JsonNode nóCidade : raiz) {
                        cidades.add(nóCidade.get("nome").asText());
                    }

                    Collections.sort(cidades);

                    SwingUtilities.invokeLater(() -> {
                        cbCidade.removeAllItems();
                        for (String nomeCidade : cidades) {
                            cbCidade.addItem(nomeCidade);
                        }
                        cbCidade.setEnabled(true);
                        btnBuscar.setBackground(new Color(24, 144, 255));
                        btnBuscar.setBorder(BorderFactory.createLineBorder(new Color(24, 144, 255), 1));
                        btnBuscar.setEnabled(true);
                    });
                } else {
                    throw new RuntimeException("Código inválido: " + response.statusCode());
                }
            } catch (Exception ex) {
                SwingUtilities.invokeLater(() -> {
                    cbCidade.removeAllItems();
                    cbCidade.addItem("Falha ao carregar");
                    btnBuscar.setBackground(new Color(24, 144, 255));
                    btnBuscar.setBorder(BorderFactory.createLineBorder(new Color(24, 144, 255), 1));
                });
            }
        }).start();
    }
}