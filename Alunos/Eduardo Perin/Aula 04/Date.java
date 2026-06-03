import java.time.LocalDate;
import java.util.Scanner;
public class Date {
    static LocalDate diaCompra = LocalDate.of(2026, 1, 1);
    static LocalDate diaFiltro = LocalDate.of(2026, 1, 1);
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        
    }
    public static LocalDate retornaDataCompra(){
        try {
            int dia = pedirDia();
            int mes = pedirMes();
            diaCompra = LocalDate.of(2026, mes, dia);
        } catch (Exception e) {
            System.out.println("voce digitou uma data invalida, tente novamente");
            retornaDataCompra();
        }
        return diaCompra;
    }
    public static LocalDate retornaDataFiltro(){
        try {
            int dia = pedirDia();
            int mes = pedirMes();
            diaFiltro = LocalDate.of(2026, mes, dia);
        } catch (Exception e) {
            System.out.println("voce digitou uma data invalida, tente novamente");
            retornaDataCompra();
        }
        return diaFiltro;
    }
    public static int pedirDia(){
        int dia = 0;
        while (dia<=0 || dia>31){
            System.out.println("digite o dia da compra: ");
            dia = scan.nextInt();
            scan.nextLine();
            if (dia<=0 || dia>31){
                System.out.println("digite um dia valido");
            }
        }
        return dia;
    }
    public static int pedirMes(){
        int mes = 0;
        while(mes<=0 || mes>12){
            System.out.println("digite o mes da compra: ");
            mes = scan.nextInt();
            scan.nextLine();
            if (mes<=0 || mes>12){
                System.out.println("digite um mes valido");
            }
        }
        return mes;
    }
}
