package Objetos;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Pedido {
    
    private static int contador = 1;
    private final int id;
    private LocalDate dataCriacao;
    private LocalDate dataPagamento;
    private LocalDate dataVencimentoReserva;
    private Cliente cliente;
    private Vendedor vendedor;
    private Loja loja;
    private ArrayList<Item> itens; 

    public Pedido(){
        this.id = contador++;
        this.itens = new ArrayList<>();
    }

    public Pedido(LocalDate dataCriacao, LocalDate dataPagamento,
                  LocalDate dataVencimentoReserva, Cliente cliente, Vendedor vendedor,
                  Loja loja, ArrayList<Item> itens){
        
        this.id = contador++;
        setDataCriacao(dataCriacao);
        setDataPagamento(dataPagamento);
        setDataVencimentoReserva(dataVencimentoReserva);
        setCliente(cliente);
        setVendedor(vendedor);
        setLoja(loja);
        if (itens != null) {
            this.itens = itens;
        } else {
            this.itens = new ArrayList<>();
        }
    }

    public int getId(){
        return id;
    }

    public LocalDate getDataCriacao(){
        return dataCriacao;
    }

    public LocalDate getDataPagamento(){
        return dataPagamento;
    }

    public LocalDate getDataVencimentoReserva(){
        return dataVencimentoReserva;
    }

    public String getCliente(){
        return cliente.getNome();
    }

    public String getVendedor(){
        return vendedor.getNome();
    }

    public String getLoja(){
        return loja.getNomeFantasia();
    }

    public ArrayList<Item> getItens(){
        return itens;
    }

    public void setDataCriacao(LocalDate dataCriacao){
        this.dataCriacao = dataCriacao;
    }

    public void setDataPagamento(LocalDate dataPagamento){
        this.dataPagamento = dataPagamento;
    }

    public void setDataVencimentoReserva(LocalDate dataVencimentoReserva){
        this.dataVencimentoReserva = dataVencimentoReserva;
    }

    public void setCliente(Cliente cliente){
        if(cliente != null) {
            this.cliente = cliente;
        }
    }

    public void setVendedor(Vendedor vendedor){
        if(vendedor != null) {
            this.vendedor = vendedor;
        }
    }

    public void setLoja(Loja loja){
        if(loja != null) {
            this.loja = loja;
        }
    }

    public void adicionarItem(Item i) {
        if(!this.itens.contains(i)) {
            this.itens.add(i);
        }
    }

    public double calcularValorTotal(){
        double valorTotal = 0;
        for(int i = 0; i < itens.size(); i++){
            valorTotal += itens.get(i).getValor();
        }
        return valorTotal;
    }

    public void gerarDescricaoVenda(){
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String dataCriacaoFormatada = dataCriacao.format(formatador);
        System.out.printf("ID %d | %s - R$%.2f\n", id, dataCriacaoFormatada, calcularValorTotal());
    }

    @Override
    public String toString(){
        return "Pedido [" +
            "id=" + id +
            ", dataCriacao=" + dataCriacao +
            ", dataPagamento=" + dataPagamento +
            ", dataVencimento=" + dataVencimentoReserva +
            ", cliente=" + (cliente != null ? cliente.getNome() : "null") +
            ", vendedor=" + (vendedor != null ? vendedor.getNome() : "null") +
            ", loja=" + (loja != null ? loja.getNomeFantasia() : "null") +
            ", total=" + calcularValorTotal() +
            "]";
    }
}
