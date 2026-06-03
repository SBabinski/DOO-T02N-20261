import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Locadora {
    List<Cliente> clientes = new ArrayList<>();
    List<Veiculo> veiculos = new ArrayList<>();
    List<Locacao> locacoes = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    void menu() {
        int op;
        do {
            System.out.println("\n1 cliente 2 veiculo 3 locacao 4 devolver 5 listar ativas 6 demo 0 sair");
            op = sc.nextInt(); sc.nextLine();
            if (op==1) cliente();
            if (op==2) veiculo();
            if (op==3) locacao();
            if (op==4) devolver();
            if (op==5) listar();
            if (op==6) demo();
        } while(op!=0);
    }

    void cliente() {
        System.out.print("nome:"); String n = sc.nextLine();
        System.out.print("cpf:"); String c = sc.nextLine();
        System.out.print("cnh:"); String h = sc.nextLine();
        clientes.add(new Cliente(n,c,h));
        System.out.println("ok");
    }

    void veiculo() {
        System.out.print("placa:"); String p = sc.nextLine();
        System.out.print("diaria:"); double d = sc.nextDouble(); sc.nextLine();
        System.out.print("1 carro 2 moto:"); int t = sc.nextInt(); sc.nextLine();
        if (t==1) {
            System.out.print("ar s/n:"); String a = sc.nextLine();
            veiculos.add(new Carro(p,d,a.equals("s")));
        } else {
            System.out.print("cilindradas:"); int c = sc.nextInt();
            veiculos.add(new Moto(p,d,c));
        }
        System.out.println("ok");
    }

    void locacao() {
        if (locacoes.size()>=10) {
            System.out.println("lotado!");
            return;
        }
        System.out.println("clientes:");
        for(int i=0;i<clientes.size();i++)
            System.out.println((i+1)+") "+clientes.get(i).mostrar());
        System.out.print("num:"); int cc = sc.nextInt()-1; sc.nextLine();

        System.out.println("veiculos:");
        for(int i=0;i<veiculos.size();i++) {
            System.out.print((i+1)+") ");
            veiculos.get(i).mostrar();
        }
        System.out.print("num:"); int vv = sc.nextInt()-1; sc.nextLine();

        System.out.print("data dd/mm:"); String r = sc.nextLine();
        locacoes.add(new Locacao(clientes.get(cc), veiculos.get(vv), r));
        System.out.println("ok");
    }

    void devolver() {
        System.out.println("ativas:");
        int cnt=1;
        for(int i=0;i<locacoes.size();i++) {
            if(!locacoes.get(i).devolvida) {
                System.out.println(cnt+") "+locacoes.get(i).mostrar());
                cnt++;
            }
        }
        System.out.print("num:"); int num = sc.nextInt()-1; sc.nextLine();
        int idx=0;
        for(int i=0;i<locacoes.size();i++) {
            if(!locacoes.get(i).devolvida && idx==num) {
                System.out.print("data dev dd/mm:"); String d = sc.nextLine();
                locacoes.get(i).devolver(d);
                System.out.println("ok");
                return;
            }
            if(!locacoes.get(i).devolvida) idx++;
        }
    }

    void listar() {
        System.out.println("ATIVAS:");
        for(Locacao l : locacoes) {
            if(!l.devolvida)
                System.out.println(l.mostrar());
        }
    }

    void demo() {
        clientes.add(new Cliente("Joao","111","111"));
        clientes.add(new Cliente("Maria","222","222"));
        veiculos.add(new Carro("ABC1",100,true));
        veiculos.add(new Moto("XYZ1",50,500));
        Locacao l1 = new Locacao(clientes.get(0),veiculos.get(0),"10/04");
        l1.devolver("15/04");
        locacoes.add(l1);
        locacoes.add(new Locacao(clientes.get(1),veiculos.get(1),"12/04"));
        System.out.println("demo ok");
        listar();
    }
}