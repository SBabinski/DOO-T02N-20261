import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    private static List<Produto> produtos;

    public static void main(String[] args) {

        atividade1();
        atividade2();
        atividade3();
        atividade4();
        atividade5();
        atividade6();
    }

    // ATV1, abaixo o código.
    public static void atividade1() {
        List<Integer> numeros = List.of(29, 70, 67, 88, 33, 54, 1, 2, 6, 9);
        List<Integer> pares = numeros.stream()
                                     .filter(n -> n % 2 == 0)
                                     .collect(Collectors.toList());
        System.out.println("ATV1 - Numeros pares: " + pares);
    }

    // ATV2, abaixo o código.
    public static void atividade2() {
        List<String> nomes = List.of("roberto", "josé", "caio", "vinicius");
        List<String> nomesMaiusculo = nomes.stream()
                                           .map(String::toUpperCase)
                                           .collect(Collectors.toList());
        System.out.println("ATV2 - Nomes maiusculos: " + nomesMaiusculo);
    }

    // ATV3, abaixo o código.
    public static void atividade3() {
        List<String> palavras = List.of("se", "talvez", "hoje", "sábado", "se", "quarta", "sábado");

        long palavraSolo = palavras.stream()
                                      .filter(p -> Collections.frequency(palavras, p) == 1)
                                      .count();
        System.out.println("ATV3 - Número de palavras sem repeticao: " + palavraSolo);
    }

    // ATV4, abaixo o código.
    public static void atividade4() {
        
        produtos = List.of(

            new Produto("Travesseiro", 29.89),
            new Produto("Action Figure do Padre Pucci", 888.00),
            new Produto("Batata Pre-Frita 850g Aurora", 99.99),
            new Produto("Televisao 67 polegadas", 670.67)
        );
        List<String> produtosMaisCaros = produtos.stream()
                                             .filter(p -> p.getPreco() > 100.00)
                                             .map(Produto::getNome)
                                             .collect(Collectors.toList());
        System.out.println("ATV4 - Produtos mais caros: " + produtosMaisCaros);
    }

    // ATV5, abaixo o código.
    public static void atividade5() {
        double precoTotal = produtos.stream()
                                    .mapToDouble(Produto::getPreco)
                                    .sum();
        System.out.println("ATV5 - Preço total dos produtos: " + precoTotal);
    }

    // ATV6, abaixo o código.
    public static void atividade6() {
        List<String> linguagens = List.of("Java", "Python", "C", "JavaScript", "Ruby");
        List<String> ordenadas = linguagens.stream()
                                          .sorted(Comparator
                                              .comparingInt(String::length)
                                              .thenComparing(Comparator.naturalOrder()))
                                          .collect(Collectors.toList());
        System.out.println("ATV6 - Linguagens ordenadas: " + ordenadas);
    }
}