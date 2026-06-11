public class Produto {

    private double preco;
    private String nome;

    public Produto(String nome, double preco) {
        setNome(nome);
        setPreco(preco);
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }
}
