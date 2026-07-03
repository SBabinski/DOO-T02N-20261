package provaDOO.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import provaDOO.model.Usuario;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {

    private static final String ARQUIVO = "usuarios.json";
    private final ObjectMapper mapper;

    public UsuarioRepository() {
        mapper = new ObjectMapper();
    }

    public List<Usuario> listarUsuarios() {
        try {
            File arquivo = new File(ARQUIVO);
            if (!arquivo.exists()) {
                return new ArrayList<>();
            }
            return mapper.readValue(
                    arquivo,
                    new TypeReference<List<Usuario>>() {}
            );

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public boolean existeUsuario(String nome) {

        return listarUsuarios()
                .stream()
                .anyMatch(usuario ->
                        usuario.getNome()
                                .equalsIgnoreCase(nome));
    }

    public void salvarUsuario(Usuario usuario) throws Exception {

        List<Usuario> usuarios = listarUsuarios();

        usuario.setId(gerarNovoId(usuarios));

        usuarios.add(usuario);

        mapper.writerWithDefaultPrettyPrinter()
                .writeValue(
                        new File(ARQUIVO),
                        usuarios
                );
    }

    private Long gerarNovoId(List<Usuario> usuarios) {
        return usuarios.stream()
                .mapToLong(Usuario::getId)
                .max()
                .orElse(0L) + 1;
    }
    
    public void atualizarUsuario(Usuario usuario)
            throws Exception {

        List<Usuario> usuarios =
                listarUsuarios();

        for (int i = 0;i < usuarios.size();i++) {
            if (usuarios.get(i).getId().equals(usuario.getId())) {
                usuarios.set(i, usuario);
                break;
            }
        }

        mapper.writerWithDefaultPrettyPrinter()
                .writeValue(new File(ARQUIVO),usuarios);
    }
}