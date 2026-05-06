import java.util.Scanner;

public class Endereco {
    static Scanner scan = new Scanner(System.in);
    private String estado;
    private String cidade;
    private String numero;
    private String bairro;
    private String rua;
    private String complemento;

    
    public Endereco(String estado, String cidade, String numero, String bairro, String rua, String complemento) {
        setEstado(estado);
        setCidade(cidade);
        setNumero(numero);
        setBairro(bairro);
        setRua(rua);
        setComplemento(complemento);
    }
    public Endereco(){
    
    }

    public void apresentarLogradouro(){
        System.out.println(cidade+", "+estado);
        System.out.println(rua+", "+numero+", "+bairro);
        System.out.println(complemento);
    }
    public void criar(){
        System.out.println("digite o estado:");
        setEstado(scan.nextLine());
        System.out.println("digite a cidade:");
        setCidade(scan.nextLine());
        System.out.println("digite a rua:");
        setRua(scan.nextLine());
        System.out.println("digite o numero:");
        setNumero(scan.nextLine());
        System.out.println("digite o bairro:");
        setBairro(scan.nextLine());
        System.out.println("digite o complemento:");
        setComplemento(scan.nextLine());
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        String estadoFormatado = estado.toUpperCase();
        this.estado = estadoFormatado;
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        String cidadeFormatada = cidade.substring(0,1).toUpperCase() + cidade.substring(1).toLowerCase(); 
        this.cidade = cidadeFormatada;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public String getBairro() {
        return bairro;
    }
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    public String getComplemento() {
        return complemento;
    }
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
    public String getRua() {
        return rua;
    }
    public void setRua(String rua) {
        this.rua = rua;
    }
    public static void main(String[] args) {
        Endereco endereco = new Endereco();
        endereco.criar();
        endereco.apresentarLogradouro();
    }
}
