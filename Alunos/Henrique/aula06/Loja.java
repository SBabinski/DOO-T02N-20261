package fag;

import java.util.ArrayList;

public class Loja {

    ArrayList<Vendedor> vendedores = new ArrayList<>();
    ArrayList<Cliente> clientes = new ArrayList<>();

    String nomeFantasia;
    String razaoSocial;
    String cnpj;
    String cidade;
    String bairro;
    String rua;

public Loja(String nomeFantasia, String razaoSocial, String cnpj, String cidade, String bairro, String rua) {
    this.nomeFantasia = nomeFantasia;
    this.razaoSocial = razaoSocial;
    if (cnpj.length() == 14) {
        this.cnpj = cnpj;
    } else {
        System.out.println("CNPJ inválido. Por favor tente novamente.");
    }
    this.cidade = cidade;
    this.bairro = bairro;
    this.rua = rua;
}

public void contarClientes() {
    System.out.println("Número de clientes: " + clientes.size());
}

public void contarVendedores() {
    System.out.println("Número de vendedores: " + vendedores.size());
}

public void apresentarse() {
	System.out.println("----APRESENTAÇÃO DA LOJA----");
	System.out.println("Nome Fantasia: "+nomeFantasia);
	System.out.println("CNPJ: "+cnpj);
	System.out.println("Endereço: "+rua + " " + bairro + " "+ cidade);
}

}