package app;

import app.ui.TelaPrincipal;

public class Main {

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(() -> {
            new TelaPrincipal();
        });

    }
}