import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {}

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("ClimaApp");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(450, 320);
            frame.setLayout(new BorderLayout());

            try {
                ImageIcon iconeApp = new ImageIcon("icons/clear-day.png");
                frame.setIconImage(iconeApp.getImage());
            } catch (Exception e) {}

            JPanel painelTextos = new JPanel(new GridLayout(2, 1, 0, 15));
            painelTextos.setBorder(BorderFactory.createEmptyBorder(45, 35, 25, 35));
            painelTextos.setBackground(new Color(245, 247, 250));

            JLabel labelTitulo = new JLabel("Bem-vindo ao ClimaApp!", SwingConstants.CENTER);
            labelTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
            labelTitulo.setForeground(new Color(33, 43, 54));

            JLabel labelInstrucao = new JLabel("Para iniciar uma consulta clique no botão abaixo.", SwingConstants.CENTER);
            labelInstrucao.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            labelInstrucao.setForeground(new Color(99, 115, 129));

            painelTextos.add(labelTitulo);
            painelTextos.add(labelInstrucao);

            JPanel painelBotao = new JPanel();
            painelBotao.setBackground(new Color(245, 247, 250));
            painelBotao.setBorder(BorderFactory.createEmptyBorder(0, 0, 45, 0));

            JButton btnIniciar = new JButton("Iniciar Consulta");
            btnIniciar.setFont(new Font("Segoe UI", Font.BOLD, 14));
            btnIniciar.setPreferredSize(new Dimension(200, 42));
            btnIniciar.setBackground(new Color(24, 144, 255));
            btnIniciar.setForeground(Color.WHITE);
            btnIniciar.setFocusPainted(false);
            btnIniciar.setContentAreaFilled(false);
            btnIniciar.setOpaque(true);
            btnIniciar.setBorder(BorderFactory.createLineBorder(new Color(24, 144, 255), 1));

            painelBotao.add(btnIniciar);

            frame.add(painelTextos, BorderLayout.CENTER);
            frame.add(painelBotao, BorderLayout.SOUTH);

            btnIniciar.addActionListener(e -> {
                frame.dispose(); 
                new JanelaConsulta().setVisible(true); 
            });

            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
