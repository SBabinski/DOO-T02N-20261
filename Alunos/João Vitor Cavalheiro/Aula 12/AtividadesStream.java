import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import objetos.Produto;

public class AtividadesStream {
    public static void main(String[] args) {

        //Atividade1
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);

        List<Integer> pares = numeros.stream()
                .filter(numero -> numero % 2 == 0)
                .collect(Collectors.toList());

        System.out.println("Atividade 1:");
        System.out.println(pares);
        System.out.println("");

        //Atividade2
        List<String> nomes = Arrays.asList("roberto", "josé", "caio", "vinicius");

        List<String> nomesMaior = nomes.stream()
            .map(nome -> nome.toUpperCase())
            .collect(Collectors.toList());

        System.out.println("Atividade 2:");
        System.out.println(nomesMaior);
        System.out.println("");

        //Atividade3
        List<String> palavras = Arrays.asList("se", "talvez", "hoje", "sábado", "se", "quarta", "sábado");

        Map<String, Long> qtdPalavras = palavras.stream()
            .collect(Collectors.groupingBy(palavra-> palavra,
            Collectors.counting()));

        System.out.println("Atividade 3:");
        System.out.println(qtdPalavras);
        System.out.println("");

        //Atividade4
        List<Produto> produtos = new ArrayList<>();

        Produto prod1 = new Produto("SopaBarata", 22);
        Produto prod2 = new Produto("SopaBoa", 100.01);
        Produto prod3 = new Produto("Sopa", 45);
        Produto prod4 = new Produto("SopaUmPoucoMaisBoa", 101);

        produtos.add(prod1);
        produtos.add(prod2);
        produtos.add(prod3);
        produtos.add(prod4);

        List<Produto> prodCaro = produtos.stream()
            .filter(prod -> prod.getPreco()>100)
            .collect(Collectors.toList());
        
        System.out.println("Atividade 4:");
        System.out.println(prodCaro);
        System.out.println("");

        //Atividade5
        Double valorProd = produtos.stream()
            .map(prod -> prod.getPreco())
            .reduce(0.0,(acumulador,preco)->acumulador+preco);

        System.out.println("Atividade 5:");
        System.out.println(valorProd);
        System.out.println("");

        //Atividade6
        List<String> linguagens = Arrays.asList("Java", "Python", "C", "JavaScript", "Ruby");

        List<String> organizadas = linguagens.stream()
            .sorted(Comparator.comparingInt(linguagem->linguagem.length()))
            .collect(Collectors.toList());

        System.out.println("Atividade 6:");
        System.out.println(organizadas);
    }
}
