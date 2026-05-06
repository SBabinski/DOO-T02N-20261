import java.util.ArrayList;

public class Loja {

    private String nomeFantasia;
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<Vendedor> vendedores = new ArrayList<>();
    private ArrayList<Pedido> pedidos = new ArrayList<>();

    public Loja(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void adicionarCliente(Cliente c) {
        clientes.add(c);
    }

    public void adicionarVendedor(Vendedor v) {
        vendedores.add(v);
    }

    public void adicionarPedido(Pedido p) {
        pedidos.add(p);
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public ArrayList<Vendedor> getVendedores() {
        return vendedores;
    }

    public void listarClientes() {
        for (Cliente c : clientes) c.apresentarSe();
    }

    public void listarVendedores() {
        for (Vendedor v : vendedores) v.apresentarSe();
    }

    public void listarPedidos() {
        for (Pedido p : pedidos) {
            p.gerarDescricaoVenda();
            System.out.println("------------------");
        }
    }
}