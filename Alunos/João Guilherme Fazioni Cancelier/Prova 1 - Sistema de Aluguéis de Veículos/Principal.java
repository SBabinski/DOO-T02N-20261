import objetos.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Principal {
    static Scanner scan = new Scanner(System.in);
    static ArrayList<Cliente> clientes  = new ArrayList<>();
    static ArrayList<Veiculo> veiculos  = new ArrayList<>();
    static ArrayList<Locacao> locacaos  = new ArrayList<>();
    public static void main(String[] args) {
        menuPrincipal();
    }

    private static void menuPrincipal() {
        int escolha;
        do {
            System.out.println("\n--- BIBLIOTECA ACADÊMICA ---");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Cadastrar Veiculo");
            System.out.println("3. Nova Locação");
            System.out.println("4. Devolução");
            System.out.println("5. Listar Pendentes");
            System.out.println("6. Demonstração");
            System.out.println("0. Sair");
            System.out.print("Opção: ");
            escolha = scan.nextInt();
            scan.nextLine();

            validarEscolha(escolha);
        } while (escolha != 0);
    }

    private static void validarEscolha(int escolha) {
       switch (escolha) {
            case 1:
                cadastrarCliente();
                break;
            case 2:
                cadastrarVeiculo();
                break;
            case 3:
                cadastrarLocalção();
                break;
            case 4:
                realizarDevolucao(); 
                break;
            case 5:
                listarPendentes();                
                break;
            case 6:
                demonstracao(); 
                break;
            case 0:
                System.out.println("Obrigado por utilizar o sistema.");
                break;
            default:
                System.out.println("Selecione uma opção válida");
                break;
        }
    }

    private static void cadastrarCliente() {
        System.out.println("Nome:");
        String nome = scan.nextLine();
        System.out.println("CPF:");
        String cpf = scan.nextLine();
        System.out.println("Numero da CNH:");
        String cnh = scan.nextLine();
        clientes.add(new Cliente(nome, cpf, cnh));
        System.out.println("Cliente cadastrado!");
    }

    private static void cadastrarVeiculo() {
        System.out.print("1-Carro / 2-Moto: ");
        int tipo = scan.nextInt();
        scan.nextLine();

        System.out.print("Placa: "); 
        String placa = scan.nextLine();

        System.out.println("Valor da diaria: ");

        double dia = scan.nextDouble();
        scan.nextLine();

		
        if (tipo == 1) {
            System.out.print("Possui Ar-condicionado\n1[SIM] 2[NÃO]: ");
            int ar = scan.nextInt();
            scan.nextLine();
            if (ar==1) {
                veiculos.add(new Carro(placa,dia , true));
                System.out.println("Carro cadastrado com sucesso!");
            }else{
                veiculos.add(new Carro(placa,dia , false));
                System.out.println("Carro cadastrado com sucesso!");
            }
        } else if (tipo == 2) {
            System.out.print("Número de Cilindradas: ");
            int cilindra = scan.nextInt();
            scan.nextLine(); 

            veiculos.add(new Moto(placa, dia, cilindra));
            System.out.println("Moto cadastrada com sucesso!");
        } else {
            System.out.println("Tipo inválido. Operação cancelada.");
        }
    }

    private static void cadastrarLocalção() {
        if (locacaos.size()>=10) {
            System.out.println("Erro: Limite de 10 empréstimos atingido!");
            return;
        }
        System.out.println("--- Selecione o Cliente ---");
        for(int i=0;i <clientes.size();i++){
            System.out.print((i+1)+"|  " );
            clientes.get(i).exibir();
        }
        int icli = scan.nextInt()-1;
        scan.nextLine();

        System.out.println("--- Selecione o Veiculo ---");
        for(int i=0;i <veiculos.size();i++){
            System.out.print((i+1)+"|  " );
            veiculos.get(i).exibir();
        }
        int ivei = scan.nextInt()-1;
        scan.nextLine();

        System.out.println("Data de retirada (dd/MM/yyyy):");
        String input = scan.nextLine();

		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data = LocalDate.parse(input, formato);

        locacaos.add(new Locacao(clientes.get(icli), veiculos.get(ivei), data));
    }

    private static void realizarDevolucao() {
      System.out.println("--- Selecione a Locação ---");
        for(int i=0;i <locacaos.size();i++){
             if (locacaos.get(i).isSituacao()==false) {
                System.out.println((i+1)+" | "+ locacaos.get(i).toString());
            }
        }
        int iemp = scan.nextInt() -1;
        scan.nextLine();

        if (iemp >= 0 && iemp < locacaos.size()) {
        locacaos.get(iemp).registrarDevolucao();
        System.out.println("Devolução concluída!");
        }
    }

    private static void listarPendentes() {
        for(int i=0;i <locacaos.size();i++){
            if (locacaos.get(i).isSituacao()==false) {
                System.out.println((i+1)+" | "+ locacaos.get(i).toString());
            }
        }
    }

    private static void demonstracao() {
        Cliente cli1 = new Cliente("Jorge", "111.111.111-11", "123456789");
        Cliente cli2 = new Cliente("Cleber", "222.222.222-22", "987654321");
        
        clientes.add(cli1);
        clientes.add(cli2);

        Carro car =new Carro("ABC4D78", 350.00, true);
        Moto mot =new Moto("FHB2A68", 125.00, 150);

        veiculos.add(car);
        veiculos.add(mot);

        Locacao loc1 = new Locacao(cli1, car, LocalDate.now().minusDays(5));
        loc1.registrarDevolucao(); 
        locacaos.add(loc1);

        Locacao loc2 = new Locacao(cli2, mot, LocalDate.now().minusDays(2));
        locacaos.add(loc2);

        listarPendentes();

    }
}
