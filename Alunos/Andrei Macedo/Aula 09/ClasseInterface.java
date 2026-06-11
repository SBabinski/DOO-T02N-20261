import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter; 
import java.awt.event.KeyEvent;   

public class ClasseInterface extends JFrame{

    public ClasseInterface(){
        setTitle("Calculadora Java");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JTextField visor = new JTextField("0");
        visor.setFont(new Font("Arial", Font.BOLD, 50));
        visor.setHorizontalAlignment(JTextField.RIGHT);
        visor.setEditable(false); 
        visor.setFocusable(true);

        visor.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                EfeitosUI.piscarAlerta(visor);
            }
        });

        add(visor, BorderLayout.NORTH);
        
        JTextArea areaHistorico = new JTextArea(6, 10);
        areaHistorico.setEditable(false); 
        areaHistorico.setBackground(new Color(245, 245, 245)); 

        JScrollPane scroll = new JScrollPane(areaHistorico);
        scroll.setBorder(BorderFactory.createTitledBorder("Histórico")); 

        add(scroll, BorderLayout.EAST);

        PanelTeclado teclado = new PanelTeclado(visor, areaHistorico);
        add(teclado, BorderLayout.CENTER);

        add(teclado, BorderLayout.CENTER);
        setVisible(true);
    } 
}

