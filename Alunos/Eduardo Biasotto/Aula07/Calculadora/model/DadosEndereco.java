package Calculadora.model;

public class DadosEndereco {
    private String cidade;
    private String bairro;
    private String rua;
    private String estado;
    private String complemento;
    private int numero;

    public DadosEndereco(String cidade, String bairro, String rua, String estado, String complemento, int numero) {
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.estado = estado;
        this.complemento = complemento;
        this.numero = numero;
    }

    public void apresentarLogradouro() {
        System.out.println("\n======= ENDEREÇO =======");
        System.out.println("Cidade: " + cidade + ", Bairro: " + bairro + ", Rua: " + rua + ", Estado: " + estado + ", Complemento: " + complemento + ", Número: " + numero);
    }

    public String getCidade() {
        return cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public String getRua() {
        return rua;
    }

    public int getNumero() {
        return numero;
    }

    public String getEstado() {
        return estado;
    }

    public String getComplemento() {
        return complemento;
    }
}
