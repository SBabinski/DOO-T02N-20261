package objetos;

public class Pessoa {
    protected String nome;
    protected int idade;
    protected Endereco endereco;


     public Pessoa(String nome, int idade, String cidade, String bairro, String rua) {
        this.nome = nome;
        this.idade = idade;
        
        
        Endereco end = new Endereco();
        end.setCidade(cidade);
        end.setBairro(bairro);
        end.setRua(rua);
        
        this.endereco = end;
    }

    public Endereco getEndereco() { 
        return endereco; 
    }
    public void setEndereco(Endereco endereco) {
         this.endereco = endereco; 
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}
