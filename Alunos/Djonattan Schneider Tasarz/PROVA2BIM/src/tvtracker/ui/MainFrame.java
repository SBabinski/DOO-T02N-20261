package tvtracker.ui;

import java.awt.*;
import javax.swing.*;
import tvtracker.model.ListType;
import tvtracker.persistence.PersistenceException;
import tvtracker.service.LibraryService;


public class MainFrame extends JFrame {

    private final LibraryService service;

    private final JLabel userLabel = new JLabel();
    private ListsPanel favoritesPanel;
    private ListsPanel watchedPanel;
    private ListsPanel toWatchPanel;

    public MainFrame(LibraryService service) {
        super("TV Tracker — Acompanhamento de Séries (TVMaze)");
        this.service = service;
        buildUi();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
    }

    private void buildUi() {
        setJMenuBar(buildMenuBar());

        JPanel root = new JPanel(new BorderLayout());

        JPanel header = new JPanel(new BorderLayout());
        header.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        updateUserLabel();
        userLabel.setFont(userLabel.getFont().deriveFont(Font.BOLD));
        header.add(userLabel, BorderLayout.WEST);
        root.add(header, BorderLayout.NORTH);

        JTabbedPane tabs = new JTabbedPane();

        SearchPanel searchPanel = new SearchPanel(this, service);
        favoritesPanel = new ListsPanel(this, service, ListType.FAVORITES);
        watchedPanel = new ListsPanel(this, service, ListType.WATCHED);
        toWatchPanel = new ListsPanel(this, service, ListType.TO_WATCH);

        tabs.addTab("Buscar séries", searchPanel);
        tabs.addTab(ListType.FAVORITES.getLabel(), favoritesPanel);
        tabs.addTab(ListType.WATCHED.getLabel(), watchedPanel);
        tabs.addTab(ListType.TO_WATCH.getLabel(), toWatchPanel);

        
        tabs.addChangeListener(e -> refreshAllLists());

        root.add(tabs, BorderLayout.CENTER);
        setContentPane(root);
    }

    private JMenuBar buildMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu userMenu = new JMenu("Usuário");
        JMenuItem switchUserItem = new JMenuItem("Trocar usuário...");
        switchUserItem.addActionListener(e -> openSwitchUserDialog());
        userMenu.add(switchUserItem);
        menuBar.add(userMenu);

        JMenu helpMenu = new JMenu("Ajuda");
        JMenuItem aboutItem = new JMenuItem("Sobre");
        aboutItem.addActionListener(e -> UiUtils.showInfo(this, "Sobre o TV Tracker",
                "TV Tracker — sistema de acompanhamento de séries de TV.\n"
                        + "Dados fornecidos pela API pública do TVMaze (https://www.tvmaze.com/api).\n\n"
                        + "Arquivo local de dados:\n" + service.getDataFileLocation()));
        helpMenu.add(aboutItem);
        menuBar.add(helpMenu);

        return menuBar;
    }

    private void openSwitchUserDialog() {
        LoginDialog dialog = new LoginDialog(this, service.getAllUserNames(), service.getCurrentUser().getName());
        dialog.setVisible(true);
        if (dialog.isConfirmed()) {
            try {
                service.switchUser(dialog.getSelectedUserName());
                updateUserLabel();
                refreshAllLists();
            } catch (PersistenceException | IllegalArgumentException ex) {
                UiUtils.showError(this, "Erro ao trocar de usuário", ex.getMessage());
            }
        }
    }

    private void updateUserLabel() {
        userLabel.setText("Usuário atual: " + service.getCurrentUser().getName());
    }

    private void refreshAllLists() {
        if (favoritesPanel != null) favoritesPanel.refresh();
        if (watchedPanel != null) watchedPanel.refresh();
        if (toWatchPanel != null) toWatchPanel.refresh();
    }
}
