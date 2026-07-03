package br.com.serietracker.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import br.com.serietracker.model.Serie;
import br.com.serietracker.model.Usuario;
import br.com.serietracker.service.JsonService;
import br.com.serietracker.service.TvMazeService;

public class MainFrame extends JFrame {

        // ---------- Paleta de cores ----------
        private static final Color COR_FUNDO = Color.decode("#121212");
        private static final Color COR_PAINEL = Color.decode("#1B1B1B");
        private static final Color COR_PAINEL_CLARO = Color.decode("#232323");
        private static final Color COR_BORDA = Color.decode("#2E2E2E");
        private static final Color COR_ACCENT = Color.decode("#E34A42");
        private static final Color COR_ACCENT_HOVER = Color.decode("#F2655D");
        private static final Color COR_TEXTO = Color.decode("#F2F2F2");
        private static final Color COR_TEXTO_SECUNDARIO = Color.decode("#9A9A9A");

        // ---------- Fontes ----------
        private static final Font FONTE_TITULO = new Font("Segoe UI", Font.BOLD, 22);
        private static final Font FONTE_SUBTITULO = new Font("Segoe UI", Font.PLAIN, 12);
        private static final Font FONTE_LABEL = new Font("Segoe UI", Font.BOLD, 12);
        private static final Font FONTE_TEXTO = new Font("Segoe UI", Font.PLAIN, 13);
        private static final Font FONTE_BOTAO = new Font("Segoe UI", Font.BOLD, 12);

        private Usuario usuario;

        private JsonService jsonService;

        private JTextField txtBusca;
        private JButton btnBuscar;

        private DefaultListModel<Serie> modeloLista;
        private JList<Serie> listaResultados;

        private TvMazeService service;
        private JTextArea painelDetalhes;
        private JButton btnFavorito;

        private DefaultListModel<Serie> modeloFavoritos;
        private JList<Serie> listaFavoritos;

        private JButton btnAssistida;

        private DefaultListModel<Serie> modeloAssistidas;
        private JList<Serie> listaAssistidas;

        private JButton btnDesejoAssistir;

        private DefaultListModel<Serie> modeloDesejo;
        private JList<Serie> listaDesejo;
        private JButton btnRemover;

        private JComboBox<String> comboOrdenacao;

        private JLabel lblSaudacao;
        private JLabel lblStatus;

        public MainFrame() {


        // mac
                try {
                        javax.swing.UIManager.setLookAndFeel(
                                        javax.swing.UIManager.getCrossPlatformLookAndFeelClassName());
                } catch (Exception e) {
                        e.printStackTrace();
                }

                aplicarTemaGlobal();

                jsonService = new JsonService();

                String nome = JOptionPane.showInputDialog(
                                null,
                                "Digite seu usuário:");

                if (nome == null || nome.isBlank()) {
                        System.exit(0);
                }

                usuario = jsonService.carregar(nome);

                if (usuario == null) {

                        int resposta = JOptionPane.showConfirmDialog(
                                        null,
                                        "Usuário não encontrado.\nDeseja criar uma conta?",
                                        "Novo Usuário",
                                        JOptionPane.YES_NO_OPTION);

                        if (resposta == JOptionPane.YES_OPTION) {

                                usuario = new Usuario(nome);

                                jsonService.salvar(usuario);

                        } else {

                                System.exit(0);
                        }
                }

                service = new TvMazeService();

                configurarJanela();

                criarComponentes();

                configurarEventos();

                carregarDadosUsuario();

                setVisible(true);
        }

        // ---------- Seguir o tema ----------
        private void aplicarTemaGlobal() {

                UIManager.put("TabbedPane.selected", COR_PAINEL_CLARO);
                UIManager.put("TabbedPane.contentAreaColor", COR_PAINEL);
                UIManager.put("TabbedPane.borderHightlightColor", COR_BORDA);
                UIManager.put("TabbedPane.darkShadow", COR_BORDA);
                UIManager.put("TabbedPane.light", COR_BORDA);
                UIManager.put("TabbedPane.highlight", COR_BORDA);
                UIManager.put("TabbedPane.unselectedBackground", COR_PAINEL);
                UIManager.put("ToolTip.background", COR_PAINEL_CLARO);
                UIManager.put("ToolTip.foreground", COR_TEXTO);
                UIManager.put("OptionPane.background", COR_PAINEL);
                UIManager.put("Panel.background", COR_PAINEL);
        }

