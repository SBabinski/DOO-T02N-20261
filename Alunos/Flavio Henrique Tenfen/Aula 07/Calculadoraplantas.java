import java.util.Date;
import java.util.Scanner;
 
public class Calculadoraplantas {
 
    static class Endereco {
        String estado;
        String cidade;
        String bairro;
        String rua;
        String numero;
        String complemento;
 
        public Endereco(String estado, String cidade, String bairro, String rua, String numero, String complemento) {
            this.estado = estado;
            this.cidade = cidade;
            this.bairro = bairro;
            this.rua = rua;
            this.numero = numero;
            this.complemento = complemento;
        }
 
        public void apresentarLogradouro() {
            System.out.println("Endereço: " + rua + ", Nº " + numero + " - " + bairro + ", " + cidade + "/" + estado
                    + (complemento != null && !complemento.isEmpty() ? " (" + complemento + ")" : ""));
        }
    }
 
    static abstract class Pessoa {
        String nome;
        int idade;
        Endereco endereco;
 
        public void apresentarSe() {
            System.out.println("Nome: " + nome);
            System.out.println("Idade: " + idade);
            if (endereco != null) endereco.apresentarLogradouro();
        }
    }
 
    static class Vendedor extends Pessoa {
        String loja;
        double salarioBase;
        double[] salarioRecebido = {1500, 1600, 1700};
 
        @Override
        public void apresentarSe() {
            System.out.println("\n--- Vendedor ---");
            super.apresentarSe();
            System.out.println("Loja: " + loja);
            System.out.println("Salário Base: R$ " + salarioBase);
            System.out.printf("Média Salarial: R$ %.2f%n", calcularMedia());
            System.out.printf("Bônus: R$ %.2f%n", calcularBonus());
            System.out.printf("Salários Recebidos: R$ %.2f | R$ %.2f | R$ %.2f%n",
                    salarioRecebido[0], salarioRecebido[1], salarioRecebido[2]);
            System.out.println("----------------------------");
        }
 
        public double calcularMedia() {
            double soma = 0;
            for (double s : salarioRecebido) soma += s;
            return soma / salarioRecebido.length;
        }
 
        public double calcularBonus() {
            return salarioBase * 0.2;
        }
    }
 
    
    static class Gerente extends Pessoa {
        String loja;
        double salarioBase;
        double[] salarioRecebido = {4500, 4800, 5000};
 
        @Override
        public void apresentarSe() {
            System.out.println("\n--- Gerente ---");
            super.apresentarSe();
            System.out.println("Loja: " + loja);
            System.out.println("Salário Base: R$ " + salarioBase);
            System.out.printf("Média Salarial: R$ %.2f%n", calcularMedia());
            System.out.printf("Bônus: R$ %.2f%n", calcularBonus());
            System.out.printf("Salários Recebidos: R$ %.2f | R$ %.2f | R$ %.2f%n",
                    salarioRecebido[0], salarioRecebido[1], salarioRecebido[2]);
            System.out.println("----------------------------");
        }
 
        public double calcularMedia() {
            double soma = 0;
            for (double s : salarioRecebido) soma += s;
            return soma / salarioRecebido.length;
        }
 
        public double calcularBonus() {
            return salarioBase * 0.35;
        }
    }
 
    static class Cliente extends Pessoa {
 
        @Override
        public void apresentarSe() {
            System.out.println("\n--- Cliente ---");
            super.apresentarSe();
            System.out.println("----------------------------");
        }
    }
 
    static class Item {
        int id;
        String nome;
        String tipo;
        double valor;
 
        public Item(int id, String nome, String tipo, double valor) {
            this.id = id;
            this.nome = nome;
            this.tipo = tipo;
            this.valor = valor;
        }
 
        public void gerarDescricao() {
            System.out.printf("Item [ID: %d] | Nome: %s | Tipo: %s | Valor: R$ %.2f%n", id, nome, tipo, valor);
        }
    }
 
    static class Pedido {
        int id;
        Date dataCriacao;
        Date dataPagamento;
        Date dataVencimentoReserva;
        Cliente cliente;
        Vendedor vendedor;
        Loja loja;
        Item[] itens;
        int totalItens;
 
