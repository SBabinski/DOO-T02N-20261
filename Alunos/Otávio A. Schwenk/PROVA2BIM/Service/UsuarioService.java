package Service;

import Objetos.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.Base64;

public class UsuarioService {

    private final ObjectMapper mapeadorJson=new ObjectMapper();

    private final File pastaUsuarios=new File("Usuarios");

    public UsuarioService(){
        if(!pastaUsuarios.exists()){
            pastaUsuarios.mkdirs();
        }
    }

    public void salvarUsuario(Usuario usuario){

        if(usuario.getNome() == null){
            throw new RuntimeException("Nome inválido");
        }

        String nomeArquivo = "Usuarios/usuario-" + usuario.getNome().toLowerCase() + ".json";
        File arquivo = new File(nomeArquivo);

        if(arquivo.exists()){
            throw new RuntimeException("Já existe um usuário com esse nome");
        }

        try {
           
            Usuario usuarioParaSalvar = new Usuario(
                usuario.getNome(),
                usuario.getApelido(),
                Base64.getEncoder().encodeToString(usuario.getSenha().getBytes()),
                usuario.getFavoritas(),
                usuario.getAssistidas(),
                usuario.getDesejaAssistir()
            );

            mapeadorJson.writerWithDefaultPrettyPrinter()
                    .writeValue(arquivo, usuarioParaSalvar);

        } catch(Exception erro){
            throw new RuntimeException("Falha ao salvar usuário", erro);
        }
    }

    public void atualizarUsuario(Usuario usuarioAtualizado){
        try{

            if(usuarioAtualizado.getNome() == null){
                throw new RuntimeException("Nome inválido");
            }

            String nomeArquivo = "Usuarios/usuario-" + usuarioAtualizado.getNome().toLowerCase() + ".json";
            File arquivo = new File(nomeArquivo);

            if(!arquivo.exists()){
                throw new RuntimeException("Usuário não encontrado para atualização");
            }

            mapeadorJson.writerWithDefaultPrettyPrinter()
                    .writeValue(arquivo, usuarioAtualizado);

        }catch(Exception erro){
            throw new RuntimeException("Falha ao atualizar usuário", erro);
        }
    }

    public Usuario buscarUsuario(String nome, String senha) {
        try {
            String nomeArquivo = "Usuarios/usuario-" + nome.toLowerCase() + ".json";
            File arquivo = new File(nomeArquivo);

            if (!arquivo.exists()) {
                throw new RuntimeException("Usuário não encontrado");
            }

            Usuario usuario = mapeadorJson.readValue(arquivo, Usuario.class);

            if (!usuario.getSenha().equals(senha)) {
                throw new RuntimeException("Senha incorreta");
            }

            return usuario;

        } catch (Exception erro) {
            throw new RuntimeException(erro.getMessage(), erro);
        }
    }
}