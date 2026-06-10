import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

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
            return "Produto{nome='" + nome + "', preco=R$ " + String.format("%.2f", preco) + "}";
        }
    }

    public static void main(String[] args) {

        //ATV1
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        List<Integer> numerosPares = numeros.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("atv1");
        System.out.println("Lista original : " + numeros);
        System.out.println("Numeros pares  : " + numerosPares);





        //ATV2
        List<String> nomes = Arrays.asList("roberto", "jose", "caio", "vinicius");
        List<String> nomesMaiusculos = nomes.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("\n atv2");
        System.out.println("Lista original   : " + nomes);
        System.out.println("Nomes maiusculos : " + nomesMaiusculos);





        //ATV3
        List<String> palavras = Arrays.asList("se", "talvez", "hoje", "sabado", "se", "quarta", "sabado");
        Map<String, Long> contagemPalavras = palavras.stream()
                .collect(Collectors.groupingBy(p -> p, Collectors.counting()));
        System.out.println("\n atv3");
        System.out.println("Lista original    : " + palavras);
        System.out.println("Contagem por palavra : " + contagemPalavras);






        //ATV4
        List<Produto> produtos = Arrays.asList(
                new Produto("Notebook",  2500.00),
                new Produto("Mouse",       80.00),
                new Produto("Teclado",    150.00),
                new Produto("Headset",     90.00)
        );
        List<Produto> produtosFiltrados = produtos.stream()
                .filter(p -> p.getPreco() > 100.00)
                .collect(Collectors.toList());
        System.out.println("\n atv4");
        produtosFiltrados.forEach(p -> System.out.println("  " + p));





        //ATV5
        double somaTotal = produtos.stream()
                .mapToDouble(Produto::getPreco)
                .sum();
        System.out.println("\n atv5");
        System.out.printf("Valor total: R$ %.2f%n", somaTotal);





        
        //ATV6
        List<String> linguagens = Arrays.asList("Java", "Python", "C", "JavaScript", "Ruby");
        List<String> linguagensOrdenadas = linguagens.stream()
                .sorted((a, b) -> Integer.compare(a.length(), b.length()))
                .collect(Collectors.toList());
        System.out.println("\n atv6");
        System.out.println("Lista original : " + linguagens);
        System.out.println("Lista ordenada : " + linguagensOrdenadas);
    }
}