        public Pedido(int id, Date dataCriacao, Date dataVencimentoReserva,
                      Cliente cliente, Vendedor vendedor, Loja loja, int maxItens) {
            this.id = id;
            this.dataCriacao = dataCriacao;
            this.dataVencimentoReserva = dataVencimentoReserva;
            this.cliente = cliente;
            this.vendedor = vendedor;
            this.loja = loja;
            this.itens = new Item[maxItens];
            this.totalItens = 0;
        }
 
        public void adicionarItem(Item item) {
            if (totalItens < itens.length) {
                itens[totalItens++] = item;
            } else {
                System.out.println("Limite de itens no pedido atingido.");
            }
        }
 
        public double calcularValorTotal() {
            double total = 0;
            for (int i = 0; i < totalItens; i++) total += itens[i].valor;
            return total;
        }
 
        public void gerarDescricaoVenda() {
            System.out.printf("Pedido [ID: %d] criado em: %s | Valor Total: R$ %.2f%n",
                    id, dataCriacao.toString(), calcularValorTotal());
        }
    }
 
    static class ProcessaPedido {
 
        public Pedido processar(int id, Date dataCriacao, Date dataVencimentoReserva,
                                Cliente cliente, Vendedor vendedor, Loja loja, Item[] itens) {
            Pedido pedido = new Pedido(id, dataCriacao, dataVencimentoReserva, cliente, vendedor, loja, itens.length);
            for (Item item : itens) pedido.adicionarItem(item);
 
            System.out.println("\nPedido criado com sucesso!");
            pedido.gerarDescricaoVenda();
            confirmarPagamento(pedido);
            return pedido;
        }
 
        private void confirmarPagamento(Pedido pedido) {
            Date agora = new Date();
            if (!agora.after(pedido.dataVencimentoReserva)) {
                pedido.dataPagamento = agora;
                System.out.println("Pagamento confirmado! Data: " + agora);
            } else {
                System.out.println("Reserva vencida! Não é possível confirmar o pagamento.");
            }
        }
 
        public static void testarConfirmarPagamento() {
            System.out.println("\n========== TESTE: confirmarPagamento ==========");
            ProcessaPedido pp = new ProcessaPedido();
 
            Date agora = new Date();
 
            Date vencimentoValido = new Date(agora.getTime() + 3_600_000L);
            Pedido pedidoValido = new Pedido(1, agora, vencimentoValido, null, null, null, 1);
            System.out.print("[Cenário 1 - Reserva válida] ");
            pp.confirmarPagamento(pedidoValido);
 
            Date vencimentoVencido = new Date(agora.getTime() - 3_600_000L);
            Pedido pedidoVencido = new Pedido(2, agora, vencimentoVencido, null, null, null, 1);
            System.out.print("[Cenário 2 - Reserva vencida] ");
            pp.confirmarPagamento(pedidoVencido);
 
            System.out.println("========== FIM DO TESTE ==========\n");
        }
    }
 
    static class Loja {
        String nomeFantasia;
        String razaoSocial;
        String cnpj;
        Endereco endereco;
 
        Vendedor[] vendedores = new Vendedor[10];
        Cliente[]  clientes   = new Cliente[10];
        Gerente[]  gerentes   = new Gerente[5];
 
        int totalVendedores = 0;
        int totalClientes   = 0;
        int totalGerentes   = 0;
 
        public void contarClientes()   { System.out.println("Total de clientes: "   + totalClientes); }
        public void contarVendedores() { System.out.println("Total de vendedores: " + totalVendedores); }
        public void contarGerentes()   { System.out.println("Total de gerentes: "   + totalGerentes); }
 
        public void apresentarSe() {
            System.out.println("\n=== LOJA ===");
            System.out.println("Loja: "         + nomeFantasia);
            System.out.println("Razão Social: " + razaoSocial);
            System.out.println("CNPJ: "         + cnpj);
            if (endereco != null) endereco.apresentarLogradouro();
        }
 
        public void adicionarCliente(Cliente c)  { clientes[totalClientes++]   = c; }
        public void adicionarVendedor(Vendedor v) { vendedores[totalVendedores++] = v; }
        public void adicionarGerente(Gerente g)   { gerentes[totalGerentes++]   = g; }
    }
 
    static int[]    quantidadesVendidas = new int[100];
    static double[] valoresVenda        = new double[100];
    static double[] descontosAplicados  = new double[100];
    static Date[]   datasVenda          = new Date[100];
    static int totalVendas = 0;
 
    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
 
