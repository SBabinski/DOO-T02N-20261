import java.util.Scanner;

public class Cliente {
    static Scanner scan = new Scanner(System.in);
    private String nome;
    private int idade;
    private Endereco endereco;

    
    public Cliente(String nome, int idade, Endereco endereco) {
        setNome(nome);
        setIdade(idade);
        this.endereco = endereco;
    }
    public Cliente(){

    }
    public void criar(){
        Endereco endereco = new Endereco();
        System.out.println("digite seu nome:");
        setNome(scan.nextLine());
        System.out.println("digite sua idade:");
        setIdade(scan.nextInt());
        scan.nextLine();
        endereco.criar();
        this.endereco = endereco;
    }
    public void adicionarClienteNaLoja(Loja loja){
        loja.adicionarCliente(this);
    }
    public void apresentarse(){
        System.out.println("Nome: "+ nome +", Idade: "+idade+".");
        endereco.apresentarLogradouro();
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
}
