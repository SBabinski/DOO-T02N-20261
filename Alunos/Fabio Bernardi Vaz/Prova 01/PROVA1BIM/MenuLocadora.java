package fag;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuLocadora {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Locadora locadora = new Locadora("Locadora Fag");

        List<Cliente> clientesCadastrados = new ArrayList<>();
        List<Veiculo> veiculosCadastrados = new ArrayList<>();

        LocadoraService service = new LocadoraService(locadora, scanner, clientesCadastrados, veiculosCadastrados, formatter);

        int opcao = 0;
        do {
            System.out.println("\nLocadora de veículos");
            System.out.println("[1] Cadastrar Cliente");
            System.out.println("[2] Cadastrar Veículo");
            System.out.println("[3] Registrar Locação");
            System.out.println("[4] Realizar Devolução");
            System.out.println("[5] Listar Locações sem Devolução");
            System.out.println("[6] Demonstração do Sistema");
            System.out.println("[0] Sair");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    service.cadastrarCliente();
                    break;
                case 2:
                    service.cadastrarVeiculo();
                    break;
                case 3:
                    service.registrarLocacao();
                    break;
                case 4:
                    service.realizarDevolucao();
                    break;
                case 5:
                    locadora.listarLocacoesSemDevolucao();
                    break;
                case 6:
                    service.demonstracaoSistema();
                    break;
                case 0:
                    System.out.println("Encerrando o sistema. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }

        } while (opcao != 0);
        scanner.close();
    }
}