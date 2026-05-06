package Objetos;

public class Endereco {

    private String estado;
    private String cidade;
    private String bairro;
    private String rua;
    private int numero;
    private String complemento;

    public Endereco(){
    }

    public Endereco(String cidade, String bairro, String rua){
        setCidade(cidade);
        setBairro(bairro);
        setRua(rua);
    }

    public Endereco(String estado, String cidade, String bairro, String rua, int numero, String complemento){
        setEstado(estado);
        setCidade(cidade);
        setBairro(bairro);
        setRua(rua);
        setNumero(numero);
        setComplemento(complemento);
    }

    public String getEstado(){
        return estado;
    }

    public String getCidade(){
        return cidade;
    }

    public String getBairro(){
        return bairro;
    }

    public String getRua(){
        return rua;
    }

    public int getNumero(){
        return numero;
    }

    public String getComplemento(){
        return complemento;
    }

    public void setEstado(String estado){
        if(estado != null && !estado.isBlank()) {
            this.estado = estado;
        }
    }

    public void setCidade(String cidade){
        if(cidade != null && !cidade.isBlank()) {
            this.cidade = cidade;
        }
    }

    public void setBairro(String bairro){
        if(bairro != null && !bairro.isBlank()) {
            this.bairro = bairro;
        }
    }

    public void setRua(String rua){
        if(rua != null && !rua.isBlank()) {
            this.rua = rua;
        }
    }

    public void setNumero(int numero){
        if(numero > 0){
            this.numero = numero;
        }
    }

    public void setComplemento(String complemento){
        if(complemento != null && !complemento.isBlank()) {
            this.complemento = complemento;
        }
    }

    public void apresentarLogradouro(){
        System.out.print("Endereço: ");

        if (rua != null) {
            System.out.print(rua);
        }

        if (numero > 0) {
            System.out.print(", " + numero);
        }

        if (complemento != null && !complemento.isBlank()) {
            System.out.print(", " + complemento);
        }

        if (bairro != null) {
            System.out.print(", " + bairro);
        }

        if (cidade != null) {
            System.out.print(", " + cidade);
        }

        if (estado != null) {
            System.out.print(" - " + estado);
        }

        System.out.println();
    }

    @Override
    public String toString(){
        String texto = "";

        if (rua != null) {
            texto += "rua=" + rua;
        }

        if (numero > 0) {
            texto += (texto.isEmpty() ? "" : ", ") + "numero=" + numero;
        }

        if (complemento != null && !complemento.isBlank()) {
            texto += (texto.isEmpty() ? "" : ", ") + "complemento=" + complemento;
        }

        if (bairro != null) {
            texto += (texto.isEmpty() ? "" : ", ") + "bairro=" + bairro;
        }

        if (cidade != null) {
            texto += (texto.isEmpty() ? "" : ", ") + "cidade=" + cidade;
        }

        if (estado != null) {
            texto += (texto.isEmpty() ? "" : ", ") + "estado=" + estado;
        }

        return "Endereco [" + texto + "]";
    }

}
