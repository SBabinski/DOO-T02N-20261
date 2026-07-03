import javax.swing.*;
import java.awt.*;

public class TelaUsuario extends JFrame {

    private Usuario usuario;

    public TelaUsuario(Usuario usuario) {

        this.usuario = usuario;

        setTitle("Perfil do Usuário");
        setSize(700, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        Color fundo = new Color(18, 18, 18);
        Color card = new Color(35, 35, 35);
        Color destaque = new Color(60, 60, 60);

        getContentPane().setBackground(fundo);

        JLabel titulo = new JLabel("Perfil do Usuário", SwingConstants.CENTER);
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));

        JPanel centro = new JPanel(new GridLayout(1, 2, 20, 20));
        centro.setBackground(fundo);
        centro.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel cardInfo = new JPanel();
        cardInfo.setLayout(new BoxLayout(cardInfo, BoxLayout.Y_AXIS));
        cardInfo.setBackground(card);
        cardInfo.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel nome = new JLabel("Nome: " + usuario.getNome());
        JLabel email = new JLabel("Email: " + usuario.getEmail());
        JLabel total = new JLabel("Total de séries: " + usuario.getTotalSeries());

        styleText(nome);
        styleText(email);
        styleText(total);

        cardInfo.add(nome);
        cardInfo.add(Box.createVerticalStrut(15));
        cardInfo.add(email);
        cardInfo.add(Box.createVerticalStrut(15));
        cardInfo.add(total);

        JPanel cardStats = new JPanel();
        cardStats.setLayout(new BoxLayout(cardStats, BoxLayout.Y_AXIS));
        cardStats.setBackground(card);
        cardStats.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel fav = new JLabel("Favoritos: " + usuario.getFavoritos().size());
        JLabel assistidas = new JLabel("Assistidas: " + usuario.getAssistidas().size());
        JLabel quero = new JLabel("Quero assistir: " + usuario.getQueroAssistir().size());

        styleText(fav);
        styleText(assistidas);
        styleText(quero);

        cardStats.add(fav);
        cardStats.add(Box.createVerticalStrut(15));
        cardStats.add(assistidas);
        cardStats.add(Box.createVerticalStrut(15));
        cardStats.add(quero);

        centro.add(cardInfo);
        centro.add(cardStats);

        JPanel rodape = new JPanel();
        rodape.setBackground(fundo);
        rodape.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));

        JButton btnListas = new JButton("Minhas Listas");
        JButton btnFechar = new JButton("Fechar");

        styleBtn(btnListas);
        styleBtn(btnFechar);

        btnListas.addActionListener(e -> new TelaListas(usuario));
        btnFechar.addActionListener(e -> dispose());

        rodape.add(btnListas);
        rodape.add(btnFechar);

        add(titulo, BorderLayout.NORTH);
        add(centro, BorderLayout.CENTER);
        add(rodape, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void styleText(JLabel label) {
        label.setForeground(Color.LIGHT_GRAY);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 16));
    }

    private void styleBtn(JButton btn) {
        btn.setBackground(new Color(50, 50, 50));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btn.setBorder(BorderFactory.createEmptyBorder(10, 18, 10, 18));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}