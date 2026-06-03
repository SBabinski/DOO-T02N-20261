import javax.swing.*;
import java.awt.*;
import java.io.File;

public class JanelaResultado extends JFrame {
    
    public JanelaResultado(ClimaDados dados) {
        setTitle("Previsão do Tempo - " + dados.getCidade());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);
        
        try {
            ImageIcon iconeApp = new ImageIcon("icons/clear-day.png");
            setIconImage(iconeApp.getImage());
        } catch (Exception e) {}

        JPanel painelTopo = new JPanel(new GridLayout(2, 1, 0, 5));
        painelTopo.setBorder(BorderFactory.createEmptyBorder(25, 30, 10, 30));
        painelTopo.setBackground(Color.WHITE);
        
        JLabel lblTitulo = new JLabel(dados.getCidade() + " - " + dados.getEstado(), SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitulo.setForeground(new Color(33, 43, 54));
        
        JLabel lblData = new JLabel("Data da Consulta: " + dados.getData(), SwingConstants.CENTER);
        lblData.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblData.setForeground(new Color(145, 158, 171));
        
        painelTopo.add(lblTitulo);
        painelTopo.add(lblData);
        
        JPanel painelCentral = new JPanel();
        painelCentral.setLayout(new BoxLayout(painelCentral, BoxLayout.Y_AXIS));
        painelCentral.setBackground(Color.WHITE);
        painelCentral.setBorder(BorderFactory.createEmptyBorder(10, 35, 10, 35));
        
        JLabel lblIcone = new JLabel();
        lblIcone.setAlignmentX(Component.CENTER_ALIGNMENT);
        String caminhoIcone = "icons/" + dados.getIcone() + ".png";
        
        if (new File(caminhoIcone).exists()) {
            ImageIcon iconImg = new ImageIcon(caminhoIcone);
            Image imgRedimensionada = iconImg.getImage().getScaledInstance(95, 95, Image.SCALE_SMOOTH);
            lblIcone.setIcon(new ImageIcon(imgRedimensionada));
        } else {
            lblIcone.setText("☁️");
            lblIcone.setFont(new Font("Segoe UI", Font.PLAIN, 48));
        }
        
        JLabel lblTemp = new JLabel(Math.round(dados.getTemperaturaAtual()) + "°C", SwingConstants.CENTER);
        lblTemp.setFont(new Font("Segoe UI", Font.BOLD, 54));
        lblTemp.setForeground(new Color(24, 144, 255));
        lblTemp.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel lblCondicao = new JLabel(dados.getCondicaoTempo(), SwingConstants.CENTER);
        lblCondicao.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblCondicao.setForeground(new Color(99, 115, 129));
        lblCondicao.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        painelCentral.add(lblIcone);
        painelCentral.add(Box.createRigidArea(new Dimension(0, 5)));
        painelCentral.add(lblTemp);
        painelCentral.add(lblCondicao);
        painelCentral.add(Box.createRigidArea(new Dimension(0, 25)));
        
        JPanel painelGrade = new JPanel(new GridLayout(3, 2, 40, 20));
        painelGrade.setBackground(new Color(245, 247, 250));
        painelGrade.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 225, 230), 1),
            BorderFactory.createEmptyBorder(20, 25, 20, 25)
        ));
        
        painelGrade.add(criarPainelMetrica("icons8-termômetro-48", "Máxima:", dados.getTemperaturaMaxima() + " °C"));
        painelGrade.add(criarPainelMetrica("icons8-termômetro-40", "Mínima:", dados.getTemperaturaMinima() + " °C"));
        painelGrade.add(criarPainelMetrica("icons8-borrar-48", "Umidade:", dados.getUmidadeAr() + " %"));
        painelGrade.add(criarPainelMetrica("icons8-nuvem-de-chuva-48", "Chuva:", dados.getPrecipitacao() + " mm"));
        painelGrade.add(criarPainelMetrica("icons8-vento-48", "Vento:", dados.getVelocidadeVento() + " km/h"));
        painelGrade.add(criarPainelMetrica("icons8-bússola-40", "Direção:", dados.getDirecaoVento() + "°"));
        
        painelCentral.add(painelGrade);
        
        JPanel painelBaixo = new JPanel();
        painelBaixo.setBackground(Color.WHITE);
        painelBaixo.setBorder(BorderFactory.createEmptyBorder(20, 0, 30, 0));
        
        JButton btnVoltar = new JButton("Nova Consulta");
        btnVoltar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnVoltar.setPreferredSize(new Dimension(200, 42));
        btnVoltar.setBackground(new Color(99, 115, 129));
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.setContentAreaFilled(false);
        btnVoltar.setOpaque(true);
        btnVoltar.setFocusPainted(false);
        btnVoltar.setBorder(BorderFactory.createLineBorder(new Color(99, 115, 129), 1));
        
        painelBaixo.add(btnVoltar);
        
        btnVoltar.addActionListener(e -> {
            dispose(); 
            new JanelaConsulta().setVisible(true); 
        });
        
        add(painelTopo, BorderLayout.NORTH);
        add(painelCentral, BorderLayout.CENTER);
        add(painelBaixo, BorderLayout.SOUTH);

        painelCentral.setPreferredSize(new Dimension(460, 380));
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
    }
    
    private JPanel criarPainelMetrica(String nomeIcone, String titulo, String valor) {
        JPanel miniPainel = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        miniPainel.setBackground(new Color(245, 247, 250));

        JLabel lblMiniIcone = new JLabel();
        String caminhoMiniIcone = "icons/" + nomeIcone + ".png";
        
        if (new File(caminhoMiniIcone).exists()) {
            ImageIcon iconImg = new ImageIcon(caminhoMiniIcone);
            Image imgRedimensionada = iconImg.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
            lblMiniIcone.setIcon(new ImageIcon(imgRedimensionada));
        }

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblTitulo.setForeground(new Color(33, 43, 54));

        JLabel lblValor = new JLabel(valor);
        lblValor.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblValor.setForeground(new Color(33, 43, 54));

        miniPainel.add(lblMiniIcone);
        miniPainel.add(lblTitulo);
        miniPainel.add(lblValor);
        
        return miniPainel;
    }
}
