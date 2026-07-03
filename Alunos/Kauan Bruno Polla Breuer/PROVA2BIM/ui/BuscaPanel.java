package ui;

import model.Serie;
import model.UserData;
import service.PersistenceService;
import service.TVMazeService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

/**
 * Painel de busca de séries via API TVMaze.
 * Exibe resultados em lista; ao selecionar, mostra detalhes no painel lateral.
 */
public class BuscaPanel extends JPanel {

    private final TVMazeService tvMaze;
    private final UserData userData;
    private final PersistenceService persistence;
    private final MainFrame mainFrame;

    private JTextField txtBusca;
    private JButton    btnBuscar;
    private DefaultListModel<Serie> modeloLista;
    private JList<Serie> listaResultados;
    private JLabel lblStatus;
    private SerieDetalhesPanel detalhesPanel;

    public BuscaPanel(TVMazeService tvMaze, UserData userData,
                      PersistenceService persistence, MainFrame mainFrame) {
        this.tvMaze      = tvMaze;
        this.userData    = userData;
        this.persistence = persistence;
        this.mainFrame   = mainFrame;
        setBackground(SerieDetalhesPanel.BG);
        setLayout(new BorderLayout(0, 0));
        initUI();
    }

    private void initUI() {
        // ── Painel esquerdo (busca + resultados) ──────────────────────────────
        JPanel esquerda = new JPanel(new BorderLayout(0, 8));
        esquerda.setBackground(SerieDetalhesPanel.BG);
        esquerda.setBorder(new EmptyBorder(16, 16, 16, 8));
        esquerda.setPreferredSize(new Dimension(340, 0));

        // Barra de busca
        JPanel barraBusca = new JPanel(new BorderLayout(6, 0));
        barraBusca.setBackground(SerieDetalhesPanel.BG);

        txtBusca = new JTextField();
        txtBusca.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtBusca.setBackground(SerieDetalhesPanel.SURFACE);
        txtBusca.setForeground(SerieDetalhesPanel.TEXT);
        txtBusca.setCaretColor(Color.WHITE);
        txtBusca.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(SerieDetalhesPanel.SURFACE1),
            BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        txtBusca.addActionListener(e -> realizarBusca());

        btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(SerieDetalhesPanel.PURPLE);
        btnBuscar.setForeground(SerieDetalhesPanel.BG);
        btnBuscar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnBuscar.setFocusPainted(false);
        btnBuscar.setBorderPainted(false);
        btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnBuscar.setPreferredSize(new Dimension(80, 36));
        btnBuscar.addActionListener(e -> realizarBusca());

        barraBusca.add(txtBusca, BorderLayout.CENTER);
        barraBusca.add(btnBuscar, BorderLayout.EAST);

        // Status
        lblStatus = new JLabel("Digite um nome e pressione Buscar.");
        lblStatus.setForeground(SerieDetalhesPanel.SUBTEXT);
        lblStatus.setFont(new Font("Segoe UI", Font.ITALIC, 12));

        // Lista de resultados
        modeloLista = new DefaultListModel<>();
        listaResultados = new JList<>(modeloLista);
        listaResultados.setBackground(SerieDetalhesPanel.SURFACE);
        listaResultados.setForeground(SerieDetalhesPanel.TEXT);
        listaResultados.setSelectionBackground(SerieDetalhesPanel.SURFACE1);
        listaResultados.setSelectionForeground(SerieDetalhesPanel.PURPLE);
        listaResultados.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        listaResultados.setFixedCellHeight(48);
        listaResultados.setCellRenderer(new SerieListCellRenderer());
        listaResultados.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Serie sel = listaResultados.getSelectedValue();
                if (sel != null) detalhesPanel.exibir(sel);
            }
        });

        JScrollPane scrollResultados = new JScrollPane(listaResultados);
        scrollResultados.setBorder(BorderFactory.createLineBorder(SerieDetalhesPanel.SURFACE1));
        scrollResultados.getViewport().setBackground(SerieDetalhesPanel.SURFACE);

        esquerda.add(barraBusca, BorderLayout.NORTH);
        esquerda.add(lblStatus,  BorderLayout.SOUTH);
        esquerda.add(scrollResultados, BorderLayout.CENTER);

        // ── Painel de detalhes (direita) ──────────────────────────────────────
        detalhesPanel = new SerieDetalhesPanel(userData, persistence, mainFrame);
        detalhesPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, SerieDetalhesPanel.SURFACE1));

        // ── Split ─────────────────────────────────────────────────────────────
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, esquerda, detalhesPanel);
        split.setDividerSize(1);
        split.setBackground(SerieDetalhesPanel.BG);
        split.setBorder(null);
        split.setResizeWeight(0.35);

        add(split, BorderLayout.CENTER);
    }

    private void realizarBusca() {
        String query = txtBusca.getText().trim();
        if (query.isEmpty()) {
            mostrarStatus("Por favor, digite o nome de uma serie.");
            return;
        }

        setBotaoAtivo(false);
        modeloLista.clear();
        detalhesPanel.mostrarVazio();
        mostrarStatus("Buscando...");

        SwingWorker<List<Serie>, Void> worker = new SwingWorker<>() {
            @Override
            protected List<Serie> doInBackground() throws Exception {
                return tvMaze.buscarPorNome(query);
            }

            @Override
            protected void done() {
                setBotaoAtivo(true);
                try {
                    List<Serie> resultados = get();
                    if (resultados.isEmpty()) {
                        mostrarStatus("Nenhuma serie encontrada para \"" + query + "\".");
                    } else {
                        for (Serie s : resultados) modeloLista.addElement(s);
                        mostrarStatus(resultados.size() + " resultado(s) encontrado(s).");
                    }
                } catch (Exception ex) {
                    String msg = ex.getCause() != null ? ex.getCause().getMessage() : ex.getMessage();
                    mostrarStatus("Erro ao buscar: " + msg);
                    JOptionPane.showMessageDialog(BuscaPanel.this,
                        "Nao foi possivel conectar a API.\n" + msg,
                        "Erro de conexao", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        worker.execute();
    }

    private void mostrarStatus(String texto) {
        lblStatus.setText(texto);
    }

    private void setBotaoAtivo(boolean ativo) {
        btnBuscar.setEnabled(ativo);
        btnBuscar.setText(ativo ? "Buscar" : "...");
    }

    // ─── Cell renderer customizado ────────────────────────────────────────────

    private static class SerieListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value,
                int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof Serie) {
                Serie s = (Serie) value;
                setText("<html><b>" + escapeHtml(s.getNome()) + "</b><br>"
                    + "<font color='#a6adc8'>" + s.getEstadoTraduzido()
                    + " &bull; " + s.getNotaFormatada() + " &bull; "
                    + escapeHtml(s.getDataEstreiaFormatada()) + "</font></html>");
            }
            setBorder(BorderFactory.createEmptyBorder(4, 10, 4, 10));
            if (!isSelected) {
                setBackground(SerieDetalhesPanel.SURFACE);
                setForeground(SerieDetalhesPanel.TEXT);
            }
            return this;
        }

        private String escapeHtml(String s) {
            return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
        }
    }
}
