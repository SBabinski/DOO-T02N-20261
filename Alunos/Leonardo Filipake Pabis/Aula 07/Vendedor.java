import java.util.ArrayList;

public class Vendedor {
    private String nome;
    private int idade;
    private Lojas loja;
    private String cidade;
    private String bairro;
    private String rua;
    private double salarioBase;
    private double salarioRecebido; 
    private ArrayList<Double> salarios = new ArrayList<>();

    public Vendedor() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if ((nome != null) && (!nome.isBlank())){
            this.nome = nome;
        }
        else {
            System.out.println("Digite um nome válido:");
            String novoNome = Main.scan.nextLine();
            setNome(novoNome);
        }
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        if (idade > 0){
            this.idade = idade;
        }
        else {
            System.out.println("Digite uma idade válida:");
            int novaIdade = Main.scan.nextInt();
            Main.scan.nextLine();
            setIdade(novaIdade);
        }
    }

    public String getLoja() {
        return loja.getNomeFantasia();
    }

    public void setLoja(Lojas loja){
        this.loja = loja;
    }

/*     public void setLoja(Lojas loja) {
        if ((loja != null) && (!loja.isBlank())){
            this.loja = loja;
        }
        else {
            System.out.println("Digite uma loja válida:");
            Lojas novaLoja = Main.scan.nextLine();
            setLoja(novaLoja);
        }
    }
 */
    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        if ((cidade != null) && (!cidade.isBlank())){
            this.cidade = cidade;
        }
        else {
            System.out.println("Digite uma cidade válida:");
            String novoCidade = Main.scan.nextLine();
            setCidade(novoCidade);
        }
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        if ((bairro != null) && (!bairro.isBlank())){
            this.bairro = bairro;
        }
        else {
            System.out.println("Digite um bairro válido:");
            String novoBairro = Main.scan.nextLine();
            setBairro(novoBairro);
        }
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        if ((rua != null) && (!rua.isBlank())){
            this.rua = rua;
        }
        else {
            System.out.println("Digite uma rua válida:");
            String novoRua = Main.scan.nextLine();
            setRua(novoRua);
        }
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        if (salarioBase > 0){
            this.salarioBase = salarioBase;
        }
        else {
            System.out.println("Digite um salario base válido:");
            double novoSalarioBase = Main.scan.nextDouble();
            Main.scan.nextLine();
            setSalarioBase(novoSalarioBase);
        }

    }

    public double getSalarioRecebido() {
        return salarioRecebido;
    }

    public void receberSalario(double salarioRecebido) {
        if (salarioRecebido >= this.salarioBase){
            this.salarioRecebido = salarioRecebido;
            salarios.add(salarioRecebido);
        }
        else {
            System.out.println("Digite um salario recebido válido:");
            double novoSalarioRecebido = Main.scan.nextDouble();
            Main.scan.nextLine();
            receberSalario(novoSalarioRecebido);
        }
        
    }

    public void apresentarse() {
        System.out.println("Vendedor: " + this.nome + " | Idade: "+ this.idade + " | Loja: " + this.loja.getNomeFantasia());
    }
    
    public double calcularMediaSalarial() {
        int contador=0;
        double soma = 0;
        for (int i=0; i<salarios.size(); i++){
            soma += salarios.get(i);
            contador += 1;
        }
        double media = soma / contador;
        return media;
    }

    public double calcularBonusSalarial() {
        double bonus = this.salarioBase * 0.2;
        return bonus;
    }
    
}
