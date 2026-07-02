package tvtracker.ui;

import java.awt.*;
import java.net.URL;
import javax.swing.*;
import tvtracker.model.TvShow;
import tvtracker.model.UserData;
import tvtracker.service.DataService;

public class ShowDetailsDialog extends JDialog {

    private final TvShow show;
    private final UserData userData;
    private final DataService dataService;
    private final Runnable onDataChanged;

    public ShowDetailsDialog(Frame parent, TvShow show, UserData userData,
                             DataService dataService, Runnable onDataChanged) {
        super(parent, "Detalhes da Série", true);
        this.show        = show;
        this.userData    = userData;
        this.dataService = dataService;
        this.onDataChanged = onDataChanged;
        buildUi();
        pack();
        setMinimumSize(new Dimension(520, 480));
        setLocationRelativeTo(parent);
    }

    private void buildUi() {
        setLayout(new BorderLayout(10, 10));
        getRootPane().setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        // Header
        JPanel headerPanel = new JPanel(new BorderLayout(12, 0));
        headerPanel.setOpaque(false);

        // Tenta carregar a imagem em background
        JLabel imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(90, 128));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setVerticalAlignment(SwingConstants.TOP);
        imageLabel.setText("📺");
        imageLabel.setFont(imageLabel.getFont().deriveFont(40f));
        if (show.getImageUrl() != null && !show.getImageUrl().isBlank()) {
            new Thread(() -> {
                try {
                    ImageIcon icon = new ImageIcon(new URL(show.getImageUrl()));
                    Image img = icon.getImage().getScaledInstance(90, 128, Image.SCALE_SMOOTH);
                    SwingUtilities.invokeLater(() -> {
                        imageLabel.setIcon(new ImageIcon(img));
                        imageLabel.setText(null);
                    });
                } catch (Exception ignored) {}
            }).start();
        }
        headerPanel.add(imageLabel, BorderLayout.WEST);

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setOpaque(false);

