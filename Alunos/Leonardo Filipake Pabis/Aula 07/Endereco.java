public class Endereco {
    private String estado;
    private String cidade;
    private String bairro;
    private String rua;
    private int numero;
    private String complemento;

    public Endereco () {

    }

    public Endereco (String cidade, String bairro, String rua) {
        this.setCidade(cidade);
        this.setBairro(bairro);
        this.setRua(rua);
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

    public final void setCidade(String cidade) {
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

    public final void setBairro(String bairro) {
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

    public final void setRua(String rua) {
        if ((rua != null) && (!rua.isBlank())){
            this.rua = rua;
        }
        else {
            System.out.println("Digite uma rua válida:");
            String novoRua = Main.scan.nextLine();
            setRua(novoRua);
        }
    }

    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        if (numero <= 0){
            System.out.println("Digite um numero válida:");
            int n = Main.scan.nextInt();
            Main.scan.nextLine();
            setNumero(n);
        }
        this.numero = numero;
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

    public void setNovoEndereco(String cidade, String bairro, String rua) {
        setCidade(cidade);
        setBairro(bairro);
        setRua(rua);
    }

    public void apresentarLogradouro() {
        System.out.println("Estado: "+ estado + " | Cidade: " + cidade + " | Bairro: " + bairro + " | Rua: " + rua + " | Complemento: " + complemento + " | \n");
    }

    @Override //Só as informações básicas
    public String toString() {
        return String.format("Cidade: %s | Bairro: %s | Rua: %s |\n", cidade, bairro, rua);
    }


    
}
