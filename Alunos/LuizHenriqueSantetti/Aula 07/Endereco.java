public class Endereco {

    String estado;
    String cidade;
    String bairro;
    String numero;
    String complemento;

    public Endereco(String estado, String cidade, String bairro, String numero, String complemento){
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.numero = numero;
        this.complemento = complemento;
    }

    public String apresentarLogradouro(){
        return rua();
    }

    public String rua(){
        return bairro + " " + cidade + " " + estado + " num:" + numero + " " + complemento;
    }
}