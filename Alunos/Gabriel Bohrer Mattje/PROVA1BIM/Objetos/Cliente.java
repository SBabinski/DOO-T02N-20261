package PROVA1BIM.Objetos;

public class Cliente  {
    String nome;
    String cpf;
    String cnh;
    
    public Cliente(String nome, String cpf, String cnh) {
        this.nome = nome;
        this.cpf = cpf;
        this.cnh = cnh;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getCnh() {
        return cnh;
    }

    public void exibirInfo(){
        System.out.println("Nome: "+ getNome()+ " | CPF: "+ getCpf()
    + " | CNH: "+ getCnh());
    }
}
