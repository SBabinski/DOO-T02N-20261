import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static class Produto {

        private final String nome;
        private final double preco;

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

    public static void main(String[] args) {

        //ATV1
        System.out.println("===== ATV1 =====");

        List<Integer> numeros = List.of(
                11, 24, 37, 42, 55, 68, 73, 84, 97, 100);

        List<Integer> pares = numeros.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());

        System.out.println("Numeros pares encontrados:");
        pares.forEach(System.out::println);



        //ATV2
        System.out.println("\n===== ATV2 =====");

        List<String> nomes = List.of(
                "mariana",
                "felipe",
                "beatriz",
                "gustavo");

        List<String> maiusculos = nomes.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.println("Nomes convertidos:");
        maiusculos.forEach(System.out::println);



        //ATV3
        System.out.println("\n===== ATV3 =====");

        List<String> palavras = List.of(
                "sol",
                "chuva",
                "vento",
                "sol",
                "nuvem",
                "chuva",
                "sol");

        Map<String, Long> contagem = palavras.stream()
                .collect(Collectors.groupingBy(
                        palavra -> palavra,
                        Collectors.counting()));

        contagem.forEach((palavra, quantidade) ->
                System.out.println(
                        palavra + ": " + quantidade));
        
        
        
        //ATV4
        System.out.println("\n===== ATV4 =====");

        List<Produto> produtos = List.of(
                new Produto("Geladeira", 2800.00),
                new Produto("Liquidificador", 90.00),
                new Produto("Microondas", 650.00),
                new Produto("Ventilador", 180.00)
        );

        List<Produto> filtrados = produtos.stream()
                .filter(produto -> produto.getPreco() > 100)
                .collect(Collectors.toList());

        System.out.println("Produtos acima de R$100:");
        filtrados.forEach(System.out::println);



        //ATV5
        System.out.println("\n===== ATV5 =====");

        double total = produtos.stream()
                .mapToDouble(Produto::getPreco)
                .sum();

        System.out.println("Valor total dos produtos: R$ " + total);



        //ATV6
        System.out.println("\n===== ATV6 =====");

        List<String> linguagens = List.of(
                "Kotlin",
                "Go",
                "Swift",
                "PHP",
                "TypeScript");

        List<String> ordenadas = linguagens.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());

        System.out.println("Ordenadas por tamanho:");

        ordenadas.forEach(System.out::println);
    }
}