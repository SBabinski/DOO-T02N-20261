public class Loja {

    String nomeFantasia;
    String razaoSocial;
    String cnpj;
    String cidade;
    String bairro;
    String rua;
    Vendedor[] vendedores;
    Cliente[] clientes;

    int contarClientes() {
        return clientes.length;
    }

    int contarVendedores() {
        return vendedores.length;
    }

    void apresentarse() {
        System.out.println("Loja: " + nomeFantasia);
        System.out.println("CNPJ: " + cnpj);
        System.out.println("Endereco: " + rua + ", " + bairro + ", " + cidade);
    }
}