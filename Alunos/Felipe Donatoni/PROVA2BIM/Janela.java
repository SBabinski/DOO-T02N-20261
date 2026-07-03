import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class Janela extends JFrame {

    private Usuario usuario;
    private ListaSeries listaSeries;
    private APITVMaze api;
    private BancoDados bancoDados;
    private Serie serieSelecionada;
    private List<Serie> resultadoBusca;

    private JTextField campoBusca;
    private JButton btnBuscar;
    private JComboBox<String> comboExibir;
    private JComboBox<String> comboOrdenar;

    private DefaultListModel<Serie> modeloLista;
    private JList<Serie> listaSeries_ui;

    private JPanel painelInfo;
    private JLabel lblNome, lblIdioma, lblNota, lblEstado;
    private JLabel lblGeneros, lblEmissora, lblEstreia, lblTermino;
    private JTextArea txtResumo;

    private JButton btnFavoritar, btnAssistida, btnDesejo;

    public Janela(Usuario usuario, ListaSeries listaSeries) {
        this.usuario     = usuario;
        this.listaSeries = listaSeries;
        this.api         = new APITVMaze();
        this.bancoDados  = new BancoDados();

        configurarJanela();
        montarInterface();
        atualizarBotoes();
        setVisible(true);
    }

    private void configurarJanela() {
        setTitle("Séries TV  —  " + usuario.getNome());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(1050, 680);
        setMinimumSize(new Dimension(700, 500));
        setLocationRelativeTo(null);

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                salvarDados();
                System.exit(0);
            }
        });
    }

    private void montarInterface() {
        JPanel root = new JPanel(new BorderLayout(8, 8));
        root.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(root);

        root.add(criarPainelBusca(),   BorderLayout.NORTH);
        root.add(criarPainelCentral(), BorderLayout.CENTER);
        root.add(criarPainelBotoes(),  BorderLayout.SOUTH);
    }

    private JPanel criarPainelBusca() {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 6));
        painel.setBorder(BorderFactory.createTitledBorder("Busca"));

        campoBusca = new JTextField(24);
        campoBusca.addActionListener(e -> buscar());

        btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(e -> buscar());

        comboExibir = new JComboBox<>(new String[]{
            "Resultado da busca", "Favoritos", "Já assistidas", "Desejo assistir"
        });
        comboExibir.addActionListener(e -> exibirListaSelecionada());

        comboOrdenar = new JComboBox<>(new String[]{
            "Alfabético", "Nota", "Estado", "Data de estreia"
        });
        comboOrdenar.addActionListener(e -> exibirListaSelecionada());

        painel.add(new JLabel("Nome:"));
        painel.add(campoBusca);
        painel.add(btnBuscar);
        painel.add(Box.createHorizontalStrut(16));
        painel.add(new JLabel("Exibir:"));
        painel.add(comboExibir);
        painel.add(new JLabel("Ordenar:"));
        painel.add(comboOrdenar);

        return painel;
    }

    private JSplitPane criarPainelCentral() {
        modeloLista    = new DefaultListModel<>();
        listaSeries_ui = new JList<>(modeloLista);
        listaSeries_ui.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        listaSeries_ui.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                serieSelecionada = listaSeries_ui.getSelectedValue();
                mostrarDetalhes();
            }
        });

        JScrollPane scrollLista = new JScrollPane(listaSeries_ui);
        scrollLista.setBorder(BorderFactory.createTitledBorder("Séries"));
        scrollLista.setPreferredSize(new Dimension(230, 0));

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                                          scrollLista, criarPainelDetalhes());
        split.setDividerLocation(230);
        split.setBorder(null);
        return split;
    }

    private JPanel criarPainelDetalhes() {
        JPanel painel = new JPanel(new BorderLayout(0, 10));
        painel.setBorder(BorderFactory.createTitledBorder("Detalhes"));
        painel.setBackground(Color.WHITE);

        painelInfo = new JPanel(new GridBagLayout());
        painelInfo.setBackground(Color.WHITE);
        painelInfo.setBorder(new EmptyBorder(10, 14, 6, 14));
        JPanel info = painelInfo;

        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(3, 0, 3, 0);

        lblNome = new JLabel(" ");
        lblNome.setFont(new Font("SansSerif", Font.BOLD, 20));
        c.gridx = 0; c.gridy = 0; c.gridwidth = 2;
        c.insets = new Insets(4, 0, 14, 0);
        info.add(lblNome, c);

        c.gridwidth = 1;
        c.insets = new Insets(3, 0, 3, 0);

        lblIdioma   = new JLabel("-");
        lblNota     = new JLabel("-");
        lblEstado   = new JLabel("-");
        lblGeneros  = new JLabel("-");
        lblEmissora = new JLabel("-");
        lblEstreia  = new JLabel("-");
        lblTermino  = new JLabel("-");

        String[] rotulos = {"Idioma", "Nota", "Estado", "Gêneros",
                            "Emissora", "Estreia", "Término"};
        JLabel[] valores = {lblIdioma, lblNota, lblEstado, lblGeneros,
                            lblEmissora, lblEstreia, lblTermino};

        for (int i = 0; i < rotulos.length; i++) {
            JLabel rot = new JLabel(rotulos[i] + ":");
            rot.setFont(new Font("SansSerif", Font.PLAIN, 12));
            valores[i].setFont(new Font("SansSerif", Font.PLAIN, 12));

            c.gridx = 0; c.gridy = i + 1;
            c.insets = new Insets(3, 0, 3, 14);
            info.add(rot, c);

            c.gridx = 1;
            c.insets = new Insets(3, 0, 3, 0);
            info.add(valores[i], c);
        }

        txtResumo = new JTextArea();
        txtResumo.setEditable(false);
        txtResumo.setLineWrap(true);
        txtResumo.setWrapStyleWord(true);
        txtResumo.setFont(new Font("SansSerif", Font.PLAIN, 12));
        txtResumo.setBackground(new Color(248, 248, 248));
        txtResumo.setBorder(new EmptyBorder(8, 10, 8, 10));
        txtResumo.setForeground(new Color(60, 60, 60));

        JScrollPane scrollResumo = new JScrollPane(txtResumo);
        scrollResumo.setBorder(BorderFactory.createTitledBorder("Sinopse"));

        painelInfo.setVisible(false);

        painel.add(info,         BorderLayout.NORTH);
        painel.add(scrollResumo, BorderLayout.CENTER);

        return painel;
    }

    private JPanel criarPainelBotoes() {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.CENTER, 14, 8));

        btnFavoritar = new JButton("Favoritar");
        btnFavoritar.setEnabled(false);
        btnFavoritar.addActionListener(e -> toggleFavoritar());

        btnAssistida = new JButton("Já assistida");
        btnAssistida.setEnabled(false);
        btnAssistida.addActionListener(e -> toggleAssistida());

        btnDesejo = new JButton("Desejo assistir");
        btnDesejo.setEnabled(false);
        btnDesejo.addActionListener(e -> toggleDesejo());

        painel.add(btnFavoritar);
        painel.add(btnAssistida);
        painel.add(btnDesejo);

        return painel;
    }

    private void buscar() {
        String nome = campoBusca.getText().trim();
        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite o nome de uma série para buscar.");
            return;
        }

        btnBuscar.setEnabled(false);
        btnBuscar.setText("Buscando...");

        new Thread(() -> {
            try {
                List<Serie> series = api.buscar(nome);
                resultadoBusca = series;

                SwingUtilities.invokeLater(() -> {
                    comboExibir.setSelectedIndex(0);
                    preencherLista(series);
                    btnBuscar.setText("Buscar");
                    btnBuscar.setEnabled(true);
                    if (series.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Nenhuma série encontrada.");
                    }
                });
            } catch (Exception ex) {
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(this, "Erro ao buscar: " + ex.getMessage());
                    btnBuscar.setText("Buscar");
                    btnBuscar.setEnabled(true);
                });
            }
        }).start();
    }

    private void exibirListaSelecionada() {
        String tipo  = (String) comboExibir.getSelectedItem();
        String ordem = (String) comboOrdenar.getSelectedItem();
        List<Serie> series;

        if ("Favoritos".equals(tipo)) {
            series = listaSeries.ordenar(listaSeries.getFavoritos(), ordem);
        } else if ("Já assistidas".equals(tipo)) {
            series = listaSeries.ordenar(listaSeries.getAssistidas(), ordem);
        } else if ("Desejo assistir".equals(tipo)) {
            series = listaSeries.ordenar(listaSeries.getDesejam(), ordem);
        } else {
            if (resultadoBusca != null) {
                series = listaSeries.ordenar(resultadoBusca, ordem);
            } else {
                return;
            }
        }

        preencherLista(series);
    }

    private void preencherLista(List<Serie> series) {
        modeloLista.clear();
        for (Serie s : series) modeloLista.addElement(s);
        serieSelecionada = null;
        limparDetalhes();
        atualizarBotoes();
    }

    private void mostrarDetalhes() {
        if (serieSelecionada == null) {
            limparDetalhes();
            atualizarBotoes();
            return;
        }
        lblNome.setText(serieSelecionada.getNome());
        lblIdioma.setText(serieSelecionada.getIdioma());
        lblNota.setText(serieSelecionada.getNotaFormatada());
        lblEstado.setText(serieSelecionada.getEstado());
        lblGeneros.setText(serieSelecionada.getGenerosFormatado());
        lblEmissora.setText(serieSelecionada.getEmissora());
        lblEstreia.setText(serieSelecionada.getDataEstreiaFormatada());
        lblTermino.setText(serieSelecionada.getDataTerminoFormatada());
        txtResumo.setText(serieSelecionada.getResumo());
        txtResumo.setCaretPosition(0);
        painelInfo.setVisible(true);
        atualizarBotoes();
    }

    private void limparDetalhes() {
        painelInfo.setVisible(false);
        txtResumo.setText("");
    }

    private void atualizarBotoes() {
        if (serieSelecionada == null) {
            btnFavoritar.setEnabled(false);
            btnAssistida.setEnabled(false);
            btnDesejo.setEnabled(false);
            btnFavoritar.setText("Favoritar");
            btnAssistida.setText("Já assistida");
            btnDesejo.setText("Desejo assistir");
            return;
        }
        btnFavoritar.setEnabled(true);
        btnAssistida.setEnabled(true);
        btnDesejo.setEnabled(true);

        btnFavoritar.setText(listaSeries.isFavorito(serieSelecionada)  ? "Remover favorito"  : "Favoritar");
        btnAssistida.setText(listaSeries.isAssistida(serieSelecionada) ? "Remover assistida" : "Já assistida");
        btnDesejo.setText(listaSeries.isDesejo(serieSelecionada)       ? "Remover desejo"    : "Desejo assistir");
    }

    private void toggleFavoritar() {
        if (serieSelecionada == null) return;
        if (listaSeries.isFavorito(serieSelecionada)) listaSeries.removerFavorito(serieSelecionada);
        else                                          listaSeries.adicionarFavorito(serieSelecionada);
        salvarDados();
        atualizarBotoes();
        exibirListaSelecionada();
    }

    private void toggleAssistida() {
        if (serieSelecionada == null) return;
        if (listaSeries.isAssistida(serieSelecionada)) listaSeries.removerAssistida(serieSelecionada);
        else                                           listaSeries.adicionarAssistida(serieSelecionada);
        salvarDados();
        atualizarBotoes();
        exibirListaSelecionada();
    }

    private void toggleDesejo() {
        if (serieSelecionada == null) return;
        if (listaSeries.isDesejo(serieSelecionada)) listaSeries.removerDesejo(serieSelecionada);
        else                                        listaSeries.adicionarDesejo(serieSelecionada);
        salvarDados();
        atualizarBotoes();
        exibirListaSelecionada();
    }

    private void salvarDados() {
        try {
            bancoDados.salvar(usuario, listaSeries);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar dados: " + e.getMessage());
        }
    }
}
