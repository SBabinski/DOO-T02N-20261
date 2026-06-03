public class Item {
    private String nome;
    private String tipo;
    private Double valor;
    private String id;

    public Item(String id, String nome, String tipo, Double valor){
        setId(id);
        setNome(nome);
        setTipo(tipo);
        setValor(valor);
    }

    public String gerarDescricao(){
        return "ID: "+id+" NOME: "+nome+" TIPO: "+tipo+" VALOR: "+valor;
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
    public Double getValor() {
        return valor;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

}
