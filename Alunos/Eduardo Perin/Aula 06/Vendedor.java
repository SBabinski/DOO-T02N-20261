import java.util.ArrayList;
import java.util.Scanner;

public class Vendedor{
    public static Scanner scan = new Scanner(System.in);
    private String nome;
    private int idade;
    private String loja;
    private String cidade;
    private String bairro;
    private String rua;
    private float salarioBase = 1500;
    private ArrayList<Float> salarioRecebido = new ArrayList<>();

    
    public Vendedor(String nome, int idade, String cidade, String bairro, String rua) {
        setNome(nome);
        setIdade(idade);
        setCidade(cidade);
        setBairro(bairro);
        setRua(rua);
    }
    public void adicionarVendedorNaLoja(Loja loja){
        loja.adicionarVendedor(this);
        this.loja = loja.getNomeFantasia();
    }
    public void receberSalario(){
        salarioRecebido.add(salarioBase + calcularBonus());
    }
    public void apresentarse(){
        System.out.println("Nome: "+ nome +", Idade: "+idade+", Loja: "+loja+".");
    }
    public float calcularMedia(){
        float media = 0;
        for(int i = 0;i < salarioRecebido.size() ;i++){
            media+=salarioRecebido.get(i);
        }
        media = media / salarioRecebido.size();
        return media;
    }
    public float calcularBonus(){
        return salarioBase * (float)0.2;
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
    public String getLoja() {
        return loja;
    }
    public void setLoja(String loja) {
        if(loja!=null && !loja.isBlank()){
            this.loja = loja;
        }
        else{
            System.out.println("nome de loja invalida, digite novamente:");
            loja = scan.nextLine();
            setLoja(loja);
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