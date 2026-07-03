import javax.swing.SwingUtilities;

import Telas.TelaLogin;

public class Principal {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TelaLogin();
        });
    }
}