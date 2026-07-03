package Objetos;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class Usuario {

    private String nome;
    private String apelido;
    private String senha;
    private List<Serie> favoritas = new ArrayList<>();
    private List<Serie> assistidas = new ArrayList<>();
    private List<Serie> desejaAssistir = new ArrayList<>();
    
    public Usuario(){
    }

    public Usuario(String nome, String apelido, String senha){
        setNome(nome);
        setApelido(apelido);
        setSenha(senha);
    }

    public Usuario(String nome, String apelido, String senha,
                List<Serie> favoritas,
                List<Serie> assistidas,
                List<Serie> desejaAssistir){

        setNome(nome);
        setApelido(apelido);
        setSenha(senha);

        setFavoritas(favoritas);
        setAssistidas(assistidas);
        setDesejaAssistir(desejaAssistir);
    }

    public void setNome(String nome){
        if(nome != null && nome.matches("[A-Za-z0-9._-]+")){
            this.nome = nome;
        }else{
            throw new IllegalArgumentException("Nome inválido");
        }
    }

    public void setApelido(String apelido){
        if(apelido != null && !apelido.isBlank()){
            this.apelido = apelido;
        }else{
            throw new IllegalArgumentException("Apelido não pode estar em branco");
        }
    }

    public void setSenha(String senha){
        if(senha != null){
            this.senha = senha;
        }else{
            throw new IllegalArgumentException("Senha inválida");
        }
    }

    public void setFavoritas(List<Serie> favoritas){
        this.favoritas = favoritas;
    }

    public void setAssistidas(List<Serie> assistidas){
        this.assistidas = assistidas;
    }

    public void setDesejaAssistir(List<Serie> desejaAssistir){
        this.desejaAssistir = desejaAssistir;
    }

    public String getNome(){
        return nome;
    }

    public String getApelido(){
        return apelido;
    }

    public String getSenha(){
        return senha;
    }

    public List<Serie> getFavoritas(){
        return favoritas;
    }

    public List<Serie> getAssistidas(){
        return assistidas;
    }

    public List<Serie> getDesejaAssistir(){
        return desejaAssistir;
    }
}
