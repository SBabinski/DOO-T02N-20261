package com.tvtracker.ui;

import com.tvtracker.model.UserProfile;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Diálogo de configuração inicial do usuário (nome/apelido).
 */
public class SetupDialog extends JDialog {

    private UserProfile resultProfile;
    private JTextField nameField;
    private JTextField nicknameField;

    public SetupDialog(Window owner) {
        super(owner, "Bem-vindo ao TV Tracker!", ModalityType.APPLICATION_MODAL);
        setSize(420, 340);
        setLocationRelativeTo(owner);
        setResizable(false);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        getContentPane().setBackground(AppTheme.BG_DARK);
        setLayout(new BorderLayout());

        buildUI();
    }

    private void buildUI() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(AppTheme.BG_DARK);
        mainPanel.setBorder(new EmptyBorder(28, 32, 20, 32));

        // Ícone e título
        JLabel iconLabel = new JLabel("\uD83D\uDCFA", JLabel.CENTER); // 📺
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 48));
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(iconLabel);
        mainPanel.add(Box.createVerticalStrut(12));

        JLabel titleLabel = new JLabel("TV Tracker");
        titleLabel.setFont(AppTheme.FONT_TITLE);
        titleLabel.setForeground(AppTheme.TEXT_PRIMARY);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(4));

        JLabel subtitleLabel = new JLabel("Crie seu perfil para começar");
        subtitleLabel.setFont(AppTheme.FONT_SMALL);
        subtitleLabel.setForeground(AppTheme.TEXT_MUTED);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(subtitleLabel);
        mainPanel.add(Box.createVerticalStrut(24));

        // Painel centralizado para os campos
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(AppTheme.BG_DARK);
        formPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.setMaximumSize(new Dimension(280, Integer.MAX_VALUE));

        // Campo nome
        addFieldLabel(formPanel, "Seu nome *");
        nameField = createTextField("Ex: João Silva");
        nameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        nameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(nameField);
        formPanel.add(Box.createVerticalStrut(12));

        // Campo apelido
        addFieldLabel(formPanel, "Apelido (opcional)");
        nicknameField = createTextField("Ex: JoãoFlix");
        nicknameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        nicknameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(nicknameField);

        mainPanel.add(formPanel);
        mainPanel.add(Box.createVerticalStrut(24));

        // Botão
        JButton startBtn = createButton("Começar →", AppTheme.ACCENT, AppTheme.TEXT_PRIMARY);
        startBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        startBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
        startBtn.addActionListener(e -> onConfirm());
        mainPanel.add(startBtn);

        add(mainPanel, BorderLayout.CENTER);

        // Pressionar Enter no campo nome vai para o apelido; no apelido confirma
        nameField.addActionListener(e -> nicknameField.requestFocusInWindow());
        nicknameField.addActionListener(e -> onConfirm());
    }

    private void onConfirm() {
        String name = nameField.getText().trim();
        if (name.isEmpty()) {
            nameField.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(AppTheme.DANGER, 1, true),
                    new EmptyBorder(6, 12, 6, 12)
            ));
            JOptionPane.showMessageDialog(this, "Por favor, informe seu nome.", "Campo obrigatório",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        String nickname = nicknameField.getText().trim();
        resultProfile = new UserProfile(name, nickname.isEmpty() ? null : nickname);
        dispose();
    }

    private void addFieldLabel(JPanel parent, String text) {
        JLabel label = new JLabel(text);
        label.setFont(AppTheme.FONT_LABEL);
        label.setForeground(AppTheme.TEXT_MUTED);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        parent.add(label);
        parent.add(Box.createVerticalStrut(4));
    }

    private JTextField createTextField(String placeholder) {
        JTextField field = new JTextField() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (getText().isEmpty()) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setFont(AppTheme.FONT_SMALL);
                    g2.setColor(AppTheme.TEXT_MUTED);
                    g2.drawString(placeholder, 12, getHeight() / 2 + 5);
                    g2.dispose();
                }
            }
        };
        field.setBackground(AppTheme.BG_INPUT);
        field.setForeground(AppTheme.TEXT_PRIMARY);
        field.setCaretColor(AppTheme.ACCENT_LIGHT);
        field.setFont(AppTheme.FONT_BODY);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(AppTheme.BORDER, 1, true),
                new EmptyBorder(6, 12, 6, 12)
        ));
        return field;
    }

    private JButton createButton(String text, Color bg, Color fg) {
        JButton btn = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFont(AppTheme.FONT_BOLD);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setOpaque(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }

    /**
     * Exibe o diálogo e retorna o perfil criado.
     * Retorna null se o usuário fechar sem configurar.
     */
    public static UserProfile showAndGetProfile(Window owner) {
        SetupDialog dialog = new SetupDialog(owner);
        dialog.setVisible(true);
        return dialog.resultProfile;
    }
}
