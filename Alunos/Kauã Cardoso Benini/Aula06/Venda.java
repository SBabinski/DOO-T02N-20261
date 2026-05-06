import java.time.LocalDate;

class Venda{
    int quantidade;
    double preco;
    double desconto;
    double total;
    LocalDate data;

    public Venda(int quantidade, double preco, double desconto, double total, LocalDate data){
        this.quantidade = quantidade;
        this.preco = preco;
        this.desconto = desconto;
        this.total = total;
        this.data = data;
    }
}