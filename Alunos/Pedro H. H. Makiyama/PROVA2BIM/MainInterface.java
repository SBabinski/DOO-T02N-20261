package com.pedrohhm.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import com.pedrohhm.controller.TvSeriesController;
import com.pedrohhm.controller.UserController;

public class MainInterface {

    private final int WIDTH = 1200;
    private final int HEIGHT = 800;

    private final JFrame frame;

    private final JTabbedPane mainTabs;

    private final ListsPanel listsPanel;
    

    public MainInterface(
        UserController userController, 
        TvSeriesController tvSeriesController) {
        
        listsPanel = new ListsPanel(tvSeriesController);

        frame = new JFrame("Interface Principal");
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setResizable(false);

        mainTabs = new JTabbedPane();

        mainTabs.addTab(
                "Busca e Resultados",
                new SearchAndResultsPanel(
                        userController, 
                        tvSeriesController, 
                        listsPanel));

        mainTabs.addTab(
                "Listas",
                listsPanel);
        
        frame.add(mainTabs, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);
    }

    public void show() {

        frame.setVisible(true);
    }
}