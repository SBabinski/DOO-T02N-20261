package tela;

import java.awt.*;
import java.awt.event.*;
import java.util.function.Consumer;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import modelo.PerfilUsuario;
import modelo.Serie;


public class CardSerie extends JPanel {

    private static final int LARGURA_ACENTO = 4;
    private boolean emHover = false;

    public CardSerie(Serie serie, PerfilUsuario perfil,
                     Consumer<Serie> aoClicarDetalhes,
                     Runnable aoMudarPerfil) {

        setLayout(new BorderLayout(0, 0));
        setBackground(TemaVisual.FUNDO_CARD);
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 96));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        Color corEstado = TemaVisual.corEstado(serie.getEstado());
        JPanel faixaLateral = new JPanel() {
            @Override protected void paintComponent(Graphics g) {
                g.setColor(corEstado);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        faixaLateral.setPreferredSize(new Dimension(LARGURA_ACENTO, 0));
        faixaLateral.setOpaque(false);
        add(faixaLateral, BorderLayout.WEST);

        JPanel conteudo = new JPanel(new BorderLayout(12, 0));
        conteudo.setOpaque(false);
        conteudo.setBorder(new EmptyBorder(12, 16, 12, 16));

        // Info à esquerda
        JPanel infoEsquerda = new JPanel();
        infoEsquerda.setLayout(new BoxLayout(infoEsquerda, BoxLayout.Y_AXIS));
        infoEsquerda.setOpaque(false);

        JLabel lblNome = new JLabel(serie.getNome() != null ? serie.getNome() : "—");
        lblNome.setFont(TemaVisual.FONTE_H2);
        lblNome.setForeground(TemaVisual.TEXTO_PRINCIPAL);
        infoEsquerda.add(lblNome);
        infoEsquerda.add(Box.createVerticalStrut(4));

        String emissora = (serie.getEmissora() != null ? serie.getEmissora() : "Emissora desconhecida")
                        + "  ·  " + (serie.getIdioma() != null ? serie.getIdioma() : "—");
        JLabel lblEmissora = new JLabel(emissora);
        lblEmissora.setFont(TemaVisual.FONTE_CORPO);
        lblEmissora.setForeground(TemaVisual.TEXTO_SECUNDARIO);
        infoEsquerda.add(lblEmissora);
        infoEsquerda.add(Box.createVerticalStrut(3));

        String generos = serie.getGenerosFormatados();
        JLabel lblGeneros = new JLabel(generos.equals("N/A") ? "Sem gênero definido" : generos);
        lblGeneros.setFont(TemaVisual.FONTE_PEQUENA);
        lblGeneros.setForeground(TemaVisual.TEXTO_DISCRETO);
        infoEsquerda.add(lblGeneros);

        conteudo.add(infoEsquerda, BorderLayout.CENTER);

        JPanel painelDireito = new JPanel();
        painelDireito.setLayout(new BoxLayout(painelDireito, BoxLayout.Y_AXIS));
        painelDireito.setOpaque(false);

        double nota = serie.getNota();
        JLabel lblNota = new JLabel(nota > 0 ? String.format("%.1f", nota) : "—");
        lblNota.setFont(new Font("Dialog", Font.BOLD, 14));
        lblNota.setForeground(nota >= 7 ? TemaVisual.DOURADO : TemaVisual.TEXTO_DISCRETO);
        lblNota.setAlignmentX(Component.RIGHT_ALIGNMENT);
        painelDireito.add(lblNota);
        painelDireito.add(Box.createVerticalStrut(6));

        JLabel badge = ComponentesVisuais.badgeEstado(serie.getEstado());
        badge.setAlignmentX(Component.RIGHT_ALIGNMENT);
        painelDireito.add(badge);
        painelDireito.add(Box.createVerticalGlue());

        JButton btnDetalhes = ComponentesVisuais.botaoSecundario("Detalhes");
        btnDetalhes.setFont(TemaVisual.FONTE_PEQUENA);
        btnDetalhes.setAlignmentX(Component.RIGHT_ALIGNMENT);
        btnDetalhes.addActionListener(e -> aoClicarDetalhes.accept(serie));
        painelDireito.add(btnDetalhes);

        conteudo.add(painelDireito, BorderLayout.EAST);
        add(conteudo, BorderLayout.CENTER);

        addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) { emHover = true;  repaint(); }
            @Override public void mouseExited(MouseEvent e)  { emHover = false; repaint(); }
            @Override public void mouseClicked(MouseEvent e) { aoClicarDetalhes.accept(serie); }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(emHover ? TemaVisual.FUNDO_CARD_HOVER : TemaVisual.FUNDO_CARD);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), TemaVisual.ARREDONDAMENTO, TemaVisual.ARREDONDAMENTO);
        Color borda = emHover ? TemaVisual.BORDA_ATIVA : TemaVisual.BORDA;
        g2.setColor(borda);
        g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, TemaVisual.ARREDONDAMENTO, TemaVisual.ARREDONDAMENTO);
        g2.dispose();
    }

    @Override public boolean isOpaque() { return false; }
}
