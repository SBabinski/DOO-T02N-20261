
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Pedido {
    private int id;
    private LocalDate dataCriacao;
    private LocalDate dataPagamento;
    private LocalDate dataVencimentoReserva;
    private Clientes cliente;
    private Vendedor vendedor;
    private Lojas loja;
    public ArrayList<Item> itens = new ArrayList<>();
    public HashMap<Item, Integer> quantidadeDosItens = new HashMap<>();
    public Double totalCompra = 0.0;

    public Pedido(){

    }

    public Pedido(int id, Clientes cliente, Vendedor vendedor, Lojas loja, String dataVencimento) {
        setId(id);
        setCliente(cliente);
        setVendedor(vendedor);
        setLoja(loja);
        setDataCriacao();
        setDataVencimentoReserva(dataVencimento);
    }

    public int getId() {
        return id;
    }

    public final void setId(int id) {
        if (id > 0) {
            this.id = id;
        } else {
            System.out.println("Digite um id válido:");
            int novoId = Main.scan.nextInt();
            Main.scan.nextLine();
            setId(novoId);
        }
        
    }

    public String getDataCriacaoString() {
        String dataAtualString = Date.dataAtualString();
        return dataAtualString;
    }

    public LocalDate getDataCriacaoDate(){
        return dataCriacao;
    }

    public final void setDataCriacao() {
        this.dataCriacao = Date.dataAtualDate();
    }

    public String getDataPagamentoString() {
        return Date.dateToString(dataPagamento);
    }

    public LocalDate getDataPagamentoDate(){
        return dataPagamento;
    }

    public void setDataPagamento() {
        if (dataVencimentoReserva.isAfter(Date.dataAtualDate())){
            while(true){
                int escolha = 0;
                System.out.println("1 - Agendar pagamento\n2 - Pagar Agora");
                escolha = Main.scan.nextInt();
                Main.scan.nextLine();
                if (escolha == 1){
                    while(true){
                        System.out.println("Para qual data deseja agendar? (dd/mm/aaaa)");
                        String data = Main.scan.nextLine();
                        LocalDate dataCerta = Date.stringToDate(data);
                        if (dataCerta.isBefore(dataVencimentoReserva)){
                            this.dataPagamento = dataCerta;
                            break;
                        }else{
                            System.out.println("Data fora do prazo de validade, tente novamente");
                        }
                    }
                break; 
                }
                else if (escolha == 2){
                    this.dataPagamento = Date.dataAtualDate();
                    System.out.println("Pago agora");
                    break;
                }else {
                    System.out.println("Digite uma opcão válida:");
                }
            }
        } else {
            System.out.println("O pedido já está vencido.");
        }
       
    }

    public String getDataVencimentoReservaString(){
        return Date.dateToString(dataVencimentoReserva);
    }

    public LocalDate getDataVencimentoReservaDate() {
        return dataVencimentoReserva;
    }

    public void setDataVencimentoReserva(String dataVencimentoReservaString) {
        LocalDate dataVencimentoReservaDate = Date.stringToDate(dataVencimentoReservaString);
        this.dataVencimentoReserva = dataVencimentoReservaDate;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public final void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public final void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Lojas getLoja() {
        return loja;
    }

    public final void setLoja(Lojas loja) {
        this.loja = loja;
    }

    public void getItens() {
        for (int i = 0; i < itens.size(); i++) {
            System.out.print("Item: "+(i+1)+" | " + itens.get(i).gerarDescicao());
        }
    }


    public void adicionarItem(Item item) {
        this.itens.add(item);
    }


    public void setTotalCompra(double valor){
        totalCompra += valor;
    }

    public double getValorTotal(){
        return this.totalCompra;
    }

    public double calcularValorTotal() {
        double soma = 0;
        for (int i = 0; i < itens.size(); i++) {
            int quantidadeItem = quantidadeDosItens.getOrDefault(itens.get(i), 0);
            Double valorItem = itens.get(i).getValor();
            if (quantidadeItem > 10){
                valorItem *= 0.95;
            }
            soma = soma + (valorItem * quantidadeItem);
        }
        return soma;
    }

    public String gerarDescricaoVenda() {
        return String.format("Id do pedido: " + id + " | Criação: " + getDataCriacaoString() + " | Total: " + getValorTotal());
    }

    public Double getTotalCompra() {
        return totalCompra;
    }



    


    
}
