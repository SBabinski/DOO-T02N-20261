import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Cliente[] clientes = new Cliente[10];
        Veiculo[] veiculos = new Veiculo[10];
        int cCount = 0;
        int vCount = 0;

        Locadora locadora = new Locadora();

        int op = -1;

        while (op != 0) {

            System.out.println("\n1-Cadastrar Cliente");
            System.out.println("2-Cadastrar Veiculo");
            System.out.println("3-Cadastrar Locacao");
            System.out.println("4-Devolver Veiculo");
            System.out.println("5-Listar Locacoes Ativas");
            System.out.println("6-Demonstracao");
            System.out.println("0-Sair");

            op = sc.nextInt();

            if (op == 1) {

                System.out.println("Nome:");
                String nome = sc.next();
                System.out.println("CPF:");
                String cpf = sc.next();
                System.out.println("CNH:");
                String cnh = sc.next();

                clientes[cCount++] = new Cliente(nome, cpf, cnh);
            }

            if (op == 2) {

                System.out.println("1-Carro  2-Moto");
                int tipo = sc.nextInt();

                System.out.println("Nome do veiculo:");
                String nome = sc.next();

                System.out.println("Placa:");
                String placa = sc.next();

                System.out.println("Valor diaria:");
                double valor = sc.nextDouble();

                if (tipo == 1) {

                    System.out.println("Tem ar? true/false");
                    boolean ar = sc.nextBoolean();

                    veiculos[vCount++] = new Carro(nome, placa, valor, ar);

                } else {

                    System.out.println("Cilindrada:");
                    int cil = sc.nextInt();

                    veiculos[vCount++] = new Moto(nome, placa, valor, cil);
                }
            }

            if (op == 3) {

                if (cCount == 0 || vCount == 0) {
                    System.out.println("Cadastre cliente e veiculo primeiro.");
                } else {

                    Locacao l = new Locacao();

                    System.out.println("\nCLIENTES:");
                    for (int i = 0; i < cCount; i++) {
                        System.out.println(i + " - " + clientes[i].nome);
                    }

                    System.out.println("Numero do cliente:");
                    int ci = sc.nextInt();

                    System.out.println("\nVEICULOS:");
                    for (int i = 0; i < vCount; i++) {
                        System.out.println(i + " - " + veiculos[i].nome);
                    }

                    System.out.println("Numero do veiculo:");
                    int vi = sc.nextInt();

                    if (ci >= cCount || vi >= vCount) {
                        System.out.println("Numero invalido.");
                    } else {

                        l.cliente = clientes[ci];
                        l.veiculo = veiculos[vi];

                        System.out.println("Dias:");
                        l.dias = sc.nextInt();

                        System.out.println("Data retirada:");
                        l.dataRetirada = sc.next();

                        System.out.println("Data devolucao:");
                        l.dataDevolucao = sc.next();

                        l.devolvido = false;

                        locadora.adicionarLocacao(l);
                    }
                }
            }

            if (op == 4) {

                System.out.println("\nLOCACOES:");
                for (int i = 0; i < locadora.contador; i++) {
                    System.out.println(i + " - " + locadora.locacoes[i].veiculo.nome);
                }

                System.out.println("Numero da locacao:");
                int li = sc.nextInt();

                if (li >= locadora.contador) {
                    System.out.println("Numero invalido.");
                } else {
                    locadora.locacoes[li].devolvido = true;
                    System.out.println("Devolucao realizada.");
                }
            }

            if (op == 5) {
                locadora.listarAtivas();
            }

            if (op == 6) {

                Cliente c1 = new Cliente("Joao", "111", "123");
                Cliente c2 = new Cliente("Maria", "222", "456");

                Carro car = new Carro("Golf", "AAA", 100, true);
                Moto moto = new Moto("XRE", "BBB", 50, 150);

                Locacao l1 = new Locacao();
                l1.cliente = c1;
                l1.veiculo = car;
                l1.dias = 3;
                l1.dataRetirada = "01/01/2026";
                l1.dataDevolucao = "04/01/2026";
                l1.devolvido = true;

                Locacao l2 = new Locacao();
                l2.cliente = c2;
                l2.veiculo = moto;
                l2.dias = 2;
                l2.dataRetirada = "05/01/2026";
                l2.dataDevolucao = "07/01/2026";
                l2.devolvido = false;

                locadora = new Locadora();
                locadora.adicionarLocacao(l1);
                locadora.adicionarLocacao(l2);

                System.out.println("\nLOCACOES ATIVAS:");
                locadora.listarAtivas();
            }
        }

        sc.close();
    }
}