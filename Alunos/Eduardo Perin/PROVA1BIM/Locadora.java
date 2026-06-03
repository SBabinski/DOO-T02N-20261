import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Locadora{
    static Scanner scan = new Scanner(System.in);
    static ArrayList<Cliente>clientes = new ArrayList<>();
    static ArrayList<Carro>carros = new ArrayList<>();
    static ArrayList<Moto>motos = new ArrayList<>();
    static ArrayList<Locacao>locacoes = new ArrayList<>();
    
    public static void main(String[] args) {
        menu();
    }
    public static void menu(){
        int escolha = 0;
        do{
            System.out.println("===MENU===");
            System.out.println("[1]-CADASTRAR CLIENTE");
            System.out.println("[2]-CADASTRAR VEICULO");
            System.out.println("[3]-CADASTRAR LOCACAO");
            System.out.println("[4]-REALIZAR DEVOLUCAO");
            System.out.println("[5]-LISTAR LOCACOES EM ABERTO");
            System.out.println("[6]-DEMONSTRACAO");
            System.out.println("[7]-SAIR");
            escolha = scan.nextInt();
            scan.nextLine();
            validarEscolha(escolha);
        } while (escolha!=7);
    }
    public static void validarEscolha(int escolha){
        switch (escolha) {
            case 1:
                cadastrarCliente();
                break;
            case 2:
                System.out.println("QUAL TIPO DE VEICULO DESEJA CADASTRAR?");
                System.out.println("[1]-CARRO");
                System.out.println("[2]-MOTO");
                int escolhinha = scan.nextInt();
                scan.nextLine();
                if(escolhinha==1){
                    cadastrarCarro();
                }
                else if(escolhinha==2){
                    cadastrarMoto();
                }
                break;
            case 3:
                cadastrarLocacao();
                break;
            case 4:
                realizarDevolucao();
                break;
            case 5:
                mostrarPendentes();
                break;
            case 6:
                demonstracao();
                break;
            default:
                if (escolha!=7) {
                    System.out.println("DIGITE UMA OPCAO VALIDA");
                }
                break;
        }
    }
    public static void cadastrarCliente(){
        System.out.println("DIGITE O NOME:");
        String nome = scan.nextLine();
        System.out.println("DIGITE O CPF:");
        String cpf = scan.nextLine();
        System.out.println("DIGITE A CNH:");
        String cnh = scan.nextLine();
        Cliente cliente = new Cliente(nome, cpf, cnh);
        clientes.add(cliente);
    }
    public static void cadastrarCarro(){
        System.out.println("DIGITE A PLACA: ");
        String placa = scan.nextLine();
        System.out.println("DIGITE O VALOR DA DIARIA: ");
        Double valorDiaria = scan.nextDouble();
        scan.nextLine();
        System.out.println("O CARRO TEM AR? \n [1]-SIM \n [0]-NAO"); 
        int escolha = scan.nextInt();
        Boolean temAr = null;
        if(escolha==1){temAr = true;}
        else {temAr = false;}
        Carro carro = new Carro(placa, valorDiaria, temAr);
        carros.add(carro);
    }
    public static void cadastrarMoto(){
        System.out.println("DIGITE A PLACA: ");
        String placa = scan.nextLine();
        System.out.println("DIGITE O VALOR DA DIARIA: ");
        Double valorDiaria = scan.nextDouble();
        scan.nextLine();
        System.out.println("DIGITE A QUANTIDADE DE CILINDRADAS: ");
        String cilindradas = scan.nextLine();
        Moto moto = new Moto(placa, valorDiaria, cilindradas);
        motos.add(moto);
    }
    public static void cadastrarLocacao(){
        if((carros.size()==0 && motos.size()==0) || clientes.size()==0){
            System.out.println("NAO TEMOS VEICULOS OU CLIENTES SUFICIENTES PARA REALIZAR UMA LOCACAO");
            return;
        }
        if(locacoes.size()>=10){
            System.out.println("LIMITE DE 10 LOCACOES ATINGIDAS");
            return;
        }

        int index=0;
        System.out.println("ESCOLHA UM CLIENTE: ");
        for(int i = 0;i<clientes.size();i++){
            System.out.printf("["+i+"] - ");
            clientes.get(i).exibirInfo();
        }
        do{
            index = scan.nextInt();
            scan.nextLine();
        }while(index>clientes.size()-1);
        Cliente cliente = clientes.get(index);

        System.out.println("QUANTOS DIAS DE LOCACAO?");
        int dias = scan.nextInt();
        scan.nextLine();
        int escolhinha = 0;

        do{
            System.out.println("QUAL TIPO DE VEICULO DESEJA ADICIONAR?");
            System.out.println("[1]-CARRO");
            System.out.println("[2]-MOTO");
            escolhinha = scan.nextInt();
            scan.nextLine();
        }while(escolhinha!=1 && escolhinha!=2);
            if(escolhinha==1){
            for (int i = 0;i<carros.size();i++) {
                System.out.printf("["+i+"] - ");
                carros.get(i).exibirInfo();
            }
            do{
                index = scan.nextInt();
                scan.nextLine();
            }while(index>carros.size()-1);
                Carro carro = carros.get(index);
            Locacao locacao = new Locacao(cliente, carro, LocalDate.now(), LocalDate.now().plusDays(dias), null);
            locacoes.add(locacao);
        }
        else if(escolhinha==2){
            for (int i = 0;i<motos.size();i++) {
                System.out.printf("["+i+"] - ");
                motos.get(i).exibirInfo();
            }
            do{
                index = scan.nextInt();
                scan.nextLine();
            }while(index>motos.size()-1);
            Moto moto = motos.get(index);
            Locacao locacao = new Locacao(cliente, moto, LocalDate.now(), LocalDate.now().plusDays(dias), null);
            locacoes.add(locacao);
        }
    }
    public static void realizarDevolucao(){
        for (int i = 0; i < locacoes.size(); i++) {
            if(locacoes.get(i).getSituacaoDevolucao()==true){
                locacoes.remove(locacoes.get(i));
            }
        }
        if(locacoes.size()==0){
            System.out.println("NAO EXISTE NENHUMA LOCACAO EM ABERTO");
            return;
        }
        System.out.println("QUAL LOCACAO DESEJA DEVOLVER: ");
        for (int i = 0; i < locacoes.size(); i++) {
            System.out.println("["+i+"] - ");
            locacoes.get(i).exibirInfo();
        }
        int escolha = 0;
        do{
            escolha = scan.nextInt();
            scan.nextLine();
        }while(escolha>locacoes.size()-1);
        locacoes.get(escolha).setSituacaoDevolucao(true);
        locacoes.remove(escolha);
    }
    public static void mostrarPendentes(){
        for (int i = 0; i < locacoes.size(); i++) {
            if(locacoes.get(i).getSituacaoDevolucao()==true){
                locacoes.remove(locacoes.get(i));
            }
        }
        if(locacoes.size()==0){
            System.out.println("NAO EXISTE NENHUMA LOCACAO EM ABERTO");
            return;
        }
        for (Locacao locacao : locacoes) {
            locacao.exibirInfo();
        }
    }
    public static void demonstracao(){

    Carro carro2 = new Carro("ABC1D23", 200, true);
    carros.add(carro2);

    Moto moto1 = new Moto("MNO2P34", 110, "300cc");
    motos.add(moto1);
    
    Cliente cliente2 = new Cliente("Ana Oliveira", "98765432100", "987654321");
    Cliente cliente3 = new Cliente("Bruno Lima", "11122233344", "111222333");

    Locacao locacao2 = new Locacao(cliente2, carro2, LocalDate.now().minusDays(3), LocalDate.now().plusDays(7), null);
    Locacao locacao3 = new Locacao(cliente3, moto1, LocalDate.now().minusDays(1), LocalDate.now().plusDays(4), true);

    clientes.add(cliente2);
    clientes.add(cliente3);

    locacoes.add(locacao2);
    locacoes.add(locacao3);

    mostrarPendentes();
    }
}