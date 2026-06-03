package Calculadora.model;

public class DadosCliente extends DadosPessoa {

    public DadosCliente(String nome, int idade, DadosEndereco endereco) {
        super(nome, idade, endereco);
    }

    @Override
    public void apresentarse() {
        System.out.println("\n======= DADOS DO CLIENTE - " + getNome() + " =======");
        System.out.println("\nNome: " + getNome());
        System.out.println("Idade: " + getIdade());
    }

    @Override
    public String toString() {
        return getNome();
    }
}
