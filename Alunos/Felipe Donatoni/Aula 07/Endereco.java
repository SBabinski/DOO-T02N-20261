public class Endereco {
    String estado;
    String cidade;
    String bairro;
    int numero;
    String complemento;

     public Endereco(String estado, String cidade, String bairro, int numero, String complemento) {
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.numero = numero;
        this.complemento = complemento;
    }
    public void apresentarLogradouro () {
        System.out.println("N: " + numero);
        System.out.println("Bairro: " + bairro);
        System.out.println("Cidade - Estado: " + cidade + " - " + estado);
        System.out.println("Complemento: " + complemento);
    }
}