import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        // ATV1
        List<Integer> numeros = Arrays.asList(10, 15, 8, 21, 34, 7, 12, 5);

        List<Integer> pares = numeros.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());

        System.out.println("ATV1 - Números pares:");
        System.out.println(pares);


        // ATV2
        List<String> nomes = Arrays.asList("roberto", "josé", "caio", "vinicius");

        List<String> nomesMaiusculos = nomes.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.println("\nATV2 - Nomes em maiúsculo:");
        System.out.println(nomesMaiusculos);


        // ATV3
        List<String> palavras = Arrays.asList(
                "se", "talvez", "hoje", "sábado",
                "se", "quarta", "sábado"
        );

        Map<String, Long> contagemPalavras = palavras.stream()
                .collect(Collectors.groupingBy(
                        palavra -> palavra,
                        Collectors.counting()
                ));

        System.out.println("\nATV3 - Contagem de palavras:");
        System.out.println(contagemPalavras);


        // ATV4
        List<Produto> produtos = Arrays.asList(
                new Produto("Mouse", 80.00),
                new Produto("Teclado", 120.00),
                new Produto("Monitor", 900.00),
                new Produto("Webcam", 150.00)
        );

        List<Produto> produtosAcimaDe100 = produtos.stream()
                .filter(produto -> produto.getPreco() > 100.00)
                .collect(Collectors.toList());

        System.out.println("\nATV4 - Produtos com preço maior que R$100:");
        produtosAcimaDe100.forEach(System.out::println);


        // ATV5
        double somaTotal = produtos.stream()
                .mapToDouble(Produto::getPreco)
                .sum();

        System.out.println("\nATV5 - Soma total dos produtos:");
        System.out.println("R$ " + somaTotal);


        // ATV6
        List<String> linguagens = Arrays.asList(
                "Java", "Python", "C", "JavaScript", "Ruby"
        );

        List<String> linguagensOrdenadas = linguagens.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());

        System.out.println("\nATV6 - Linguagens ordenadas por tamanho:");
        System.out.println(linguagensOrdenadas);
    }
}

class Produto {
    private String nome;
    private double preco;

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    @Override
    public String toString() {
        return "Produto{nome='" + nome + "', preco=" + preco + "}";
    }
}