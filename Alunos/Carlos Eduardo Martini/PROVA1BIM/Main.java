import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Locadora locadora = new Locadora();
        Clientes[] listaClientes = new Clientes[10];
        int quantClient = 0;
        Veiculos[] listaVeiculos = new Veiculos[10];
        int quantVeicu = 0;

        int opc = 1000;

        while (opc != 0) {
            System.out.println("===Sistema de locação===");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Cadastrar Veículo");
            System.out.println("3 - Cadastrar Locação");
            System.out.println("4 - Realizar Devolução");
            System.out.println("5 - Listar Locações sem devolução");
            System.out.println("6 - Fazer Demonstração");
            System.out.println("0 - Sair");
            opc = scan.nextInt();
            scan.nextLine();

            switch (opc) {
                case 1:

                    if (quantClient < 10) {
                        System.out.println("---Cadastrar cliente---");
                        System.out.println("nome: ");
                        String nome = scan.nextLine();
                        System.out.println("cpf: ");
                        String cpf = scan.nextLine();
                        System.out.println("CNH: ");
                        String cnh = scan.nextLine();

                        Clientes novocliente = new Clientes(nome, cpf, cnh);

                        listaClientes[quantClient] = novocliente;
                        quantClient++;

                        System.out.println("novo cliente cadastrado");
                    } else {
                        System.out.println("!!! limite maximo de clientes atingido !!!");
                    }
                    break;

                case 2:

                    if (quantVeicu < 10) {
                        System.out.println("\n--- CADASTRAR NOVO VEÍCULO ---");
                        System.out.println("1 - Carro");
                        System.out.println("2 - Moto");
                        System.out.print("Escolha o tipo do veículo: ");
                        int tipoVeiculo = scan.nextInt();
                        scan.nextLine();

                        System.out.print("Digite a placa: ");
                        String placa = scan.nextLine();

                        System.out.print("Digite o valor da diária: ");
                        double valorDiaria = scan.nextDouble();
                        scan.nextLine();

                        if (tipoVeiculo == 1) {
                            System.out.print("Possui ar-condicionado? (true para Sim / false para Não): ");
                            boolean arCondicionado = scan.nextBoolean();
                            scan.nextLine();

                            Carros novoCarro = new Carros(placa, valorDiaria, arCondicionado);
                            listaVeiculos[quantVeicu] = novoCarro;
                            quantVeicu++;
                            System.out.println("Carro cadastrado com sucesso!");

                        } else if (tipoVeiculo == 2) {
                            System.out.print("Digite a cilindrada da moto: ");
                            int cilindrada = scan.nextInt();
                            scan.nextLine();

                            Motos novaMoto = new Motos(placa, valorDiaria, cilindrada);
                            listaVeiculos[quantVeicu] = novaMoto;
                            quantVeicu++;
                            System.out.println("Moto cadastrada com sucesso!");

                        } else {
                            System.out.println("Opção inválida! O veículo não foi cadastrado.");
                        }
                    } else {
                        System.out.println("!!! Limite máximo de veículos cadastrados atingido. !!!");
                    }
                    break;

                case 3:

                    if (quantClient == 0 || quantVeicu == 0) {
                        System.out.println("!!! Erro: Cadastre pelo menos 1 cliente e 1 veículo primeiro !!!");
                        break;
                    }

                    System.out.println("\n--- CADASTRAR NOVA LOCAÇÃO ---");

                    System.out.println("Clientes disponíveis:");
                    for (int i = 0; i < quantClient; i++) {
                        System.out.println(i + " - " + listaClientes[i].nome + " (CPF: " + listaClientes[i].cpf + ")");
                    }
                    System.out.print("Escolha o NÚMERO do cliente: ");
                    int idCliente = scan.nextInt();
                    scan.nextLine();

                    System.out.println("\nVeículos disponíveis:");
                    for (int i = 0; i < quantVeicu; i++) {
                        System.out.println(i + " - Placa: " + listaVeiculos[i].placa + " | Diária: R$"
                                + listaVeiculos[i].valorDiaria);
                    }
                    System.out.print("Escolha o NÚMERO do veículo: ");
                    int idVeiculo = scan.nextInt();
                    scan.nextLine();

                    if (idCliente >= 0 && idCliente < quantClient && idVeiculo >= 0 && idVeiculo < quantVeicu) {

                        System.out.print("Digite a Data de Retirada (ex: 22/04/2026): ");
                        String dataRetirada = scan.nextLine();

                        System.out.print("Digite a Data de Devolução (ex: 25/04/2026): ");
                        String dataDevolucao = scan.nextLine();

                        System.out.print("Qual a quantidade de diárias?: ");
                        int dias = scan.nextInt();
                        scan.nextLine();

                        Locacao novaLocacao = new Locacao(listaClientes[idCliente], listaVeiculos[idVeiculo],
                                dataRetirada);

                        novaLocacao.dataDevolucao = dataDevolucao;
                        novaLocacao.quantidadeDiarias = dias;

                        locadora.registrarLocacao(novaLocacao);

                    } else {
                        System.out.println("!!! Erro: O número do cliente ou do veículo é inválido !!!");
                    }
                    break;
                case 4:

                    if (locadora.contador == 0) {
                        System.out.println("!!! Nenhuma locação registrada no sistema ainda. !!!");
                        break;
                    }

                    System.out.println("\n--- REALIZAR DEVOLUÇÃO ---");
                    System.out.println("Locações ativas:");
                    boolean temAtiva = false;

                    for (int i = 0; i < locadora.contador; i++) {
                        if (locadora.locacoes[i].devolvido == false) {
                            System.out.println(i + " - Cliente: " + locadora.locacoes[i].cliente.nome
                                    + " | Veículo Placa: " + locadora.locacoes[i].veiculo.placa);
                            temAtiva = true;
                        }
                    }

                    if (temAtiva == false) {
                        System.out.println(" Todas as locações já foram devolvidas!");
                    } else {
                        System.out.print("\nDigite o NÚMERO da locação que deseja devolver: ");
                        int idDevolucao = scan.nextInt();
                        scan.nextLine();

                        if (idDevolucao >= 0 && idDevolucao < locadora.contador) {

                            if (locadora.locacoes[idDevolucao].devolvido == false) {
                                locadora.locacoes[idDevolucao].devolvido = true;
                                System.out.println(" Devolução realizada com sucesso!");

                                locadora.locacoes[idDevolucao].calcularValorTotal();
                            } else {
                                System.out.println("!!! Esta locação já foi devolvida anteriormente. !!!");
                            }

                        } else {
                            System.out.println("!!! Número de locação inválido !!!");
                        }
                    }

                    break;
                case 5:
                    locadora.listarLocacoesSemDevolucao();
                    break;
                case 6:
                    fazerDemonstracao(locadora);
                    break;
                case 0:
                    System.out.println("Agradecemos por utilizar o sistema");
                    break;

                default:
                    System.out.println("!!! Insira uma opção valida !!!");
                    break;
            }
        }

    }

    public static void fazerDemonstracao(Locadora locadora) {
        System.out.println("\n--- INICIANDO DEMONSTRAÇÃO ---");

        Clientes c1 = new Clientes("Joao da Silva", "111.111.111-11", "123456789");
        Clientes c2 = new Clientes("Maria Oliveira", "222.222.222-22", "987654321");

        Carros carro1 = new Carros("ABC-1234", 150.0, true);
        Motos moto1 = new Motos("XYZ-9876", 80.0, 250);

        Locacao locacao1 = new Locacao(c1, carro1, "10/05/2026");
        locacao1.dataDevolucao = "15/05/2026";
        locacao1.quantidadeDiarias = 5;
        
        Locacao locacao2 = new Locacao(c2, moto1, "20/05/2026");
        locacao2.dataDevolucao = "22/05/2026";
        locacao2.quantidadeDiarias = 2;

        locacao1.devolvido = true;

        locadora.registrarLocacao(locacao1);
        locadora.registrarLocacao(locacao2);

        locadora.listarLocacoesSemDevolucao();

        System.out.println("--- FIM DA DEMONSTRAÇÃO ---");
    }
}