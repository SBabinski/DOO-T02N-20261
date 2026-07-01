package tvtracker.ui;

import java.awt.*;
import javax.swing.*;
import tvtracker.model.ListType;
import tvtracker.model.Show;
import tvtracker.persistence.PersistenceException;
import tvtracker.service.LibraryService;


public class ShowDetailsDialog extends JDialog {

    private final LibraryService service;
    private final Show show;
    private final Runnable onChange;

    public ShowDetailsDialog(Window owner, LibraryService service, Show show, Runnable onChange) {
        super(owner, "Detalhes da série", ModalityType.APPLICATION_MODAL);
        this.service = service;
        this.show = show;
        this.onChange = onChange;
        buildUi();
        pack();
        setMinimumSize(new Dimension(480, 420));
        setLocationRelativeTo(owner);
    }

    private void buildUi() {
        JPanel root = new JPanel(new BorderLayout(10, 10));
        root.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel title = new JLabel(show.getName() != null ? show.getName() : "(sem nome)");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 20f));
        root.add(title, BorderLayout.NORTH);

        JPanel infoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int row = 0;
        row = addInfoRow(infoPanel, gbc, row, "Idioma:", nullSafe(show.getLanguage()));
        row = addInfoRow(infoPanel, gbc, row, "Gêneros:", show.getGenresAsText());
        row = addInfoRow(infoPanel, gbc, row, "Nota geral:", show.getRatingAsText());
        row = addInfoRow(infoPanel, gbc, row, "Estado:", nullSafe(show.getStatus()));
        row = addInfoRow(infoPanel, gbc, row, "Data de estreia:", nullSafe(show.getPremiered()));
        row = addInfoRow(infoPanel, gbc, row, "Data de término:", nullSafe(show.getEnded()));
        row = addInfoRow(infoPanel, gbc, row, "Emissora:", nullSafe(show.getNetwork()));

        JTextArea summaryArea = new JTextArea(show.getSummary() != null ? show.getSummary() : "Sem sinopse disponível.");
        summaryArea.setLineWrap(true);
        summaryArea.setWrapStyleWord(true);
        summaryArea.setEditable(false);
        summaryArea.setOpaque(false);
        summaryArea.setFont(infoPanel.getFont());
        JScrollPane summaryScroll = new JScrollPane(summaryArea);
        summaryScroll.setBorder(BorderFactory.createTitledBorder("Sinopse"));
        summaryScroll.setPreferredSize(new Dimension(420, 120));

        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.add(infoPanel, BorderLayout.NORTH);
        centerPanel.add(summaryScroll, BorderLayout.CENTER);
        root.add(centerPanel, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        buttonsPanel.setBorder(BorderFactory.createTitledBorder("Minhas listas"));
        buttonsPanel.add(buildToggleButton(ListType.FAVORITES));
        buttonsPanel.add(buildToggleButton(ListType.WATCHED));
        buttonsPanel.add(buildToggleButton(ListType.TO_WATCH));

        JButton closeButton = new JButton("Fechar");
        closeButton.addActionListener(e -> dispose());

        JPanel south = new JPanel(new BorderLayout());
        south.add(buttonsPanel, BorderLayout.CENTER);
        JPanel closeWrap = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        closeWrap.add(closeButton);
        south.add(closeWrap, BorderLayout.SOUTH);

        root.add(south, BorderLayout.SOUTH);

        setContentPane(root);
    }

    private int addInfoRow(JPanel panel, GridBagConstraints gbc, int row, String label, String value) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0;
        JLabel labelComp = new JLabel(label);
        labelComp.setFont(labelComp.getFont().deriveFont(Font.BOLD));
        panel.add(labelComp, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        panel.add(new JLabel(value), gbc);
        return row + 1;
    }

    private String nullSafe(String s) {
        return s != null && !s.isBlank() ? s : "—";
    }


    private JButton buildToggleButton(ListType type) {
        JButton button = new JButton();
        updateToggleButtonText(button, type);
        button.addActionListener(e -> {
            try {
                boolean alreadyIn = service.isInList(type, show.getId());
                if (alreadyIn) {
                    service.removeFromList(type, show.getId());
                } else {
                    service.addToList(type, show);
                }
                updateToggleButtonText(button, type);
                if (onChange != null) {
                    onChange.run();
                }
            } catch (PersistenceException ex) {
                UiUtils.showError(this, "Erro ao salvar",
                        "Não foi possível salvar a alteração: " + ex.getMessage());
            }
        });
        return button;
    }

    private void updateToggleButtonText(JButton button, ListType type) {
        boolean in = service.isInList(type, show.getId());
        button.setText((in ? "Remover de " : "Adicionar em ") + type.getLabel());
    }
}
