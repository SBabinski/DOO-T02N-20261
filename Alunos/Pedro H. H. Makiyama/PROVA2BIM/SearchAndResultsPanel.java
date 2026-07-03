package com.pedrohhm.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.pedrohhm.controller.TvSeriesController;
import com.pedrohhm.controller.UserController;
import com.pedrohhm.exception.ApiConnectionException;
import com.pedrohhm.exception.DuplicateSeriesException;
import com.pedrohhm.exception.InvalidSearchException;
import com.pedrohhm.exception.TvMazeException;
import com.pedrohhm.model.TvSeries;

public class SearchAndResultsPanel extends JPanel {

    private final Font DEFAULT_FONT = new Font("Arial", Font.PLAIN, 20);
    private final Font USERNAME_LABEL_FONT = new Font("Arial", Font.BOLD, 24);
    private final Font BUTTONS_DEFAULT_FONT = new Font("Arial", Font.PLAIN, 15);

    private final UserController userController;
    private final TvSeriesController tvSeriesController;

    private final ListsPanel listsPanel;

    private final int WIDTH = 1200;
    private final int HEIGHT = 800;

    private JPanel mainPanel;
    private JPanel usernameAndSearchPanel;
    
    private JPanel searchPanel;
    private JPanel resultsNamesAndSortingsPanel;
    private JPanel sortingButtonsPanel;
    private JPanel detailsAndListsPanel;
    private JPanel detailsPanel;
    private JPanel listsButtonsPanel;

    private JLabel usernameLabel;
    private JLabel nameValueLabel;
    private JLabel languageValueLabel;
    private JLabel genresValueLabel;
    private JLabel ratingValueLabel;
    private JLabel statusValueLabel;
    private JLabel premieredValueLabel;
    private JLabel endedValueLabel;
    private JLabel broadcasterValueLabel;

    private JButton addFavoriteButton;
    private JButton addToWatchButton;
    private JButton addWatchedButton;
    private JButton searchButton;
    private JButton nameSortingButton;
    private JButton ratingSortingButton;
    private JButton statusSortingButton;
    private JButton premieredSortingButton;

    private JTextField searchField;
    
    private JScrollPane resultsNamesScrollPane;
    
    private final JList<String> resultsList = new JList<>();
    
    // CONSTRUCTOR \\

    public SearchAndResultsPanel(UserController userController,
         TvSeriesController tvSeriesController, 
         ListsPanel listsPanel) {
        
        this.setLayout(new BorderLayout());

        this.userController = userController;
        this.tvSeriesController = tvSeriesController;

        this.listsPanel = listsPanel;

        createMainPanel();
        createUsernameAndSearchPanel();
        createUsernameLabel();  
        createSearchPanel();
        createSearchField();
        createSearchButton();
        createResultsNamesAndSortingsPanel();
        createResultsNamesScrollPane();
        createSortingButtonsPanel();
        createNameSortingButton();
        createRatingSortingButton();
        createStatusSortingButton();
        createPremieredSortingButton();
        createDetailsAndListsPanel();
        createDetailsPanel();
        createAddToListsButtonsPanel();
        createAddFavoriteButton();
        createAddToWatchButton();
        createAddWatchedButton();
        

        createListeners();
    }

    // MÉTODOS PARA CRIAR OS COMPONENTES \\

    private void createMainPanel(){

        mainPanel = new JPanel(new BorderLayout());
        this.add(mainPanel, BorderLayout.CENTER);
    }

