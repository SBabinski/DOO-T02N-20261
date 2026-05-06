import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
public class Principal {
    public static void main (String[] args){
     Scanner scan = new Scanner (System.in);
Calculadora_Floricultura calculadora = new Calculadora_Floricultura();
      int opcao;

   do {
   System.out.println("\nMenu\n");
   System.out.println(" [1] - Calcular Preço Total");
   System.out.println(" [2] - Calcular Troco    ");
   System.out.println("[3] -Listar vendas  ");
   System.out.println("[4] Listar por Datas");
   System.out.println("[5] - Consultar vendedor");
   System.out.println("[6] - Consultar Cliente");
   System.out.println("[7] - Consultar Loja" );
   System.out.println("[8] Consultar Gerentes");
   System.out.println("9 - Criar Item");
   System.out.println("10 - Criar Pedido");
  System.out.println("[0] - Sair  ");

  System.out.println("Entre com a opcao");

    
   opcao = scan.nextInt();
   scan.nextLine();
   
   switch (opcao){
  
    case 1:
        calculadora.calculartotal();
    break;

    case 2:
       calculadora.calcularpreco();
       break;
      case 3:
        calculadora.registrarVendas();
        break;
        case 4:

            calculadora.consultarDatas();
            break;
            
            case 5:
                Endereco endV1 = new Endereco ("PR", "Cascavel", "Morumbi", "100", "Ao lado do mercado");
                Endereco endV2 = new Endereco ("PR", "Cascavel", "Canada", "250", "Casa Verde");
                Endereco endV3 = new Endereco ("PR", "Cascavel", "Mantovani" , "240", "Hospital");
                Endereco endV4 = new Endereco("PR", "Cascavel", "Esmeralda", "280", "Escola");

                Vendedor primeiro = new Vendedor("Pedro",18,"My Plant New", 2800, endV1);
                Vendedor segundo = new Vendedor("Maria",20,"My Plant New", 2700, endV2);
                Vendedor terceiro = new Vendedor("Jonas",23,"My Plant New", 2500, endV3);
                Vendedor quarto = new Vendedor("Guilherme",25,"My Plant New", 2800, endV4);
                Vendedor quinto = new Vendedor("Pascal",19,"My Plant New", 2400, endV1);
                Vendedor sexto = new Vendedor("Leandra",21,"My Plant New", 2800, endV3);

                primeiro.adicionarSalario(1800);
                primeiro.adicionarSalario(1800);
                primeiro.adicionarSalario(1800);
                
                segundo.adicionarSalario(2800);
                segundo.adicionarSalario(2800);
                segundo.adicionarSalario(2700);
                
                terceiro.adicionarSalario(2600);
                terceiro.adicionarSalario(2700);
                terceiro.adicionarSalario(2800);

                quarto.adicionarSalario(2700);
                quarto.adicionarSalario(2900);
                quarto.adicionarSalario(2850);

                quinto.adicionarSalario(2500);
                quinto.adicionarSalario(2600);
                quinto.adicionarSalario(2450);

                sexto.adicionarSalario(2850);
                sexto.adicionarSalario(2900);
                sexto.adicionarSalario(2850);

                
             ArrayList <Vendedor> vendedores = new ArrayList<>();
            vendedores.add(primeiro);
            vendedores.add(segundo);
            vendedores.add(terceiro);
            vendedores.add(quarto);
            vendedores.add(quinto);
            vendedores.add(sexto);
           
            for (Vendedor recebe: vendedores ){
               recebe.apresentarse();
               
               System.out.println(" Media dos salarios: " + recebe.calcularMedia());
               System.out.println("Bonus: " + recebe.calcularBonus());
               System.out.println();
            }
           break;

           case 6:
        Endereco endCli1 = new  Endereco("PR", "Cascavel", "Centro", "10", "Apto 201");
        Endereco endCli2 = new Endereco ("PR", "Cascavel", "Centro", "501", "Escola");

        Cliente cli0 = new Cliente("Fernando", 30, endCli1);
        Cliente cli1  = new Cliente("Juliana", 30, endCli2);
        Cliente cli2 = new Cliente("Jessica", 28, endCli1);
        
        ArrayList <Cliente> clientes  = new ArrayList<>();
        clientes.add(cli0);
        clientes.add(cli1);
        clientes.add(cli2);

        for(Cliente mostrar: clientes){
            mostrar.apresentarse();
            System.out.println();
        }
        break;
        
        case 7:
        Endereco endLoja =  new Endereco("PR", "Cascavel", "Centro", "270", "Banco Caixa");
        Loja store1 = new Loja("My Plant","My Plant","12345",endLoja);
        
        store1.apresentarse();
        break;
        
       case 8:
        Endereco endGe = new Endereco("PR", "Cascavel","Canada","250", "CMEI");

        Gerente ge1 = new Gerente ("Luis", 30, "My Plant New", 3500, endGe);
        Gerente ge2 = new Gerente ("Fernanda", 35, "My Plant New", 3500, endGe);
        
        ge1.adicionarSalario(3000);
        ge1.adicionarSalario(3000);
        ge1.adicionarSalario(3000);

        ge2.adicionarSalario(3000);
        ge2.adicionarSalario(3000);
        ge2.adicionarSalario(3000);
       
       ArrayList <Gerente> gerentes = new ArrayList<>();
       gerentes.add(ge1);
       gerentes.add(ge2);

       for(Gerente receber : gerentes){
        receber.apresentarse();
        System.out.println("Media dos salarios: " + receber.calcularMedia());
        System.out.println("Bonus: " + receber.calcularBonus());
        System.out.println();

       }
       break; 

       case 9:
    Item flor1 = new Item(1, "Orquidea", "Flor", 89.90);
    Item vaso1 = new Item(2, "Vaso de Barro", "Acessorio", 45.00);

    System.out.println("Itens cadastrados com sucesso!");
    flor1.gerarDescricao();
    vaso1.gerarDescricao();
    break;
    
    case 10:
    Endereco endP = new Endereco("PR", "Cascavel", "Centro", "100", "Loja 01");
    Cliente c1 = new Cliente("Seu Jose", 70, endP);
    Vendedor v1 = new Vendedor("Ana", 25, "My Plant", 2500.0, endP);
    Loja minhaLoja = new Loja("My Plant", "Flores LTDA", "12345", endP);
    
    ArrayList<Item> itensCompra = new ArrayList<>();
    itensCompra.add(new Item(1, "Rosa", "Flor", 10.0));
    itensCompra.add(new Item(2, "vaso plastico", "Acessorio", 15.0));

    ProcessaPedido processador = new ProcessaPedido();

    Pedido pedidoFeito = processador.processar(501, c1, v1, minhaLoja, itensCompra);

    System.out.println("Pedido processado com suceddo");
    pedidoFeito.gerarDescricaoVenda();
    break;
    case 0:
    System.out.println("Saindo...");
    break;

    default: 

    System.out.println("Opcao invalida");
    break;

   } 



   } while (opcao !=0);

scan.close();
    }
}