package Calculadora.model;


public class DadosItem {
    private int id;
    private String nome;
    private double valor;
    private String tipo;

    public DadosItem(int id, String nome, double valor, String tipo) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.tipo = tipo;
        
    }

    public void gerarDescricao() {
        System.out.println("\n======= DADOS DO ITEM - " + nome + " =======");
        System.out.println("\nID: " + id);
        System.out.println("Nome: " + nome);
        System.out.println("Valor: R$ " + valor);
        System.out.println("Tipo: " + tipo);
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getValor() {
        return valor;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nome: " + nome + ", Valor: " + valor + ", Tipo: " + tipo;
    }

}
