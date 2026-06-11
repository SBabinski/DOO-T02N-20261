import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {

        //ATV1
        List<Integer> valores = Arrays.asList(8, 15, 23, 42, 50, 61, 74, 88);
        List<Integer> pares = valores.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println(pares);

        //ATV2
        List<String> nomes = Arrays.asList("roberto", "josé", "caio", "vinicius");
        List<String> maiusculos = nomes.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println(maiusculos);

        //ATV3
        List<String> palavras = Arrays.asList("se", "talvez", "hoje", "sábado", "se", "quarta", "sábado");
        Map<String, Long> contagem = palavras.stream()
                .collect(Collectors.groupingBy(p -> p, Collectors.counting()));
        System.out.println(contagem);

        //ATV4
        List<Produto> estoque = Arrays.asList(
                new Produto("Monitor 144hz", 1250.00),
                new Produto("Mouse Gamer", 95.00),
                new Produto("Teclado Mecanico", 350.00),
                new Produto("Cabo USB-C", 45.00)
        );
        List<Produto> acimaCem = estoque.stream()
                .filter(p -> p.preco > 100.0)
                .collect(Collectors.toList());
        acimaCem.forEach(p -> System.out.println(p.nome + " : R$ " + p.preco));

        //ATV5
        double totalEstoque = estoque.stream()
                .mapToDouble(p -> p.preco)
                .sum();
        System.out.println("Soma total: R$ " + totalEstoque);

        //ATV6
        List<String> linguagens = Arrays.asList("Java", "Python", "C", "JavaScript", "Ruby");
        List<String> listaOrdenada = linguagens.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());
        System.out.println(listaOrdenada);
    }
}

class Produto {
    public String nome;
    public double preco;

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }
}