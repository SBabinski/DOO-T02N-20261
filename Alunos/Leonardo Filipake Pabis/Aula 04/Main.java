
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner scan = new Scanner(System.in);
    static int opcao = 1000;
    static ArrayList <Vendas> vendas = new ArrayList<>();
    static double valor_de_venda_total = 0;
    public static void main(String[] args){
            Menu();

    }
    
    public static void Menu(){
        System.out.println("********");
        System.out.println("* MENU *");
        System.out.println("********");
        System.out.println("1 - Cálculo de preço total");
        System.out.println("2 - Cálculo de troco");
        System.out.println("3 - Consultar vendas");
        System.out.println("4 - Alocar vendas totais");
        System.out.println("5 - Buscar vendas do dia");
        System.out.println("6 - Sair");
        
        do{
            System.out.println("Digite uma opção válida");
            opcao = scan.nextInt();
            scan.nextLine();
            if (opcao == 1){
                CalculoPrecoTotal();
            }else if (opcao == 2){
                CalculoDeTroco();
            }else if (opcao == 3){
                ConsultarVendas();
            }else if (opcao == 4){
                Date.EscolherDia(valor_de_venda_total);
            }else if (opcao == 5){
                Date.BuscarVendaDia();
            }
            else if (opcao == 6){
                Sair();
            }
        }while (opcao != 6);

    }
    public static void CalculoPrecoTotal(){
        Vendas venda = new Vendas();
        System.out.println("Digite a quantidade da planta vendida:");
        int quantidade = scan.nextInt();
        scan.nextLine();
        venda.setQuantidade(quantidade);
        System.out.println("Digite o preço unitário da planta:");
        double preco = scan.nextDouble();
        scan.nextLine();
        double preco_total = preco * quantidade;
        venda.setPreco(preco_total);
        if (quantidade > 10){
            double valorDesconto = preco_total * 0.05;
            preco_total -= valorDesconto;
            venda.setvalorDescontoRecebido(valorDesconto);
        }
        System.out.printf("Preço Total: %.2f\n", preco_total);
        System.out.println("Venda cadastrada no sistema\n");
        vendas.add(venda);
        valor_de_venda_total += preco_total;
        VoltarMenu();
    }
    public static void CalculoDeTroco(){
        System.out.println("Valor recebido pelo cliente:");
        double valor = scan.nextDouble();
        scan.nextLine();
        System.out.println("Valor total da compra:");
        double total = scan.nextDouble();
        scan.nextLine();
        if (valor-total < 0){
            System.out.println("Valor insuficiênte, tente novamente");
            CalculoDeTroco();
        }else{
            System.out.printf("Troco a ser devolvido: %.2f\n", valor-total);
            VoltarMenu();
        }
    }

    public static void VoltarMenu(){
        System.out.println("Pressione enter para voltar ao menu");
        scan.nextLine();
        Menu();
    }

    public static void ConsultarVendas(){
        System.out.println("Número da venda - quantidade - preço total - desconto recebido - preço descontado\n");
        for (int i = 0; i < vendas.size(); i++){
            System.out.println(i+1+" - "+vendas.get(i).getQuantidade()+" - "+vendas.get(i).getPreco()+" - "+ vendas.get(i).getvalorDescontoRecebido()+" - "+(vendas.get(i).getPreco()-vendas.get(i).getvalorDescontoRecebido())+"\n");
        }
        System.out.printf("Vendas totais(não alocadas): %.2f\n\n", valor_de_venda_total);
        VoltarMenu();
    }

    public static void Sair(){
        System.out.println("Finalizando o systema");
    }
}
