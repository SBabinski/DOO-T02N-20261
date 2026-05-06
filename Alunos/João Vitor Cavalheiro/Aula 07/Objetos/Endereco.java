public class Endereco {
    String estado;
    String cidade;
    String bairro;
    String rua;
    int numero;
    String complemento;

    public Endereco(String bairro, String cidade, String complemento, String estado, int numero, String rua) {
        this.bairro = bairro;
        this.cidade = cidade;
        this.complemento = complemento;
        this.estado = estado;
        this.numero = numero;
        this.rua = rua;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public void apresentarLogradouro(){
        System.out.println("=======================");
        System.out.println("----------Localização----------");
        System.out.println("Estado: "+getEstado());
        System.out.println("Cidade: "+getCidade());
        System.out.println("Bairro: "+ getBairro());
        System.out.println("Rua:"+getRua());
        System.out.println("Número:"+getNumero());
        System.out.println("Complemento:"+getComplemento());
        System.out.println("=======================");
    }

}
