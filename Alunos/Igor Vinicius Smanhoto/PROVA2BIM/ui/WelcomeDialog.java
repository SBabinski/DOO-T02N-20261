package com.tvtracker.ui;

import com.tvtracker.model.UserData;
import com.tvtracker.service.DataPersistenceService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Tela de boas-vindas exibida apenas na primeira execução do aplicativo,
 * quando ainda não existe arquivo de dados salvo em disco.
 */
public class WelcomeDialog extends JDialog {

    private final UserData userData;
    private final DataPersistenceService persistenceService;
    private JTextField nameField;
    private boolean confirmed = false;

    public WelcomeDialog(UserData userData, DataPersistenceService persistenceService) {
        super((Frame) null, "Bem-vindo ao pobreflixHD!", true);
        this.userData = userData;
        this.persistenceService = persistenceService;

        setSize(420, 340);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE); // impede fechar sem preencher
        getContentPane().setBackground(AppTheme.BG_DARK);
        setLayout(new BorderLayout(0, 0));

        buildUI();
    }

    // Retorna true se o usuário confirmou (preencheu o nome e clicou em continuar)
    public boolean isConfirmed() {
        return confirmed;
    }

    // Monta a tela de boas-vindas com logo e formulário
    private void buildUI() {
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(AppTheme.BG_DARK);
        content.setBorder(new EmptyBorder(32, 48, 24, 48));

        // Ícone decorativo
        JLabel icon = new JLabel("📺", SwingConstants.CENTER);
        icon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 48));
        icon.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Título principal
        JLabel title = new JLabel("pobreflixHD", SwingConstants.CENTER);
        title.setFont(AppTheme.FONT_TITLE);
        title.setForeground(AppTheme.ACCENT);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Subtítulo explicativo
        JLabel subtitle = new JLabel("Seu catálogo pessoal de séries — sem mensalidade!");
        subtitle.setFont(AppTheme.FONT_SMALL);
        subtitle.setForeground(AppTheme.TEXT_MUTED);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        content.add(icon);
        content.add(Box.createVerticalStrut(8));
        content.add(title);
        content.add(Box.createVerticalStrut(4));
        content.add(subtitle);
        content.add(Box.createVerticalStrut(28));

        // Campo de nome: única informação solicitada ao usuário
        JLabel nameLabel = new JLabel("Como podemos te chamar?");
        nameLabel.setFont(AppTheme.FONT_BODY);
        nameLabel.setForeground(AppTheme.TEXT_SECONDARY);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        nameField = new JTextField();
        nameField.setFont(AppTheme.FONT_BODY);
        nameField.setBackground(AppTheme.BG_CARD);
        nameField.setForeground(AppTheme.TEXT_PRIMARY);
        nameField.setCaretColor(AppTheme.TEXT_PRIMARY);
        nameField.setBorder(BorderFactory.createCompoundBorder(
                AppTheme.BORDER_CARD,
                new EmptyBorder(8, 12, 8, 12)));
        nameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        // Confirma ao pressionar Enter no campo de nome
        nameField.addActionListener(e -> confirm());

        content.add(nameLabel);
        content.add(Box.createVerticalStrut(8));
        content.add(nameField);

        add(content, BorderLayout.CENTER);
        add(buildButtonPanel(), BorderLayout.SOUTH);
    }

    // Painel com o botão de confirmação
    private JPanel buildButtonPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(AppTheme.BG_DARK);
        panel.setBorder(new EmptyBorder(0, 48, 24, 48));

        JButton startBtn = AppTheme.createButton("Começar a usar", AppTheme.ACCENT);
        startBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        startBtn.addActionListener(e -> confirm());

        panel.add(startBtn, BorderLayout.CENTER);
        return panel;
    }

    // Valida o nome e salva os dados do novo usuário
    private void confirm() {
        String name = nameField.getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, insira um nome ou apelido para continuar.",
                    "Campo obrigatório", JOptionPane.WARNING_MESSAGE);
            return;
        }

        userData.setUserName(name);
        try {
            persistenceService.save(userData);
            confirmed = true;
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao salvar: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
