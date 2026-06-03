import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class ProcessaPedido {
    
    static Scanner scan = new Scanner(System.in);
    public static Pedido processar(String id, LocalDate dataCriacao, Cliente cliente, Vendedor vendedor, Loja loja, ArrayList<Item> itens){
        LocalDate dataVencimento = dataCriacao.plusDays(3);
        Pedido pedido = new Pedido(id, dataCriacao, null, dataVencimento, cliente, vendedor, loja, itens);
        return pedido;
    }
    private static void confirmarPagamento(Pedido pedido){
        LocalDate dataHoje = LocalDate.now();
        if(dataHoje.isAfter(pedido.getDataVencimentoReserva())){
            System.out.println("Data atual superior a data de vencimento, pedido nao concluido");
        }
        else{
            System.out.println("PEDIDO FINALIZADO COM SUCESSO");
            pedido.setDataPagamento(dataHoje);
            pedido.gerarDescricaoVenda();
        }
    }
    public static void processarPagamento(Pedido pedido) {
        confirmarPagamento(pedido);
    }
}
