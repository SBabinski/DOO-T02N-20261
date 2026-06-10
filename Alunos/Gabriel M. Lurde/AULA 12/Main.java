import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        //ATV1
        System.out.println("===== ATV1 - Filtrando números PARES =====");

        List<Integer> numeros = Arrays.asList(
                5, 8, 12, 3, 7, 20, 15, 18);

        List<Integer> pares = numeros.stream()
                .filter(numero -> numero % 2 == 0)
                .collect(Collectors.toList());

        System.out.println("Numeros pares: " + pares);


        //ATV2
        System.out.println("\n===== ATV2 - Nomes em letras MAIÚSCULAS =====");

        List<String> nomes = Arrays.asList(
                "roberto",
                "jose",
                "caio",
                "vinicius");

        List<String> nomesMaiusculos = nomes.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.println("Nomes em maiusculo: " + nomesMaiusculos);


        //ATV3
        System.out.println("\n===== ATV3 - Contagem das palavras =====");

        List<String> palavras = Arrays.asList(
                "se",
                "talvez",
                "hoje",
                "sabado",
                "se",
                "quarta",
                "sabado");

        Map<String, Long> contagem = palavras.stream()
                .collect(Collectors.groupingBy(
                        palavra -> palavra,
                        Collectors.counting()));

        System.out.println("Contagem de palavras:");
        contagem.forEach((palavra, quantidade) ->
                System.out.println(palavra + ": " + quantidade));


        //ATV4
        System.out.println("\n===== ATV4 - Produtos com NOMES e PREÇOS =====");

        List<Produto> produtos = Arrays.asList(
                new Produto("Mouse", 80.00),
                new Produto("Teclado", 150.00),
                new Produto("Monitor", 900.00),
                new Produto("Headset", 120.00)
        );

        List<Produto> produtosFiltrados = produtos.stream()
                .filter(produto -> produto.getPreco() > 100)
                .collect(Collectors.toList());

        System.out.println("Produtos acima de R$100:");
        produtosFiltrados.forEach(System.out::println);


        //ATV5
        System.out.println("\n===== ATV5 - Soma TOTAL dos produtos =====");

        double soma = produtos.stream()
                .mapToDouble(Produto::getPreco)
                .sum();

        System.out.println("Soma total dos produtos: R$ " + soma);


        //ATV6
        System.out.println("\n===== ATV6 - Linguagens ORDENADAS =====");

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
        return nome + " - R$ " + preco;
    }
}