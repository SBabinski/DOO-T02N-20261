public class Endereco {
    public String cidade;
    public String bairro;
    public String rua;
    public String estado;
    public int numero;
    public String complemento;

    public Endereco(String cidade, String bairro, String rua, String estado, int numero, String complemento){
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.estado = estado;
        this.numero = numero;
        this.complemento = complemento;
    }

    public void apresentarse(){
        System.out.println("cidade: "+cidade+" bairro: "+bairro+"rua"+rua+"estado"+estado+"numero"+numero+"complemento"+complemento);
    }
}
