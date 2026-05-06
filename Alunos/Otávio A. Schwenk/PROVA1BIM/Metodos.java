import Objetos.Cliente;
import Objetos.Carro;
import Objetos.Moto;
import Objetos.Veiculo;
import Objetos.Locacao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Metodos {

    static DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static Scanner scan = new Scanner(System.in);
    public static List<Cliente> clientes = new ArrayList<>();
    public static List<Veiculo> veiculos = new ArrayList<>();
    public static List<Carro> carros = new ArrayList<>();
    public static List<Moto> motos = new ArrayList<>();
    public static List<Locacao> locacoesAtivas = new ArrayList<>(10);
    public static List<Locacao> locacoesDevolvidas = new ArrayList<>();

    public static void listarCliente(){
        System.out.println("Clientes:");
        for(int i =0; i < clientes.size(); i++){
            System.out.printf("%d - %s - CPF: %s\n", i+1, clientes.get(i).getNome(), clientes.get(i).getCpf());
        }
        System.out.println("");
    }

    public static void listarAtivas(){
        System.out.println("Ativas:");
        for(int i =0; i < locacoesAtivas.size(); i++){
            locacoesAtivas.get(i).mostrarLocacao();
        }
        System.out.println("");
    } 

    public static void listarVeiculos(){
        System.out.println("Veiculos:");
        for(int i =0; i < veiculos.size(); i++){
            System.out.printf("%d - ", i+1);
            veiculos.get(i).apresentarSe();
        }
        System.out.println("");
    }
    
    public static void cadastrarCliente(){
        String nome;
        String cpf;
        String numeroCnh;

        System.out.println("Insira o nome do cliente:");
        nome = scan.nextLine();
        System.out.println("Insira o CPF:");
        cpf = scan.nextLine();
        System.out.println("Insira o número da CNH:");
        numeroCnh = scan.nextLine();

        Cliente cliente = new Cliente(nome, cpf, numeroCnh);
        clientes.add(cliente);

        System.out.println("Cliente cadastrado com sucesso!");
    }

    public static void cadastrarCarro(){
        int resposta = 0;
        String placa;
        double valorDiaria = 0;
        boolean arCondicionado;

        System.out.println("Insira a placa do carro:");
        placa = scan.nextLine();
        System.out.println("Insira o valor da diária:");
        valorDiaria = scan.nextDouble();
        do{
            System.out.println("Veículo possui ar-condicionado?");
            System.out.println("[1] - SIM");
            System.out.println("[2] - NÃO");
            if(resposta != 1 && resposta != 2){
                System.out.println("ERRO: Escolha uma opcao valida!");
            }
        }while(resposta != 1 && resposta != 2);
        if(resposta == 1){
            arCondicionado = true;
        }else{
            arCondicionado = false;
        }

        Carro carro = new Carro(placa, valorDiaria, arCondicionado);
        carros.add(carro);
        veiculos.add(carro);

        System.out.println("Carro cadastrado com sucesso!");
    }

    public static void cadastrarMoto(){
        String placa;
        double valorDiaria = 0;
        int cilindradas = 0;

        System.out.println("Insira a placa da moto:");
        placa = scan.nextLine();
        System.out.println("Insira o valor da diária:");
        valorDiaria = scan.nextDouble();
        System.out.println("Insira a Cilindrada da moto:");
        cilindradas = scan.nextInt();

        Moto moto = new Moto(placa, valorDiaria, cilindradas);
        motos.add(moto);
        veiculos.add(moto);

        System.out.println("Moto cadastrada com sucesso!");
    }

    public static void realizarLocação(){
        if(locacoesAtivas.size() >= 10){
            System.out.println("Máximo de locações atingido!");
            return;
        }
        int escolha = 0;
        Cliente cliente = new Cliente();
        Veiculo veiculo = new Veiculo();
        LocalDate dataRetirada;

        do{
            System.out.println("Escolha o Cliente que deseja alocar:");
            listarCliente();
            escolha = scan.nextInt()-1;
        }while(escolha > clientes.size() || escolha < 0);
        cliente = clientes.get(escolha);
        do{
            System.out.println("Escolha o Veículo que deseja alocar:");
            listarVeiculos();
            escolha = scan.nextInt()-1;
        }while(escolha > veiculos.size() || escolha < 0);
        veiculo = veiculos.get(escolha);
        dataRetirada = LocalDate.now();

        Locacao novaLocacao = new Locacao(cliente, veiculo, dataRetirada, null, false);
        locacoesAtivas.add(novaLocacao);
        System.out.println("Locação realizada com sucesso!");
    }

    public static void realizarDevolucao(){
        System.out.println("");
    }

    public static void listarLocacoesSemDevolucao(){
        System.out.println("Não devolvidos:");
        for(int i =0; i < locacoesAtivas.size(); i++){
            System.out.printf("%d - ", i+1);
            locacoesAtivas.get(i).mostrarLocacao();
        }
        System.out.println("");
    }

    public static void testar(){

        String dataUm = "10/10/2025";
        String dataDois = "16/09/2024";
        String dataTres = "01/04/2026";

        LocalDate primeiraData = LocalDate.parse(dataUm, formatador);
        LocalDate segundaData = LocalDate.parse(dataDois, formatador);
        LocalDate terceiraData = LocalDate.parse(dataTres, formatador);

        Cliente clienteUm = new Cliente("Otavio", "78945612312", "12345678");
        Cliente clienteDois = new Cliente("Carlos", "456789123978", "56123789");
        clientes.add(clienteDois);
        clientes.add(clienteUm);

        Moto moto = new Moto("abc1234", 45.30, 150);
        Carro carro = new Carro("efg7894", 89.20, false);
        Locacao locacaoUm = new Locacao(clienteUm, moto, primeiraData, segundaData, true);
        Locacao locacaoDois = new Locacao(clienteDois, carro, primeiraData, terceiraData, false);

        locacoesAtivas.add(locacaoDois);
        locacoesDevolvidas.add(locacaoUm);

        listarAtivas();
    }
    
}
