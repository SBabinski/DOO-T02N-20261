public class Endereco {
    private String estado;
    private String cidade;
    private String bairro;
    private String rua;
    private String complemento;

    public Endereco () {

    }

    public Endereco (String cidade, String bairro, String rua) {
        setCidade(cidade);
        setBairro(bairro);
        setRua(rua);
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        if ((estado != null) && (!estado.isBlank())){
            this.estado = estado;
        }
        else {
            System.out.println("Digite um estado válida:");
            String novoEstado = Main.scan.nextLine();
            setEstado(novoEstado);
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

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        if ((complemento != null) && (!complemento.isBlank())){
            this.complemento = complemento;
        }
        else {
            System.out.println("Digite um complemento válido:");
            String novoComplemento = Main.scan.nextLine();
            setComplemento(novoComplemento);
        }
    }

    public void apresentarLogradouro() {
        System.out.println("Cidade: " + cidade + " | Bairro: " + bairro + " | Rua: " + rua);
    }
    
}
