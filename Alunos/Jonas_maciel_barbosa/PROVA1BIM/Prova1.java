import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Prova1 {
    
    static Scanner scan = new Scanner(System.in);
    static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    static ArrayList<Cliente> clientes = new ArrayList<>();
    static ArrayList<Veiculo> veiculos = new ArrayList<>();
    static Locadora locadora = new Locadora();


    public static void main(String[] args) {
        
        int opcao =-1;
 
        while (opcao != 0) {
            System.out.println();
            System.out.println("==LOCADORA DE VEICULOS==");
            System.out.println("1 - Cadastrar cliente");
            System.out.println("2 - Cadastrar veiculo");
            System.out.println("3 - Registrar locacao");
            System.out.println("4 - Realizar devolucao");
            System.out.println("5 - Listar locacoes sem devolucao");
            System.out.println("6 - Demonstracao");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opcao: ");

            opcao = scan.nextInt();
            scan.nextLine();

                   if (opcao == 1) {
                cadastrarCliente();
            } else if (opcao == 2) {
                cadastrarVeiculo();
            } else if (opcao == 3) {
                registrarLocacao();
            } else if (opcao == 4) {
                realizarDevolucao();
            } else if (opcao == 5) {
                locadora.listarSemDevolucao();
            } else if (opcao == 6) {
                demonstracao();
            } else if (opcao == 0) {
                System.out.println("Encerrando o sistema...");
            } else {
                System.out.println("Opcao invalida, tente novamente.");
            }       
    }
}

         static void cadastrarCliente() {
            System.out.println("**Cadastro de Clientes**");
           
        System.out.print("Nome: ");
            String nome = scan.nextLine();

        System.out.print("CPF: ");
            String cpf = scan.nextLine();

        System.out.print("Numero da CNH: ");
            String cnh = scan.nextLine();

        Cliente c = new Cliente(nome, cpf, cnh);
            clientes.add(c);

        System.out.println("Cliente cadastrado com sucesso!");
    }

    static void cadastrarVeiculo() {
        System.out.println("**Cadastro de Veiculo**");
        System.out.println("Tipo: 1 - Carro  |  2 - Moto");
        System.out.print("Escolha: ");
        int tipo = scan.nextInt();
        scan.nextLine();

        System.out.print("Placa: ");
        String placa = scan.nextLine();

        System.out.print("Valor da diaria (R$): ");
        double diaria = scan.nextDouble();
        scan.nextLine();

        if (tipo == 1) {
            System.out.print("Tem ar-condicionado? (s/n): ");
            String resposta = scan.nextLine();
            boolean ar = resposta.equalsIgnoreCase("s");
            Carro carro = new Carro(placa, diaria, ar);
            veiculos.add(carro);
            System.out.println("Carro cadastrado com sucesso!");

        } else if (tipo == 2) {
            System.out.print("Cilindrada (cc): ");
            int cilindra = scan.nextInt();
            scan.nextLine();
            Moto moto = new Moto(placa, diaria, cilindra);
            veiculos.add(moto);
            System.out.println("Moto cadastrada com sucesso!");

        } else {
            System.out.println("Tipo invalido.");
        }
    }


    static void registrarLocacao() {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado ainda.");
            return;
        }
        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veiculo cadastrado ainda.");
            return;
        }

        System.out.println(" **Clientes cadastrados** ");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println(i + " - " + clientes.get(i).getNome());
        }
        System.out.print("Indice do cliente: ");
        int ic = scan.nextInt();
        scan.nextLine();

        System.out.println(" ** Veiculos cadastrados ** ");
        for (int i = 0; i < veiculos.size(); i++) {
            System.out.print(i + " - ");
            veiculos.get(i).exibirInfo();
        }
        System.out.print("Indice do veiculo: ");
        int iv = scan.nextInt();
        scan.nextLine();

        System.out.print("Data de retirada (dd/MM/yyyy): ");
        LocalDate retirada = LocalDate.parse(scan.nextLine(), dtf);

        System.out.print("Data de devolucao (dd/MM/yyyy): ");
        LocalDate devolucao = LocalDate.parse(scan.nextLine(), dtf);

        Locacao loc = new Locacao(clientes.get(ic), veiculos.get(iv), retirada, devolucao);
        locadora.adicionarLocacao(loc);
    }

    static void realizarDevolucao() {

        locadora.listarSemDevolucao();

        System.out.print("Digite o indice da locacao a devolver: ");
        int idx = scan.nextInt();
        scan.nextLine();

        Locacao loc = locadora.getLocacao(idx);

        if (loc == null) {
            System.out.println("Indice invalido.");
        } else if (loc.isDevolvido()) {
            System.out.println("Essa locacao ja foi devolvida.");
        } else {
            loc.realizarDevolucao();
        }
    }

 static void demonstracao() {
        System.out.println("*** DEMONSTRACAO ***");

        Cliente c1 = new Cliente("Jonas Maciel", "123.456.789-00", "CNH-1001");
        Cliente c2 = new Cliente("Samuel Babinski", "987.654.321-00", "CNH-2002");

        Carro carro = new Carro("ABC-1234", 200.0, true);
        Moto moto = new Moto("XYZ-9876", 100.0, 300);


        Locadora testeLocadora = new Locadora();

        LocalDate retirada1 = LocalDate.of(2025, 4, 1);
        LocalDate devolucao1 = LocalDate.of(2025, 4, 5);
        Locacao loc1 = new Locacao(c1, carro, retirada1, devolucao1);
        loc1.realizarDevolucao();
        testeLocadora.adicionarLocacao(loc1);

        LocalDate retirada2 = LocalDate.of(2025, 4, 10);
        LocalDate devolucao2 = LocalDate.of(2025, 4, 15);
        Locacao loc2 = new Locacao(c2, moto, retirada2, devolucao2);
        testeLocadora.adicionarLocacao(loc2);

        System.out.println();
        System.out.println("Locacoes ativas (sem devolucao):");
        testeLocadora.listarSemDevolucao();
    }
       
}