        Scanner scanner = new Scanner(System.in);
 
        ProcessaPedido.testarConfirmarPagamento();
 
        Loja loja = new Loja();
        loja.nomeFantasia = "Plantas da Gabrielinha";
        loja.razaoSocial  = "Plantas da Gabrielinha LTDA";
        loja.cnpj         = "12.345.678/0001-99";
        loja.endereco     = new Endereco("PR", "Cascavel", "Centro", "Rua das Flores", "100", "");
 
        int opcao = 0;
 
        while (opcao != 12) {
 
            System.out.println("\n=== SISTEMA PLANTAS DA GABRIELINHA ===");
            System.out.println("[1]  Calcular Preço Total");
            System.out.println("[2]  Venda com Desconto + Registro");
            System.out.println("[3]  Calcular Troco");
            System.out.println("[4]  Ver Registro de Vendas");
            System.out.println("[5]  Buscar Vendas por Data");
            System.out.println("[6]  Cadastrar Cliente");
            System.out.println("[7]  Cadastrar Vendedor");
            System.out.println("[8]  Dados da Loja");
            System.out.println("[9]  Ver Clientes");
            System.out.println("[10] Ver Vendedores");
            System.out.println("[11] Criar Pedido (demonstração)");
            System.out.println("[12] Sair");
 
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
 
            switch (opcao) {
 
                case 1:
                    System.out.print("Quantidade: ");
                    int q = scanner.nextInt();
                    System.out.print("Preço unitário: ");
                    double p = scanner.nextDouble();
                    System.out.printf("Total: R$ %.2f%n", q * p);
                    break;
 
                case 2:
                    System.out.print("Quantidade: ");
                    int qtd = scanner.nextInt();
                    System.out.print("Preço unitário: ");
                    double preco = scanner.nextDouble();
 
                    double total = qtd * preco;
                    double desconto = 0;
 
                    if (qtd > 10) {
                        desconto = total * 0.05;
                        total -= desconto;
                        System.out.println("Desconto de 5% aplicado!");
                    }
 
                    System.out.printf("Total: R$ %.2f%n", total);
 
                    Date dataVenda = new Date();
                    quantidadesVendidas[totalVendas] = qtd;
                    valoresVenda[totalVendas]        = total;
                    descontosAplicados[totalVendas]  = desconto;
                    datasVenda[totalVendas]          = dataVenda;
                    totalVendas++;
 
                    System.out.println("Venda registrada em: " + dataVenda);
                    break;
 
                case 3:
                    System.out.print("Valor recebido: ");
                    double recebido = scanner.nextDouble();
                    System.out.print("Valor da compra: ");
                    double compra = scanner.nextDouble();
                    double troco = recebido - compra;
 
                    if (troco < 0)
                        System.out.printf("Faltam R$ %.2f%n", Math.abs(troco));
                    else
                        System.out.printf("Troco: R$ %.2f%n", troco);
                    break;
 
                case 4:
                    if (totalVendas == 0) { System.out.println("Nenhuma venda registrada."); break; }
                    for (int i = 0; i < totalVendas; i++) {
                        System.out.printf("%s | Qtd: %d | R$ %.2f | Desc: R$ %.2f%n",
                                datasVenda[i], quantidadesVendidas[i], valoresVenda[i], descontosAplicados[i]);
                    }
                    break;
 
                case 5:
                    System.out.print("Mês (1-12): ");
                    int mes = scanner.nextInt();
                    System.out.print("Dia: ");
                    int dia = scanner.nextInt();
 
                    double totalDia = 0;
                    int count = 0;
 
                    for (int i = 0; i < totalVendas; i++) {
                        if ((datasVenda[i].getMonth() + 1) == mes && datasVenda[i].getDate() == dia) {
                            count++;
                            totalDia += valoresVenda[i];
                            System.out.printf("Venda: R$ %.2f%n", valoresVenda[i]);
                        }
                    }
 
                    System.out.println("Total de vendas: " + count);
                    System.out.printf("Total arrecadado: R$ %.2f%n", totalDia);
                    break;
 
                case 6:
                    Cliente c = new Cliente();
                    scanner.nextLine();
                    System.out.print("Nome: ");        c.nome  = scanner.nextLine();
                    System.out.print("Idade: ");       c.idade = scanner.nextInt(); scanner.nextLine();
                    System.out.print("Estado: ");      String estC  = scanner.nextLine();
                    System.out.print("Cidade: ");      String cidC  = scanner.nextLine();
                    System.out.print("Bairro: ");      String baiC  = scanner.nextLine();
                    System.out.print("Rua: ");         String ruaC  = scanner.nextLine();
                    System.out.print("Número: ");      String numC  = scanner.nextLine();
                    System.out.print("Complemento: "); String compC = scanner.nextLine();
                    c.endereco = new Endereco(estC, cidC, baiC, ruaC, numC, compC);
                    loja.adicionarCliente(c);
                    System.out.println("Cliente cadastrado!");
                    break;
 
                case 7:
                    Vendedor v = new Vendedor();
                    scanner.nextLine();
                    System.out.print("Nome: ");        v.nome  = scanner.nextLine();
                    System.out.print("Idade: ");       v.idade = scanner.nextInt(); scanner.nextLine();
                    System.out.print("Estado: ");      String estV  = scanner.nextLine();
                    System.out.print("Cidade: ");      String cidV  = scanner.nextLine();
                    System.out.print("Bairro: ");      String baiV  = scanner.nextLine();
                    System.out.print("Rua: ");         String ruaV  = scanner.nextLine();
                    System.out.print("Número: ");      String numV  = scanner.nextLine();
                    System.out.print("Complemento: "); String compV = scanner.nextLine();
                    v.endereco = new Endereco(estV, cidV, baiV, ruaV, numV, compV);
                    System.out.print("Salário Base: "); v.salarioBase = scanner.nextDouble();
                    v.loja = loja.nomeFantasia;
                    loja.adicionarVendedor(v);
                    System.out.println("Vendedor cadastrado!");
                    System.out.printf("Média salarial: R$ %.2f | Bônus: R$ %.2f%n", v.calcularMedia(), v.calcularBonus());
                    break;
 
                case 8:
                    loja.apresentarSe();
                    loja.contarClientes();
                    loja.contarVendedores();
                    loja.contarGerentes();
                    break;
 
                case 9:
                    if (loja.totalClientes == 0) { System.out.println("Nenhum cliente cadastrado."); break; }
                    System.out.println("\n=== CLIENTES ===");
                    for (int i = 0; i < loja.totalClientes; i++) loja.clientes[i].apresentarSe();
                    break;
 
                case 10:
                    if (loja.totalVendedores == 0) { System.out.println("Nenhum vendedor cadastrado."); break; }
                    System.out.println("\n=== VENDEDORES ===");
                    for (int i = 0; i < loja.totalVendedores; i++) loja.vendedores[i].apresentarSe();
                    break;
 
                case 11:
                    System.out.println("\n=== CRIANDO PEDIDO (DEMONSTRAÇÃO) ===");
 
                    Cliente clienteFake = new Cliente();
                    clienteFake.nome    = "Ana Lima";
                    clienteFake.idade   = 30;
                    clienteFake.endereco = new Endereco("PR", "Cascavel", "Universitário", "Av. Brasil", "500", "Apto 10");
 
                    Vendedor vendedorFake = new Vendedor();
                    vendedorFake.nome        = "Carlos Silva";
                    vendedorFake.loja        = loja.nomeFantasia;
                    vendedorFake.salarioBase = 2000;
 
                    Item[] itensFake = {
                        new Item(1, "Samambaia",     "Planta",    45.90),
                        new Item(2, "Vaso Cerâmica", "Acessório", 30.00),
                        new Item(3, "Substrato",     "Insumo",    15.50)
                    };
 
                    Date agora      = new Date();
                    Date vencimento = new Date(agora.getTime() + 7_200_000L);
 
                    ProcessaPedido processador = new ProcessaPedido();
                    Pedido pedido = processador.processar(1, agora, vencimento, clienteFake, vendedorFake, loja, itensFake);
 
                    System.out.println("\nItens do pedido:");
                    for (int i = 0; i < pedido.totalItens; i++) pedido.itens[i].gerarDescricao();
                    break;
 
                case 12:
                    System.out.println("Encerrando sistema. Até logo!");
                    break;
 
                default:
                    System.out.println("Opção inválida!");
            }
        }
 
        scanner.close();
    }
}