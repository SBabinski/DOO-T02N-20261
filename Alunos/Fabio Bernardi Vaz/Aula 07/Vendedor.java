package fag;

public class Vendedor extends Funcionario {
    
    public Vendedor(String nome, int idade, Endereco endereco, Loja loja, double salarioBase) {
        super(nome, idade, endereco, loja, salarioBase);
    }

    @Override
    public double calcularBonus() {
        return salarioBase * 0.20;
    }
    
    public void exibirDetalhes() {
        System.out.println("\n=== FICHA DO VENDEDOR ===");
        apresentarse();
        endereco.apresentarLogradouro();
        System.out.println("Salário Base: R$ " + salarioBase);
        System.out.printf("Média Salarial: R$ %.2f | Bônus(20%%): R$ %.2f%n", calcularMedia(), calcularBonus());
        System.out.println("=========================");
    }
}