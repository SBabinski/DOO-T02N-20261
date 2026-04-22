public class Item {

    int    id;
    String nome;
    String tipo;
    double valor;

    public Item(int id, String nome, String tipo, double valor) {
        this.id    = id;
        this.nome  = nome;
        this.tipo  = tipo;
        this.valor = valor;
    }

    // printa as informações do item de forma legível
    public void gerarDescricao() {
        System.out.println("\n  Item #" + id);
        System.out.println("  Nome  : " + nome);
        System.out.println("  Tipo  : " + tipo);
        System.out.printf ("  Valor : R$ %.2f%n", valor);
    }
}
