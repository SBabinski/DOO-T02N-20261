import model.User;
import repository.UserRepository;
import view.TelaPrincipal;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UserRepository userRepository = new UserRepository();

            User usuario = carregarOuCriarUsuario(userRepository);

            TelaPrincipal telaPrincipal = new TelaPrincipal(usuario, userRepository);
            telaPrincipal.setVisible(true);
        });
    }

    private static User carregarOuCriarUsuario(UserRepository userRepository) {
        try {
            User usuarioCarregado = userRepository.carregarUsuario();

            if (usuarioCarregado != null) {
                return usuarioCarregado;
            }

        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Não foi possível carregar o usuário salvo. Um novo usuário será criado.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );
        }

        String nome = JOptionPane.showInputDialog(
                null,
                "Digite seu nome ou apelido:",
                "Criar usuário",
                JOptionPane.QUESTION_MESSAGE
        );

        if (nome == null || nome.trim().isEmpty()) {
            nome = "Usuário";
        }

        User novoUsuario = new User(nome.trim());

        try {
            userRepository.salvarUsuario(novoUsuario);

        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Não foi possível salvar o novo usuário.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        return novoUsuario;
    }
}