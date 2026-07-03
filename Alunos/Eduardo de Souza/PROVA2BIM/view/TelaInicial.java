package provaDOO.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import provaDOO.services.UsuarioService;

import java.awt.*;

public class TelaInicial extends JFrame {

    public TelaInicial() {
        configurarJanela();
        criarComponentes();
    }

    private void configurarJanela() {
        setTitle("Minhas Séries");
        setSize(1200, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void criarComponentes() {

        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setBackground(new Color(18, 18, 18));
        painelPrincipal.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        JPanel painelCentro = new JPanel();
        painelCentro.setOpaque(false);
        painelCentro.setLayout(new BoxLayout(painelCentro, BoxLayout.Y_AXIS));
        painelCentro.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("MINHAS SÉRIES");
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 42));
        titulo.setForeground(new Color(229, 9, 20));

        JLabel subtitulo = new JLabel("Organize e acompanhe suas séries favoritas");
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        subtitulo.setForeground(Color.LIGHT_GRAY);

        painelCentro.add(titulo);
        painelCentro.add(Box.createVerticalStrut(15));
        painelCentro.add(subtitulo);
        painelCentro.add(Box.createVerticalStrut(60));

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 0));
        painelBotoes.setOpaque(false);

        JButton btnCadastrar = criarBotao("Cadastrar",
                new Color(229, 9, 20), Color.WHITE);
        btnCadastrar.addActionListener(e -> {
            TelaCadastro telaCadastro = new TelaCadastro();
            telaCadastro.setVisible(true);
        });

        JButton btnEntrar = criarBotao("Entrar",
                new Color(40, 40, 40), Color.WHITE);
        btnEntrar.addActionListener(e -> {

            UsuarioService usuarioService = new UsuarioService();

            if (usuarioService.listarUsuarios().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Nenhum usuário cadastrado.\nCadastre um usuário antes de entrar.",
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            TelaLogin telaLogin = new TelaLogin();
            telaLogin.setVisible(true);
            dispose();
        });

        painelBotoes.add(btnCadastrar);
        painelBotoes.add(btnEntrar);

        painelCentro.add(painelBotoes);

        gbc.gridx = 0;
        gbc.gridy = 0;
        painelPrincipal.add(painelCentro, gbc);

        add(painelPrincipal);
    }

    private JButton criarBotao(String texto, Color fundo, Color textoCor) {
        JButton botao = new JButton(texto);

        botao.setFont(new Font("Segoe UI", Font.BOLD, 16));
        botao.setForeground(textoCor);
        botao.setBackground(fundo);

        botao.setFocusPainted(false);
        botao.setBorderPainted(false);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));

        botao.setPreferredSize(new Dimension(180, 50));

        return botao;
    }
}