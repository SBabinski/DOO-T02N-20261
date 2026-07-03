package tela;

import java.awt.*;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import modelo.CriterioOrdenacao;
import modelo.PerfilUsuario;
import modelo.Serie;
import servico.OrdenadorSeries;
import servico.ServicoPerfil;

public class TelaLista extends JPanel {

    private final String icone;
    private final String titulo;
    private final Function<PerfilUsuario, List<Serie>> obterLista;
    private final BiConsumer<PerfilUsuario, Serie> removerSerie;
    private final PerfilUsuario perfil;
    private final ServicoPerfil servicoPerfil;

    private final JPanel painelCards;
    private final JComboBox<CriterioOrdenacao> comboOrdenacao;
    private final JLabel lblContagem;

    public TelaLista(String icone,
                     String titulo,
                     Function<PerfilUsuario, List<Serie>> obterLista,
                     BiConsumer<PerfilUsuario, Serie> removerSerie,
                     PerfilUsuario perfil,
                     ServicoPerfil servicoPerfil) {
        this.icone         = icone;
        this.titulo        = titulo;
        this.obterLista    = obterLista;
        this.removerSerie  = removerSerie;
        this.perfil        = perfil;
        this.servicoPerfil = servicoPerfil;

        setLayout(new BorderLayout(0, 0));
        setBackground(TemaVisual.FUNDO_BASE);

        JPanel cabecalho = new JPanel(new BorderLayout(12, 0));
        cabecalho.setBackground(TemaVisual.FUNDO_CABECALHO);
        cabecalho.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, TemaVisual.BORDA),
                new EmptyBorder(18, 24, 14, 24)));

        JPanel linhaTitulo = new JPanel(new BorderLayout());
        linhaTitulo.setOpaque(false);

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(TemaVisual.FONTE_TITULO);
        lblTitulo.setForeground(TemaVisual.TEXTO_PRINCIPAL);
        linhaTitulo.add(lblTitulo, BorderLayout.WEST);

        lblContagem = new JLabel("0 séries");
        lblContagem.setFont(TemaVisual.FONTE_PEQUENA);
        lblContagem.setForeground(TemaVisual.TEXTO_DISCRETO);
        lblContagem.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(TemaVisual.BORDA, 1),
                new EmptyBorder(3, 10, 3, 10)));
        JPanel painelContagem = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        painelContagem.setOpaque(false);
        painelContagem.add(lblContagem);
        linhaTitulo.add(painelContagem, BorderLayout.EAST);
        cabecalho.add(linhaTitulo, BorderLayout.NORTH);

        JPanel linhaOrdenacao = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        linhaOrdenacao.setOpaque(false);
        linhaOrdenacao.setBorder(new EmptyBorder(10, 0, 0, 0));
        linhaOrdenacao.add(ComponentesVisuais.labelSecao("Ordenar por"));
        comboOrdenacao = ComponentesVisuais.comboEscuro(CriterioOrdenacao.values());
        comboOrdenacao.addActionListener(e -> atualizar());
        linhaOrdenacao.add(comboOrdenacao);
        cabecalho.add(linhaOrdenacao, BorderLayout.CENTER);

        add(cabecalho, BorderLayout.NORTH);

        painelCards = new JPanel();
        painelCards.setLayout(new BoxLayout(painelCards, BoxLayout.Y_AXIS));
        painelCards.setBackground(TemaVisual.FUNDO_BASE);
        painelCards.setBorder(new EmptyBorder(14, 18, 18, 18));

        JScrollPane scroll = ComponentesVisuais.scrollEscuro(painelCards);
        add(scroll, BorderLayout.CENTER);

        atualizar();
    }

    public void atualizar() {
        painelCards.removeAll();
        List<Serie> series = obterLista.apply(perfil);
        CriterioOrdenacao criterio = (CriterioOrdenacao) comboOrdenacao.getSelectedItem();
        List<Serie> ordenadas = OrdenadorSeries.ordenar(series, criterio != null ? criterio : CriterioOrdenacao.NOME);

        int total = ordenadas.size();
        lblContagem.setText(total + (total == 1 ? " série" : " séries"));

        if (ordenadas.isEmpty()) {
            painelCards.add(montarEstadoVazio());
        } else {
            for (Serie serie : ordenadas) {
                painelCards.add(montarLinhaDeSerie(serie));
                painelCards.add(Box.createVerticalStrut(8));
            }
        }
        painelCards.revalidate();
        painelCards.repaint();
    }

    private JPanel montarLinhaDeSerie(Serie serie) {
        JPanel linha = new JPanel(new BorderLayout(8, 0));
        linha.setOpaque(false);
        linha.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

        CardSerie card = new CardSerie(serie, perfil, this::abrirDetalhes, this::atualizar);
        linha.add(card, BorderLayout.CENTER);

        JButton btnRemover = ComponentesVisuais.botaoPerigo("Remover");
        btnRemover.setToolTipText("Remover da lista");
        btnRemover.setPreferredSize(new Dimension(100, 38));
        btnRemover.setFont(new Font("Dialog", Font.BOLD, 14));
        btnRemover.addActionListener(e -> {
            removerSerie.accept(perfil, serie);
            salvarPerfil();
            atualizar();
        });

        JPanel painelBotao = new JPanel(new GridBagLayout());
        painelBotao.setOpaque(false);
        painelBotao.add(btnRemover);
        linha.add(painelBotao, BorderLayout.EAST);

        return linha;
    }

    private JLabel montarEstadoVazio() {
        JLabel lbl = new JLabel(
                "<html><div style='text-align:center;padding:20px'>"
                + "Nenhuma série nesta lista ainda.<br>"
                + "<span style='color:#505878'>Use a busca para encontrar e adicionar séries.</span>"
                + "</div></html>", SwingConstants.CENTER);
        lbl.setFont(TemaVisual.FONTE_CORPO);
        lbl.setForeground(TemaVisual.TEXTO_DISCRETO);
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        lbl.setBorder(new EmptyBorder(50, 0, 0, 0));
        return lbl;
    }

    private void abrirDetalhes(Serie serie) {
        Window janela = SwingUtilities.getWindowAncestor(this);
        Frame frame = (janela instanceof Frame f) ? f : null;
        new JanelaDetalhes(frame, serie, perfil, servicoPerfil, this::atualizar).setVisible(true);
    }

    private void salvarPerfil() {
        try { servicoPerfil.salvar(perfil); }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao salvar: " + ex.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
