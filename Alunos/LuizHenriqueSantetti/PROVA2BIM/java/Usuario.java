public class Usuario {
    private String nome;

    public Usuario(String nome) {
        setNome(nome);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            this.nome = "Usuario";
        } else {
            this.nome = nome.trim();
        }
    }
}
