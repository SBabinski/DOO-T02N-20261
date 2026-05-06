public class Vendas {
    int quantidade;
    double valorTotal;
    double desconto;        
    double valorFinal;

    public Vendas(int quantidade, double valorTotal, double desconto, double valorFinal) {
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
        this.desconto = desconto;
        this.valorFinal = valorFinal;
    }   
    public void mostrarVenda(){ 
        System.out.println( "");
        System.out.println("-------------------");
        System.err.println("=== Detalhes da Venda ===");
        System.out.println("Quantidade: " + quantidade);
        System.out.println("Valor Total: " + valorTotal);
        System.out.println("Desconto: " + desconto);    
        System.out.println("Valor Final: " + valorFinal);
    }
}
