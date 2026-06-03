import java.util.Date;

public class ProcessaPedido {

    public Pedido processar(Cliente c, Vendedor v){

        Item i1 = new Item(1,"Planta","Ornamental",50);
        Item i2 = new Item(2,"Vaso","Decoracao",30);

        Item[] lista = {i1,i2};

        Pedido p = new Pedido(1,c,v,"My Plant",lista);

        if(confirmarPagamento(p)){
            p.dataPagamento = new Date();
            System.out.println("pagamento ok");
        }else{
            System.out.println("reserva vencida");
        }

        return p;
    }

    private boolean confirmarPagamento(Pedido p){
        Date agora = new Date();

        if(agora.before(p.dataVencimentoReserva)){
            return true;
        }else{
            return false;
        }
    }
}