        private void configurarJanela() {

                setTitle(
                                "Series Tracker - " +
                                                usuario.getApelido());

                setSize(1240, 760);

                setLocationRelativeTo(null);

                setDefaultCloseOperation(
                                JFrame.EXIT_ON_CLOSE);

                setLayout(
                                new BorderLayout());

                getContentPane().setBackground(COR_FUNDO);
        }

        private void criarModelos() {

                modeloLista = new DefaultListModel<>();

                modeloFavoritos = new DefaultListModel<>();

                modeloAssistidas = new DefaultListModel<>();

                modeloDesejo = new DefaultListModel<>();
        }

        private void criarComponentes() {

                criarModelos();

                txtBusca = new JTextField(20);

                btnBuscar = new JButton("Buscar");

                btnFavorito = new JButton("★ Favoritar");

                btnAssistida = new JButton("✓ Assistida");

                btnDesejoAssistir = new JButton("+ Desejo Assistir");

                btnRemover = new JButton("✕ Remover");

                listaResultados = new JList<>(modeloLista);

                listaFavoritos = new JList<>(modeloFavoritos);

                listaAssistidas = new JList<>(modeloAssistidas);

                listaDesejo = new JList<>(modeloDesejo);

                comboOrdenacao = new JComboBox<>();

                comboOrdenacao.addItem("Nome");
                comboOrdenacao.addItem("Nota");
                comboOrdenacao.addItem("Estado");
                comboOrdenacao.addItem("Estreia");

                painelDetalhes = new JTextArea();

                painelDetalhes.setEditable(false);
                painelDetalhes.setLineWrap(true);
                painelDetalhes.setWrapStyleWord(true);

                painelDetalhes.setBackground(COR_PAINEL);
                painelDetalhes.setForeground(COR_TEXTO);

                painelDetalhes.setFont(new Font("Segoe UI", Font.PLAIN, 14));

                lblSaudacao = new JLabel();
                lblStatus = new JLabel(" ");

                aplicarTema();

                montarLayout();

                exibirDetalhes(null);
        }

        private void aplicarTema() {

                txtBusca.setBackground(COR_PAINEL_CLARO);
                txtBusca.setForeground(COR_TEXTO);
                txtBusca.setCaretColor(COR_TEXTO);
                txtBusca.setFont(FONTE_TEXTO);
                txtBusca.setBorder(new javax.swing.border.CompoundBorder(
                                new LineBorder(COR_BORDA, 1, true),
                                new EmptyBorder(6, 10, 6, 10)));

                JButton[] botoesPrincipais = { btnBuscar };
                for (JButton b : botoesPrincipais) {
                        estilizarBotao(b, COR_ACCENT, COR_ACCENT_HOVER);
                }

                JButton[] botoesSecundarios = { btnFavorito, btnAssistida, btnDesejoAssistir, btnRemover };
                for (JButton b : botoesSecundarios) {
                        estilizarBotao(b, COR_PAINEL_CLARO, COR_BORDA);
                }

                JList<?>[] listas = {
                                listaResultados,
                                listaFavoritos,
                                listaAssistidas,
                                listaDesejo
                };

                for (JList<?> lista : listas) {

                        lista.setBackground(COR_PAINEL);
                        lista.setForeground(COR_TEXTO);

                        lista.setSelectionBackground(COR_ACCENT);
                        lista.setSelectionForeground(Color.WHITE);

                        lista.setFixedCellHeight(48);

                        @SuppressWarnings("unchecked")
                        JList<Serie> listaSerie = (JList<Serie>) lista;
                        listaSerie.setCellRenderer(new SerieCellRenderer());
                }

                comboOrdenacao.setBackground(COR_PAINEL_CLARO);
                comboOrdenacao.setForeground(COR_TEXTO);
                comboOrdenacao.setFont(FONTE_TEXTO);
                comboOrdenacao.setFocusable(false);

                painelDetalhes.setBackground(COR_PAINEL);
                painelDetalhes.setBorder(new EmptyBorder(16, 18, 16, 18));

                lblSaudacao.setForeground(COR_TEXTO_SECUNDARIO);
                lblSaudacao.setFont(FONTE_SUBTITULO);

                lblStatus.setForeground(COR_TEXTO_SECUNDARIO);
                lblStatus.setFont(FONTE_SUBTITULO);
        }

