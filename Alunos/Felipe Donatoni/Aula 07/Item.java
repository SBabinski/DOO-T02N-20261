public class Item {
    
    int id;
    String nome;
    String tipo;
    double valor;

    public Item (int id, String nome, String tipo, double valor) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.valor = valor;
    }
    public void gerarDescricao() {
        System.out.println ("Descrição");
        System.out.println ("ID do item: " + id);
        System.out.println ("Nome: " + nome);
        System.out.println ("Tipo: " + tipo);
        System.out.println ("Valor: " + valor);   
    }
}