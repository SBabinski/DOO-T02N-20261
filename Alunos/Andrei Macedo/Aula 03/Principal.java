import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    static Scanner leia = new Scanner(System.in);
    static List<Vendas> historico = new ArrayList<>();

    public static void main(String[] args) {
        exibir_menu();
        leia.close();
    }

    public static void exibir_menu(){
        int op = 0;
        do{
            System.out.println(" === Digite a opção desejada: === ");
            System.out.println("[1] Para abrir a calculadora.");
            System.out.println("[2] Realizar venda.");
            System.out.println("[3] Exibir historico.");
            System.out.println("[4] Sair.");
            op = leia.nextInt();
            validar_escolha(op);
        }while (op != 4);
    }

    public static void validar_escolha(int op){
        switch (op) {
            case 1:
                calcular_preco();
                break;
            
            case 2:
                realizar_venda();
                break;

            case 3:
                mostrar_historico();
                break;

            case 4:
                System.out.println("Obrigado por utilizar o sistema!");
                break;
        
            default:
                System.out.println("Digite uma opção válida!");
                break;
        }
    }

    public static void mostrar_historico(){
        for (Vendas venda : historico) {
            System.out.println(venda);
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

    public static void realizar_venda(){
        Vendas venda = new Vendas();
        System.out.println("Digite o valor da planta que esta sendo vendida:");
        venda.setValor(leia.nextDouble());
        if (!validar_valor(venda.getValor())) {
            return;
        }
        System.out.println("Digite a quantidade de plantas:");
        venda.setQntd(leia.nextInt());
        if (!validar_qntd(venda.getQntd())) {
            return;
        }
        venda.setValor_total(concluir_venda(venda.getQntd(), venda.getValor()));

        if (venda.getQntd() > 10) {
            System.out.println("Desconto de 5% aplicado.");
            venda.setDesconto(aplicar_desconto(venda.getValor_total()));
            venda.setValor_total(venda.getValor_total() - venda.getDesconto());
        }

        System.out.printf("Valor total da compra %.2f\n", venda.getValor_total());

        if (!calcular_troco(venda.getValor_total())) {
            return;
        }

        venda.setHoras(salvar_hora());

        historico.add(venda);
        System.out.println("Venda realizada com sucesso!");
    }

    public static LocalDateTime salvar_hora(){
        LocalDateTime agora = LocalDateTime.now();
        return agora;
    }

    public static boolean calcular_troco(double total){
        System.out.println("Digite o valor pago pelo cliente:");
        double valor_pago = leia.nextDouble();
        if (valor_pago <= 0 || valor_pago < total) {
            System.err.println("Erro! Saldo insuficiente.");
            return false;
        }

        else {
            double troco = valor_pago - total;
            System.out.printf("Troco: R$ %.2f\n", troco);
            return true;
        }
    }

    public static double aplicar_desconto(double total) {
        double desconto = total * 0.05;
        return desconto;
    }
    
    public static double concluir_venda(int qntd, double valor){
        double valor_total = qntd * valor;
        return valor_total;
    }

    public static boolean validar_valor(double valor){
        if (valor <= 0){
            System.out.println("Não permitido valores igual ou menor que 0.");
            return false;
        }

        else {
            return true;
        }
    }

    public static boolean validar_qntd(int qntd){
        if (qntd <= 0) {
            return false;
        }

        else {
            return true;
        }
    }
}