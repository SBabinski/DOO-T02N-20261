import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    private static void chamarMetodos() {
        atv1();
        atv2();
        atv3();

        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto("Chocolate", 10.00));
        produtos.add(new Produto("Panela", 349.99));
        produtos.add(new Produto("Faca", 79.99));
        produtos.add(new Produto("Porcelana", 110.00));

        atv4(produtos);
        atv5(produtos);
        atv6();
    }

    private static void atv1() {
        System.out.println("======= Exercício 1 =======\n");
        List<Integer> num = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, -14);

        List<Integer> pares = num.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("Números pares: " + pares);
    }

    private static void atv2() {
        System.out.println("======= Exercício 2 =======\n");
        List<String> nomes = List.of("roberto", "josé", "caio", "vinicius");

        List<String> nomeM = nomes.stream()
                .map(m -> m.toUpperCase())
                .collect(Collectors.toList());
        System.out.println("Nomes com letras maiúsculas: " + nomeM);
    }

    private static void atv3() {
        System.out.println("======= Exercício 3 =======\n");
        List<String> palavras = List.of("se", "talvez", "hoje", "sabado", "se", "quarta", "sabado");

        Map<String, Long> contagem = palavras.stream()
                .collect(Collectors.groupingBy(
                    palavra -> palavra,
                    Collectors.counting()
                ));

        System.out.println("Palavras: " + contagem);
    }

    private static void atv4(List<Produto> produtos) {
        System.out.println("======= Exercício 4 =======\n");

        List<Produto> produtosFiltrados = produtos.stream()
                .filter(produto -> produto.getPreco() > 100)
                .collect(Collectors.toList());
        
        System.out.println("Produtos acima de R$ 100,00: " + produtosFiltrados);
    }

    private static void atv5(List<Produto> produtos) {
        System.out.println("======= Exercício 5 =======\n");
        double somaTotal = produtos.stream()
                .mapToDouble(produto -> produto.getPreco())
                .sum();
        
        System.out.println("Soma dos produtos: " + somaTotal);
    }

    private static void atv6() {
        System.out.println("======= Exercício 6 =======\n");
        List<String> linguagens = List.of("Java", "Python", "C", "JavaScript", "Ruby");

        List<String> menorMaior = linguagens.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());

        System.out.println("Lista ordenada: " + menorMaior);
    }

    public static void main(String[] args) {
        chamarMetodos();
    }
}
