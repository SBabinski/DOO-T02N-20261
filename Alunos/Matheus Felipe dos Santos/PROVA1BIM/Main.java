import java.util.Scanner;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Locadora locadora = new Locadora();

        Cliente[] clientes = new Cliente[10];
        Veiculo[] veiculos = new Veiculo[10];

        int qtdClientes = 0;
        int qtdVeiculos = 0;

        int op;

        do {
            System.out.println("---------LOCAÇÃO DE VEICULOS!---------");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Cadastrar Veiculo");
            System.out.println("3 - Cadastrar Locacao");
            System.out.println("4 - Realizar Devolucao");
            System.out.println("5 - Listar Locacoes Ativas");
            System.out.println("6 - Demonstracao");
            System.out.println("0 - Sair");

            op = sc.nextInt();

            switch (op) {

                case 1:
                    if (qtdClientes < 10) {
                        Cliente c = new Cliente();
                        System.out.println("Nome:");
                        sc.nextLine();
                        c.nome = sc.nextLine();
                        System.out.println("CPF:");
                        c.cpf = sc.nextLine();
                        System.out.println("CNH:");
                        c.cnh = sc.nextLine();

                        clientes[qtdClientes] = c;
                        qtdClientes++;
                    }
                    break;

                case 2:
                    if (qtdVeiculos < 10) {
                        System.out.println("1-Carro 2-Moto");
                        int tipo = sc.nextInt();

                        if (tipo == 1) {
                            Carro car = new Carro();
                            System.out.println("Placa:");
                            sc.nextLine();
                            car.placa = sc.nextLine();

                            System.out.println("Valor diaria:");
                            car.valorDiaria = sc.nextDouble();

                            System.out.println("Tem ar? (S/N)");
                            sc.nextLine();
                            car.arCondicionado = sc.nextLine().equalsIgnoreCase("S");

                            veiculos[qtdVeiculos] = car;
                        } else {
                            Moto m = new Moto();
                            System.out.println("Placa:");
                            sc.nextLine();
                            m.placa = sc.nextLine();

                            System.out.println("Valor diaria:");
                            m.valorDiaria = sc.nextDouble();

                            System.out.println("Cilindrada:");
                            m.cilindrada = sc.nextInt();

                            veiculos[qtdVeiculos] = m;
                        }
                        qtdVeiculos++;
                    }
                    break;

                case 3:
                    if (locadora.quantidade < 10 && qtdClientes > 0 && qtdVeiculos > 0) {

                        Locacao l = new Locacao();

                        System.out.println("Escolha cliente (indice):");
                        for (int i = 0; i < qtdClientes; i++) {
                            System.out.println(i + " - " + clientes[i].nome);
                        }
                        int ic = sc.nextInt();
                        l.cliente = clientes[ic];

                        System.out.println("Escolha veiculo (indice):");
                        for (int i = 0; i < qtdVeiculos; i++) {
                            System.out.println(i + " - " + veiculos[i].placa);
                        }
                        int iv = sc.nextInt();
                        l.veiculo = veiculos[iv];

                        System.out.println("Data retirada (AAAA/MM/DD):");
                        sc.nextLine();
                        String dr = sc.nextLine();

                        System.out.println("Data devolucao (AAAA/MM/DD):");
                        String dd = sc.nextLine();

                        try {
                            l.dataRetirada = LocalDate.parse(dr.replace("/", "-"));
                            l.dataDevolucao = LocalDate.parse(dd.replace("/", "-"));
                        } catch (Exception e) {
                            System.out.println("Data invalida!");
                            break;
                        }

                        if (l.dataDevolucao.isBefore(l.dataRetirada)) {
                            System.out.println("Data de devolucao nao pode ser antes da retirada");
                            break;
                        }

                        l.devolvido = false;
                        locadora.adicionarLocacao(l);

                    } else {
                        System.out.println("Cadastre cliente e veiculo primeiro");
                    }
                    break;

                
                 case 4:
                    if (locadora.quantidade > 0 && qtdClientes > 0) {

                        System.out.println("Escolha cliente (indice):");
                        for (int i = 0; i < qtdClientes; i++) {
                            System.out.println(i + " - " + clientes[i].nome);
                        }
                        int ic = sc.nextInt();

                        Cliente clienteSelecionado = clientes[ic];

                        boolean encontrou = false;

                        System.out.println("Locacoes desse cliente:");

                        for (int i = 0; i < locadora.quantidade; i++) {
                            if (locadora.locacoes[i].cliente == clienteSelecionado 
                                && !locadora.locacoes[i].devolvido) {

                                System.out.println(i + " - Veiculo: " 
                                    + locadora.locacoes[i].veiculo.placa);

                                encontrou = true;
                            }
                        }

                        if (!encontrou) {
                            System.out.println("Nenhuma locacao pendente para esse cliente");
                            break;
                        }

                        System.out.println("Digite o indice da locacao para devolver:");
                        int idx = sc.nextInt();

                        if (idx >= 0 && idx < locadora.quantidade) {
                            if (locadora.locacoes[idx].cliente == clienteSelecionado) {
                                locadora.locacoes[idx].devolvido = true;
                                System.out.println("Devolucao realizada!");
                            } else {
                                System.out.println("Locacao nao pertence a esse cliente");
                            }
                        }

                    } else {
                        System.out.println("Nenhuma locacao cadastrada");
                    }
                    break;

                case 5:
                    locadora.listarLocacoesAtivas();
                    break;

                case 6:

                    Cliente c1 = new Cliente();
                    c1.nome = "Joao";
                    c1.cpf = "111";
                    c1.cnh = "123";

                    Cliente c2 = new Cliente();
                    c2.nome = "Maria";
                    c2.cpf = "222";
                    c2.cnh = "456";

                    Carro carro = new Carro();
                    carro.placa = "ABC123";
                    carro.valorDiaria = 100;
                    carro.arCondicionado = true;

                    Moto moto = new Moto();
                    moto.placa = "XYZ999";
                    moto.valorDiaria = 50;
                    moto.cilindrada = 300;

                    Locacao l1 = new Locacao();
                    l1.cliente = c1;
                    l1.veiculo = carro;
                    l1.dataRetirada = LocalDate.of(2024, 1, 1);
                    l1.dataDevolucao = LocalDate.of(2024, 1, 5);
                    l1.devolvido = true;

                    Locacao l2 = new Locacao();
                    l2.cliente = c2;
                    l2.veiculo = moto;
                    l2.dataRetirada = LocalDate.of(2024, 2, 1);
                    l2.dataDevolucao = LocalDate.of(2024, 2, 3);
                    l2.devolvido = false;

                    locadora.adicionarLocacao(l1);
                    locadora.adicionarLocacao(l2);

                    locadora.listarLocacoesAtivas();
                    break;
            }

        } while (op != 0);
        sc.close();
    }
}