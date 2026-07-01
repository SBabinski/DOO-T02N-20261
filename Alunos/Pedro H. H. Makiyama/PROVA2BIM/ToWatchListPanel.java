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

public class ToWatchListPanel extends JPanel {

    private final Font BUTTONS_DEFAULT_FONT = new Font("Arial", Font.PLAIN, 15);

    private final TvSeriesController tvSeriesController;

    private final int WIDTH = 1200;
    private final int HEIGHT = 800;

    private JPanel mainPanel;
    private JPanel toWatchNamesAndSortingsPanel;

    private JScrollPane toWatchListScrollPane;

    private final ListsPanel listsPanel;

    private JPanel sortingButtonsPanel;
    private JPanel detailsAndListsPanel;
    private JPanel detailsPanel;
    private JPanel listsButtonsPanel;

    private JButton removeToWatchButton;
    private JButton addFavoriteButton;
    private JButton addWatchedButton;
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

    private final JList<String> toWatchList = new JList<>();

    // CONSTRUCTOR \\

    public ToWatchListPanel(TvSeriesController tvSeriesController, ListsPanel listsPanel) {

        this.setLayout(new BorderLayout());

        this.tvSeriesController = tvSeriesController;

        this.listsPanel = listsPanel;

        createMainPanel();
        createToWatchNamesAndSortingsPanel();
        createToWatchListScrollPane();
        createSortingButtonsPanel();
        createNameSortingButton();
        createRatingSortingButton();
        createStatusSortingButton();
        createPremieredSortingButton();
        createDetailsAndListsPanel();
        createDetailsPanel();
        createListsButtonsPanel();
        createAddFavoriteButton();
        createRemoveToWatchButton();
        createAddWatchedButton();

        createListeners();

        updateToWatchList();
    }

    // MÉTODOS PARA CRIAR OS COMPONENTES \\

    private void createMainPanel(){

        mainPanel = new JPanel(new BorderLayout());
        this.add(mainPanel, BorderLayout.CENTER);
    }

    private void createToWatchNamesAndSortingsPanel(){

        toWatchNamesAndSortingsPanel = new JPanel(new BorderLayout());
        toWatchNamesAndSortingsPanel.setPreferredSize(new Dimension(WIDTH / 2, HEIGHT));
        mainPanel.add(toWatchNamesAndSortingsPanel, BorderLayout.WEST);
    }

    private void createToWatchListScrollPane(){

        toWatchListScrollPane = new JScrollPane();
        toWatchListScrollPane.setBorder(BorderFactory.createTitledBorder("Nomes das Séries para Assistir"));
        toWatchNamesAndSortingsPanel.add(toWatchListScrollPane, BorderLayout.CENTER);
        toWatchListScrollPane.setViewportView(toWatchList);
    }

    private void createSortingButtonsPanel(){

        sortingButtonsPanel = new JPanel(new GridLayout(1, 4));
        sortingButtonsPanel.setPreferredSize(new Dimension(WIDTH / 2, HEIGHT / 12));
        sortingButtonsPanel.setBorder(BorderFactory.createTitledBorder("Ordenar por:"));
        toWatchNamesAndSortingsPanel.add(sortingButtonsPanel, BorderLayout.SOUTH);
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
    
    private void createRemoveToWatchButton(){

        removeToWatchButton = new JButton("<html>Remover desta Lista<html>");
        removeToWatchButton.setHorizontalTextPosition(JButton.CENTER);
        removeToWatchButton.setVerticalTextPosition(JButton.CENTER);
        removeToWatchButton.setFont(BUTTONS_DEFAULT_FONT);
        listsButtonsPanel.add(removeToWatchButton);
    }

    private void createAddFavoriteButton(){

        addFavoriteButton = new JButton("<html>Adicionar às Favoritas<html>");
        addFavoriteButton.setHorizontalTextPosition(JButton.CENTER);
        addFavoriteButton.setVerticalTextPosition(JButton.CENTER);
        addFavoriteButton.setFont(BUTTONS_DEFAULT_FONT);
        listsButtonsPanel.add(addFavoriteButton);
    }

    private void createAddWatchedButton(){

        addWatchedButton = new JButton("<html>Adicionar às Assistidas<html>");
        addWatchedButton.setHorizontalTextPosition(JButton.CENTER);
        addWatchedButton.setVerticalTextPosition(JButton.CENTER);
        addWatchedButton.setFont(BUTTONS_DEFAULT_FONT);
        listsButtonsPanel.add(addWatchedButton);
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

        removeToWatchButton.addActionListener(
            event -> removeSelectedSeries()
        );

        addFavoriteButton.addActionListener(
            event -> addSelectedToFavorites()
        );

        addWatchedButton.addActionListener(
            event -> addSelectedToWatched()
        );

        toWatchList.addListSelectionListener(event -> {

            if (!event.getValueIsAdjusting()) {

                showSelectedSeriesDetails();
            }
        });
    }

    // SELECIONAR SÉRIE E ATUALIZAR LISTA \\

    private void updateToWatchList() {

        List<String> names = tvSeriesController.getToWatch()
                .stream()
                .map(TvSeries::getName)
                .toList();

        toWatchList.setListData(
                names.toArray(new String[0])
        );
    }

    private TvSeries getSelectedSeries() {

        int index = toWatchList.getSelectedIndex();

        if (index == -1) {

            return null;
        }

        return tvSeriesController
                .getToWatch()
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

        updateToWatchList();
        clearDetails();
        toWatchList.clearSelection();
    }

    // MÉTODOS DE ORDENAÇÃO \\

    private void sortByName() {

        try {
            
            tvSeriesController.sortByName(tvSeriesController.getToWatch());

            updateToWatchList();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                this,
                "Não foi possível ordenar a lista de séries para assistir.",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }

    private void sortByRating() {

        try {

            tvSeriesController.sortByRating(tvSeriesController.getToWatch());

            updateToWatchList();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                this,
                "Não foi possível ordenar a lista de séries para assistir.",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }

    private void sortByStatus() {

        try {

            tvSeriesController.sortByStatus(tvSeriesController.getToWatch());

            updateToWatchList();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                this,
                "Não foi possível ordenar a lista de séries para assistir.",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }  
    }

    private void sortByPremiered() {

        try {

            tvSeriesController.sortByPremiered(tvSeriesController.getToWatch());

            updateToWatchList();
        
        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                this,
                "Não foi possível ordenar a lista de séries para assistir.",
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
            
            tvSeriesController.removeToWatch(selected);

            listsPanel.refreshAll();

            JOptionPane.showMessageDialog(
                this,
                "Série removida da lista para assistir."
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
