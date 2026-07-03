package provaDOO.view;

import javax.swing.*;

import provaDOO.model.Usuario;
import provaDOO.model.Serie;
import provaDOO.services.SerieService;
import provaDOO.services.UsuarioService;

import java.awt.*;

public class TelaHome extends JFrame {
	
	private Serie serieAtual;
	
	private final UsuarioService usuarioService =
	        new UsuarioService();

	private final SerieService serieService =
	        new SerieService();

    private JTextField txtPesquisa;

    private JLabel lblNome;
    private JLabel lblIdioma;
    private JLabel lblGeneros;
    private JLabel lblNota;
    private JLabel lblStatus;
    private JLabel lblEstreia;
    private JLabel lblTermino;
    private JLabel lblEmissora;

    private Usuario usuarioLogado;
    
    private TelaListaSeries telaFavoritos;
    private TelaListaSeries telaAssistidas;
    private TelaListaSeries telaDesejaAssistir;

    public TelaHome(Usuario usuario) {

        this.usuarioLogado = usuario;

        setTitle(
            "Minhas Séries - "
            + usuario.getNome()
        );

        setSize(1200, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        criarComponentes();
    }
    
    private void configurarJanela() {

        setTitle("Minhas Séries");
        setSize(1200, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    private void criarComponentes() {

        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBackground(new Color(18, 18, 18));

        painelPrincipal.add(criarTopo(), BorderLayout.NORTH);
        painelPrincipal.add(criarCentro(), BorderLayout.CENTER);

        add(painelPrincipal);
    }

    private JPanel criarTopo() {

        JPanel topo = new JPanel(new BorderLayout());
        topo.setBackground(new Color(18, 18, 18));
        topo.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("MINHAS SÉRIES");

        titulo.setFont(
                new Font("Segoe UI", Font.BOLD, 32));

        titulo.setForeground(
                new Color(229, 9, 20));

        JPanel painelDireito = new JPanel(
                new FlowLayout(FlowLayout.RIGHT, 10, 0));

        painelDireito.setOpaque(false);

        JButton btnFavoritos =
                criarBotaoTopo("Favoritos");

        btnFavoritos.addActionListener(e -> {

            if (telaFavoritos == null ||
                    !telaFavoritos.isDisplayable()) {

                telaFavoritos =
                        new TelaListaSeries(
                                usuarioLogado,
                                "Favoritos");

                telaFavoritos.setVisible(true);

            } else {

                telaFavoritos.toFront();
                telaFavoritos.requestFocus();
            }
        });

        JButton btnAssistidas =
                criarBotaoTopo("Assistidas");

        btnAssistidas.addActionListener(e -> {

            if (telaAssistidas == null ||
                    !telaAssistidas.isDisplayable()) {

                telaAssistidas =
                        new TelaListaSeries(
                                usuarioLogado,
                                "Assistidas");

                telaAssistidas.setVisible(true);

            } else {

                telaAssistidas.toFront();
                telaAssistidas.requestFocus();
            }
        });

        JButton btnDesejaAssistir =
                criarBotaoTopo("Deseja Assistir");

        btnDesejaAssistir.addActionListener(e -> {

            if (telaDesejaAssistir == null ||
                    !telaDesejaAssistir.isDisplayable()) {

                telaDesejaAssistir =
                        new TelaListaSeries(
                                usuarioLogado,
                                "Deseja Assistir");

                telaDesejaAssistir.setVisible(true);

            } else {

                telaDesejaAssistir.toFront();
                telaDesejaAssistir.requestFocus();
            }
        });

        painelDireito.add(btnFavoritos);
        painelDireito.add(btnAssistidas);
        painelDireito.add(btnDesejaAssistir);

        topo.add(titulo, BorderLayout.WEST);
        topo.add(painelDireito, BorderLayout.EAST);

        return topo;
    }

    private JPanel criarCentro() {

        JPanel centro = new JPanel();
        centro.setBackground(new Color(18, 18, 18));

        centro.setLayout(
                new BoxLayout(
                        centro,
                        BoxLayout.Y_AXIS));

        JPanel painelPesquisa = new JPanel();

        painelPesquisa.setOpaque(false);

        txtPesquisa = new JTextField(30);

        txtPesquisa.setFont(
                new Font("Segoe UI",
                        Font.PLAIN,
                        16));

        JButton btnPesquisar =
                new JButton("Pesquisar");

        btnPesquisar.addActionListener(
                e -> pesquisarSerie()
        );

        btnPesquisar.setFocusPainted(false);

        painelPesquisa.add(txtPesquisa);
        painelPesquisa.add(btnPesquisar);

        centro.add(Box.createVerticalStrut(30));
        centro.add(painelPesquisa);
        centro.add(Box.createVerticalStrut(40));

        JPanel painelInfo = criarPainelInformacoes();

        centro.add(painelInfo);

        return centro;
    }

    private JPanel criarPainelInformacoes() {

        JPanel painel = new JPanel();

        painel.setLayout(
                new GridLayout(8, 1, 5, 5));

        painel.setBackground(
                new Color(30, 30, 30));

        painel.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20));

        painel.setMaximumSize(
                new Dimension(700, 350));

        lblNome = criarLabel("Nome: ");
        lblIdioma = criarLabel("Idioma: ");
        lblGeneros = criarLabel("Gêneros: ");
        lblNota = criarLabel("Nota: ");
        lblStatus = criarLabel("Status: ");
        lblEstreia = criarLabel("Estreia: ");
        lblTermino = criarLabel("Término: ");
        lblEmissora = criarLabel("Emissora: ");

        painel.add(lblNome);
        painel.add(lblIdioma);
        painel.add(lblGeneros);
        painel.add(lblNota);
        painel.add(lblStatus);
        painel.add(lblEstreia);
        painel.add(lblTermino);
        painel.add(lblEmissora);

        JPanel container = new JPanel();
        container.setOpaque(false);

        container.setLayout(
                new BoxLayout(
                        container,
                        BoxLayout.Y_AXIS));

        painel.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        container.add(painel);
        container.add(Box.createVerticalStrut(20));

        JPanel botoes = new JPanel();
        botoes.setOpaque(false);

        JButton btnFavoritar =
                criarBotaoAcao("Favoritar");

        btnFavoritar.addActionListener(
                e -> adicionarFavorito()
        );

        JButton btnAssistida =
                criarBotaoAcao("Já Assisti");

        btnAssistida.addActionListener(
                e -> adicionarAssistida()
        );

        JButton btnDesejaAssistir =
                criarBotaoAcao("Desejo Assistir");

        btnDesejaAssistir.addActionListener(
                e -> adicionarDesejaAssistir()
        );

        botoes.add(btnFavoritar);
        botoes.add(btnAssistida);
        botoes.add(btnDesejaAssistir);

        container.add(botoes);

        return container;
    }
    
    private void pesquisarSerie() {

    	String pesquisa = txtPesquisa.getText().trim();

    	if (pesquisa.isBlank()) {

    	    JOptionPane.showMessageDialog(
    	            this,
    	            "Digite o nome de uma série."
    	    );

    	    return;
    	}

    	if (!pesquisa.matches("[\\p{L}0-9\\s]+")) {

    	    JOptionPane.showMessageDialog(
    	            this,
    	            "A pesquisa contém caracteres inválidos."
    	    );

    	    return;
    	}
    	
        try {

            serieAtual =
                    serieService.pesquisar(
                            txtPesquisa.getText());

            lblNome.setText(
                    "Nome: " +
                    serieAtual.getNome());

            lblIdioma.setText(
                    "Idioma: " +
                    serieAtual.getIdioma());

            lblGeneros.setText(
                    "Gêneros: " +
                    String.join(
                            ", ",
                            serieAtual.getGeneros()));

            lblNota.setText(
                    "Nota: " +
                    serieAtual.getNota());

            lblStatus.setText(
                    "Status: " +
                    serieAtual.getStatus());

            lblEstreia.setText(
                    "Estreia: " +
                    serieAtual.getEstreia());

            lblTermino.setText(
                    "Término: " +
                    serieAtual.getTermino());

            lblEmissora.setText(
                    "Emissora: " +
                    serieAtual.getEmissora());

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private JLabel criarLabel(String texto) {

        JLabel label = new JLabel(texto);

        label.setForeground(Color.WHITE);

        label.setFont(
                new Font("Segoe UI",
                        Font.PLAIN,
                        18));

        return label;
    }

    private JButton criarBotaoTopo(String texto) {

        JButton botao = new JButton(texto);

        botao.setFont(new Font("Segoe UI", Font.BOLD, 16));
        botao.setForeground(Color.white);
        botao.setBackground(new Color(40, 40, 40));

        botao.setFocusPainted(false);
        botao.setBorderPainted(false);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));

        botao.setPreferredSize(new Dimension(180, 50));

        return botao;
    }
    
    private void adicionarFavorito() {

        if (serieAtual == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Pesquise uma série primeiro."
            );

            return;
        }

        try {

            usuarioService.adicionarFavorito(
                    usuarioLogado.getId(),
                    serieAtual.getId());

            usuarioLogado =
                    usuarioService.buscarPorId(
                            usuarioLogado.getId());

            JOptionPane.showMessageDialog(
                    this,
                    "Série adicionada aos favoritos!"
            );

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage()
            );
        }
    }

    private void adicionarAssistida() {

        if (serieAtual == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Pesquise uma série primeiro."
            );

            return;
        }

        try {

            usuarioService.adicionarAssistida(
                    usuarioLogado.getId(),
                    serieAtual.getId());

            usuarioLogado =
                    usuarioService.buscarPorId(
                            usuarioLogado.getId());

            JOptionPane.showMessageDialog(
                    this,
                    "Série adicionada aos assistidas!"
            );

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage()
            );
        }
    }

    private void adicionarDesejaAssistir() {

        if (serieAtual == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Pesquise uma série primeiro."
            );

            return;
        }

        try {

            usuarioService.adicionarDesejaAssistir(
                    usuarioLogado.getId(),
                    serieAtual.getId());

            usuarioLogado =
                    usuarioService.buscarPorId(
                            usuarioLogado.getId());

            JOptionPane.showMessageDialog(
                    this,
                    "Série adicionada aos Deseja Assistir!"
            );

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage()
            );
        }
    }

    private JButton criarBotaoAcao(String texto) {

        JButton botao =
                new JButton(texto);

        botao.setPreferredSize(
                new Dimension(170, 40));

        botao.setFont(new Font("Segoe UI", Font.BOLD, 16));
        botao.setForeground(Color.white);
        botao.setBackground(new Color(229, 9, 20));

        botao.setFocusPainted(false);
        botao.setBorderPainted(false);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return botao;
    }
}