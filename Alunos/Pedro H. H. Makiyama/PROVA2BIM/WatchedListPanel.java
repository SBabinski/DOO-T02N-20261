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

import com.pedrohhm.controller.TvSeriesController;
import com.pedrohhm.exception.DuplicateSeriesException;
import com.pedrohhm.model.TvSeries;

public class WatchedListPanel extends JPanel {

    private final Font BUTTONS_DEFAULT_FONT = new Font("Arial", Font.PLAIN, 15);

    private final TvSeriesController tvSeriesController;

    private final int WIDTH = 1200;
    private final int HEIGHT = 800;

    private final ListsPanel listsPanel;

    private JPanel mainPanel;
    private JPanel watchedNamesAndSortingsPanel;

    private JScrollPane watchedListScrollPane;

    private JPanel sortingButtonsPanel;
    private JPanel detailsAndListsPanel;
    private JPanel detailsPanel;
    private JPanel listsButtonsPanel;

    private JButton addToWatchButton;
    private JButton addFavoriteButton;
    private JButton removeWatchedButton;
    private JButton nameSortingButton;
    private JButton ratingSortingButton;
    private JButton statusSortingButton;
    private JButton premieredSortingButton;

    private JLabel nameValueLabel;
    private JLabel languageValueLabel;
    private JLabel genresValueLabel;
    private JLabel ratingValueLabel;
    private JLabel statusValueLabel;
    private JLabel premieredValueLabel;
    private JLabel endedValueLabel;
    private JLabel broadcasterValueLabel;

    private final JList<String> watchedList = new JList<>();

    // CONSTRUCTOR \\

    public WatchedListPanel(TvSeriesController tvSeriesController, ListsPanel listsPanel) {

        this.setLayout(new BorderLayout());

        this.tvSeriesController = tvSeriesController;

        this.listsPanel = listsPanel;

        createMainPanel();
        createWatchedNamesAndSortingsPanel();
        createWatchedListScrollPane();
        createSortingButtonsPanel();
        createNameSortingButton();
        createRatingSortingButton();
        createStatusSortingButton();
        createPremieredSortingButton();
        createDetailsAndListsPanel();
        createDetailsPanel();
        createListsButtonsPanel();
        createAddFavoriteButton();
        createAddToWatchButton();
        createRemoveWatchedButton();

        createListeners();

        updateWatchedList();
    }

    // MÉTODOS PARA CRIAR OS COMPONENTES \\

    private void createMainPanel(){

        mainPanel = new JPanel(new BorderLayout());
        this.add(mainPanel, BorderLayout.CENTER);
    }

    private void createWatchedNamesAndSortingsPanel(){

        watchedNamesAndSortingsPanel = new JPanel(new BorderLayout());
        watchedNamesAndSortingsPanel.setPreferredSize(new Dimension(WIDTH / 2, HEIGHT));
        mainPanel.add(watchedNamesAndSortingsPanel, BorderLayout.WEST);
    }

    private void createWatchedListScrollPane(){

        watchedListScrollPane = new JScrollPane();
        watchedListScrollPane.setBorder(BorderFactory.createTitledBorder("Nomes das Séries Assistidas"));
        watchedNamesAndSortingsPanel.add(watchedListScrollPane, BorderLayout.CENTER);
        watchedListScrollPane.setViewportView(watchedList);
    }

