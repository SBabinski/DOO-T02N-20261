import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        int opcao = 100;
        do{
            System.out.println("1. Registrar Cliente");
            System.out.println("2. Registrar Veiculo");
            System.out.println("3. Registrar Locação");
            System.out.println("4. Registrar Devolução");
            System.out.println("5. Listar locações sem devolução");
            System.out.println("6. Demonstração");
            System.out.println("0. Sair");
            opcao = scan.nextInt();


            switch(opcao){
                case 1:
                    Cliente.cadastroCliente(scan);
                    break;
                case 2:
                    Veiculo.cadastroVeiculo(scan);
                    break;
                case 3:
                    Locacao.cadastroLocacao(scan);
                    break;
                case 4:
                    Devolucao.realizarDevolucao(scan);
                    break;
                case 5:
                    Locacao.listarLocacoes();
                    break;
                case 6:
                    Demonstracao.demonstracao();
                    break;
                case 7:
                    break;


            }


        }while(opcao < 7);
    }
}