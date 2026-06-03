import java.util.Scanner;

public class Menu {

    private Scanner scan;
    private int opcao;
    Locadora locadora = new Locadora();

    public Menu(){
        this.scan = new Scanner(System.in);
    }
    
    public void chamarMenu(){
        System.out.println("Seja Bem-vindo a biblioteca!");
        
        do{
            System.out.println(" === Escolha a opção desejada === ");
            System.out.println("Para cadastrar um cliente, digite [1]");
            System.out.println("Para cadastrar um veículo, digite [2]");
            System.out.println("Para cadastrar uma locação, digite [3]");
            System.out.println("Para realizar uma devolução,  digite [4]");
            System.out.println("Para gerar um demostrativo, digite [5]");
            System.out.println("Para exibir a locações, digite [6]");
            System.out.println("Para SAIR, digite [7]");
            opcao = scan.nextInt();
            scan.nextLine();
            validarOpcao(opcao);

        }while(opcao != 7);

        fecharScanner();
    }

    public void validarOpcao(int opcao){
        switch(opcao){
            case 1:
                System.out.println("Para cadastrar um cliente novo, precisamos de algumas informações.");
                System.out.println("Digite o nome do novo cliente:");
                String nomeCliente = scan.nextLine();
                System.out.println("Agora digite o CPF do cliente:");
                String cpfCliente = scan.nextLine();
                System.out.println("Por fim, digite o número da CNH do cliente");
                String cnhlCliente = scan.nextLine();
                locadora.cadastrarCliente(nomeCliente, cpfCliente, cnhlCliente);
                System.out.println("Cliente cadastrado com sucesso!");
                break;

            case 2:
                System.out.println("Para cadastrar um veículo, digite o número da placa:");
                String placa = scan.nextLine();
                System.out.println("Digite o valor da diária");
                double valorDiaria = scan.nextDouble();
                System.out.println("Selecione o tipo de veículo a ser cadastrado:");
                System.out.println("Digite [1] para CARRO");
                System.out.println("Digite [2] para MOTO");
                int item = scan.nextInt();
                scan.nextLine();
                if (item == 1) {
                    System.out.println("O carro tem ar condicionado? Sim ou não.");
                    String ar = scan.nextLine();
                    locadora.cadastrarCarro(placa, valorDiaria, ar);
                    System.out.println("Carro cadastrado com sucesso!");
                }
                else if (item == 2) {
                    scan.nextLine();
                    System.out.println("Informe a cilindrada do motor:");
                    String cilindrada = scan.nextLine();
                    locadora.cadastrarMoto(placa, valorDiaria, cilindrada);
                    System.out.println("Moto cadastrada com sucesso!");
                } else {
                    System.out.println("opção INVALIDA! Operação cancelada.");
                    return;
                }
                break;
            
            case 3:
                System.out.println("Para cadastrar uma locação, selecione um cliente");
                locadora.apresentarCliente();
                int tamanhoLista = locadora.retornarListaTam();
                if(tamanhoLista == 0){
                    System.out.println("Lista de intens vazia!");
                    return;
                }
                int opcaoCliente = scan.nextInt();
                System.out.println("Agora selecione o veículo a ser emprestado:");
                locadora.apresentarVeiculos();
                int opcaoVeiculo = scan.nextInt();
                System.out.println("Digite a data da locação (retirada):");
                System.out.println("Digite primeiro dia (enter), depois mês (enter) e por fim o ano (enter)");
                int dia = scan.nextInt();
                int mes = scan.nextInt();
                int ano = scan.nextInt();
                System.out.println("Agora informe a data de devolução:");
                int diaDevolucao = scan.nextInt();
                int mesDevolucao = scan.nextInt();
                int anoDevolucao = scan.nextInt();
                locadora.cadastrarLocacao(opcaoCliente, opcaoVeiculo, ano, mes, dia, anoDevolucao, mesDevolucao, diaDevolucao);
                break;
            
            case 4:
                System.out.println("Para realizar uma devolução, selecione uma locação:");
                locadora.apresentarLocacao();
                int tamanhoListaEmprestimos = locadora.retornarLocacaoTam();
                if(tamanhoListaEmprestimos == 0){
                    System.out.println("Lista de locações vazia vazia!");
                    return;
                }
                int opcaoEmprestimo = scan.nextInt();
                locadora.removerLocacao(opcaoEmprestimo);
                break;

            case 5:
                locadora.gerarDemonstrativo();
                break;


            case 6:
                locadora.apresentarLocacao();
                break;

            case 7:
                System.out.println("Obrigado por usar o sistema!");

            default:
                System.out.println("Selecione um opção válida!");
                break;
        }
            
    }

    private void fecharScanner() {
        scan.close();
    }
}
