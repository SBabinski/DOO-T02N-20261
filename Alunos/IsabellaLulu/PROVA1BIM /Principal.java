import java.util.*;

public class Principal {

    static Scanner sc = new Scanner(System.in);

    static ArrayList<Cliente> clientes = new ArrayList<>();
    static ArrayList<Veiculos> veiculos = new ArrayList<>();
    static Locadora locadora = new Locadora();

    public static void main(String[] args) {


        Cliente c1 = new Cliente("Ana", "111", "CNH1");
        Cliente c2 = new Cliente("Carlos", "222", "CNH2");

        Carro carro = new Carro("ABC-1234", 100, true);
        Moto moto = new Moto("XYZ-9999", 50, 300);

        Locacao l1 = new Locacao(c1, carro, 3);
        l1.devolver();

        Locacao l2 = new Locacao(c2, moto, 2);

        locadora.adicionarLocacao(l1);
        locadora.adicionarLocacao(l2);

        locadora.listarAtivas();

        mostrarMenu();
    }

    public static void mostrarMenu() {
        int op;

        do {
            System.out.println("\n=== LOCADORA ===");
            System.out.println("[1] Cliente");
            System.out.println("[2] Veículo");
            System.out.println("[3] Locação");
            System.out.println("[4] Devolução");
            System.out.println("[5] Listar Ativas");
            System.out.println("[0] Sair");

            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1 -> cadastrarCliente();
                case 2 -> cadastrarVeiculo();
                case 3 -> cadastrarLocacao();
                case 4 -> devolver();
                case 5 -> locadora.listarAtivas();
            }

        } while (op != 0);
    }

    // CLIENTE
    public static void cadastrarCliente() {
        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("CPF: ");
        String cpf = sc.nextLine();

        System.out.print("CNH: ");
        String cnh = sc.nextLine();

        clientes.add(new Cliente(nome, cpf, cnh));
    }

    // VEICULO
    public static void cadastrarVeiculo() {
        System.out.println("[1] Carro | [2] Moto");
        int tipo = sc.nextInt();
        sc.nextLine();

        System.out.print("Placa: ");
        String placa = sc.nextLine();

        System.out.print("Valor: ");
        double valor = sc.nextDouble();

        if (tipo == 1) {
            System.out.print("Tem ar (true/false): ");
            boolean ar = sc.nextBoolean();
            veiculos.add(new Carro(placa, valor, ar));
        } else {
            System.out.print("Cilindrada: ");
            int cil = sc.nextInt();
            veiculos.add(new Moto(placa, valor, cil));
        }
    }

    // LOCACAO
    public static void cadastrarLocacao() {

        if (clientes.isEmpty() || veiculos.isEmpty()) {
            System.out.println("Cadastre cliente e veículo primeiro!");
            return;
        }

        for (int i = 0; i < clientes.size(); i++) {
            System.out.print(i + " - ");
            clientes.get(i).mostrar();
        }

        System.out.print("Escolha cliente: ");
        int c = sc.nextInt();

        for (int i = 0; i < veiculos.size(); i++) {
            System.out.print(i + " - ");
            veiculos.get(i).mostrar();
        }

        System.out.print("Escolha veículo: ");
        int v = sc.nextInt();

        System.out.print("Dias: ");
        int dias = sc.nextInt();

        Locacao l = new Locacao(clientes.get(c), veiculos.get(v), dias);
        locadora.adicionarLocacao(l);
    }

    // DEVOLUÇÃO
    public static void devolver() {

        locadora.listarAtivas();

        System.out.print("\nEscolha índice: ");
        int i = sc.nextInt();

        locadora.devolverLocacao(i);
    }
}