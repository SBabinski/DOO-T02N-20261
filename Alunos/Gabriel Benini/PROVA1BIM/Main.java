import java.util.Scanner;

static Banco banco = new Banco();

public static void main(String[] args) {

    Scanner scan = new Scanner(System.in);
    Demo.carregar(banco);

    int opcao = 100;

    do {

        System.out.println("[0] Sair");
        System.out.println("[1] Registrar cliente");
        System.out.println("[2] Registrar veiculo");
        System.out.println("[3] Registrar locação");
        System.out.println("[4] Listar locações");
        opcao = scan.nextInt();

        switch (opcao) {

            default: System.out.println("Selecione uma opção válida"); break;
            case 0: break;
            case 1: cadastroClientes(); break;
            case 2: cadastroVeiculos(scan); break;
            case 3: cadastroLocacao(scan); break;
            case 4: banco.listarLocacoes(); break;


        }

    } while (opcao != 0);

}

public static void cadastroClientes(){

    Cliente cliente = new Cliente();

    cliente.cadastrarCliente();
    banco.getClientes().add(cliente);

}

public static void cadastroVeiculos(Scanner scan){

    System.out.println("Qual tipo de veículo será cadastrado: ");
    System.out.println("[1] Carro");
    System.out.println("[2] Moto");
    int opcao = scan.nextInt();

    switch (opcao) {

        default: System.out.println("Selecione uma opção válida"); break;
        case 1: cadastroCarro(); break;
        case 2: cadastroMoto(); break;

    }

}

public static void cadastroCarro(){

    Carro carro = new Carro();

    carro.cadastrarCarro();
    banco.getCarros().add(carro);

}

public static void cadastroMoto(){

    Moto moto = new Moto();

    moto.cadastrarMoto();
    banco.getMotos().add(moto);

}

public static void cadastroLocacao(Scanner scan) {

    Locadora sistema = new Locadora(banco);
    sistema.cadastrarLocacao(scan);

}