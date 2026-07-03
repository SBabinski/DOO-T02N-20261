package br.com.serietracker;

import br.com.serietracker.view.MainFrame;

public class Main {

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(() -> {
            new MainFrame();
        });

    }
}