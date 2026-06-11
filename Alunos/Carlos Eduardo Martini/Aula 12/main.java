import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        //ATV1
        List<Integer> nums = Arrays.asList(9, 30, 4, 57, 0, 65, 9, 8, 3, 7);
        List<Integer> numsPares = 
        nums.stream()
            .filter(n -> n % 2 ==0)
            .collect(Collectors.toList());
        
        System.out.println("ATV1: " + numsPares);
        
        //ATV2
        List<String> nomes = Arrays.asList("roberto", "josé", "caio", "vinicius");
        List<String> nomesMaiusculo = nomes.stream()
                                            .map(nome -> nome.toUpperCase())
                                            .collect(Collectors.toList());
        
        System.out.println("ATV2: " + nomesMaiusculo);

        //ATV3
        List<String> palav = Arrays.asList("se", "talvez", "hoje", "sábado", "se", "quarta", "sábado");
        Map<String, Long> contagemPalavras =
                            palav.stream()
                                 .collect(Collectors.groupingBy(p -> p, Collectors.counting()));

        System.out.println("ATV3: " + contagemPalavras);

        //ATV4 
        List<Produto> produto = Arrays.asList(
            new Produto("Mouse Simples", 45.00),
            new Produto("Teclado Mecânico", 250.00),
            new Produto("Monitor 24 polegadas", 800.00),
            new Produto("Mousepad", 30.00d)
        );
        List<Produto> prodCaros = produto.stream()
                .filter(p -> p.getPreco() > 100.00)
                .collect(Collectors.toList());

        System.out.println("ATV4: " + prodCaros);

        //ATV5
        double valorTotal = produto.stream()
                .mapToDouble(p -> p.getPreco()) 
                .sum();                         
                
        System.out.println("ATV5: R$ " + valorTotal); 

        //ATV6
        List<String> linguagens = Arrays.asList("Java", "Python", "C", "JavaScript", "Ruby");
        
        List<String> linguagensOrdenadas = linguagens.stream()
                .sorted(java.util.Comparator.comparing(String::length)) 
                .collect(Collectors.toList());                         
                
        System.out.println("ATV6: " + linguagensOrdenadas);
    }
}

class Produto {
    private String nome;
    private double preco;

    public Produto(String nome, double preco){
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome(){
        return nome;
    }

    public double getPreco(){
        return preco;
    }
    
    public String toString(){
        return nome + "(R$ " + preco + ")";
    }
}