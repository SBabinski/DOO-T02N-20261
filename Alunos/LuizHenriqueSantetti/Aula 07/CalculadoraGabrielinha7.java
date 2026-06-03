import java.util.Scanner;

public class CalculadoraGabrielinha7 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Endereco end = new Endereco("PR","Cascavel","Centro","123","casa");

        Cliente c1 = new Cliente("Carlos",28,end);
        Vendedor v1 = new Vendedor("Luiz",25,end,"My Plant",2000);

        int op = 0;

        while(op != 2){

            System.out.println("1 criar pedido");
            System.out.println("2 sair");

            op = sc.nextInt();

            if(op == 1){

                ProcessaPedido proc = new ProcessaPedido();

                Pedido p = proc.processar(c1,v1);

                p.gerarDescricaoVenda();
            }
        }
    }
}