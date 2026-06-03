//aula 6
import java.util.Scanner;
import java.time.LocalDate;
import java.util.ArrayList;

public class CalculadoraMenu {
    static Scanner scan = new Scanner(System.in);
    static ArrayList<Historico> historicoVendas = new ArrayList<>();
    static ArrayList<Vendedor> listaDeVendedores = new ArrayList<>();
    static ArrayList<Cliente> listaDeClientes = new ArrayList<>();
    static Loja minhaLoja;
    

    public static void main(String[] args) {
        criaClasses();
        Menu();
        scan.close();
    }

    public static void ValidarResp(int resp) {
        if (resp == 1) {
            PreçoTotal();
        } else if (resp == 2) {
            CalcularTroco();
        } else if (resp == 3) {
            exibirHistorico();
        }else if (resp == 4) {
            sobreLoja();
        }
    }

    public static void validarLoja(int respL){
        if (respL == 1) {
            System.out.println("-------Lista-de-funcionarios-------");
            for(int x = 0; x < 3; x++){
                Vendedor v = listaDeVendedores.get(x);
                v.apresentarse();
                v.calcularMedia();
                v.calcularBonus();
            System.out.println("-----------------------------------");
            }
        } else if (respL == 2) {
            System.out.println("-------Lista-de-Clientes-------");
            for(int x = 0; x < listaDeClientes.size(); x++){
                Cliente c = listaDeClientes.get(x);
                c.apresentarse();
            }
        } else if (respL == 3) {
            System.out.println("-------Informações-da-Loja-------");
            minhaLoja.apresentarse();
            minhaLoja.contarClientes();
            minhaLoja.contarVendedores();
            System.out.println("----------------------------------");
        }    
    }

    public static void sobreLoja(){
        int respL = 1000;
        do{
            System.out.println("-----------Sobre-a-Loja-----------");
            System.out.println("[1] - Funcionarios");
            System.out.println("[2] - Clientes");
            System.out.println("[3] - Loja");
            System.out.println("[0] - Sair");
            respL = scan.nextInt();
            scan.nextLine();
            validarLoja(respL);
        }while(respL != 0);
    }

    public static void Menu() {
        int resp = 1000;
        do {
            System.out.println("-----------Menu-----------");
            System.out.println("[1] - Calcular Preço Total");
            System.out.println("[2] - Calcular Troco");
            System.out.println("[3] - Exibir Historico");
            System.out.println("[4] - Sobre a Loja");
            System.out.println("[0] - Sair");
            resp = scan.nextInt();
            scan.nextLine();
            ValidarResp(resp);
        } while (resp != 0);
        System.out.println("Obrigado por utilizar a calculadora!!!");
    }

    
    public static void exibirHistorico() {
        double totalVendasDia = 0;
        System.out.println("-----Historico-de-Vendas-----");
        

        if(historicoVendas.isEmpty()){
            System.out.println("nenhuma venda registrada");
        }else{
            System.out.println("insira o dia da venda:");
            int diaH = scan.nextInt();
            scan.nextLine();
            System.out.println("insira o mes da venda:");
            int mesH = scan.nextInt();
            scan.nextLine();
            for(Historico novaVenda : historicoVendas){
                if(novaVenda.hoje.getDayOfMonth() == diaH && novaVenda.hoje.getMonthValue() == mesH){
                    System.out.printf("Quantidade: %d, Total: %.2f, Desconto: %.2f%n", novaVenda.quantPlant, novaVenda.resultTotal, novaVenda.valorDesc);
                    if(novaVenda.valorDesc == 0){
                        totalVendasDia = totalVendasDia + novaVenda.resultTotal;
                    }else{
                        totalVendasDia = totalVendasDia + novaVenda.valorDesc;
                    } 
                    
                }                
            }
            System.out.println("total das vendas desse dia: " +totalVendasDia);
         }
    }

    public static void PreçoTotal() {
        

        System.out.println("-----Calcular-Preço-Total-----");
        System.out.println("Adicione a quantidade de plantas vendidas");
        int quantPlant = scan.nextInt();
        scan.nextLine();
        System.out.println("Adicione o preco da plantas vendida");
        double precoPlant = scan.nextDouble();
        scan.nextLine();
        double resultTotal = quantPlant * precoPlant;
        double valorDesc;
        LocalDate hoje = LocalDate.now();
        if(quantPlant < 10){
            System.out.printf("O preco total da venda sera %.2f%n", resultTotal);
            valorDesc = 0;
        }else{
            valorDesc = resultTotal * 0.05;
            valorDesc = resultTotal - valorDesc;
            System.out.printf("O original da venda seria %.2f%n", resultTotal);
            System.out.printf("Com desconto fica %.2f%n", valorDesc);
        }

        registrarVenda(quantPlant, resultTotal, valorDesc, hoje);
    }

    public static void CalcularTroco() {

        System.out.println("-----Calcular-Troco-----");
        System.out.println("O preco que o cliente pagou");
        double precoCliente = scan.nextDouble();
        scan.nextLine();
        System.out.println("adicione o total da venda");
        double precoTotal = scan.nextDouble();
        scan.nextLine();

        double troco = precoCliente - precoTotal;
        if (troco > 0) {
            System.out.printf("Voce deve %.2f de troco!%n", troco);
        } else if (troco < 0) {
            System.out.printf("O cliente ainda deve %.2f%n", troco);
        } else if (troco == 0) {
            System.out.println("Ninguem deve nada!!!");
        }
    }

    public static void criaClasses(){
        Vendedor v1 = new Vendedor("José", 30, "My Plant", "Cascavel", "Centro", "Rua Paraná", 2000.0, new double[]{1500.0, 1800.0, 2100.0});
        Vendedor v2 = new Vendedor("Maria",19,"My Plant", "Cascavel", "centro", "Rua B", 1800.0, new double[]{1500.0, 1800.0, 2100.0});
        Vendedor v3 = new Vendedor("Mauro", 60, "My Plant", "Capitão", "Centro", "Rua C", 2000.0, new double[]{1500.0, 1800.0, 2100.0});
        Vendedor[] listaVendedores = new Vendedor[]{v1, v2, v3};

        listaDeVendedores.add(v1);
        listaDeVendedores.add(v2);
        listaDeVendedores.add(v3);
        //----------------------------
        Cliente c1 = new Cliente("Carlos", 19, "Cascavel", "bairro 1", "Rua 1");
        Cliente c2 = new Cliente("Gabriela", 18, "Cascavel", "Bairro 2", "Rua 2");
        Cliente c3 = new Cliente("Adriani", 38, "Cascavel", "Bairro 1", "Rua 2");
        Cliente[] listaClientes = new Cliente[]{c1, c2, c3};

        listaDeClientes.add(c1);
        listaDeClientes.add(c2);
        listaDeClientes.add(c3);
        //----------------------------
        minhaLoja = new Loja("My Plant", "Flores LTDA", "00.000/0001-00", "Cascavel", "Centro", "Rua C", listaVendedores, listaClientes);
    }

    public static void registrarVenda(int quantPlant, double resultTotal, double valorDesc, LocalDate hoje) {
        Historico novaVenda = new Historico(quantPlant, resultTotal, valorDesc, hoje);
        historicoVendas.add(novaVenda);
    }
}