    private void createSortingButtonsPanel(){

        sortingButtonsPanel = new JPanel(new GridLayout(1, 4));
        sortingButtonsPanel.setPreferredSize(new Dimension(WIDTH / 2, HEIGHT / 12));
        sortingButtonsPanel.setBorder(BorderFactory.createTitledBorder("Ordenar por:"));
        watchedNamesAndSortingsPanel.add(sortingButtonsPanel, BorderLayout.SOUTH);
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

    private void createListsButtonsPanel(){

        listsButtonsPanel = new JPanel(new GridLayout(1, 3));
        listsButtonsPanel.setPreferredSize(new Dimension(WIDTH / 2, HEIGHT / 12));
        listsButtonsPanel.setBorder(BorderFactory.createTitledBorder("Adicionar/Remover de Listas"));
        detailsAndListsPanel.add(listsButtonsPanel, BorderLayout.SOUTH);
    }
    
    private void createAddToWatchButton(){

        addToWatchButton = new JButton("<html>Adicionar às para Assistir<html>");
        addToWatchButton.setHorizontalTextPosition(JButton.CENTER);
        addToWatchButton.setVerticalTextPosition(JButton.CENTER);
        addToWatchButton.setFont(BUTTONS_DEFAULT_FONT);
        listsButtonsPanel.add(addToWatchButton);
    }

    private void createAddFavoriteButton(){

        addFavoriteButton = new JButton("<html>Adicionar às Favoritas<html>");
        addFavoriteButton.setHorizontalTextPosition(JButton.CENTER);
        addFavoriteButton.setVerticalTextPosition(JButton.CENTER);
        addFavoriteButton.setFont(BUTTONS_DEFAULT_FONT);
        listsButtonsPanel.add(addFavoriteButton);
    }

    private void createRemoveWatchedButton(){

        removeWatchedButton = new JButton("<html>Remover desta Lista<html>");
        removeWatchedButton.setHorizontalTextPosition(JButton.CENTER);
        removeWatchedButton.setVerticalTextPosition(JButton.CENTER);
        removeWatchedButton.setFont(BUTTONS_DEFAULT_FONT);
        listsButtonsPanel.add(removeWatchedButton);
    }

    // CRIAR LISTENERS \\

    private void createListeners() {

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

        addToWatchButton.addActionListener(
            event -> addSelectedToWatch()
        );

        addFavoriteButton.addActionListener(
            event -> addSelectedToFavorites()
        );

        removeWatchedButton.addActionListener(
            event -> removeSelectedSeries()
        );

        watchedList.addListSelectionListener(event -> {

            if (!event.getValueIsAdjusting()) {

                showSelectedSeriesDetails();
            }
        });
    }

    // SELECIONAR SÉRIE E ATUALIZAR LISTA \\

    private void updateWatchedList() {

        List<String> names = tvSeriesController.getWatched()
                .stream()
                .map(TvSeries::getName)
                .toList();

        watchedList.setListData(
                names.toArray(new String[0])
        );
    }

    private TvSeries getSelectedSeries() {

        int index = watchedList.getSelectedIndex();

        if (index == -1) {

            return null;
        }

        return tvSeriesController
                .getWatched()
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

    public void refresh() {

        updateWatchedList();
        clearDetails();
        watchedList.clearSelection();
    }
    
    // MÉTODOS DE ORDENAÇÃO \\

    private void sortByName() {

        try {
            
            tvSeriesController.sortByName(tvSeriesController.getWatched());

            updateWatchedList();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                this,
                "Não foi possível ordenar a lista de séries assistidas.",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }

    private void sortByRating() {

        try {

            tvSeriesController.sortByRating(tvSeriesController.getWatched());

            updateWatchedList();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                this,
                "Não foi possível ordenar a lista de séries assistidas.",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }

    private void sortByStatus() {

        try {

            tvSeriesController.sortByStatus(tvSeriesController.getWatched());

            updateWatchedList();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                this,
                "Não foi possível ordenar a lista de séries assistidas.",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }  
    }

    private void sortByPremiered() {

        try {

            tvSeriesController.sortByPremiered(tvSeriesController.getWatched());

            updateWatchedList();
        
        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                this,
                "Não foi possível ordenar a lista de séries assistidas.",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }

    // MÉTODOS PARA ADICIONAR E REMOVER DE LISTAS \\

    private void removeSelectedSeries() {

        TvSeries selected = getSelectedSeries();

        if (selected == null) {

            return;
        }

        try {
            
            tvSeriesController.removeWatched(selected);

            listsPanel.refreshAll();

            JOptionPane.showMessageDialog(
                this,
                "Série removida da lista de séries assistidas."
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                this,
                "Não foi possível remover a série.",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }

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
}
