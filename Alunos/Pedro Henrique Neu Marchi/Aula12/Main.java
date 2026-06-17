import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        // ATV1
        System.out.println("===== ATV1 =====");

        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> pares = numeros.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());

        System.out.println("Números pares: " + pares);


        // ATV2
        System.out.println("\n===== ATV2 =====");

        List<String> nomes = Arrays.asList(
                "roberto",
                "josé",
                "caio",
                "vinicius");

        List<String> nomesMaiusculos = nomes.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.println("Nomes em maiúsculo: " + nomesMaiusculos);


        // ATV3
        System.out.println("\n===== ATV3 =====");

        List<String> palavras = Arrays.asList(
                "se",
                "talvez",
                "hoje",
                "sábado",
                "se",
                "quarta",
                "sábado");

        Map<String, Long> contagem = palavras.stream()
                .collect(Collectors.groupingBy(
                        palavra -> palavra,
                        Collectors.counting()));

        System.out.println("Contagem de palavras:");
        contagem.forEach((palavra, quantidade) ->
                System.out.println(palavra + " = " + quantidade));


        // ATV4
        System.out.println("\n===== ATV4 =====");

        List<Produto> produtos = Arrays.asList(
                new Produto("Mouse", 80.00),
                new Produto("Teclado", 150.00),
                new Produto("Monitor", 900.00),
                new Produto("Webcam", 120.00));

        List<Produto> produtosFiltrados = produtos.stream()
                .filter(produto -> produto.preco > 100)
                .collect(Collectors.toList());

        System.out.println("Produtos acima de R$100,00:");

        produtosFiltrados.forEach(System.out::println);


        // ATV5
        System.out.println("\n===== ATV5 =====");

        double soma = produtos.stream()
                .mapToDouble(produto -> produto.preco)
                .sum();

        System.out.println("Valor total dos produtos: R$ " + soma);


        // ATV6
        System.out.println("\n===== ATV6 =====");

        List<String> linguagens = Arrays.asList(
                "Java",
                "Python",
                "C",
                "JavaScript",
                "Ruby");

        List<String> ordenadas = linguagens.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());

        System.out.println("Ordenadas por tamanho:");
        System.out.println(ordenadas);
    }
}

class Produto {

    String nome;
    double preco;

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    @Override
    public String toString() {
        return nome + " - R$ " + preco;
    }
}