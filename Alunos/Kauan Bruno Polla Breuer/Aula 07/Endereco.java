public class Endereco {

    String estado;
    String cidade;
    String bairro;
    String numero;
    String complemento;

    public Endereco(String estado, String cidade, String bairro, String numero, String complemento) {
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.numero = numero;
        this.complemento = complemento;
    }

    public void apresentarLogradouro() {
        String complementoTexto = "";

        if (complemento != null && !complemento.trim().isEmpty()) {
            complementoTexto = " - " + complemento;
        }

        System.out.println("Endereco: " + bairro + ", " + cidade + " - " + estado + ", nº " + numero + complementoTexto);
    }
}