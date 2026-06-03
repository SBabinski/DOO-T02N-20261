public class Cliente{
    private String nome;
    private String cpf;
    private String cnh;


    public Cliente(String nome, String cpf, String cnh){
        this.nome = nome;
        this.cpf = cpf;
        this.cnh = cnh;
    }

    public void mostrar(){
        System.out.println("Nome: " + nome + "| CPF: " + cpf + "| CNH: " + cnh);
    }
}