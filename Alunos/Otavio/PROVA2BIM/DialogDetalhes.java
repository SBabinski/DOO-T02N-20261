import javax.swing.*;
import java.awt.*;

public final class DialogDetalhes {

    private DialogDetalhes() {
    }

    public static void mostrar(Component pai, Serie serie) {
        JPanel painel = new JPanel(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(4, 6, 4, 6);
        g.anchor = GridBagConstraints.WEST;

        int linha = 0;
        linha = adicionarLinha(painel, g, linha, "Nome:", serie.getNome());
        linha = adicionarLinha(painel, g, linha, "Idioma:", serie.getIdioma());
        linha = adicionarLinha(painel, g, linha, "Generos:", serie.getGenerosTexto());
        linha = adicionarLinha(painel, g, linha, "Nota geral:", serie.getNotaTexto());
        linha = adicionarLinha(painel, g, linha, "Estado:", serie.getEstado().getDescricao());
        linha = adicionarLinha(painel, g, linha, "Estreia:", serie.getEstreiaTexto());
        linha = adicionarLinha(painel, g, linha, "Termino:", serie.getTerminoTexto());
        adicionarLinha(painel, g, linha, "Emissora:", serie.getEmissora());

        JOptionPane.showMessageDialog(pai, painel,
                "Detalhes da serie", JOptionPane.INFORMATION_MESSAGE);
    }

    private static int adicionarLinha(JPanel painel, GridBagConstraints g, int linha,
                                      String rotulo, String valor) {
        g.gridx = 0;
        g.gridy = linha;
        g.weightx = 0;
        JLabel titulo = new JLabel(rotulo);
        titulo.setFont(titulo.getFont().deriveFont(Font.BOLD));
        painel.add(titulo, g);

        g.gridx = 1;
        g.weightx = 1;
        String texto = (valor == null || valor.isEmpty()) ? "-" : valor;
        painel.add(new JLabel(texto), g);

        return linha + 1;
    }
}