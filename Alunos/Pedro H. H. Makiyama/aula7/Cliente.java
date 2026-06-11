package aula7;

public class Cliente {
    
    private String nome;
    private int idade;
    private String estado;
    private String cidade;
    private String bairro;
    private String rua;
    private int numero;
    private String complemento;
    private Endereco endereco;

    public Cliente(String nome, int idade, String estado, String cidade, String bairro, String rua, int numero, String complemento){

        this.nome = nome;
        this.idade = idade;
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;

        endereco = new Endereco(estado, cidade, bairro, rua, numero, complemento);
    }

    public void apresentarse(){

        System.out.println("\n| Nome - " + this.nome + " | Idade - " + this.idade + " " + endereco.apresentarLogradouro());
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
        endereco.setCidade(cidade);
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
        endereco.setBairro(bairro);
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
        endereco.setRua(rua);
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
        endereco.setEstado(estado);
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
        endereco.setNumero(numero);
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
        endereco.setComplemento(complemento);
    }
}
