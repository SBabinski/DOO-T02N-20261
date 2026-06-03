import java.util.ArrayList;
import java.util.Scanner;

class Venda{
    int quantidade;
    double preco;
    double desconto;
    double total;

    public Venda(int quantidade, double preco, double desconto, double total){
        this.quantidade=quantidade;
        this.preco=preco;
        this.desconto=desconto;
        this.total=total;
    }
}

public class CalculadoraLojaAtualizada {
    
    static ArrayList<Venda> vendas = new ArrayList<>();

    public static void main(String[] args){

        Scanner scan = new Scanner(System.in);
        int opcao;

        do{
           
            System.out.println("\n=== Loja da Dona Gabrielinha ===");
            System.out.println("[1] Calcular Preço Total");
            System.out.println("[2] Calcular Troco");
            System.out.println("[3] Ver vendas");
            System.out.println("[4] Sair");
            System.out.print("Escolha uma opção: ");
            
            opcao = scan.nextInt();

            switch(opcao){
                case 1:
                    total(scan);
                    break;
                
                case 2:
                    troco(scan);
                    break;

                case 3:
                    vendas();
                    break;

                case 4:
                    System.out.println("Encerrando programa...");
                    break;
                
                default:
                    System.out.println("Opcao invalida!, tente novamente");
                    break;

            }
        }while(opcao!=4);

        scan.close();
    }

    public static void total(Scanner scan){
        
        System.out.println("Digite a quantidade:");
        int quantidade = scan.nextInt();

        System.out.println("Digite o valor:");
        double preco = scan.nextDouble();

        double total = preco*quantidade;
        double desconto=0;

        if(quantidade>10){
           desconto = total*0.05;
            total=total-desconto;
        }

        System.out.println("Total: R$ " + total);
        
        if(desconto>0){
            System.out.println("Desconto aplicado: R$ "+desconto);
        }

        vendas.add(new Venda(quantidade, preco, total, desconto));
    }
    
    public static void troco(Scanner scan){
        System.out.println("Digite o valor pago: ");
        double pago = scan.nextDouble();

        System.out.println("Digite o valor da compra: ");
        double compra = scan.nextDouble();

        double troco = pago-compra;

        if(troco<0){
            System.out.println("Valor insuficiente, faltam: R$ "+(-troco));
        }
        else{
            System.out.println("Troco: R$ "+troco);
        }
    }

    public static void vendas(){
        if(vendas.isEmpty()){
            System.out.println("Nenhuma venda registrada!");
            return;
        }
        System.out.println("\n=== Histórico de Vendas ===");

        for (int i = 0; i < vendas.size(); i++) {
            Venda v = vendas.get(i);

            System.out.println("Venda " + (i + 1));
            System.out.println("Quantidade: " + v.quantidade);
            System.out.println("Preço unitário: R$ " + v.preco);
            System.out.println("Total final: R$ " + v.total);
            System.out.println("Desconto: R$ " + v.desconto);
            
        }
    }
}