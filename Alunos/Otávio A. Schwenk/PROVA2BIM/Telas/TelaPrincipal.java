package Telas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.JSplitPane;

import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Objetos.Serie;
import Objetos.Usuario;
import Service.SerieService;
import Service.UsuarioService;

public class TelaPrincipal {

    private Usuario usuario;
    private SerieService service = new SerieService();
    private UsuarioService usuarioService = new UsuarioService();

    private List<Serie> cacheSeries = new ArrayList<>();
    private List<Serie> listaFavoritos = new ArrayList<>();
    private List<Serie> listaAssistidas = new ArrayList<>();
    private List<Serie> listaDesejo = new ArrayList<>();

    Color roxoUm = new Color(198, 127, 245);
    Color roxoDois = new Color(177, 85, 237);
    Color roxoTres = new Color(169, 84, 222);
    Color roxoFundoEscuro = new Color(31, 4, 48);
    Color vermelhoPrincipal = new Color(212, 78, 134);
    Color verdePrincipal = new Color(99, 224, 31);

    JFrame telaPrincipal = new JFrame("HermitNine");

    private JPanel fundoPrincipal = new JPanel(new BorderLayout());

    private JLabel boasVindas = new JLabel();

    private JTextField campoPesquisa = new JTextField();
    private JButton buscar = new JButton("Buscar");

    private JPanel painelTop = new JPanel(new BorderLayout());
    private JPanel resultados = new JPanel(new BorderLayout());
    private JPanel painelAcoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

    private JTabbedPane tabs = new JTabbedPane();

    private JPanel favoritos = new JPanel(new BorderLayout());
    private JPanel assistidas = new JPanel(new BorderLayout());
    private JPanel queroAssistir = new JPanel(new BorderLayout());

    private JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

    private DefaultTableModel modeloSeries;
    private JTable tabelaSeries;

    private DefaultTableModel modeloFavoritos;
    private JTable tabelaFavoritos;

    private DefaultTableModel modeloAssistidas;
    private JTable tabelaAssistidas;

    private DefaultTableModel modeloDesejo;
    private JTable tabelaDesejo;
    private JComboBox<String> comboFiltro;

    private JButton botaoFavoritos = new JButton("Favoritos");
    private JButton botaoAssistidos = new JButton("Assistidas");
    private JButton botaoDesejoAssistir = new JButton("Desejo assistir");
    private JButton botaoRemover = new JButton("Remover");
    private JButton botaoSalvar = new JButton("Salvar");

    public static final Comparator<Serie> COMPARADOR_ESTADO = new Comparator<Serie>() {
        @Override
        public int compare(Serie s1, Serie s2) {

            String e1 = s1.getEstado() != null ? s1.getEstado() : "";
            String e2 = s2.getEstado() != null ? s2.getEstado() : "";

            if (e1.equalsIgnoreCase("Ainda transmitindo") && !e2.equalsIgnoreCase("Ainda transmitindo")) return -1;
            if (!e1.equalsIgnoreCase("Ainda transmitindo") && e2.equalsIgnoreCase("Ainda transmitindo")) return 1;

            return e1.compareToIgnoreCase(e2);
        }
    };

    public TelaPrincipal(Usuario usuario) {
        this.usuario = usuario;

        carregarDadosUsuario();

        criarTelaPrincipal();
        configurarBotoes();
        configurarTabelas();

        atualizarTabela(modeloFavoritos, listaFavoritos);
        atualizarTabela(modeloAssistidas, listaAssistidas);
        atualizarTabela(modeloDesejo, listaDesejo);
    }

