package fag;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import objetos.Carro;
import objetos.Cliente;
import objetos.Locacao;
import objetos.Moto;
import objetos.Veiculo;


public class Program {

    public static ArrayList<Cliente> lista = new ArrayList<>();
    public static ArrayList<Veiculo> veiculos = new ArrayList<>();
    public static ArrayList<Locacao> locacoes = new ArrayList<>();
    public static Scanner sc = new Scanner(System.in);

    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        exibirMenu();
    }

    public static void exibirMenu() {
        int opcao;

        do {
            System.out.println("\n===== MENU =====");
            System.out.println("[1] Adicionar cliente");
            System.out.println("[2] Adicionar veículo");
            System.out.println("[3] Criar locação");
            System.out.println("[4] Devolução");
            System.out.println("[5] Listar pendentes");
            System.out.println("[6] Demonstração");
            System.out.println("[7] Sair");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {

                case 1:
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();

                    System.out.print("CPF: ");
                    String cpf = sc.nextLine();

                    System.out.print("CNH: ");
                    String cnh = sc.nextLine();

                    lista.add(new Cliente(nome, cpf, cnh));
                    break;

                case 2:
                    System.out.println("1-Carro 2-Moto");
                    int tipo = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Placa: ");
                    String placa = sc.nextLine();

                    System.out.print("Diária: ");
                    double diaria = sc.nextDouble();

                    if (tipo == 1) {
                        System.out.print("Ar (true/false): ");
                        boolean ar = sc.nextBoolean();
                        veiculos.add(new Carro(placa, diaria, ar));
                    } else {
                        System.out.print("Cilindrada: ");
                        int cil = sc.nextInt();
                        veiculos.add(new Moto(placa, diaria, cil));
                    }
                    break;

                case 3:

                    if (locacoes.size() >= 10) {
                        System.out.println("Limite atingido!");
                        break;
                    }

                    if (lista.isEmpty() || veiculos.isEmpty()) {
                        System.out.println("Cadastre antes.");
                        break;
                    }

                    for (int i = 0; i < lista.size(); i++)
                        System.out.println(i + "-" + lista.get(i).getNome());

                    int c = sc.nextInt();

                    for (int i = 0; i < veiculos.size(); i++)
                        System.out.println(i + "-" + veiculos.get(i).getDescricao());

                    int v = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Data retirada (dd/MM/yyyy): ");
                    String dataStr = sc.nextLine();

                    LocalDate data = LocalDate.parse(dataStr, formatter);

                    locacoes.add(new Locacao(lista.get(c), veiculos.get(v), data));

                    System.out.println("OK");
                    break;

                case 4:

                    for (int i = 0; i < locacoes.size(); i++) {
                        if (!locacoes.get(i).isDevolvido())
                            System.out.println(i + "-" + locacoes.get(i).exibirDados());
                    }

                    int l = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Data devolução (dd/MM/yyyy): ");
                    
                    String d = sc.nextLine();

                    LocalDate dataDev = LocalDate.parse(d, formatter);

                    locacoes.get(l).realizarDevolucao(dataDev);
                    System.out.println(locacoes.get(l).exibirDados());
                    break;

                case 5:

                    for (Locacao loc : locacoes) {
                        if (!loc.isDevolvido()) {
                            System.out.println("----------------");
                            System.out.println(loc.exibirDados());
                        }
                    }
                    break;

                case 6:

                    lista.clear();
                    veiculos.clear();
                    locacoes.clear();

                    Cliente c1 = new Cliente("João", "111", "123");
                    Cliente c2 = new Cliente("Maria", "222", "456");

                    lista.add(c1);
                    lista.add(c2);

                    Veiculo carro = new Carro("ABC-1234", 100, true);
                    Veiculo moto = new Moto("XYZ-9999", 50, 300);

                    veiculos.add(carro);
                    veiculos.add(moto);

                    Locacao l1 = new Locacao(c1, carro, LocalDate.of(2024, 1, 1));
                    l1.realizarDevolucao(LocalDate.of(2024, 1, 5));

                    Locacao l2 = new Locacao(c2, moto, LocalDate.of(2024, 2, 1));

                    locacoes.add(l1);
                    locacoes.add(l2);

                    System.out.println("\nATIVAS:");

                    for (Locacao loc : locacoes) {
                        if (!loc.isDevolvido()) {
                            System.out.println("----------------");
                            System.out.println(loc.exibirDados());
                        }
                    }

                    break;
            }

        } while (opcao != 7);
    }
}