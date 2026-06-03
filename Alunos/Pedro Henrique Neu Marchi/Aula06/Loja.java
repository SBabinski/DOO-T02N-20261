public class Loja {

    String nomeFantasia;
    String razaoSocial;
    String cnpj;

    String cidade;
    String bairro;
    String rua;
    String numero;

    Vendedor[] vendedores;
    Cliente[] clientes;

    public void contarClientes() {
        if (clientes == null) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            System.out.println("Total de clientes: " + clientes.length);
        }
    }

    public void contarVendedores() {
        if (vendedores == null) {
            System.out.println("Nenhum vendedor cadastrado.");
        } else {
            System.out.println("Total de vendedores: " + vendedores.length);
        }
    }

    public void apresentarSe() {
        System.out.println("Loja: " + nomeFantasia);
        System.out.println("CNPJ: " + cnpj);
        System.out.println("Endereço: " + cidade + ", " + bairro + ", " + rua + ", " + numero);
    }
}