package com.example.view;

import com.example.model.Serie;
import com.example.model.Usuario;
import com.example.service.ApiService;
import com.example.service.PersistenciaService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaPrincipal extends JFrame {

    private JTextField campoBusca;
    private JButton botaoBuscar;
    private JPanel painelInferior;

    private JList<Serie> listaResultados;
    private DefaultListModel<Serie> modeloResultados;

    private JTextArea areaDetalhes;

    private JButton botaoAdicionarFavorito;
    private JButton botaoAdicionarAssistida;
    private JButton botaoAdicionarDesejo;

    private JButton botaoRemoverFavorito;
    private JButton botaoRemoverAssistida;
    private JButton botaoRemoverDesejo;
    private JButton botaoMostrarOcultarListas;

    private JComboBox<String> comboOrdenacao;

    private JList<Serie> listaFavoritos;
    private JList<Serie> listaAssistidas;
    private JList<Serie> listaDesejo;

    private DefaultListModel<Serie> modeloFavoritos;
    private DefaultListModel<Serie> modeloAssistidas;
    private DefaultListModel<Serie> modeloDesejo;

    private ApiService apiService;
    private PersistenciaService persistenciaService;
    private Usuario usuario;

    private final Color COR_FUNDO = Color.decode("#505050");
    private final Color COR_PAINEL = Color.decode("#ffffff");
    private final Color COR_BOTAO = Color.decode("#ff7070");
    private final Color COR_BOTAO_TEXTO = Color.decode("#ffffff");
    private final Color COR_TEXTO = Color.decode("#ffffff");
    private final Color COR_TITULO = Color.decode("#ff7070");
    private final Color COR_SECUNDARIA = Color.decode("#ffffff");
    private final Color COR_BORDA = Color.decode("#ff7070");

    private void aplicarFonte(Component componente) {

        if (componente instanceof JButton) {
            componente.setFont(new Font("Segoe UI", Font.BOLD, 14));
        } else if (componente instanceof JTextField) {
            componente.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        } else if (componente instanceof JTextArea) {
            componente.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        } else if (componente instanceof JList) {
            componente.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        } else if (componente instanceof JComboBox) {
            componente.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        } else if (componente instanceof JTabbedPane) {
            componente.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        }

        if (componente instanceof Container) {
            for (Component filho : ((Container) componente).getComponents()) {
                aplicarFonte(filho);
            }
        }
    }

    private void aplicarCores(Component componente) {

        if (componente instanceof JPanel) {
            componente.setBackground(COR_FUNDO);
        }

        if (componente instanceof JTextArea) {
            componente.setBackground(COR_PAINEL);
            componente.setForeground(COR_TEXTO);
        }

        if (componente instanceof JTextField) {
            componente.setBackground(COR_PAINEL);
            componente.setForeground(COR_TEXTO);
        }

        if (componente instanceof JList) {
            JList<?> lista = (JList<?>) componente;
            lista.setBackground(COR_PAINEL);
            lista.setForeground(COR_TEXTO);
            lista.setSelectionBackground(COR_BOTAO);
            lista.setSelectionForeground(COR_BOTAO_TEXTO);
        }

        if (componente instanceof JButton) {
            JButton botao = (JButton) componente;

            botao.setBackground(COR_BOTAO);
            botao.setForeground(COR_BOTAO_TEXTO);
            botao.setFocusPainted(false);
            botao.setBorderPainted(false);
            botao.setRolloverEnabled(false);
            botao.setContentAreaFilled(true);
            botao.setOpaque(true);
            botao.setCursor(new Cursor(Cursor.HAND_CURSOR));

            botao.setBorder(BorderFactory.createEmptyBorder(8, 14, 8, 14));
        }

        if (componente instanceof JTabbedPane) {
            componente.setBackground(COR_FUNDO);
            componente.setForeground(COR_TEXTO);
        }

        if (componente instanceof JTabbedPane) {
        JTabbedPane abas = (JTabbedPane) componente;

        abas.setBackground(COR_FUNDO);
        abas.setForeground(COR_TEXTO);

        abas.setOpaque(true);

        for (int i = 0; i < abas.getTabCount(); i++) {
            abas.setBackgroundAt(i, COR_FUNDO);
            abas.setForegroundAt(i, COR_TEXTO);
        }

        abas.setBorder(BorderFactory.createEmptyBorder());
        }

        if (componente instanceof Container) {
            for (Component filho : ((Container) componente).getComponents()) {
                aplicarCores(filho);
            }
        }
    }

    public TelaPrincipal() {
        apiService = new ApiService();
        persistenciaService = new PersistenciaService();

        carregarUsuario();

        configurarJanela();
        criarRodape();

        UIManager.put("TabbedPane.selected", COR_BOTAO);
        UIManager.put("TabbedPane.contentAreaColor", COR_FUNDO);
        UIManager.put("TabbedPane.focus", COR_FUNDO);
        UIManager.put("TabbedPane.borderHightlightColor", COR_FUNDO);
        UIManager.put("TabbedPane.darkShadow", COR_FUNDO);
        UIManager.put("TabbedPane.light", COR_FUNDO);
        UIManager.put("TabbedPane.highlight", COR_FUNDO);

        criarComponentes();
        configurarEventos();
        atualizarListasUsuario();

        aplicarFonte(this);
        aplicarCores(this);
    }

    private void carregarUsuario() {
        try {
            usuario = persistenciaService.carregarUsuario();

        if (usuario == null) {
            String nome = JOptionPane.showInputDialog(
                    null,
                    "Digite seu nome ou apelido:",
                    "Cadastro de usuário",
                    JOptionPane.QUESTION_MESSAGE
            );

            if (nome == null || nome.isBlank()) {
                nome = "Usuário";
            }

            usuario = new Usuario(nome.trim());
            adicionarSeriesPreCarregadas();
            persistenciaService.salvarUsuario(usuario);
        }

        } catch (Exception e) {

        String nome = JOptionPane.showInputDialog(
            null,
            "Não foi possível carregar o usuário salvo.\nDigite seu nome ou apelido para criar um novo usuário:",
            "Novo usuário",
            JOptionPane.WARNING_MESSAGE
        );

        if (nome == null || nome.isBlank()) {
            nome = "Usuário";
        }

        usuario = new Usuario(nome.trim());

        JOptionPane.showMessageDialog(
            null,
            "Um novo usuário foi criado.",
            "Aviso",
            JOptionPane.INFORMATION_MESSAGE
        );

        e.printStackTrace();
        }
    }

    private void configurarJanela() {
        setTitle("Uma Série de Filmes ");
        setSize(1500, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private JPanel criarRodape() {
        JPanel painelRodape = new JPanel(new BorderLayout());
        painelRodape.setBackground(COR_FUNDO);
        painelRodape.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        JLabel emojis = new JLabel("🎞🎞🎞🎞🎞🎞🎞🎞🎞🎞🎞🎞🎞🎞🎞🎞🎞🎞🎞🎞🎞🎞🎞🎞🎞🎞🎞🎞");
        emojis.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 64));
        emojis.setHorizontalAlignment(SwingConstants.CENTER);

        painelRodape.add(emojis, BorderLayout.CENTER);

        return painelRodape;
    }
    private javax.swing.border.Border criarBordaTitulo(String titulo) {
    javax.swing.border.Border linha = BorderFactory.createLineBorder(COR_BORDA, 1);

    javax.swing.border.TitledBorder borda = BorderFactory.createTitledBorder(
            linha,
            titulo
    );

    borda.setTitleFont(new Font("Segoe UI", Font.BOLD, 13));
    borda.setTitleColor(COR_TITULO);

    return borda;
    }

    private void criarComponentes() {
        JPanel painelPrincipal = new JPanel(new BorderLayout(40,40));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(40,40,40,40));

        JPanel painelBusca = criarPainelBusca();
        JSplitPane painelCentro = criarPainelCentro();

        JTabbedPane abasListas = criarAbasListas();
        abasListas.setPreferredSize(new Dimension(0, 180));

        painelInferior = new JPanel(new BorderLayout(5, 5));
        painelInferior.add(criarPainelOrdenacao(), BorderLayout.NORTH);
        painelInferior.add(abasListas, BorderLayout.CENTER);

        painelInferior.setVisible(false);

        botaoMostrarOcultarListas = new JButton("Mostrar minhas listas");

        JPanel painelBotaoListas = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelBotaoListas.add(botaoMostrarOcultarListas);

        JPanel painelSul = new JPanel(new BorderLayout(5, 5));
        painelSul.add(painelBotaoListas, BorderLayout.NORTH);
        painelSul.add(painelInferior, BorderLayout.CENTER);

        painelPrincipal.add(painelBusca, BorderLayout.NORTH);
        painelPrincipal.add(painelCentro, BorderLayout.CENTER);
        painelPrincipal.add(painelBusca, BorderLayout.NORTH);
        painelPrincipal.add(painelCentro, BorderLayout.CENTER);

        JPanel painelInferiorGeral = new JPanel(new BorderLayout(5, 5));
        painelInferiorGeral.setBackground(COR_FUNDO);
        painelInferiorGeral.add(painelSul, BorderLayout.CENTER);
        painelInferiorGeral.add(criarRodape(), BorderLayout.SOUTH);

        painelPrincipal.add(painelInferiorGeral, BorderLayout.SOUTH);


        add(painelPrincipal);
    }

    private JPanel criarPainelOrdenacao() {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        comboOrdenacao = new JComboBox<>();
        comboOrdenacao.addItem("Nome");
        comboOrdenacao.addItem("Nota geral");
        comboOrdenacao.addItem("Status");
        comboOrdenacao.addItem("Data de estreia");

        JLabel labelOrdenacao = new JLabel("Ordenar listas por:");
        labelOrdenacao.setForeground(COR_BOTAO_TEXTO);
        labelOrdenacao.setFont(new Font("Segoe UI", Font.BOLD, 14));

        painel.add(labelOrdenacao);
        painel.add(comboOrdenacao);

        return painel;
    }

    private JPanel criarPainelBusca() {
        JPanel painelTopo = new JPanel(new BorderLayout(5, 15));
        painelTopo.setBackground(COR_FUNDO);

        JLabel emoji = new JLabel("🎞🎞🎞🎞🎞🎞🎞🎞🎞🎞🎞🎞🎞🎞🎞🎞🎞🎞🎞🎞🎞🎞🎞🎞🎞🎞🎞🎞");
        emoji.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 64
        ));
        emoji.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel titulo = new JLabel("UMA SÉRIE");
        JLabel subtitulo = new JLabel("DE FILMES");
        titulo.setFont(new Font("H", Font.BOLD, 40));
        titulo.setForeground(COR_TITULO);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        subtitulo.setFont(new Font("HHorizon", Font.BOLD, 40));
        subtitulo.setForeground(COR_TITULO);    
        subtitulo.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel saudacao = new JLabel("Olá, " + usuario.getNome());
        saudacao.setFont(new Font("Segoe UI", Font.BOLD, 20));
        saudacao.setForeground(COR_SECUNDARIA);

        JPanel painelTitulo = new JPanel(new GridLayout(4, 1));
        painelTitulo.setBackground(COR_FUNDO);
        painelTitulo.add(emoji);
        painelTitulo.add(titulo);
        painelTitulo.add(subtitulo);
        painelTitulo.add(saudacao);

        JPanel painelBusca = new JPanel(new BorderLayout(8, 8));
        painelBusca.setBackground(COR_FUNDO);

        campoBusca = new JTextField();
        botaoBuscar = new JButton("Buscar");

        JLabel labelNomeSerie = new JLabel("NOME DA SÉRIE:");
        labelNomeSerie.setForeground(COR_BOTAO_TEXTO);
        labelNomeSerie.setFont(new Font("Segoe UI", Font.BOLD, 14));

        painelBusca.add(labelNomeSerie, BorderLayout.WEST);
        painelBusca.add(campoBusca, BorderLayout.CENTER);
        painelBusca.add(botaoBuscar, BorderLayout.EAST);

        painelTopo.add(painelTitulo, BorderLayout.NORTH);
        painelTopo.add(painelBusca, BorderLayout.SOUTH);

        return painelTopo;
    }

    private JSplitPane criarPainelCentro() {
    modeloResultados = new DefaultListModel<>();
    listaResultados = new JList<>(modeloResultados);

    JScrollPane scrollLista = new JScrollPane(listaResultados);
    scrollLista.setBorder(criarBordaTitulo("Resultados da busca"));

    areaDetalhes = new JTextArea();
    areaDetalhes.setEditable(false);
    areaDetalhes.setLineWrap(true);
    areaDetalhes.setWrapStyleWord(true);

    JScrollPane scrollDetalhes = new JScrollPane(areaDetalhes);
    scrollDetalhes.setBorder(criarBordaTitulo("Detalhes da série"));

    botaoAdicionarFavorito = new JButton("Adicionar aos favoritos");
    botaoAdicionarAssistida = new JButton("Adicionar às assistidas");
    botaoAdicionarDesejo = new JButton("Adicionar ao desejo");

    JPanel painelBotoesAdicionar = new JPanel(new GridLayout(1, 3, 10, 5));
    painelBotoesAdicionar.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
    painelBotoesAdicionar.setBackground(COR_FUNDO);

    painelBotoesAdicionar.add(botaoAdicionarFavorito);
    painelBotoesAdicionar.add(botaoAdicionarAssistida);
    painelBotoesAdicionar.add(botaoAdicionarDesejo);

    JPanel painelResultados = new JPanel(new BorderLayout());
    painelResultados.setBackground(COR_FUNDO);
    painelResultados.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
    painelResultados.add(scrollLista, BorderLayout.CENTER);

    JPanel painelDetalhesCompleto = new JPanel(new BorderLayout(5, 5));
    painelDetalhesCompleto.setBackground(COR_FUNDO);
    painelDetalhesCompleto.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
    painelDetalhesCompleto.add(scrollDetalhes, BorderLayout.CENTER);
    painelDetalhesCompleto.add(painelBotoesAdicionar, BorderLayout.SOUTH);

    JSplitPane splitPane = new JSplitPane(
            JSplitPane.HORIZONTAL_SPLIT,
            painelResultados,
            painelDetalhesCompleto
    );

    splitPane.setDividerLocation(350);
    splitPane.setDividerSize(8);
    splitPane.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
    splitPane.setBackground(COR_FUNDO);

    return splitPane;
    }

    private JTabbedPane criarAbasListas() {
        JTabbedPane abas = new JTabbedPane();

        modeloFavoritos = new DefaultListModel<>();
        modeloAssistidas = new DefaultListModel<>();
        modeloDesejo = new DefaultListModel<>();

        listaFavoritos = new JList<>(modeloFavoritos);
        listaAssistidas = new JList<>(modeloAssistidas);
        listaDesejo = new JList<>(modeloDesejo);

        botaoRemoverFavorito = new JButton("Remover dos favoritos");
        botaoRemoverAssistida = new JButton("Remover das assistidas");
        botaoRemoverDesejo = new JButton("Remover do desejo");

        abas.addTab("Favoritos", criarPainelAba(listaFavoritos, botaoRemoverFavorito));
        abas.addTab("Já assistidas", criarPainelAba(listaAssistidas, botaoRemoverAssistida));
        abas.addTab("Desejo assistir", criarPainelAba(listaDesejo, botaoRemoverDesejo));

        return abas;
    }

    private JPanel criarPainelAba(JList<Serie> lista, JButton botaoRemover) {
        JPanel painel = new JPanel(new BorderLayout(5, 5));

        JScrollPane scroll = new JScrollPane(lista);

        painel.add(scroll, BorderLayout.CENTER);
        painel.add(botaoRemover, BorderLayout.SOUTH);

        return painel;
    }

    private void configurarEventos() {
        botaoBuscar.addActionListener(e -> buscarSeries());

        campoBusca.addActionListener(e -> buscarSeries());

        listaResultados.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Serie serieSelecionada = listaResultados.getSelectedValue();

                if (serieSelecionada != null) {
                    mostrarDetalhes(serieSelecionada);
                }
            }
        });

        listaFavoritos.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Serie serieSelecionada = listaFavoritos.getSelectedValue();

                if (serieSelecionada != null) {
                    mostrarDetalhes(serieSelecionada);
                }
            }
        });

        listaAssistidas.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Serie serieSelecionada = listaAssistidas.getSelectedValue();

                if (serieSelecionada != null) {
                    mostrarDetalhes(serieSelecionada);
                }
            }
        });

        listaDesejo.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Serie serieSelecionada = listaDesejo.getSelectedValue();

                if (serieSelecionada != null) {
                    mostrarDetalhes(serieSelecionada);
                }
            }
        });

        botaoAdicionarFavorito.addActionListener(e -> adicionarFavorito());
        botaoAdicionarAssistida.addActionListener(e -> adicionarAssistida());
        botaoAdicionarDesejo.addActionListener(e -> adicionarDesejo());

        botaoRemoverFavorito.addActionListener(e -> removerFavorito());
        botaoRemoverAssistida.addActionListener(e -> removerAssistida());
        botaoRemoverDesejo.addActionListener(e -> removerDesejo());

        botaoMostrarOcultarListas.addActionListener(e -> alternarVisibilidadeListas());
        comboOrdenacao.addActionListener(e -> {
        ordenarListas();
        atualizarListasUsuario();
        });
    }

    private void buscarSeries() {
        String busca = campoBusca.getText();

        if (busca == null || busca.isBlank()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Digite o nome de uma série para buscar.",
                    "Busca vazia",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        try {
            modeloResultados.clear();
            areaDetalhes.setText("");

            List<Serie> series = apiService.buscarSeries(busca);

            if (series.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Nenhuma série encontrada.",
                        "Resultado",
                        JOptionPane.INFORMATION_MESSAGE
                );
                return;
            }

            for (Serie serie : series) {
                modeloResultados.addElement(serie);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao buscar séries. Verifique sua conexão com a internet.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );

            ex.printStackTrace();
        }
    }

    private Serie obterSerieSelecionadaNosResultados() {
        Serie serieSelecionada = listaResultados.getSelectedValue();

        if (serieSelecionada == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Selecione uma série nos resultados da busca.",
                    "Nenhuma série selecionada",
                    JOptionPane.WARNING_MESSAGE
            );
        }

        return serieSelecionada;
    }

    private void adicionarFavorito() {
        Serie serie = obterSerieSelecionadaNosResultados();

        if (serie == null) {
            return;
        }

        int tamanhoAntes = usuario.getFavoritos().size();

        usuario.adicionarFavorito(serie);

        if (usuario.getFavoritos().size() == tamanhoAntes) {
            JOptionPane.showMessageDialog(this, "Essa série já está nos favoritos.");
            return;
        }

        salvarEAtualizar();
    }

    private void adicionarAssistida() {
        Serie serie = obterSerieSelecionadaNosResultados();

        if (serie == null) {
            return;
        }

        int tamanhoAntes = usuario.getAssistidas().size();

        usuario.adicionarAssistida(serie);

        if (usuario.getAssistidas().size() == tamanhoAntes) {
            JOptionPane.showMessageDialog(this, "Essa série já está nas assistidas.");
            return;
        }

        salvarEAtualizar();
    }

    private void adicionarDesejo() {
        Serie serie = obterSerieSelecionadaNosResultados();

        if (serie == null) {
            return;
        }

        int tamanhoAntes = usuario.getDesejoAssistir().size();

        usuario.adicionarDesejoAssistir(serie);

        if (usuario.getDesejoAssistir().size() == tamanhoAntes) {
            JOptionPane.showMessageDialog(this, "Essa série já está na lista de desejo.");
            return;
        }

        salvarEAtualizar();
    }

    private void removerFavorito() {
        Serie serie = listaFavoritos.getSelectedValue();

        if (serie == null) {
            JOptionPane.showMessageDialog(this, "Selecione uma série nos favoritos para remover.");
            return;
        }

        usuario.removerFavorito(serie);
        salvarEAtualizar();
    }

    private void removerAssistida() {
        Serie serie = listaAssistidas.getSelectedValue();

        if (serie == null) {
            JOptionPane.showMessageDialog(this, "Selecione uma série nas assistidas para remover.");
            return;
        }

        usuario.removerAssistida(serie);
        salvarEAtualizar();
    }

    private void removerDesejo() {
        Serie serie = listaDesejo.getSelectedValue();

        if (serie == null) {
            JOptionPane.showMessageDialog(this, "Selecione uma série na lista de desejo para remover.");
            return;
        }

        usuario.removerDesejoAssistir(serie);
        salvarEAtualizar();
    }

    private void salvarEAtualizar() {
        try {
            ordenarListas();
            atualizarListasUsuario();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao salvar os dados do usuário.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }

    private void atualizarListasUsuario() {
        modeloFavoritos.clear();
        modeloAssistidas.clear();
        modeloDesejo.clear();

        for (Serie serie : usuario.getFavoritos()) {
            modeloFavoritos.addElement(serie);
        }

        for (Serie serie : usuario.getAssistidas()) {
            modeloAssistidas.addElement(serie);
        }

        for (Serie serie : usuario.getDesejoAssistir()) {
            modeloDesejo.addElement(serie);
        }
    }

    private void mostrarDetalhes(Serie serie) {
        String detalhes = "";

        detalhes += "Nome: " + serie.getNome() + "\n";
        detalhes += "Idioma: " + serie.getIdioma() + "\n";
        detalhes += "Gêneros: " + serie.getGeneros() + "\n";
        detalhes += "Nota geral: " + serie.getNota() + "\n";
        detalhes += "Status: " + serie.getStatus() + "\n";
        detalhes += "Data de estreia: " + serie.getEstreia() + "\n";
        detalhes += "Data de término: " + serie.getTermino() + "\n";
        detalhes += "Emissora: " + serie.getEmissora() + "\n";

        areaDetalhes.setText(detalhes);
    }

    private void ordenarListas() {
        String criterio = (String) comboOrdenacao.getSelectedItem();

        if (criterio == null) {
            return;
        }

        ordenarLista(usuario.getFavoritos(), criterio);
        ordenarLista(usuario.getAssistidas(), criterio);
        ordenarLista(usuario.getDesejoAssistir(), criterio);

        try {
            persistenciaService.salvarUsuario(usuario);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao salvar a ordenação das listas.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }

    private void ordenarLista(List<Serie> lista, String criterio) {
        switch (criterio) {
            case "Nome":
                lista.sort((s1, s2) -> compararTexto(s1.getNome(), s2.getNome()));
                break;

            case "Nota geral":
                lista.sort((s1, s2) -> Double.compare(s2.getNota(), s1.getNota()));
                break;

            case "Status":
                lista.sort((s1, s2) -> compararTexto(s1.getStatus(), s2.getStatus()));
                break;

            case "Data de estreia":
                lista.sort((s1, s2) -> compararData(s1.getEstreia(), s2.getEstreia()));
                break;

            default:
                break;
        }
    }

    private int compararTexto(String texto1, String texto2) {
        if (texto1 == null) {
            texto1 = "";
        }

        if (texto2 == null) {
            texto2 = "";
        }

        return texto1.compareToIgnoreCase(texto2);
    }

    private int compararData(String data1, String data2) {
        boolean data1Invalida = data1 == null || data1.isBlank() || data1.equalsIgnoreCase("Não informado");
        boolean data2Invalida = data2 == null || data2.isBlank() || data2.equalsIgnoreCase("Não informado");

        if (data1Invalida && data2Invalida) {
            return 0;
        }

        if (data1Invalida) {
            return 1;
        }

        if (data2Invalida) {
            return -1;
        }

        return data1.compareTo(data2);
    }
    private void alternarVisibilidadeListas() {
        boolean listasVisiveis = painelInferior.isVisible();

        painelInferior.setVisible(!listasVisiveis);

        if (listasVisiveis) {
            botaoMostrarOcultarListas.setText("Mostrar minhas listas");
        } else {
            botaoMostrarOcultarListas.setText("Ocultar minhas listas");
        }

        revalidate();
        repaint();
    }
    private void adicionarSeriesPreCarregadas() {
        Serie breakingBad = new Serie(
            1,
            "Breaking Bad",
            "English",
            List.of("Drama", "Crime", "Thriller"),
            9.3,
            "Ended",
            "2008-01-20",
            "2013-09-29",
            "AMC"
        );
        usuario.adicionarFavorito(breakingBad);
    }
}