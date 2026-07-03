import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class PainelLista extends JPanel {

    private final Biblioteca biblioteca;
    private final TipoLista tipo;

    private final SerieTableModel modelo = new SerieTableModel();
    private final JTable tabela = new JTable(modelo);
    private final JComboBox<CriterioOrdenacao> seletorOrdem =
            new JComboBox<>(CriterioOrdenacao.values());
    private final JLabel status = new JLabel(" ");

    public PainelLista(Biblioteca biblioteca, TipoLista tipo) {
        this.biblioteca = biblioteca;
        this.tipo = tipo;
        montar();
        atualizar();
    }

    private void montar() {
        setLayout(new BorderLayout(8, 8));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel topo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topo.add(new JLabel("Ordenar por: "));
        topo.add(seletorOrdem);
        JButton botaoAtualizar = new JButton("Atualizar");
        topo.add(botaoAtualizar);
        add(topo, BorderLayout.NORTH);

        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabela.setRowHeight(24);
        SerieTableModel.ajustarLarguras(tabela);
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        JPanel base = new JPanel(new BorderLayout());
        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton detalhes = new JButton("Ver detalhes");
        JButton remover = new JButton("Remover da lista");
        botoes.add(detalhes);
        botoes.add(remover);
        base.add(botoes, BorderLayout.WEST);
        base.add(status, BorderLayout.SOUTH);
        add(base, BorderLayout.SOUTH);

        seletorOrdem.addActionListener(e -> atualizar());
        botaoAtualizar.addActionListener(e -> atualizar());
        detalhes.addActionListener(e -> verDetalhes());
        remover.addActionListener(e -> remover());

        tabela.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    verDetalhes();
                }
            }
        });
    }

    public void atualizar() {
        List<Serie> series = new ArrayList<>(biblioteca.getLista(tipo));
        CriterioOrdenacao criterio = (CriterioOrdenacao) seletorOrdem.getSelectedItem();
        if (criterio != null) {
            series.sort(criterio.getComparador());
        }
        modelo.setSeries(series);
        if (series.isEmpty()) {
            status.setText("Nenhuma serie nesta lista ainda.");
        } else {
            status.setText(series.size() + " serie(s) na lista.");
        }
    }

    private Serie selecionada() {
        int linha = tabela.getSelectedRow();
        if (linha < 0) {
            return null;
        }
        return modelo.getSerieEm(tabela.convertRowIndexToModel(linha));
    }

    private void verDetalhes() {
        Serie s = selecionada();
        if (s == null) {
            avisarSelecao();
            return;
        }
        DialogDetalhes.mostrar(this, s);
    }

    private void remover() {
        Serie s = selecionada();
        if (s == null) {
            avisarSelecao();
            return;
        }
        int opcao = JOptionPane.showConfirmDialog(this,
                "Remover \"" + s.getNome() + "\" de " + tipo.getDescricao() + "?",
                "Confirmar remocao", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (opcao != JOptionPane.YES_OPTION) {
            return;
        }
        try {
            biblioteca.remover(tipo, s);
            atualizar();
        } catch (PersistenciaException ex) {
            JOptionPane.showMessageDialog(this,
                    "A serie foi removida da memoria, mas houve erro ao salvar:\n"
                            + ex.getMessage(),
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            atualizar();
        }
    }

    private void avisarSelecao() {
        JOptionPane.showMessageDialog(this,
                "Selecione uma serie na lista.",
                "Atencao", JOptionPane.WARNING_MESSAGE);
    }
}