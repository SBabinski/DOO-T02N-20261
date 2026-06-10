import java.util.*;
import java.util.stream.*;

public class Main {

    // ===== Classe Produto (para Atv4 e Atv5) =====
    static class Produto {
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

    public static void main(String[] args) {

        // ===== Atv1 - Filtrar números pares =====
        System.out.println("=== Atv1 - Números Pares ===");
        List<Integer> numeros = Arrays.asList(1, 4, 7, 8, 12, 3, 20, 15, 6, 9);
        List<Integer> pares = numeros.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("Lista original: " + numeros);
        System.out.println("Números pares: " + pares);

        // ===== Atv2 - Converter nomes para maiúsculas =====
        System.out.println("\n=== Atv2 - Nomes em Maiúsculo ===");
        List<String> nomes = Arrays.asList("roberto", "josé", "caio", "vinicius");
        List<String> nomesMaiusculos = nomes.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("Original: " + nomes);
        System.out.println("Maiúsculos: " + nomesMaiusculos);

        // ===== Atv3 - Contar ocorrências de cada palavra =====
        System.out.println("\n=== Atv3 - Contagem de Palavras ===");
        List<String> palavras = Arrays.asList("se", "talvez", "hoje", "sábado", "se", "quarta", "sábado");
        Map<String, Long> contagem = palavras.stream()
                .collect(Collectors.groupingBy(p -> p, Collectors.counting()));
        contagem.forEach((palavra, qtd) ->
                System.out.println("\"" + palavra + "\": " + qtd + " vez(es)"));

        // ===== Atv4 - Filtrar produtos com preço > R$ 100,00 =====
        System.out.println("\n=== Atv4 - Produtos acima de R$ 100,00 ===");
        List<Produto> produtos = Arrays.asList(
                new Produto("Teclado", 150.00),
                new Produto("Mouse", 80.00),
                new Produto("Monitor", 1200.00),
                new Produto("Mousepad", 45.00)
        );
        List<Produto> produtosFiltrados = produtos.stream()
                .filter(p -> p.getPreco() > 100.00)
                .collect(Collectors.toList());
        System.out.println("Produtos com preço > R$ 100,00:");
        produtosFiltrados.forEach(System.out::println);

        // ===== Atv5 - Soma do valor total dos produtos da Atv4 =====
        System.out.println("\n=== Atv5 - Soma Total dos Produtos ===");
        double somaTotal = produtos.stream()
                .mapToDouble(Produto::getPreco)
                .sum();
        System.out.printf("Soma total: R$ %.2f%n", somaTotal);

        // ===== Atv6 - Ordenar linguagens por tamanho da palavra =====
        System.out.println("\n=== Atv6 - Linguagens Ordenadas por Tamanho ===");
        List<String> linguagens = Arrays.asList("Java", "Python", "C", "JavaScript", "Ruby");
        List<String> ordenadas = linguagens.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());
        System.out.println("Ordenadas (menor → maior): " + ordenadas);
    }
}
