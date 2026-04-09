public class Pessoa {
    private String nome;
    private int idade;

    public Pessoa(){

    }
    public Pessoa(String nome, int idade){
        setNome(nome);
        setIdade(idade);
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
}
