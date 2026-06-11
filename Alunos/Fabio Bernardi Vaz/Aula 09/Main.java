package fag;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculadoraFrame frame = new CalculadoraFrame();
            frame.apresentarse();
        });
    }
}