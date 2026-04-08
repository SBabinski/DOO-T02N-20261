
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner scan = new Scanner(System.in);
    static int opcao = 1000;
    static ArrayList <Vendas> vendas = new ArrayList<>();
    static double valor_de_venda_total = 0;
    
    public static void main(String[] args){
        Lojas loja1 = new Lojas();
        loja1.setNomeFantasia("Lojinha");
        loja1.setRazaoSocial("LOJINHARAZAOSOCIAL");
        loja1.setCnpj("12345676543211");
        loja1.setCidade("Cafelandia");
        loja1.setBairro("Bairro nobre");
        loja1.setRua("Rua rapida");

        Lojas loja2 = new Lojas();
        loja2.setNomeFantasia("Lojão");
        loja2.setRazaoSocial("LOJÃORAZAOSOCIAL");
        loja2.setCnpj("19283740594837");
        loja2.setCidade("Cascavel");
        loja2.setBairro("Bairro pobre");
        loja2.setRua("Rua devagar");

        Vendedor vend1 = new Vendedor();
        vend1.setNome("Leo");
        vend1.setIdade(18);
        vend1.setLoja(loja2);
        vend1.setSalarioBase(1200);
        vend1.receberSalario(1200);
        vend1.receberSalario(1250);
        vend1.receberSalario(1300);
        vend1.apresentarse();
        System.out.println(vend1.calcularMediaSalarial());
        System.out.println(vend1.calcularBonusSalarial());
        loja2.vendedores.add(vend1);

        Vendedor vend2 = new Vendedor();
        vend2.setNome("Patrick");
        vend2.setIdade(22);
        vend2.setLoja(loja1);
        vend2.setSalarioBase(1500);
        vend2.receberSalario(1500);
        vend2.receberSalario(1550);
        vend2.receberSalario(1600);
        vend2.apresentarse();
        System.out.println(vend2.calcularMediaSalarial());
        System.out.println(vend2.calcularBonusSalarial());
        loja1.vendedores.add(vend2);
        
        Clientes cliente10 = new Clientes();
        cliente10.setNome("Eduardo");
        cliente10.setIdade(30);
        cliente10.setCidade("NY");
        cliente10.setBairro("bairro country");
        cliente10.setRua("rua da lapa");

        Clientes cliente20 = new Clientes();
        cliente20.setNome("Jemer");
        cliente20.setIdade(45);
        cliente20.setCidade("Fortaleza");
        cliente20.setBairro("Bairro cinema");
        cliente20.setRua("Rua abelha"); 

        loja1.clientes.add(cliente20);
        loja2.clientes.add(cliente10);
        
        Main.dadosLojas.add(loja1);
        Main.dadosLojas.add(loja2);
        menu();

    }
    
    public static void menu(){
        System.out.println("********");
        System.out.println("* MENU *");
        System.out.println("********");
        System.out.println("1 - Cálculo de preço total");
        System.out.println("2 - Cálculo de troco");
        System.out.println("3 - Consultar vendas");
        System.out.println("4 - Alocar vendas totais");
        System.out.println("5 - Buscar vendas do dia");
        System.out.println("6 - Listar dados (loja, vendedores e clientes)");
        System.out.println("7 - Sair");
        
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
            }else if (opcao == 6){
                listarDados();
            }
            else if (opcao == 7){
                Sair();
            }
        }while (opcao != 7);

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
        menu();
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


    public static ArrayList<Lojas> dadosLojas = new ArrayList<>(); 

    public static void listarDados(){
        for (int i = 0; i < dadosLojas.size(); i++){
            dadosLojas.get(i).apresentarse();
            for (int j = 0; j < dadosLojas.get(i).vendedores.size(); j++){
                System.out.printf(" -");
                dadosLojas.get(i).vendedores.get(j).apresentarse();
            }
            for (int k = 0; k < dadosLojas.get(i).clientes.size(); k++){
                System.out.printf(" -");
                dadosLojas.get(i).clientes.get(k).apresentarse();
            }
        }
        VoltarMenu();
    }

}