        JLabel titleLabel = new JLabel(show.getName() != null ? show.getName() : "Sem nome");
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 18f));
        titlePanel.add(titleLabel);
        titlePanel.add(Box.createVerticalStrut(4));

        String year = show.getPremiered() != null && show.getPremiered().length() >= 4
                ? show.getPremiered().substring(0, 4) : "?";
        titlePanel.add(makeSubtitle("Ano de estreia: " + year));

        if (show.getNetwork() != null && !show.getNetwork().isBlank()) {
            titlePanel.add(makeSubtitle("Emissora: " + show.getNetwork()));
        }

        JLabel ratingLabel = new JLabel("⭐ " + show.getRatingDisplay());
        ratingLabel.setFont(ratingLabel.getFont().deriveFont(Font.BOLD, 14f));
        ratingLabel.setForeground(new Color(0xC97B00));
        titlePanel.add(Box.createVerticalStrut(6));
        titlePanel.add(ratingLabel);

        headerPanel.add(titlePanel, BorderLayout.CENTER);
        add(headerPanel, BorderLayout.NORTH);

        // Info grid
        JPanel infoPanel = new JPanel(new GridBagLayout());
        infoPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(0xDDDDDD)), "Informações"));
        infoPanel.setBackground(new Color(0xF9F9F9));

        GridBagConstraints lc = new GridBagConstraints();
        lc.anchor = GridBagConstraints.WEST;
        lc.insets = new Insets(3, 8, 3, 6);
        lc.gridx = 0; lc.weightx = 0;

        GridBagConstraints vc = new GridBagConstraints();
        vc.anchor = GridBagConstraints.WEST;
        vc.insets = new Insets(3, 0, 3, 8);
        vc.gridx = 1; vc.weightx = 1; vc.fill = GridBagConstraints.HORIZONTAL;

        int row = 0;
        addInfoRow(infoPanel, lc, vc, row++, "Idioma:",     nvl(show.getLanguage()));
        addInfoRow(infoPanel, lc, vc, row++, "Gêneros:",    show.getGenresDisplay());
        addInfoRow(infoPanel, lc, vc, row++, "Estado:",     show.getStatusDisplay());
        addInfoRow(infoPanel, lc, vc, row++, "Estreia:",    nvl(show.getPremiered()));
        addInfoRow(infoPanel, lc, vc, row++, "Encerramento:", show.getEnded() != null && !show.getEnded().isBlank()
                ? show.getEnded() : "Em andamento");
        addInfoRow(infoPanel, lc, vc, row,   "Nota:",       show.getRatingDisplay());

        add(infoPanel, BorderLayout.CENTER);

        // Painel inferior
        JPanel bottomPanel = new JPanel(new BorderLayout(0, 8));
        bottomPanel.setOpaque(false);

        if (show.getSummary() != null && !show.getSummary().isBlank()) {
            JTextArea summaryArea = new JTextArea(show.getSummary());
            summaryArea.setLineWrap(true);
            summaryArea.setWrapStyleWord(true);
            summaryArea.setEditable(false);
            summaryArea.setRows(4);
            summaryArea.setFont(summaryArea.getFont().deriveFont(12f));
            summaryArea.setBackground(new Color(0xF0F0F0));
            JScrollPane sp = new JScrollPane(summaryArea);
            sp.setBorder(BorderFactory.createTitledBorder("Sinopse"));
            bottomPanel.add(sp, BorderLayout.CENTER);
        }

        // Botões de lista
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 4));
        buttonPanel.setOpaque(false);

        JToggleButton favBtn  = createToggleBtn("⭐ Favorito",  userData.isFavorite(show));
        JToggleButton watchedBtn = createToggleBtn("✅ Assistida", userData.isWatched(show));
        JToggleButton wlBtn   = createToggleBtn("🔖 Quero Assistir", userData.isInWatchlist(show));

        favBtn.addActionListener(e -> {
            if (favBtn.isSelected()) { userData.addFavorite(show); }
            else                     { userData.removeFavorite(show); }
            saveData();
            if (onDataChanged != null) onDataChanged.run();
        });
        watchedBtn.addActionListener(e -> {
            if (watchedBtn.isSelected()) { userData.addWatched(show); }
            else                         { userData.removeWatched(show); }
            saveData();
            if (onDataChanged != null) onDataChanged.run();
        });
        wlBtn.addActionListener(e -> {
            if (wlBtn.isSelected()) { userData.addToWatchlist(show); }
            else                    { userData.removeFromWatchlist(show); }
            saveData();
            if (onDataChanged != null) onDataChanged.run();
        });

        buttonPanel.add(favBtn);
        buttonPanel.add(watchedBtn);
        buttonPanel.add(wlBtn);

        JButton closeBtn = new JButton("Fechar");
        closeBtn.addActionListener(e -> dispose());
        buttonPanel.add(Box.createHorizontalStrut(20));
        buttonPanel.add(closeBtn);

        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void addInfoRow(JPanel panel, GridBagConstraints lc, GridBagConstraints vc,
                            int row, String label, String value) {
        lc.gridy = row;
        vc.gridy = row;
        JLabel lbl = new JLabel(label);
        lbl.setFont(lbl.getFont().deriveFont(Font.BOLD));
        panel.add(lbl, lc);
        JLabel val = new JLabel(value);
        panel.add(val, vc);
    }

    private JLabel makeSubtitle(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setForeground(Color.DARK_GRAY);
        return lbl;
    }

    private JToggleButton createToggleBtn(String text, boolean selected) {
        JToggleButton btn = new JToggleButton(text, selected);
        btn.setFocusPainted(false);
        updateToggleStyle(btn);
        btn.addChangeListener(e -> updateToggleStyle(btn));
        return btn;
    }

    private void updateToggleStyle(JToggleButton btn) {
        if (btn.isSelected()) {
            btn.setBackground(new Color(0x4CAF50));
            btn.setForeground(Color.WHITE);
        } else {
            btn.setBackground(UIManager.getColor("Button.background"));
            btn.setForeground(UIManager.getColor("Button.foreground"));
        }
    }

    private void saveData() {
        try { dataService.save(userData); }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar: " + ex.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String nvl(String s) { return s != null && !s.isBlank() ? s : "N/A"; }
}
