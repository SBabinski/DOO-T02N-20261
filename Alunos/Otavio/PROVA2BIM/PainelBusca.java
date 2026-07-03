import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class PainelBusca extends JPanel {

    private final Biblioteca biblioteca;
    private final TVMazeClient cliente;

    private final JTextField campoBusca = new JTextField();
    private final JButton botaoBuscar = new JButton("Buscar");
    private final SerieTableModel modelo = new SerieTableModel();
    private final JTable tabela = new JTable(modelo);
    private final JLabel status = new JLabel(" ");

    public PainelBusca(Biblioteca biblioteca, TVMazeClient cliente) {
        this.biblioteca = biblioteca;
        this.cliente = cliente;
        montar();
    }

    private void montar() {
        setLayout(new BorderLayout(8, 8));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel topo = new JPanel(new BorderLayout(6, 0));
        topo.add(new JLabel("Nome da serie: "), BorderLayout.WEST);
        topo.add(campoBusca, BorderLayout.CENTER);
        topo.add(botaoBuscar, BorderLayout.EAST);
        add(topo, BorderLayout.NORTH);

        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabela.setRowHeight(24);
        SerieTableModel.ajustarLarguras(tabela);
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        JPanel base = new JPanel(new BorderLayout());
        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton detalhes = new JButton("Ver detalhes");
        JButton favoritos = new JButton("Adicionar a Favoritos");
        JButton assistidas = new JButton("Adicionar a Ja assistidas");
        JButton deseja = new JButton("Adicionar a Quero assistir");
        botoes.add(detalhes);
        botoes.add(favoritos);
        botoes.add(assistidas);
        botoes.add(deseja);
        base.add(botoes, BorderLayout.WEST);
        base.add(status, BorderLayout.SOUTH);
        add(base, BorderLayout.SOUTH);

        botaoBuscar.addActionListener(e -> buscar());
        campoBusca.addActionListener(e -> buscar()); // tecla Enter
        detalhes.addActionListener(e -> verDetalhes());
        favoritos.addActionListener(e -> adicionar(TipoLista.FAVORITOS));
        assistidas.addActionListener(e -> adicionar(TipoLista.ASSISTIDAS));
        deseja.addActionListener(e -> adicionar(TipoLista.DESEJA_ASSISTIR));

        tabela.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    verDetalhes();
                }
            }
        });
    }

    private void buscar() {
        final String termo = campoBusca.getText().trim();
        if (termo.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Digite o nome de uma serie para buscar.",
                    "Atencao", JOptionPane.WARNING_MESSAGE);
            return;
        }

        botaoBuscar.setEnabled(false);
        status.setText("Buscando \"" + termo + "\"...");
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        SwingWorker<List<Serie>, Void> tarefa = new SwingWorker<List<Serie>, Void>() {
            @Override
            protected List<Serie> doInBackground() throws Exception {
                return cliente.buscarPorNome(termo);
            }

            @Override
            protected void done() {
                try {
                    List<Serie> resultados = get();
                    modelo.setSeries(resultados);
                    if (resultados.isEmpty()) {
                        status.setText("Nenhuma serie encontrada para \"" + termo + "\".");
                    } else {
                        status.setText(resultados.size() + " serie(s) encontrada(s).");
                    }
                } catch (ExecutionException ex) {
                    Throwable causa = (ex.getCause() != null) ? ex.getCause() : ex;
                    modelo.setSeries(Collections.<Serie>emptyList());
                    status.setText("Erro na busca.");
                    JOptionPane.showMessageDialog(PainelBusca.this,
                            causa.getMessage(), "Erro na busca", JOptionPane.ERROR_MESSAGE);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    status.setText("Busca interrompida.");
                } finally {
                    botaoBuscar.setEnabled(true);
                    setCursor(Cursor.getDefaultCursor());
                }
            }
        };
        tarefa.execute();
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

    private void adicionar(TipoLista tipo) {
        Serie s = selecionada();
        if (s == null) {
            avisarSelecao();
            return;
        }
        try {
            boolean adicionada = biblioteca.adicionar(tipo, s);
            if (adicionada) {
                JOptionPane.showMessageDialog(this,
                        "\"" + s.getNome() + "\" adicionada a " + tipo.getDescricao() + ".",
                        "Pronto", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "\"" + s.getNome() + "\" ja esta em " + tipo.getDescricao() + ".",
                        "Atencao", JOptionPane.WARNING_MESSAGE);
            }
        } catch (PersistenciaException ex) {
            JOptionPane.showMessageDialog(this,
                    "A serie foi adicionada na memoria, mas houve erro ao salvar:\n" + ex.getMessage(),
                    "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void avisarSelecao() {
        JOptionPane.showMessageDialog(this,
                "Selecione uma serie na lista de resultados.",
                "Atencao", JOptionPane.WARNING_MESSAGE);
    }
}