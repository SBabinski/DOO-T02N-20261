import java.util.Scanner;

public class Cliente {
    static Scanner scan = new Scanner(System.in);
    private String nome;
    private int idade;
    private String cidade;
    private String bairro;
    private String rua;

    
    public Cliente(String nome, int idade, String cidade, String bairro, String rua) {
        setNome(nome);
        setIdade(idade);
        setCidade(cidade);
        setBairro(bairro);
        setRua(rua);
    }
    public void adicionarClienteNaLoja(Loja loja){
        loja.adicionarCliente(this);
    }
    public void apresentarse(){
        System.out.println("Nome: "+ nome +", Idade: "+idade+".");
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        if(nome!=null && !nome.isBlank()){
            this.nome = nome;
        }
        else{
            System.out.println("nome invalido. digite outro nome:");
            nome = scan.nextLine();
            setNome(nome);
        }
    }
    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        if (idade>0){
            this.idade = idade;
        }
        else{
            System.out.println("idade invalida. digite outra idade:");
            idade = scan.nextInt();
            scan.nextLine();
            setIdade(idade);
        }
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        if(cidade!=null && !cidade.isBlank()){
            this.cidade = cidade;
        }
        else{
            System.out.println("cidade invalida, digite novamente:");
            cidade = scan.nextLine();
            setCidade(cidade);
        }
    }
    public String getBairro() {
        return bairro;
    }
    public void setBairro(String bairro) {
        if(bairro!=null && !bairro.isBlank()){
            this.bairro = bairro;
        }
        else{
            System.out.println("bairro invalido, digite novamente:");
            bairro = scan.nextLine();
            setBairro(bairro);
        }
    }
    public String getRua() {
        return rua;
    }
    public void setRua(String rua) {
        if(rua!=null && !rua.isBlank()){
            this.rua = rua;
        }
        else{
            System.out.println("rua invalida, digite novamente:");
            rua = scan.nextLine();
            setRua(rua);
        }
    }
}
