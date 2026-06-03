public class CalculadoraGabrielinha {
    public static void main(String[] args) {

        Vendedor v1 = new Vendedor("Luiz", 25, "My Plant", "Cascavel", "Centro", "Rua A", 2000);
        Vendedor v2 = new Vendedor("Henrique", 30, "My Plant", "Cascavel", "Centro", "Rua B", 2200);

        Cliente c1 = new Cliente("Carlos", 28, "Cascavel", "Centro", "Rua X");
        Cliente c2 = new Cliente("Ana", 35, "Cascavel", "Centro", "Rua Y");

        Loja loja = new Loja("My Plant", "My Plant LTDA", "12.345.678/0001-90", "Cascavel", "Centro", "Rua Principal");

        loja.vendedores.add(v1);
        loja.vendedores.add(v2);

        loja.clientes.add(c1);
        loja.clientes.add(c2);

        loja.apresentarse();

        loja.contarVendedores();
        loja.contarClientes();

        v1.apresentarse();
        System.out.println(v1.calcularMedia());
        System.out.println(v1.calcularBonus());

        v2.apresentarse();
        System.out.println(v2.calcularMedia());
        System.out.println(v2.calcularBonus());

        c1.apresentarse();
        c2.apresentarse();
    }
}
