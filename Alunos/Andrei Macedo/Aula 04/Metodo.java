import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Metodo {

    static List<Vendas> historico = new ArrayList<>();

    public static void mostrar_historico(){
        for (Vendas venda : historico) {
            System.out.println(venda);
        }
    }

    public static void realizar_venda(Scanner leia){
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

        if (!calcular_troco(venda.getValor_total(), leia)) {
            return;
        }

        venda.setHoras(salvar_hora(leia));

        historico.add(venda);
        System.out.println("Venda realizada com sucesso!");
    }

    public static LocalDate salvar_hora(Scanner leia){
        System.out.println("Insira a data da compra:");
        System.out.println("Insira o dia:");
        int dia = leia.nextInt();
        System.out.println("Insira o mês:");
        int mes = leia.nextInt();
        System.out.println("Insira o ano:");
        int ano = leia.nextInt();
        LocalDate day = LocalDate.of(ano, mes, dia);
        return day;
    }

    public static boolean calcular_troco(double total, Scanner leia){
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

    public static void filtrar_data(Scanner leia) {
        LocalDate day = salvar_hora(leia);
        int cont = 0;
        for (Vendas v : historico) {
            if(v.getHoras().isEqual(day)){
                System.out.println(v);
                cont++;
            }
        }
        if(cont == 0){
            System.out.println("Não existem vendas neste dia.");
        }
    }
}