package com.pedrohhm.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.pedrohhm.controller.TvSeriesController;

public class ListsPanel extends JPanel {

    private final JTabbedPane tabs;

    private final FavoritesListPanel favoritesPanel;
    private final ToWatchListPanel toWatchPanel;
    private final WatchedListPanel watchedPanel;

    public ListsPanel(TvSeriesController tvSeriesController) {

        setLayout(new BorderLayout());

        tabs = new JTabbedPane();

        tabs.addTab(
                "Favoritas",
                favoritesPanel = new FavoritesListPanel(tvSeriesController, this));

        tabs.addTab(
                "Para Assistir",
                toWatchPanel = new ToWatchListPanel(tvSeriesController, this));

        tabs.addTab(
                "Assistidas",
                watchedPanel = new WatchedListPanel(tvSeriesController, this));

        add(tabs, BorderLayout.CENTER);
    }

    public void refreshAll() {

        favoritesPanel.refresh();
        toWatchPanel.refresh();
        watchedPanel.refresh();
    }
}