        // ---------- Botão ----------

        private void estilizarBotao(JButton botao, Color corNormal, Color corHover) {

                botao.setFont(FONTE_BOTAO);
                botao.setForeground(Color.WHITE);
                botao.setBackground(corNormal);
                botao.setFocusPainted(false);
                botao.setBorderPainted(false);
                botao.setContentAreaFilled(false);
                botao.setOpaque(false);
                botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                botao.setBorder(new EmptyBorder(8, 16, 8, 16));

                botao.putClientProperty("corNormal", corNormal);
                botao.putClientProperty("corHover", corHover);
                botao.putClientProperty("corAtual", corNormal);

                botao.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseEntered(MouseEvent e) {
                                botao.putClientProperty("corAtual", corHover);
                                botao.repaint();
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                                botao.putClientProperty("corAtual", corNormal);
                                botao.repaint();
                        }
                });

                // Arredondar
                botao.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
                        @Override
                        public void paint(Graphics g, javax.swing.JComponent c) {
                                Graphics2D g2 = (Graphics2D) g.create();
                                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                                Color cor = (Color) ((JButton) c).getClientProperty("corAtual");
                                g2.setColor(cor != null ? cor : corNormal);
                                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 10, 10);
                                g2.dispose();
                                super.paint(g, c);
                        }
                });
        }

        private void montarLayout() {

                // ---------- Cabeçalho ----------
                JPanel cabecalho = new JPanel(new BorderLayout());
                cabecalho.setBackground(COR_FUNDO);
                cabecalho.setBorder(new EmptyBorder(16, 20, 8, 20));

                JLabel lblTitulo = new JLabel("Series Tracker");
                lblTitulo.setFont(FONTE_TITULO);
                lblTitulo.setForeground(COR_ACCENT);

                cabecalho.add(lblTitulo, BorderLayout.WEST);
                cabecalho.add(lblSaudacao, BorderLayout.EAST);

                // ---------- Linha de busca ----------
                JPanel linhaBusca = new JPanel();
                linhaBusca.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 6));
                linhaBusca.setBackground(COR_FUNDO);
                linhaBusca.setBorder(new EmptyBorder(0, 20, 14, 20));

                JLabel lblSerie = new JLabel("Série:");
                lblSerie.setForeground(COR_TEXTO);
                lblSerie.setFont(FONTE_LABEL);

                JLabel lblOrdenar = new JLabel("Ordenar:");
                lblOrdenar.setForeground(COR_TEXTO);
                lblOrdenar.setFont(FONTE_LABEL);

                linhaBusca.add(lblSerie);
                linhaBusca.add(txtBusca);
                linhaBusca.add(btnBuscar);

                linhaBusca.add(criarSeparadorVertical());

                linhaBusca.add(lblOrdenar);
                linhaBusca.add(comboOrdenacao);

                linhaBusca.add(criarSeparadorVertical());

                linhaBusca.add(btnFavorito);
                linhaBusca.add(btnAssistida);
                linhaBusca.add(btnDesejoAssistir);
                linhaBusca.add(btnRemover);

                JPanel topo = new JPanel(new BorderLayout());
                topo.setBackground(COR_FUNDO);
                topo.add(cabecalho, BorderLayout.NORTH);
                topo.add(linhaBusca, BorderLayout.SOUTH);
                topo.setBorder(new javax.swing.border.MatteBorder(0, 0, 1, 0, COR_BORDA));

                add(topo, BorderLayout.NORTH);

                // ---------- Painel lateral (listas do usuário) ----------
                JTabbedPane abas = new JTabbedPane();

                abas.addChangeListener(e -> {

                listaFavoritos.clearSelection();
                listaAssistidas.clearSelection();
                listaDesejo.clearSelection();

                exibirDetalhes(null);
                });

                abas.setFont(FONTE_LABEL);
                abas.setBackground(COR_PAINEL);
                abas.setForeground(COR_TEXTO);

                abas.add("Favoritos", new JScrollPane(listaFavoritos));
                abas.add("Assistidas", new JScrollPane(listaAssistidas));
                abas.add("Desejo Assistir", new JScrollPane(listaDesejo));

                estilizarScroll(abas);

                JPanel painelLateral = new JPanel(new BorderLayout());
                painelLateral.setBackground(COR_PAINEL);
                painelLateral.setPreferredSize(new java.awt.Dimension(330, 0));
                painelLateral.setBorder(new javax.swing.border.MatteBorder(0, 0, 0, 1, COR_BORDA));

                JLabel lblMinhasListas = new JLabel("MINHAS LISTAS");
                lblMinhasListas.setForeground(COR_TEXTO_SECUNDARIO);
                lblMinhasListas.setFont(FONTE_LABEL);
                lblMinhasListas.setBorder(new EmptyBorder(12, 14, 8, 14));

                painelLateral.add(lblMinhasListas, BorderLayout.NORTH);
                painelLateral.add(abas, BorderLayout.CENTER);

                add(painelLateral, BorderLayout.WEST);

                // ---------- Centro: resultados da busca + detalhes ----------
                JPanel painelResultados = new JPanel(new BorderLayout());
                painelResultados.setBackground(COR_PAINEL);

                JLabel lblResultados = new JLabel("RESULTADOS DA BUSCA");
                lblResultados.setForeground(COR_TEXTO_SECUNDARIO);
                lblResultados.setFont(FONTE_LABEL);
                lblResultados.setBorder(new EmptyBorder(12, 16, 8, 16));

                JScrollPane scrollResultados = new JScrollPane(listaResultados);
                estilizarScroll(scrollResultados);

                painelResultados.add(lblResultados, BorderLayout.NORTH);
                painelResultados.add(scrollResultados, BorderLayout.CENTER);

                JPanel painelDetalhesContainer = new JPanel(new BorderLayout());
                painelDetalhesContainer.setBackground(COR_PAINEL);

                JLabel lblDetalhesTitulo = new JLabel("DETALHES DA SÉRIE");
                lblDetalhesTitulo.setForeground(COR_TEXTO_SECUNDARIO);
                lblDetalhesTitulo.setFont(FONTE_LABEL);
                lblDetalhesTitulo.setBorder(new EmptyBorder(12, 16, 0, 16));

                JScrollPane scrollDetalhes = new JScrollPane(painelDetalhes);
                scrollDetalhes.setBorder(new EmptyBorder(0, 0, 0, 0));
                estilizarScroll(scrollDetalhes);

                painelDetalhesContainer.add(lblDetalhesTitulo, BorderLayout.NORTH);
                painelDetalhesContainer.add(scrollDetalhes, BorderLayout.CENTER);

                JSplitPane splitCentral = new JSplitPane(
                                JSplitPane.VERTICAL_SPLIT,
                                painelResultados,
                                painelDetalhesContainer);

                splitCentral.setResizeWeight(0.62);
                splitCentral.setDividerSize(6);
                splitCentral.setBorder(null);
                splitCentral.setBackground(COR_BORDA);

                add(splitCentral, BorderLayout.CENTER);

                // ---------- Barra de status ----------
                JPanel barraStatus = new JPanel(new BorderLayout());
                barraStatus.setBackground(COR_FUNDO);
                barraStatus.setBorder(new EmptyBorder(6, 20, 8, 20));
                barraStatus.add(lblStatus, BorderLayout.WEST);

                add(barraStatus, BorderLayout.SOUTH);
        }

        private JLabel criarSeparadorVertical() {
                JLabel separador = new JLabel("│");
                separador.setForeground(COR_BORDA);
                return separador;
        }

        private void estilizarScroll(JScrollPane scroll) {
                scroll.setBorder(new EmptyBorder(0, 0, 0, 0));
                scroll.getViewport().setBackground(COR_PAINEL);
        }

        private void estilizarScroll(JTabbedPane abas) {
                for (int i = 0; i < abas.getTabCount(); i++) {
                        Component comp = abas.getComponentAt(i);
                        if (comp instanceof JScrollPane) {
                                estilizarScroll((JScrollPane) comp);
                        }
                }
        }

        private void carregarDadosUsuario() {

                for (Serie serie : usuario.getFavoritos()) {
                        modeloFavoritos.addElement(serie);
                }

                for (Serie serie : usuario.getAssistidas()) {
                        modeloAssistidas.addElement(serie);
                }

                for (Serie serie : usuario.getDesejoAssistir()) {
                        modeloDesejo.addElement(serie);
                }

                lblSaudacao.setText("Bem-vindo(a), " + usuario.getApelido());
        }

        private void configurarEventos() {

                btnBuscar.addActionListener(e -> buscar());

                txtBusca.addActionListener(e -> buscar());

                comboOrdenacao.addActionListener(e -> ordenarListas());

                listaResultados.addListSelectionListener(e -> {
                        if (!e.getValueIsAdjusting()) {
                                exibirDetalhes(listaResultados.getSelectedValue());
                        }
                });

                listaFavoritos.addListSelectionListener(e -> {
                        if (!e.getValueIsAdjusting() && listaFavoritos.getSelectedValue() != null) {
                                exibirDetalhes(listaFavoritos.getSelectedValue());
                        }
                });

                listaAssistidas.addListSelectionListener(e -> {
                        if (!e.getValueIsAdjusting() && listaAssistidas.getSelectedValue() != null) {
                                exibirDetalhes(listaAssistidas.getSelectedValue());
                        }
                });

                listaDesejo.addListSelectionListener(e -> {
                        if (!e.getValueIsAdjusting() && listaDesejo.getSelectedValue() != null) {
                                exibirDetalhes(listaDesejo.getSelectedValue());
                        }
                });

                btnFavorito.addActionListener(e -> {

                        Serie serie = listaResultados.getSelectedValue();

                        if (serie == null) {
                                definirStatus("Selecione uma série na lista de resultados.");
                                return;
                        }

                        if (!modeloFavoritos.contains(serie)) {

                                modeloFavoritos.addElement(serie);

                                usuario.getFavoritos().add(serie);

                                jsonService.salvar(usuario);

                                definirStatus("\"" + serie.getNome() + "\" adicionada aos favoritos.");
                        } else {
                                definirStatus("\"" + serie.getNome() + "\" já está nos favoritos.");
                        }
                });

                btnAssistida.addActionListener(e -> {

                        Serie serie = listaResultados.getSelectedValue();

                        if (serie == null) {
                                definirStatus("Selecione uma série na lista de resultados.");
                                return;
                        }

                        if (!modeloAssistidas.contains(serie)) {

                                modeloAssistidas.addElement(serie);

                                usuario.getAssistidas().add(serie);

                                jsonService.salvar(usuario);

                                definirStatus("\"" + serie.getNome() + "\" marcada como assistida.");
                        } else {
                                definirStatus("\"" + serie.getNome() + "\" já está em assistidas.");
                        }
                });

                btnDesejoAssistir.addActionListener(e -> {

                        Serie serie = listaResultados.getSelectedValue();

                        if (serie == null) {
                                definirStatus("Selecione uma série na lista de resultados.");
                                return;
                        }

                        if (!modeloDesejo.contains(serie)) {

                                modeloDesejo.addElement(serie);

                                usuario.getDesejoAssistir().add(serie);

                                jsonService.salvar(usuario);

                                definirStatus("\"" + serie.getNome() + "\" adicionada à lista de desejos.");
                        } else {
                                definirStatus("\"" + serie.getNome() + "\" já está na lista de desejos.");
                        }
                });

                btnRemover.addActionListener(e -> {

                        Serie serie = listaFavoritos.getSelectedValue();

                        if (serie != null) {

                                modeloFavoritos.removeElement(serie);

                                usuario.getFavoritos().remove(serie);

                                jsonService.salvar(usuario);

                                definirStatus("\"" + serie.getNome() + "\" removida dos favoritos.");

                                return;
                        }

                        serie = listaAssistidas.getSelectedValue();

                        if (serie != null) {

                                modeloAssistidas.removeElement(serie);

                                usuario.getAssistidas().remove(serie);

                                jsonService.salvar(usuario);

                                listaFavoritos.clearSelection();
                                listaAssistidas.clearSelection();
                                listaDesejo.clearSelection();

                                exibirDetalhes(null);

                                definirStatus("\"" + serie.getNome() + "\" removida de assistidas.");

                                return;
                        }

                        serie = listaDesejo.getSelectedValue();

                        if (serie != null) {

                                modeloDesejo.removeElement(serie);

                                usuario.getDesejoAssistir().remove(serie);

                                jsonService.salvar(usuario);

                                definirStatus("\"" + serie.getNome() + "\" removida da lista de desejos.");

                        } else {
                                definirStatus("Selecione um item em uma das suas listas para remover.");
                        }
                });
        }

        private void definirStatus(String mensagem) {
                lblStatus.setText(mensagem);
        }

        private void buscar() {

                try {

                        String nome = txtBusca.getText();

                        if (nome.isBlank()) {

                                JOptionPane.showMessageDialog(
                                                this,
                                                "Digite o nome de uma série.");

                                return;
                        }

                        definirStatus("Buscando \"" + nome + "\"...");

                        modeloLista.clear();

                        List<Serie> series = service.buscarSeries(nome);

                        for (Serie serie : series) {
                                modeloLista.addElement(serie);
                        }

                        definirStatus(series.isEmpty()
                                        ? "Nenhuma série encontrada para \"" + nome + "\"."
                                        : series.size() + " série(s) encontrada(s) para \"" + nome + "\".");

                } catch (Exception e) {

                        JOptionPane.showMessageDialog(
                                        this,
                                        "Erro ao consultar API. Verifique sua conexão com a internet.",
                                        "Erro de comunicação",
                                        JOptionPane.ERROR_MESSAGE);

                        definirStatus("Falha ao consultar a API do TVMaze.");

                        e.printStackTrace();
                }
        }

        private void ordenarListas() {

                String criterio = (String) comboOrdenacao.getSelectedItem();

                ordenarModelo(modeloFavoritos, criterio);

                ordenarModelo(modeloAssistidas, criterio);

                ordenarModelo(modeloDesejo, criterio);
        }

        private void ordenarModelo(DefaultListModel<Serie> modelo, String criterio) {

                List<Serie> lista = java.util.Collections.list(modelo.elements());

                switch (criterio) {

                        case "Nome":
                                lista.sort(java.util.Comparator.comparing(Serie::getNome));
                                break;

                        case "Nota":
                                lista.sort(java.util.Comparator.comparingDouble(Serie::getNota).reversed());
                                break;

                        case "Estado":
                                lista.sort(java.util.Comparator.comparing(Serie::getEstado));
                                break;

                        case "Estreia":
                                lista.sort(java.util.Comparator.comparing(Serie::getEstreia));
                                break;
                }

                modelo.clear();

                for (Serie serie : lista) {
                        modelo.addElement(serie);
                }
        }

        private void exibirDetalhes(Serie serie) {

                if (serie == null) {

                        painelDetalhes.setText(
                                        "Selecione uma série para visualizar os detalhes.");

                        return;
                }

                painelDetalhes.setText(

                                "NOME\n" +
                                                serie.getNome() +

                                                "\n\nIDIOMA\n" +
                                                serie.getIdioma() +

                                                "\n\nGÊNEROS\n" +
                                                String.join(", ", serie.getGeneros()) +

                                                "\n\nNOTA\n" +
                                                serie.getNota() +

                                                "\n\nESTADO\n" +
                                                serie.getEstado() +

                                                "\n\nESTREIA\n" +
                                                serie.getEstreia() +

                                                "\n\nTÉRMINO\n" +
                                                serie.getTermino() +

                                                "\n\nEMISSORA\n" +
                                                serie.getEmissora());

                painelDetalhes.setCaretPosition(0);
        }

        //Lista
        private class SerieCellRenderer extends JPanel implements ListCellRenderer<Serie> {

                private final JLabel lblNome = new JLabel();
                private final JLabel lblInfo = new JLabel();

                SerieCellRenderer() {
                        setOpaque(true);
                        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
                        setBorder(new EmptyBorder(6, 12, 6, 12));

                        lblNome.setFont(FONTE_TEXTO.deriveFont(Font.BOLD));
                        lblInfo.setFont(FONTE_SUBTITULO);

                        add(lblNome);
                        add(lblInfo);
                }

                @Override
                public Component getListCellRendererComponent(
                                JList<? extends Serie> list,
                                Serie value,
                                int index,
                                boolean isSelected,
                                boolean cellHasFocus) {

                        String nome = value != null ? value.getNome() : "";
                        String estado = (value != null && value.getEstado() != null) ? value.getEstado() : "—";
                        String nota = (value != null && value.getNota() > 0) ? String.valueOf(value.getNota()) : "—";

                        lblNome.setText(nome);
                        lblInfo.setText("★ " + nota + "    •    " + estado);

                        if (isSelected) {
                                setBackground(COR_ACCENT);
                                lblNome.setForeground(Color.WHITE);
                                lblInfo.setForeground(Color.WHITE);
                        } else {
                                setBackground(COR_PAINEL);
                                lblNome.setForeground(COR_TEXTO);
                                lblInfo.setForeground(COR_TEXTO_SECUNDARIO);
                        }

                        return this;
                }
        }
}