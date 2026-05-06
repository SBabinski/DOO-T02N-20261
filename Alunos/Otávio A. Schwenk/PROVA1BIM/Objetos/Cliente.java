package Objetos;

public class Cliente {

    private String nome;
    private String cpf;
    private String numeroCnh;

    public Cliente(){
    }

    public Cliente(String nome, String cpf, String numeroCnh){
        setCpf(cpf);
        setNome(nome);
        setNumeroCnh(numeroCnh);
    }

    public String getCpf(){
        return cpf;
    }

    public String getNome(){
        return nome;
    }

    public String getNumeroCnh(){
        return numeroCnh;
    }

    public void setCpf(String cpf){
        if(cpf != null && !cpf.isBlank()){
            this.cpf = cpf;
        }
    }

    public void setNome(String nome){
        if(nome != null && !nome.isBlank()){
            this.nome = nome;
        }
    }

    public void setNumeroCnh(String numeroCnh){
        if(numeroCnh != null && !numeroCnh.isBlank()){
            this.numeroCnh = numeroCnh;
        }
    }

    public void apresentarSe(){
        System.out.printf("Nome: %s | CPF: %s | Número da CNH: %s\n ", nome, cpf, numeroCnh);
    }

}
