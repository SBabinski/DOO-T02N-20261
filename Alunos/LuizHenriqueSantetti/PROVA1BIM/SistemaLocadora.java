import java.time.LocalDate;
import java.util.Scanner;

public class SistemaLocadora { 
    static Locadora locadora = new Locadora();

public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n-----------------MENU--------------------------");
            System.out.println("1 - Cadastrar clientes");
            System.out.println("2 - Cadastrar veiculos");
            System.out.println("3 - Cadastrar locacão");
            System.out.println("4 - Realiza devolucao");
             System.out.println("5 - Listar locaçoes ativas");
            System.out.println("6 - demonstracao");
            System.out.println("0 -Sair");
            opcao = scan.nextInt();

            if (opcao == 1) cadastrarCliente(scan);
            else if (opcao == 2) cadastrarVeiculo(scan);
            else if (opcao == 3) cadastrarLocacao(scan);
            else if (opcao == 4) realizarDevolucao(scan);
            else if (opcao == 5) listarAtivas();
            else if (opcao == 6) demonstracao();
        }
    }

    public static void cadastrarCliente(Scanner s) {
        Cliente c = new Cliente();
        System.out.print("Me diga seu nome: "); c.nome = s.next();
        System.out.print("Qual CPF do senhor/a? "); c.cpf = s.next();
        System.out.print("junto com sua CNH por gentileza: "); c.cnh = s.next();
        locadora.clientes.add(c);
    }

    public static void cadastrarVeiculo(Scanner s) {
        System.out.print("1- carro 2- moto");
        int t = s.nextInt();
        if (t == 1) {
            Carro car = new Carro();
            System.out.print("qual a placa?: "); car.placa = s.next();
             System.out.print("qual valor da diaria: "); car.diaria = s.nextDouble();
            System.out.print("Ele tem ar condicionado? (true/false): "); car.temAr = s.nextBoolean();
            locadora.veiculos.add(car);
        } else {
            Moto mot = new Moto();
            System.out.print("Informe a placa desse veiculo: "); mot.placa = s.next();
            System.out.print("Diaria: "); mot.diaria = s.nextDouble();
            System.out.print("Qual é a cilindrada da sua moto?: "); mot.cilindrada = s.nextInt();
            locadora.veiculos.add(mot);
        }
    }

    public static void cadastrarLocacao(Scanner s) {
        if (locadora.totalLocacoes >= 10) {
         System.out.println("Limite atingido!");
            return;
        }
        if (locadora.clientes.isEmpty() || locadora.veiculos.isEmpty()) {
         System.out.println("Ta faltando dados ai pra gente poder fazer o cadastro");
            return;
    }
        System.out.print("Indice cliente.. digite 0 depois 1 1(0, 1...): "); int ic = s.nextInt();
         System.out.print("Indice Veiculo (0, 1...): "); int iv = s.nextInt();
        
        Locacao l = new Locacao(locadora.clientes.get(ic), locadora.veiculos.get(iv), 
        LocalDate.now(), LocalDate.now().plusDays(3));
        locadora.locacoes[locadora.totalLocacoes] = l;
        locadora.totalLocacoes++;
    }

    public static void realizarDevolucao(Scanner s) {
        System.out.print("Indice da Locacao: ");
        int id = s.nextInt();
        if (id >= 0 && id < locadora.totalLocacoes) {
            locadora.locacoes[id].devolucaoRealizada = true;
            System.out.println("Devolvido!");
        }
    }
    public static void listarAtivas() {
        for (int i = 0; i < locadora.totalLocacoes; i++) {
        if (locadora.locacoes[i].devolucaoRealizada == false) {
            locadora.locacoes[i].visualizarLocacao();
        
        }
        
        }
    }
   
 public static void demonstracao() {

        Scanner s= new Scanner(System.in);
        Cliente c1 = new Cliente(); 
        System.out.print("Me diga seu nome (cliente 1): "); c1.nome = s.next();
        System.out.print("Qual CPF do senhor/a? "); c1.cpf = s.next();
        c1.cnh = "111";
        locadora.clientes.add(c1);

        Cliente c2 =new Cliente(); 
        System.out.print("Me diga seu nome (cliente 2): "); c2.nome = s.next();
        System.out.print("Qual CPF do senhor/a? "); c2.cpf = s.next();
        c2.cnh = "222";
        locadora.clientes.add(c2);
        Carro car =new Carro(); 
        System.out.print("Placa do carro: "); car.placa = s.next();
        System.out.print("Diaria do carro: "); car.diaria= s.nextDouble();
        car.temAr=true;
        locadora.veiculos.add(car);

        Moto mot = new Moto(); 
        System.out.print("Placa da moto: ");mot.placa = s.next();
        System.out.print("Diaria da moto: "); mot.diaria = s.nextDouble();
        mot.cilindrada = 250;
        locadora.veiculos.add(mot);
        Locacao l1 = new Locacao(c1, car, LocalDate.now().minusDays(5), LocalDate.now());
        l1.devolucaoRealizada = true;

        Locacao l2 = new Locacao(c2, mot, LocalDate.now(), LocalDate.now().plusDays(2));
        locadora.locacoes[locadora.totalLocacoes] = l1;
        locadora.totalLocacoes++;
        locadora.locacoes[locadora.totalLocacoes] = l2;
        locadora.totalLocacoes++;

        System.out.println("Mostrando o que ficou aberto");
        listarAtivas();
    }
}