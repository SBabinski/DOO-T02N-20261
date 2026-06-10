import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        System.out.println("--- LISTA DE ATIVIDADES StreamAPI ---");

        // ATV1 - Filtrar números pares
        System.out.println("\n// ATV1");
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 15, 20);
        List<Integer> numerosPares = numeros.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("Números pares: " + numerosPares);


        // ATV2 - Converter nomes para maiúsculas
        System.out.println("\n// ATV2");
        List<String> nomes = Arrays.asList("roberto", "josé", "caio", "vinicius");
        List<String> nomesMaiusculos = nomes.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("Nomes em maiúsculo: " + nomesMaiusculos);


        // ATV3 - Contar ocorrência de cada palavra
        System.out.println("\n// ATV3");
        List<String> palavras = Arrays.asList("se", "talvez", "hoje", "sábado", "se", "quarta", "sábado");
        Map<String, Long> contagemPalavras = palavras.stream()
                .collect(Collectors.groupingBy(palavra -> palavra, Collectors.counting()));
        System.out.println("Contagem da ocorrencia de palavras: " + contagemPalavras);


        // ATV4 - Filtrar produtos com preço maior que R$ 100,00 
        System.out.println("\n// ATV4");
        Map<String, Double> produtos = Map.of(
                "Mouse", 45.50,
                "Teclado Mecânico", 250.00,
                "Monitor", 950.00,
                "Cabo USB", 25.00
        );
        
        List<Map.Entry<String, Double>> produtosMaisDeCem = produtos.entrySet().stream()
                .filter(entry -> entry.getValue() > 100.0)
                .collect(Collectors.toList());
        System.out.println("Produtos acima de R$ 100,00: " + produtosMaisDeCem);


        // ATV5 - Soma do valor total dos produtos 
        System.out.println("\n// ATV5");
        double somaTotal = produtos.values().stream()
                .mapToDouble(Double::doubleValue)
                .sum();
        System.out.println("Soma total dos produtos: R$ " + somaTotal);


        // ATV6 - Ordenar lista pelo tamanho da palavra
        System.out.println("\n// ATV6");
        List<String> linguagens = Arrays.asList("Java", "Python", "C", "JavaScript", "Ruby");
        List<String> linguagensOrdenadas = linguagens.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());
        System.out.println("Linguagens ordenadas por tamanho: " + linguagensOrdenadas);

    }
}