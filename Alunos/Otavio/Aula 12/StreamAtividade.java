import java.util.*;
import java.util.stream.*;

public class StreamAtividade {

    public static void main(String[] args) {

        //Atv1

        List<Integer> numeros = Arrays.asList(1, 4, 7, 12, 9, 22, 3, 18, 6, 11);

        List<Integer> pares = numeros.stream()
                .filter(n -> n % 2 == 0)
                .toList();

        System.out.println("=== Atv1 – Números pares ===");
        System.out.println("Lista original : " + numeros);
        System.out.println("Apenas pares   : " + pares);

        //Atv2

        List<String> nomes = Arrays.asList("roberto", "josé", "caio", "vinicius");

        List<String> nomesMaiusculos = nomes.stream()
                .map(String::toUpperCase)
                .toList();

        System.out.println("\n=== Atv2 – Nomes em maiúsculas ===");
        System.out.println("Original   : " + nomes);
        System.out.println("Maiúsculas : " + nomesMaiusculos);

        //Atv3

        List<String> palavras = Arrays.asList(
                "se", "talvez", "hoje", "sábado", "se", "quarta", "sábado");

        Map<String, Long> contagem = palavras.stream()
                .collect(Collectors.groupingBy(p -> p, Collectors.counting()));

        System.out.println("\n=== Atv3 – Contagem de palavras ===");
        contagem.forEach((palavra, qtd) ->
                System.out.println("  \"" + palavra + "\" → " + qtd + "x"));

        //Atv4

        List<Produto> produtos = Arrays.asList(
                new Produto("Teclado",    89.90),
                new Produto("Monitor",   899.00),
                new Produto("Mouse",      49.90),
                new Produto("Headset",   199.90)
        );

        List<Produto> produtosCaros = produtos.stream()
                .filter(p -> p.getPreco() > 100.00)
                .toList();

        System.out.println("\n=== Atv4 – Produtos com preço > R$ 100,00 ===");
        produtosCaros.forEach(p -> System.out.println("  " + p));

        //Atv5

        double total = produtos.stream()
                .mapToDouble(Produto::getPreco)
                .sum();

        System.out.println("\n=== Atv5 – Soma total dos produtos ===");
        System.out.printf("  Total: R$ %.2f%n", total);

        //Atv6

        List<String> linguagens = Arrays.asList(
                "Java", "Python", "C", "JavaScript", "Ruby");

        List<String> linguagensOrdenadas = linguagens.stream()
                .sorted(Comparator.comparingInt(String::length))
                .toList();

        System.out.println("\n=== Atv6 – Linguagens ordenadas por tamanho ===");
        System.out.println("Original  : " + linguagens);
        System.out.println("Ordenadas : " + linguagensOrdenadas);
    }
}