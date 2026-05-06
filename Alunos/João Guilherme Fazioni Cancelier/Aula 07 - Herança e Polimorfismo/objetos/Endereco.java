package objetos;

public class Endereco {
    private String cidade;
    private String bairro;
    private String rua;

    public String getCidade() { 
        return cidade; 
    }
    public void setCidade(String cidade) { 
        this.cidade = cidade; 
    }

    public String getBairro() { 
        return bairro; 
    }
    public void setBairro(String bairro) { 
        this.bairro = bairro; 
    }

    public String getRua() { 
        return rua; 
    }
    public void setRua(String rua) { 
        this.rua = rua; 
    }

    public void apresentarLogradouro() {
        System.out.println("Cidade: " + cidade);
        System.out.println("Bairro: " + bairro);
        System.out.println("Rua: " + rua);
    }
}
