import java.util.Scanner;

public class Principal {
    static Scanner leia = new Scanner(System.in);

    public static void main(String[] args) {
        exibir_menu();
        leia.close();
    }

    public static void exibir_menu(){
        int op = 0;
        do{
            System.out.println(" === Digite a opção desejada: === ");
            System.out.println("[1] Calcular preço total.");
            System.out.println("[2] Calcular troco.");
            System.out.println("[3] Sair.");
            op = leia.nextInt();
            validar_escolha(op);
        }while (op != 3);
    }

    public static void validar_escolha(int op){
        switch (op) {
            case 1:
                calcular_preco();
                break;
            
            case 2:
                calcular_troco();
                break;

            case 3:
                System.out.println("Obrigado por usar o sistema!");
                break;
        
            default:
                System.out.println("Digite uma opção válida!");
                break;
        }
    }

    public static void calcular_preco(){
        System.out.println("Digite a quantidade de plantas vendidas");
        int quantidade = leia.nextInt();
        System.out.println("Digite o valor da planta vendida");
        double valor = leia.nextDouble();
        double total = quantidade * valor;
        System.out.printf("O valor calculado é de R$ %.2f\n", total);
    }

    public static void calcular_troco(){
        System.out.println("Digite o valor total da compra:");
        double total = leia.nextDouble();
        System.out.println("Digite o valor pago pelo cliente:");
        double pagamento = leia.nextDouble();
        validar_valor(pagamento, total);
        double troco = pagamento - total;
        if (total <= pagamento){
            System.out.printf("Troco R$ %.2f\n", troco);
        }
    }

    public static void validar_valor(double pagamento, double total){
        if (pagamento <= 0){
            System.out.println("Não permitido valores igual ou menor que 0.");
            return;
        }

        else if (pagamento < total){
            System.out.println("Valor insuficiente.");
            return;
        }

        else{
            System.out.println("Calculando troco...");
        }
    }
}

