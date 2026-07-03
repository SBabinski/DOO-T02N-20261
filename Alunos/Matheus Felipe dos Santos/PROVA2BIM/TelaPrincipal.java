import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends JFrame {

    private JTextField campoBusca;
    private JButton btnBuscar;
    private JButton btnListas;
    private JButton btnUsuario;
    private JButton btnQueroAssistir;

    private JLabel lblBanner;
    private JLabel lblNome;
    private JLabel lblIdioma;
    private JLabel lblStatus;
    private JLabel lblNota;
    private JLabel lblGeneros;
    private JLabel lblEstreia;
    private JLabel lblTermino;
    private JLabel lblEmissora;

    private Usuario usuario;
    private Serie ultimaSerie;

    public TelaPrincipal(Usuario usuario) {

        this.usuario = usuario;

        setTitle("Series");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(35, 35, 35));

        campoBusca = new JTextField(20);
        btnBuscar = new JButton("Buscar");
        btnListas = new JButton("Minhas Listas");
        btnUsuario = new JButton("Usuário");
        btnQueroAssistir = new JButton("Quero assistir");
        lblBanner = new JLabel("Sem imagem", SwingConstants.CENTER);

        lblNome = new JLabel();
        lblIdioma = new JLabel();
        lblStatus = new JLabel();
        lblNota = new JLabel();
        lblGeneros = new JLabel();
        lblEstreia = new JLabel();
        lblTermino = new JLabel();
        lblEmissora = new JLabel();

        

        JPanel topo = new JPanel(new BorderLayout());
        topo.setBackground(new Color(78, 52, 155));
        topo.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        JLabel titulo = new JLabel("Series Tracker");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(Color.WHITE);

        topo.add(titulo, BorderLayout.WEST);

        JPanel painelBusca = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelBusca.setOpaque(false);

        campoBusca.setPreferredSize(new Dimension(250, 30));

        btnBuscar.setBackground(new Color(120, 70, 220));
        btnBuscar.setForeground(Color.WHITE);

        painelBusca.add(campoBusca);
        painelBusca.add(btnBuscar);

        topo.add(painelBusca, BorderLayout.EAST);

        add(topo, BorderLayout.NORTH);

        JPanel centro = new JPanel(new BorderLayout(15, 15));
        centro.setBackground(new Color(35, 35, 35));
        centro.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

    
        JPanel painelInfo = new JPanel();
        painelInfo.setLayout(new BoxLayout(painelInfo, BoxLayout.Y_AXIS));
        painelInfo.setBackground(new Color(45, 45, 45));
        painelInfo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        Font fonteTitulo = new Font("Arial", Font.BOLD, 18);
        Font texto = new Font("Arial", Font.PLAIN, 14);

        lblNome.setFont(fonteTitulo);
        lblIdioma.setFont(texto);
        lblStatus.setFont(texto);
        lblNota.setFont(texto);
        lblGeneros.setFont(texto);
        lblEstreia.setFont(texto);
        lblTermino.setFont(texto);
        lblEmissora.setFont(texto);

        lblNome.setForeground(Color.WHITE);
        lblIdioma.setForeground(Color.WHITE);
        lblStatus.setForeground(Color.WHITE);
        lblNota.setForeground(Color.WHITE);
        lblGeneros.setForeground(Color.WHITE);
        lblEstreia.setForeground(Color.WHITE);
        lblTermino.setForeground(Color.WHITE);
        lblEmissora.setForeground(Color.WHITE);

        painelInfo.add(lblNome);
        painelInfo.add(lblIdioma);
        painelInfo.add(lblStatus);
        painelInfo.add(lblNota);
        painelInfo.add(lblGeneros);
        painelInfo.add(lblEstreia);
        painelInfo.add(lblTermino);
        painelInfo.add(lblEmissora);

        lblBanner.setPreferredSize(new Dimension(250, 340));
        lblBanner.setOpaque(true);
        lblBanner.setBackground(new Color(70, 70, 70));

        centro.add(painelInfo, BorderLayout.CENTER);
        centro.add(lblBanner, BorderLayout.EAST);

        add(centro, BorderLayout.CENTER);

        JPanel rodape = new JPanel();

        rodape.setBackground(new Color(35,35,35));

        btnQueroAssistir.setBackground(new Color(120,70,220));
        btnQueroAssistir.setForeground(Color.WHITE);

        btnListas.setBackground(new Color(120,70,220));
        btnListas.setForeground(Color.WHITE);

        btnUsuario.setBackground(new Color(120,70,220));
        btnUsuario.setForeground(Color.WHITE);

        rodape.add(btnQueroAssistir);
        rodape.add(btnListas);
        rodape.add(btnUsuario);

        add(rodape, BorderLayout.SOUTH);

        btnBuscar.addActionListener(e -> buscarSerie());
        campoBusca.addActionListener(e -> buscarSerie());

        btnListas.addActionListener(e -> new TelaListas(usuario));

        btnUsuario.addActionListener(e -> new TelaUsuario(usuario));

        btnQueroAssistir.addActionListener(e -> {
            if (ultimaSerie != null) {
                usuario.adicionarQueroAssistir(ultimaSerie);
                PersistenciaUsuario.salvar(usuario);
                JOptionPane.showMessageDialog(this, "Adicionado à lista!");
            }
        });

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                PersistenciaUsuario.salvar(usuario);
            }
        });

        setVisible(true);
    }

    private void buscarSerie() {

    String nomeSerie = campoBusca.getText().trim();

    if (nomeSerie.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Digite o nome de uma série!");
        return;
    }

    btnBuscar.setEnabled(false);

    SwingWorker<Serie, Void> worker = new SwingWorker<Serie, Void>() {

        @Override
        protected Serie doInBackground() {
            ApiSeries api = new ApiSeries();
            return api.buscarSerie(nomeSerie);
        }

        @Override
        protected void done() {
            try {
                btnBuscar.setEnabled(true);

                Serie serie = get();

                if (serie == null ||
                        "Não encontrada".equals(serie.getNome()) ||
                        "Erro".equals(serie.getNome())) {

                    JOptionPane.showMessageDialog(
                            TelaPrincipal.this,
                            "Série não encontrada!"
                    );
                    return;
                }

                ultimaSerie = serie;

                lblNome.setText("Nome: " + serie.getNome());
                lblIdioma.setText("Idioma: " + serie.getIdioma());
                lblStatus.setText("Status: " + serie.getStatus());
                lblNota.setText("Nota: " + serie.getNota());
                lblGeneros.setText("Gêneros: " + serie.getGeneros());
                lblEstreia.setText("Estreia: " + serie.getEstreia());
                lblTermino.setText("Término: " + serie.getTermino());
                lblEmissora.setText("Emissora: " + serie.getEmissora());

                carregarImagem(serie.getImagemUrl());

            } catch (Exception e) {
                btnBuscar.setEnabled(true);
                JOptionPane.showMessageDialog(
                        TelaPrincipal.this,
                        "Erro ao buscar série: " + e.getMessage()
                );
            }
        }
    };

    worker.execute();
        }

            private void carregarImagem(String url) {
                try {
                    ImageIcon icon = new ImageIcon(new java.net.URL(url));
                    Image img = icon.getImage().getScaledInstance(250, 340, Image.SCALE_SMOOTH);
                    lblBanner.setIcon(new ImageIcon(img));
                    lblBanner.setText("");
                } catch (Exception e) {
                    lblBanner.setText("Sem imagem");
                    lblBanner.setIcon(null);
                }
            }
}