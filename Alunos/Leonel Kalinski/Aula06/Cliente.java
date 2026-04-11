package objetos;

import fag.Floricultura;

public class Cliente {

    private String nome;
    private int idade;
    private String cidade;
    private String bairro;
    private String rua;

    public Cliente() {

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
            String novoNome = Floricultura.scan.nextLine();
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
            int novaIdade = Floricultura.scan.nextInt();
            Floricultura.scan.nextLine();
            setIdade(novaIdade);
        }
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
    if ((cidade != null) && (!cidade.isBlank())){
        this.cidade = cidade;
    }
    else {
        System.out.println("Digite uma cidade válida:");
        String novoCidade = Floricultura.scan.nextLine();
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
        String novoBairro = Floricultura.scan.nextLine();
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
            String novoRua = Floricultura.scan.nextLine();
            setRua(novoRua);
        }
    }

    public void apresentarse() {
        System.out.println("Cliente: "+ this.nome + " | Idade: "+ this.idade);
    }
    
}
