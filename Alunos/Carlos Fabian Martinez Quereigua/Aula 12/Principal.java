package exercicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Principal {
    static ArrayList<Produto> produtos;

    public static void main(String[] args) {
        atividade1();
        atividade2();
        atividade3();
        atividade4();
        atividade5();
        atividade6();
    }

    private static void atividade6() {
        IO.println("-----------Atividade 6-----------");
        List<String> linguagens = List.of(
                "Java", "Python", "C", "JavaScript", "Ruby");

        List<String> ordenadas = linguagens.stream()
                .sorted((a, b) -> a.length() - b.length())
                .toList();

        IO.println(ordenadas);
        IO.println("\n\n");
    }

    private static void atividade5() {
        IO.println("-----------Atividade 5-----------");
        double total = produtos.stream()
                .mapToDouble(produto -> produto.preco)
                .sum();

        IO.println("Valor total dos produtos " + total);
        IO.println("\n\n");
    }

    private static void atividade4() {
        IO.println("-----------Atividade 4-----------");
        Produto p1 = new Produto("Arroz", 10.0);
        Produto p2 = new Produto("Sal", 8.0);
        Produto p3 = new Produto("Feijão", 11.0);
        Produto p4 = new Produto("Sapato", 101.0);

        produtos = new ArrayList<Produto>();
        produtos.add(p1);
        produtos.add(p2);
        produtos.add(p3);
        produtos.add(p4);

        List<Produto> maiorDeCem = produtos.stream()
                .filter(produto -> produto.preco > 100.00)
                .collect(Collectors.toList());

        for (Produto p : maiorDeCem){
            IO.println(p.nome);
        }
        IO.println("\n\n");
    }

    private static void atividade3() {
        IO.println("-----------Atividade 3-----------");
        ArrayList<String> palavras = new ArrayList<>(
                List.of("se", "talvez", "hoje", "sábado", "se", "quarta", "sábado"));

        Map<String, Integer> contagem = palavras.stream()
                .collect(Collectors.toMap(
                        palavra -> palavra,
                        palavra -> 1,
                        (a, b) -> a + b));

        IO.println(contagem);
        IO.println("\n\n");
    }

    private static void atividade2() {
        IO.println("-----------Atividade 2-----------");
        ArrayList<String> nomes = new ArrayList<>(List.of("roberto", "josé", "caio", "vinicius"));

        List<String> nomesMaiusculas = nomes.stream()
                .map(nome -> nome.toUpperCase())
                .toList();

        IO.println(nomesMaiusculas);
        IO.println("\n\n");
    }

    private static void atividade1() {
        IO.println("-----------Atividade 1-----------");

        ArrayList<Integer> numeros = new ArrayList<>(
                List.of(1,2,3,4,5,6,7,8));

        List<Integer> pares = numeros.stream()
                                     .filter(numero -> numero % 2 == 0)
                                     .collect(Collectors.toList());

        IO.println(pares);
        IO.println("\n\n");
    }
}