    public void criarTelaPrincipal() {

        telaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaPrincipal.setExtendedState(JFrame.MAXIMIZED_BOTH);

        boasVindas.setText("Olá, " + usuario.getApelido());
        boasVindas.setFont(new Font("Arial Black", Font.BOLD, 32));
        boasVindas.setForeground(Color.WHITE);

        fundoPrincipal.setLayout(new BorderLayout());
        fundoPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        fundoPrincipal.add(boasVindas, BorderLayout.NORTH);
        fundoPrincipal.setBackground(roxoFundoEscuro);

        campoPesquisa.setFont(new Font("Arial", Font.PLAIN, 16));
        campoPesquisa.setPreferredSize(new Dimension(300, 35));

        buscar.setFont(new Font("Arial Black", Font.BOLD, 14));
        buscar.setBackground(new Color(101, 21, 140));
        buscar.setForeground(Color.WHITE);
        buscar.setFocusPainted(false);

        JPanel buscaContainer = new JPanel(new BorderLayout(10, 10));
        buscaContainer.add(campoPesquisa, BorderLayout.CENTER);
        buscaContainer.add(buscar, BorderLayout.EAST);

        String[] colunas = {
                "Nome", "Idioma", "Gêneros", "Nota",
                "Estado", "Estreia", "Término", "Emissora"
        };

        String[] filtros = {
                "Filtro",
                "Nome (A-Z)",
                "Nota geral",
                "Estado",
                "Data de estreia"
        };

        comboFiltro = new JComboBox<>(filtros);
        comboFiltro.setFont(new Font("Arial", Font.PLAIN, 14));
        comboFiltro.setPreferredSize(new Dimension(150, 25));
        comboFiltro.setFocusable(false);

        modeloSeries = new DefaultTableModel(colunas, 0);
        tabelaSeries = new JTable(modeloSeries);

        JScrollPane scrollSeries = new JScrollPane(tabelaSeries);

        botaoFavoritos.setBackground(roxoUm);
        botaoAssistidos.setBackground(roxoDois);
        botaoDesejoAssistir.setBackground(roxoTres);
        botaoRemover.setBackground(vermelhoPrincipal);
        botaoSalvar.setBackground(verdePrincipal);

        painelAcoes.add(botaoFavoritos);
        painelAcoes.add(botaoAssistidos);
        painelAcoes.add(botaoDesejoAssistir);
        painelAcoes.add(botaoRemover);
        painelAcoes.add(botaoSalvar);
        painelAcoes.add(comboFiltro);

        resultados.add(buscaContainer, BorderLayout.NORTH);
        resultados.add(scrollSeries, BorderLayout.CENTER);
        resultados.add(painelAcoes, BorderLayout.SOUTH);

        painelTop.setLayout(new BorderLayout());
        painelTop.add(resultados, BorderLayout.CENTER);

        criarTabelasTabs();

        tabs.setFont(new Font("Arial", Font.BOLD, 14));

        tabs.addTab("Favoritos", favoritos);
        tabs.addTab("Assistidas", assistidas);
        tabs.addTab("Desejo assistir", queroAssistir);

        tabs.setBackgroundAt(0, roxoUm);
        tabs.setForegroundAt(0, Color.BLACK);

        tabs.setBackgroundAt(1, roxoDois);
        tabs.setForegroundAt(1, Color.BLACK);

        tabs.setBackgroundAt(2, roxoTres);
        tabs.setForegroundAt(2, Color.BLACK);

        split.setTopComponent(painelTop);
        split.setBottomComponent(tabs);

        split.setDividerLocation(500);
        split.setResizeWeight(0.65);

        fundoPrincipal.add(split, BorderLayout.CENTER);

        telaPrincipal.add(fundoPrincipal);
        telaPrincipal.setVisible(true);
    }

    private void criarTabelasTabs() {

        String[] colunas = {
                "Nome", "Idioma", "Gêneros", "Nota",
                "Estado", "Estreia", "Término", "Emissora"
        };

        modeloFavoritos = new DefaultTableModel(colunas, 0);
        tabelaFavoritos = new JTable(modeloFavoritos);
        favoritos.add(new JScrollPane(tabelaFavoritos), BorderLayout.CENTER);

        modeloAssistidas = new DefaultTableModel(colunas, 0);
        tabelaAssistidas = new JTable(modeloAssistidas);
        assistidas.add(new JScrollPane(tabelaAssistidas), BorderLayout.CENTER);

        modeloDesejo = new DefaultTableModel(colunas, 0);
        tabelaDesejo = new JTable(modeloDesejo);
        queroAssistir.add(new JScrollPane(tabelaDesejo), BorderLayout.CENTER);
    }

    public void configurarBotoes() {

        buscar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {

                        String pesquisa = URLEncoder.encode(
                                campoPesquisa.getText(),
                                StandardCharsets.UTF_8
                        );

                        cacheSeries = service.buscarSerie(pesquisa);

                        if (cacheSeries == null || cacheSeries.isEmpty()) {
                            throw new RuntimeException("Nenhuma série encontrada");
                        }

                        preencherTabela(cacheSeries);

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(
                                null,
                                ex.getMessage(),
                                "Erro",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                }
            });

