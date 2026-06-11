import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        // ATV1
        List<Integer> numeros = List.of(10, 3, 5, 8, 12, 7, 20, 1);

        List<Integer> numerosPares = numeros.stream()
                .filter(numero -> numero % 2 == 0)
                .collect(Collectors.toList());

        System.out.println("ATV1 - Numeros pares: " + numerosPares);

        // ATV2
        List<String> nomes = List.of("roberto", "josé", "caio", "vinicius");

        List<String> nomesMaiusculos = nomes.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.println("ATV2 - Nomes em maiusculo: " + nomesMaiusculos);

        // ATV3
        List<String> palavras = List.of("se", "talvez", "hoje", "sábado", "se", "quarta", "sábado");

        Map<String, Long> quantidadePorPalavra = palavras.stream()
                .collect(Collectors.groupingBy(palavra -> palavra, Collectors.counting()));

        System.out.println("ATV3 - Quantidade por palavra: " + quantidadePorPalavra);

        // ATV4
        List<Produto> produtos = List.of(
                new Produto("Teclado", 80.00),
                new Produto("Monitor", 900.00),
                new Produto("Mouse", 55.00),
                new Produto("Headset", 150.00)
        );

        List<Produto> produtosAcimaDeCem = produtos.stream()
                .filter(produto -> produto.getPreco() > 100.00)
                .collect(Collectors.toList());

        System.out.println("ATV4 - Produtos acima de R$ 100,00: " + produtosAcimaDeCem);

        // ATV5
        double somaTotalProdutos = produtos.stream()
                .mapToDouble(Produto::getPreco)
                .sum();

        System.out.println("ATV5 - Soma total dos produtos: R$ " + somaTotalProdutos);

        // ATV6
        List<String> linguagens = List.of("Java", "Python", "C", "JavaScript", "Ruby");

        List<String> linguagensOrdenadasPorTamanho = linguagens.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());

        System.out.println("ATV6 - Linguagens ordenadas por tamanho: " + linguagensOrdenadasPorTamanho);
    }

    static class Produto {
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
            return "Produto{nome='" + nome + "', preco=R$ " + preco + "}";
        }
    }
}