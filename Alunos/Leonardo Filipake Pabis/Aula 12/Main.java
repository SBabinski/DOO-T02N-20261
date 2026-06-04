
import java.util.Arrays;
import java.util.List;


public class Main {
    public static void main(String[] args) {
    //ATV1
    //     List<Integer> listaDeNumeros = Arrays.asList(1, 2, 3, 4, 28, 81, 50, 100, 101, 42);
    //     List<Integer> listaNumerosPares = listaDeNumeros.stream().filter(n -> n % 2 == 0).toList();
    //     System.out.println(listaDeNumeros);
    //     System.out.println(listaNumerosPares);

    //ATV2
    // List<String> listaDeNomes = Arrays.asList("roberto", "josé", "caio", "vinicius");
    // List<String> ListaNomesMaiusculos = listaDeNomes.stream().map(String::toUpperCase).collect(Collectors.toList());
    // System.out.println(listaDeNomes);
    // System.out.println(ListaNomesMaiusculos);

    //ATV3
    // List<String> listaDePalavras = Arrays.asList("se", "talvez", "hoje", "sábado", "se", "quarta", "sábado");
    // Map<String, Long> contagemDePalavras = listaDePalavras.stream().collect(Collectors.groupingBy(palavra -> palavra, Collectors.counting()));

    // contagemDePalavras.forEach((palavra, quantidade) -> {
    //     System.out.println(palavra + " : " + quantidade);
    //     });

    //ATV4
    Produto chinelo = new Produto("chinelo", 99.9);
    Produto barato = new Produto("produto barato", 10);
    Produto caro = new Produto("produto caro", 1000);
    Produto ram = new Produto("8gb ram", 200);

    List<Produto> produtos = Arrays.asList(chinelo, barato, caro, ram);
    // List<Produto> produtosCaros = produtos.stream().filter(produto -> produto.getPreco() > 100).collect(Collectors.toList());
    // System.out.println(produtos);
    // System.out.println(produtosCaros);

    //ATV5
    
    }
}