    private void createUsernameAndSearchPanel(){

        usernameAndSearchPanel = new JPanel(new BorderLayout());
        usernameAndSearchPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT / 6));
        mainPanel.add(usernameAndSearchPanel, BorderLayout.NORTH);
    }

    private void createUsernameLabel(){

        usernameLabel = new JLabel("Username: " + userController.getUsername());
        usernameLabel.setPreferredSize(new Dimension(WIDTH, HEIGHT / 12));
        usernameLabel.setHorizontalAlignment(JLabel.CENTER);
        usernameLabel.setVerticalAlignment(JLabel.CENTER);
        usernameLabel.setFont(USERNAME_LABEL_FONT);
        usernameAndSearchPanel.add(usernameLabel, BorderLayout.NORTH);
    }

    private void createSearchPanel(){

        searchPanel = new JPanel(new BorderLayout());
        searchPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT / 12));
        searchPanel.setBorder(BorderFactory.createTitledBorder("Pesquisar Séries de TV"));
        usernameAndSearchPanel.add(searchPanel, BorderLayout.CENTER);
    }

    private void createSearchField(){

        searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(WIDTH - 300, HEIGHT / 12));
        searchField.setFont(DEFAULT_FONT);
        searchPanel.add(searchField, BorderLayout.WEST);
    }

    private void createSearchButton(){

        searchButton = new JButton("Pesquisar");
        searchButton.setFont(DEFAULT_FONT);
        searchPanel.add(searchButton, BorderLayout.CENTER);
    }

    private void createResultsNamesAndSortingsPanel(){

        resultsNamesAndSortingsPanel = new JPanel(new BorderLayout());
        resultsNamesAndSortingsPanel.setPreferredSize(new Dimension(WIDTH / 2, HEIGHT));
        mainPanel.add(resultsNamesAndSortingsPanel, BorderLayout.WEST);
    }
    
    private void createResultsNamesScrollPane(){

        resultsNamesScrollPane = new JScrollPane();
        resultsNamesScrollPane.setBorder(BorderFactory.createTitledBorder("Resultados"));
        resultsNamesScrollPane.setViewportView(resultsList);
        resultsNamesAndSortingsPanel.add(resultsNamesScrollPane, BorderLayout.CENTER);
    }

    private void createSortingButtonsPanel(){

        sortingButtonsPanel = new JPanel(new GridLayout(1, 4));
        sortingButtonsPanel.setPreferredSize(new Dimension(WIDTH / 2, HEIGHT / 12));
        sortingButtonsPanel.setBorder(BorderFactory.createTitledBorder("Ordenar por:"));
        resultsNamesAndSortingsPanel.add(sortingButtonsPanel, BorderLayout.SOUTH);
    }

    private void createNameSortingButton(){

        nameSortingButton = new JButton("<html>Nome<html>");
        nameSortingButton.setFont(BUTTONS_DEFAULT_FONT);
        sortingButtonsPanel.add(nameSortingButton);
    }

    private void createRatingSortingButton(){

        ratingSortingButton = new JButton("<html>Nota<html>");
        ratingSortingButton.setFont(BUTTONS_DEFAULT_FONT);
        sortingButtonsPanel.add(ratingSortingButton);
    }

    private void createStatusSortingButton(){

        statusSortingButton = new JButton("<html>Estado<html>");
        statusSortingButton.setFont(BUTTONS_DEFAULT_FONT);
        sortingButtonsPanel.add(statusSortingButton);
    }

    private void createPremieredSortingButton(){

        premieredSortingButton = new JButton("<html>Data de Lançamento<html>");
        premieredSortingButton.setFont(BUTTONS_DEFAULT_FONT);
        sortingButtonsPanel.add(premieredSortingButton);
    }

    private void createDetailsAndListsPanel(){

        detailsAndListsPanel = new JPanel(new BorderLayout());
        mainPanel.add(detailsAndListsPanel, BorderLayout.CENTER);
    }

    private void createDetailsPanel() {

        detailsPanel = new JPanel(new GridLayout(8, 2, 10, 10));
        detailsPanel.setBorder(
            BorderFactory.createTitledBorder("Detalhes")
        );

        Font titleFont = new Font("Arial", Font.BOLD, 16);
        Font valueFont = new Font("Arial", Font.PLAIN, 16);

        // Nome
        JLabel nameTitle = new JLabel("Nome:");
        nameTitle.setFont(titleFont);

        nameValueLabel = new JLabel("-");
        nameValueLabel.setFont(valueFont);

        // Idioma
        JLabel languageTitle = new JLabel("Idioma:");
        languageTitle.setFont(titleFont);

        languageValueLabel = new JLabel("-");
        languageValueLabel.setFont(valueFont);

        // Gêneros
        JLabel genresTitle = new JLabel("Gêneros:");
        genresTitle.setFont(titleFont);

        genresValueLabel = new JLabel("-");
        genresValueLabel.setFont(valueFont);

        // Nota
        JLabel ratingTitle = new JLabel("Nota:");
        ratingTitle.setFont(titleFont);

        ratingValueLabel = new JLabel("-");
        ratingValueLabel.setFont(valueFont);

        // Estado
        JLabel statusTitle = new JLabel("Estado:");
        statusTitle.setFont(titleFont);

        statusValueLabel = new JLabel("-");
        statusValueLabel.setFont(valueFont);

        // Estreia
        JLabel premieredTitle = new JLabel("Estreia:");
        premieredTitle.setFont(titleFont);

        premieredValueLabel = new JLabel("-");
        premieredValueLabel.setFont(valueFont);

        // Fim
        JLabel endedTitle = new JLabel("Fim:");
        endedTitle.setFont(titleFont);

        endedValueLabel = new JLabel("-");
        endedValueLabel.setFont(valueFont);

        // Emissora
        JLabel broadcasterTitle = new JLabel("Canal/Plataforma:");
        broadcasterTitle.setFont(titleFont);

        broadcasterValueLabel = new JLabel("-");
        broadcasterValueLabel.setFont(valueFont);

        detailsPanel.add(nameTitle);
        detailsPanel.add(nameValueLabel);

        detailsPanel.add(languageTitle);
        detailsPanel.add(languageValueLabel);

        detailsPanel.add(genresTitle);
        detailsPanel.add(genresValueLabel);

        detailsPanel.add(ratingTitle);
        detailsPanel.add(ratingValueLabel);

        detailsPanel.add(statusTitle);
        detailsPanel.add(statusValueLabel);

        detailsPanel.add(premieredTitle);
        detailsPanel.add(premieredValueLabel);

        detailsPanel.add(endedTitle);
        detailsPanel.add(endedValueLabel);

        detailsPanel.add(broadcasterTitle);
        detailsPanel.add(broadcasterValueLabel);

        detailsAndListsPanel.add(detailsPanel, BorderLayout.CENTER);
    }

    private void createAddToListsButtonsPanel(){

        listsButtonsPanel = new JPanel(new GridLayout(1, 3));
        listsButtonsPanel.setPreferredSize(new Dimension(WIDTH / 2, HEIGHT / 12));
        listsButtonsPanel.setBorder(BorderFactory.createTitledBorder("Adicionar a: "));
        detailsAndListsPanel.add(listsButtonsPanel, BorderLayout.SOUTH);
    }

    private void createAddFavoriteButton(){

        addFavoriteButton = new JButton("<html>Favoritas<html>");
        addFavoriteButton.setFont(BUTTONS_DEFAULT_FONT);
        listsButtonsPanel.add(addFavoriteButton);
    }

    private void createAddWatchedButton(){

        addWatchedButton = new JButton("<html>Assistidas<html>");
        addWatchedButton.setFont(BUTTONS_DEFAULT_FONT);
        listsButtonsPanel.add(addWatchedButton);
    }

    private void createAddToWatchButton(){

        addToWatchButton = new JButton("<html>Para Assistir<html>");
        addToWatchButton.setFont(BUTTONS_DEFAULT_FONT);
        listsButtonsPanel.add(addToWatchButton);
    }

    // LISTENERS \\

    private void createListeners() {

        searchButton.addActionListener(event -> searchTvSeries());

        searchField.addActionListener(
            e -> searchTvSeries()
        );

        nameSortingButton.addActionListener(
            event -> sortByName()
        );

        ratingSortingButton.addActionListener(
            event -> sortByRating()
        );

        statusSortingButton.addActionListener(
            event -> sortByStatus()
        );

        premieredSortingButton.addActionListener(
            event -> sortByPremiered()
        );

        addFavoriteButton.addActionListener(
            event -> addSelectedToFavorites()
        );

        addToWatchButton.addActionListener(
            event -> addSelectedToWatch()
        );

        addWatchedButton.addActionListener(
            event -> addSelectedToWatched()
        );

        resultsList.addListSelectionListener(
            event -> {

                if (!event.getValueIsAdjusting()) {

                    showSelectedSeriesDetails();
                }
            }
        );
    }

    // PROCURAR SÉRIES E ATUALIZAR LISTA DE RESULTADOS \\

    private void searchTvSeries() {

        try {

            tvSeriesController.searchTvSeries(
                searchField.getText()
            );

            updateResultsList();
            clearDetails();
            resultsList.clearSelection();

        } 
        catch (InvalidSearchException e) {

            resultsList.setListData(new String[0]);
            clearDetails();
            
            JOptionPane.showMessageDialog(
                this,
                e.getMessage(),
                "Pesquisa inválida",
                JOptionPane.WARNING_MESSAGE
            );
        }
        catch (TvMazeException e) {

            resultsList.setListData(new String[0]);
            clearDetails();

            JOptionPane.showMessageDialog(
                this,
                e.getMessage(),
                "Erro na pesquisa",
                JOptionPane.ERROR_MESSAGE
            );

            resultsList.setListData(new String[0]);
        }
        catch (ApiConnectionException e) {

            resultsList.setListData(new String[0]);
            clearDetails();
            
            JOptionPane.showMessageDialog(
                this,
                "Não foi possível conectar à internet.",
                "Sem conexão",
                JOptionPane.ERROR_MESSAGE
            );

            clearDetails();

            resultsList.clearSelection();
        }
        catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                this,
                "Ocorreu um erro inesperado.",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void updateResultsList() {

        List<String> names =
            tvSeriesController.getCurrentSearchResults()
                .stream()
                .map(TvSeries::getName)
                .toList();

        resultsList.setListData(
            names.toArray(new String[0])
        );
    }

    // MÉTODOS DE ORDENAÇÃO \\

    private void sortByName() {

        try {

            tvSeriesController.sortByName(tvSeriesController.getCurrentSearchResults());

            updateResultsList();
        }
        catch (Exception e) {

            JOptionPane.showMessageDialog(
                this,
                "Não foi possível ordenar os resultados.",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }

    private void sortByRating() {

        try {

            tvSeriesController.sortByRating(tvSeriesController.getCurrentSearchResults());

            updateResultsList();
        }
        catch (Exception e) {

            JOptionPane.showMessageDialog(
                this,
                "Não foi possível ordenar os resultados.",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }

    private void sortByStatus() {

        try {

            tvSeriesController.sortByStatus(tvSeriesController.getCurrentSearchResults());

            updateResultsList();
        }
        catch (Exception e) {

            JOptionPane.showMessageDialog(
                this,
                "Não foi possível ordenar os resultados.",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }

    private void sortByPremiered() {

        try {

            tvSeriesController.sortByPremiered(tvSeriesController.getCurrentSearchResults());

            updateResultsList();
        }
        catch (Exception e) {

            JOptionPane.showMessageDialog(
                this,
                "Não foi possível ordenar os resultados.",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }

    // SELECIONAR SÉRIES E MOSTRAR E LIMPAR DETALHES \\

    private TvSeries getSelectedSeries() {

        int index = resultsList.getSelectedIndex();

        if (index == -1) {

            return null;
        }

        return tvSeriesController
                .getCurrentSearchResults()
                .get(index);
    }

    private void showSelectedSeriesDetails() {

        TvSeries selected = getSelectedSeries();

        if (selected == null) {

            clearDetails();

            return;
        }

        nameValueLabel.setText(selected.getName());

        languageValueLabel.setText(
            selected.getLanguage() != null
                    ? selected.getLanguage()
                    : "-"
        );

        genresValueLabel.setText(
            selected.getGenres() != null && selected.getGenres().isEmpty() == false
                ? String.join(", ", selected.getGenres())
                : "-"
        );

        if (selected.getRating() != null &&
            selected.getRating().getAverage() != null) {

            ratingValueLabel.setText(
                String.format("%.1f",
                    selected.getRating().getAverage()));
        }
        else {

            ratingValueLabel.setText("Sem nota");
        }

        statusValueLabel.setText(
            selected.getStatus() != null
                    ? selected.getStatus()
                    : "-"
        );

        premieredValueLabel.setText(
            selected.getPremiered() != null
                    ? selected.getPremiered().toString()
                    : "-"
        );

        endedValueLabel.setText(
            selected.getEnded() != null
                    ? selected.getEnded().toString()
                    : "-"
        );

        broadcasterValueLabel.setText(
            selected.getBroadcasterName()
        );
    }

    private void clearDetails() {

        nameValueLabel.setText("-");
        languageValueLabel.setText("-");
        genresValueLabel.setText("-");
        ratingValueLabel.setText("-");
        statusValueLabel.setText("-");
        premieredValueLabel.setText("-");
        endedValueLabel.setText("-");
        broadcasterValueLabel.setText("-");
    }

    // ADICIONAR E REMOVER DE LISTAS \\

    private void addSelectedToFavorites() {

        TvSeries selected = getSelectedSeries();

        if (selected == null) {

            return;
        }
        
        try {

            tvSeriesController.addFavorite(selected);

            JOptionPane.showMessageDialog(
                this,
                "Série adicionada aos favoritos."
            );

            listsPanel.refreshAll();

        }
        catch (DuplicateSeriesException e) {

            JOptionPane.showMessageDialog(
                this,
                e.getMessage(),
                "Aviso",
                JOptionPane.WARNING_MESSAGE
            );
        }

        catch (Exception e) {

            JOptionPane.showMessageDialog(
                this,
                "Não foi possível adicionar a série.",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }

    private void addSelectedToWatch() {

        TvSeries selected = getSelectedSeries();

        if (selected == null) {

            return;
        }

        try {

            tvSeriesController.addToWatch(selected);

            JOptionPane.showMessageDialog(
                this,
                "Série adicionada à lista para assistir."
            );

            listsPanel.refreshAll();

        }
        catch (DuplicateSeriesException e) {

            JOptionPane.showMessageDialog(
                this,
                e.getMessage(),
                "Aviso",
                JOptionPane.WARNING_MESSAGE
            );
        }

        catch (Exception e) {

            JOptionPane.showMessageDialog(
                this,
                "Não foi possível adicionar a série.",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }

    private void addSelectedToWatched() {

        TvSeries selected = getSelectedSeries();

        if (selected == null) {

            return;
        }

        try {

            tvSeriesController.addWatched(selected);

            JOptionPane.showMessageDialog(
                this,
                "Série adicionada às assistidas."
            );

            listsPanel.refreshAll();

        }
        catch (DuplicateSeriesException e) {

            JOptionPane.showMessageDialog(
                this,
                e.getMessage(),
                "Aviso",
                JOptionPane.WARNING_MESSAGE
            );
        }

        catch (Exception e) {

            JOptionPane.showMessageDialog(
                this,
                "Não foi possível adicionar a série.",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }
}   