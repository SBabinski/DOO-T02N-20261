import java.util.ArrayList;
import java.util.Scanner;
import Objetos.*;

public class Index {

    static Scanner scan = new Scanner(System.in);
    static ArrayList<Cliente> clientes = new ArrayList<>();
    static ArrayList<Veiculo> veiculos = new ArrayList<>();
    static ArrayList<Locacao> locacoes = new ArrayList<>();

    public static void main(String[] args) {
        popularListas();
        menu();
    }

    public static void menu() {

        int opcao = -1;

        do {

            System.out.println("=================================");
            System.out.println("( 1 ) Cadastrar cliente");
            System.out.println("( 2 ) Cadastrar Veiculo");
            System.out.println("( 3 ) Cadastrar locacao");
            System.out.println("( 4 ) Cadastrar devolucao");
            System.out.println("( 5 ) Listar devolucoes pendentes");
            System.out.println("( 6 ) Demonstrar");
            System.out.println("( 0 ) Sair");
            System.out.println("=================================");
            System.out.printf("Selecione uma opcao: ");

            opcao = scan.nextInt();
            scan.nextLine();

            validaOpcao(opcao);

        } while (opcao != 0);

    }

    public static void validaOpcao(int opcao) {

        if (opcao == 1) {
            cadastrarCliente();
        } else if (opcao == 2) {
            cadastrarVeiculo();
        } else if (opcao == 3) {
            cadastrarLocacao();
        } else if (opcao == 4) {
            cadastrarDevolucao();
        } else if (opcao == 5) {
            listarDevolucoesPendentes();
        } else if (opcao == 6) {
            System.out.println("Demonstrando funcionamento...");
        } else if (opcao == 0) {
            System.out.println("Saindo...");
        } else {
            System.out.println("Opcao invalida...");
        }

    }

    public static void cadastrarCliente() {

        Cliente novo_cliente = new Cliente();

        System.out.println("=================================");
        System.out.printf("Informe o nome do cliente: ");
        novo_cliente.setNome(scan.nextLine());

        System.out.printf("Informe o CPF do cliente: ");
        novo_cliente.setCpf(scan.nextLine());

        System.out.printf("Informe a CNH do cliente: ");
        novo_cliente.setCnh(scan.nextLine());

        System.out.println("=================================");
        System.out.println("Cliente cadastrado com sucesso!");
        novo_cliente.infoCliente();

        clientes.add(novo_cliente);
    }

    public static void cadastrarVeiculo() {

        int opcao = -1;

        System.out.println("=================================");
        System.out.println("Escolha o tipo de veiculo");
        System.out.println("( 1 ) Carro");
        System.out.println("( 2 ) Moto");
        System.out.println("=================================");
        System.out.printf("Escolha uma opcao: ");
        opcao = scan.nextInt();
        scan.nextLine();

        if (opcao == 1) {
            Carro novo_carro = new Carro();

            novo_carro.setTipoVeiculo("Carro");

            System.out.println("=================================");
            System.out.printf("Informe a placa do veiculo: ");
            novo_carro.setPlaca(scan.nextLine());

            System.out.printf("Informe o valor da diaria: ");
            novo_carro.setValorDiaria(scan.nextDouble());
            scan.nextLine();

            System.out.println("O Veiculo possui ar-condicionado ?");
            System.out.println("( 1 ) Sim | ( 0 ) Nao");
            System.out.printf("Escolha: ");
            novo_carro.setPossuiAr(scan.nextInt());
            scan.nextLine();

            novo_carro.infoCarro();

            veiculos.add(novo_carro);
        }

        if (opcao == 2) {
            Moto nova_moto = new Moto();

            nova_moto.setTipoVeiculo("Moto");

            System.out.println("=================================");
            System.out.printf("Informe a placa do veiculo: ");
            nova_moto.setPlaca(scan.nextLine());

            System.out.printf("Informe o valor da diaria: ");
            nova_moto.setValorDiaria(scan.nextDouble());
            scan.nextLine();

            System.out.printf("Informe a cilindrada do veiculo: ");
            nova_moto.setCilindradas(scan.nextInt());
            scan.nextLine();

            nova_moto.infoMoto();
            veiculos.add(nova_moto);
        }

    }

