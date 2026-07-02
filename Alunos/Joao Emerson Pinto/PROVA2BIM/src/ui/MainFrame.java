package ui;

import model.Serie;
import model.Usuario;
import persistence.JsonManager;
import service.TVMazeService;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MainFrame extends JFrame {
    private static final Color COR_FUNDO = new Color(241, 244, 248);
    private static final Color COR_SUPERFICIE = Color.WHITE;
    private static final Color COR_PRIMARIA = new Color(34, 92, 173);
    private static final Color COR_PRIMARIA_ESCURA = new Color(24, 67, 128);
    private static final Color COR_SECUNDARIA = new Color(21, 128, 111);
    private static final Color COR_ALERTA = new Color(178, 85, 24);
    private static final Color COR_PERIGO = new Color(180, 54, 54);
    private static final Color COR_TEXTO = new Color(28, 37, 49);
    private static final Color COR_TEXTO_SUAVE = new Color(91, 105, 123);
    private static final Color COR_BORDA = new Color(215, 222, 231);
    private static final Color COR_CABECALHO_TABELA = new Color(230, 236, 244);
    private static final Font FONTE_PADRAO = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Font FONTE_TITULO = new Font("Segoe UI", Font.BOLD, 28);
    private static final Font FONTE_SUBTITULO = new Font("Segoe UI", Font.BOLD, 18);

    private final JsonManager jsonManager;
    private final TVMazeService tvMazeService;

    private Usuario usuario;
    private final SerieTableModel buscaModel = new SerieTableModel();
    private final SerieTableModel favoritosModel = new SerieTableModel();
    private final SerieTableModel assistidasModel = new SerieTableModel();
    private final SerieTableModel desejoModel = new SerieTableModel();

    private JTable buscaTable;
    private JTable favoritosTable;
    private JTable assistidasTable;
    private JTable desejoTable;

    private JTextField campoPesquisa;
    private JComboBox<String> comboOrdenacao;
    private JTabbedPane abas;
    private JTextArea areaDetalhes;
    private JLabel labelStatus;
    private JLabel labelResumo;

    public MainFrame(JsonManager jsonManager) {
        this.jsonManager = jsonManager;
        this.tvMazeService = new TVMazeService();

        configurarJanela();
        carregarOuCriarUsuario();
        montarInterface();
        atualizarTabelasDasListas();
        mostrarBoasVindas();
    }

    private void configurarJanela() {
        setTitle("Acompanhamento de Séries - TVMaze");
        setSize(1180, 740);
        setMinimumSize(new Dimension(960, 620));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                salvarDados();
                dispose();
                System.exit(0);
            }
        });
    }

    private void carregarOuCriarUsuario() {
        try {
            usuario = jsonManager.carregarUsuario();
        } catch (IOException e) {
            mostrarErro("Não foi possível carregar o arquivo JSON. Um novo usuário será criado.");
        }

        if (usuario == null || usuario.getNome() == null || usuario.getNome().isBlank()) {
            solicitarNomeUsuario();
        }
    }

    private void solicitarNomeUsuario() {
        String nome = "";

        while (nome.isBlank()) {
            nome = JOptionPane.showInputDialog(
                    this,
                    "Bem-vindo! Digite seu nome ou apelido:",
                    "Cadastro do Usuário",
                    JOptionPane.QUESTION_MESSAGE
            );

            if (nome == null) {
                nome = "Usuário";
                break;
            }

            nome = nome.trim();
            if (nome.isBlank()) {
                JOptionPane.showMessageDialog(this, "O nome não pode ficar vazio.", "Atenção", JOptionPane.WARNING_MESSAGE);
            }
        }

        usuario = new Usuario(nome);
        salvarDados();
    }

    private void montarInterface() {
        aplicarEstiloGlobal();

        JPanel painelPrincipal = new JPanel(new BorderLayout(16, 16));
        painelPrincipal.setBackground(COR_FUNDO);
        painelPrincipal.setBorder(new EmptyBorder(18, 18, 18, 18));
        setContentPane(painelPrincipal);

        painelPrincipal.add(criarCabecalho(), BorderLayout.NORTH);

        abas = new JTabbedPane();
        abas.setFont(FONTE_PADRAO);
        abas.setBorder(new EmptyBorder(0, 0, 0, 0));
        abas.addTab("Buscar Séries", criarAbaBusca());
        abas.addTab("Favoritos", criarAbaLista(favoritosModel, "Remover dos Favoritos"));
        abas.addTab("Já Assistidas", criarAbaLista(assistidasModel, "Remover das Assistidas"));
        abas.addTab("Desejo Assistir", criarAbaLista(desejoModel, "Remover dos Desejos"));
        abas.addChangeListener(e -> {
            ordenarTabelaAtual();
            atualizarDetalhes(getSerieSelecionadaNaAbaAtual());
        });

        JSplitPane divisao = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, criarCartao(abas), criarPainelDetalhes());
        divisao.setResizeWeight(0.72);
        divisao.setDividerSize(8);
        divisao.setBorder(null);
        divisao.setOpaque(false);
        painelPrincipal.add(divisao, BorderLayout.CENTER);
    }

    private void aplicarEstiloGlobal() {
        UIManager.put("TabbedPane.font", FONTE_PADRAO);
        UIManager.put("Button.font", FONTE_PADRAO);
        UIManager.put("Label.font", FONTE_PADRAO);
        UIManager.put("Table.font", FONTE_PADRAO);
        UIManager.put("TableHeader.font", new Font("Segoe UI", Font.BOLD, 13));
        UIManager.put("TextField.font", FONTE_PADRAO);
        UIManager.put("ComboBox.font", FONTE_PADRAO);
    }

    private JPanel criarCabecalho() {
        JPanel cabecalho = new JPanel(new BorderLayout(16, 10));
        cabecalho.setOpaque(false);

        JPanel textos = new JPanel();
        textos.setOpaque(false);
        textos.setLayout(new BoxLayout(textos, BoxLayout.Y_AXIS));

        JLabel titulo = new JLabel("Minhas Séries");
        titulo.setFont(FONTE_TITULO);
        titulo.setForeground(COR_TEXTO);
        titulo.setAlignmentX(Component.LEFT_ALIGNMENT);

        labelStatus = new JLabel("Olá, " + usuario.getNome() + "! Organize suas séries favoritas em um só lugar.");
        labelStatus.setFont(FONTE_PADRAO);
        labelStatus.setForeground(COR_TEXTO_SUAVE);
        labelStatus.setAlignmentX(Component.LEFT_ALIGNMENT);

        textos.add(titulo);
        textos.add(Box.createVerticalStrut(4));
        textos.add(labelStatus);

        JPanel ladoDireito = new JPanel(new GridBagLayout());
        ladoDireito.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 8, 0, 0);
        gbc.gridy = 0;

        labelResumo = criarChipResumo();
        ladoDireito.add(labelResumo, gbc);

        gbc.gridx = 1;
        ladoDireito.add(new JLabel("Ordenar por:"), gbc);

        comboOrdenacao = new JComboBox<>(new String[]{
                "Nome (A-Z)",
                "Nota Geral",
                "Estado da Série",
                "Data de Estreia"
        });
        comboOrdenacao.setPreferredSize(new Dimension(170, 34));
        comboOrdenacao.addActionListener(e -> ordenarTabelaAtual());
        gbc.gridx = 2;
        ladoDireito.add(comboOrdenacao, gbc);

        cabecalho.add(textos, BorderLayout.WEST);
        cabecalho.add(ladoDireito, BorderLayout.EAST);
        return cabecalho;
    }

    private JLabel criarChipResumo() {
        JLabel chip = new JLabel();
        chip.setOpaque(true);
        chip.setBackground(new Color(225, 238, 235));
        chip.setForeground(new Color(19, 96, 84));
        chip.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(190, 218, 211)),
                new EmptyBorder(7, 10, 7, 10)
        ));
        return chip;
    }

    private JPanel criarAbaBusca() {
        JPanel painel = criarPainelConteudo();

        JPanel painelPesquisa = new JPanel(new BorderLayout(10, 0));
        painelPesquisa.setOpaque(false);

        campoPesquisa = new JTextField();
        campoPesquisa.setToolTipText("Digite o nome de uma série");
        campoPesquisa.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COR_BORDA),
                new EmptyBorder(9, 10, 9, 10)
        ));
        campoPesquisa.addActionListener(e -> pesquisarSeries());
        painelPesquisa.add(campoPesquisa, BorderLayout.CENTER);

        JButton botaoPesquisar = criarBotaoPrimario("Pesquisar");
        botaoPesquisar.addActionListener(e -> pesquisarSeries());
        painelPesquisa.add(botaoPesquisar, BorderLayout.EAST);

        buscaTable = criarTabela(buscaModel);
        buscaTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                atualizarDetalhes(getSerieSelecionada(buscaTable, buscaModel));
            }
        });

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        painelBotoes.setOpaque(false);
        JButton botaoFavorito = criarBotaoSecundario("+ Favoritos", COR_PRIMARIA);
        JButton botaoAssistida = criarBotaoSecundario("+ Assistidas", COR_SECUNDARIA);
        JButton botaoDesejo = criarBotaoSecundario("+ Desejo", COR_ALERTA);

        botaoFavorito.addActionListener(e -> adicionarSerieNaLista(usuario.getFavoritos(), "favoritos"));
        botaoAssistida.addActionListener(e -> adicionarSerieNaLista(usuario.getAssistidas(), "já assistidas"));
        botaoDesejo.addActionListener(e -> adicionarSerieNaLista(usuario.getDesejoAssistir(), "desejo assistir"));

        painelBotoes.add(botaoFavorito);
        painelBotoes.add(botaoAssistida);
        painelBotoes.add(botaoDesejo);

        painel.add(painelPesquisa, BorderLayout.NORTH);
        painel.add(criarScrollTabela(buscaTable), BorderLayout.CENTER);
        painel.add(painelBotoes, BorderLayout.SOUTH);
        return painel;
    }

    private JPanel criarAbaLista(SerieTableModel model, String textoBotaoRemover) {
        JPanel painel = criarPainelConteudo();

        JTable tabela = criarTabela(model);
        if (model == favoritosModel) {
            favoritosTable = tabela;
        } else if (model == assistidasModel) {
            assistidasTable = tabela;
        } else {
            desejoTable = tabela;
        }

        tabela.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                atualizarDetalhes(getSerieSelecionada(tabela, model));
            }
        });

        JButton botaoRemover = criarBotaoSecundario(textoBotaoRemover, COR_PERIGO);
        botaoRemover.addActionListener(e -> removerSerieDaListaAtual());

        JPanel rodape = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        rodape.setOpaque(false);
        rodape.add(botaoRemover);

        painel.add(criarScrollTabela(tabela), BorderLayout.CENTER);
        painel.add(rodape, BorderLayout.SOUTH);
        return painel;
    }

    private JPanel criarPainelConteudo() {
        JPanel painel = new JPanel(new BorderLayout(12, 12));
        painel.setBackground(COR_SUPERFICIE);
        painel.setBorder(new EmptyBorder(14, 14, 14, 14));
        return painel;
    }

    private JPanel criarPainelDetalhes() {
        JPanel painel = criarCartao(new JPanel(new BorderLayout(10, 10)));
        painel.setPreferredSize(new Dimension(320, 100));

        JPanel conteudo = (JPanel) painel.getComponent(0);
        conteudo.setBackground(COR_SUPERFICIE);
        conteudo.setBorder(new EmptyBorder(16, 16, 16, 16));

        JLabel titulo = new JLabel("Detalhes da Série");
        titulo.setFont(FONTE_SUBTITULO);
        titulo.setForeground(COR_TEXTO);

        JLabel dica = new JLabel("Selecione uma linha para ver informações completas.");
        dica.setForeground(COR_TEXTO_SUAVE);

        JPanel topo = new JPanel();
        topo.setOpaque(false);
        topo.setLayout(new BoxLayout(topo, BoxLayout.Y_AXIS));
        titulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        dica.setAlignmentX(Component.LEFT_ALIGNMENT);
        topo.add(titulo);
        topo.add(Box.createVerticalStrut(4));
        topo.add(dica);

        areaDetalhes = new JTextArea(12, 20);
        areaDetalhes.setEditable(false);
        areaDetalhes.setFont(FONTE_PADRAO);
        areaDetalhes.setForeground(COR_TEXTO);
        areaDetalhes.setBackground(COR_SUPERFICIE);
        areaDetalhes.setLineWrap(true);
        areaDetalhes.setWrapStyleWord(true);
        areaDetalhes.setBorder(new EmptyBorder(8, 0, 0, 0));
        areaDetalhes.setText("Nenhuma série selecionada.");

        conteudo.add(topo, BorderLayout.NORTH);
        conteudo.add(areaDetalhes, BorderLayout.CENTER);
        return painel;
    }

    private JPanel criarCartao(Component conteudo) {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBackground(COR_SUPERFICIE);
        painel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COR_BORDA),
                new EmptyBorder(0, 0, 0, 0)
        ));
        painel.add(conteudo, BorderLayout.CENTER);
        return painel;
    }

    private JScrollPane criarScrollTabela(JTable tabela) {
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBorder(BorderFactory.createLineBorder(COR_BORDA));
        scroll.getViewport().setBackground(COR_SUPERFICIE);
        return scroll;
    }

    private JTable criarTabela(SerieTableModel model) {
        JTable tabela = new JTable(model);
        tabela.setRowHeight(34);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.setShowGrid(false);
        tabela.setIntercellSpacing(new Dimension(0, 0));
        tabela.setFillsViewportHeight(true);
        tabela.setForeground(COR_TEXTO);
        tabela.setBackground(COR_SUPERFICIE);
        tabela.setSelectionBackground(new Color(218, 233, 255));
        tabela.setSelectionForeground(COR_TEXTO);
        tabela.setAutoCreateRowSorter(false);

        tabela.getColumnModel().getColumn(0).setPreferredWidth(210);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(180);
        tabela.getColumnModel().getColumn(3).setPreferredWidth(70);
        tabela.getColumnModel().getColumn(5).setPreferredWidth(90);

        JTableHeader header = tabela.getTableHeader();
        header.setBackground(COR_CABECALHO_TABELA);
        header.setForeground(COR_TEXTO);
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 36));
        header.setReorderingAllowed(false);

        return tabela;
    }

    private JButton criarBotaoPrimario(String texto) {
        return criarBotao(texto, COR_PRIMARIA, COR_PRIMARIA_ESCURA, Color.WHITE);
    }

    private JButton criarBotaoSecundario(String texto, Color cor) {
        return criarBotao(texto, misturarComBranco(cor, 0.88), cor, COR_TEXTO);
    }

    private JButton criarBotao(String texto, Color fundo, Color borda, Color textoCor) {
        JButton botao = new JButton(texto);
        botao.setFocusPainted(false);
        botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botao.setBackground(fundo);
        botao.setForeground(textoCor);
        botao.setOpaque(true);
        botao.setContentAreaFilled(true);
        botao.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(borda),
                new EmptyBorder(8, 13, 8, 13)
        ));
        return botao;
    }

    private Color misturarComBranco(Color cor, double pesoBranco) {
        int vermelho = (int) Math.round(cor.getRed() * (1 - pesoBranco) + 255 * pesoBranco);
        int verde = (int) Math.round(cor.getGreen() * (1 - pesoBranco) + 255 * pesoBranco);
        int azul = (int) Math.round(cor.getBlue() * (1 - pesoBranco) + 255 * pesoBranco);
        return new Color(vermelho, verde, azul);
    }

    private void pesquisarSeries() {
        String texto = campoPesquisa.getText().trim();
        if (texto.isBlank()) {
            JOptionPane.showMessageDialog(this, "Digite o nome de uma série para pesquisar.", "Campo vazio", JOptionPane.WARNING_MESSAGE);
            return;
        }

        labelStatus.setText("Pesquisando séries...");
        campoPesquisa.setEnabled(false);

        SwingWorker<List<Serie>, Void> worker = new SwingWorker<>() {
            @Override
            protected List<Serie> doInBackground() throws Exception {
                return tvMazeService.buscarSeries(texto);
            }

            @Override
            protected void done() {
                campoPesquisa.setEnabled(true);

                try {
                    List<Serie> series = get();
                    buscaModel.setSeries(series);
                    ordenarTabelaAtual();

                    if (series.isEmpty()) {
                        labelStatus.setText("Nenhuma série encontrada.");
                        JOptionPane.showMessageDialog(MainFrame.this, "Nenhuma série foi encontrada.", "Resultado", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        labelStatus.setText(series.size() + " resultado(s) encontrado(s).");
                    }
                } catch (Exception e) {
                    labelStatus.setText("Falha na pesquisa.");
                    mostrarErro("Não foi possível conectar à API TVMaze. Verifique sua internet e tente novamente.");
                }
            }
        };

        worker.execute();
    }

    private void adicionarSerieNaLista(List<Serie> lista, String nomeLista) {
        Serie serie = getSerieSelecionada(buscaTable, buscaModel);

        if (serie == null) {
            JOptionPane.showMessageDialog(this, "Selecione uma série nos resultados da busca.", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (lista.contains(serie)) {
            JOptionPane.showMessageDialog(this, "Esta série já está na lista de " + nomeLista + ".", "Série duplicada", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        lista.add(serie);
        atualizarTabelasDasListas();
        salvarDados();
        JOptionPane.showMessageDialog(this, "Série adicionada à lista de " + nomeLista + ".", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    private void removerSerieDaListaAtual() {
        int indice = abas.getSelectedIndex();
        Serie serie = getSerieSelecionadaNaAbaAtual();

        if (serie == null) {
            JOptionPane.showMessageDialog(this, "Selecione uma série para remover.", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (indice == 1) {
            usuario.getFavoritos().remove(serie);
        } else if (indice == 2) {
            usuario.getAssistidas().remove(serie);
        } else if (indice == 3) {
            usuario.getDesejoAssistir().remove(serie);
        } else {
            return;
        }

        atualizarTabelasDasListas();
        atualizarDetalhes(null);
        salvarDados();
        JOptionPane.showMessageDialog(this, "Série removida com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    private void atualizarTabelasDasListas() {
        favoritosModel.setSeries(usuario.getFavoritos());
        assistidasModel.setSeries(usuario.getAssistidas());
        desejoModel.setSeries(usuario.getDesejoAssistir());
        atualizarResumo();
        ordenarTabelaAtual();
    }

    private void atualizarResumo() {
        if (labelResumo == null || usuario == null) {
            return;
        }

        labelResumo.setText(usuario.getFavoritos().size() + " favoritos  |  "
                + usuario.getAssistidas().size() + " assistidas  |  "
                + usuario.getDesejoAssistir().size() + " desejos");
    }

    private Serie getSerieSelecionadaNaAbaAtual() {
        int indice = abas.getSelectedIndex();

        if (indice == 0) {
            return getSerieSelecionada(buscaTable, buscaModel);
        }
        if (indice == 1) {
            return getSerieSelecionada(favoritosTable, favoritosModel);
        }
        if (indice == 2) {
            return getSerieSelecionada(assistidasTable, assistidasModel);
        }
        if (indice == 3) {
            return getSerieSelecionada(desejoTable, desejoModel);
        }

        return null;
    }

    private Serie getSerieSelecionada(JTable tabela, SerieTableModel model) {
        if (tabela == null || tabela.getSelectedRow() < 0) {
            return null;
        }

        int linha = tabela.convertRowIndexToModel(tabela.getSelectedRow());
        return model.getSerie(linha);
    }

    private void atualizarDetalhes(Serie serie) {
        if (serie == null) {
            areaDetalhes.setText("Nenhuma série selecionada.");
            return;
        }

        String detalhes = "Nome\n" + serie.getNome()
                + "\n\nIdioma\n" + serie.getIdioma()
                + "\n\nGêneros\n" + serie.getGenerosFormatados()
                + "\n\nNota geral\n" + serie.getNotaFormatada()
                + "\n\nEstado\n" + serie.getEstado()
                + "\n\nEstreia\n" + serie.getEstreia()
                + "\n\nTérmino\n" + serie.getTermino()
                + "\n\nEmissora\n" + serie.getEmissora();

        areaDetalhes.setText(detalhes);
        areaDetalhes.setCaretPosition(0);
    }

    private void ordenarTabelaAtual() {
        if (comboOrdenacao == null || abas == null) {
            return;
        }

        SerieTableModel model = getModeloDaAbaAtual();
        if (model == null) {
            return;
        }

        model.ordenar(getComparadorSelecionado());
    }

    private SerieTableModel getModeloDaAbaAtual() {
        int indice = abas.getSelectedIndex();

        if (indice == 0) {
            return buscaModel;
        }
        if (indice == 1) {
            return favoritosModel;
        }
        if (indice == 2) {
            return assistidasModel;
        }
        if (indice == 3) {
            return desejoModel;
        }

        return null;
    }

    private Comparator<Serie> getComparadorSelecionado() {
        String opcao = (String) comboOrdenacao.getSelectedItem();

        if ("Nota Geral".equals(opcao)) {
            return Comparator.comparingDouble(Serie::getNota).reversed()
                    .thenComparing(Serie::getNome, String.CASE_INSENSITIVE_ORDER);
        }

        if ("Estado da Série".equals(opcao)) {
            return Comparator.comparing(Serie::getEstado, String.CASE_INSENSITIVE_ORDER)
                    .thenComparing(Serie::getNome, String.CASE_INSENSITIVE_ORDER);
        }

        if ("Data de Estreia".equals(opcao)) {
            return Comparator.comparing(Serie::getEstreia, String.CASE_INSENSITIVE_ORDER)
                    .thenComparing(Serie::getNome, String.CASE_INSENSITIVE_ORDER);
        }

        return Comparator.comparing(Serie::getNome, String.CASE_INSENSITIVE_ORDER);
    }

    private void mostrarBoasVindas() {
        JOptionPane.showMessageDialog(
                this,
                "Bem-vindo(a), " + usuario.getNome() + "!",
                "Olá",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void salvarDados() {
        try {
            jsonManager.salvarUsuario(usuario);
        } catch (IOException e) {
            mostrarErro("Não foi possível salvar os dados em JSON.");
        } catch (Exception e) {
            mostrarErro("Ocorreu um erro inesperado ao salvar os dados.");
        }
    }

    private void mostrarErro(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    private static class SerieTableModel extends AbstractTableModel {
        private final String[] colunas = {"Nome", "Idioma", "Gêneros", "Nota", "Estado", "Estreia", "Emissora"};
        private final List<Serie> series = new ArrayList<>();

        public void setSeries(List<Serie> novasSeries) {
            series.clear();
            if (novasSeries != null) {
                series.addAll(novasSeries);
            }
            fireTableDataChanged();
        }

        public Serie getSerie(int linha) {
            if (linha < 0 || linha >= series.size()) {
                return null;
            }
            return series.get(linha);
        }

        public void ordenar(Comparator<Serie> comparator) {
            series.sort(comparator);
            fireTableDataChanged();
        }

        @Override
        public int getRowCount() {
            return series.size();
        }

        @Override
        public int getColumnCount() {
            return colunas.length;
        }

        @Override
        public String getColumnName(int column) {
            return colunas[column];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Serie serie = series.get(rowIndex);

            return switch (columnIndex) {
                case 0 -> serie.getNome();
                case 1 -> serie.getIdioma();
                case 2 -> serie.getGenerosFormatados();
                case 3 -> serie.getNotaFormatada();
                case 4 -> serie.getEstado();
                case 5 -> serie.getEstreia();
                case 6 -> serie.getEmissora();
                default -> "";
            };
        }
    }
}
