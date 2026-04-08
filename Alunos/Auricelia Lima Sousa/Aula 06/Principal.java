import java.util.Scanner;
import java.util.ArrayList;
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
                Vendedor primeiro = new Vendedor("Pedro",18,"My Plant New","Cascavel","Morumbi", "Santa Catarina", 2800);
                Vendedor segundo = new Vendedor("Maria",20,"My Plant New","Cascavel","Canada", "Santa Cruz ", 2700);
                Vendedor terceiro = new Vendedor("Jonas",23,"My Plant New","Cascavel","Esmeralda", " Tiradentes", 2500);
                Vendedor quarto = new Vendedor("Guilherme",25,"My Plant New","Cascavel","Periolo", "Europa", 2800);
                Vendedor quinto = new Vendedor("Pascal",19,"My Plant New","Cascavel","Morumbi", "Corbelia", 2400);
                Vendedor sexto = new Vendedor("Leandra",21,"My Plant New","Cascavel","Centro", "Osvaldo Cruz", 2800);

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
            
        Cliente cli0 = new Cliente("Fernando", 30, "Cascavel", "Morumbi", "Bahia");
        Cliente cli1  = new Cliente("Juliana", 30, "Cascavel", "Periolo", "Brasilia");
        Cliente cli2 = new Cliente("Jessica", 28, "Cascavel", "Morumbi", "Pedra Branca ");
        
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
        Loja store1 = new Loja("My Plant","My Plant","12345","Cascavel", "Morumbi", "Esmeralda" );
        
        store1.apresentarse();
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