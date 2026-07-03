package view;

import controller.ListaController;
import controller.PesquisaController;
import model.Serie;
import model.User;
import repository.UserRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TelaPrincipal extends JFrame {

    private PainelTopo painelTopo;
    private PainelResultados painelResultados;
    private PainelDetalhesSerie painelDetalhesSerie;
    private PainelImagemSerie painelImagemSerie;

    private PesquisaController pesquisaController;
    private ListaController listaController;
    private UserRepository userRepository;

    private User usuario;

    private Serie serieSelecionada;
    private List<Serie> listaAtual;

    private String modoAtual;

    public TelaPrincipal(User usuario, UserRepository userRepository) {
        pesquisaController = new PesquisaController();
        listaController = new ListaController();

        this.usuario = usuario;
        this.userRepository = userRepository;

        listaAtual = new ArrayList<>();
        modoAtual = "pesquisa";

        configurarJanela();
        criarComponentes();
        configurarEventos();
    }

    private void configurarJanela() {
        setTitle("Acompanhador de Séries");
        setSize(1100, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                fecharPrograma();
            }
        });
    }

    private void criarComponentes() {
        painelTopo = new PainelTopo(usuario.getNome());
        painelResultados = new PainelResultados();
        painelDetalhesSerie = new PainelDetalhesSerie();
        painelImagemSerie = new PainelImagemSerie();

        JSplitPane splitCentro = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                painelResultados,
                painelDetalhesSerie
        );

        splitCentro.setDividerLocation(380);
        splitCentro.setResizeWeight(0.40);

        add(painelTopo, BorderLayout.NORTH);
        add(splitCentro, BorderLayout.CENTER);
        add(painelImagemSerie, BorderLayout.EAST);

        painelResultados.exibirMensagem("Pesquise uma série para começar.");
    }

    private void configurarEventos() {
        painelTopo.getBtnBuscar().addActionListener(e -> buscarSeries());

        painelTopo.getBtnSeriesAssistidas().addActionListener(e -> mostrarAssistidas());
        painelTopo.getBtnSeriesFavoritas().addActionListener(e -> mostrarFavoritas());
        painelTopo.getBtnSeriesDesejoAssistir().addActionListener(e -> mostrarDesejoAssistir());

        painelTopo.getComboOrdenacao().addActionListener(e -> ordenarListaAtual());

        painelImagemSerie.getBtnAssistida().addActionListener(e -> alternarAssistida());
        painelImagemSerie.getBtnFavorita().addActionListener(e -> alternarFavorita());
        painelImagemSerie.getBtnDesejada().addActionListener(e -> alternarDesejada());
    }

    private void buscarSeries() {
        String nome = painelTopo.getNomePesquisado();

        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Digite o nome de uma série.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        try {
            List<Serie> seriesEncontradas = pesquisaController.buscaSerie(nome);

            if (seriesEncontradas == null) {
                seriesEncontradas = new ArrayList<>();
            }

            modoAtual = "pesquisa";
            listaAtual = seriesEncontradas;

            painelTopo.getComboOrdenacao().setSelectedIndex(0);

            limparSelecao();

            painelResultados.setTitulo("Resultados da pesquisa");
            painelResultados.exibirSeries(listaAtual, this::selecionarSerie);

        } catch (RuntimeException e) {
            e.printStackTrace();

            Throwable causa = e.getCause();

            JOptionPane.showMessageDialog(
                    this,
                    "Erro real: " + (causa != null ? causa.getMessage() : e.getMessage()),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void selecionarSerie(Serie serie) {
        serieSelecionada = serie;

        painelDetalhesSerie.exibirSerie(serie);
        painelImagemSerie.exibirImagemSerie(serie);

        atualizarTextoBotoes();

        System.out.println("Série selecionada: " + serie.getName());
    }

    private void mostrarAssistidas() {
        mostrarLista("assistidas", "Séries assistidas", usuario.getAssistidas());
    }

    private void mostrarFavoritas() {
        mostrarLista("favoritas", "Séries favoritas", usuario.getFavoritos());
    }

    private void mostrarDesejoAssistir() {
        mostrarLista("desejo", "Séries que deseja assistir", usuario.getDesejoAssistir());
    }

    private void mostrarLista(String modo, String titulo, List<Serie> lista) {
        modoAtual = modo;
        listaAtual = lista;

        painelTopo.getComboOrdenacao().setSelectedIndex(0);

        limparSelecao();

        painelResultados.setTitulo(titulo);
        painelResultados.exibirSeries(listaAtual, this::selecionarSerie);
    }

    private void ordenarListaAtual() {
        if (listaAtual == null || listaAtual.isEmpty()) {
            return;
        }

        String opcao = (String) painelTopo.getComboOrdenacao().getSelectedItem();

        if (opcao == null || opcao.equals("Ordenar por...")) {
            return;
        }

        List<Serie> listaOrdenada;

        switch (opcao) {
            case "Nome" -> listaOrdenada = listaController.ordenarPorNome(listaAtual);

            case "Nota geral" -> listaOrdenada = listaController.ordenarPorNota(listaAtual);

            case "Estado" -> listaOrdenada = listaController.ordenarPorEstado(listaAtual);

            case "Data de estreia" -> listaOrdenada = listaController.ordenarPorDataEstreia(listaAtual);

            default -> {
                return;
            }
        }

        listaAtual = listaOrdenada;

        limparSelecao();

        painelResultados.exibirSeries(listaAtual, this::selecionarSerie);
    }

    private void alternarAssistida() {
        if (serieSelecionada == null) {
            JOptionPane.showMessageDialog(this, "Selecione uma série primeiro.");
            return;
        }

        if (usuario.getAssistidas().contains(serieSelecionada)) {
            listaController.removeSerieAssistida(usuario, serieSelecionada);
        } else {
            listaController.adicionaSerieAssistida(usuario, serieSelecionada);
        }

        salvarUsuario();
        atualizarTextoBotoes();
        atualizarListaAtualSemLimparSelecao();
    }

    private void alternarFavorita() {
        if (serieSelecionada == null) {
            JOptionPane.showMessageDialog(this, "Selecione uma série primeiro.");
            return;
        }

        if (usuario.getFavoritos().contains(serieSelecionada)) {
            listaController.removeFavorito(usuario, serieSelecionada);
        } else {
            listaController.adicionaFavorito(usuario, serieSelecionada);
        }

        salvarUsuario();
        atualizarTextoBotoes();
        atualizarListaAtualSemLimparSelecao();
    }

    private void alternarDesejada() {
        if (serieSelecionada == null) {
            JOptionPane.showMessageDialog(this, "Selecione uma série primeiro.");
            return;
        }

        if (usuario.getDesejoAssistir().contains(serieSelecionada)) {
            listaController.removeSerieDesejada(usuario, serieSelecionada);
        } else {
            listaController.adicionaSerieDesejada(usuario, serieSelecionada);
        }

        salvarUsuario();
        atualizarTextoBotoes();
        atualizarListaAtualSemLimparSelecao();
    }

    private void atualizarTextoBotoes() {
        if (serieSelecionada == null) {
            painelImagemSerie.atualizarTextoBotoes(false, false, false);
            return;
        }

        boolean estaAssistida = usuario.getAssistidas().contains(serieSelecionada);
        boolean estaFavorita = usuario.getFavoritos().contains(serieSelecionada);
        boolean estaDesejada = usuario.getDesejoAssistir().contains(serieSelecionada);

        painelImagemSerie.atualizarTextoBotoes(
                estaAssistida,
                estaFavorita,
                estaDesejada
        );
    }

    private void atualizarListaAtualSemLimparSelecao() {
        switch (modoAtual) {
            case "assistidas" -> {
                listaAtual = usuario.getAssistidas();
                painelResultados.setTitulo("Séries assistidas");
                painelResultados.exibirSeries(listaAtual, this::selecionarSerie);
            }

            case "favoritas" -> {
                listaAtual = usuario.getFavoritos();
                painelResultados.setTitulo("Séries favoritas");
                painelResultados.exibirSeries(listaAtual, this::selecionarSerie);
            }

            case "desejo" -> {
                listaAtual = usuario.getDesejoAssistir();
                painelResultados.setTitulo("Séries que deseja assistir");
                painelResultados.exibirSeries(listaAtual, this::selecionarSerie);
            }
        }
    }

    private void limparSelecao() {
        serieSelecionada = null;

        painelDetalhesSerie.limpar();
        painelImagemSerie.limparImagem();
        painelImagemSerie.atualizarTextoBotoes(false, false, false);
    }

    private void salvarUsuario() {
        try {
            userRepository.salvarUsuario(usuario);

        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao salvar usuário.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void fecharPrograma() {
        salvarUsuario();
        dispose();
        System.exit(0);
    }
}