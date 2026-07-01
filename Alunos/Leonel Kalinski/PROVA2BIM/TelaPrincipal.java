package view;

import model.Serie;
import model.Usuario;
import service.ApiService;
import service.PersistenciaService;
import util.OrdenadorSeries;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class TelaPrincipal extends JFrame {

    private static final Color COR_FUNDO = new Color(245, 246, 250);
    private static final Color COR_DESTAQUE = new Color(64, 110, 220);
    private static final Color COR_FAVORITOS = new Color(214, 69, 99);
    private static final Color COR_ASSISTIDAS = new Color(56, 161, 105);
    private static final Color COR_DESEJA = new Color(66, 133, 196);
    private static final Color COR_CINZA = new Color(120, 120, 130);
    private static final Font FONTE_TITULO = new Font("Segoe UI", Font.BOLD, 16);
    private static final Font FONTE_BASE = new Font("Segoe UI", Font.PLAIN, 13);

    private Usuario usuario;
    private ApiService api;

    private JTextField txtBusca;
    private JTextArea txtDetalhes;

    private DefaultListModel<Serie> modeloFavoritos;
    private DefaultListModel<Serie> modeloAssistidas;
    private DefaultListModel<Serie> modeloDeseja;

    private JList<Serie> listaFavoritos;
    private JList<Serie> listaAssistidas;
    private JList<Serie> listaDeseja;

    private Serie serieAtual;
    private JLabel lblUsuario;

    public TelaPrincipal() {

        aplicarVisual();

        String nome = JOptionPane.showInputDialog(
                null,
                "Digite seu nome ou apelido:"
        );

        if (nome == null || nome.isBlank()) {
            nome = "Convidado";
        }

        usuario = PersistenciaService.carregar(nome);

        if (usuario == null) {
            usuario = new Usuario(nome);
        }

        api = new ApiService();

        configurarJanela();
        criarComponentes();
        carregarListas();
    }

    private void aplicarVisual() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    return;
                }
            }
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }
    }

    private void configurarJanela() {

        setTitle("TV Tracker - " + usuario.getNome());
        setSize(1200, 700);
        setMinimumSize(new Dimension(950, 600));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setBackground(COR_FUNDO);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                PersistenciaService.salvar(usuario);
            }
        });
    }

    private void criarComponentes() {

        setLayout(new BorderLayout(10, 10));
        ((JComponent) getContentPane()).setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel painelBusca = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 8));
        painelBusca.setBackground(Color.WHITE);
        painelBusca.setBorder(new CompoundBorder(
                new LineBorder(new Color(220, 222, 230), 1, true),
                new EmptyBorder(8, 14, 8, 14)));

        lblUsuario = new JLabel("Usuário: " + usuario.getNome());
        lblUsuario.setFont(FONTE_TITULO);
        lblUsuario.setForeground(COR_DESTAQUE);

        JLabel lblSerie = new JLabel("Série:");
        lblSerie.setFont(FONTE_BASE);

        txtBusca = new JTextField(25);
        txtBusca.setFont(FONTE_BASE);
        txtBusca.setBorder(new CompoundBorder(
                new LineBorder(new Color(200, 200, 210)),
                new EmptyBorder(5, 8, 5, 8)));

        JButton btnBuscar = criarBotao("Buscar", COR_DESTAQUE);

        painelBusca.add(lblUsuario);
        painelBusca.add(Box.createHorizontalStrut(24));
        painelBusca.add(lblSerie);
        painelBusca.add(txtBusca);
        painelBusca.add(btnBuscar);

        add(painelBusca, BorderLayout.NORTH);

        txtDetalhes = new JTextArea();
        txtDetalhes.setEditable(false);
        txtDetalhes.setFont(new Font("Consolas", Font.PLAIN, 14));
        txtDetalhes.setLineWrap(true);
        txtDetalhes.setWrapStyleWord(true);
        txtDetalhes.setBackground(Color.WHITE);
        txtDetalhes.setBorder(new EmptyBorder(12, 14, 12, 14));

        JScrollPane scrollDetalhes = new JScrollPane(txtDetalhes);
        scrollDetalhes.setBorder(new TitledBorder(
                new LineBorder(new Color(220, 222, 230), 1, true),
                "  Detalhes da Série  ", TitledBorder.LEFT, TitledBorder.TOP, FONTE_TITULO));

        JPanel painelListas = new JPanel(new GridLayout(1, 3, 10, 0));
        painelListas.setBackground(COR_FUNDO);

        modeloFavoritos = new DefaultListModel<>();
        modeloAssistidas = new DefaultListModel<>();
        modeloDeseja = new DefaultListModel<>();

        listaFavoritos = new JList<>(modeloFavoritos);
        listaAssistidas = new JList<>(modeloAssistidas);
        listaDeseja = new JList<>(modeloDeseja);

        painelListas.add(criarPainelLista(
                "Favoritos", listaFavoritos, modeloFavoritos,
                usuario.getFavoritos(), COR_FAVORITOS, ""));

        painelListas.add(criarPainelLista(
                "Assistidas", listaAssistidas, modeloAssistidas,
                usuario.getAssistidas(), COR_ASSISTIDAS, ""));

        painelListas.add(criarPainelLista(
                "Deseja Assistir", listaDeseja, modeloDeseja,
                usuario.getDesejaAssistir(), COR_DESEJA, ""));

        JSplitPane splitCentro = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT, scrollDetalhes, painelListas);
        splitCentro.setResizeWeight(0.45);
        splitCentro.setDividerSize(8);
        splitCentro.setContinuousLayout(true);
        splitCentro.setBorder(null);

        add(splitCentro, BorderLayout.CENTER);

        JPanel painelAcoes = new JPanel();
        painelAcoes.setLayout(new BoxLayout(painelAcoes, BoxLayout.Y_AXIS));
        painelAcoes.setBackground(COR_FUNDO);
        painelAcoes.setBorder(new EmptyBorder(0, 0, 0, 10));

        JButton btnFavorito = criarBotao("Favoritar", COR_FAVORITOS);
        JButton btnAssistida = criarBotao("Assistida", COR_ASSISTIDAS);
        JButton btnDeseja = criarBotao("Deseja Assistir", COR_DESEJA);
        JButton btnTrocarUsuario = criarBotao("Sair do Usuário", COR_CINZA);

        for (JButton botao : new JButton[]{btnFavorito, btnAssistida, btnDeseja}) {
            botao.setAlignmentX(Component.LEFT_ALIGNMENT);
            botao.setMaximumSize(new Dimension(180, 36));
            painelAcoes.add(botao);
            painelAcoes.add(Box.createVerticalStrut(8));
        }

        painelAcoes.add(Box.createVerticalGlue());

        btnTrocarUsuario.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnTrocarUsuario.setMaximumSize(new Dimension(180, 36));
        painelAcoes.add(btnTrocarUsuario);

        add(painelAcoes, BorderLayout.WEST);

        btnBuscar.addActionListener(e -> buscarSerie());
        btnFavorito.addActionListener(e -> adicionarFavorito());
        btnAssistida.addActionListener(e -> adicionarAssistida());
        btnDeseja.addActionListener(e -> adicionarDeseja());
        btnTrocarUsuario.addActionListener(e -> trocarUsuario());
    }

    private JButton criarBotao(String texto, Color cor) {

        JButton botao = new JButton(texto);
        botao.setFont(FONTE_BASE);
        botao.setFocusPainted(false);
        botao.setBackground(cor);
        botao.setForeground(Color.WHITE);
        botao.setBorder(new EmptyBorder(8, 14, 8, 14));
        botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return botao;
    }

    private JButton criarBotaoPequeno(String texto) {

        JButton botao = new JButton(texto);
        botao.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        botao.setMargin(new Insets(2, 6, 2, 6));
        botao.setFocusPainted(false);
        botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return botao;
    }

    private JPanel criarPainelLista(
            String titulo,
            JList<Serie> lista,
            DefaultListModel<Serie> modelo,
            List<Serie> origem,
            Color cor,
            String icone) {

        JPanel painel = new JPanel(new BorderLayout(0, 6));
        painel.setBackground(Color.WHITE);
        painel.setBorder(new TitledBorder(
                new LineBorder(cor, 1, true),
                "  " + icone + " " + titulo + "  ",
                TitledBorder.LEFT, TitledBorder.TOP, FONTE_TITULO, cor));

        lista.setFont(FONTE_BASE);
        lista.setCellRenderer(new SerieCellRenderer());
        lista.setFixedCellHeight(28);
        lista.setSelectionBackground(cor);
        lista.setSelectionForeground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(lista);
        scroll.setBorder(null);
        painel.add(scroll, BorderLayout.CENTER);

        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 6));
        botoes.setBackground(Color.WHITE);

        JButton remover = criarBotaoPequeno("Remover");
        JButton ordenarNome = criarBotaoPequeno("Nome");
        JButton ordenarNota = criarBotaoPequeno("Nota");
        JButton ordenarEstado = criarBotaoPequeno("Estado");
        JButton ordenarData = criarBotaoPequeno("Estreia");

        botoes.add(remover);
        botoes.add(ordenarNome);
        botoes.add(ordenarNota);
        botoes.add(ordenarEstado);
        botoes.add(ordenarData);

        painel.add(botoes, BorderLayout.SOUTH);

        remover.addActionListener(e -> {

            Serie selecionada = lista.getSelectedValue();

            if (selecionada != null) {

                origem.remove(selecionada);

                modelo.removeElement(selecionada);
            }
        });

        ordenarNome.addActionListener(
                e -> atualizarModelo(
                        modelo,
                        OrdenadorSeries.porNome(origem)));

        ordenarNota.addActionListener(
                e -> atualizarModelo(
                        modelo,
                        OrdenadorSeries.porNota(origem)));

        ordenarEstado.addActionListener(
                e -> atualizarModelo(
                        modelo,
                        OrdenadorSeries.porEstado(origem)));

        ordenarData.addActionListener(
                e -> atualizarModelo(
                        modelo,
                        OrdenadorSeries.porEstreia(origem)));

        return painel;
    }

    private void atualizarModelo(
            DefaultListModel<Serie> modelo,
            List<Serie> lista) {

        modelo.clear();

        lista.forEach(modelo::addElement);
    }

    private void buscarSerie() {

        if (txtBusca.getText() == null || txtBusca.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Digite o nome de uma série para buscar.");
            return;
        }

        try {

            serieAtual =
                    api.buscarSerie(
                            txtBusca.getText());

            txtDetalhes.setText(
                    "Nome: " +
                            serieAtual.getNome()
                            + "\n\nIdioma: "
                            + serieAtual.getIdioma()
                            + "\n\nGêneros: "
                            + serieAtual.getGeneros()
                            + "\n\nNota: "
                            + serieAtual.getNota()
                            + "\n\nEstado: "
                            + serieAtual.getEstado()
                            + "\n\nEstreia: "
                            + serieAtual.getEstreia()
                            + "\n\nTérmino: "
                            + serieAtual.getTermino()
                            + "\n\nEmissora: "
                            + serieAtual.getEmissora());

            txtDetalhes.setCaretPosition(0);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Série não encontrada."
            );
        }
    }

    private void adicionarFavorito() {

        if(serieAtual == null)
            return;

        boolean existe =
                usuario.getFavoritos()
                        .stream()
                        .anyMatch(s ->
                                s.getId()
                                        ==
                                        serieAtual.getId());

        if(!existe) {

            usuario.getFavoritos()
                    .add(serieAtual);

            modeloFavoritos
                    .addElement(serieAtual);

            listaFavoritos.setSelectedValue(serieAtual, true);
        }
    }

    private void adicionarAssistida() {

        if(serieAtual == null)
            return;

        boolean existe =
                usuario.getAssistidas()
                        .stream()
                        .anyMatch(s ->
                                s.getId()
                                        ==
                                        serieAtual.getId());

        if(!existe) {

            usuario.getAssistidas()
                    .add(serieAtual);

            modeloAssistidas
                    .addElement(serieAtual);

            listaAssistidas.setSelectedValue(serieAtual, true);
        }
    }

    private void adicionarDeseja() {

        if(serieAtual == null)
            return;

        boolean existe =
                usuario.getDesejaAssistir()
                        .stream()
                        .anyMatch(s ->
                                s.getId()
                                        ==
                                        serieAtual.getId());

        if(!existe) {

            usuario.getDesejaAssistir()
                    .add(serieAtual);

            modeloDeseja
                    .addElement(serieAtual);

            listaDeseja.setSelectedValue(serieAtual, true);
        }
    }

    private void carregarListas() {

        usuario.getFavoritos()
                .forEach(
                        modeloFavoritos::addElement);

        usuario.getAssistidas()
                .forEach(
                        modeloAssistidas::addElement);

        usuario.getDesejaAssistir()
                .forEach(
                        modeloDeseja::addElement);
    }

    private void trocarUsuario() {

        PersistenciaService.salvar(usuario);

        String nome = JOptionPane.showInputDialog(
                this,
                "Digite o nome do usuário:"
        );

        if (nome == null || nome.isBlank()) {
            return;
        }

        nome = nome.trim();

        usuario = PersistenciaService.carregar(nome);

        if (usuario == null) {
            usuario = new Usuario(nome);
        }

        atualizarInterface();
    }

    private void atualizarInterface() {

        setTitle("TV Tracker - " + usuario.getNome());

        lblUsuario.setText(
                "Usuário: " + usuario.getNome()
        );

        modeloFavoritos.clear();
        modeloAssistidas.clear();
        modeloDeseja.clear();

        usuario.getFavoritos()
                .forEach(modeloFavoritos::addElement);

        usuario.getAssistidas()
                .forEach(modeloAssistidas::addElement);

        usuario.getDesejaAssistir()
                .forEach(modeloDeseja::addElement);

        txtDetalhes.setText("");
        txtBusca.setText("");

        serieAtual = null;
    }

   
    private static class SerieCellRenderer extends JPanel implements ListCellRenderer<Serie> {

        private final JLabel lblNome = new JLabel();
        private final JLabel lblInfo = new JLabel();

        SerieCellRenderer() {
            setLayout(new BorderLayout(8, 0));
            setBorder(new EmptyBorder(4, 10, 4, 10));
            setOpaque(true);

            lblNome.setFont(FONTE_BASE);
            lblInfo.setFont(new Font("Segoe UI", Font.PLAIN, 11));

            add(lblNome, BorderLayout.WEST);
            add(lblInfo, BorderLayout.EAST);
        }

        @Override
        public Component getListCellRendererComponent(
                JList<? extends Serie> list,
                Serie serie,
                int index,
                boolean isSelected,
                boolean cellHasFocus) {

            if (serie != null) {
                lblNome.setText(serie.getNome());
                lblInfo.setText("★ " + serie.getNota() + "   " + serie.getEstado());
            } else {
                lblNome.setText("");
                lblInfo.setText("");
            }

            if (isSelected) {
                setBackground(list.getSelectionBackground());
                lblNome.setForeground(list.getSelectionForeground());
                lblInfo.setForeground(list.getSelectionForeground());
            } else {
                setBackground(index % 2 == 0 ? Color.WHITE : new Color(248, 248, 252));
                lblNome.setForeground(Color.DARK_GRAY);
                lblInfo.setForeground(COR_CINZA);
            }

            return this;
        }
    }
}