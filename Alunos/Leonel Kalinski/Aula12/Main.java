package fag;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        // ATV1
        System.out.println("=== ATV1 ===");

        List<Integer> numeros = Arrays.asList(5, 12, 7, 18, 21, 30, 44, 9);

        List<Integer> pares = numeros.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());

        System.out.println("Números pares: " + pares);


        // ATV2
        System.out.println("\n=== ATV2 ===");

        List<String> nomes = Arrays.asList("roberto", "josé", "caio", "vinicius");

        List<String> nomesMaiusculos = nomes.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.println("Nomes em maiúsculo: " + nomesMaiusculos);


        // ATV3
        System.out.println("\n=== ATV3 ===");

        List<String> palavras = Arrays.asList(
                "se", "talvez", "hoje", "sábado",
                "se", "quarta", "sábado"
        );

        Map<String, Long> contagemPalavras = palavras.stream()
                .collect(Collectors.groupingBy(
                        palavra -> palavra,
                        Collectors.counting()
                ));

        System.out.println("Contagem de palavras:");
        contagemPalavras.forEach((palavra, quantidade) ->
                System.out.println(palavra + ": " + quantidade)
        );


        // ATV4
        System.out.println("\n=== ATV4 ===");

        List<Produto> produtos = Arrays.asList(
                new Produto("Mouse", 80.00),
                new Produto("Teclado", 120.00),
                new Produto("Monitor", 900.00),
                new Produto("Headset", 150.00)
        );

        List<Produto> produtosFiltrados = produtos.stream()
                .filter(produto -> produto.getPreco() > 100.00)
                .collect(Collectors.toList());

        System.out.println("Produtos com preço maior que R$100,00:");
        produtosFiltrados.forEach(System.out::println);


        // ATV5
        System.out.println("\n=== ATV5 ===");

        double somaTotal = produtos.stream()
                .mapToDouble(Produto::getPreco)
                .sum();

        System.out.println("Soma total dos produtos: R$ " + somaTotal);


        // ATV6
        System.out.println("\n=== ATV6 ===");

        List<String> linguagens = Arrays.asList("Java", "Python", "C", "JavaScript", "Ruby");

        List<String> ordenadas = linguagens.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());

        System.out.println("Linguagens ordenadas por tamanho:");
        System.out.println(ordenadas);
    }
}

// Classe Produto
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