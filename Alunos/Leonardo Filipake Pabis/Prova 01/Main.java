import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
static Scanner scan = new Scanner(System.in);
static int opcao = 1000;
static ArrayList<Locacao> locacoes = new ArrayList<>();
static ArrayList<Cliente> clientes = new ArrayList<>();
static ArrayList<Carro> veiculosCarros = new ArrayList<>();
static ArrayList<Moto> veiculosMotos = new ArrayList<>();
    public static void main(String[] args) {
        menu();
    }

    public static void menu(){
        //vendas não está mais sendo utilizada, foi trocado pelos pedidos, e alocar o dia da venda é feita automaticamente com o dia de criação da venda (que é sempre o dia atual)
        System.out.println("********");
        System.out.println("* MENU *");
        System.out.println("********");
        System.out.println("1 - Cadastrar locação");
        System.out.println("2 - Cadastrar cliente");
        System.out.println("3 - Cadastrar veiculo");
        System.out.println("4 - Realizar devolucao");
        System.out.println("5 - Listar locações ativas");
        System.out.println("6 - Demonstração");
        System.out.println("7 - Sair");
        
        do{
            System.out.println("Digite uma opção válida");
            opcao = scan.nextInt();
            scan.nextLine();
            if (opcao == 1){
                cadastrarLocacao();
            }else if (opcao == 2){
                cadastrarCliente();
            }else if (opcao == 3){
                cadastrarVeiculo();
            }else if (opcao == 4){
                realizarDevolucao();
            }else if (opcao == 5){
                listarLocacoes();
            }else if (opcao == 6){
                demonstracao();
            }else if (opcao == 7){
                Sair();
            }
        }while (opcao != 7);

    }

    public static void cadastrarLocacao(){
        if (locacoes.size() >= 10){
            System.out.println("Locações cheias, faça uma devolução");
            voltarMenu();
        }
        Locacao locacao = new Locacao();
        System.out.println("1 - Carro \n2 - Moto");
        int opcao = 0;
        Veiculo veiculo = null;
        while(true){
            opcao = scan.nextInt();
            scan.nextLine();
            if (opcao == 1){
                veiculo = locacaoCarro();
                break;
            }else if (opcao == 2){
                veiculo = locacaoMoto();
                break;
            }else {
                System.out.println("Digite uma opção válida: ");
            }
        }

        locacao.setVeiculo(veiculo);

        Cliente cliente = null;
        System.out.println("Clientes: ");
        for (int i = 0; i < clientes.size(); i++){
            System.out.println(i+1 + " - "+clientes.get(i).informacoes());
        }
        opcao = 0;
        while(cliente == null){
            opcao = scan.nextInt();
            scan.nextLine();
            for (int i = 0; i < clientes.size(); i++){
                if (opcao - 1 == i){
                    cliente = clientes.get(i);
                }
            }
            if (cliente == null){
                System.out.println("Digite uma opção válida: ");
            }
        }
        
        veiculo.setCliente(cliente);
        locacao.setCliente(cliente);

        System.out.println("Digite a data de retirada (dd/mm/aaaa): ");
        String dataRetirada = scan.nextLine();
        locacao.setDataRetirada(dataRetirada);

        System.out.println("Digite a data para devolução (dd/mm/aaaa): ");
        String dataDevolucao = scan.nextLine();
        locacao.setDataRetirada(dataDevolucao);

        locacao.setDevolvido(false);

        System.out.println("Locação realizada com sucesso");
        locacoes.add(locacao);
        voltarMenu();
    }

    public static Carro locacaoCarro(){
        if (veiculosCarros.isEmpty()){
            System.out.println("Sem carros disponíveis");
            voltarMenu();
        }
        Carro carro = null;
        System.out.println("Carros disponíveis:");
        for (int i = 0; i < veiculosCarros.size(); i++){
            System.out.println((i+1)+" - "+veiculosCarros.get(i).informacoes());
        }
        int escolhaCarro = 0;
        System.out.println("Digite a escolha:");
        while (carro == null) {
            escolhaCarro = scan.nextInt();
            scan.nextLine();
            for (int i = 0; i < veiculosCarros.size(); i++){
                if ((escolhaCarro-1) == i){
                    carro = veiculosCarros.get(i);
                }
            }
            if (carro == null){
                System.out.println("Digite uma opção válida: ");
            }
        }
        veiculosCarros.remove(carro);
        return carro;
    }

    public static Moto locacaoMoto(){
        if (veiculosMotos.isEmpty()){
            System.out.println("Sem motos disponíveis");
            voltarMenu();
        }
        Moto moto = null;
        System.out.println("Motos disponíveis:");
        for (int i = 0; i < veiculosMotos.size(); i++){
            System.out.println((i+1)+" - "+veiculosMotos.get(i).informacoes());
        }
        int escolhaMoto = 0;
        System.out.println("Digite a escolha:");
        while (moto == null) {
            escolhaMoto = scan.nextInt();
            scan.nextLine();
            for (int i = 0; i < veiculosMotos.size(); i++){
                if ((escolhaMoto-1) == i){
                    moto = veiculosMotos.get(i);
                }
            }
            if (moto == null){
                System.out.println("Digite uma opção válida:");
            }
        }
        veiculosMotos.remove(moto);
        return moto;
    }
    
    public static void cadastrarCliente(){
        Cliente cliente = new Cliente();
        System.out.println("Digite o nome do cliente: ");
        String nome = scan.nextLine();
        cliente.setNome(nome);

        System.out.println("Digite o CPF do cliente (11122233344): ");
        String cpf = scan.nextLine();
        cliente.setCpf(cpf);

        System.out.println("Digite o número da CNH (123456789): ");
        String numero = scan.nextLine();
        cliente.setNumeroCNH(numero);

        System.out.println("Cliente cadastrado com sucesso");
        clientes.add(cliente);
        voltarMenu();
    }

    public static void cadastrarVeiculo(){
        System.out.println("1 - Cadastrar carro\n2 - Cadastrar moto");
        int opcao = 0;
        System.out.println("Digite uma opção:");
        while (true){
            opcao = scan.nextInt();
            scan.nextLine();
            if (opcao == 1){
                cadastrarCarro();
                break;
            }else if (opcao == 2){
                cadastrarMoto();
                break;
            }else{
                System.out.println("Digite uma opção válida:");
            }
        }
    }

    public static void cadastrarCarro(){
        Carro carro = new Carro();
        System.out.println("Digite a placa do carro: ");
        String placa = scan.nextLine();
        carro.setPlaca(placa);

        System.out.println("Digite o valor da diária: ");
        double valor = scan.nextDouble();
        scan.nextLine();
        carro.setValorDiaria(valor);

        System.out.println("O carro tem ar condicionado? (true) / (false)");
        boolean ar = scan.nextBoolean();
        carro.setAr(ar);

        System.out.println("Carro cadastrado com sucesso");
        veiculosCarros.add(carro);
        voltarMenu();
    }

    public static void cadastrarMoto(){
        Moto moto = new Moto();
        System.out.println("Digite a placa da moto: ");
        String placa = scan.nextLine();
        moto.setPlaca(placa);

        System.out.println("Digite o valor da diária: ");
        double valor = scan.nextDouble();
        scan.nextLine();
        moto.setValorDiaria(valor);

        System.out.println("Digite a cilindrada da moto: ");
        double cilindrada = scan.nextDouble();
        scan.nextLine();
        moto.setCilindrada(cilindrada);

        System.out.println("Moto cadastrada com sucesso");
        veiculosMotos.add(moto);
        voltarMenu();
    }

    public static void realizarDevolucao(){
        for (int i = 0; i < locacoes.size(); i++){
            System.out.println((i+1)+" - "+locacoes.get(i).informacoes());
        }
        System.out.println("Escolha o id da locação para devolver: ");
        while (true){
            int opcao = 0;
            opcao = scan.nextInt();
            scan.nextLine();
            for (int i = 0; i < locacoes.size(); i++){
                if (opcao-1 == i){
                    Veiculo v = locacoes.get(i).getVeiculo();
                    if (v instanceof Moto){
                        veiculosMotos.add((Moto) v);
                    }else if (v instanceof Carro){
                        veiculosCarros.add((Carro) v);
                    }
                    locacoes.get(i).getVeiculo().setCliente(null);
                    locacoes.get(i).setDevolvido(true);
                    locacoes.remove(i);
                    System.out.println("Locação devolvida com sucesso");
                    voltarMenu();
                }
            }
            if (opcao == 0){
                System.out.println("Digite uma opção válida:");
            }
        }
    }

    public static void listarLocacoes(){
        for (int i = 0; i < locacoes.size(); i++){
            System.out.println(locacoes.get(i).informacoes());
        }
        voltarMenu();
    }

    public static void demonstracao(){
        if (locacoes.size() >= 10){
            System.out.println("Locações cheias");
            voltarMenu();
        }
        Cliente cliente1 = new Cliente("Leo", "08573985019", "123765940");
        Cliente cliente2 = new Cliente("Patrick", "12309845633", "098765432");
        clientes.add(cliente1);
        clientes.add(cliente2);
        Carro carro = new Carro("bra2e19", 250, true);
        veiculosCarros.add(carro);
        Moto moto = new Moto("grau244", 50, 1000);
        veiculosMotos.add(moto);
        Locacao locacao1 = new Locacao(carro, cliente1, "25/04/2026", "30/04/2026");
        carro.setCliente(cliente1);
        veiculosCarros.remove(carro);
        Locacao locacao2 = new Locacao(moto, cliente2, "18/04/2026", "20/04/2026");
        moto.setCliente(cliente2);
        veiculosMotos.remove(moto);
        locacoes.add(locacao1);
        locacoes.add(locacao2);
        devolucaoDemonstracao(locacao2);
        veiculosMotos.add(moto);
        listarLocacoes();
        voltarMenu();

    }

    public static void devolucaoDemonstracao(Locacao locacao){
        
        locacao.getVeiculo().setCliente(null);
        locacao.setDevolvido(true);
        locacoes.remove(locacao);
    }

    public static void voltarMenu(){
        System.out.println("Pressione enter para voltar ao menu");
        scan.nextLine();
        menu();
    }

    public static void Sair(){
        System.out.println("Finalizando o systema");
    }
}