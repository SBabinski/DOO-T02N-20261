public class Pessoa {
    private String nome;
    private int idade;
    public Endereco endereco;

    public Pessoa(){

    }
    public Pessoa(String nome, int idade, String cidade, String bairro, String rua){
        this.setNome(nome);
        this.setIdade(idade);
        this.endereco = new Endereco(cidade, bairro, rua);
    }

    public String getNome() {
        return nome;
    }

    public final void setNome(String nome) {
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

    public final void setIdade(int idade) {
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

    public Endereco getEndereco() {
        return endereco;
    }

}
