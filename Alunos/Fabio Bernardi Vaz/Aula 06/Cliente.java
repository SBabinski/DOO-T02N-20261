package fag;

public class Cliente {
    private String nome;
    private int idade;
    private Loja loja;
    private String cidade;
    private String bairro;
    private String rua;

    public Cliente(String nome, int idade, Loja loja, String cidade, String bairro, String rua) {
        this.nome = nome;
        this.idade = idade;
        this.loja = loja;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
    }

    public void apresentarse() {
        String nomeDaLoja = (loja != null) ? loja.getNomeFantasia() : "Sem Loja Vinculada";
        System.out.println("Olá, sou o(a) cliente " + nome + " (" + idade + " anos). Fui cadastrado(a) na " + nomeDaLoja + ".");
    }

    public String getNome() { return nome; }
}