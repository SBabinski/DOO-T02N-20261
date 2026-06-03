public class Endereco{
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

public void apresentarseLogadouro(){

    System.out.println("Localizacao");
    System.out.println(bairro + ", nº " + numero + "(" + complemento + ")");
    System.out.println(cidade  + "-" + estado);
    System.out.println();
}


}