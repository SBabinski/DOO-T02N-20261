package objetos;

import fag.Floricultura;

public class Enderecos {
    private String estado;
    private String cidade;
    private String bairro;
    private String rua;
    private String complemento;

    public void apresentarLogradouro() {
        System.out.println(
            "Endereço: " + ruaFormatada()
        );
    }

    private String ruaFormatada() {
        return cidade + ", " + bairro + ", Nº " + rua + 
               (complemento != null ? " - " + complemento : "");
    }

    
    public String getEstado() {
    	return estado;
    }
    public void setEstado(String estado) {
    	if ((estado != null) && (!estado.isBlank())){
            this.estado = estado;
        }
        else {
            System.out.println("Digite um estadi válido:");
            String novoCidade = Floricultura.scan.nextLine();
            setCidade(novoCidade);
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
    public String getComplemento() {
    	return complemento;
    }
    
    public void setComplemento(String complemento) {
    	if ((complemento != null) && (!complemento.isBlank())) {
    		this.complemento = complemento;
    	}
    }
}

