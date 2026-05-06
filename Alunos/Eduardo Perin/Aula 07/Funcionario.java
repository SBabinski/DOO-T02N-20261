import java.util.ArrayList;
import java.util.Scanner;

public class Funcionario {
    public static Scanner scan = new Scanner(System.in);
    private String nome;
    private int idade;
    private Loja loja;
    private Endereco endereco;
    protected double salarioBase = 1200;
    protected ArrayList<Double> salarioRecebido = new ArrayList<>();

    public Funcionario(String nome,int idade,Endereco endereco){
        setNome(nome);
        setIdade(idade);
        this.endereco = endereco;
    }
    public Funcionario(){}

    public void criar(){
        Endereco endereco = new Endereco();
        System.out.println("Digite o nome");
        setNome(scan.nextLine());
        System.out.println("Digite a idade");
        setIdade(scan.nextInt());
        scan.nextLine();
        endereco.criar();
        this.endereco = endereco;

    }
    public void apresentarse(){
        System.out.println("NOME: "+ nome +", IDADE: "+idade+", LOJA: "+loja.getNomeFantasia()+".");
        System.out.println("ENDEREÇO:");
        endereco.apresentarLogradouro();
    }
    public void adicionarFuncionarioNaLoja(Loja loja){
        this.loja = loja;
    }
    public void receberSalario(){
        salarioRecebido.add(salarioBase);
    }
    public double calcularMedia(){
        double media = 0;
        for(int i = 0;i < salarioRecebido.size() ;i++){
            media+=salarioRecebido.get(i);
        }
        media = media / salarioRecebido.size();
        return media;
    }
    public double calcularBonus(){
        return salarioBase * 0.2;
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
