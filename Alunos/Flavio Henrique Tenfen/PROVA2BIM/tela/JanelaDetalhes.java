package tela;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import modelo.PerfilUsuario;
import modelo.Serie;
import servico.ServicoPerfil;

public class JanelaDetalhes extends JDialog {

    private final PerfilUsuario perfil;
    private final ServicoPerfil servicoPerfil;
    private final Runnable aoMudarPerfil;

    public JanelaDetalhes(Frame janelaPai, Serie serie,
                          PerfilUsuario perfil,
                          ServicoPerfil servicoPerfil,
                          Runnable aoMudarPerfil) {
        super(janelaPai, serie.getNome(), true);
        this.perfil         = perfil;
        this.servicoPerfil  = servicoPerfil;
        this.aoMudarPerfil  = aoMudarPerfil;

        setSize(570, 570);
        setLocationRelativeTo(janelaPai);
        setResizable(false);
        getContentPane().setBackground(TemaVisual.FUNDO_PAINEL);
        setLayout(new BorderLayout(0, 0));

        add(montarCabecalho(serie), BorderLayout.NORTH);
        add(montarCorpo(serie),     BorderLayout.CENTER);
        add(montarRodape(serie),    BorderLayout.SOUTH);
    }

    private JPanel montarCabecalho(Serie serie) {
        Color cor = TemaVisual.corEstado(serie.getEstado());

        JPanel painel = new JPanel(new BorderLayout(0, 8)) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                GradientPaint gp = new GradientPaint(
                        0, 0, new Color(cor.getRed(), cor.getGreen(), cor.getBlue(), 50),
                        0, getHeight(), TemaVisual.FUNDO_PAINEL);
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.setColor(cor);
                g2.fillRect(0, 0, getWidth(), 3);
                g2.dispose();
            }
        };
        painel.setOpaque(false);
        painel.setBorder(new EmptyBorder(22, 26, 18, 26));

        JLabel lblNome = new JLabel(serie.getNome() != null ? serie.getNome() : "—");
        lblNome.setFont(TemaVisual.FONTE_TITULO);
        lblNome.setForeground(TemaVisual.TEXTO_PRINCIPAL);
        painel.add(lblNome, BorderLayout.NORTH);

        JPanel linhaInferior = new JPanel(new BorderLayout());
        linhaInferior.setOpaque(false);

        String emissora = serie.getEmissora() != null ? serie.getEmissora() : "Emissora desconhecida";
        linhaInferior.add(ComponentesVisuais.labelCorpo(emissora), BorderLayout.WEST);

        JPanel notaEBadge = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        notaEBadge.setOpaque(false);
        double nota = serie.getNota();
        JLabel lblNota = new JLabel(nota > 0 ? String.format("%.1f / 10", nota) : "Sem nota");
        lblNota.setFont(new Font("Dialog", Font.BOLD, 14));
        lblNota.setForeground(nota >= 7 ? TemaVisual.DOURADO : TemaVisual.TEXTO_DISCRETO);
        notaEBadge.add(lblNota);
        notaEBadge.add(ComponentesVisuais.badgeEstado(serie.getEstado()));
        linhaInferior.add(notaEBadge, BorderLayout.EAST);

        painel.add(linhaInferior, BorderLayout.CENTER);
        return painel;
    }

    private JPanel montarCorpo(Serie serie) {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBackground(TemaVisual.FUNDO_PAINEL);
        painel.setBorder(new EmptyBorder(6, 26, 10, 26));

        painel.add(montarGradeInfo(serie));
        painel.add(Box.createVerticalStrut(16));

        if (serie.getSinopse() != null && !serie.getSinopse().isBlank()) {
            JLabel lblSec = ComponentesVisuais.labelSecao("Sinopse");
            lblSec.setAlignmentX(Component.LEFT_ALIGNMENT);
            painel.add(lblSec);
            painel.add(Box.createVerticalStrut(6));

            JTextArea areaSinopse = new JTextArea(serie.getSinopse());
            areaSinopse.setFont(TemaVisual.FONTE_CORPO);
            areaSinopse.setForeground(TemaVisual.TEXTO_SECUNDARIO);
            areaSinopse.setBackground(TemaVisual.FUNDO_CARD);
            areaSinopse.setWrapStyleWord(true);
            areaSinopse.setLineWrap(true);
            areaSinopse.setEditable(false);
            areaSinopse.setBorder(new EmptyBorder(10, 12, 10, 12));

            JScrollPane scroll = new JScrollPane(areaSinopse);
            scroll.setAlignmentX(Component.LEFT_ALIGNMENT);
            scroll.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
            scroll.setBorder(BorderFactory.createLineBorder(TemaVisual.BORDA, 1));
            scroll.getViewport().setBackground(TemaVisual.FUNDO_CARD);
            painel.add(scroll);
        }

        return painel;
    }

    private JPanel montarGradeInfo(Serie serie) {
        JPanel grade = new JPanel(new GridLayout(3, 2, 12, 8));
        grade.setOpaque(false);
        grade.setAlignmentX(Component.LEFT_ALIGNMENT);

        grade.add(celulaInfo("IDIOMA",    serie.getIdioma()));
        grade.add(celulaInfo("GÊNEROS",   serie.getGenerosFormatados()));
        grade.add(celulaInfo("ESTREIA",   serie.getDataEstreia()));
        grade.add(celulaInfo("TÉRMINO",   serie.getDataTermino() != null ? serie.getDataTermino() : "Em andamento"));
        grade.add(celulaInfo("ESTADO",    TemaVisual.rotuloEstado(serie.getEstado())));
        grade.add(celulaInfo("EMISSORA",  serie.getEmissora()));

        return grade;
    }

    private JPanel celulaInfo(String chave, String valor) {
        JPanel celula = new JPanel(new BorderLayout(0, 3));
        celula.setBackground(TemaVisual.FUNDO_CARD);
        celula.setBorder(new EmptyBorder(10, 12, 10, 12));

        celula.add(ComponentesVisuais.labelSecao(chave), BorderLayout.NORTH);

        JLabel lblValor = new JLabel(valor != null && !valor.isBlank() ? valor : "—");
        lblValor.setFont(TemaVisual.FONTE_CORPO);
        lblValor.setForeground(TemaVisual.TEXTO_PRINCIPAL);
        celula.add(lblValor, BorderLayout.CENTER);

        JPanel wrapper = new JPanel(new BorderLayout()) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(TemaVisual.FUNDO_CARD);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                g2.setColor(TemaVisual.BORDA);
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 10, 10);
                g2.dispose();
            }
            @Override public boolean isOpaque() { return false; }
        };
        wrapper.setOpaque(false);
        wrapper.add(celula, BorderLayout.CENTER);
        return wrapper;
    }

    private JPanel montarRodape(Serie serie) {
        JPanel rodape = new JPanel(new BorderLayout(0, 10));
        rodape.setBackground(TemaVisual.FUNDO_CABECALHO);
        rodape.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 0, 0, 0, TemaVisual.BORDA),
                new EmptyBorder(12, 26, 16, 26)));

        JLabel lblSec = ComponentesVisuais.labelSecao("Adicionar às suas listas");
        rodape.add(lblSec, BorderLayout.NORTH);

        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 4));
        botoes.setOpaque(false);
        botoes.add(botaoToggleLista(serie, "Favoritas",
                () -> perfil.ehFavorita(serie),
                () -> { if (perfil.ehFavorita(serie)) perfil.removerFavorita(serie);
                        else perfil.adicionarFavorita(serie); }));
        botoes.add(botaoToggleLista(serie, "Assistidas",
                () -> perfil.jaAssistiu(serie),
                () -> { if (perfil.jaAssistiu(serie)) perfil.removerAssistida(serie);
                        else perfil.adicionarAssistida(serie); }));
        botoes.add(botaoToggleLista(serie, "Quero Assistir",
                () -> perfil.estaNaLista(serie),
                () -> { if (perfil.estaNaLista(serie)) perfil.removerQueroAssistir(serie);
                        else perfil.adicionarQueroAssistir(serie); }));
        rodape.add(botoes, BorderLayout.CENTER);

        JButton btnFechar = ComponentesVisuais.botaoSecundario("Fechar");
        btnFechar.addActionListener(e -> dispose());
        JPanel painelFechar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        painelFechar.setOpaque(false);
        painelFechar.add(btnFechar);
        rodape.add(painelFechar, BorderLayout.SOUTH);

        return rodape;
    }

    private JButton botaoToggleLista(Serie serie, String rotulo,
                                      java.util.function.BooleanSupplier ativo,
                                      Runnable alternar) {
        JButton btn = ativo.getAsBoolean()
                ? ComponentesVisuais.botaoSucesso(rotulo)
                : ComponentesVisuais.botaoSecundario(rotulo);
        btn.addActionListener(e -> {
            alternar.run();
            salvarPerfil();
            dispose();
            aoMudarPerfil.run();
        });
        return btn;
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
