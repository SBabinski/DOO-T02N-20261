import java.util.*;
import java.util.stream.*;

public class Main {

    // Atv4 e Atv5: Classe Produto 
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

        // Atv1
        System.out.println("===== Atv1: Números Pares =====");
        List<Integer> numeros = Arrays.asList(3, 8, 15, 22, 7, 14, 9, 30, 11, 42);
        List<Integer> pares = numeros.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("Lista original: " + numeros);
        System.out.println("Números pares: " + pares);

        // Atv2
        System.out.println("\n===== Atv2: Nomes em Maiúsculas =====");
        List<String> nomes = Arrays.asList("roberto", "josé", "caio", "vinicius");
        List<String> nomesMaiusculos = nomes.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("Lista original: " + nomes);
        System.out.println("Em maiúsculas:  " + nomesMaiusculos);

        // Atv3
        System.out.println("\n===== Atv3: Contagem de Palavras =====");
        List<String> palavras = Arrays.asList("se", "talvez", "hoje", "sábado", "se", "quarta", "sábado");
        Map<String, Long> contagem = palavras.stream()
                .collect(Collectors.groupingBy(p -> p, Collectors.counting()));
        System.out.println("Lista de palavras: " + palavras);
        System.out.println("Contagem:");
        contagem.forEach((palavra, count) ->
                System.out.println("  \"" + palavra + "\": " + count + "x"));

        // Atv4
        System.out.println("\n===== Atv4: Produtos com preço > R$ 100,00 =====");
        List<Produto> produtos = Arrays.asList(
                new Produto("Teclado",   150.00),
                new Produto("Mouse",      80.00),
                new Produto("Monitor",   900.00),
                new Produto("Headset",    99.90)
        );
        List<Produto> produtosFiltrados = produtos.stream()
                .filter(p -> p.getPreco() > 100.00)
                .collect(Collectors.toList());
        System.out.println("Todos os produtos:");
        produtos.forEach(p -> System.out.println("  " + p));
        System.out.println("Produtos acima de R$ 100,00:");
        produtosFiltrados.forEach(p -> System.out.println("  " + p));

        // Atv5
        System.out.println("\n===== Atv5: Soma Total dos Produtos =====");
        double total = produtos.stream()
                .mapToDouble(Produto::getPreco)
                .sum();
        System.out.printf("Soma total dos produtos: R$ %.2f%n", total);

        // Atv6
        System.out.println("\n===== Atv6: Linguagens Ordenadas por Tamanho =====");
        List<String> linguagens = Arrays.asList("Java", "Python", "C", "JavaScript", "Ruby");
        List<String> linguagensOrdenadas = linguagens.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());
        System.out.println("Lista original:  " + linguagens);
        System.out.println("Lista ordenada:  " + linguagensOrdenadas);
    }
}
