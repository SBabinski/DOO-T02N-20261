import Objetos.Planta;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Metodos {
    
    static List<Planta> registro = new ArrayList<>();
    static Scanner scan = new Scanner(System.in);

    public static void chamarBoasVindas(){
        System.out.println("---------------LOJA DA DONA GABRIELINHA---------------");
        System.out.println("Seja Bem-Vindo a calculadora da loja de plantas!");
        System.out.println("Aqui você pode calcular o valor do troco das plantas!");
        System.out.println("Basta escolher o item desejado do menu!");
    }

    public static void chamarMenu(){
        int escolha = 0;
        do{
            System.out.println("--------------------MENU--------------------");
            System.out.println("[1] - Calcular Preço Total");
            System.out.println("[2] - Calcular Troco ");
            System.out.println("[3] - Registro de Vendas");
            System.out.println("[0] - Sair");

            escolha = scan.nextInt();
            scan.nextLine();
            validarEscolha(escolha);
        }while(escolha!=0);
    }

    public static void validarEscolha(int escolha){
        switch(escolha){
            case 1:
                calcularPrecoTotal();
                break;
            case 2:
                calcularTroco();
                break;
            case 3:
                mostrarVendas();
                break;
            case 0:
                System.out.println("Ate a proxima!");
                System.out.println("---------------------FIM---------------------");
                break;
            default:
                System.out.println("ERRO: Favor escolher uma opcao valida!");
                break;
        }
    }

    public static void calcularPrecoTotal(){
        String planta;
        double valorUnit = 0;
        int resposta = 0;
        int quant = 0;
        double desconto = 0;
        double valorTotal = 0;

        System.out.println("Insira o nome da planta!");
        planta = scan.nextLine();
        System.out.println("Insira a quantidade de plantas vendidas!");
        quant = scan.nextInt();
        System.out.println("Digite o valor da planta vendida!");
        valorUnit = scan.nextDouble();
        valorTotal = quant * valorUnit;

        if(quant>10){
            desconto = valorTotal * 0.05;
            valorTotal -= desconto;
            System.out.printf("O valor total da venda foi de R$%.2f (desconto de R$%.2f)\n",valorTotal, desconto);
        }else{
            System.out.printf("O valor total da venda foi de R$%.2f\n",valorTotal);
        }

        System.out.println("Venda confirmada?");
        resposta = confirmarAcao();

        if(resposta == 1){
            Planta nova = new Planta(planta, valorUnit, quant, valorTotal, desconto);
            registro.add(nova);
            System.out.println("Registro realizado com sucesso!");
        }else{
            System.out.println("ATENCAO: Venda nao confirmada!");
            System.out.println("Nenhuma nova venda registrada!");
        }
    }

    public static void calcularTroco(){
        double valorTotal = 0;
        double valorPago = 0;
        double troco = 0;

        System.out.println("Insira o valor total da compra!");
        valorTotal = scan.nextDouble();
        System.out.println("Insira  valor pago pelo cliente!");
        valorPago = scan.nextDouble();
        troco = valorPago - valorTotal;

        if(troco == 0){
            System.out.println("Sem troco!");
        }else if(troco < 0){
            System.out.printf("ATENCAO: Esta faltando R$%.2f!\n",troco*-1);
        }else{
            System.out.printf("O troco sera de R$%.2f!\n",troco);
        }
    }

    public static void mostrarVendas(){
        double soma = somarRegistros();
        double valorDesconto = somarDescontos();
        int quantDesconto = 0;
        if(registro.isEmpty()){
            System.out.println("ATENCAO: Nenhum registro foi encontrado");
            return;
        }
        System.out.println("Registro:");
        for(int i = 0; i < registro.size(); i++){
            if(registro.get(i).getDesconto() > 0){
                quantDesconto++;
            }
            System.out.printf("Venda %d: ",i+1);
            registro.get(i).mostrarPlanta();
        }
        System.out.printf("O total das vendas foi de R$%.2f\n", soma);
        System.out.printf("Houve descontos em %d vendas, totalizando R$%.2f de desconto\n", quantDesconto, valorDesconto);
    }

    public static double somarRegistros(){
        double soma = 0;
        for(int i = 0; i < registro.size(); i++){
            soma += registro.get(i).getTotal();
        }
        return soma;
    }

    public static double somarDescontos(){
        double descontos = 0;
        for(int i = 0; i < registro.size(); i++){
            descontos += registro.get(i).getDesconto();
        }
        return descontos;
    }

    public static int confirmarAcao(){
        int resposta = 0;
        do{
            System.out.println("[1] - SIM");
            System.out.println("[2] - NÃO");
            resposta = scan.nextInt();
            if(resposta!=1 && resposta!=2){
                System.out.println("ATENCAO: Escolha uma opcao valida!");
            }
        }while(resposta!=1 && resposta!=2);
        return resposta;
    }
}