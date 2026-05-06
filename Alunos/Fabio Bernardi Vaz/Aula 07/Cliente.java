package fag;

public class Cliente extends Pessoa {
    private Loja loja;

    public Cliente(String nome, int idade, Endereco endereco, Loja loja) {
        super(nome, idade, endereco);
        this.loja = loja;
    }

    @Override
    public void apresentarse() {
        System.out.println("Olá, sou o(a) cliente " + nome + " (" + idade + " anos).");
    }
}