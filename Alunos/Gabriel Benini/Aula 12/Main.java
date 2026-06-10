import java.util.*;
import java.util.stream.*;
import java.util.function.*;

public class Main {

    //ATV4
    static class Produto {
        String nome;
        double preco;

        Produto(String nome, double preco) {
            this.nome = nome;
            this.preco = preco;
        }

        @Override
        public String toString() {
            return nome + " - R$" + preco;
        }

        static boolean precoMaiorQueCem(Produto p) {
            return p.preco > 100.00;
        }

        static double getPreco(Produto p) {
            return p.preco;
        }
    }

    static boolean isPar(int n) {
        return n % 2 == 0;
    }

    static String maiusculo(String s) {
        return s.toUpperCase();
    }

    static Function<String, String> identidade() {
        return Function.identity();
    }

    static int compararTamanho(String a, String b) {
        return a.length() - b.length();
    }

    static void imprimirPalavra(Map.Entry<String, Long> entrada) {
        System.out.println(entrada.getKey() + ": " + entrada.getValue());
    }

    public static void main(String[] args) {

        //ATV1
        List<Integer> numeros = Arrays.asList(3, 8, 15, 22, 7, 44, 10, 33, 6, 19);

        List<Integer> numerosPares = numeros.stream()
                .filter(Main::isPar)
                .collect(Collectors.toList());

        System.out.println("ATV1 - Números pares:");
        System.out.println(numerosPares);

        //ATV2
        List<String> nomes = Arrays.asList("roberto", "josé", "caio", "vinicius");

        List<String> nomesMaiusculos = nomes.stream()
                .map(Main::maiusculo)
                .collect(Collectors.toList());

        System.out.println("\nATV2 - Nomes em maiúsculas:");
        System.out.println(nomesMaiusculos);

        //ATV3
        List<String> palavras = Arrays.asList("se", "talvez", "hoje", "sábado", "se", "quarta", "sábado");

        Map<String, Long> contagemPalavras = palavras.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        System.out.println("\nATV3 - Contagem de palavras:");
        contagemPalavras.entrySet().stream()
                .forEach(Main::imprimirPalavra);

        //ATV4
        List<Produto> produtos = Arrays.asList(
                new Produto("Teclado", 150.00),
                new Produto("Mouse", 80.00),
                new Produto("Monitor", 900.00),
                new Produto("Headset", 60.00)
        );

        List<Produto> produtosFiltrados = produtos.stream()
                .filter(Produto::precoMaiorQueCem)
                .collect(Collectors.toList());

        System.out.println("\nATV4 - Produtos com preço maior que R$100,00:");
        produtosFiltrados.forEach(System.out::println);

        //ATV5
        double somaTotal = produtos.stream()
                .mapToDouble(Produto::getPreco)
                .sum();

        System.out.println("\nATV5 - Soma total dos preços:");
        System.out.println("R$" + somaTotal);

        //ATV6
        List<String> linguagens = Arrays.asList("Java", "Python", "C", "JavaScript", "Ruby");

        List<String> linguagensOrdenadas = linguagens.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());

        System.out.println("\nATV6 - Linguagens ordenadas por tamanho:");
        System.out.println(linguagensOrdenadas);
    }
}