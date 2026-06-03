package objetos;

public class Loja {
    private static int contador = 0;
	private int idLoja;
    private String nomeFantasia, razaoSocial, cnpj;
    private Endereco endereco;


    public Loja(String nomeFantasia, String razaoSocial, String cnpj, String cidade, String bairro, String rua) {
        this.idLoja = ++contador;
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        Endereco end = new Endereco();
        end.setCidade(cidade);
        end.setBairro(bairro);
        end.setRua(rua);

        this.setEndereco(end);
    }

    public void setEndereco(Endereco endereco) {
         this.endereco = endereco; 
    }
    public Endereco getEndereco() { 
        return endereco; 
    }
    public int getIdLoja() {   
        return idLoja; 
    }

    public String getNomeFantasia() { 
        return nomeFantasia; 
    }
    public void setNomeFantasia(String nomeFantasia) { 
        this.nomeFantasia = nomeFantasia;
     }

    public String getRazaoSocial() { 
        return razaoSocial; 
    }
    public void setRazaoSocial(String razaoSocial) { 
        this.razaoSocial = razaoSocial;
     }

    public String getCnpj() { 
        return cnpj; 
    }
    public void setCnpj(String cnpj) { 
        this.cnpj = cnpj; 
    }

    public void apresentarse() {
        System.out.println("ID: " + idLoja + " | Unidade: " + nomeFantasia + " | CNPJ: " + cnpj);
        if (endereco != null) {
            endereco.apresentarLogradouro();
        }
    }
}