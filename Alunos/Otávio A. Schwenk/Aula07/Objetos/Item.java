package Objetos;

public class Item {
    
    private static int contador = 1;
    private final int id;
    private String nome;
    private String tipo;
    private double valor;

    public Item(){
        this.id = contador++;
    }

    public Item (String nome, String tipo, double valor){
        this.id = contador++;
        setNome(nome);
        setTipo(tipo);
        setValor(valor);
    }

    public int getId(){
        return id;
    }

    public String getNome(){
        return nome;
    }

    public String getTipo(){
        return tipo;
    }

    public double getValor(){
        return valor;
    }

    public void setNome(String nome){
        if(nome != null && !nome.isBlank()) {
            this.nome = nome;
        }
    }

    public void setTipo(String tipo){
        if(tipo != null && !tipo.isBlank()) {
            this.tipo = tipo;
        }
    }

    public void setValor(double valor){
        if(valor > 0) {
            this.valor = valor;
        }
    }

    public void gerarDescricao(){
        System.out.printf("ID %d | %s - %s - R$%.2f\n", id, nome, tipo, valor);
    }

    @Override
    public String toString(){
        return "Item [id=" + id +
            ", nome=" + nome +
            ", tipo=" + tipo +
            ", valor=" + valor +
            "]";
    }
}
