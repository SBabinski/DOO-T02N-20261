import java.time.LocalDate;

public class Vendas {
    int quantidade;
    double valorTotal;
    double desconto;        
    double valorFinal;
    LocalDate data;

    public Vendas(int quantidade, double valorTotal, double desconto, double valorFinal, LocalDate data) {
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
        this.desconto = desconto;
        this.valorFinal = valorFinal;
        this.data = data;
        
    }   
    public void mostrarVenda(){ 
        System.out.println( "");
        System.out.println("=== Detalhes da Venda ===");
        System.out.println("Data: " + data);
        System.out.println("Quantidade: " + quantidade);
        System.out.println("Valor Total: " + valorTotal);
        System.out.println("Desconto: " + desconto);    
        System.out.println("Valor Final: " + valorFinal);
    }
}
