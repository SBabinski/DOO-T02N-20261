package clima;

import clima.ui.InterfaceGrafica;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InterfaceGrafica::new);
    }
}