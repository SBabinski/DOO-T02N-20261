package com.tvtracker.ui;

import com.tvtracker.model.UserData;
import com.tvtracker.service.DataPersistenceService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Diálogo para visualização e edição do perfil do usuário local.
 * Exibe estatísticas das listas e permite alterar o nome/apelido.
 * Também oferece opção de trocar completamente de usuário (reset).
 */
public class UserProfileDialog extends JDialog {

    private final UserData userData;
    private final DataPersistenceService persistenceService;
    private JTextField nameField;

    // Flag lida pelo MainFrame para saber se deve reiniciar o fluxo de usuário
    private boolean resetRequested = false;

    public UserProfileDialog(Frame parent, UserData userData, DataPersistenceService persistenceService) {
        super(parent, "Meu Perfil", true);
        this.userData = userData;
        this.persistenceService = persistenceService;

        setSize(380, 400);
        setLocationRelativeTo(parent);
        setResizable(false);
        getContentPane().setBackground(AppTheme.BG_DARK);
        setLayout(new BorderLayout(0, 0));

        buildUI();
    }

    // Retorna true se o usuário pediu para trocar de nome/resetar
    public boolean isResetRequested() {
        return resetRequested;
    }

    private void buildUI() {
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(AppTheme.BG_DARK);
        content.setBorder(new EmptyBorder(24, 32, 16, 32));

        content.add(buildAvatarPanel());
        content.add(Box.createVerticalStrut(16));
        content.add(buildNameSection());
        content.add(Box.createVerticalStrut(24));
        content.add(buildStatsSection());

        add(content, BorderLayout.CENTER);
        add(buildActionPanel(), BorderLayout.SOUTH);
    }

    // Círculo com a inicial do nome como avatar
    private JPanel buildAvatarPanel() {
        JPanel avatarPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(AppTheme.ACCENT);
                g2.fillOval(0, 0, 72, 72);
                g2.setColor(Color.WHITE);
                g2.setFont(new Font("Segoe UI", Font.BOLD, 30));
                String initial = userData.getUserName().isEmpty() ? "?"
                        : String.valueOf(userData.getUserName().charAt(0)).toUpperCase();
                FontMetrics fm = g2.getFontMetrics();
                int x = (72 - fm.stringWidth(initial)) / 2;
                int y = (72 + fm.getAscent() - fm.getDescent()) / 2;
                g2.drawString(initial, x, y);
                g2.dispose();
            }
        };
        avatarPanel.setPreferredSize(new Dimension(72, 72));
        avatarPanel.setMaximumSize(new Dimension(72, 72));
        avatarPanel.setBackground(AppTheme.BG_DARK);
        avatarPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return avatarPanel;
    }

    private JPanel buildNameSection() {
        JPanel panel = new JPanel(new BorderLayout(0, 6));
        panel.setBackground(AppTheme.BG_DARK);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

        JLabel label = new JLabel("Seu nome ou apelido:");
        label.setFont(AppTheme.FONT_SMALL);
        label.setForeground(AppTheme.TEXT_MUTED);

        nameField = new JTextField(userData.getUserName());
        nameField.setFont(AppTheme.FONT_BODY);
        nameField.setBackground(AppTheme.BG_CARD);
        nameField.setForeground(AppTheme.TEXT_PRIMARY);
        nameField.setCaretColor(AppTheme.TEXT_PRIMARY);
        nameField.setBorder(BorderFactory.createCompoundBorder(
                AppTheme.BORDER_CARD,
                new EmptyBorder(6, 10, 6, 10)));

        panel.add(label, BorderLayout.NORTH);
        panel.add(nameField, BorderLayout.CENTER);
        return panel;
    }

    private JPanel buildStatsSection() {
        JPanel panel = new JPanel(new GridLayout(1, 3, 8, 0));
        panel.setBackground(AppTheme.BG_DARK);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));

        panel.add(createStatCard("Favoritos",  userData.getFavorites().size(),   AppTheme.ACCENT));
        panel.add(createStatCard("Assistidos", userData.getWatched().size(),     AppTheme.GREEN));
        panel.add(createStatCard("Quero ver",  userData.getWantToWatch().size(), AppTheme.GOLD));

        return panel;
    }

    private JPanel createStatCard(String label, int count, Color color) {
        JPanel card = new JPanel(new BorderLayout(0, 4));
        card.setBackground(AppTheme.BG_CARD);
        card.setBorder(new EmptyBorder(10, 8, 10, 8));

        JLabel countLabel = new JLabel(String.valueOf(count), SwingConstants.CENTER);
        countLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        countLabel.setForeground(color);

        JLabel nameLabel = new JLabel(label, SwingConstants.CENTER);
        nameLabel.setFont(AppTheme.FONT_SMALL);
        nameLabel.setForeground(AppTheme.TEXT_MUTED);

        card.add(countLabel, BorderLayout.CENTER);
        card.add(nameLabel, BorderLayout.SOUTH);
        return card;
    }

    // Painel de ações: cancelar, trocar usuário e salvar
    private JPanel buildActionPanel() {
        JPanel panel = new JPanel(new BorderLayout(0, 0));
        panel.setBackground(AppTheme.BG_DARK);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 0, 0, 0, AppTheme.BG_CARD),
                new EmptyBorder(10, 16, 10, 16)));

        // Botão "Trocar Usuário" alinhado à esquerda — reinicia o app do zero
        JButton trocarBtn = AppTheme.createButton("Trocar Usuario", new Color(70, 40, 40));
        trocarBtn.setFont(AppTheme.FONT_SMALL);
        trocarBtn.setToolTipText("Apaga todos os dados e pede um novo nome");
        trocarBtn.addActionListener(e -> confirmarTrocarUsuario());

        // Botões de cancelar e salvar alinhados à direita
        JPanel rightBtns = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        rightBtns.setBackground(AppTheme.BG_DARK);

        JButton cancelBtn = AppTheme.createButton("Cancelar", AppTheme.BG_CARD);
        cancelBtn.addActionListener(e -> dispose());

        JButton saveBtn = AppTheme.createButton("Salvar", AppTheme.ACCENT);
        saveBtn.addActionListener(e -> saveName());

        rightBtns.add(cancelBtn);
        rightBtns.add(saveBtn);

        panel.add(trocarBtn, BorderLayout.WEST);
        panel.add(rightBtns, BorderLayout.EAST);
        return panel;
    }

    // Salva apenas o nome alterado sem apagar listas
    private void saveName() {
        String name = nameField.getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, insira um nome ou apelido.",
                    "Campo obrigatório", JOptionPane.WARNING_MESSAGE);
            return;
        }
        userData.setUserName(name);
        try {
            persistenceService.save(userData);
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao salvar o perfil: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Pede confirmação e, se aceito, limpa TODOS os dados em memória e em disco.
     * Sinaliza ao MainFrame (via resetRequested) para reabrir a tela de nome.
     */
    private void confirmarTrocarUsuario() {
        int opcao = JOptionPane.showConfirmDialog(this,
                "Isso vai apagar o nome e TODAS as suas listas.\nTem certeza?",
                "Trocar Usuario",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (opcao != JOptionPane.YES_OPTION) return;

        // Limpa tudo em memória
        userData.setUserName("");
        userData.getFavorites().clear();
        userData.getWatched().clear();
        userData.getWantToWatch().clear();

        // Apaga o arquivo de dados em disco
        try {
            persistenceService.deleteData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao apagar os dados: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Sinaliza ao MainFrame para reiniciar o fluxo de cadastro
        resetRequested = true;
        dispose();
    }
}
