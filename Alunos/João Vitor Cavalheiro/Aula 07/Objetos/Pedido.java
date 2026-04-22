import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    int id;
    LocalDate dataCriacao;
    LocalDate dataPagamento;
    LocalDate dataVencimentoReserva;
    Vendedor vendedor;
    Cliente cliente;
    Loja loja;
    DateTimeFormatter formatoBR = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    List <Item> itens = new ArrayList<>();

    public Pedido(Cliente cliente, LocalDate dataCriacao, LocalDate dataPagamento, LocalDate dataVencimentoReserva, int id, Loja loja, Vendedor vendedor) {
        this.cliente = cliente;
        this.dataCriacao = dataCriacao;
        this.dataPagamento = dataPagamento;
        this.dataVencimentoReserva = dataVencimentoReserva;
        this.id = id;
        this.loja = loja;
        this.vendedor = vendedor;
    }

        public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public LocalDate getDataVencimentoReserva() {
        return dataVencimentoReserva;
    }

    public void setDataVencimentoReserva(LocalDate dataVencimentoReserva) {
        this.dataVencimentoReserva = dataVencimentoReserva;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }

    public void AddItem(Item item){
        itens.add(item);
    }

    public double calcularValorTotal(){
        double total=0;
        for (int i=0;i<itens.size();i++) {
            double parcial = itens.get(i).valor;    
            total = total + parcial;
        }
        return total;
    }

    public void gerarDescricaoVenda(){
        System.out.println("=======================");
        System.out.println("----------Pedido----------");
        System.out.println("A data de criação des pedito foi dai: "+dataCriacao.format(formatoBR));
        System.out.println("E seu valor total é de R$"+calcularValorTotal());
        System.out.println("=======================");
    }

}
