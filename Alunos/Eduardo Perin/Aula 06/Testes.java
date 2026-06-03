import java.util.ArrayList;

public class Testes {
    static ArrayList<Loja>lojas = new ArrayList<>();
    public static void main(String[] args) {
    }
    public static void main(){
        Loja loj = new Loja("copacol", "copacolfrango", "123091240918", "cafelandia", "juscelino", "flor");
        Vendedor vend1 = new Vendedor("jorge", 19, "cafelandia", "primavera", "pinto");
        Cliente clien = new Cliente("Eduardo", 19, "Caf", "Parque verde", "cardoso");
        clien.adicionarClienteNaLoja(loj);
        vend1.adicionarVendedorNaLoja(loj);
        lojas.add(loj);
        Loja loj2 = new Loja("constel", "constelLTDA", "91829486124", "cascavel", "cubiculo", "quadrado");
        Vendedor vend2 = new Vendedor("jairo", 52, "cafelandia", "pau", "pinto");
        Cliente clien2 = new Cliente("Pedro", 21, "Caf", "Brejao", "padre");
        clien2.adicionarClienteNaLoja(loj2);
        vend2.adicionarVendedorNaLoja(loj2);
        lojas.add(loj2);
        
    }
    public static void rodar(){
        for (int i = 0; i < lojas.size(); i++) {
            lojas.get(i).apresentarse();
        }
    }
}
