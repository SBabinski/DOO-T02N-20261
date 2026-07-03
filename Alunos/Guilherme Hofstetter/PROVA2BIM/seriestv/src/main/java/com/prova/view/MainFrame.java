package com.prova.view;

import com.prova.controller.SerieController;
import com.prova.model.Serie;
import com.prova.repository.JsonRepository;

import javax.swing.BorderFactory;
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
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {

    private static final Color COR_BUSCAR = Color.decode("#4F7CAC");
    private static final Color COR_FAVORITOS = Color.decode("#F39C12");
    private static final Color COR_ASSISTIDAS = Color.decode("#2E7D32");
    private static final Color COR_DESEJO_ASSISTIR = Color.decode("#4FC3F7");
    private static final Color COR_TEXTO_ESCURO = Color.decode("#1F2933");

    private SerieController controller;

    private JTextField campoBusca;
    private JButton botaoBuscar;
    private JButton botaoFavorito;
    private JButton botaoAssistida;
    private JButton botaoDesejoAssistir;

    private JTable tabelaResultados;
    private DefaultTableModel modeloResultados;

    private JTabbedPane abasListas;

    private JTable tabelaFavoritos;
    private JTable tabelaAssistidas;
    private JTable tabelaDesejoAssistir;

    private DefaultTableModel modeloFavoritos;
    private DefaultTableModel modeloAssistidas;
    private DefaultTableModel modeloDesejoAssistir;

    private JComboBox<String> comboOrdenacao;
    private JButton botaoRemover;

    private List<Serie> resultadosBusca = new ArrayList<>();

    public MainFrame() {
        try {
            String nomeUsuarioInicial = solicitarNomePrimeiroAcesso();
            controller = new SerieController(nomeUsuarioInicial);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Erro ao iniciar o sistema: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        configurarJanela();
        criarComponentes();
        montarLayout();
        configurarEventos();
        atualizarTabelasListas();
        atualizarCorBotaoRemover();

        setVisible(true);
    }

    private String solicitarNomePrimeiroAcesso() {
        JsonRepository repository = new JsonRepository();

        if (repository.existeArquivo()) {
            return null;
        }

        String nome = JOptionPane.showInputDialog(
                null,
                "Digite seu nome:",
                "Primeiro acesso",
                JOptionPane.QUESTION_MESSAGE);

        if (nome == null || nome.trim().isEmpty()) {
            return "Usuário";
        }

        return nome.trim();
    }

    private void configurarJanela() {
        setTitle("Parece a Netflix, mas é diferente");
        setSize(1100, 750);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                confirmarFechamento();
            }
        });
    }

    private void confirmarFechamento() {
        int resposta = JOptionPane.showConfirmDialog(
                this,
                "Deseja realmente fechar o sistema?",
                "Confirmar saída",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (resposta == JOptionPane.YES_OPTION) {
            dispose();
            System.exit(0);
        }
    }

    private void criarComponentes() {
        campoBusca = new JTextField(30);

        botaoBuscar = new JButton("Buscar");
        botaoFavorito = new JButton("Adicionar aos favoritos");
        botaoAssistida = new JButton("Adicionar às assistidas");
        botaoDesejoAssistir = new JButton("Quero assistir");

        estilizarBotao(botaoBuscar, COR_BUSCAR, Color.WHITE);
        estilizarBotao(botaoFavorito, COR_FAVORITOS, Color.WHITE);
        estilizarBotao(botaoAssistida, COR_ASSISTIDAS, Color.WHITE);
        estilizarBotao(botaoDesejoAssistir, COR_DESEJO_ASSISTIR, COR_TEXTO_ESCURO);

        modeloResultados = criarModeloTabela();
        modeloFavoritos = criarModeloTabela();
        modeloAssistidas = criarModeloTabela();
        modeloDesejoAssistir = criarModeloTabela();

        tabelaResultados = new JTable(modeloResultados);
        tabelaFavoritos = new JTable(modeloFavoritos);
        tabelaAssistidas = new JTable(modeloAssistidas);
        tabelaDesejoAssistir = new JTable(modeloDesejoAssistir);

        tabelaResultados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaFavoritos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaAssistidas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaDesejoAssistir.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        abasListas = new JTabbedPane();

        abasListas.addTab("Favoritos", new JScrollPane(tabelaFavoritos));
        abasListas.addTab("Assistidas", new JScrollPane(tabelaAssistidas));
        abasListas.addTab("Desejo assistir", new JScrollPane(tabelaDesejoAssistir));

        abasListas.setBackgroundAt(0, COR_FAVORITOS);
        abasListas.setForegroundAt(0, Color.WHITE);

        abasListas.setBackgroundAt(1, COR_ASSISTIDAS);
        abasListas.setForegroundAt(1, Color.WHITE);

        abasListas.setBackgroundAt(2, COR_DESEJO_ASSISTIR);
        abasListas.setForegroundAt(2, COR_TEXTO_ESCURO);

        comboOrdenacao = new JComboBox<>(new String[] { "Nome", "Nota", "Estado", "Estreia" });

        botaoRemover = new JButton("Remover da lista");
    }

    private DefaultTableModel criarModeloTabela() {
        return new DefaultTableModel(
                new Object[] {
                        "Nome",
                        "Idioma",
                        "Gêneros",
                        "Nota",
                        "Estado",
                        "Estreia",
                        "Término",
                        "Emissora"
                },
                0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    private void montarLayout() {
        JLabel labelUsuario = new JLabel("Usuário: " + controller.getNomeUsuario());
        labelUsuario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel painelBusca = new JPanel();
        painelBusca.add(new JLabel("Nome da série:"));
        painelBusca.add(campoBusca);
        painelBusca.add(botaoBuscar);

        JPanel painelTopo = new JPanel(new BorderLayout());
        painelTopo.add(labelUsuario, BorderLayout.NORTH);
        painelTopo.add(painelBusca, BorderLayout.SOUTH);

        JPanel painelBotoesBusca = new JPanel();
        painelBotoesBusca.add(botaoFavorito);
        painelBotoesBusca.add(botaoAssistida);
        painelBotoesBusca.add(botaoDesejoAssistir);

        JPanel painelResultados = new JPanel(new BorderLayout());
        painelResultados.setBorder(BorderFactory.createTitledBorder("Busca de séries"));
        painelResultados.add(new JScrollPane(tabelaResultados), BorderLayout.CENTER);
        painelResultados.add(painelBotoesBusca, BorderLayout.SOUTH);

        JPanel painelOrdenacao = new JPanel();
        painelOrdenacao.add(new JLabel("Ordenar por:"));
        painelOrdenacao.add(comboOrdenacao);
        painelOrdenacao.add(botaoRemover);

        JPanel painelListas = new JPanel(new BorderLayout());
        painelListas.setBorder(BorderFactory.createTitledBorder("Minhas listas"));
        painelListas.add(painelOrdenacao, BorderLayout.NORTH);
        painelListas.add(abasListas, BorderLayout.CENTER);

        JSplitPane divisor = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT,
                painelResultados,
                painelListas);

        divisor.setResizeWeight(0.55);

        setLayout(new BorderLayout());
        add(painelTopo, BorderLayout.NORTH);
        add(divisor, BorderLayout.CENTER);
    }

    private void configurarEventos() {
        botaoBuscar.addActionListener(e -> buscarSeries());

        botaoFavorito.addActionListener(e -> adicionarFavorito());

        botaoAssistida.addActionListener(e -> adicionarAssistida());

        botaoDesejoAssistir.addActionListener(e -> adicionarDesejoAssistir());

        comboOrdenacao.addActionListener(e -> atualizarTabelasListas());

        abasListas.addChangeListener(e -> {
            atualizarTabelasListas();
            atualizarCorBotaoRemover();
        });

        botaoRemover.addActionListener(e -> removerSerieDaListaAtual());
    }

    private void buscarSeries() {
        String nome = campoBusca.getText();

        if (nome == null || nome.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite o nome de uma série.");
            return;
        }

        try {
            resultadosBusca = controller.buscarSeriesPorNome(nome.trim());
            preencherTabela(modeloResultados, resultadosBusca);

            if (resultadosBusca.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nenhuma série encontrada.");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao buscar séries: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void preencherTabela(DefaultTableModel modelo, List<Serie> series) {
        modelo.setRowCount(0);

        for (Serie serie : series) {
            modelo.addRow(new Object[] {
                    serie.getNome(),
                    serie.getIdioma(),
                    String.join(", ", serie.getGeneros()),
                    serie.getNota() != null ? serie.getNota() : "Não informado",
                    serie.getEstado(),
                    serie.getDataEstreia(),
                    serie.getDataTermino(),
                    serie.getEmissora()
            });
        }
    }

    private Serie getSerieSelecionadaBusca() {
        int linhaSelecionada = tabelaResultados.getSelectedRow();

        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma série na tabela de resultados.");
            return null;
        }

        return resultadosBusca.get(linhaSelecionada);
    }

    private void adicionarFavorito() {
        Serie serie = getSerieSelecionadaBusca();

        if (serie == null) {
            return;
        }

        try {
            boolean adicionou = controller.adicionarFavorito(serie);

            if (adicionou) {
                JOptionPane.showMessageDialog(this, "Série adicionada aos favoritos.");
            } else {
                JOptionPane.showMessageDialog(this, "Essa série já está nos favoritos.");
            }

            atualizarTabelasListas();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao salvar favorito: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void adicionarAssistida() {
        Serie serie = getSerieSelecionadaBusca();

        if (serie == null) {
            return;
        }

        try {
            boolean adicionou = controller.adicionarAssistida(serie);

            if (adicionou) {
                JOptionPane.showMessageDialog(this, "Série adicionada às assistidas.");
            } else {
                JOptionPane.showMessageDialog(this, "Essa série já está nas assistidas.");
            }

            atualizarTabelasListas();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao salvar série assistida: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void adicionarDesejoAssistir() {
        Serie serie = getSerieSelecionadaBusca();

        if (serie == null) {
            return;
        }

        try {
            boolean adicionou = controller.adicionarDesejoAssistir(serie);

            if (adicionou) {
                JOptionPane.showMessageDialog(this, "Série adicionada à lista de desejo assistir.");
            } else {
                JOptionPane.showMessageDialog(this, "Essa série já está na lista de desejo assistir.");
            }

            atualizarTabelasListas();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao salvar série: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarTabelasListas() {
        String criterio = (String) comboOrdenacao.getSelectedItem();

        preencherTabela(modeloFavoritos, controller.listarFavoritosOrdenados(criterio));
        preencherTabela(modeloAssistidas, controller.listarAssistidasOrdenadas(criterio));
        preencherTabela(modeloDesejoAssistir, controller.listarDesejoAssistirOrdenadas(criterio));
    }

    private void removerSerieDaListaAtual() {
        int abaSelecionada = abasListas.getSelectedIndex();
        String criterio = (String) comboOrdenacao.getSelectedItem();

        JTable tabelaAtual;
        List<Serie> listaAtual;

        if (abaSelecionada == 0) {
            tabelaAtual = tabelaFavoritos;
            listaAtual = controller.listarFavoritosOrdenados(criterio);
        } else if (abaSelecionada == 1) {
            tabelaAtual = tabelaAssistidas;
            listaAtual = controller.listarAssistidasOrdenadas(criterio);
        } else {
            tabelaAtual = tabelaDesejoAssistir;
            listaAtual = controller.listarDesejoAssistirOrdenadas(criterio);
        }

        int linhaSelecionada = tabelaAtual.getSelectedRow();

        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma série para remover.");
            return;
        }

        Serie serie = listaAtual.get(linhaSelecionada);

        int confirmacao = JOptionPane.showConfirmDialog(
                this,
                "Deseja remover \"" + serie.getNome() + "\" desta lista?",
                "Confirmar remoção",
                JOptionPane.YES_NO_OPTION);

        if (confirmacao != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            boolean removeu;

            if (abaSelecionada == 0) {
                removeu = controller.removerFavorito(serie);
            } else if (abaSelecionada == 1) {
                removeu = controller.removerAssistida(serie);
            } else {
                removeu = controller.removerDesejoAssistir(serie);
            }

            if (removeu) {
                JOptionPane.showMessageDialog(this, "Série removida com sucesso.");
            } else {
                JOptionPane.showMessageDialog(this, "Não foi possível remover a série.");
            }

            atualizarTabelasListas();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao remover série: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarCorBotaoRemover() {
        int abaSelecionada = abasListas.getSelectedIndex();

        if (abaSelecionada == 0) {
            estilizarBotao(botaoRemover, COR_FAVORITOS, Color.WHITE);
        } else if (abaSelecionada == 1) {
            estilizarBotao(botaoRemover, COR_ASSISTIDAS, Color.WHITE);
        } else {
            estilizarBotao(botaoRemover, COR_DESEJO_ASSISTIR, COR_TEXTO_ESCURO);
        }
    }

    private void estilizarBotao(JButton botao, Color corFundo, Color corTexto) {
        botao.setBackground(corFundo);
        botao.setForeground(corTexto);
        botao.setFocusPainted(false);
        botao.setOpaque(true);
        botao.setContentAreaFilled(true);
        botao.setBorderPainted(false);
        botao.setBorder(BorderFactory.createEmptyBorder(8, 14, 8, 14));
    }
}