    public static void cadastrarLocacao() {

        Locacao nova_locacao = new Locacao();

        System.out.println("=================================");

        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado!");
            return;
        }

        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veiculo cadastrado!");
            return;
        }

        System.out.println("Selecione o cliente:");
        for (int i = 0; i < clientes.size(); i++) {
            Cliente nome_cliente = clientes.get(i);
            System.out.printf("( %d ) %s \n", i, nome_cliente.getNome());
        }

        System.out.println("=================================");
        System.out.print("Selecione o cliente: ");
        int selecao = scan.nextInt();
        scan.nextLine();

        if (selecao < 0 || selecao >= clientes.size()) {
            System.out.println("Cliente invalido!");
            return;
        }

        nova_locacao.setCliente(clientes.get(selecao));

        System.out.println("=================================");
        System.out.println("Selecione o veiculo:");
        for (int i = 0; i < veiculos.size(); i++) {
            Veiculo veiculo = veiculos.get(i);
            System.out.printf("( %d ) Placa: %s | Diaria R$: %.2f \n",
                    i, veiculo.getPlaca(), veiculo.getValorDiaria());
        }

        System.out.println("=================================");
        System.out.print("Selecione o veiculo: ");
        selecao = scan.nextInt();
        scan.nextLine();

        if (selecao < 0 || selecao >= veiculos.size()) {
            System.out.println("Veiculo invalido!");
            return;
        }

        nova_locacao.setVeiculo(veiculos.get(selecao));

        System.out.print("Informe a data de retirada (DD/MM/AAAA): ");
        String dataRetiradaStr = scan.nextLine();

        System.out.print("Informe a data de devolucao (DD/MM/AAAA): ");
        String dataDevolucaoStr = scan.nextLine();

        String[] retirada = dataRetiradaStr.split("/");
        String dataRetiradaFormatada = retirada[2] + "-" + retirada[1] + "-" + retirada[0];

        String[] devolucao = dataDevolucaoStr.split("/");
        String dataDevolucaoFormatada = devolucao[2] + "-" + devolucao[1] + "-" + devolucao[0];

        nova_locacao.setDataRetirada(java.sql.Date.valueOf(dataRetiradaFormatada));
        nova_locacao.setDataDevolucao(java.sql.Date.valueOf(dataDevolucaoFormatada));

        nova_locacao.setDevolvido(false);

        long diferencaMilissegundos = nova_locacao.getDataDevolucao().getTime()
                - nova_locacao.getDataRetirada().getTime();

        long quantidadeDias = diferencaMilissegundos / (1000 * 60 * 60 * 24);

        if (quantidadeDias <= 0) {
            quantidadeDias = 1;
        }

        double total = quantidadeDias * nova_locacao.getVeiculo().getValorDiaria();
        nova_locacao.setTotalDiaria(total);

        locacoes.add(nova_locacao);

        System.out.println("=================================");
        System.out.println("Locacao cadastrada com sucesso!");
        nova_locacao.infoLocacao();
    }

    public static void cadastrarDevolucao() {
        System.out.println("=================================");

        if (locacoes.isEmpty()) {
            System.out.println("Nenhuma locacao cadastrada!");
            return;
        }

        System.out.println("Selecione a locacao a ser baixada:");
        for (int i = 0; i < locacoes.size(); i++) {
            Locacao locacao = locacoes.get(i);
            System.out.printf("( %d ) %s | %s | R$: %.2f\n",
                    i,
                    locacao.getCliente().getNome(),
                    locacao.getVeiculo().getPlaca(),
                    locacao.getTotalDiaria());
        }

        System.out.println("=================================");
        System.out.print("Selecione a locacao: ");
        int selecao = scan.nextInt();
        scan.nextLine();

        if (selecao < 0 || selecao >= locacoes.size()) {
            System.out.println("Locacao invalida!");
            return;
        }

        Locacao locacaoSelecionada = locacoes.get(selecao);

        if (locacaoSelecionada.getDevolvido()) {
            System.out.println("Esta locacao ja foi devolvida!");
            return;
        }

        locacaoSelecionada.setDevolvido(true);

        System.out.println("=================================");
        System.out.println("Devolucao realizada com sucesso!");
        locacaoSelecionada.infoLocacao();
    }

    public static void listarDevolucoesPendentes() {

        System.out.println("=================================");
    
        if (locacoes.isEmpty()) {
            System.out.println("Nenhuma locacao cadastrada!");
            return;
        }

        boolean encontrouPendente = false;

        for (int i = 0; i < locacoes.size(); i++) {
            Locacao locacao = locacoes.get(i);

            if (!locacao.getDevolvido()) {
                encontrouPendente = true;

                System.out.printf("( %d ) Cliente: %s \n", i, locacao.getCliente().getNome());
                System.out.printf("      Veiculo: %s \n", locacao.getVeiculo().getPlaca());
                System.out.printf("      Retirada: %s \n", locacao.getDataRetirada());
                System.out.printf("      Devolucao: %s \n", locacao.getDataDevolucao());
                System.out.printf("      Total: R$ %.2f \n", locacao.getTotalDiaria());
             }
        }

        if (!encontrouPendente) {
            System.out.println("Nao ha devolucoes pendentes.");
        }
    }

    public static void popularListas() {

        Cliente cl1 = new Cliente("Jorge", "123.456.789-01", "12.345-01");
        Cliente cl2 = new Cliente("Ronaldo", "987.654.321.98", "54.321-01");

        Carro cr1 = new Carro("Carro", "A1B2C3", 250.4, 1);
        Carro cr2 = new Carro("Carro", "D1E2F3", 450.9, 0);

        Moto mt1 = new Moto("Moto", "G1H2I3", 150, 125);
        Moto mt2 = new Moto("Moto", "J1K2L3", 550, 1000);

        Locacao lc1 = new Locacao(cl2, mt2,
                java.sql.Date.valueOf("2026-04-22"),
                java.sql.Date.valueOf("2026-04-25"),
                false,
                0);

        Locacao lc2 = new Locacao(cl1, cr1,
                java.sql.Date.valueOf("2026-04-22"),
                java.sql.Date.valueOf("2026-04-22"),
                true,
                0);

        clientes.add(cl1);
        clientes.add(cl2);
        veiculos.add(cr1);
        veiculos.add(cr2);
        veiculos.add(mt1);
        veiculos.add(mt2);
        locacoes.add(lc1);
        locacoes.add(lc2);

    }

}