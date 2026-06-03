public class Cliente {
    private String nome;
    private String cpf;
    private String numeroCNH;

    public Cliente(){

    }

    public Cliente(String nome, String cpf, String numeroCNH){
        setNome(nome);
        setCpf(cpf);
        setNumeroCNH(numeroCNH);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNumeroCNH() {
        return numeroCNH;
    }

    public void setNumeroCNH(String numeroCNH) {
        this.numeroCNH = numeroCNH;
    }

    public String informacoes(){
        return String.format("Nome: "+getNome()+" | CPF: "+getCpf()+" | CNH: "+getNumeroCNH());
    }

}
