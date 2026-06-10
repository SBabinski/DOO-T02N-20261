import java.util.*;
import java.util.stream.*;

public class Main {

    public static void main(String[] args) {

        //ATV1
        List<Integer> numeros = Arrays.asList(1, 4, 7, 8, 13, 20, 33, 42, 55, 60);

        List<Integer> pares = numeros.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());

        System.out.println("=== ATV1 - Números Pares ===");
        System.out.println("Lista original: " + numeros);
        System.out.println("Números pares:  " + pares);

        //ATV2
        List<String> nomes = Arrays.asList("roberto", "josé", "caio", "vinicius");

        List<String> nomesMaiusculos = nomes.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.println("\n=== ATV2 - Nomes em Maiúsculo ===");
        System.out.println("Original:   " + nomes);
        System.out.println("Maiúsculos: " + nomesMaiusculos);

        //ATV3
        List<String> palavras = Arrays.asList("se", "talvez", "hoje", "sábado", "se", "quarta", "sábado");

        Map<String, Long> contagem = palavras.stream()
                .collect(Collectors.groupingBy(p -> p, Collectors.counting()));

        System.out.println("\n=== ATV3 - Contagem de Palavras ===");
        contagem.forEach((palavra, qtd) ->
                System.out.println("\"" + palavra + "\" aparece " + qtd + " vez(es)"));

        //ATV4
        class Produto {
            private String nome;
            private double preco;

            public Produto(String nome, double preco) {
                this.nome = nome;
                this.preco = preco;
            }

            public String getNome() { return nome; }
            public double getPreco() { return preco; }

            @Override
            public String toString() {
                return nome + " - R$ " + String.format("%.2f", preco);
            }
        }

        List<Produto> produtos = Arrays.asList(
                new Produto("Teclado", 150.00),
                new Produto("Mouse", 80.00),
                new Produto("Monitor", 950.00),
                new Produto("Headset", 99.90)
        );

        List<Produto> produtosFiltrados = produtos.stream()
                .filter(p -> p.getPreco() > 100.00)
                .collect(Collectors.toList());

        System.out.println("\n=== ATV4 - Produtos acima de R$ 100,00 ===");
        produtosFiltrados.forEach(System.out::println);

        //ATV5
        double total = produtos.stream()
                .mapToDouble(Produto::getPreco)
                .sum();

        System.out.println("\n=== ATV5 - Soma Total dos Produtos ===");
        System.out.printf("Valor total: R$ %.2f%n", total);

        //ATV6
        List<String> linguagens = Arrays.asList("Java", "Python", "C", "JavaScript", "Ruby");

        List<String> ordenadas = linguagens.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());

        System.out.println("\n=== ATV6 - Linguagens Ordenadas por Tamanho ===");
        System.out.println("Original:  " + linguagens);
        System.out.println("Ordenadas: " + ordenadas);
    }
}