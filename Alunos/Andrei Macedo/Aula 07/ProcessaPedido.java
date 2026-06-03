import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProcessaPedido {
    
    private final List<Venda> historico = new ArrayList<>();
    private final List<Pedido> pedidos = new ArrayList<>();
    private static final double percentual_desconto = 0.05;
    private static Loja loja;

    public void realizar_venda(int quantidade_venda, double valor_unitario, double pagamento, int ano, int mes, int dia){

        Venda venda = new Venda();
        apresentarse();
        
        if (!validar_quantidade(quantidade_venda, valor_unitario)){
            System.err.println("Erro! Saldo nulo ou insuficiente. O histórico não foi salvo!");
            return;
        }

        double total_venda = quantidade_venda * valor_unitario;
        total_venda = VerificarDesconto(quantidade_venda, total_venda, venda);

        if(!verificar_troco(pagamento, total_venda)) {
            System.out.println("Saldo insuficiente para o pagamento!");
            return;
        }

        if(!verificar_data(ano, mes, dia)){
            System.err.println("O histórico não foi salvo!");
            return;
        } else {
            System.out.println("Histórico salvo com sucesso!");
            LocalDate day = LocalDate.of(ano, mes, dia);
            venda.setData(day);
        }
        
        venda.setTroco(pagamento - total_venda);
        System.out.printf("Compra aprovada! Troco: R$ %.2f\n", venda.getTroco());
        venda.setQuantidade_venda(quantidade_venda);
        venda.setValor_unitario(valor_unitario);
        venda.setValor_total(total_venda);

        salvar_historico(venda);
    }

    public boolean verificar_troco(double pagamento, double total) {
        if (pagamento < total) {
            return false;
        } else {
            return true;
        }
    }

    public double VerificarDesconto(int quantidade, double total, Venda venda) {
        if (quantidade > 10) {
            System.out.println(" === DESCONTO APLICADO! === ");
            double desconto = total * percentual_desconto;
            venda.setDesconto(desconto);
            return total - desconto;
        } else {
            return total;
        }
    }

    public boolean verificar_data(int ano, int mes, int dia){
         try {
            LocalDate.of(ano, mes, dia);
            return true;
        } catch (DateTimeException e) {
            System.err.println("Erro, data inválida.");
            return false;
        } 
    }

    public boolean validar_quantidade(int quantidade_venda, double valor_unitario){
        if (quantidade_venda <= 0 || valor_unitario <= 0){
            return false;
        } else {
            return true;
        }
    }

    public void salvar_historico(Venda venda){
        historico.add(venda);
        System.out.println("Compra salva com sucesso!");
        System.out.println(venda.getValor_unitario());
    }

    public void mostrar_historico(){
        apresentarse();

        for (Pedido p : pedidos) {
            p.gerarDescricaoVenda();
        }

        if (!verificarLista()){
            System.out.println("Não há nada para mostrar aqui por enquanto.");
            return;
        }
        for (Venda v : historico) {
            System.out.println(v);
        }
    }

    public boolean verificarLista(){
        if (historico.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public void ProcessarData(int ano, int mes, int dia) {
        if (!verificar_data(ano, mes, dia)) {
            return;
        }
        LocalDate day = LocalDate.of(ano, mes, dia);
        HistoricoData(day);
    }

    public void HistoricoData(LocalDate day){
        apresentarse();
        for (Venda venda : historico) {
            if (venda.getData().isEqual(day)) {
                System.out.println(venda);
            }
        }
        System.out.println("Fim da exibição.");
    }

    public void CadastrarLoja(String nome, String razao, String cnpj, String estado, String cidade, String bairro,
    int numero, String complemento){
        Endereco endereco = new Endereco(estado, cidade, bairro, numero, complemento);
        loja = new Loja(nome, razao, cnpj, endereco);
    }

    public void apresentarse() {
        System.out.println("Loja: " + loja.getNome());
        System.out.println("CNPJ: " + loja.getCnpj());
        System.out.println("Endereço:" + loja.getEndereco());
    }

    public void CadastrarFunc(String nome, int idade, double salario, String estado, String cidade, String bairro,
    int numero, String complemento) {
        Endereco endereco = new Endereco(estado, cidade, bairro, numero, complemento);
        Vendedor funcionario = new Vendedor(nome, idade, salario, endereco);
        loja.salvar_vendedor(funcionario);
    }

    public void CadastrarCliente(String nome, int idade, String cpf, String estado, String cidade, String bairro,
    int numero, String complemento ) {
        Endereco enderecoCliente = new Endereco(estado, cidade, bairro, numero, complemento);
        Cliente cliente = new Cliente(nome, idade, cpf, enderecoCliente);
        loja.salvar_cliente(cliente);
    }

    public void chamar_listas() {
        apresentarse();
        loja.apresentarse();
    }

    public void processar(int id, int diaCriacao, int mesCriacao, int anoCriacao, int diaPagamento, int mesPagamento, int anoPagamento, 
    int diaVencimento, int mesVencimento, int anoVencimento, int escolhaVendedor, int escolhaCliente, int pedidoEscolhido, int qntd) {
        Pedido pedido = new Pedido();
        if(!verificar_data(anoCriacao, mesCriacao, diaCriacao)){
            return;
        }

        if(!verificar_data(anoPagamento, mesPagamento, diaPagamento)){
            return;
        }

        if(!verificar_data(anoVencimento, mesVencimento, diaVencimento)){
            return;
        }

        LocalDate dayCriacao = LocalDate.of(anoCriacao, mesCriacao, diaCriacao);
        LocalDate dayPagamento = LocalDate.of(anoPagamento, mesPagamento, diaPagamento);
        LocalDate dayVencimento = LocalDate.of(anoVencimento, mesVencimento, diaVencimento);

        if(!confirmarPagamento(dayPagamento, dayVencimento)){
            return;
        }

        double total = pedido.calcularValorTotal(pedidoEscolhido, qntd);

        pedido.setId(id);
        pedido.setDataCriacao(dayCriacao);
        pedido.setDataPagamento(dayPagamento);
        pedido.setDataVencimentoReserva(dayVencimento);
        Cliente clienteEncontrado = loja.buscarCliente(escolhaCliente);
        pedido.setCliente(clienteEncontrado);
        Vendedor vendedorEncontrado = loja.buscarVendedor(escolhaVendedor);
        pedido.setVendedor(vendedorEncontrado);
        pedido.setValor(total);
        
        pedidos.add(pedido);
    }

    private boolean confirmarPagamento(LocalDate dataPagamento, LocalDate dataVencimento){
        if (dataPagamento.isAfter(dataVencimento)){
            System.out.println(("Erro: A reserva está vencida. Pagamento não confirmado"));
            return false;
        } else {
            System.out.println("Pagamento confirmado");
            return true;
        }
    }
}
