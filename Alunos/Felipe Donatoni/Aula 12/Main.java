import java.util.*;
import java.util.stream.*;

public class Main {

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

        List<Integer> numeros = Arrays.asList(1, 4, 7, 8, 12, 15, 22, 33, 40, 51);

        List<Integer> pares = numeros.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());

        System.out.println("ATV1 - Numeros pares: " + pares);

        List<String> nomes = Arrays.asList("roberto", "josé", "caio", "vinicius");

        List<String> maiusculos = nomes.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.println("ATV2 - Nomes em maiusculo: " + maiusculos);

        List<String> palavras = Arrays.asList("se", "talvez", "hoje", "sábado", "se", "quarta", "sábado");

        Map<String, Long> contagem = palavras.stream()
                .collect(Collectors.groupingBy(p -> p, Collectors.counting()));

        System.out.println("ATV3 - Contagem de palavras: " + contagem);

        List<Produto> produtos = Arrays.asList(

                new Produto("Teclado", 150.00),
                new Produto("Mouse", 80.00),
                new Produto("Monitor", 899.90),
                new Produto("Mousepad", 45.00)
                
        );

        List<Produto> filtrados = produtos.stream()
                .filter(p -> p.getPreco() > 100.00)
                .collect(Collectors.toList());

        System.out.println("ATV4 - Produtos acima de R$ 100,00: " + filtrados);

        double total = produtos.stream()
                .mapToDouble(Produto::getPreco)
                .sum();

        System.out.println("ATV5 - Total dos produtos: R$ " + String.format("%.2f", total));

        List<String> linguagens = Arrays.asList("Java", "Python", "C", "JavaScript", "Ruby");

        List<String> ordenadas = linguagens.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());

        System.out.println("ATV6 - Linguagens por tamanho: " + ordenadas);
    }
}