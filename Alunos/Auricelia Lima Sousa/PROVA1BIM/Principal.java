import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        Locadora locadora = new Locadora();
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n Menu Locadora");
            System.out.println("[1] Cadastrar Cliente/Veiculo/Locacao (Manual)");
            System.out.println("[2] Realizar Devolucao");
            System.out.println("[3] Listar Locacoes sem devolucao");
            System.out.println("[4] Fazer Demonstracao");
            System.out.println("[0] Sair");
            System.out.print("Opcao: ");
            
            opcao = leitor.nextInt();

            if (opcao == 3) {
                locadora.listarAtivas();
            } 
            else if (opcao == 4) { 
                Cliente c1 = new Cliente();
                c1.nome = "Ana"; c1.cpf = "111";
                
                Cliente c2 = new Cliente();
                c2.nome = "Joao"; c2.cpf = "222";
                
                Carro car = new Carro(); 
                car.placa = "AAA-1234"; 
                car.valorDiaria = 100.0; 
                car.temArCondicionado = true;
                
                Moto mot = new Moto(); 
                mot.placa = "BBB-2530"; 
                mot.valorDiaria = 50.0; 
                mot.cilindrada = 250;

                Locacao loc1 = new Locacao(); 
                loc1.cliente = c1; 
                loc1.veiculo = car;
                loc1.dataRetirada = "20/04"; 
                loc1.dataDevolucao = "25/04";
                loc1.qtdDiarias = 5; 
                loc1.devolvida = true;
                
                Locacao loc2 = new Locacao();
                loc2.cliente = c2; 
                loc2.veiculo = mot;
                loc2.dataRetirada = "21/04"; 
                loc2.dataDevolucao = "23/04"; 
                loc2.qtdDiarias = 2;          
                loc2.devolvida = false;

                locadora.adicionarLocacao(loc1);
                locadora.adicionarLocacao(loc2);
                
                System.out.println("\n Demonstracao: Listando apenas ativas:");
                locadora.listarAtivas();
            }
        }
        leitor.close();
    }
}