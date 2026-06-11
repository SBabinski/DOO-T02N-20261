import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class EfeitosUI {
    
    private static Timer timerAlerta;

    public static void piscarAlerta(JTextField componente) {
        Color corOriginal = Color.WHITE;
        Color corAlerta = new Color(255, 200, 200);
        Toolkit.getDefaultToolkit().beep();

        if (timerAlerta != null && timerAlerta.isRunning()) {
            timerAlerta.stop();
        }

        componente.setBackground(corAlerta);

        timerAlerta = new Timer(300, e -> {
            componente.setBackground(corOriginal);
        });
        
        timerAlerta.setRepeats(false);
        timerAlerta.start();
    }

    public static String formatar(double numero) {
        DecimalFormat df = new DecimalFormat("###,###.##");
        return df.format(numero);
    }
}