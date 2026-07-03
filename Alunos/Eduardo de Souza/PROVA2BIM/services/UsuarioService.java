package provaDOO.services;

import java.util.List;

import provaDOO.model.Usuario;
import provaDOO.repository.UsuarioRepository;

public class UsuarioService {

    private final UsuarioRepository repository =
            new UsuarioRepository();

    public void cadastrar(String nome)
            throws Exception {

        nome = nome.trim();

        if(nome.isBlank()) {
            throw new Exception(
                    "Informe um nome."
            );
        }

        if(repository.existeUsuario(nome)) {
            throw new Exception(
                    "Já existe um usuário com esse nome."
            );
        }

        Usuario usuario = new Usuario();

        usuario.setNome(nome);

        repository.salvarUsuario(usuario);
    }
    
    public List<Usuario> listarUsuarios() {
        return repository.listarUsuarios();
    }
    
    public void adicionarFavorito(
            Long usuarioId,
            Long serieId)
            throws Exception {

        Usuario usuario =
                buscarUsuario(usuarioId);

        if (!usuario.getFavoritos()
                .contains(serieId)) {

            usuario.getFavoritos()
                    .add(serieId);

            repository.atualizarUsuario(
                    usuario);
        }
    }

    public void adicionarAssistida(
            Long usuarioId,
            Long serieId)
            throws Exception {

        Usuario usuario =
                buscarUsuario(usuarioId);

        if (!usuario.getAssistidas()
                .contains(serieId)) {

            usuario.getAssistidas()
                    .add(serieId);

            repository.atualizarUsuario(
                    usuario);
        }
    }

    public void adicionarDesejaAssistir(
            Long usuarioId,
            Long serieId)
            throws Exception {

        Usuario usuario =
                buscarUsuario(usuarioId);

        if (!usuario.getDesejaAssistir()
                .contains(serieId)) {

            usuario.getDesejaAssistir()
                    .add(serieId);

            repository.atualizarUsuario(
                    usuario);
        }
    }
    
    public void removerFavorito(
            Long usuarioId,
            Long serieId)
            throws Exception {

        Usuario usuario = buscarUsuario(usuarioId);

        usuario.getFavoritos().remove(serieId);

        repository.atualizarUsuario(usuario);
    }

    public void removerAssistida(
            Long usuarioId,
            Long serieId)
            throws Exception {

        Usuario usuario = buscarUsuario(usuarioId);

        usuario.getAssistidas().remove(serieId);

        repository.atualizarUsuario(usuario);
    }

    public void removerDesejaAssistir(
            Long usuarioId,
            Long serieId)
            throws Exception {

        Usuario usuario = buscarUsuario(usuarioId);

        usuario.getDesejaAssistir().remove(serieId);

        repository.atualizarUsuario(usuario);
    }

    private Usuario buscarUsuario(
            Long usuarioId)
            throws Exception {

        return repository
                .listarUsuarios()
                .stream()
                .filter(usuario ->
                        usuario.getId()
                                .equals(usuarioId))
                .findFirst()
                .orElseThrow(() ->
                        new Exception(
                                "Usuário não encontrado."));
    }
    
    public Usuario buscarPorId(Long id) {

        return listarUsuarios()
                .stream()
                .filter(
                        usuario ->
                                usuario.getId()
                                        .equals(id)
                )
                .findFirst()
                .orElse(null);
    }
    
}