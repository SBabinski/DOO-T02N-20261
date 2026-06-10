package Aula12;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        // ===================== ATV1 =====================
        // Receber uma lista de números inteiros e retornar apenas os pares usando Stream API
        List<Integer> numeros = Arrays.asList(10, 15, 22, 33, 44, 51, 60, 77, 88, 95);

        List<Integer> pares = numeros.stream()
                .filter(numero -> numero % 2 == 0)
                .collect(Collectors.toList());

        System.out.println("===== ATV1 - Números pares =====");
        System.out.println("Lista original: " + numeros);
        System.out.println("Números pares: " + pares);
        System.out.println();


        // ===================== ATV2 =====================
        // Converter todos os nomes para letras maiúsculas usando Stream API
        List<String> nomes = Arrays.asList("roberto", "josé", "caio", "vinicius");

        List<String> nomesMaiusculos = nomes.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.println("===== ATV2 - Nomes em maiúsculas =====");
        System.out.println("Lista original: " + nomes);
        System.out.println("Lista em maiúsculas: " + nomesMaiusculos);
        System.out.println();


        // ===================== ATV3 =====================
        // Contar quantas vezes cada palavra única aparece em uma lista de strings usando Stream API
        List<String> palavras = Arrays.asList("se", "talvez", "hoje", "sábado", "se", "quarta", "sábado");

        Map<String, Long> contagemPalavras = palavras.stream()
                .collect(Collectors.groupingBy(palavra -> palavra, Collectors.counting()));

        System.out.println("===== ATV3 - Contagem de palavras =====");
        System.out.println("Lista original: " + palavras);
        System.out.println("Contagem: " + contagemPalavras);
        System.out.println();


        // ===================== ATV4 =====================
        // Criar a classe Produto (definida abaixo da classe Main) e filtrar produtos com preço > 100,00
        List<Produto> produtos = Arrays.asList(
                new Produto("Notebook", 3500.00),
                new Produto("Mouse", 45.90),
                new Produto("Teclado", 120.00),
                new Produto("Caneta", 2.50)
        );

        List<Produto> produtosCaros = produtos.stream()
                .filter(produto -> produto.getPreco() > 100.00)
                .collect(Collectors.toList());

        System.out.println("===== ATV4 - Produtos com preço maior que R$ 100,00 =====");
        produtosCaros.forEach(produto -> System.out.println(produto.getNome() + " - R$ " + produto.getPreco()));
        System.out.println();


        // ===================== ATV5 =====================
        // Somar o valor total de todos os produtos da lista criada na ATV4 usando Stream API
        double valorTotal = produtos.stream()
                .mapToDouble(Produto::getPreco)
                .sum();

        System.out.println("===== ATV5 - Soma total dos produtos =====");
        System.out.println("Valor total: R$ " + valorTotal);
        System.out.println();


        // ===================== ATV6 =====================
        // Ordenar a lista de strings conforme o tamanho da palavra, da menor para a maior, usando Stream API
        List<String> linguagens = Arrays.asList("Java", "Python", "C", "JavaScript", "Ruby");

        List<String> linguagensOrdenadas = linguagens.stream()
                .sorted((palavra1, palavra2) -> Integer.compare(palavra1.length(), palavra2.length()))
                .collect(Collectors.toList());

        System.out.println("===== ATV6 - Lista ordenada por tamanho da palavra =====");
        System.out.println("Lista original: " + linguagens);
        System.out.println("Lista ordenada: " + linguagensOrdenadas);
    }
}


// Classe Produto utilizada na ATV4 e ATV5
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
}