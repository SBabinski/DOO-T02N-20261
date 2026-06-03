import java.time.LocalDate;

public class ProcessaPedido {

    public Venda processar(int quantidade, double precoUnitario) {

        double total = quantidade * precoUnitario;

        double desconto = 0;

        if (quantidade > 10) {
            desconto = total * 0.05;
            total -= desconto;
        }

        Venda venda = new Venda(
                quantidade,
                total,
                LocalDate.now()
        );

        return venda;
    }
}