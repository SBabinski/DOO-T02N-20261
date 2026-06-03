
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner scan = new Scanner(System.in);
    static int opcao = 1000;
    static ArrayList <Vendas> vendas = new ArrayList<>();
    static double valor_de_venda_total = 0;
    static ArrayList <Item> itensLoja = new ArrayList<>();
    static ArrayList<Pedido> pedidosTotais = new ArrayList<>();
    static ArrayList<Lojas> lojas = new ArrayList<>();
    
    public static void main(String[] args){
        Lojas loja1 = new Lojas("Lojinha", "LOJINHARAZAOSOCIAL", "12345676543211", "Cafelandia", "Bairro nobre", "Rua rapida");
        loja1.apresentarse();
        System.out.println(loja1.getEndereco());
        loja1.getEndereco().apresentarLogradouro();

        Lojas loja2 = new Lojas("Lojão", "LOJÃORAZAOSOCIAL", "19283740594837", "Cascavel", "Bairro pobre", "Rua devagar");
        loja2.apresentarse();
        System.out.println(loja2.getEndereco());
        loja2.getEndereco().apresentarLogradouro();

        Vendedor vend1 = new Vendedor("Leo", 18, loja2, 1200.0, "Cascavel", "Bairro pobre 2", "rua devagar 2");
        vend1.receberSalario(1200);
        vend1.receberSalario(1250);
        vend1.receberSalario(1300);
        vend1.apresentarse();
        System.out.println(vend1.calcularMediaSalarial());
        System.out.println(vend1.calcularBonusSalarial());
        loja2.vendedores.add(vend1);
        

        Vendedor vend2 = new Vendedor("Patrick", 22, loja1, 1500.0, "cidade jghfi", "bairro nbfd", "rua popo");
        vend2.receberSalario(1500);
        vend2.receberSalario(1550);
        vend2.receberSalario(1600);
        vend2.apresentarse();
        System.out.println(vend2.calcularMediaSalarial());
        System.out.println(vend2.calcularBonusSalarial());
        loja1.vendedores.add(vend2);

        Vendedor vend3 = new Vendedor("Pietro", 36, loja1, 1600.0, "Cidade abcd", "bairro efg", "rua hij");
        loja1.vendedores.add(vend3);

        Vendedor vend4 = new Vendedor("Felipe", 58, loja2, 1800.0, "cidade pon", "bairro cavalo", "rua pony");
        loja2.vendedores.add(vend4);
        
        Clientes cliente10 = new Clientes("Eduardo", 30, "NY", "bairro country", "rua da lapa");

        Clientes cliente20 = new Clientes("Jemer", 45, "Fortaleza", "bairro cinema", "rua abelha");

        Clientes cliente30 = new Clientes("maria", 60, "cidade cidade", "bairro bairro", "rua rua");

        Clientes cliente40 = new Clientes("joao", 70, "cidade cida", "bairro bai", "rua ru");

        loja1.clientes.add(cliente20);
        loja2.clientes.add(cliente10);
        loja2.clientes.add(cliente30);
        loja1.clientes.add(cliente40);
        
        lojas.add(loja1);
        lojas.add(loja2);

        Main.dadosLojas.add(loja1);
        Main.dadosLojas.add(loja2);

        Gerente gerente1 = new Gerente("Gerentinho", 50, loja2, 3500.0, "Cidade grande", "bairro grande", "rua grande");

        gerente1.receberSalario(4000);
        gerente1.receberSalario(4250);
        gerente1.receberSalario(4500);
        gerente1.apresentarse();
        System.out.println(gerente1.calcularMediaSalarial());
        System.out.println(gerente1.calcularBonusSalarial());
        loja2.setGerente(gerente1);

        Gerente gerente2 = new Gerente("Gerentão", 70, loja1, 5000.0, "cidade pequena", "bairro pequeno", "rua pequena");
        gerente2.receberSalario(5000);
        gerente2.receberSalario(5250);
        gerente2.receberSalario(5500);
        gerente2.apresentarse();
        System.out.println(gerente2.calcularMediaSalarial());
        System.out.println(gerente2.calcularBonusSalarial());
        loja1.setGerente(gerente2);

        Item item1 = new Item(1, "Rosácea", "flor rosa", 10.0);
        Item item2 = new Item(10, "Girassol", "flor amarela", 25.0);
        Item item3 = new Item(20, "Flor grande", "grande flor", 50.0);

        System.out.println(item1.gerarDescicao());
        System.out.println(item2.gerarDescicao());
        itensLoja.add(item1);
        itensLoja.add(item2);
        itensLoja.add(item3);
        
        Pedido pedido5 = ProcessaPedido.processar(5, cliente10, vend2, loja1, "12/12/2026");
        pedido5.setDataVencimentoReserva("12/12/2026");
        pedido5.adicionarItem(item1);
        pedido5.adicionarItem(item2);
        pedido5.getItens();
        pedido5.gerarDescricaoVenda();
        System.out.println(pedido5.getDataVencimentoReservaString());
        ProcessaPedido.pagamentoConfirmado(pedido5);
        pedido5.setDataVencimentoReserva("14/04/2026");
        System.out.println(pedido5.getDataVencimentoReservaString());
        ProcessaPedido.pagamentoConfirmado(pedido5);

        menu();

    }
    
    public static void menu(){
        //vendas não está mais sendo utilizada, foi trocado pelos pedidos, e alocar o dia da venda é feita automaticamente com o dia de criação da venda (que é sempre o dia atual)
        System.out.println("********");
        System.out.println("* MENU *");
        System.out.println("********");
        System.out.println("1 - Criar pedido");
        System.out.println("2 - Cálculo de troco");
        System.out.println("3 - Consultar vendas (outdated)");
        System.out.println("4 - Alocar vendas totais (outdated)");
        System.out.println("5 - Buscar vendas do dia");
        System.out.println("6 - Listar dados (loja, vendedores e clientes)");
        System.out.println("7 - Listar pedidos");
        System.out.println("8 - Sair");
        
        do{
            System.out.println("Digite uma opção válida");
            opcao = scan.nextInt();
            scan.nextLine();
            if (opcao == 1){
                criarPedido();
            }else if (opcao == 2){
                CalculoDeTroco();
            }else if (opcao == 3){
                ConsultarVendas();
            }else if (opcao == 4){
                Date.EscolherDia(valor_de_venda_total);
            }else if (opcao == 5){
                Date.BuscarVendaDia();
            }else if (opcao == 6){
                listarDados();
            }else if (opcao == 7){
                listarPedidos();
            }
            else if (opcao == 8){
                Sair();
            }
        }while (opcao != 8);

    }

    public static void criarPedido(){
        int id = (pedidosTotais.size()+1);

        System.out.println("Escolha a loja:");
        Lojas loja = null;
        for (int i = 0; i < lojas.size(); i++){
            System.out.println((i+1)+" - " + lojas.get(i).getNomeFantasia());
        }
        while (loja == null) { 
            int escolhaLoja = scan.nextInt();
            scan.nextLine();
            for (int i = 0; i < lojas.size(); i++){
                if ((escolhaLoja-1) == i){
                    loja = lojas.get(i);
                }
            }
            if (loja == null){
                System.out.println("Digite uma opção válida: ");
            }
        }
        
        
        

        System.out.println("Escolha o vendedor da loja: ");
        for (int i = 0; i < loja.vendedores.size(); i++){
            System.out.println((i+1)+" - "+loja.vendedores.get(i).getNome());
        }
        Vendedor vendedor = null;
        while (vendedor == null) { 
            int escolhaVendedor = scan.nextInt();
            scan.nextLine();
            for (int i = 0; i < loja.vendedores.size(); i++){
                if ((escolhaVendedor-1) == i){
                    vendedor = loja.vendedores.get(i);
                }
            }
            if (vendedor == null){
                System.out.println("Digite uma opção válida: ");
            }
        }

        System.out.println("Escolha o cliente da loja: ");
        for (int i = 0; i < loja.clientes.size(); i++){
            System.out.println((i+1)+" - "+loja.clientes.get(i).getNome());
        }
        Clientes cliente = null;
        while (cliente == null) { 
            int escolhaCliente = scan.nextInt();
            scan.nextLine();
            for (int i = 0; i < loja.clientes.size(); i++){
                if ((escolhaCliente-1) == i){
                    cliente = loja.clientes.get(i);
                }
            }
            if (cliente == null){
                System.out.println("Digite uma opção válida: ");
            }
        }

        System.out.println("Escolha a data de vencimento: (dd/mm/aaaa)");
        String escolhaData = scan.nextLine();

        Pedido pedido = ProcessaPedido.processar(id, cliente, vendedor, loja, escolhaData);

        System.out.println("Itens disponíveis:");
        for (int i = 0; i < itensLoja.size(); i++){
            System.out.println(itensLoja.get(i).gerarDescicao());
        }
        while (true) { 
            System.out.println("1 - Adicionar item à compra\n2 - Ir para o pagamento");
            int escolha = scan.nextInt();
            scan.nextLine();
            if (escolha == 1){
                adicionarItemACompra(pedido);
            }else if (escolha == 2){
                irPagamento(pedido);
                break;
            }
        }
        
    }

    public static void adicionarItemACompra(Pedido pedido){
        System.out.println("Digite o ID do item:");
        while (true){
            boolean ifExecutado = true;
            int id = scan.nextInt();
            scan.nextLine();    
            for (int i = 0; i<itensLoja.size(); i++){
                if (id == itensLoja.get(i).getId()){
                    ifExecutado = false;
                    if (pedido.itens.contains(itensLoja.get(i))){
                        System.out.println("Digite a quantidade para adicionar:");
                        int quantidade = scan.nextInt();
                        scan.nextLine();
                        pedido.quantidadeDosItens.merge(itensLoja.get(i), quantidade, Integer::sum);
                        System.out.println("Quantidade adicionada com sucesso");
                        break;
                    }else{
                        pedido.adicionarItem(itensLoja.get(i));
                        System.out.println("Digite a quantidade para adicionar:");
                        int quantidade = scan.nextInt();
                        scan.nextLine();
                        pedido.quantidadeDosItens.merge(itensLoja.get(i), quantidade, Integer::sum);
                        System.out.println("Pedido adicionado com sucesso");
                        break;
                    }
                    
                }
            }

        if (ifExecutado){
            System.out.println("Digite um ID válido: ");
            }
        if (ifExecutado == false){
            break;
        }
        }
    }

    public static void irPagamento(Pedido pedido){
        double totalCompra = 0;
        int quantidadeTotal = 0;
        for (int i = 0; i < pedido.itens.size(); i++){
            Item item = pedido.itens.get(i);
            double valorDoItem = item.getValor(); 
            int quantidadeItem = pedido.quantidadeDosItens.get(item);
            quantidadeTotal += pedido.quantidadeDosItens.get(item);
            double totalItem = valorDoItem * quantidadeItem;
            totalCompra = totalCompra + totalItem;
        }
        if (quantidadeTotal > 10){
            totalCompra *= 0.95;
        }
        System.out.println("Total da compra: "+totalCompra);
        pedido.setDataPagamento();
        pedido.setTotalCompra(totalCompra);
        pedidosTotais.add(pedido);
        
        Date.valoresDiaEMes.merge(LocalDate.now(), totalCompra, Double::sum);
        VoltarMenu();
    }

    public static void CalculoPrecoTotal(){
        Vendas venda = new Vendas();
        System.out.println("Digite a quantidade da planta vendida:");
        int quantidade = scan.nextInt();
        scan.nextLine();
        venda.setQuantidade(quantidade);
        System.out.println("Digite o preço unitário da planta:");
        double preco = scan.nextDouble();
        scan.nextLine();
        double preco_total = preco * quantidade;
        venda.setPreco(preco_total);
        if (quantidade > 10){
            double valorDesconto = preco_total * 0.05;
            preco_total -= valorDesconto;
            venda.setvalorDescontoRecebido(valorDesconto);
        }
        System.out.printf("Preço Total: %.2f\n", preco_total);
        System.out.println("Venda cadastrada no sistema\n");
        vendas.add(venda);
        valor_de_venda_total += preco_total;
        VoltarMenu();
    }
    public static void CalculoDeTroco(){
        System.out.println("Valor recebido pelo cliente:");
        double valor = scan.nextDouble();
        scan.nextLine();
        System.out.println("Valor total da compra:");
        double total = scan.nextDouble();
        scan.nextLine();
        if (valor-total < 0){
            System.out.println("Valor insuficiênte, tente novamente");
            CalculoDeTroco();
        }else{
            System.out.printf("Troco a ser devolvido: %.2f\n", valor-total);
            VoltarMenu();
        }
    }

    public static void VoltarMenu(){
        System.out.println("Pressione enter para voltar ao menu");
        scan.nextLine();
        menu();
    }

    public static void ConsultarVendas(){
        System.out.println("Número da venda - quantidade - preço total - desconto recebido - preço descontado\n");
        for (int i = 0; i < vendas.size(); i++){
            System.out.println(i+1+" - "+vendas.get(i).getQuantidade()+" - "+vendas.get(i).getPreco()+" - "+ vendas.get(i).getvalorDescontoRecebido()+" - "+(vendas.get(i).getPreco()-vendas.get(i).getvalorDescontoRecebido())+"\n");
        }
        System.out.printf("Vendas totais(não alocadas): %.2f\n\n", valor_de_venda_total);
        VoltarMenu();
    }

    public static void Sair(){
        System.out.println("Finalizando o systema");
    }


    public static ArrayList<Lojas> dadosLojas = new ArrayList<>(); 

    public static void listarDados(){
        for (int i = 0; i < dadosLojas.size(); i++){
            dadosLojas.get(i).apresentarse();
            System.out.println("Gerente: " + dadosLojas.get(i).getGerente().getNome());
            for (int j = 0; j < dadosLojas.get(i).vendedores.size(); j++){
                System.out.printf(" -");
                dadosLojas.get(i).vendedores.get(j).apresentarse();
            }
            for (int k = 0; k < dadosLojas.get(i).clientes.size(); k++){
                System.out.printf(" -");
                dadosLojas.get(i).clientes.get(k).apresentarse();
            }
        }
        VoltarMenu();
    }

    public static void listarPedidos(){
        for (int i = 0; i < pedidosTotais.size(); i++){
            System.out.println(pedidosTotais.get(i).gerarDescricaoVenda()+" | Loja: "+pedidosTotais.get(i).getLoja().getNomeFantasia()+" | Vendedor: "+pedidosTotais.get(i).getVendedor().getNome()+" | Cliente: "+pedidosTotais.get(i).getCliente().getNome()+" | Data vencimento: "+pedidosTotais.get(i).getDataVencimentoReservaString()+" | Data pagamento: "+pedidosTotais.get(i).getDataPagamentoString());
        }
        if (pedidosTotais.isEmpty()){
            System.out.println("Nenhum pedido registrado");
        }
        VoltarMenu();
    }

}


