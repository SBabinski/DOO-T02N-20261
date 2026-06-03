package fag;
import java.util.ArrayList;
import java.util.List;

public class Vendedor {
    private String nome;
    private int idade;
    private Loja loja;
    private String cidade;
    private String bairro;
    private String rua;
    private double salarioBase;
    
    private List<Double> salarioRecebido = new ArrayList<>();

    public Vendedor(String nome, int idade, Loja loja, String cidade, String bairro, String rua, double salarioBase) {
        this.nome = nome;
        this.idade = idade;
        this.loja = loja;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.salarioBase = salarioBase;
        
        // Inserindo valores fixos no código como sugerido
        salarioRecebido.add(1500.00); 
        salarioRecebido.add(1650.50); 
        salarioRecebido.add(1550.00); 
    }

    public void apresentarse() {
        String nomeDaLoja = (loja != null) ? loja.getNomeFantasia() : "Sem Loja Definida";
        System.out.println("Olá, meu nome é " + nome + ", tenho " + idade + " anos e trabalho na loja " + nomeDaLoja + ".");
    }

    public double calcularMedia() {
        if (salarioRecebido.isEmpty()) return 0;
        
        double soma = 0;
        for (double salario : salarioRecebido) {
            soma += salario;
        }
        return soma / salarioRecebido.size();
    }

    public double calcularBonus() {
        return salarioBase * 0.20;
    }
    
    public void exibirDetalhes() {
        System.out.println("\n=== FICHA DO VENDEDOR ===");
        System.out.println("Nome: " + nome + " | Idade: " + idade + " anos");
        System.out.println("Endereço: " + rua + ", " + bairro + " - " + cidade);
        System.out.println("Loja de Atuação: " + (loja != null ? loja.getNomeFantasia() : "Nenhuma"));
        System.out.println("Salário Base: R$ " + salarioBase);
        System.out.println("Histórico de Salários Recebidos: " + salarioRecebido);
        System.out.printf("Média Salarial: R$ %.2f%n", calcularMedia());
        System.out.printf("Bônus Calculado (20%%): R$ %.2f%n", calcularBonus());
        System.out.println("=========================");
    }

    public String getNome() { return nome; }
    public Loja getLoja() { return loja; }
}