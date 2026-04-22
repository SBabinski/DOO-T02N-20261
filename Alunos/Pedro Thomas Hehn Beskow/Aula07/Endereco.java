public class Endereco {

    String estado;
    String cidade;
    String bairro;
    String numero;
    String complemento;

    public Endereco(String estado, String cidade, String bairro,
                    String numero, String complemento) {
        this.estado      = estado;
        this.cidade      = cidade;
        this.bairro      = bairro;
        this.numero      = numero;
        this.complemento = complemento;
    }

    // printa os dados de endereço de forma concatenada e formatada
    public void apresentarLogradouro() {
        String base = "  📍 " + numero + ", " + bairro + " - " + cidade + "/" + estado;
        if (complemento != null && !complemento.isEmpty()) {
            base += " (" + complemento + ")";
        }
        System.out.println(base);
    }
}
