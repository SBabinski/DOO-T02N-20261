package tvtracker.ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import tvtracker.model.UserData;
import tvtracker.model.UserProfile;
import tvtracker.service.DataService;

public class ProfileDialog extends JDialog {

    private final UserData userData;
    private final DataService dataService;
    private final Runnable onSave;

    private JTextField nameField;
    private JTextField nicknameField;

    public ProfileDialog(Frame parent, UserData userData, DataService dataService, Runnable onSave) {
        super(parent, "Perfil do Usuário", true);
        this.userData    = userData;
        this.dataService = dataService;
        this.onSave      = onSave;
        buildUi();
        pack();
        setMinimumSize(new Dimension(360, 200));
        setLocationRelativeTo(parent);
        setResizable(false);
    }

    private void buildUi() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(20, 24, 12, 24));

        GridBagConstraints lc = new GridBagConstraints();
        lc.anchor = GridBagConstraints.WEST;
        lc.insets = new Insets(6, 0, 6, 8);
        lc.gridx = 0;

        GridBagConstraints fc = new GridBagConstraints();
        fc.fill   = GridBagConstraints.HORIZONTAL;
        fc.weightx = 1;
        fc.insets = new Insets(6, 0, 6, 0);
        fc.gridx = 1;

        // Nome
        lc.gridy = 0; fc.gridy = 0;
        panel.add(new JLabel("Nome:"), lc);
        nameField = new JTextField(userData.getProfile().getName(), 22);
        panel.add(nameField, fc);

        // Apelido
        lc.gridy = 1; fc.gridy = 1;
        panel.add(new JLabel("Apelido:"), lc);
        nicknameField = new JTextField(userData.getProfile().getNickname(), 22);
        panel.add(nicknameField, fc);

        // Dica
        GridBagConstraints hintC = new GridBagConstraints();
        hintC.gridx = 0; hintC.gridy = 2; hintC.gridwidth = 2;
        hintC.insets = new Insets(0, 0, 8, 0);
        JLabel hint = new JLabel("<html><i>O apelido será exibido se preenchido.</i></html>");
        hint.setForeground(Color.GRAY);
        hint.setFont(hint.getFont().deriveFont(11f));
        panel.add(hint, hintC);

        // Botões
        GridBagConstraints btnC = new GridBagConstraints();
        btnC.gridx = 0; btnC.gridy = 3; btnC.gridwidth = 2;
        btnC.anchor = GridBagConstraints.CENTER;
        btnC.insets = new Insets(8, 0, 0, 0);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        btnPanel.setOpaque(false);

        JButton saveBtn = new JButton("Salvar");
        saveBtn.setPreferredSize(new Dimension(100, 32));
        saveBtn.addActionListener(e -> doSave());

        JButton cancelBtn = new JButton("Cancelar");
        cancelBtn.setPreferredSize(new Dimension(100, 32));
        cancelBtn.addActionListener(e -> dispose());

        btnPanel.add(saveBtn);
        btnPanel.add(cancelBtn);
        panel.add(btnPanel, btnC);

        // Enter key submits
        getRootPane().setDefaultButton(saveBtn);

        setContentPane(panel);
    }

    private void doSave() {
        String name     = nameField.getText().trim();
        String nickname = nicknameField.getText().trim();

        if (name.isBlank() && nickname.isBlank()) {
            JOptionPane.showMessageDialog(this,
                    "Preencha pelo menos o nome ou o apelido.",
                    "Campo obrigatório", JOptionPane.WARNING_MESSAGE);
            return;
        }

        userData.setProfile(new UserProfile(name, nickname));
        try {
            dataService.save(userData);
            if (onSave != null) onSave.run();
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao salvar perfil: " + ex.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
