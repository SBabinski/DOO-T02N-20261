public class Endereco {

    String estado;
    String cidade;
    String bairro;
    String numero;
    String complemento;

    public void apresentarLogradouro() {
        System.out.println(cidade + " - " + bairro + ", Nº " + numero + " (" + complemento + ")");
    }
}