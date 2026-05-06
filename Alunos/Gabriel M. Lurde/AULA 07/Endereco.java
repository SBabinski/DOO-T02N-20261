public class Endereco {

    String estado;
    String cidade;
    String bairro;
    int numero;
    String complemento;

    void apresentarLogradouro() {
        System.out.println(cidade + ", " + bairro + ", " + numero + " - " + complemento + " (" + estado + ")");
    }
}