            botaoSalvar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    atualizarDadosUsuario();
                    JOptionPane.showMessageDialog(
                            telaPrincipal,
                            "Dados salvos com sucesso",
                            "Sucesso",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
            });

        comboFiltro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String filtro = (String) comboFiltro.getSelectedItem();

                if (filtro == null || filtro.equals("Filtro")) {
                    return;
                }

                int tab = tabs.getSelectedIndex();

                List<Serie> listaAtual;

                switch (tab) {
                    case 0:
                        listaAtual = listaFavoritos;
                        break;

                    case 1:
                        listaAtual = listaAssistidas;
                        break;

                    case 2:
                        listaAtual = listaDesejo;
                        break;

                    default:
                        return;
                }

                ordenar(listaAtual, filtro);

                DefaultTableModel modeloAtual;

                switch (tab) {
                    case 0:
                        modeloAtual = modeloFavoritos;
                        break;

                    case 1:
                        modeloAtual = modeloAssistidas;
                        break;

                    case 2:
                        modeloAtual = modeloDesejo;
                        break;

                    default:
                        return;
                }

                atualizarTabela(modeloAtual, listaAtual);
            }
        });

        botaoFavoritos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarSerie(modeloFavoritos, "Favoritos");
            }
        });

        botaoAssistidos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarSerie(modeloAssistidas, "Assistidas");
            }
        });

        botaoDesejoAssistir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarSerie(modeloDesejo, "Desejo Assistir");
            }
        });

        botaoRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerSerieSelecionada();
            }
        });

    }

    private void preencherTabela(List<Serie> series) {

        modeloSeries.setRowCount(0);

        for (Serie s : series) {
            modeloSeries.addRow(new Object[]{
                    s.getNome(),
                    s.getIdioma(),
                    String.join(", ", s.getGeneros()),
                    s.getNotaGeral(),
                    s.getEstado(),
                    s.getDataEstreia(),
                    s.getDataTermino(),
                    s.getEmissora()
            });
        }
    }

    private void adicionarSerie(DefaultTableModel modelo, String tipo) {

        int row = tabelaSeries.getSelectedRow();
        if (row < 0) return;

        Serie s = cacheSeries.get(row);

        List<Serie> listaAlvo;

        switch (tipo) {

            case "Favoritos":
                listaAlvo = listaFavoritos;
                break;

            case "Assistidas":
                listaAlvo = listaAssistidas;
                break;

            case "Desejo Assistir":
                listaAlvo = listaDesejo;
                break;

            default:
                throw new IllegalArgumentException("Tipo inválido: " + tipo);
        }

        boolean existe = listaAlvo.stream()
                .anyMatch(x -> x.getNome().equalsIgnoreCase(s.getNome()));

        if (existe) {
            JOptionPane.showMessageDialog(
                    null,
                    "Esta Série já existe em " + tipo,
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                null,
                "Adicionar em " + tipo + "?",
                "Confirmação",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) return;

        listaAlvo.add(s);

        atualizarTabela(modelo, listaAlvo);
    }

        private void atualizarTabela(DefaultTableModel modelo, List<Serie> lista) {

            modelo.setRowCount(0);

            for (Serie s : lista) {
                modelo.addRow(new Object[]{
                        s.getNome(),
                        s.getIdioma(),
                        String.join(", ", s.getGeneros()),
                        s.getNotaGeral(),
                        s.getEstado(),
                        s.getDataEstreia(),
                        s.getDataTermino(),
                        s.getEmissora()
                });
            }
        }

    private void removerSerieSelecionada() {

        int tab = tabs.getSelectedIndex();

        JTable tabelaAtual;
        DefaultTableModel modeloAtual;
        List<Serie> listaAtual;

        if (tab == 0) {
            tabelaAtual = tabelaFavoritos;
            modeloAtual = modeloFavoritos;
            listaAtual = listaFavoritos;
        } else if (tab == 1) {
            tabelaAtual = tabelaAssistidas;
            modeloAtual = modeloAssistidas;
            listaAtual = listaAssistidas;
        } else {
            tabelaAtual = tabelaDesejo;
            modeloAtual = modeloDesejo;
            listaAtual = listaDesejo;
        }

        int row = tabelaAtual.getSelectedRow();

        if (row < 0) {
            JOptionPane.showMessageDialog(null, "Selecione uma série para remover");
            return;
        }

        int confirmacao = JOptionPane.showConfirmDialog(
                null,
                "Tem certeza que deseja remover esta série?",
                "Confirmação",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmacao != JOptionPane.YES_OPTION) return;

        listaAtual.remove(row);
        modeloAtual.removeRow(row);
    }

    public void configurarTabelas() {

        tabelaSeries.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = tabelaSeries.getSelectedRow();
                if (row >= 0) {
                    System.out.println(modeloSeries.getValueAt(row, 0));
                }
            }
        });
    }

        public void ordenar(List<Serie> lista, String tipo) {

            switch (tipo) {

                case "Nome (A-Z)":
                    lista.sort(Comparator.comparing(
                            Serie::getNome,
                            String.CASE_INSENSITIVE_ORDER
                    ));
                    break;

                case "Nota geral":
                    lista.sort(Comparator.comparingDouble(Serie::getNotaGeral).reversed());
                    break;

                case "Data de estreia":
                    lista.sort(Comparator.comparing(
                            Serie::getDataEstreia,
                            Comparator.nullsLast(Comparator.naturalOrder())
                    ).reversed());
                    break;

                case "Estado":
                    lista.sort(COMPARADOR_ESTADO);
                    break;

                default:
                    break;
            }

        }

    private void atualizarDadosUsuario() {

        usuario.setFavoritas(new ArrayList<>());
        usuario.setAssistidas(new ArrayList<>());
        usuario.setDesejaAssistir(new ArrayList<>());

        usuario.setFavoritas(new ArrayList<>(listaFavoritos));
        usuario.setAssistidas(new ArrayList<>(listaAssistidas));
        usuario.setDesejaAssistir(new ArrayList<>(listaDesejo));

        System.out.println(usuario.getFavoritas());

        try {
            usuarioService.atualizarUsuario(usuario);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Erro ao atualizar usuário: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void carregarDadosUsuario() {

        if (usuario.getFavoritas() != null) {
            listaFavoritos = new ArrayList<>(usuario.getFavoritas());
        }

        if (usuario.getAssistidas() != null) {
            listaAssistidas = new ArrayList<>(usuario.getAssistidas());
        }

        if (usuario.getDesejaAssistir() != null) {
            listaDesejo = new ArrayList<>(usuario.getDesejaAssistir());
        }
    }
}