public class Endereco {

    private String estado;
    private String cidade;
    private String bairro;
    private int numero;
    private String complemento;

    public Endereco(String estado, String cidade, String bairro, int numero,
            String complemento) {

        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.numero = numero;
        this.complemento = complemento;
    }

    public String getEstado() {
        return this.estado;
    }

    public String getCidade() {
        return this.cidade;
    }

    public String getBairro() {
        return this.bairro;
    }

    public int getNumero() {
        return this.numero;
    }

    public String getComplemento() {
        return this.complemento;
    }

    public void apresentarLogradouro() {

        System.out.println(bairro + " | " + " N° " + numero + " | " + cidade + " | " + estado + " | " + complemento);
    }

}
