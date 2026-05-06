public class Loja {

    String nomeFantasia;
    String razaoSocial;
    String cnpj;
    Endereco endereco;
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
        endereco.apresentarLogradouro();
    }
}