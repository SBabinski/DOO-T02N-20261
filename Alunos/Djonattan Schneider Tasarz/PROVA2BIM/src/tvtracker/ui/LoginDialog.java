package tvtracker.ui;

import java.awt.*;
import java.util.Set;
import javax.swing.*;


public class LoginDialog extends JDialog {

    private String selectedUserName;
    private boolean confirmed = false;

    public LoginDialog(Window owner, Set<String> existingUsers, String currentUser) {
        super(owner, "Identifique-se", ModalityType.APPLICATION_MODAL);
        buildUi(existingUsers, currentUser);
        pack();
        setMinimumSize(new Dimension(380, 180));
        setLocationRelativeTo(owner);
    }

    private void buildUi(Set<String> existingUsers, String currentUser) {
        JPanel root = new JPanel(new BorderLayout(10, 10));
        root.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel title = new JLabel("Quem está usando o sistema?");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 15f));
        root.add(title, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(2, 1, 5, 5));

        JComboBox<String> existingCombo = new JComboBox<>(existingUsers.toArray(new String[0]));
        if (currentUser != null && existingUsers.contains(currentUser)) {
            existingCombo.setSelectedItem(currentUser);
        }
        JTextField newUserField = new JTextField();

        JPanel existingPanel = new JPanel(new BorderLayout(5, 0));
        existingPanel.add(new JLabel("Usuário existente:"), BorderLayout.WEST);
        existingPanel.add(existingCombo, BorderLayout.CENTER);
        existingPanel.setVisible(!existingUsers.isEmpty());

        JPanel newPanel = new JPanel(new BorderLayout(5, 0));
        newPanel.add(new JLabel("Ou novo apelido:"), BorderLayout.WEST);
        newPanel.add(newUserField, BorderLayout.CENTER);

        form.add(existingPanel);
        form.add(newPanel);
        root.add(form, BorderLayout.CENTER);

        JButton okButton = new JButton("Entrar");
        JButton cancelButton = new JButton("Cancelar");
        okButton.addActionListener(e -> {
            String typed = newUserField.getText().trim();
            if (!typed.isEmpty()) {
                selectedUserName = typed;
            } else if (existingCombo.getSelectedItem() != null) {
                selectedUserName = (String) existingCombo.getSelectedItem();
            }

            if (selectedUserName == null || selectedUserName.isBlank()) {
                UiUtils.showError(this, "Nome obrigatório",
                        "Informe um apelido novo ou selecione um usuário existente.");
                return;
            }
            confirmed = true;
            dispose();
        });
        cancelButton.addActionListener(e -> dispose());

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttons.add(cancelButton);
        buttons.add(okButton);
        root.add(buttons, BorderLayout.SOUTH);

        getRootPane().setDefaultButton(okButton);
        setContentPane(root);
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public String getSelectedUserName() {
        return selectedUserName;
    }
}
