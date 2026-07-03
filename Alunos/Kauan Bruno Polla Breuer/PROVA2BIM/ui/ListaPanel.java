package ui;

import model.Serie;
import model.UserData;
import service.PersistenceService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Painel genérico que exibe uma das três listas do usuário
 * (Favoritos, Já Assistidas, Quero Assistir) com suporte a ordenação.
 */
public class ListaPanel extends JPanel {

    public enum TipoLista { FAVORITOS, JA_ASSISTIDAS, QUER_ASSISTIR }

    private final TipoLista tipo;
    private final UserData userData;
    private final PersistenceService persistence;
    private final MainFrame mainFrame;

    private DefaultListModel<Serie> modeloLista;
    private JList<Serie> jList;
    private JComboBox<String> cmbOrdem;
    private SerieDetalhesPanel detalhesPanel;
    private JLabel lblVazio;

    public ListaPanel(TipoLista tipo, UserData userData,
                      PersistenceService persistence, MainFrame mainFrame) {
        this.tipo        = tipo;
        this.userData    = userData;
        this.persistence = persistence;
        this.mainFrame   = mainFrame;
        setBackground(SerieDetalhesPanel.BG);
        setLayout(new BorderLayout(0, 0));
        initUI();
    }

    private void initUI() {
        // ── Coluna esquerda ───────────────────────────────────────────────────
        JPanel esquerda = new JPanel(new BorderLayout(0, 8));
        esquerda.setBackground(SerieDetalhesPanel.BG);
        esquerda.setBorder(new EmptyBorder(16, 16, 16, 8));
        esquerda.setPreferredSize(new Dimension(340, 0));

        // Cabeçalho
        JLabel lblTitulo = new JLabel(getTituloLista());
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(getCorLista());

        // Ordenação
        String[] ordens = {
            "Ordem alfabetica",
            "Nota (maior primeiro)",
            "Estado da serie",
            "Data de estreia"
        };
        cmbOrdem = new JComboBox<>(ordens);
        cmbOrdem.setBackground(SerieDetalhesPanel.SURFACE);
        cmbOrdem.setForeground(SerieDetalhesPanel.TEXT);
        cmbOrdem.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        cmbOrdem.setFocusable(false);
        cmbOrdem.addActionListener(e -> recarregar());

        JPanel topo = new JPanel(new BorderLayout(6, 6));
        topo.setBackground(SerieDetalhesPanel.BG);
        topo.add(lblTitulo, BorderLayout.NORTH);
        topo.add(cmbOrdem, BorderLayout.CENTER);

        // Botão remover
        JButton btnRemover = new JButton("Remover selecionada");
        btnRemover.setBackground(SerieDetalhesPanel.RED);
        btnRemover.setForeground(SerieDetalhesPanel.BG);
        btnRemover.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnRemover.setFocusPainted(false);
        btnRemover.setBorderPainted(false);
        btnRemover.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnRemover.addActionListener(e -> removerSelecionada());

        // Lista
        modeloLista = new DefaultListModel<>();
        jList = new JList<>(modeloLista);
        jList.setBackground(SerieDetalhesPanel.SURFACE);
        jList.setForeground(SerieDetalhesPanel.TEXT);
        jList.setSelectionBackground(SerieDetalhesPanel.SURFACE1);
        jList.setSelectionForeground(getCorLista());
        jList.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        jList.setFixedCellHeight(52);
        jList.setCellRenderer(new ListaCellRenderer());
        jList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Serie sel = jList.getSelectedValue();
                if (sel != null) detalhesPanel.exibir(sel);
            }
        });

        JScrollPane scroll = new JScrollPane(jList);
        scroll.setBorder(BorderFactory.createLineBorder(SerieDetalhesPanel.SURFACE1));
        scroll.getViewport().setBackground(SerieDetalhesPanel.SURFACE);

        lblVazio = new JLabel("Lista vazia. Adicione series pela busca!", JLabel.CENTER);
        lblVazio.setForeground(SerieDetalhesPanel.SUBTEXT);
        lblVazio.setFont(new Font("Segoe UI", Font.ITALIC, 13));

        esquerda.add(topo,       BorderLayout.NORTH);
        esquerda.add(scroll,     BorderLayout.CENTER);
        esquerda.add(btnRemover, BorderLayout.SOUTH);

        // ── Painel de detalhes ────────────────────────────────────────────────
        detalhesPanel = new SerieDetalhesPanel(userData, persistence, mainFrame);
        detalhesPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, SerieDetalhesPanel.SURFACE1));

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, esquerda, detalhesPanel);
        split.setDividerSize(1);
        split.setBackground(SerieDetalhesPanel.BG);
        split.setBorder(null);
        split.setResizeWeight(0.38);
        add(split, BorderLayout.CENTER);

        recarregar();
    }

    /** Recarrega a lista respeitando a ordenação selecionada. */
    public void recarregar() {
        modeloLista.clear();
        List<Serie> series = new ArrayList<>(getListaDoTipo());
        ordenar(series);
        for (Serie s : series) modeloLista.addElement(s);
    }

    // ─── Remoção ──────────────────────────────────────────────────────────────

    private void removerSelecionada() {
        Serie sel = jList.getSelectedValue();
        if (sel == null) {
            JOptionPane.showMessageDialog(this, "Selecione uma serie para remover.",
                "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this,
            "Remover \"" + sel.getNome() + "\" da lista?",
            "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            removerDaLista(sel);
            salvar();
            recarregar();
            detalhesPanel.mostrarVazio();
            mainFrame.notificarAlteracaoListas();
        }
    }

    // ─── Ordenação ────────────────────────────────────────────────────────────

    private void ordenar(List<Serie> list) {
        switch (cmbOrdem.getSelectedIndex()) {
            case 0: // Alfabética
                list.sort(Comparator.comparing(s -> s.getNome().toLowerCase()));
                break;
            case 1: // Nota
                list.sort((a, b) -> {
                    Double na = a.getNota(), nb = b.getNota();
                    if (na == null && nb == null) return 0;
                    if (na == null) return 1;
                    if (nb == null) return -1;
                    return Double.compare(nb, na);
                });
                break;
            case 2: // Estado
                list.sort(Comparator.comparing(s -> s.getEstadoTraduzido()));
                break;
            case 3: // Data de estreia
                list.sort((a, b) -> {
                    String da = a.getDataEstreia(), db = b.getDataEstreia();
                    if (da == null && db == null) return 0;
                    if (da == null) return 1;
                    if (db == null) return -1;
                    return db.compareTo(da); // mais recente primeiro
                });
                break;
        }
    }

    // ─── Helpers ──────────────────────────────────────────────────────────────

    private List<Serie> getListaDoTipo() {
        switch (tipo) {
            case FAVORITOS:     return userData.getFavoritos();
            case JA_ASSISTIDAS: return userData.getJaAssistidas();
            default:            return userData.getQuerAssistir();
        }
    }

    private void removerDaLista(Serie s) {
        switch (tipo) {
            case FAVORITOS:     userData.removeFavorito(s);   break;
            case JA_ASSISTIDAS: userData.removeJaAssistida(s); break;
            default:            userData.removeQuerAssistir(s); break;
        }
    }

    private String getTituloLista() {
        switch (tipo) {
            case FAVORITOS:     return "Favoritos";
            case JA_ASSISTIDAS: return "Ja Assistidas";
            default:            return "Quero Assistir";
        }
    }

    private Color getCorLista() {
        switch (tipo) {
            case FAVORITOS:     return SerieDetalhesPanel.RED;
            case JA_ASSISTIDAS: return SerieDetalhesPanel.GREEN;
            default:            return SerieDetalhesPanel.YELLOW;
        }
    }

    private void salvar() {
        try {
            persistence.salvar(userData);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                "Erro ao salvar: " + ex.getMessage(),
                "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ─── Cell renderer ────────────────────────────────────────────────────────

    private class ListaCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value,
                int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof Serie) {
                Serie s = (Serie) value;
                Color cor = getCorLista();
                String hexCor = String.format("#%02x%02x%02x", cor.getRed(), cor.getGreen(), cor.getBlue());
                setText("<html><b style='color:" + hexCor + "'>" + escapeHtml(s.getNome()) + "</b><br>"
                    + "<font color='#a6adc8'>" + escapeHtml(s.getGenerosFormatados())
                    + " &bull; " + s.getNotaFormatada() + "</font></html>");
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
