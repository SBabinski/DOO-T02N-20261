import java.util.Scanner;

public class Devolucao {

    public static void realizarDevolucao(Scanner scan){

        Locacao.listarLocacoes();


        System.out.println("Escolha o número da locacao:");
        int indice = scan.nextInt();
        scan.nextLine();


        if (indice < 0 || indice >= Lista.locacao.size()) {
            System.out.println("Locacao invalida!");
            return;
        }


        Locacao l = Lista.locacao.get(indice);


        l.setDevolvido(true);

        System.out.println("Devolucao realizada com sucesso!");

        Lista.locacao.remove(indice);
    }
}