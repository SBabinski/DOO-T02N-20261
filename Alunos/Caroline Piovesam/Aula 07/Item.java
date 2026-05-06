public class Item {

    private int id;
    private String nome;
    private String tipo;
    private double valor;

    public Item(int id, String nome, String tipo,
            double valor) {

        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.valor = valor;

    }

    public int getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public String getTipo() {
        return this.tipo;
    }

    public double getValor() {
        return this.valor;
    }

    public void gerarDescricao() {

        System.out.println("ID: " + id + "|" +
                "Nome Item: " + nome + "|" + "Tipo: " + tipo + "|" + "Valor R$: " +
                valor);

    }

}