public class Cliente {
    private String nome;
    private String CPF;
    private String CNH;

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCPF() {
        return CPF;
    }
    public void setCPF(String cPF) {
        CPF = cPF;
    }
    public String getCNH() {
        return CNH;
    }
    public void setCNH(String cNH) {
        CNH = cNH;
    }

    public Cliente(String nome, String CPF, String CNH) {
        setNome(nome);
        setCNH(CNH);
        setCPF(CPF);
    }
}
