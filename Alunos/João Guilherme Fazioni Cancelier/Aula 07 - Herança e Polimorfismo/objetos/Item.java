package objetos;

public class Item {
    private static int contador = 0;
	private int idItem;
	private String nome;
	private String tipo;
	private double valor;
    public Item(String nome, String tipo, double valor) {
        this.idItem = ++ contador;
        this.nome = nome;
        this.tipo = tipo;
        this.valor = valor;
    }

    public int getIdItem() {
        return idItem;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }

    public void gerarDescricao(){
        System.out.printf("ID: %d - Nome: %s - Tipo: %s - Valor: %.2f .\n", idItem,nome,tipo,valor);
    }
   
}
