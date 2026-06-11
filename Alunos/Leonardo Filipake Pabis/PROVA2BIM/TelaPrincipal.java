package app.ui;

import app.model.Serie;
import app.model.Usuario;
import app.service.ApiService;
import app.service.JsonService;
import app.util.OrdenadorSeries;

import javax.swing.*;
import java.awt.*;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TelaPrincipal extends JFrame {

    private Usuario usuario;

    private final ApiService apiService =
            new ApiService();

    private final JsonService jsonService =
            new JsonService();

    private JTextArea areaDescricao;

    private DefaultListModel<String> modeloLista;

    private JList<String> listaSeries;

    private List<Serie> seriesAtuais =
            new ArrayList<>();

    private List<Serie> listaEmExibicao;

    private JComboBox<String> comboOrdenacao;

    private JLabel tituloLista;

    private JButton botaoRemoverLista;

    private JButton botaoRemoverListaDetalhe;

    private JPanel painelRemoverListaDetalhe;

    private String nomeListaEmExibicao;

    private final Map<String, Set<String>> seriesRemovidasNestaExecucao =
            new HashMap<>();

    private boolean usuarioCriadoNestaExecucao;

    private final CardLayout cardLayout =
            new CardLayout();

    private final JPanel painelRaiz =
            new JPanel(cardLayout);

    public TelaPrincipal() {

        carregarUsuario();

        setTitle("Sistema de Séries");

        setSize(1200, 700);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                jsonService.salvar(usuario);
            }

            @Override
            public void windowClosed(java.awt.event.WindowEvent e) {
                jsonService.salvar(usuario);
            }
        });

        setContentPane(painelRaiz);

        painelRaiz.add(
                criarTelaBoasVindas(),
                "boasVindas"
        );

        painelRaiz.add(
                criarTelaSistema(),
                "sistema"
        );

        cardLayout.show(
                painelRaiz,
                "boasVindas"
        );

        setVisible(true);
    }

    private JPanel criarTelaBoasVindas() {

        JPanel painel =
                new JPanel(new BorderLayout());

        JPanel conteudo =
                new JPanel();

        conteudo.setLayout(
                new BoxLayout(
                        conteudo,
                        BoxLayout.Y_AXIS
                )
        );

        conteudo.setBorder(
                BorderFactory.createEmptyBorder(
                        120,
                        250,
                        120,
                        250
                )
        );

        JLabel titulo =
                new JLabel(
                        "Bem-vindo, " + usuario.getNome() + "!"
                );

        titulo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        34
                )
        );

        titulo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JLabel subtitulo =
                new JLabel(
                        "Organize suas séries favoritas, assistidas e as que deseja ver."
                );

        subtitulo.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        16
                )
        );

        subtitulo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JButton entrar =
                new JButton("Entrar no sistema");

        entrar.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        entrar.addActionListener(e ->
                cardLayout.show(
                        painelRaiz,
                        "sistema"
                )
        );

        conteudo.add(Box.createVerticalGlue());
        conteudo.add(titulo);
        conteudo.add(Box.createRigidArea(new Dimension(0, 15)));
        conteudo.add(subtitulo);
        conteudo.add(Box.createRigidArea(new Dimension(0, 35)));
        conteudo.add(entrar);
        conteudo.add(Box.createVerticalGlue());

        painel.add(
                conteudo,
                BorderLayout.CENTER
        );

        return painel;
    }

    private JPanel criarTelaSistema() {

        JPanel painelSistema =
                new JPanel(new BorderLayout());

        JPanel topo =
                new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel saudacao =
                new JLabel("Olá, " + usuario.getNome() + " |");

        JTextField campoBusca =
                new JTextField(24);

        JButton botaoBuscar =
                new JButton("Buscar");

        JButton favoritos =
                new JButton("Favoritos");

        JButton assistidas =
                new JButton("Já Vistas");

        JButton desejaVer =
                new JButton("Deseja Ver");

        topo.add(saudacao);
        topo.add(new JLabel("Série:"));
        topo.add(campoBusca);
        topo.add(botaoBuscar);
        topo.add(favoritos);
        topo.add(assistidas);
        topo.add(desejaVer);

        painelSistema.add(
                topo,
                BorderLayout.NORTH
        );

        modeloLista =
                new DefaultListModel<>();

        listaSeries =
                new JList<>(modeloLista);

        JScrollPane painelLista =
                new JScrollPane(listaSeries);

        painelLista.setPreferredSize(
                new Dimension(320, 0)
        );

        JPanel painelEsquerdo =
                new JPanel(new BorderLayout());

        tituloLista =
                new JLabel(
                        "Resultados da busca",
                        SwingConstants.CENTER
                );

        tituloLista.setBorder(
                BorderFactory.createEmptyBorder(
                        8,
                        8,
                        8,
                        8
                )
        );

        painelEsquerdo.add(
                tituloLista,
                BorderLayout.NORTH
        );

        painelEsquerdo.add(
                painelLista,
                BorderLayout.CENTER
        );

        painelSistema.add(
                painelEsquerdo,
                BorderLayout.WEST
        );

        JPanel centro =
                new JPanel(new BorderLayout());

        areaDescricao =
                new JTextArea();

        areaDescricao.setLineWrap(true);

        areaDescricao.setWrapStyleWord(true);

        areaDescricao.setEditable(false);

        areaDescricao.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        areaDescricao.setText(
                "Busque uma série para começar."
        );

        painelRemoverListaDetalhe =
                new JPanel(new FlowLayout(FlowLayout.LEFT));

        painelRemoverListaDetalhe.setBorder(
                BorderFactory.createEmptyBorder(
                        8,
                        8,
                        0,
                        8
                )
        );

        botaoRemoverListaDetalhe =
                new JButton("Remover da lista");

        botaoRemoverListaDetalhe.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        botaoRemoverListaDetalhe.setVisible(false);

        painelRemoverListaDetalhe.add(
                botaoRemoverListaDetalhe
        );

        painelRemoverListaDetalhe.setVisible(false);

        centro.add(
                painelRemoverListaDetalhe,
                BorderLayout.NORTH
        );

        centro.add(
                new JScrollPane(areaDescricao),
                BorderLayout.CENTER
        );

        JPanel painelBotoes =
                new JPanel();

        JButton addFavoritos =
                new JButton("Adicionar aos Favoritos");

        JButton addAssistidas =
                new JButton("Adicionar em Já Vistas");

        JButton addDeseja =
                new JButton("Adicionar em Deseja Ver");

        botaoRemoverLista =
                new JButton("Remover esta série da lista");

        botaoRemoverLista.setEnabled(false);

        botaoRemoverLista.setToolTipText(
                "Abra uma lista salva e selecione uma série para remover."
        );

        painelBotoes.add(addFavoritos);
        painelBotoes.add(addAssistidas);
        painelBotoes.add(addDeseja);
        painelBotoes.add(botaoRemoverLista);

        centro.add(
                painelBotoes,
                BorderLayout.SOUTH
        );

        painelSistema.add(
                centro,
                BorderLayout.CENTER
        );

        JPanel direita =
                new JPanel(new BorderLayout());

        direita.setPreferredSize(
                new Dimension(260, 0)
        );

        JPanel painelOrdenacao =
                new JPanel();

        painelOrdenacao.setLayout(
                new BoxLayout(
                        painelOrdenacao,
                        BoxLayout.Y_AXIS
                )
        );

        painelOrdenacao.setBorder(
                BorderFactory.createEmptyBorder(
                        12,
                        12,
                        12,
                        12
                )
        );

        painelOrdenacao.add(
                new JLabel("Ordenar por:")
        );

        comboOrdenacao =
                new JComboBox<>(new String[]{
                        "Ordem alfabética do nome",
                        "Nota geral",
                        "Estado da série",
                        "Data de estreia"
                });

        painelOrdenacao.add(
                Box.createRigidArea(new Dimension(0, 8))
        );

        painelOrdenacao.add(
                comboOrdenacao
        );

        direita.add(
                painelOrdenacao,
                BorderLayout.NORTH
        );

        painelSistema.add(
                direita,
                BorderLayout.EAST
        );

        Runnable acaoBuscar = () ->
                buscarSeries(
                        campoBusca.getText()
                );

        botaoBuscar.addActionListener(e ->
                acaoBuscar.run()
        );

        campoBusca.addActionListener(e ->
                acaoBuscar.run()
        );

        listaSeries.addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {

                int index =
                        listaSeries.getSelectedIndex();

                if (index >= 0 &&
                        index < seriesAtuais.size()) {

                    Serie serie =
                            seriesAtuais.get(index);

                    areaDescricao.setText(
                            serie.toString()
                    );

                    atualizarEstadoBotaoRemover();
                }
            }
        });

        addFavoritos.addActionListener(e ->
                adicionarNaLista(
                        usuario.getFavoritos(),
                        "Favoritos"
                )
        );

        addAssistidas.addActionListener(e ->
                adicionarNaLista(
                        usuario.getAssistidas(),
                        "Já Vistas"
                )
        );

        addDeseja.addActionListener(e ->
                adicionarNaLista(
                        usuario.getDesejaAssistir(),
                        "Deseja Ver"
                )
        );

        botaoRemoverLista.addActionListener(e ->
                removerDaListaAtual()
        );

        botaoRemoverListaDetalhe.addActionListener(e ->
                removerDaListaAtual()
        );

        favoritos.addActionListener(e ->
                mostrarLista(
                        usuario.getFavoritos(),
                        "Favoritos"
                )
        );

        assistidas.addActionListener(e ->
                mostrarLista(
                        usuario.getAssistidas(),
                        "Já Vistas"
                )
        );

        desejaVer.addActionListener(e ->
                mostrarLista(
                        usuario.getDesejaAssistir(),
                        "Deseja Ver"
                )
        );

        comboOrdenacao.addActionListener(e ->
                ordenarListaAtual()
        );

        return painelSistema;
    }

    private void buscarSeries(String busca) {

        if (busca == null || busca.isBlank()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Digite o nome de uma série para buscar."
            );
            return;
        }

        tituloLista.setText(
                "Resultados da busca"
        );

        areaDescricao.setText(
                "Buscando séries..."
        );

        listaEmExibicao = null;
        nomeListaEmExibicao = null;
        atualizarEstadoBotaoRemover();

        seriesAtuais =
                apiService.buscarSeries(busca);

        ordenarListaAtual();

        if (seriesAtuais.isEmpty()) {
            areaDescricao.setText(
                    "Nenhuma série encontrada."
            );
        } else {
            areaDescricao.setText(
                    "Selecione uma série para ver os detalhes."
            );
        }
    }

    private void adicionarNaLista(
            List<Serie> lista,
            String nomeLista) {

        // Antes de validar duplicidade, sincroniza a memória com o JSON atual.
        // Assim, se uma série foi removida e salva, ela não fica presa em
        // nenhuma referência antiga da tela.
        Usuario usuarioRecarregado = jsonService.carregar();
        if (usuarioRecarregado != null) {
            usuario = usuarioRecarregado;
            garantirListasNaoNulas();
        }

        lista = obterListaRealPorNome(nomeLista);

        if (lista == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Lista não encontrada."
            );
            return;
        }

        int index =
                listaSeries.getSelectedIndex();

        if (index < 0 ||
                index >= seriesAtuais.size()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Selecione uma série antes de adicionar."
            );
            return;
        }

        Serie serie =
                seriesAtuais.get(index);

        if (serieJaExisteNaLista(lista, serie)) {

            if (serieFoiRemovidaNestaExecucao(nomeLista, serie)) {
                lista = removerSerieDaListaPorChaves(lista, serie);
                substituirListaReal(nomeLista, lista);
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Essa série já está na lista " + nomeLista + "."
                );
                return;
            }
        }

        lista.add(serie);
        limparMarcacaoRemovida(nomeLista, serie);

        jsonService.salvar(usuario);

        if (nomeLista.equals(nomeListaEmExibicao)) {
            mostrarLista(lista, nomeLista);
        }

        JOptionPane.showMessageDialog(
                this,
                "Série adicionada em " + nomeLista + "."
        );
    }

    private boolean serieJaExisteNaLista(
            List<Serie> lista,
            Serie serie) {

        for (Serie item : lista) {
            if (mesmaSerie(item, serie)) {
                return true;
            }
        }

        return false;
    }

    private boolean mesmaSerie(Serie primeira, Serie segunda) {

        if (primeira == null || segunda == null) {
            return false;
        }

        String nomePrimeira = normalizar(primeira.getName());
        String nomeSegunda = normalizar(segunda.getName());

        if (!nomePrimeira.isBlank() && !nomeSegunda.isBlank()) {
            return nomePrimeira.equals(nomeSegunda);
        }

        if (primeira.getId() != null &&
                segunda.getId() != null &&
                primeira.getId().equals(segunda.getId())) {
            return true;
        }

        return false;
    }

    private String normalizar(String texto) {

        if (texto == null) {
            return "";
        }

        return Normalizer.normalize(
                        texto,
                        Normalizer.Form.NFD
                )
                .replaceAll("\\p{M}", "")
                .trim()
                .toLowerCase();
    }

    private void removerDaListaAtual() {

        String listaAtual = nomeListaEmExibicao;

        if (listaAtual == null || obterListaRealPorNome(listaAtual) == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Abra uma das listas salvas para remover uma série."
            );
            return;
        }

        Serie serieSelecionada = obterSerieSelecionada();

        if (serieSelecionada == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Selecione uma série para remover."
            );
            return;
        }

        String nomeSelecionadoNaTela = listaSeries.getSelectedValue();

        // Recarrega antes de remover para garantir que a operação seja feita
        // na lista persistida atual, não em uma cópia visual/ordenada antiga.
        Usuario usuarioRecarregado = jsonService.carregar();
        if (usuarioRecarregado != null) {
            usuario = usuarioRecarregado;
            garantirListasNaoNulas();
        }

        List<Serie> listaReal = obterListaRealPorNome(listaAtual);

        ResultadoRemocao resultado = removerSerieCriandoNovaLista(
                listaReal,
                serieSelecionada,
                nomeSelecionadoNaTela
        );

        if (!resultado.removeu()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Essa série não foi encontrada na lista " + listaAtual + "."
            );
            atualizarEstadoBotaoRemover();
            return;
        }

        substituirListaReal(listaAtual, resultado.listaAtualizada());
        marcarSerieRemovidaNestaExecucao(listaAtual, serieSelecionada);

        jsonService.salvar(usuario);

        Usuario usuarioSalvo = jsonService.carregar();
        if (usuarioSalvo != null) {
            usuario = usuarioSalvo;
            garantirListasNaoNulas();
        }

        mostrarLista(obterListaRealPorNome(listaAtual), listaAtual);

        areaDescricao.setText(
                "Série removida da lista " + listaAtual + ": " + serieSelecionada.getName()
        );

        atualizarEstadoBotaoRemover();
    }

    private ResultadoRemocao removerSerieCriandoNovaLista(
            List<Serie> lista,
            Serie serie,
            String nomeSelecionadoNaTela) {

        List<Serie> listaAtualizada = new ArrayList<>();

        if (lista == null || serie == null) {
            return new ResultadoRemocao(listaAtualizada, false);
        }

        String nomeTelaNormalizado = normalizar(nomeSelecionadoNaTela);
        boolean removeu = false;

        for (Serie item : lista) {

            boolean itemSelecionado =
                    mesmaSerie(item, serie) ||
                            (!nomeTelaNormalizado.isBlank() &&
                                    normalizar(item.getName()).equals(nomeTelaNormalizado));

            if (itemSelecionado) {
                removeu = true;
            } else {
                listaAtualizada.add(item);
            }
        }

        return new ResultadoRemocao(listaAtualizada, removeu);
    }

    private record ResultadoRemocao(List<Serie> listaAtualizada, boolean removeu) {}


    private void marcarSerieRemovidaNestaExecucao(String nomeLista, Serie serie) {

        if (nomeLista == null || serie == null) {
            return;
        }

        Set<String> chaves = seriesRemovidasNestaExecucao.computeIfAbsent(
                nomeLista,
                chave -> new HashSet<>()
        );

        String chaveId = chavePorId(serie);
        String chaveNome = chavePorNome(serie);

        if (!chaveId.isBlank()) {
            chaves.add(chaveId);
        }

        if (!chaveNome.isBlank()) {
            chaves.add(chaveNome);
        }
    }

    private boolean serieFoiRemovidaNestaExecucao(String nomeLista, Serie serie) {

        if (nomeLista == null || serie == null) {
            return false;
        }

        Set<String> chaves = seriesRemovidasNestaExecucao.get(nomeLista);

        if (chaves == null || chaves.isEmpty()) {
            return false;
        }

        String chaveId = chavePorId(serie);
        String chaveNome = chavePorNome(serie);

        if (!chaveNome.isBlank()) {
            return chaves.contains(chaveNome);
        }

        return !chaveId.isBlank() && chaves.contains(chaveId);
    }

    private void limparMarcacaoRemovida(String nomeLista, Serie serie) {

        Set<String> chaves = seriesRemovidasNestaExecucao.get(nomeLista);

        if (chaves == null || serie == null) {
            return;
        }

        chaves.remove(chavePorId(serie));
        chaves.remove(chavePorNome(serie));
    }

    private List<Serie> removerSerieDaListaPorChaves(List<Serie> lista, Serie serie) {

        List<Serie> listaAtualizada = new ArrayList<>();

        if (lista == null) {
            return listaAtualizada;
        }

        String chaveId = chavePorId(serie);
        String chaveNome = chavePorNome(serie);

        for (Serie item : lista) {

            boolean itemRemovido = !chaveNome.isBlank()
                    ? chaveNome.equals(chavePorNome(item))
                    : !chaveId.isBlank() && chaveId.equals(chavePorId(item));

            if (!itemRemovido) {
                listaAtualizada.add(item);
            }
        }

        return listaAtualizada;
    }

    private String chavePorId(Serie serie) {

        if (serie == null || serie.getId() == null) {
            return "";
        }

        return "id:" + serie.getId();
    }

    private String chavePorNome(Serie serie) {

        if (serie == null) {
            return "";
        }

        String nomeNormalizado = normalizar(serie.getName());

        return nomeNormalizado.isBlank()
                ? ""
                : "nome:" + nomeNormalizado;
    }

    private Serie obterSerieSelecionada() {

        int index =
                listaSeries.getSelectedIndex();

        if (index < 0 ||
                seriesAtuais == null ||
                index >= seriesAtuais.size()) {
            return null;
        }

        return seriesAtuais.get(index);
    }

    private List<Serie> obterListaEmExibicaoReal() {

        if (nomeListaEmExibicao == null) {
            return null;
        }

        return obterListaRealPorNome(nomeListaEmExibicao);
    }

    private List<Serie> obterListaRealPorNome(String nomeLista) {

        if (nomeLista == null) {
            return null;
        }

        return switch (nomeLista) {
            case "Favoritos" -> usuario.getFavoritos();
            case "Já Vistas" -> usuario.getAssistidas();
            case "Deseja Ver" -> usuario.getDesejaAssistir();
            default -> null;
        };
    }

    private void substituirListaReal(String nomeLista, List<Serie> novaLista) {

        if (nomeLista == null || novaLista == null) {
            return;
        }

        switch (nomeLista) {
            case "Favoritos" -> usuario.setFavoritos(novaLista);
            case "Já Vistas" -> usuario.setAssistidas(novaLista);
            case "Deseja Ver" -> usuario.setDesejaAssistir(novaLista);
            default -> { }
        }
    }

    private void mostrarLista(
            List<Serie> lista,
            String nomeLista) {

        listaEmExibicao = lista;

        nomeListaEmExibicao = nomeLista;

        seriesAtuais = new ArrayList<>(lista);

        tituloLista.setText(
                "Lista: " + nomeLista
        );

        ordenarListaAtual();

        if (seriesAtuais.isEmpty()) {
            areaDescricao.setText(
                    "Essa lista ainda está vazia."
            );
        } else {
            areaDescricao.setText(
                    "Selecione uma série para ver os detalhes e, se quiser, remover da lista."
            );
        }

        atualizarEstadoBotaoRemover();
    }

    private void atualizarEstadoBotaoRemover() {

        if (botaoRemoverLista == null) {
            return;
        }

        boolean estaEmListaSalva =
                obterListaEmExibicaoReal() != null;

        boolean temSerieSelecionada =
                listaSeries != null &&
                        listaSeries.getSelectedIndex() >= 0;

        boolean podeRemover =
                estaEmListaSalva && temSerieSelecionada;

        botaoRemoverLista.setEnabled(podeRemover);

        String textoBotao =
                estaEmListaSalva && nomeListaEmExibicao != null
                        ? "Remover de " + nomeListaEmExibicao
                        : "Remover esta série da lista";

        botaoRemoverLista.setText(textoBotao);

        if (botaoRemoverListaDetalhe != null) {
            botaoRemoverListaDetalhe.setText(textoBotao);
            botaoRemoverListaDetalhe.setVisible(podeRemover);
        }

        if (painelRemoverListaDetalhe != null) {
            painelRemoverListaDetalhe.setVisible(podeRemover);
            painelRemoverListaDetalhe.revalidate();
            painelRemoverListaDetalhe.repaint();
        }
    }

    private void ordenarListaAtual() {

        if (seriesAtuais == null) {
            return;
        }

        String tipo =
                comboOrdenacao == null
                        ? "Ordem alfabética do nome"
                        : comboOrdenacao
                        .getSelectedItem()
                        .toString();

        switch (tipo) {

            case "Ordem alfabética do nome" ->
                    OrdenadorSeries
                            .ordenarPorNome(
                                    seriesAtuais
                            );

            case "Nota geral" ->
                    OrdenadorSeries
                            .ordenarPorNota(
                                    seriesAtuais
                            );

            case "Estado da série" ->
                    OrdenadorSeries
                            .ordenarPorStatus(
                                    seriesAtuais
                            );

            case "Data de estreia" ->
                    OrdenadorSeries
                            .ordenarPorEstreia(
                                    seriesAtuais
                            );
        }

        atualizarModelo();
    }

    private void atualizarModelo() {

        if (modeloLista == null) {
            return;
        }

        modeloLista.clear();

        for (Serie s : seriesAtuais) {

            modeloLista.addElement(
                    s.getName()
            );
        }

        if (listaSeries != null) {
            listaSeries.clearSelection();
        }

        atualizarEstadoBotaoRemover();
    }

    private void carregarUsuario() {

        usuario = jsonService.carregar();

        boolean existeUsuarioSalvo =
                usuario != null &&
                        usuario.getNome() != null &&
                        !usuario.getNome().isBlank() &&
                        !usuario.getNome().equals("Usuário");

        if (!existeUsuarioSalvo) {

            String nome = JOptionPane.showInputDialog(
                    null,
                    "Digite seu nome:",
                    "Primeiro acesso",
                    JOptionPane.QUESTION_MESSAGE
            );

            while (nome != null && nome.isBlank()) {
                nome = JOptionPane.showInputDialog(
                        null,
                        "Digite seu nome para continuar:",
                        "Primeiro acesso",
                        JOptionPane.QUESTION_MESSAGE
                );
            }

            if (nome == null || nome.isBlank()) {
                nome = "Usuário";
            }

            usuario = new Usuario(nome.trim());
            garantirListasNaoNulas();
            popularListasIniciaisSeNecessario();
            usuario.setDadosIniciaisCriados(true);
            jsonService.salvar(usuario);

            return;
        }

        garantirListasNaoNulas();

        if (!usuario.isDadosIniciaisCriados()) {
            usuario.setDadosIniciaisCriados(true);
            jsonService.salvar(usuario);
        }
    }

    private void garantirListasNaoNulas() {
        usuario.getFavoritos();
        usuario.getAssistidas();
        usuario.getDesejaAssistir();
    }

    private void popularListasIniciaisSeNecessario() {

        if (usuario.getFavoritos().isEmpty()) {
            usuario.getFavoritos().add(criarSerie(46562, "The Last of Us", "English",
                    List.of("Drama", "Horror", "Science-Fiction"), 8.2, "Running",
                    "2023-01-15", "", "HBO",
                    "Vinte anos após uma pandemia mudar o mundo, Joel recebe a missão de escoltar Ellie por uma jornada perigosa."));
            usuario.getFavoritos().add(criarSerie(335, "Sherlock", "English",
                    List.of("Crime", "Drama", "Mystery"), 9.0, "Ended",
                    "2010-07-25", "2017-01-15", "BBC One",
                    "Sherlock Holmes e John Watson investigam crimes complexos em uma Londres moderna."));
            usuario.getFavoritos().add(criarSerie(258, "Batman", "English",
                    List.of("Action", "Adventure", "Crime"), 7.5, "Ended",
                    "1966-01-12", "1968-03-14", "ABC",
                    "Batman e Robin protegem Gotham City enfrentando vilões clássicos e situações cheias de ação."));
        }

        if (usuario.getAssistidas().isEmpty()) {
            usuario.getAssistidas().add(criarSerie(169, "Breaking Bad", "English",
                    List.of("Crime", "Drama", "Thriller"), 9.3, "Ended",
                    "2008-01-20", "2013-09-29", "AMC",
                    "Um professor de química passa a produzir metanfetamina após receber um diagnóstico grave."));
            usuario.getAssistidas().add(criarSerie(431, "Friends", "English",
                    List.of("Comedy", "Romance"), 8.5, "Ended",
                    "1994-09-22", "2004-05-06", "NBC",
                    "Seis amigos vivem situações engraçadas e marcantes enquanto constroem suas vidas em Nova York."));
            usuario.getAssistidas().add(criarSerie(82, "Game of Thrones", "English",
                    List.of("Adventure", "Drama", "Fantasy"), 8.9, "Ended",
                    "2011-04-17", "2019-05-19", "HBO",
                    "Famílias nobres disputam poder, alianças e sobrevivência em Westeros."));
        }

        if (usuario.getDesejaAssistir().isEmpty()) {
            usuario.getDesejaAssistir().add(criarSerie(2993, "The Witcher", "English",
                    List.of("Adventure", "Drama", "Fantasy"), 7.4, "Running",
                    "2019-12-20", "", "Netflix",
                    "Geralt de Rívia, um caçador de monstros, tenta encontrar seu lugar em um mundo cheio de magia e conflitos."));
            usuario.getDesejaAssistir().add(criarSerie(1371, "Stranger Things", "English",
                    List.of("Drama", "Fantasy", "Horror"), 8.4, "Running",
                    "2016-07-15", "", "Netflix",
                    "Um grupo de amigos enfrenta eventos sobrenaturais ligados a uma dimensão misteriosa."));
            usuario.getDesejaAssistir().add(criarSerie(73, "The Walking Dead", "English",
                    List.of("Drama", "Horror", "Thriller"), 8.0, "Ended",
                    "2010-10-31", "2022-11-20", "AMC",
                    "Sobreviventes tentam reconstruir suas vidas em um mundo dominado por zumbis."));
        }
    }

    private Serie criarSerie(
            Integer id,
            String nome,
            String idioma,
            List<String> generos,
            Double nota,
            String status,
            String estreia,
            String encerramento,
            String emissora,
            String descricao) {

        return new Serie(
                id,
                nome,
                idioma,
                generos,
                nota,
                status,
                estreia,
                encerramento,
                emissora,
                descricao
        );
    }
}
